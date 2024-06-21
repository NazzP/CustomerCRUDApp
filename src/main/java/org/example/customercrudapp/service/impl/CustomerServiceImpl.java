package org.example.customercrudapp.service.impl;

import org.example.customercrudapp.dto.CustomerDTO;
import org.example.customercrudapp.entity.Customer;
import org.example.customercrudapp.exception.CustomerAlreadyExists;
import org.example.customercrudapp.exception.NullEntityReferenceException;
import org.example.customercrudapp.repository.CustomerRepository;
import org.example.customercrudapp.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer savedCustomer;
        if (customerRepository.findByEmail(customerDTO.getEmail()).isPresent()) {
            logger.error("User with email {} already exists", customerDTO.getEmail());
            throw new CustomerAlreadyExists("Customer with this email already exist");
        } else {
            savedCustomer = customerDTO.toEntity();
            customerRepository.save(savedCustomer);
            logger.info("Customer with email {} created successfully", savedCustomer.getEmail());
        }
        return CustomerDTO.fromEntity(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> readAll() {
        return CustomerDTO.fromEntityList(customerRepository.findAll());
    }


    @Override
    @Transactional(readOnly = true)
    public CustomerDTO read(long id) {
        return CustomerDTO.fromEntity(customerRepository.findById(id).orElseThrow(
                () -> new NullEntityReferenceException("Customer with id " + id + " is not found")
        ));
    }

    @Override
    @Transactional
    public CustomerDTO update(long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(
                () -> new NullEntityReferenceException("Customer with id " + id + " is not found")
        );
        existingCustomer.setFullName(customerDTO.getFullName());
        existingCustomer.setPhone(customerDTO.getPhone());
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return CustomerDTO.fromEntity(updatedCustomer);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(
                () -> new NullEntityReferenceException("Customer with id " + id + " is not found")
        );
        existingCustomer.setActive(false);
        customerRepository.save(existingCustomer);
    }
}
