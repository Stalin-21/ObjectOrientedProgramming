package com.oops;

import java.util.ArrayList;
import java.util.Scanner;

class Stock {
    String name;
    int numberOfShares;
    double sharePrice;



    public Stock(String name, int numberOfShares, double sharePrice) {
        this.name = name;
        this.numberOfShares = numberOfShares;
        this.sharePrice = sharePrice;
    }

    public double totalValue() {
     return this.sharePrice * this.numberOfShares;
    }

}

public class StackAccManagement {
    public static void main(String[] args) {
        System.out.println("Welcome to the Stack Account Management!");

        double totalPortfolioValue = 0;
        ArrayList<Stock> stocks = new ArrayList<Stock>();
        Scanner sc = new Scanner(System.in);
        String key = "yes";

        while(key.equalsIgnoreCase("yes")) {

            System.out.println("Enter the name of the stock: ");
            String name = sc.nextLine();
            System.out.println("Enter the number of shares: ");
            int numberOfShares = sc.nextInt();
            System.out.println("Enter the share price: ");
            double sharePrice = sc.nextDouble();
            sc.nextLine();

            System.out.println("Do you want to calculate more share price for stock account? (yes/no)");
            key = sc.nextLine();
            Stock stock = new Stock(name, numberOfShares, sharePrice);

            stocks.add(stock);



        }
        System.out.println("Stock Report: ");
        System.out.println("-------------");
        System.out.printf("%-10s %-8s %-8s %-12s\n", "Name", "Shares", "Price", "Total Value");
        for (Stock s : stocks) {
            System.out.printf("%-10s %-8d %-8.2f %-12.2f\n", s.name, s.numberOfShares, s.sharePrice, s.totalValue());
            totalPortfolioValue += s.totalValue();
        }
        System.out.println();
        System.out.println("Total Portfolio Value: "+totalPortfolioValue);

    }
}
