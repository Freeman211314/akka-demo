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

package com.beauty.akka.thread;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;

/**
 * 功能描述: 1 查询总个数 <p>
 *
 * @author : fujian <p>
 * @version 1.0 2017-12-06
 * @since Homework 1.0
 */
public class GetAllUsersRunnable implements Runnable
{

    private static String CAT_TYPE = GetAllUsersRunnable.class.getSimpleName();


    private Object message;

    public void run()
    {
        Transaction transaction = Cat.newTransaction(CAT_TYPE, getClass().getSimpleName());
        try
        {
            if (message instanceof String)
            {
                System.out.println("GetAllUsersRunnable开始" + message + "   " + System.currentTimeMillis());
                Thread.sleep(500);
                System.out.println("GetAllUsersRunnable结束" + message + "   " + System.currentTimeMillis());
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

    public GetAllUsersRunnable(Object message)
    {
        this.message = message;
    }

    public void setMessage(Object message)
    {
        this.message = message;
    }
    
}
