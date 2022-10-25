package org.hbrs.se1.ws22.uebung2;

import org.hbrs.se1.ws22.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws22.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws22.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws22.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ContainerTest {
    Container container=Container.getInstanz();

    @Test
    void containerTest(){ //aus Übung 2
        Member member1 =new ConcreteMember(5);
        Member member2 =new ConcreteMember(328);
        Member member3 =new ConcreteMember(66);
        assertDoesNotThrow(()->container.addMember(member1));
        assertEquals(1,container.size());
        assertDoesNotThrow(()->container.addMember(member2));
        assertEquals(2,container.size());
        assertDoesNotThrow(()->container.addMember(member3));
        assertEquals(3,container.size());
        assertThrows(ContainerException.class, ()->container.addMember(member1),
                "Das Member-Objekt mit der ID 5 ist bereits vorhanden!");
        assertNotNull(container.deleteMember(328));
        assertEquals(2,container.size());
        assertNull(container.deleteMember(328));
    }

    @Test
    void nullStrategyTest(){
        container.setPersistenceStrategy(null); //not necessary, just to be sure
        assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet,assertThrows(PersistenceException.class, ()->container.load(), "Es ist keine Strategie gesetzt!").getExceptionTypeType());
        assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet,assertThrows(PersistenceException.class, ()->container.load(), "Es ist keine Strategie gesetzt!").getExceptionTypeType());
    }

    @Test
    void useMongoDBTest(){
        container.setPersistenceStrategy(new PersistenceStrategyMongoDB<>());
        assertThrows(UnsupportedOperationException.class, ()->container.load());
        assertThrows(UnsupportedOperationException.class, ()->container.store());
    }

    @Test
    void fileDoesNotExistTest(){
        PersistenceStrategyStream<Member> persistenceStrategy=new PersistenceStrategyStream<>();
        persistenceStrategy.setLocation("doesnotexist.ser");
        container.setPersistenceStrategy(persistenceStrategy);
        assertEquals(PersistenceException.ExceptionType.FileNotFound,assertThrows(PersistenceException.class, ()->container.load()).getExceptionTypeType());
    }

    @Test
    void directoryTest(){
        PersistenceStrategyStream<Member> persistenceStrategy=new PersistenceStrategyStream<>();
        persistenceStrategy.setLocation("./");  //current Directory instead of file
        container.setPersistenceStrategy(persistenceStrategy);
        assertEquals(PersistenceException.ExceptionType.FileNotFound,assertThrows(PersistenceException.class, ()->container.load()).getExceptionTypeType());
        assertEquals(PersistenceException.ExceptionType.FileNotFound,assertThrows(PersistenceException.class, ()->container.store()).getExceptionTypeType());
    }

    @Test
    void storeLoadTest(){
        Member member=new ConcreteMember(3);
        container.setPersistenceStrategy(new PersistenceStrategyStream<>());
        assertEquals(0,container.size());
        assertDoesNotThrow(()->container.addMember(member));
        assertEquals(1,container.size());
        assertDoesNotThrow(()->container.store());
        assertNotNull(container.deleteMember(member.getID()));
        assertEquals(0,container.size());
        assertDoesNotThrow(()->container.load());
        assertEquals(1,container.size());
        assertEquals("3",container.deleteMember(3));
    }
}