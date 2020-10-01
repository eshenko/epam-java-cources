package com.epam.university.java.core.task032;

import java.lang.reflect.Proxy;

public class Task032Impl implements Task032 {
    @Override
    public CountingProxy createProxyWrapper() {
        return new CountingProxyImpl();
    }

    @Override
    public SomeActionExecutor createExecutorWithProxy(CountingProxy proxy) {
        if (proxy == null) {
            throw new IllegalArgumentException();
        }
        SomeActionExecutor before = new SomeActionExecutorImpl();
        ((CountingProxyImpl) proxy).setSomeActionExecutor(before);
        return (SomeActionExecutor) Proxy
                .newProxyInstance(before.getClass().getClassLoader(),
                        before.getClass().getInterfaces(), proxy);
    }
}
