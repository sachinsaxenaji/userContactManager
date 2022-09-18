package com.userContactManager.userContactManager.repository;

import com.userContactManager.userContactManager.entity.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyOrderRepository extends JpaRepository<MyOrder, Long> {

    public MyOrder findByOrderId(String orderID);


}
