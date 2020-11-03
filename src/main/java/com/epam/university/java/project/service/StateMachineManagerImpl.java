package com.epam.university.java.project.service;

import com.epam.university.java.project.core.cdi.io.Resource;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinitionImpl;
import com.epam.university.java.project.core.state.machine.domain.StateMachineEventHandler;
import com.epam.university.java.project.core.state.machine.domain.StateMachineState;
import com.epam.university.java.project.core.state.machine.domain.StateMachineStateImpl;
import com.epam.university.java.project.core.state.machine.domain.StatefulEntity;
import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;
import com.epam.university.java.project.domain.BookEvent;
import com.epam.university.java.project.domain.BookStatus;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class StateMachineManagerImpl implements StateMachineManager {
    @Override
    @SuppressWarnings("unchecked")
    public StateMachineDefinition<?, ?> loadDefinition(Resource resource) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(
                    StateMachineDefinitionImpl.class,
                    StateMachineStateImpl.class
            );
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (StateMachineDefinition<BookStatus, BookEvent>) unmarshaller.unmarshal(resource.getFile());
        } catch (JAXBException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public <S, E> StatefulEntity<S, E> initStateMachine(StatefulEntity<S, E> entity,
                                                        StateMachineDefinition<S, E> definition) {
        entity.setStateMachineDefinition(definition);
        entity.setState(definition.getStartState());
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S, E> StatefulEntity<S, E> handleEvent(StatefulEntity<S, E> entity, E event) {
        StateMachineDefinition<S, E> definition = entity.getStateMachineDefinition();
        Class<? extends StateMachineEventHandler> handlerClass = definition.getHandlerClass();
        Collection<StateMachineState<S, E>> states = definition.getStates();
        try {
            StateMachineEventHandler handler = Objects.requireNonNull(handlerClass)
                    .getConstructor().newInstance();
            for (StateMachineState<S, E> state : states) {
                if (event.equals(state.getOn()) && entity.getState().equals(state.getFrom())) {
                    String method = state.getMethodToCall();
                    entity = (StatefulEntity<S, E>) handlerClass
                            .getDeclaredMethod(method, entity.getClass())
                            .invoke(handler, entity);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }
        return entity;
    }
}
