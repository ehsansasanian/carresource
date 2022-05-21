package com.example.carresource.service;

import com.example.carresource.model.Customer;

public interface CustomerService {
    Customer registerCustomer(Customer customer);

    Customer updateCustomer();
}
