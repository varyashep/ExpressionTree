package org.example;
import java.util.*;

public class ExpressionTree {
    Node root;

    boolean isOperator(String element) {
        if (Objects.equals(element, "^") || Objects.equals(element, "*") || Objects.equals(element, "/") ||
                Objects.equals(element, "+") || Objects.equals(element, "-")) {
            return true;
        }
        return false;
    }


    boolean isOperand(String element) {
            if (!isOperator(element) && !element.equals("(") && !element.equals(")")) {
                if (Character.isDigit(element.charAt(0))) {
                    return true;
                }
            }
            return false;

    }

    String replaceLetters(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            if (!isOperand(String.valueOf(expression.charAt(i))) && !isOperator(String.valueOf(expression.charAt(i))) && expression.charAt(i) != '(' && expression.charAt(i) != ')' && expression.charAt(i) != ' ') {
                int number = 0;
                boolean flag = false;
                while (!flag) {
                    flag = true;
                    System.out.println("Введите число для замены элемента " + expression.charAt(i) + ": ");
                    Scanner keyboard = new Scanner(System.in);
                    try {
                        number = keyboard.nextInt();
                    } catch (Exception e) {
                        System.out.println("Необходимо заменить на число.");
                        flag = false;
                    }
                }

               expression = expression.replaceAll(String.valueOf(expression.charAt(i)), String.valueOf(number));
            }
        }

