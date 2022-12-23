package ma.bankconnect.service.transaction;

import lombok.RequiredArgsConstructor;
import ma.bankconnect.entity.Transaction;
import ma.bankconnect.repository.transaction.TransactionRepo;
import ma.bankconnect.service.account.AccountServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepo transactionRepo;
    private final AccountServiceImpl accountService;

    @Override
    public Transaction save(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction findByCode(String code) {
        return null;
    }

    @Override
    public Transaction findByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public Transaction findByAccountId(Long accountId) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions(Long customerId) {
        Long accountId = accountService.findAccountByCustomerId(customerId).getId();
        return transactionRepo.findAllByAccountId(accountId);
    }
}
