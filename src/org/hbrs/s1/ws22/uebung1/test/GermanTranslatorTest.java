package org.hbrs.s1.ws22.uebung1.test;

import static org.junit.jupiter.api.Assertions.*;
import org.hbrs.s1.ws22.uebung1.control.*;

class GermanTranslatorTest {

    Translator translator;
    @org.junit.jupiter.api.Test
    void testTranslateNumber() {
        translator = FactoryGermanTranslator.createGermanTranslator();
        assertEquals("vier", translator.translateNumber(4));
        assertEquals("Übersetzung der Zahl -3 nicht möglich. Version: 1.0", translator.translateNumber(-3));
        assertEquals("Übersetzung der Zahl 15 nicht möglich. Version: 1.0", translator.translateNumber(15));
        assertEquals("Übersetzung der Zahl 0 nicht möglich. Version: 1.0", translator.translateNumber(0));
    }
}