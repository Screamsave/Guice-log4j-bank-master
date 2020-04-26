package bank.domain;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    public List<Account> accounts;

    private String firstName;
    private String lastName;

    public Customer(String f, String l) {
        firstName = f;
        lastName = l;
        // initialize accounts array
        accounts = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumOfAccounts() {
        return accounts.size();
    }

    public Account getAccount(int account_index) {
        return accounts.get(account_index);
    }
}
