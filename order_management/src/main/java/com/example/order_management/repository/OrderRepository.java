package com.example.order_management.repository;
import com.example.order_management.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByCustomerNameContainingIgnoreCase(String keyword);

	List<Order> findByProductNameContainingIgnoreCase(String keyword);

	List<Order> findByStatusContainingIgnoreCase(String keyword);
	
	long countByStatus(String status);
}