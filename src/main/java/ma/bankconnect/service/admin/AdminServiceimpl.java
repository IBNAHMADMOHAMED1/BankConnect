package ma.bankconnect.service.admin;

import ma.bankconnect.entity.Agent;
import ma.bankconnect.repository.admin.AdminRepo;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceimpl implements AdminService {
    private AdminRepo adminRepo;

    @Override
    public Agent getByEmail(String email) {
        return adminRepo.findAgentByEmail(email);
    }
}
