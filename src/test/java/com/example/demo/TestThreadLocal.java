package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by harbor on 2019/5/19.
 */
public class TestThreadLocal {

    static ThreadLocal<AtomicInteger> sequencer = ThreadLocal.withInitial(()-> new AtomicInteger(0));

    static class Task implements Runnable{

//        ThreadLocal<AtomicInteger> sequencer = ThreadLocal.withInitial(()-> new AtomicInteger(0));

        @Override
        public void run() {
            int value = sequencer.get().getAndIncrement();
            System.out.println(Thread.currentThread().getName() + " " + value);
//            sequencer.remove();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for(int i=0;i<10;i++) {
            executor.execute(new Task());
        }
        executor.shutdown();
    }


}
