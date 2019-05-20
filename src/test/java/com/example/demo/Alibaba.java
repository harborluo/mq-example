package com.example.demo;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * 　常见的应用场景是对海量数据进行一些统计工作，比如日志分析、用户数统计等。
 *　 有1千万个随机数，随机数的范围在1到1亿之间。现在要求写出一种算法，将1到1亿之间没有在随机数中的数求出来？
 * Created by harbor on 2019/5/20.
 */
public class Alibaba {
    public static void main(String[] args)
    {
        Random random=new Random();

        int range = 10000000;

        range = 10;

        List<Integer> list=new ArrayList<>();
        for(int i=0;i<range;i++)
        {
            int randomResult=random.nextInt(range);
            list.add(randomResult);
        }
        System.out.println("产生的随机数有");
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i));
        }
        BitSet bitSet=new BitSet(range);
        for(int i=0;i<range;i++)
        {
            bitSet.set(list.get(i));
        }

        System.out.println("0-"+range+"不在上述随机数中有"+bitSet.size());
        for (int i = 0; i < range; i++)
        {
            if(!bitSet.get(i))
            {
                System.out.println(i);
            }
        }
    }
}
