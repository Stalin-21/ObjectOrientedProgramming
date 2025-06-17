package com.oops;

class Account{
    private final String accountHolderName;
    private double balance;

    public Account(String accountHolderName, double balance){
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }
    public String getAccountHolderName(){
        return accountHolderName;
    }
    public double getBalance(){
        return balance;
    }
    public void deposit(double amount){
        balance += amount;
    }
    public double debit(double amount){
        if(amount <= balance) {
            return balance -= amount;
        }else {
            return -1;
        }

    }

}

public class AccountDebitDemo {
    public static void main(String[] args) {
        Account account1 = new Account("Stalin", 200);
       double remainingBalance =  account1.debit(100);
        if(remainingBalance == -1){
            System.out.println("Account withdrawal failed, debit amount exceeded account balance.");
        }else {
            System.out.println("Account withdrawal successful");
            System.out.println("Balance: " + account1.getBalance());
        }
    }
}
