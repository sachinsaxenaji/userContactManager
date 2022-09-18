package com.userContactManager.userContactManager.controller;


import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.userContactManager.userContactManager.entity.MyOrder;
import com.userContactManager.userContactManager.repository.MyOrderRepository;
import com.userContactManager.userContactManager.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.Map;
import org.springframework.cglib.util.StringSwitcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller

public class DonationController {


    @Autowired
    MyOrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/create_order")
    @ResponseBody
    public String  donation(@RequestBody Map<String, Object> data, Principal principal) throws RazorpayException

    {

        System.out.println(data);
        int amt = Integer.parseInt(data.get("amount").toString());
        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_TUshSQF35AENnm","yswhbUGSXr4OlqsW63n4CpBe") ;


        JSONObject options = new JSONObject();

        options.put("amount", amt*100);
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");
        Order order = razorpayClient.Orders.create(options);
        System.out.println(order);

        MyOrder myOrder = new MyOrder();
        myOrder.setOrderId(order.get("order_id"));
        myOrder.setPaymentId(null);
        myOrder.setStatus("created");
        myOrder.setAmount(order.get("amount"));
        myOrder.setReceipt(order.get("receipt"));
        myOrder.setUser(this.userRepository.getUserByUserName(principal.getName()));
        this.orderRepository.save(myOrder);
        return order.toString();
    }

    @PostMapping("/update_order")
    @ResponseBody
    public ResponseEntity<?> orderUpdate(@RequestBody Map<String, Object> data)
    {

        MyOrder myOrder = this.orderRepository.findByOrderId(data.get("order_id").toString());
        myOrder.setStatus(data.get("payment_id").toString());
        myOrder.setStatus(data.get("status").toString());
        this.orderRepository.save(myOrder);
        return ResponseEntity.ok(Map.of("msg", "updated"));
    }


}
