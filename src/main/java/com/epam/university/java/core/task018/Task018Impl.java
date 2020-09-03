package com.epam.university.java.core.task018;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task018Impl implements Task018 {
    @Override
    public boolean isAnnotationPresent(Object toCheck, Class<?> annotationToFind) {
        Class<?> clazz = toCheck.getClass();
        List<Annotation> annotations = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            annotations.addAll(Arrays.asList(field.getDeclaredAnnotations()));
        }
        for (Constructor constructor : clazz.getDeclaredConstructors()) {
            annotations.addAll(Arrays.asList(constructor.getDeclaredAnnotations()));
        }
        for (Method method : clazz.getDeclaredMethods()) {
            annotations.addAll(Arrays.asList(method.getDeclaredAnnotations()));
            for (Parameter parameter : method.getParameters()) {
                annotations.addAll(Arrays.asList(parameter.getAnnotations()));
            }
        }
        annotations.addAll(Arrays.asList(clazz.getPackage().getAnnotations()));
        annotations.addAll(Arrays.asList(clazz.getAnnotations()));

        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(annotationToFind)) {
                return true;
            }
        }
        return false;
    }
}
