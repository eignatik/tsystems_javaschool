package com.tsystems.javaschool.tasks.calculator;

import java.util.Stack;

/**
 * Class provides methods to transform string expressions in postfix form
 */
class StringTransformer {
    private Stack<Operator> operators = new Stack<>();
    private StringBuilder postfixExpression = new StringBuilder();
    private static String charactersPattern = "\\+|\\*|\\-|\\/";
    private static String digitPattern = "\\d+\\.\\d+|\\d+\\;";

    String getPostfixStacks(String statementToParse) {
        for (int i = 0; i < statementToParse.length(); i++) {
            char symbol = statementToParse.charAt(i);
            switch (symbol) {
                case '+':
                case '-':
                    postfixExpression.append(";");
                    this.setOperator(new Operator(symbol, 1));
                    break;
                case '*':
                case '/':
                    postfixExpression.append(";");
                    this.setOperator(new Operator(symbol, 2));
                    break;
                case '(':
                    operators.push(new Operator(symbol));
                    break;
                case ')':
                    postfixExpression.append(";");
                    this.setBracket();
                    break;
                default:
                    postfixExpression.append(symbol);
            }
        }
        postfixExpression.append(";");
        appendLeftOperators();
        return postfixExpression.toString();
    }

    private void setOperator(Operator operator) {
        while (!operators.isEmpty()) {
            Operator prev = operators.pop();
            int currentPriority = operator.getPriority();
            int prevPriority = prev.getPriority();
            if (prev.getOperator() != '(') {
                if (currentPriority > prevPriority) {
                    operators.push(prev);
                    break;
                } else {
                    postfixExpression.append(prev.getOperator());
                }
            } else {
                operators.push(prev);
                break;
            }
        }
        operators.push(operator);
    }

    private void setBracket() {
        while(!operators.isEmpty()) {
            Operator op = operators.pop();
            if (op.getOperator() != '(') {
                postfixExpression.append(op.getOperator());
            }
            else {
                break;
            }
        }
    }

    private void appendLeftOperators() {
        while (!operators.isEmpty()) {
            postfixExpression.append(operators.pop().getOperator());
        }
    }
}
