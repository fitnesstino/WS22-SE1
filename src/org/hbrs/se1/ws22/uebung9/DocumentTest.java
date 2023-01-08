package org.hbrs.se1.ws22.uebung9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {
    GraficDocument graficDocument = new GraficDocument("localhost:8080");
    TextDocument textDocument = new TextDocument("Ein Text!", TextDocument.Encoding.UTF8);
    ComplexDocument complexDocument = new ComplexDocument();
    @Test
    void idTest(){
        int id = ( int ) ( Math.random() * Integer.MAX_VALUE );
        graficDocument.setID(id);
        assertEquals( id , graficDocument.getID( ) );

        id = ( int ) ( Math.random() * Integer.MAX_VALUE );
        textDocument.setID(id);
        assertEquals( id , textDocument.getID( ) );

        id = ( int ) ( Math.random() * Integer.MAX_VALUE );
        complexDocument.setID(id);
        assertEquals( id , complexDocument.getID( ) );
    }

    @Test
    void contentTest(){
        assertEquals("localhost:8080",graficDocument.getUrl());
        assertEquals("Ein Text!", textDocument.getContent());
    }

    @Test
    void complexTest(){
        textDocument.setID(123);
        graficDocument.setID(456);
        complexDocument.add(textDocument);
        complexDocument.add(graficDocument);
        assertEquals(textDocument,complexDocument.get(123));
        assertEquals(graficDocument,complexDocument.get(456));
        int size = complexDocument.size();
        complexDocument.remove(graficDocument);
        assertTrue(complexDocument.size() < size);
    }

    @Test
    void sizeTest(){
        assertTrue((new TextDocument("Ein Text!", TextDocument.Encoding.UTF8)).size()<(new TextDocument("Ein Text!", TextDocument.Encoding.UTF16)).size());
        assertTrue((new TextDocument("Ein Text!", TextDocument.Encoding.UTF16)).size()<(new TextDocument("Ein Text!", TextDocument.Encoding.UTF32)).size());
        assertEquals(1200,graficDocument.size());
        complexDocument.add(textDocument);
        complexDocument.add(graficDocument);
        assertEquals(textDocument.size()+ graficDocument.size(),complexDocument.size());
    }
}