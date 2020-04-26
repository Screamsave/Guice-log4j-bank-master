package bank.main;

import bank.domain.Bank;
import bank.domain.Customer;
import bank.domain.OverdraftException;
import bank.domain.Type;
import bank.handler.Module;
import bank.handler.RequestHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new Module());
        RequestHandler requestHandler = injector.getInstance(RequestHandler.class);
        Customer customer;
        Customer customer1;

        Bank.addCustomer("First", "Second");
        Bank.addCustomer("anotherFirst", "anotherSecond");
        customer = Bank.getCustomer(0);
        customer1 = Bank.getCustomer(1);

        requestHandler.createAccount(Type.SAVE, 500, customer, 3);
        requestHandler.createAccount(Type.CHECK, 600, customer, 300);
        customer.getAccount(0).getBalance();

        requestHandler.createAccount(Type.SAVE, 400, customer1, 4);
        requestHandler.createAccount(Type.CHECK, 700, customer1, 500);
        customer1.getAccount(0).getBalance();
        System.out.println("================================================================");

        try {
        customer1.getAccount(0).deposit(50);
        customer1.getAccount(0).getBalance();
        customer1.getAccount(0).withdraw(450);
        customer1.getAccount(0).getBalance();
        customer1.getAccount(1).withdraw(1000);
        customer1.getAccount(1).getBalance();
        } catch (OverdraftException e1) {
            System.out.println("Exception: " + e1.getMessage()
                    + "   Deficit: " + e1.getDeficit());
        }
    }
}
