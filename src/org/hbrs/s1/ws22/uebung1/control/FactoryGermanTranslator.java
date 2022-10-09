package org.hbrs.s1.ws22.uebung1.control;

/**
 * Anwendung des Factory Method Design Pattern
 */

public class FactoryGermanTranslator {

    public static Translator createGermanTranslator() {
        return new GermanTranslator();
    }
}
