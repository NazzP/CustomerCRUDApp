package org.example.customercrudapp.dto;

import lombok.Builder;
import lombok.Value;
import org.example.customercrudapp.entity.Customer;

import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder
public class CustomerDTO {
    Long id;
    String fullName;
    String email;
    String phone;


    public static CustomerDTO fromEntity(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .build();
    }

    public Customer toEntity() {
        Customer customer = new Customer();
        customer.setId(this.id);
        customer.setFullName(this.fullName);
        customer.setEmail(this.email);
        customer.setPhone(this.phone);
        return customer;
    }

    public static List<CustomerDTO> fromEntityList(List<Customer> customers) {
        return customers.stream()
                .map(CustomerDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
