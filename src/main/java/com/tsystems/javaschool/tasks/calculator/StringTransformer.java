package com.tsystems.javaschool.tasks.calculator;

import java.util.Stack;

/**
 * Class provides methods to transform string expressions in postfix form
 */
class StringTransformer {
    private Stack<String> statementStack = new Stack<>();
    private Stack<Operator> charStack = new Stack<>();
    private static String charactersPattern = "\\+|\\*|\\-|\\/";
    private static String digitPattern = "\\d+\\.\\d+|\\d+";

    void parseString(String statementToParse) {
        makePostfixStacks(statementToParse);
        calculate();
    }

    private void makePostfixStacks(String statementToParse) {
        String exp = "";
        for (int i = 0; i < statementToParse.length(); i++) {
            char symbol = statementToParse.charAt(i);
            switch (symbol) {
                case '+':
                case '-':
                    statementStack.push(exp);
                    exp = "";
                    charStack.push(new Operator(symbol, 2));
                    break;
                case '*':
                case '/':
                    statementStack.push(exp);
                    exp = "";
                    charStack.push(new Operator(symbol, 1));
                    break;
                case '(':
                case ')':
                    break;
                default:
                    exp += symbol;
                    if (i == statementToParse.length() - 1) {
                        statementStack.push(exp);
                    }
                    break;
            }
        }
    }

    private void calculate() {
        while (statementStack.size() > 1) {
            double result = 0;
            double firstOperand = Double.parseDouble(statementStack.pop());
            double secondOperand = Double.parseDouble(statementStack.pop());
            Operator operator = charStack.pop();
            Operator currentOperator = swapOperatorsAndGetCurrent(operator);
            calculateExpression(currentOperator, firstOperand, secondOperand);
            statementStack.push("" + result);
        }
    }

    private Operator swapOperatorsAndGetCurrent(Operator operator) {
        Operator currentOperator;
        if (charStack.size() >= 1) {
            Operator nextOperator = charStack.pop();
            if (nextOperator.getPriority() < operator.getPriority()) {
                charStack.push(operator);
                currentOperator = nextOperator;
            } else {
                charStack.push(nextOperator);
                currentOperator = operator;
            }
        } else {
            currentOperator = operator;
        }
        return currentOperator;
    }

    private double calculateExpression(Operator operator, double firstOperand, double secondOperand) {
        double result = 0;
        switch (operator.getOperator()) {
            case '+':
                result = firstOperand + secondOperand;
                break;
            case '-':
                result = firstOperand - secondOperand;
                break;
            case '*':
                result = firstOperand * secondOperand;
                break;
            case '/':
                result = firstOperand / secondOperand;
                break;
            default: break;
        }
        return result;
    }
}
