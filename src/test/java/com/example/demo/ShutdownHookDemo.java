package com.example.demo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by harbor on 2019/5/20.
 */
public class ShutdownHookDemo {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("do some work before shutting down....");
        }));
        System.out.println("main thread shutdown.");

        ExecutorService executorService = Executors.newFixedThreadPool(10000);

//        final Map<String,String> map = new HashMap<>();
        final Map<String,String> map = Collections.synchronizedMap(new HashMap<>());



        final AtomicInteger cnt = new AtomicInteger(0);
        for(int i=0;i<10000;i++) {
            executorService.execute(() -> {
                map.put("test"+cnt.incrementAndGet(),"");
            });
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        System.out.println(map.size());




    }
}
