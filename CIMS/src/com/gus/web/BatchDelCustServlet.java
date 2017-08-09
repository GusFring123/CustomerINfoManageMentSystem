package com.gus.web;

import com.gus.factory.BasicFactory;
import com.gus.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BatchDelCustServlet", urlPatterns = "/servlet/BatchDelCustServlet")
public class BatchDelCustServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService service = BasicFactory.getBasicFactory().getInstance(CustomerService.class);
//        1.获取所有的需要删除的id；
        String[] ids = req.getParameterValues("delId");
//        2.通过调用service方法执行批量删除操作
        service.batchDelCust(ids);
//        3.执行请求转发操作
        req.getRequestDispatcher("/servlet/ListCustomerServlet").forward(req,resp);
    }
}
