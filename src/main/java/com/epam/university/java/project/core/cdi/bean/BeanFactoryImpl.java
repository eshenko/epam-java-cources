package com.epam.university.java.project.core.cdi.bean;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

public class BeanFactoryImpl implements BeanFactory {
    private final BeanDefinitionRegistry registry;

    public BeanFactoryImpl(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        String className = beanClass.getSimpleName();
        return (T) getBean(className);
        //return (T) getBean(className.substring(0, 1)
        //        .toLowerCase() + className.substring(1));
/*        if (beanClass.isInterface()) {
            Set<Class<? extends T>> realizations = new Reflections("com.epam.university.java.project")
                    .getSubTypesOf(beanClass);
            for (Class<? extends T> realization : realizations) {
                if (registry.getBeanDefinition(realization.getName()) != null) {
                    result = (T) registry.getBeanDefinition(realization.getName());
                }

            }
        }*/
    }

    @Override
    public Object getBean(String beanName) {
        Object bean;
        String beanId = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
        BeanDefinition definition = registry.getBeanDefinition(beanId);
        try {
            bean = Objects.requireNonNull(Class.forName(definition.getClassName()))
                    .getConstructor().newInstance();
            Collection<BeanPropertyDefinition> properties = definition.getProperties();
            if (properties != null) {
                for (BeanPropertyDefinition property : properties) {
                    Field field = bean.getClass().getDeclaredField(property.getName());
                    field.setAccessible(true);
                    if (property.getRef() != null) {
                        field.set(bean, getBean(property.getRef()));
                    }
                }
            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return null;
    }
}
