package com.epam.university.java.project.core.cdi.context;

import com.epam.university.java.project.core.cdi.bean.BeanDefinitionReader;
import com.epam.university.java.project.core.cdi.bean.BeanDefinitionReaderImpl;
import com.epam.university.java.project.core.cdi.bean.BeanDefinitionRegistry;
import com.epam.university.java.project.core.cdi.bean.BeanDefinitionRegistryImpl;
import com.epam.university.java.project.core.cdi.bean.BeanFactory;
import com.epam.university.java.project.core.cdi.bean.BeanFactoryImpl;
import com.epam.university.java.project.core.cdi.io.Resource;

import java.util.Collection;

public class ApplicationContextImpl implements ApplicationContext {
    BeanDefinitionRegistry registry = new BeanDefinitionRegistryImpl();
    BeanDefinitionReader reader = new BeanDefinitionReaderImpl(registry);
    BeanFactory factory = new BeanFactoryImpl(registry);

    @Override
    public int loadBeanDefinitions(Resource resource) {
        return reader.loadBeanDefinitions(resource);
    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        return reader.loadBeanDefinitions(resources);
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return factory.getBean(beanClass);
    }

    @Override
    public Object getBean(String beanName) {
        return factory.getBean(beanName);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return factory.getBean(beanName, beanClass);
    }
}
