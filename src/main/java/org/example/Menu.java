package org.example;

import java.util.Scanner;

public class Menu {
    public int displayMenu() {
        boolean flag = true;
        int choice = 0;
        do {
            flag = true;
            try
            {
                System.out.println();
                System.out.println("__________________________________________");
                System.out.println("Дерево-выражений");
                System.out.println("Выберите форму ввода выражения:");
                System.out.println("1. Инфиксная");
                System.out.println("2. Постфиксная");
                System.out.println("3. Префиксная");
                System.out.println("4. EXIT");
                System.out.println("__________________________________________");
                Scanner keyboard = new Scanner(System.in);
                choice = keyboard.nextInt();
            } catch (Exception exception) {
                System.out.println("Выбрана несуществующая опция. Попробуйте снова.");
                flag = false;
            }
        } while (flag == false);
        return choice;
    }

    public void chooseTraverseMethod(Node tree) {
        System.out.println();
        System.out.println("__________________________________________");
        System.out.println("Выберите формат вывода дерева: ");
        System.out.println("1. Инфиксная форма");
        System.out.println("2. Постфиксная форма");
        System.out.println("3. Префиксная форма");
        Scanner keyboard = new Scanner(System.in);
        ExpressionTree eTree = new ExpressionTree();
        boolean flag = true;
        do {
            flag = true;
            int choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    eTree.traverseInOrder(tree);
                    break;
                case 2:
                    eTree.traversePostOrder(tree);
                    break;
                case 3:
                    eTree.traversePreOrder(tree);
                    break;
                default:
                    flag = false;
                    System.out.println("Выбрана несуществущая опция. Попробуйте снова.");
                    break;
            }
        } while (flag == false);
        System.out.println();
    }
}
