package com.gus.web;

import com.gus.domain.Customer;
import com.gus.domain.Page;
import com.gus.factory.BasicFactory;
import com.gus.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "pageCustServlet", urlPatterns = "/servlet/pageCustServlet")
public class pageCustServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService service = BasicFactory.getBasicFactory().getInstance(CustomerService.class);
//        1.获取当前要显示的页数
        req.setCharacterEncoding("utf-8");
        int thispage = Integer.parseInt(req.getParameter("thispage"));
//        每页显示的记录数。在这里写死为5
        int rowperpage = 5;
//        2.调用service分页查询方法进行根据页数查询
        Page page = service.pageCust(thispage, rowperpage);
//        3.将查询到的结果集存入request域中
        req.setAttribute("page", page);
//        4.请求转发至pageList.jsp中做展示
        req.getRequestDispatcher("/pageList.jsp").forward(req,resp);

    }
}
