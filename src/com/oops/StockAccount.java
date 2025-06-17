package com.oops;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class CompanyShares {
    private String symbol;
    private int numberOfShares;
    private String dateTime;

    public CompanyShares(String symbol, int numberOfShares) {
        this.symbol = symbol;
        this.numberOfShares = numberOfShares;
        this.dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public String getSymbol() {
        return symbol;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void addShares(int amount) {
        this.numberOfShares += amount;
        this.dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public void removeShares(int amount) {
        this.numberOfShares -= amount;
        this.dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-8d %-20s", symbol, numberOfShares, dateTime);
    }

    public String toFileFormat() {
        return symbol + "," + numberOfShares + "," + dateTime;
    }

    public static CompanyShares fromFileFormat(String line) {
        String[] parts = line.split(",");
        CompanyShares cs = new CompanyShares(parts[0], Integer.parseInt(parts[1]));
        cs.dateTime = parts[2];
        return cs;
    }
}

public class StockAccount {
    private List<CompanyShares> portfolio;

    public StockAccount(String filename) {
        portfolio = new ArrayList<>();
        load(filename);
    }

    public void load(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                portfolio.add(CompanyShares.fromFileFormat(line));
            }
        } catch (IOException e) {
            System.out.println("No existing file found. Starting fresh.");
        }
    }

    public double valueOf() {
        // Just assume each share is ₹100 for calculation purposes
        double value = 0;
        for (CompanyShares cs : portfolio) {
            value += cs.getNumberOfShares() * 100;
        }
        return value;
    }

    public void buy(int amount, String symbol) {
        for (CompanyShares cs : portfolio) {
            if (cs.getSymbol().equalsIgnoreCase(symbol)) {
                cs.addShares(amount);
                return;
            }
        }
        portfolio.add(new CompanyShares(symbol, amount));
    }

    public void sell(int amount, String symbol) {
        for (CompanyShares cs : portfolio) {
            if (cs.getSymbol().equalsIgnoreCase(symbol)) {
                if (cs.getNumberOfShares() >= amount) {
                    cs.removeShares(amount);
                } else {
                    System.out.println("Not enough shares to sell.");
                }
                return;
            }
        }
        System.out.println("Stock symbol not found.");
    }

    public void save(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (CompanyShares cs : portfolio) {
                bw.write(cs.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }

    public void printReport() {
        System.out.println("Stock Report");
        System.out.println("------------");
        System.out.printf("%-10s %-8s %-20s\n", "Symbol", "Shares", "Last Updated");
        for (CompanyShares cs : portfolio) {
            System.out.println(cs);
        }
        System.out.println("------------");
        System.out.println("Total Value of Portfolio: ₹" + valueOf());
    }

    // Demo Main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StockAccount account = new StockAccount("stockdata.txt");

        boolean running = true;

        while (running) {
            System.out.println("\n1. Buy Shares\n2. Sell Shares\n3. Print Report\n4. Save & Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear input

            switch (choice) {
                case 1:
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = sc.nextLine();
                    System.out.print("Enter number of shares to buy: ");
                    int buyAmount = sc.nextInt();
                    account.buy(buyAmount, buySymbol);
                    break;
                case 2:
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = sc.nextLine();
                    System.out.print("Enter number of shares to sell: ");
                    int sellAmount = sc.nextInt();
                    account.sell(sellAmount, sellSymbol);
                    break;
                case 3:
                    account.printReport();
                    break;
                case 4:
                    account.save("stockdata.txt");
                    System.out.println("Saved and exited.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
