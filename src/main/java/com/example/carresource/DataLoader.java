package com.example.carresource;

import com.example.carresource.model.Customer;
import com.example.carresource.model.Vehicle;
import com.example.carresource.repo.CustomerRepository;
import com.example.carresource.repo.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Customer customer = new Customer();
        customer.setUserName("example@carresource.com");
        customer.setPassword("not encoded password");
        customer.setFullName("customer one");
        customer.setAddress("nowhere");
        customer.setPhoneNumber("123456789");
        Customer saveCustomer = customerRepository.save(customer);

        Vehicle mercedes = new Vehicle();
        mercedes.setBrand("Mercedes");
        mercedes.setModel("S-500");
        mercedes.setPlateNumber("ABC-123");
        mercedes.setCustomer(saveCustomer);
        vehicleRepository.save(mercedes);

        Vehicle bmw = new Vehicle();
        bmw.setBrand("BMW");
        bmw.setModel("i8");
        bmw.setPlateNumber("GFE-432");
        bmw.setCustomer(saveCustomer);
        vehicleRepository.save(bmw);
    }
}
