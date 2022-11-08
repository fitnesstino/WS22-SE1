package org.hbrs.se1.ws22.uebung4;

public class ContainerException extends Exception {
    public ContainerException(String message) {
        super("Das Member-Objekt mit der ID " + message + " ist bereits vorhanden!");
    }

    public ContainerException() {
        super("Objekt darf nicht NULL sein!");
    }
}
