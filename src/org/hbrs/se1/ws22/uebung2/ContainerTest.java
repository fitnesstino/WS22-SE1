package org.hbrs.se1.ws22.uebung2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    Container container = new Container();

    ConcreteMember member1 = new ConcreteMember(-17);
    ConcreteMember member2 = new ConcreteMember(328);
    ConcreteMember member3 = new ConcreteMember(5);

    @Test
    void testContainer() {
        assertEquals(0, container.size());
        assertDoesNotThrow(() -> container.addMember(member1));
        assertEquals(1, container.size());
        assertDoesNotThrow(() -> container.addMember(member2));
        assertEquals(2, container.size());
        assertDoesNotThrow(() -> container.addMember(member3));
        assertEquals(3, container.size());
        assertThrows(ContainerException.class, () -> container.addMember(member1), "Das Member-Objekt mit der ID -17 ist bereits vorhanden!");
        assertNotNull(container.deleteMember(328));
        assertEquals(2, container.size());
        assertNull(container.deleteMember(328));
    }


}