package controller;

import datasource.DbcpDataSource;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestMysql extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5765160188981936290L;

	@RequestMapping("/select")
	public void select(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String userId = id;
		String userAge = null;
		String sql = "";
		sql = "select * from test01 where id="+userId;
		System.out.println(sql);
		try {
			//1：连接数据库，查询数据库，获得结果集
			Connection conn = DbcpDataSource.getInstance().getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);

			//2：读取查询结果
			while (rs.next()) {
				userAge = rs.getString(2);
			}

			//3：关闭连接
			rs.close();
			stat.close();
			conn.close();
		}
		catch (Exception e) {
			Logger.getLogger("test01").log(Level.SEVERE, e.getMessage());
		}
		finally {
			//4：返回结果
			PrintWriter out = response.getWriter();
			out.write("select message -> id: " + userId + ", age: " + userAge);
			out.flush();
			out.close();
		}
	}

	@RequestMapping("/update")
	public void update(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String age = request.getParameter("age");
		String userId = id;
		String userAge = age;
		String sql = "";
		sql = "update test01 set age=" + userAge + " where id=" + userId;
		System.out.println(sql);
		PrintWriter out = response.getWriter();
		try {
			//1：连接数据库，查询数据库，获得结果集
			Connection conn = DbcpDataSource.getInstance().getConnection();
			Statement stat = conn.createStatement();
			stat.execute(sql);

			//2：关闭连接
			stat.close();
			conn.close();
		}
		catch(Exception e) {
			Logger.getLogger("test01").log(Level.SEVERE, e.getMessage());
			out.write("update message -> update error");
		}
		finally {
			//3：返回结果
			out.write("update message -> id: " + userId + ", age: " + age);
			out.flush();
			out.close();
		}
	}

	@RequestMapping("/add")
	public void add(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String age = request.getParameter("age");
		String userId = id;
		String userAge = age;
		String sql = "";
		sql = "insert into test01(age) values(" + userAge + ")";
		System.out.println(sql);
		PrintWriter out = response.getWriter();
		try {
			//1：连接数据库，查询数据库，获得结果集
			Connection conn = DbcpDataSource.getInstance().getConnection();
			Statement stat = conn.createStatement();
			stat.execute(sql);

			//2：关闭连接
			stat.close();
			conn.close();
		}
		catch(Exception e) {
			out.write("add message -> add error");
			Logger.getLogger("test01").log(Level.SEVERE, e.getMessage());
		}
		finally {
			//3：返回结果
			out.write("add message -> age: " + age + " is add");
			out.flush();
			out.close();
	    }
	}

	@RequestMapping("/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String userId = id;
		String sql = "";
		sql = "delete from test01 where id=" + userId;
		System.out.println(sql);
		PrintWriter out = response.getWriter();
		try {
			//1：连接数据库，查询数据库，获得结果集
			Connection conn = DbcpDataSource.getInstance().getConnection();
			Statement stat = conn.createStatement();
			stat.execute(sql);

			//2：关闭连接
			stat.close();
			conn.close();
		}
		catch(Exception e) {
			out.write("delete message -> delete error");
			Logger.getLogger("test01").log(Level.SEVERE, e.getMessage());
		}
		finally {
			//3：返回结果
			out.write("delete message -> id: " + userId + " is delete");
			out.flush();
			out.close();
		}
	}
}
