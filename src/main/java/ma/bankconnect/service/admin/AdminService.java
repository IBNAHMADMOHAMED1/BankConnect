package ma.bankconnect.service.admin;

import ma.bankconnect.entity.Agent;

public interface AdminService {

    Agent getByEmail(String email);
}
