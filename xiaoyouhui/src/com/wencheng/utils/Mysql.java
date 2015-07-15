package com.wencheng.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Mysql {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Mysql.class);
	
	private static Mysql mysql;
	private Connection conn;
	private Statement stat;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	private Mysql()
	{
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			logger.warn("Mysql() - 找不到数据库配置文件", e); //$NON-NLS-1$
		}
		
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块

			logger.warn("Mysql() - 数据库连接失败", e); //$NON-NLS-1$
		}
		
		
		
		
	}
	
	/**
	 * 获取单例的Mysql对象
	 * @return
	 */
	public static Mysql newInstance()
	{
		if(mysql == null)
		{
			mysql = new Mysql();
		}
		return mysql;
	}
	
	/**
	 * 执行查询语句
	 * @param sql
	 * @return 查询结果
	 */
	public ResultSet query(String sql)
	{
		try {
			return stat.executeQuery(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块

			logger.warn("query(String) - 数据查询出错", e); //$NON-NLS-1$

			return null;
		}
	}
	
	/**
	 * 执行插入等返回布尔值的语句
	 * @param sql
	 * @return	boolean
	 */
	public boolean execute(String sql)
	{
		return mysql.execute(sql);
	}
	
	
	/**
	 * 执行更新语句
	 * @param sql
	 * @return
	 */
	public int executeupdate(String sql)
	{
		return mysql.executeupdate(sql);
	}
	
	
}
