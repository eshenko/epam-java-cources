package com.epam.university.java.core.task060;

import java.io.Serializable;
import java.util.Collection;

public interface PersonSerializable extends Serializable {

    void setFullName(String fullName);

    void setAge(int age);

    void setMale(boolean male);

    void setSpouse(PersonSerializable spouse);

    void setChildren(Collection<PersonSerializable> children);
}
