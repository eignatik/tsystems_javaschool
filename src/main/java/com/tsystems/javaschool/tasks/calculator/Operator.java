package com.tsystems.javaschool.tasks.calculator;

public class Operator {
    private char operator;
    private int priority;

    Operator(char operator, int priority) {
        this.operator = operator;
        this.priority = priority;
    }

    public char getOperator() {
        return operator;
    }

    public int getPriority() {
        return priority;
    }
}
