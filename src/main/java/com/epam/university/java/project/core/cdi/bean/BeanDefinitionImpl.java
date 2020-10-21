package com.epam.university.java.project.core.cdi.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
public class BeanDefinitionImpl implements BeanDefinition {
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "class")
    private String className;
    @XmlElement(name = "property", type = BeanPropertyDefinitionImpl.class)
    private Collection<BeanPropertyDefinition> properties;
    @XmlAttribute(name = "init")
    private String postConstruct;
    @XmlAttribute(name = "scope")
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
