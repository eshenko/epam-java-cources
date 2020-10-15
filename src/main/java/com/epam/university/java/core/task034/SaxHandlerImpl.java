package com.epam.university.java.core.task034;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.Collection;

public class SaxHandlerImpl extends SaxHandler {
    private final Person person = new PersonImpl();
    private final Collection<PhoneNumber> phoneNumbers = new ArrayList<>();
    private String lastElementName;

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        if (qName.equals("person")) {
            person.setId(Integer.parseInt(attributes.getValue("id")));
        }
        lastElementName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (lastElementName != null && lastElementName.equals("first-name")) {
            person.setFirstName(new String(ch, start, length));
            lastElementName = null;
        }
        if (lastElementName != null && lastElementName.equals("last-name")) {
            person.setLastName(new String(ch, start, length));
            lastElementName = null;
        }
        if (lastElementName != null && lastElementName.equals("person-phone")) {
            PhoneNumber phoneNumber = new PhoneNumberImpl();
            phoneNumber.setPhoneNumber(new String(ch, start, length));
            phoneNumbers.add(phoneNumber);
            lastElementName = null;
        }
    }

    public Person getPerson() {
        person.setPhoneNumbers(phoneNumbers);
        return person;
    }

}
