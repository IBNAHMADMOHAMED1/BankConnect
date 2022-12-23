package ma.bankconnect.service.transaction;

import ma.bankconnect.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction save(Transaction transaction);
    Transaction findByCode(String code);
    Transaction findByCustomerId(Long customerId);
    Transaction findByAccountId(Long accountId);
    List<Transaction> getAllTransactions(Long customerId);
}
