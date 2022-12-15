package ma.bankconnect.service;

import ma.bankconnect.entity.Customer;

public interface CustomerService {
    Customer save(Customer customer);
    Customer findByEmail(String email);
    Customer findByCin(String cin);

}
