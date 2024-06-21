package org.example.customercrudapp.controller;

import jakarta.validation.Valid;
import org.example.customercrudapp.dto.CustomerDTO;
import org.example.customercrudapp.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.create(customerDTO), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CustomerDTO>> readAllCustomers() {
        return new ResponseEntity<>(customerService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> readCustomer(@PathVariable("id") Long customerId) {
        return new ResponseEntity<>(customerService.read(customerId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long customerId,
                                                      @Valid @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.update(customerId, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long customerId) {
        customerService.delete(customerId);
    }

}
