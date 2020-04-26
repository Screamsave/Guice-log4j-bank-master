package bank.domain;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import org.apache.log4j.Logger;

public class SaveAccount extends Account {
    private static final Logger log = Logger.getLogger(SaveAccount.class);
    protected double balance;
    protected double interestRate;


    @Override
    public void deposit(double amt) {
        balance = balance + amt;
        log.info("deposit " + amt);
    }

    @Override
    public void withdraw(double amt) throws OverdraftException {
        boolean result = false;  // assume operation failure
        if ( amt <= balance ) {
            balance = balance - amt;
            log.info("withdraw " + amt);
        } else {
            log.info("not enough balance to withdraw " + amt);
            throw new OverdraftException("Insufficient funds", amt - balance);
        }
    }

    @Override
    public void getBalance() {
        System.out.println(balance);

    }

    /**
     * Marked by @AssistedInject. Guice factory
     * @param balance Marked by annotation @Assisted to throw arguments.
     * @param interestRate Marked by annotation @Assisted to throw arguments.
     */
    @AssistedInject
    public SaveAccount(@Assisted("balance") double balance, @Assisted("interestRate")double interestRate) {
        this.balance = balance;
        this.interestRate = interestRate;
    }
}
