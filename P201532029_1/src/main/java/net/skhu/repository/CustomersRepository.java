package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Customer;

public interface CustomersRepository extends JpaRepository<Customer, Integer>  {

}

