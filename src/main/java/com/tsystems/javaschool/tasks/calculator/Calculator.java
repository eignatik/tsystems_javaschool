package com.tsystems.javaschool.tasks.calculator;

import java.util.Stack;

public class Calculator {

    private Stack<Double> operands = new Stack<>();
    private String statementPattern = "";

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        String result;
        try {
            result = !hasIncorrectCharacters(statement) ?
                    calculate(new StringTransformer().getPostfixStacks(statement))
                    : null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = null;
        }
        return result;
    }

    private boolean hasIncorrectCharacters(String statement) {
        int parenthesisCounter = 0;
        for (int i = 0; i < statement.length(); i++) {
            if (statement.charAt(i) == '(') {
                parenthesisCounter++;
            } else if (statement.charAt(i) == ')') {
                parenthesisCounter--;
            }
        }
        return statement.isEmpty()
                || parenthesisCounter != 0
                || statement.matches(".+\\,.+")
                || statement.matches(".+\\s.+")
                || statement.matches(".+\\+{2,}.+")
                || statement.matches(".+\\*{2,}.+")
                || statement.matches(".+\\/{2,}.+")
                || statement.matches(".+\\-{2,}.+")
                || statement.matches(".+\\({2,}.+")
                || statement.matches(".+\\){2,}.+");
    }

    private String calculate(String expression) throws Exception {
        double result;
        String number = "";
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch) || ch == '.') {
                number += ch;
            } else if (ch == ';'){
                if (!number.isEmpty()) {
                    operands.push(Double.valueOf(number));
                }
                number = "";
            } else {
                double secondOperand = operands.pop();
                double firstOperand = operands.pop();
                switch (ch) {
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
                        if (secondOperand != 0) {
                            result = firstOperand / secondOperand;
                        } else {
                            throw new Exception("Division by zero!");
                        }
                        break;
                    default:
                        result = 0;
                }
                operands.push(result);
            }
        }
        result = operands.pop();
        return (result * 100 % 100 == 0)? Integer.toString((int) result) : Double.toString(result);
    }
}
