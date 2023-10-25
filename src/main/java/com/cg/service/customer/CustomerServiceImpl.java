package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.Deposit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements ICustomerService {

    private static final List<Customer> customers = new ArrayList<>();
    private static long id = 1L;

    static {
        customers.add(new Customer(id++, "NVA", "nva@co.cc", "2345", "28 Nguyễn Tri Phương", BigDecimal.ZERO, false));
    }

    @Override
    public List<Customer> findAll() {
        return customers.stream().filter(cus -> !cus.getDeleted()).collect(Collectors.toList());
    }

    @Override
    public Customer findById(Long id) {
        Customer customer =customers.stream().filter(cus -> cus.getId().equals(id)).findFirst().orElse(null);
        return customer;
    }

    @Override
    public List<Customer> findAllWithoutId(Long id) {
        return customers.stream().filter(customer -> !Objects.equals(customer.getId(), id)).collect(Collectors.toList());
    }

    @Override
    public void create(Customer customer) {
        customer.setId(id++);
        customer.setBalance(BigDecimal.ZERO);
        customer.setDeleted(false);
        customers.add(customer);
    }

    @Override
    public void update(Long id, Customer customer) {
        int index = customers.indexOf(findById(id));
        customers.set(index, customer);
    }

    @Override
    public void deposit(Deposit deposit) {
        Customer customer = deposit.getCustomer();
        BigDecimal currentBalance = customer.getBalance();
        BigDecimal transactionAmount = deposit.getTransactionAmount();
        BigDecimal newBalance = currentBalance.add(transactionAmount);
        customer.setBalance(newBalance);

        update(customer.getId(), customer);

    }

    @Override
    public void removeById(Long id) {
        int index = customers.indexOf(findById(id));

        Customer customer = customers.stream().filter(cus -> cus.getId().equals(id)).findFirst().orElse(null);

        assert customer != null;
        customer.setDeleted(true);

        customers.set(index, customer);

    }
}
