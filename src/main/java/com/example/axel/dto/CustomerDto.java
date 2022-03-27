package com.example.axel.dto;

import com.example.axel.Customer;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
@Getter
@Setter
@Accessors(chain = true)
public class CustomerDto  {
    private Integer  id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String country;
    private String gender;
    private static ModelMapper modelMapper = new ModelMapper();
    public static  CustomerDto convertToDto(Customer customer) {

        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return customerDto;
    }
    public static  Customer convertToEntity(CustomerDto customerDto) {

        Customer customer = modelMapper.map(customerDto, Customer.class);
        return customer;
    }
}
