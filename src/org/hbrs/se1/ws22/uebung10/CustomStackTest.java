package org.hbrs.se1.ws22.uebung10;

import org.hbrs.se1.ws22.test.MyPrettyRectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class CustomStackTest {

    final int N=4;

    @Test
    void customStackTest() {
        CustomStack<Object> stack=new CustomStack<>(N);

        assertTrue(stack.empty());

        stack.push(null);
        assertEquals(1,stack.size());

        stack.push(3);
        assertFalse(stack.empty());
        assertEquals(3,stack.pop());

        stack.push(Math.PI);
        stack.push(new MyPrettyRectangle(0,0,0,0));
        stack.push(BigInteger.valueOf(Long.MAX_VALUE));

        assertTrue(stack.isFull());
        assertThrows(IndexOutOfBoundsException.class,()->stack.push(new Object()));

        while(stack.size()>0){
            stack.pop();
        }
        assertTrue(stack.empty());
    }
}