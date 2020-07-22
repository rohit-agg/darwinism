package com.codegym.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {

    SomeInterfaceWithMethods object;

    public CustomInvocationHandler(SomeInterfaceWithMethods param) {
        object = param;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(method.getName() + " in");
        Object result = method.invoke(object, args);
        System.out.println(method.getName() + "  out");
        return result;
    }
}
