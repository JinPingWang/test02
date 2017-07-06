package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Test01 extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        doGet(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		PrintWriter out =response.getWriter();
		out.println("hello world");
		out.flush();
		out.close();
    }
}

