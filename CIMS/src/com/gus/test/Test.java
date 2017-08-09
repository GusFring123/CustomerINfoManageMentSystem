package com.gus.test;

import com.gus.domain.Customer;
import com.gus.exception.MsgException;
import com.gus.factory.BasicFactory;
import com.gus.service.CustomerService;

import java.sql.Date;

public class Test {
    public static void main(String[] args) {
        CustomerService service = BasicFactory.getBasicFactory().getInstance(CustomerService.class);
        for (int i = 100; i < 200; i++) {
            Customer customer = new Customer();
            customer.setName("姓名"+i);
            customer.setPreference("乒乓球,玻璃球");
            customer.setBirthday(new Date(12));
            customer.setCellphone("15745"+i);
            customer.setEmail("159"+i+"@qq.com");
            customer.setDescription(i+"asd");
            customer.setGender("男");
            customer.setType("白金客户");
            try {
                service.addCustom(customer);
            } catch (MsgException e) {
                e.printStackTrace();
            }
        }
    }
}
