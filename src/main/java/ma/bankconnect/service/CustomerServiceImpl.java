package ma.bankconnect.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.bankconnect.entity.Customer;
import ma.bankconnect.repository.AccountRepo;
import ma.bankconnect.repository.CustomerRepo;
import ma.bankconnect.security.Hash;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor @Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final AccountRepo accountRepo;
    private final Hash hash;
    /*
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new org.springframework.security.core.userdetails.User(
                customer.getEmail(),
                customer.getPassword(),
                authorities
        );

    }

     */

    @Override
    public Customer save(Customer customer) {
        // hash password
        String passwordBCrypt = hash.BCryptHashing(customer.getPassword());
        customer.setPassword(passwordBCrypt);
        return customerRepo.save(customer);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepo.findByEmail(email);
    }

    @Override
    public Customer findByCin(String cin) {
        return customerRepo.findByCin(cin);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }



}

