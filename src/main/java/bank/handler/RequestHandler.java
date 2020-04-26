package bank.handler;

import bank.domain.Account;
import bank.domain.Customer;
import bank.domain.Type;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Annotated @Singleton we need only one object
 */
@Singleton
public class RequestHandler {
    /**
     * Add factory dependence
     */
    private AccountFactory accountFactory;

    @Inject
    public RequestHandler(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }

    /**
     * Here we create account for transferred customer
     * @param type ENUM type
     * @param balance
     * @param customer
     * @param arg only one extra option in different account type
     */
    public void createAccount(Type type, double balance, Customer customer, double arg) {
        Account account = null;
        double overdraft = 0;
        double interestRate = 0;
        if (type.equals(Type.SAVE)) {
            interestRate = arg;
            account = accountFactory.createSaveAccount(balance, interestRate);
        } else if (type.equals(Type.CHECK)) {
            overdraft = arg;
            account = accountFactory.createCheckAccount(balance, overdraft);
        }
        customer.accounts.add(account);
        //account.getBalance();
    }
}
