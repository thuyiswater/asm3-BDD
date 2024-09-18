package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    private Scanner scanner;

    public Calculator(){
        scanner = new Scanner(System.in);
    }

    private void addition() {
        try {
            System.out.println("========== Addition ==========");
            System.out.print("Input the first number: ");
            int num1 = this.scanner.nextInt();
            System.out.print("Input the second number: ");
            int num2 = this.scanner.nextInt();
            int result = num1 + num2;
            System.out.println("\n" + num1 + " + " + num2 + " = " + result);
        } catch (InputMismatchException e){
            System.out.println("Support integer only!\n");
            scanner.next();
        }
    }

    private void subtract() {
        try {
            System.out.println("========== Subtraction ==========");
            System.out.print("Input the first number: ");
            int num1 = scanner.nextInt();

            System.out.print("Input the second number: ");
            int num2 = scanner.nextInt();

            int result = num1 - num2;
            System.out.println("\n" + num1 + " - " + num2 + " = " + result);
        } catch (InputMismatchException e) {
            System.out.println("Support integer only!\n");
            scanner.next();
        }
    }

    private void multiply() {
        try {
            System.out.println("========== Multiplication ==========");
            System.out.print("Input the first number: ");
            int num1 = scanner.nextInt();

            System.out.print("Input the second number: ");
            int num2 = scanner.nextInt();

            int result = num1 * num2;
            System.out.println("\n" + num1 + " * " + num2 + " = " + result);
        } catch (InputMismatchException e) {
            System.out.println("Support integer only!\n");
            scanner.next();
        }
    }

    private void divide() {
        try {
            System.out.println("========== Division ==========");
            System.out.print("Input the first number: ");
            int num1 = scanner.nextInt();

            System.out.print("Input the second number: ");
            int num2 = scanner.nextInt();

            if (num2 == 0) {
                System.out.println("Cannot divide by zero!");
                return;
            }

            int result = num1 / num2;
            System.out.println("\n" + num1 + " / " + num2 + " = " + result);
        } catch (InputMismatchException e) {
            System.out.println("Support integer only!\n");
            scanner.next();
        }
    }

    private void modulo() {
        try {
            System.out.println("========== Modulo ==========");
            System.out.print("Input the first number: ");
            int num1 = scanner.nextInt();

            System.out.print("Input the second number: ");
            int num2 = scanner.nextInt();

            if (num2 == 0) {
                System.out.println("Cannot modulo by zero!");
                return;
            }

            int result = num1 % num2;
            System.out.println("\n" + num1 + " % " + num2 + " = " + result);
        } catch (InputMismatchException e) {
            System.out.println("Support integer only!\n");
            scanner.next();
        }
    }

    private void power() {
        try {
            System.out.println("========== Power ==========");
            System.out.print("Input the base number: ");
            int num1 = scanner.nextInt();

            System.out.print("Input the root number: ");
            int num2 = scanner.nextInt();

            if (num2 < 0) {
                System.out.println("Does not support negative root number");
                return;
            }

            int result = 1;
            for (int i = 0; i < num2; i++) {
                result = result * num1;
            }

            System.out.println("\n" + num1 + " ^ " + num2 + " = " + result);
        } catch (InputMismatchException e) {
            System.out.println("Support integer only!\n");
            scanner.next();
        }
    }

    private int max(int num1, int num2){
        if (num1 > num2){
            return num1;
        }
        return num2;
    }

    private int gcd_calculation(int num1, int num2) {
        if (num1 == 0 || num2 == 0) {
            return Math.max(num1, num2);
        }
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    private void gcd() {
        try {
            System.out.println("========== GCD ==========");
            System.out.print("Input the first number: ");
            int num1 = scanner.nextInt();

            System.out.print("Input the second number: ");
            int num2 = scanner.nextInt();

            int result = gcd_calculation(num1, num2);
            System.out.println("\n" + "The Greatest Common Divisor (GCD) of " + num1 + " and " + num2 + " is " + result);
        } catch (InputMismatchException e) {
            System.out.println("Support integer only!\n");
            scanner.next();
        }
    }

    private void lcm() {
        try {
            System.out.println("========== LCM ==========");
            System.out.print("Input the first number: ");
            int num1 = scanner.nextInt();

            System.out.print("Input the second number: ");
            int num2 = scanner.nextInt();

            if(num1 == 0 || num2 == 0){
                System.out.println("\n" + "The Least Common Multiple of " + num1 + " and " + num2 + " is 0");
            }

            int result = (num1 * num2) / gcd_calculation(num1, num2);
            System.out.println("\n" + "The Greatest Common Divisor (GCD) of " + num1 + " and " + num2 + " is " + result);
        } catch (InputMismatchException e) {
            System.out.println("Support integer only!\n");
            scanner.next();
        }
    }

    private void fibonacci() {
        try {
            System.out.println("========== Fibonacci ==========");
            System.out.print("Input a number: ");
            int num1 = scanner.nextInt();

            if (num1 < 0) {
                System.out.println("Fibonacci is not defined for negative numbers");
                return;
            }
            int result = 0;
            if(num1 <= 1){
                result = num1;
            }

            int a = 0, b = 1, c;
            for (int i = 2; i <= num1; i++) {
                c = a + b;
                a = b;
                b = c;
            }

            result = b;
            System.out.println("\n" + "The Fibonacci of " + num1 + " is " + result);
        } catch (InputMismatchException e) {
            System.out.println("Support integer only!\n");
            scanner.next();
        }
    }

    public void run(){
        System.out.println("========================== Welcome to Group org.example.Calculator =========================");
        try {
            int choice = 10;

            while (choice != 0) {
                displayMenu();
                choice = this.scanner.nextInt();

                switch (choice) {
                    case 0 -> {
                        System.out.println("Exiting the calculator. Goodbye!");
                        this.scanner.close();
                    }
                    case 1 -> this.addition();
                    case 2 -> this.subtract();
                    case 3 -> this.multiply();
                    case 4 -> this.divide();
                    case 5 -> this.modulo();
                    case 6 -> this.power();
                    case 7 -> this.gcd();
                    case 8 -> this.lcm();
                    case 9 -> this.fibonacci();
                    default -> System.out.println("Invalid choice. Please select a valid option.");
                }
                System.out.println("================================================================================");
            }
        }catch (InputMismatchException e) {
            System.out.println("\n*Sorry we encountered an unusual error, please try again*");
            this.scanner.close();
        }
    }

    private void displayMenu(){
        System.out.println("\nPlease Select An Operation:");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("| Basic Operation  |        Advanced Operation       |          Other          |");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|1. Addition       | 7. Greatest Common Divisor (GCD)| 0. Exist The org.example.Calculator |");
        System.out.println("|2. Subtraction    | 8. Least Common Multiple (LCM)  |                         |");
        System.out.println("|3. Multiplication | 9. Fibonacci                    |                         |");
        System.out.println("|4. Division       |                                 |                         |");
        System.out.println("|5. Modulo         |                                 |                         |");
        System.out.println("|6. Power          |                                 |                         |");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Enter Your Choice: ");
    }
}
