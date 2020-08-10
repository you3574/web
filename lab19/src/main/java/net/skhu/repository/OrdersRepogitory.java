package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Orders;

public interface OrdersRepogitory extends JpaRepository<Orders, Integer>  {

}