        return expression;
    }

    boolean isValidInfix(String expression) {
        Stack<String> stack = new Stack<>();
        String[] elements = expression.split(" ");
        int operators = 0;
        int operands = 0;
        for (String element : elements) {
            if (isOperator(element)) {
                operators++;
            }
            else if (isOperand(element)) {
                operands++;
            }
        }
        if (operands < operators || operators < operands - 1)
        {
            return false;
        }
        for (String element : elements) {
            if (element.equals("(")) {
                stack.push(element);
            } else if (element.equals(")")) {
                if (stack.isEmpty() || !stack.contains("(")) {
                    return false;
                }
            } else if (isOperator(element)) {
                if (stack.isEmpty() || isOperator(stack.peek())) {
                    return false;
                }
                stack.push(element);
            }
            else if (isOperand(element)) {
                stack.push(element);
            }
        }

        return !stack.isEmpty() && !isOperator(stack.peek());
    }

    public Node postfixTree(String expression) {
        Node e1, e2, tmp;
        Stack<Node> curNodes = new Stack<Node>();
        String[] expressionSplitted = expression.split(" ");
        for (int i = 0; i < expressionSplitted.length; i++) {
            tmp = new Node(expressionSplitted[i]);
            if (!isOperator(expressionSplitted[i])) {
                curNodes.push(tmp);
            }
            else {
                e2 = curNodes.pop();
                e1 = curNodes.pop();
                tmp.leftChild = e1;
                tmp.leftChild.value = e1.value;
                tmp.rightChild = e2;
                tmp.rightChild.value = e2.value;
                curNodes.push(tmp);
            }
        }
        tmp = curNodes.pop();
        return tmp;
    }

    public boolean isValidPostfix(String expression) {
        Stack<String> stack = new Stack<>();
        String[] elements = expression.split(" ");

        for (String element : elements) {
            if (isOperand(element)) {
                stack.push(element);
            } else if (isOperator(element)) {
                if (stack.size() < 2) {
                    return false;
                }
                stack.pop();
                stack.pop();
                stack.push("0");
            } else {
                return false;
            }
        }
        return stack.size() == 1 && isOperand(stack.peek());
    }

    public Node prefixTree(String expression) {
        Stack<Node> curNodes = new Stack<>();
        String[] expressionSplitted = expression.split(" ");
        for (int i = expressionSplitted.length- 1; i>=0; i--) {
            Node tmp = new Node(expressionSplitted[i]);
            if (isOperator(expressionSplitted[i])) {
                tmp.leftChild = curNodes.pop();
                tmp.rightChild = curNodes.pop();
                curNodes.push(tmp);
            } else {
                curNodes.push(tmp);
            }
        }
        return curNodes.pop();
    }

    public boolean isValidPrefix(String expression) {
        Stack<String> stack = new Stack<>();
        String[] elements = expression.split(" ");

        for (int i = elements.length - 1; i >= 0; i--) {
            String element = elements[i];
            if (isOperand(element)) {
                stack.push(element);
            } else if (isOperator(element)) {
                if (stack.size() < 2) {
                    return false;
                }
                stack.pop();
                stack.pop();
                stack.push("0");
            } else {
                return false;
            }
        }
        return stack.size() == 1 && isOperand(stack.peek());
    }

    public int precedence(String c) {
        switch (c) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
        }
        return -1;
    }

    public int counter(int a, int b, String operator) throws Exception {
        int counter = 0;
        if (Objects.equals(operator, "+")) {
            counter = a + b;
        }
        else if (Objects.equals(operator, "-")) {
            counter = a - b;
        }
        else if (Objects.equals(operator, "*")) {
            counter = a * b;
        }
        else if (Objects.equals(operator, "/")) {
            counter = a / b;
        }
        else if (Objects.equals(operator, "^")) {
            counter = (int) Math.pow(a, b);
        }
        else {
            throw new Exception("No such operator");
        }
        return counter;
    }

    String newString = "";
    int iterator = 0;
    static String expressionToAdd = "";
    public String addBrackets(String expression) {
        int index = expression.indexOf('^');
        int indexNextOperator = 0;
        String secondPart = "";
        if (index != - 1) {
            String firstPart = expression.substring(0, index+1);
            for (int i = index + 1; i < expression.length() && indexNextOperator == 0; i++) {
                if (isOperator(String.valueOf(expression.charAt(i))) && expression.charAt(i) != '^') {
                    indexNextOperator = i;
                    //break;
                }
            }
            //secondPart = expression.substring(index+1, indexNextOperator);
            if (indexNextOperator != 0) {
                secondPart = expression.substring(index+1, indexNextOperator-1);
                expressionToAdd = expression.substring(indexNextOperator-1);
            }
            else {
                secondPart = expression.substring(index+1);
            }
            if (iterator == 0) {
                newString += firstPart;
                iterator++;
            }
            else {
                if (iterator == 1) {
                    newString += " (" + firstPart;
                    secondPart += " )";
                } else if (iterator > 1){
                    newString += " (" + firstPart;
                    secondPart += " )";
                }

            }
            return addBrackets(secondPart);
        } else {
            newString += expression;
            newString += expressionToAdd;
            expressionToAdd = "";
            return newString;
        }
    }

    public Node infixTree(String expression) {
        expression = addBrackets(expression);
        StringBuilder postfix = new StringBuilder();
        Stack<String> symbols = new Stack<>();
        String[] expressionSplitted = expression.split(" ");
        for (int i = 0; i < expressionSplitted.length; i++) {
            String c = expressionSplitted[i];
            if (!isOperator(c) && !Objects.equals(c, "(") && !Objects.equals(c, ")")) {
                postfix.append(c);
                postfix.append(" ");
            } else if (Objects.equals(c, "(")) {
                symbols.push(c);
            } else if (Objects.equals(c, ")")) {
                while (!symbols.isEmpty() && !Objects.equals(symbols.peek(), "(")) {
                    postfix.append(symbols.pop());
                    postfix.append(" ");
                }
                symbols.pop();
            } else {
                while (!symbols.isEmpty() && precedence(c) <= precedence(symbols.peek())) {
                    postfix.append(symbols.pop());
                    postfix.append(" ");
                }
                symbols.push(c);
            }
        }
        while (!symbols.isEmpty()) {
            postfix.append(symbols.pop());
            postfix.append(" ");
        }


        Node tree = postfixTree(String.valueOf(postfix));
        return tree;
    }


    public int countExpression(Node node) throws Exception {

        if (node.leftChild == null && node.rightChild == null) {
            // Если узел является листом дерева, то возвращаем его значение
            return Integer.parseInt(String.valueOf(node.value));
        } else {
            // Если узел имеет дочерние узлы, то вычисляем значение выражения
            int left, right;
            left = countExpression(node.leftChild);
            right = countExpression(node.rightChild);
            System.out.println("Частичное вычисление выражения: " + left + " " + node.value + " " + right);
            return counter(left, right, node.value);
        }
    }

    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.leftChild);
            traversePostOrder(node.rightChild);
            System.out.print(" " + node.value);
        }
    }

    int iterator2 = 0;
    public void traverseInOrder(Node node) {
        if (node != null) {
            if (node.leftChild != null || node.rightChild != null){
                if (iterator2 == 0) {
                    root = node;
                }
                if (node != root) {
                    System.out.print("(");
                }
            }
            iterator2++;
            traverseInOrder(node.leftChild);
            System.out.print(" " + node.value + " ");
            traverseInOrder(node.rightChild);
            if ((node.leftChild != null || node.rightChild != null) && (node != root)) {
                System.out.print(")");
            }
        }
    }

    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.value);
            traversePreOrder(node.leftChild);
            traversePreOrder(node.rightChild);
        }
    }

}
