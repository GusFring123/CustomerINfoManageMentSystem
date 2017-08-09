package com.gus.web;

import com.gus.exception.MsgException;
import com.gus.factory.BasicFactory;
import com.gus.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DelCustServlet", urlPatterns = "/servlet/DelCustServlet")
public class DelCustServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService service = BasicFactory.getBasicFactory().getInstance(CustomerService.class);
        try {
//        1.获取用户id
            String id = req.getParameter("id");
//        2.调用service中的方法，执行删除操作
            service.delCustById(id);
//        3.请求转发至ListCustomerServlet
            req.getRequestDispatcher("/servlet/ListCustomerServlet").forward(req,resp);
        } catch (MsgException e) {
            e.printStackTrace();
        }
    }
}
