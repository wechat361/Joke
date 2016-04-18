package com.ctlovedove.joke.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ctlovedove.joke.quartz.CatchJokeInfoQuartz;


public class Test {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml",
				"springmvc-servlet.xml");
		//ManagerService d = (ManagerService)ctx.getBean("managerService");
		//Manager manager = d.queryByName("joke");
		//Manager manager = d.queryManagerByNameAndPwd("joke", "");
		//Manager m = new Manager();
		//m.setAccountId(1);
		//List<Manager> list = d.queryManagerListByPager(m, 1, 2);
		//int count = d.getTotalCountByPager(new Manager());
		//System.out.println(count);
		String url = "http://lengxiaohua.com/";
		CatchJokeInfoQuartz catchJokeInfoQuartz = ctx.getBean("catchJokeInfoQuartz", CatchJokeInfoQuartz.class);
		catchJokeInfoQuartz.execute(url);
//		test();
	}
	
	public static void test() throws SQLException{
		Connection conn = null;
        String sql;
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
        // 下面语句之前就要先创建javademo数据库
        String url = "jdbc:mysql://127.0.0.1:3306/jokedb?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull";
 
        try {
            // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url, "root", "000000");
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            sql = "select * from t_manager where accountName = 'joke' and password = '123456'";
            //int result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
            
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
	}
	
	private void test2() {
		ProxyFactoryBean bean = new ProxyFactoryBean();
	}

}
