package org.hbrs.se1.ws22.uebung2;

public class ContainerException extends Exception{

    public ContainerException(Integer id) {
        super("Das Member-Objekt mit der ID " + id + "ist bereits vorhanden!");
    }
}
