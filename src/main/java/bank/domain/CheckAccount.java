package bank.domain;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import org.apache.log4j.Logger;

public class CheckAccount extends Account {
    /**
     * Added Logger to create operation log.
     */
    private static final Logger log = Logger.getLogger(SaveAccount.class);
    protected double balance;
    protected double overdraft;

    @Override
    public void deposit(double amt) {
        balance = balance + amt;
        log.info("deposit " + amt);
    }

    public void withdraw(double amount) throws OverdraftException {
        if ( balance < amount ) {
            // Not enough balance to cover the amount requested to withdraw
            // Check if there is enough in the overdraft protection (if any)
            double overdraftNeeded = amount - balance;
            if ( overdraft < overdraftNeeded ) {
                // No overdraft protection or not enough to cover the amount needed
                log.info("not enough balance to withdraw  " + amount);
                throw new OverdraftException("Insufficient funds for overdraft protection",
                        overdraftNeeded);
            } else {
                // Yes, there is overdraft protection and enough to cover the amount
                balance = 0.0;
                overdraft -= overdraftNeeded;
                log.info("overdraft cover amount  " + overdraftNeeded);
            }
        } else {
            // Yes, there is enough balance to cover the amount
            // Proceed as usual
            balance = balance - amount;
            log.info("withdraw " + amount);
        }
    }

    @Override
    public void getBalance() {
        System.out.println(balance);
    }

    /**
     * Marked by @AssistedInject. Guice factory
     * @param balance Marked by annotation @Assisted to throw arguments.
     * @param overdraft Marked by annotation @Assisted to throw arguments.
     */
    @AssistedInject
    public CheckAccount(@Assisted("balance") double balance, @Assisted("overdraft") double overdraft) {
        this.balance = balance;
        this.overdraft = overdraft;
    }
}
