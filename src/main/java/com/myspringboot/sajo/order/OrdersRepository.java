package com.myspringboot.sajo.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrdersRepository extends JpaRepository<Orders, String> {
@Query(value = "SELECT os.order_date, os.order_no, os.order_status, od.qty, it.item_price, it.item_img, it.item_name " +
               "FROM orders os " +
               "JOIN order_detail od ON os.order_no = od.order_no " +
               "JOIN item it ON od.item_idx = it.item_idx " +
               "WHERE os.member_no = :memberNo", 
       nativeQuery = true)	
	List<Object[]> findOrderHistory(@Param("memberNo") Integer memerNo);
}
