package com.example.demo;

/**
 * Created by harbor on 2019/5/20.
 */
public class Child extends Parent {
  public Child(){
      System.out.println("run child constructor");
      count++;
  }

    public static void main(String[] args) {

        System.out.println("begin....");

        System.out.println("Count = " + getCount());
        Child child = new Child();
        System.out.println("Count = " + getCount());

        System.out.println("--------------------------");
        int cnt =0;
        cnt++;
        System.out.println("value of cnt after cnt++ is " + cnt);
        cnt=0;
        ++cnt;
        System.out.println("value of cnt after ++cnt is " + cnt);
    }

}

class Parent {

    static protected  int count = 0;

    public Parent(){
        System.out.println("run parent constructor");
        count++;
    }

    static int getCount(){
        return count;
    }

}
