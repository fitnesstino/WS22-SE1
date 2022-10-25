package org.hbrs.se1.ws22.uebung3.persistence;

import org.hbrs.se1.ws22.uebung2.Container;

public class Main {
    public static void main(String[] args){
        Container container=Container.getInstanz();
        container.setPersistenceStrategy(new PersistenceStrategyStream<>());    //Im Folgenden nicht benötigt
        Client.fill_then_dump(container);   //Übergabewert nur zur Klarheit, alle Objekte greifen auf selbes Container Objekt zu
    }
}
