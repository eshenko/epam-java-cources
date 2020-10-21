package com.epam.university.java.project.core.cdi.bean;

import org.reflections.Reflections;

import java.util.Set;

public class BeanFactoryImpl implements BeanFactory {
    private final BeanDefinitionRegistry registry;

    public BeanFactoryImpl(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        T result = null;
        if (beanClass.isInterface()) {
            Set<Class<? extends T>> realizations = new Reflections().getSubTypesOf(beanClass);
            for (Class<? extends T> realization : realizations) {
                if (registry.getBeanDefinition(realization.getName()) != null) {
                    result = (T) registry.getBeanDefinition(realization.getName());
                }

            }
        }
        return result;
    }

    @Override
    public Object getBean(String beanName) {
        return registry.getBeanDefinition(beanName);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return null;
    }
}
