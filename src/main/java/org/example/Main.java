package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Menu menu = new Menu();
        int choice = 0;
        do {
            choice = menu.displayMenu();
            String expression;
            ExpressionTree eTree = new ExpressionTree();
            Node tree;
            int result;
            try {

                switch (choice) {
                    case 1:
                        do {
                            System.out.println("Введите выражение в инфиксной форме:");
                            Scanner infixExp = new Scanner(System.in);
                            expression = infixExp.nextLine();
                            expression = eTree.replaceLetters(expression);
                        } while (!eTree.isValidInfix(expression));
                        tree = eTree.infixTree(expression);
                        menu.chooseTraverseMethod(tree);
                        result = eTree.countExpression(tree);
                        System.out.println();
                        System.out.println("Значения выражения: " + result);
                        break;
                    case 2:
                        do {
                            System.out.println("Введите выражение в постфиксной форме: ");
                            Scanner postfixExp = new Scanner(System.in);
                            expression = postfixExp.nextLine();
                            expression = eTree.replaceLetters(expression);
                        } while(!eTree.isValidPostfix(expression));
                        tree = eTree.postfixTree(expression);
                        menu.chooseTraverseMethod(tree);
                        result = eTree.countExpression(tree);
                        System.out.println();
                        System.out.println("Значения выражения: " + result);
                        break;
                    case 3:
                        do {
                            System.out.println("Введите выражение в префиксной форме: ");
                            Scanner prefixExp = new Scanner(System.in);
                            expression = prefixExp.nextLine();
                            expression = eTree.replaceLetters(expression);
                        } while (!eTree.isValidPrefix(expression));
                        tree = eTree.prefixTree(expression);
                        menu.chooseTraverseMethod(tree);
                        result = eTree.countExpression(tree);
                        System.out.println();
                        System.out.println("Значение выражения: " + result);
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println();
                        System.out.println("Неверный формат ввода. Попробуйте снова.");
                        break;
                }
            } catch(Exception e) {
                System.out.println();
                System.out.println("Неверный формат ввода. Попробуйте снова.");
            }

        } while (choice != 4);
    }
}