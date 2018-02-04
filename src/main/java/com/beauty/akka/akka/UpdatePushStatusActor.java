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

import akka.actor.UntypedActor;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;

/**
 * 功能描述: 功能聚合 Actor <p>
 *
 * @author : fujian <p>
 * @version 1.0 2017-12-06
 * @since Homework 1.0
 */
public class UpdatePushStatusActor extends UntypedActor
{

    private static String CAT_TYPE = UpdatePushStatusActor.class.getSimpleName();

    @Override
    public void onReceive(Object message) throws Exception
    {
        Transaction transaction = Cat.newTransaction(CAT_TYPE, getClass().getSimpleName());
        try
        {
            
            transaction.setStatus(Transaction.SUCCESS);
        }
        catch (Exception e)
        {
            Cat.logEvent(CAT_TYPE, "UpdatePushStatusActor err");
            transaction.setStatus(e);
        }
        finally
        {
            transaction.complete();
        }

    }

}
