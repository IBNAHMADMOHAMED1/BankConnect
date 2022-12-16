package ma.bankconnect.service;

import lombok.RequiredArgsConstructor;
import ma.bankconnect.entity.Customer;
import ma.bankconnect.repository.AccountRepo;
import ma.bankconnect.repository.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final AccountRepo accountRepo;

    @Override
    public Customer save(Customer customer) {
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

