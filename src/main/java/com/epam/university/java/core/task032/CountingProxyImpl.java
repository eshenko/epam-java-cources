package com.epam.university.java.core.task032;

import java.lang.reflect.Method;

public class CountingProxyImpl implements CountingProxy {
    private int countAction = 0;
    private int countAnotherAction = 0;
    private Object someActionExecutor;

    @Override
    public int getInvocationsCount(String methodName) {
        if (methodName.equals("doAction")) {
            return countAction;
        } else if (methodName.equals("doAnotherAction")) {
            return countAnotherAction;
        }
        return 0;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("doAction")) {
            this.countAction++;
            return method.invoke(someActionExecutor, args);
        } else if (method.getName().equals("doAnotherAction")) {
            this.countAnotherAction++;
            return method.invoke(someActionExecutor, args);
        }
        return null;
    }

    public void setSomeActionExecutor(Object someActionExecutor) {
        this.someActionExecutor = someActionExecutor;
    }
}
