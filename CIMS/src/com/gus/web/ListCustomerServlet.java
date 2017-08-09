package com.gus.web;

import com.gus.domain.Customer;
import com.gus.factory.BasicFactory;
import com.gus.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListCustomerServlet", urlPatterns = "/servlet/ListCustomerServlet")
public class ListCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService service = BasicFactory.getBasicFactory().getInstance(CustomerService.class);

//        1.调用service中的查询方法，返回一个客户list
        List<Customer> list = service.getAllCustomer();
//        2.将用户信息放置在request域中，请求转发至listCustomer.jsp中
        req.setAttribute("list", list);
        req.getRequestDispatcher("/listCustomer.jsp").forward(req, resp);
    }

}
