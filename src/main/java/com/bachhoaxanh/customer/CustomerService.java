package com.bachhoaxanh.customer;

import java.util.List;
import java.util.Optional;

/**
 * Business logic service skeleton class for Customer operations with VARCHAR(50) PK.
 */
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(String maKhachHang) {
        return customerRepository.findById(maKhachHang);
    }

    public List<Customer> getAllActiveCustomers() {
        return customerRepository.findAllActive();
    }

    public Customer updateCustomerProfile(String maKhachHang, Customer updatedProfile) {
        return null;
    }

    public void deactivateCustomer(String maKhachHang) {
        customerRepository.softDeleteById(maKhachHang);
    }
}
