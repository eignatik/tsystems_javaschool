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
        statement = statement.replaceAll("\\s", "");
        String result;
        try {
            result = !isStatementCorrect(statement)? calculate(new StringTransformer().getPostfixStacks(statement)) : null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = null;
        }
        return result;
    }

    private boolean isStatementCorrect(String statement) {
        return statement.matches(statementPattern);
    }

    private String calculate(String expression) {
        double result;
        String number = "";
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch) || ch == '.') {
                number += ch;
            } else if (ch == ';'){
                operands.push(Double.valueOf(number));
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
                        result = firstOperand / secondOperand;
                        break;
                    default:
                        result = 0;
                }
                operands.push(result);
            }
        }
        result = operands.pop();
        return Double.toString(result);
    }
}
