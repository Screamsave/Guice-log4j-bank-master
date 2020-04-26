package bank.handler;

import bank.domain.CheckAccount;
import bank.domain.SaveAccount;
import com.google.inject.assistedinject.Assisted;

/**
 * This version of the project implements a Google Guice  factory.
 * We need to pull field in RUNTIME.
 */
public interface AccountFactory {
    /**
     * Field must be marked @Assisted with value, look at annotation description
     * @param balance
     * @param interestRate
     * @return
     */
    SaveAccount createSaveAccount (@Assisted("balance") double balance,
                                   @Assisted("interestRate") double interestRate);

    CheckAccount createCheckAccount (@Assisted("balance")double balance,
                                     @Assisted("overdraft") double overdraft);
}
