package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.structure.ListDefinition;
import com.epam.university.java.project.core.cdi.structure.ListDefinition.ListItemDefinition;
import com.epam.university.java.project.core.cdi.structure.MapDefinition;
import com.epam.university.java.project.core.cdi.structure.MapDefinition.MapEntryDefinition;
import com.epam.university.java.project.core.cdi.structure.StructureDefinition;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BeanFactoryImpl implements BeanFactory {
    private final BeanDefinitionRegistry registry;
    private final Map<String, Object> singletons = new HashMap<>();

    public BeanFactoryImpl(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    @Override
    @SuppressWarnings("unchecked")
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
        BeanDefinition definition = registry.getBeanDefinition(beanId);
        if ("singleton".equals(definition.getScope())
                && singletons.containsKey(beanId)) {
            return singletons.get(beanId);
        }
        Object bean;
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
                    } else if (property.getRef() != null) {
                        field.set(bean, getBean(property.getRef()));
                    } else if (property.getValue() != null) {
                        if ((int.class).equals(field.getType())) {
                            field.set(bean, Integer.parseInt(property.getValue()));
                        } else {
                            field.set(bean, property.getValue());
                        }
                    } else if (property.getData() != null) {
                        StructureDefinition data = property.getData();

                        if (data instanceof ListDefinition) {
                            List<String> items = new ArrayList<>();
                            for (ListItemDefinition dataItem : ((ListDefinition) data).getItems()) {
                                items.add(dataItem.getValue());
                            }
                            field.set(bean, items);

                        } else if (data instanceof MapDefinition) {
                            Map<Object, Object> entries = new HashMap<>();
                            for (MapEntryDefinition dataEntry
                                    : ((MapDefinition) data).getValues()) {
                                if (dataEntry.getValue() != null && dataEntry.getRef() != null) {
                                    throw new RuntimeException();
                                } else if (dataEntry.getValue() != null) {
                                    entries.put(dataEntry.getKey(), dataEntry.getValue());
                                } else if (dataEntry.getRef() != null) {
                                    entries.put(dataEntry.getKey(), getBean(dataEntry.getRef()));
                                }
                            }
                            field.set(bean, entries);
                        }
                    }
                }
            }
            if ("singleton".equals(definition.getScope())) {
                singletons.put(definition.getId(), bean);
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return (T) getBean(beanName);
    }
}
