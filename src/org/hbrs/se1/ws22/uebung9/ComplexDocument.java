package org.hbrs.se1.ws22.uebung9;

import java.util.LinkedList;
import java.util.List;

public class ComplexDocument implements Document{
    private int id;
    private List<Document> list = new LinkedList<>();

    public void add( Document document ){
        list.add(document);
    }

    public boolean remove ( Document document ){
        return list.remove(document);
    }

    public Document get ( int id ){
        return list.stream().filter(document -> document.getID()==id).findFirst().orElse(null);
    }
    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int size() {
        int size = 0;
        for(Document document : list){
            size += document.size();
        }
        return size;
    }
}
