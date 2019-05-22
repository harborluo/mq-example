package com.example.demo;





import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by harbor on 2019/5/22.
 */
public class TimeBucket {

    LoadingCache<Long, AtomicLong> counter = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.SECONDS)
            .build(new CacheLoader<Long, AtomicLong>() {
                @Override
                public AtomicLong load(Long aLong) throws Exception {
                    return new AtomicLong(0);
                }
            });

    public void bucketLimit(long limit){



//        while (true){
        long currentSecond = System.currentTimeMillis()/1000;
        try {
            if (counter.get(currentSecond).incrementAndGet()>limit){
                System.out.println(new Date()+"limit exceed....");
//                continue;
            }else {
                //do business logic
                System.out.println(new Date()+"do something  business....");
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        }

    }

    @Test
    public void testBucket(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            executorService.execute(()->{
                bucketLimit(5);
            });
        }

        executorService.shutdown();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



}
