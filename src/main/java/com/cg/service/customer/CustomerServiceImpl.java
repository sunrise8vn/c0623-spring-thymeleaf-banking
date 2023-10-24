package com.cg.service.customer;

import com.cg.model.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements ICustomerService {

    private static final List<Customer> customers = new ArrayList<>();
    private static long id = 1L;

    static {
        customers.add(new Customer(id++, "NVA", "nva@co.cc", "2345", "28 Nguyễn Tri Phương", BigDecimal.ZERO, false));
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Customer findById(Long aLong) {
        return null;
    }

    @Override
    public void create(Customer customer) {
        customer.setId(id++);
        customer.setBalance(BigDecimal.ZERO);
        customers.add(customer);
    }

    @Override
    public void update(Long aLong, Customer customer) {

    }

    @Override
    public void removeById(Long aLong) {

    }
}
