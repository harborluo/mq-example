package com.example.demo;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by harbor on 2019/5/21.
 */
public class DynamicProxyTest {
    interface IHello {
        void sayHello();
    }
    static class Hello implements IHello{
        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }
    static class DynamicProxy implements InvocationHandler {

        Object targetObject;

        Object bind(Object obj){
            this.targetObject = obj;
            return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);

        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(targetObject, args);
        }

    }

    public static void main(String[] args) {
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();
    }
}
