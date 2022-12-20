package ma.bankconnect.repository.admin;

import ma.bankconnect.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Agent, Long> {
    Agent findByEmail(String email);
}
