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

    @Override
    public void changeVerified(Long id) {
        Customer customer = customerRepo.findById(id).orElse(null);
        if (customer != null) {
            customer.setVerified(true);
            customerRepo.save(customer);
        }
    }

    public Customer findById(Long id) {
        return customerRepo.findById(id).orElse(null);
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
    public Long findIdCustomerByEmail(String email) {
        System.out.println("email: " + email);
        return customerRepo.findIdByEmail(email);
    }

}

