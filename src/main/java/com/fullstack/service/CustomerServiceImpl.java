package com.fullstack.service;

import com.fullstack.model.Customer;
import com.fullstack.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer findByEmailId(String custEmailId) {
        return customerRepository.findByCustEmailId(custEmailId);
    }

    @Override
    public Customer signUp(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean signIn(String custEmailId, String custPassword) {

        boolean flag = false;

        Customer customer = customerRepository.findByCustEmailIdAndCustPassword(custEmailId, custPassword);

        if (customer != null && customer.getCustEmailId().equals(custEmailId) && customer.getCustPassword().equals(custPassword)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Optional<Customer> findById(int custId) {
        return Optional.ofNullable(customerRepository.findById(custId).map(customer -> customer.isDeleted() ? null : customer).orElse(null));
    }

    @Override
    public List<Customer> findAll() {

        return customerRepository.findAll().stream().filter(customer -> !customer.isDeleted()).collect(Collectors.toList()); //Returns only non deleted customer
    }

    @Override
    public List<Customer> findByName(String custName) {
        return customerRepository.findByCustName(custName);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(int custId) {
        customerRepository.deleteById(custId);
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }

    @Override
    public void save(Customer existingCustomer) {
        customerRepository.save(existingCustomer);
    }
}
