package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by harbor on 2019/5/18.
 */
//@RunWith(SpringRunner.class)
public class ConsoleDemoTest
{
    @Test
    public  void testConsole() {
        final String msg="abc";
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(()->{
            System.out.println(msg);
        });
        executorService.execute(()->{
            System.out.println(msg);
        });
        executorService.execute(()->{
            System.out.println(msg);
        });

    }
}
