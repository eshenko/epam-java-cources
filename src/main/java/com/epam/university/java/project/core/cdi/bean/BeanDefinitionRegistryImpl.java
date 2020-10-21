package com.epam.university.java.project.core.cdi.bean;

import java.util.ArrayList;
import java.util.Collection;

public class BeanDefinitionRegistryImpl implements BeanDefinitionRegistry {
    //private final Map<String, BeanDefinition> registry = new HashMap<>();
    private final Collection<BeanDefinition> registry = new ArrayList<>();

    @Override
    public void addBeanDefinition(BeanDefinition definition) {
        registry.add(definition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        BeanDefinition result = null;
        for (BeanDefinition bean : registry) {
            if (bean.getId().equals(beanId)) {
                result = bean;
            }
        }
        return result;
    }
}
