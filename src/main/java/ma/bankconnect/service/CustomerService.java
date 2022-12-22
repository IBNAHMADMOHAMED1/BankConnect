package ma.bankconnect.service;

import ma.bankconnect.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    Customer findByEmail(String email);
    Customer findByCin(String cin);

    List<Customer> getAllCustomers();
    void changeVerified(Long id);

}
