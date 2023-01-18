package org.hbrs.se1.ws22.uebung10;

import java.util.Stack;

public class CustomStack<T> extends Stack<T> {

    private final int MAX_SIZE;

    public CustomStack(int maxSize){
        this.MAX_SIZE=maxSize;
    }

    @Override
    public T push(T item) {
        if(isFull()) throw new IndexOutOfBoundsException("CustomStack ist voll!");
        return super.push(item);
    }

    public boolean isFull(){
        return size()==MAX_SIZE;
    }
}
