package ma.bankconnect.service.operation;

import ma.bankconnect.entity.AccountOperation;

public interface OperationService {
    Boolean transfer(AccountOperation accountOperation);
    Boolean deposit(AccountOperation accountOperation);
    Boolean withdraw(AccountOperation accountOperation);
}
