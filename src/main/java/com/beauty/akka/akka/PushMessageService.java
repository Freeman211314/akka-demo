/*
 * Create Author  : fujian
 * 功能描述:<p>
 * 
 * Create Date    : 2017-12-06
 * Project        : Homework
 * File Name      : Test.java
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package com.beauty.akka.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinRouter;
import com.typesafe.config.ConfigFactory;

/**
 * 功能描述: push消息service <p>
 *
 * @author : fujian <p>
 * @version 1.0 2017-12-06
 * @since Homework 1.0
 */
public class PushMessageService 
{
    private ActorSystem system;

    private ActorRef queryAllUsersActor;

    private ActorRef pushByPageActor;

    {
        ActorSystem system = ActorSystem.create("WechatPush", ConfigFactory.load());
        this.system = system;
        // 3、执行完以后执行完后，更新到数据库中
        ActorRef reduceRouter = system.actorOf(Props.create(UpdatePushStatusActor.class)
                                                       .withDispatcher("push-update-dispatcher")
                                                       .withRouter(new RoundRobinRouter(20)));
        // 2、多workers分页push消息
        ActorRef mapRouter = system.actorOf(Props.create(PushActor.class, reduceRouter)
                                                    .withMailbox("bounded-mailbox-push")
                                                    .withDispatcher("push-calling-dispatcher")
                                                    .withRouter(new RoundRobinRouter(Integer.valueOf(40))));
        this.pushByPageActor = mapRouter;
        // 1、查询总数
        ActorRef wechatPushActor = system.actorOf(Props.create(GetAllUsersActor.class,mapRouter)
                                                          .withMailbox("bounded-mailbox-getAllUser")
                                                          .withDispatcher("push-thread-pool-dispatcher")
                                                          .withRouter(new RoundRobinRouter(Integer.valueOf(4))));
        this.queryAllUsersActor = wechatPushActor;
    }

    public void tell(String message)
    {
        this.queryAllUsersActor.tell(message, this.queryAllUsersActor);
    }

    public void shutDown()
    {
        this.system.shutdown();
    }

}
