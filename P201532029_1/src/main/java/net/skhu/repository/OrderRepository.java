package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}