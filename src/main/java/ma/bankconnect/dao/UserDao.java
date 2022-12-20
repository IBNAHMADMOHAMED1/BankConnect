package ma.bankconnect.dao;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ma.bankconnect.entity.Agent;
import ma.bankconnect.entity.Customer;
import ma.bankconnect.service.CustomerServiceImpl;
import ma.bankconnect.service.admin.AdminService;
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
    private final AdminService adminService;
    // how is want to login
    private String who;

    public UserDetails findByEmail(String email) {
        String role = getWho();
        if (role.equals("customer")) {
            Customer customer = customerService.findByEmail(email);
            return new User(customer.getEmail(), customer.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
        } else if (role.equals("admin")) {
            Agent agent = adminService.findByEmail(email);
            return new User(agent.getEmail(), agent.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }
        return null;
    }

}
