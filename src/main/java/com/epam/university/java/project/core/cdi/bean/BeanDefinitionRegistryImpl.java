package com.epam.university.java.project.core.cdi.bean;

import java.util.Collection;
import java.util.Map;

public class BeanDefinitionRegistryImpl implements BeanDefinitionRegistry {
    private Map<String, BeanDefinition> registryById;
    private Map<Class<?>, BeanDefinition> registryByClass;
    private Collection<BeanDefinition> registry;

    @Override
    public void addBeanDefinition(BeanDefinition definition) {
        try {
            registryById.put(definition.getId(), definition);
            registryByClass.put(Class.forName(definition.getClassName()), definition);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return registryById.get(beanId);
    }

    public BeanDefinition getBeanDefinition(Class<?> beanClass) throws ClassNotFoundException {
        for (BeanDefinition bean : registry) {
            if ((Class.forName(bean.getClassName()).getInterfaces()[0]).equals(beanClass)) {
                return bean;
            }
        }
        return null;
    }
}
