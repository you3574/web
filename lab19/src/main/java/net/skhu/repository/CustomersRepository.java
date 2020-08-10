package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Customers;

public interface CustomersRepository extends JpaRepository<Customers, Integer>  {

}

