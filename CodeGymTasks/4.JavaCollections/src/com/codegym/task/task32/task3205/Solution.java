package com.codegym.task.task32.task3205;

import java.lang.reflect.Proxy;

/*
Creating a proxy object

*/
public class Solution {
    public static void main(String[] args) {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);

        /* Expected output:
        stringMethodWithoutArgs in
        inside stringMethodWithoutArgs
        stringMethodWithoutArgs out
        voidMethodWithIntArg in
        inside voidMethodWithIntArg
        inside voidMethodWithoutArgs
        voidMethodWithIntArg out
        */
    }

    public static SomeInterfaceWithMethods getProxy() {

        SomeInterfaceWithMethods someInterfaceWithMethods = new SomeInterfaceWithMethodsImpl();
        ClassLoader classLoader = someInterfaceWithMethods.getClass().getClassLoader();
        Class<?>[] interfaces = someInterfaceWithMethods.getClass().getInterfaces();
        CustomInvocationHandler customInvocationHandler = new CustomInvocationHandler(someInterfaceWithMethods);

        return (SomeInterfaceWithMethods) Proxy.newProxyInstance(classLoader, interfaces, customInvocationHandler);
    }
}