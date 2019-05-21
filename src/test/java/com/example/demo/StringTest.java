package com.example.demo;

/**
 * Created by harbor on 2019/5/21.
 */
public class StringTest {
    public static void main(String[] args) {
        String s1 = "runoob";
        String s2 = "runoob";
        System.out.println("s1 == s2 is:" + (s1 == s2));

        char c = 65;
        System.out.println("c = "+c);

        StringBuffer a = new StringBuffer("Runoob");
        StringBuffer b = new StringBuffer("Google");
        a.delete(1,3);
        a.append(b);
        System.out.println(a);
    }
}
