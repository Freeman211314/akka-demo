/*
 * Create Author  : fujian
 * 功能描述:<p>
 * 
 * Create Date    : 2017-12-06
 * Project        : Homework
 * File Name      : WCMapReduceActor.java
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package com.beauty.akka.akka;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;

/**
 * 功能描述: 1 查询总个数 <p>
 *
 * @author : fujian <p>
 * @version 1.0 2017-12-06
 * @since Homework 1.0
 */
public class GetAllUsersActor extends UntypedActor
{

    private static String CAT_TYPE = GetAllUsersActor.class.getSimpleName();

    private ActorRef mapRouter;

    @Override
    public void onReceive(Object message)
    {
        Transaction transaction = Cat.newTransaction(CAT_TYPE, getClass().getSimpleName());
        try
        {
            if (message instanceof String)
            {
                System.out.println("GetAllUsersActor开始" + message + "   " + System.currentTimeMillis());
                Thread.sleep(500);
                System.out.println("GetAllUsersActor结束" + message + "   " + System.currentTimeMillis());
            }
//            mapRouter.tell(message, mapRouter);
            transaction.setStatus(Transaction.SUCCESS);
        }
        catch (Exception e)
        {
            Cat.logEvent(CAT_TYPE, "WCMapReduceActor err");
            transaction.setStatus(e);
        }
        finally
        {
            transaction.complete();
        }

    }

    public GetAllUsersActor(ActorRef inMapRouter)
    {
        mapRouter = inMapRouter;
    }
    
}
