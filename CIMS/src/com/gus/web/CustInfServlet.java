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

@WebServlet(name = "CustInfServlet", urlPatterns = "/servlet/CustInfServlet")
public class CustInfServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService service = BasicFactory.getBasicFactory().getInstance(CustomerService.class);
//        1.h获取传过来的用户
        String id = req.getParameter("id");
//        2.调用service方法查询用户的信息
        Customer customer = service.getCustomerById(id);
        if (null == customer) {
            throw new RuntimeException("找不到该客户");
        }
//        2.将客户信息封装到request域中，做请求转发到updateCust.jsp页面展示
        req.setAttribute("cust", customer);
        req.getRequestDispatcher("/updateCust.jsp").forward(req,resp);
    }
}
