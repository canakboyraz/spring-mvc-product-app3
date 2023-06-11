package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    final CustomerRepository customerRepository;

    public Customer save(Customer customer){
        try{
            return customerRepository.save(customer);
        }
        catch (DataIntegrityViolationException ex){
            System.err.println(("Hatalı deneme" + ex));
            return customer;
        }catch (Exception ex){
            return null;
        }
    }

    public Customer login(String email,String password){
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsAndPasswordEqualsIgnoreCase(email, password);
        if (optionalCustomer.isPresent()){
            return optionalCustomer.get();
        }
        return null;
    }

    public Customer loginUser(String email){
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(email);
        if (optionalCustomer.isPresent()){
            return optionalCustomer.get();
        }
        return null;
    }
}
