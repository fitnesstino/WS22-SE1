package org.hbrs.se1.ws22.uebung9;

public abstract class CoreDocument implements Document{
    private int id;

    public void setID( int id ){
        this.id = id;
    }

    public int getID( ){
        return id;
    }
}
