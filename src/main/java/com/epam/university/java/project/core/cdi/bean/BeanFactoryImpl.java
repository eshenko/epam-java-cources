package com.epam.university.java.project.core.cdi.bean;

public class BeanFactoryImpl implements BeanFactory {
    private final BeanDefinitionRegistry registry;

    public BeanFactoryImpl(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return registry.addBeanDefinition(beanClass);
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
