package bank.handler;

import bank.domain.CheckAccount;
import bank.domain.SaveAccount;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Class Module need to Guice configuration
 */
public class Module extends AbstractModule {

    /**
     *  FactoryModuleBuilder — provides a factory that combines the caller's
     *  arguments with injector-supplied values to construct objects.
     *  Method - implement (Class<T> source, Class<? extends T> target)
     *  source - type of returned value.
     *  target - implementation of type factory supplied
     *  build - provide factory
     */
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(SaveAccount.class, SaveAccount.class)
                .implement(CheckAccount.class, CheckAccount.class)
                .build(AccountFactory.class));
    }
}
