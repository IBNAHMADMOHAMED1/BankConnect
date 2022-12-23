package ma.bankconnect.service.operation;

import lombok.RequiredArgsConstructor;
import ma.bankconnect.entity.AccountOperation;
import ma.bankconnect.repository.operation.OperationRepo;
import ma.bankconnect.service.account.AccountServiceImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final OperationRepo repo;
    private final AccountServiceImpl accountService;

    @Override
    public Boolean transfer(AccountOperation accountOperation) {
        return null;
    }

    @Override
    public Boolean deposit(AccountOperation accountOperation) {
        return null;
    }

    @Override
    public Boolean withdraw(AccountOperation accountOperation) {
        return null;
    }

    // save
    public AccountOperation save(AccountOperation accountOperation) {
        if (accountOperation.getType().equals("DEPOSIT")) {
            deposit(accountOperation);
        } else if (accountOperation.getType().equals("WITHDRAW")) {
            withdraw(accountOperation);
        } else if (accountOperation.getType().equals("TRANSFER")) {
            transfer(accountOperation);
        }
        return repo.save(accountOperation);
    }
}
