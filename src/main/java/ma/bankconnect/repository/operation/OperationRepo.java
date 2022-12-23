package ma.bankconnect.repository.operation;

import ma.bankconnect.entity.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepo extends JpaRepository<AccountOperation, Long> {
}
