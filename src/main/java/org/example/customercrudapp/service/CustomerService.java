package org.example.customercrudapp.service;

import org.example.customercrudapp.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO create(CustomerDTO customerDTO);
    List<CustomerDTO> readAll();
    CustomerDTO read(long id);
    CustomerDTO update(long id, CustomerDTO request);
    void delete(long id);
}
