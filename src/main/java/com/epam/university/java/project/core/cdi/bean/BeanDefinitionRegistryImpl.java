package com.epam.university.java.project.core.cdi.bean;

import java.util.ArrayList;
import java.util.Collection;

public class BeanDefinitionRegistryImpl implements BeanDefinitionRegistry {
    private final Collection<BeanDefinition> registry = new ArrayList<>();

    @Override
    public void addBeanDefinition(BeanDefinition definition) {
        registry.add(definition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        for (BeanDefinition bean : registry) {
            if (bean.getId().equals(beanId)) {
                return bean;
            }
        }
        return null;
    }
}
