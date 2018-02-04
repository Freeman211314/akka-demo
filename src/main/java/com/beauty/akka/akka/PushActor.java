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
 * 功能描述: map功能Actor <p>
 *
 * @author : fujian <p>
 * @version 1.0 2017-12-06
 * @since Homework 1.0
 */
public class PushActor extends UntypedActor
{

    private static String CAT_TYPE = PushActor.class.getSimpleName();

    private ActorRef reduceActor;

    @Override
    public void onReceive(Object message) {
        Transaction transaction = Cat.newTransaction(CAT_TYPE,getClass().getSimpleName());
        try
        {
            if (message instanceof String)
            {
                System.out.println("PushActor " + message);
            }
            transaction.setStatus(Transaction.SUCCESS);
        }
        catch (Exception e)
        {
            Cat.logEvent(CAT_TYPE, "MapActor err");
            transaction.setStatus(e);
        }
        finally
        {
            transaction.complete();
        }
    }

    public PushActor(ActorRef reduceActor) {
        this.reduceActor = reduceActor;
    }
    

}
