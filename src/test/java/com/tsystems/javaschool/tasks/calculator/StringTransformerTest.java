package com.tsystems.javaschool.tasks.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringTransformerTest {

    @Test
    public void testParseString() {
        assertEquals("2;3;*5;+", new StringTransformer().getPostfixStacks("2*3+5"));
        assertEquals("12;6;*", new StringTransformer().getPostfixStacks("12*6"));
        assertEquals("12;6;*2;/3;2;-;+", new StringTransformer().getPostfixStacks("12*6/2+(3-2)"));
    }
}
