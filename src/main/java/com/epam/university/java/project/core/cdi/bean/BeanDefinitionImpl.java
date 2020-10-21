package com.epam.university.java.project.core.cdi.bean;

import java.util.Collection;

public class BeanDefinitionImpl implements BeanDefinition {
    private String id;
    private String className;
    private Collection<BeanPropertyDefinition> properties;
    private String postConstruct;
    private String scope;

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public String getClassName() {
        return null;
    }

    @Override
    public void setClassName(String className) {

    }

    @Override
    public Collection<BeanPropertyDefinition> getProperties() {
        return null;
    }

    @Override
    public void setProperties(Collection<BeanPropertyDefinition> properties) {

    }

    @Override
    public String getPostConstruct() {
        return null;
    }

    @Override
    public void setPostConstruct(String methodName) {

    }

    @Override
    public String getScope() {
        return null;
    }

    @Override
    public void setScope(String scope) {

    }
}
