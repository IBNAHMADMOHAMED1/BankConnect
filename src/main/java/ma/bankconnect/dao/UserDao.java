package ma.bankconnect.dao;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ma.bankconnect.entity.Agent;
import ma.bankconnect.entity.Customer;
import ma.bankconnect.repository.admin.AdminRepo;
import ma.bankconnect.service.CustomerServiceImpl;
import ma.bankconnect.service.admin.AdminServiceimpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@RequiredArgsConstructor
@Component
@Data
public class UserDao {
    private final CustomerServiceImpl customerService;
    private final AdminServiceimpl adminService;
    // how is want to login
    private String who;
    private final AdminRepo adminRepo;

    public UserDetails findByEmail(String email) {
        String role = getWho();
        System.out.println("role: " + role);
        if (role.equals("customer")) {
            Customer customer = customerService.findByEmail(email);
            return new User(customer.getEmail(), customer.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
        } else if (role.equals("admin")) {
            Agent agent = adminRepo.findAgentByEmail(email);
            System.out.println(agent.getNom());
            return new User(agent.getEmail(), agent.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));

        }
        return null;
    }
}
