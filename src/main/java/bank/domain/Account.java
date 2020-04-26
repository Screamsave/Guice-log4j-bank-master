package bank.domain;

/**
 * Account class declare abstract. We don't need to create it.
 */
public abstract class Account {

    public abstract void deposit(double amt);
    public abstract void withdraw(double amt) throws OverdraftException;
    public abstract void getBalance();

    public Account() {

    }
}
