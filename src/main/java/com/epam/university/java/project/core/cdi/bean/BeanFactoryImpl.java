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
        return (T) getBean(beanClass.getSimpleName());
    }

    @Override
    public Object getBean(String beanName) {
        if (beanName.endsWith("Interface")) {
            beanName = beanName.substring(0, beanName.length() - "Interface".length());
        }
        if (beanName.startsWith("Default")) {
            beanName = beanName.substring("Default".length());
        }
        String beanId = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
        Object bean;
        BeanDefinition definition = registry.getBeanDefinition(beanId);
        try {
            bean = Objects.requireNonNull(Class.forName(definition.getClassName()))
                    .getConstructor().newInstance();
            Collection<BeanPropertyDefinition> properties = definition.getProperties();
            if (properties != null) {
                for (BeanPropertyDefinition property : properties) {
                    Field field = bean.getClass().getDeclaredField(property.getName());
                    field.setAccessible(true);
                    if (property.getRef() == null
                            && property.getValue() == null
                            && property.getData() == null) {
                        throw new RuntimeException();
                    }
                    if (property.getRef() != null) {
                        field.set(bean, getBean(property.getRef()));
                    }
                    if (property.getValue() != null) {
                        if ((int.class).equals(field.getType())) {
                            field.set(bean, Integer.parseInt(property.getValue()));
                        } else {
                            field.set(bean, property.getValue());
                        }
                    }
/*                    if (property.getData() != null) {
                        field.set(bean, getBean(property.getData()));
                    }*/
                }
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return (T) getBean(beanClass.getSimpleName());
    }
}
