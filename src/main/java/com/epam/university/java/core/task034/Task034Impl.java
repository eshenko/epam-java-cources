package com.epam.university.java.core.task034;

import org.xml.sax.helpers.DefaultHandler;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class Task034Impl implements Task034 {
    @Override
    public Person readWithSaxParser(DefaultHandler handler, String filepath) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File("src/main/resources" + filepath), handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ((SaxHandlerImpl) handler).getPerson();
    }

    @Override
    public Person readWithJaxbParser(String filepath) {
        JAXBContext context;
        Unmarshaller unmarshaller;
        Person person = null;
        try {
            context = JAXBContext.newInstance(PersonImpl.class);
            unmarshaller = context.createUnmarshaller();
            person = (Person) unmarshaller
                    .unmarshal(new File("src/main/resources" + filepath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person readWithStaxParser(XMLStreamReader streamReader) {
        Person person = new PersonImpl();
        Collection<PhoneNumber> phones = new ArrayList<>();
        try {
            while (streamReader.hasNext()) {
                if (streamReader.next() == XMLEvent.START_ELEMENT) {
                    if ("person".equals(streamReader.getLocalName())) {
                        person.setId(Integer.parseInt(
                                streamReader.getAttributeValue(null, "id")));
                    }
                    if ("first-name".equals(streamReader.getLocalName())) {
                        person.setFirstName(streamReader.getElementText());
                    }
                    if ("last-name".equals(streamReader.getLocalName())) {
                        person.setLastName(streamReader.getElementText());
                    }
                    if ("person-phone".equals(streamReader.getLocalName())) {
                        PhoneNumber phone = new PhoneNumberImpl();
                        phone.setPhoneNumber(streamReader.getElementText());
                        phones.add(phone);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        person.setPhoneNumbers(phones);
        return person;
    }
}
