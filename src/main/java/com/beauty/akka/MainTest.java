/*
 * Create Author  : fujian
 * 功能描述:<p>
 *
 * Create Date    : 2017-12-14
 * Project        : beauty-akka
 * File Name      : MainTest.java
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package com.beauty.akka;

import com.beauty.akka.akka.PushMessageService;
import com.beauty.akka.thread.GetAllUsersRunnable;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 功能描述:  <p>
 *
 * @author : fujian <p>
 * @version 1.0 2017-12-14
 * @since beauty-akka 1.0
 */
public class MainTest
{
    public static Executor executor = Executors.newFixedThreadPool(4);

    public static void actor()
    {
        PushMessageService pushMessageService = new PushMessageService();
        long begin = System.currentTimeMillis();
        System.out.println("actor 最开始的地方" + begin);
        for (int i = 0; i < 10; i++)
        {
            pushMessageService.tell("" + i);
        }
        long end = System.currentTimeMillis();
        System.out.println("actor 最结束的地方" + end);
        System.out.println(end - begin);
//        pushMessageService.shutDown();
    }

    public static void main(String[] args) {
        thread();
//        actor();
    }


    public static void thread()
    {
        long begin = System.currentTimeMillis();
        System.out.println("thread 最开始的地方" + begin);
        GetAllUsersRunnable getAllUsersRunnable = new GetAllUsersRunnable("");
        for (int i = 0; i < 10; i++)
        {
            getAllUsersRunnable.setMessage("" + i);
            executor.execute(getAllUsersRunnable);
        }
        long end = System.currentTimeMillis();
        System.out.println("thread 最结束的地方" + end);
        System.out.println(end - begin);
    }

}
