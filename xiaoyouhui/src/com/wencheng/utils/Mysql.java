package com.wencheng.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Mysql {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Mysql.class);
	
	private static Mysql mysql;
	
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
		
	}
	
	public static Mysql newInstance()
	{
		if(mysql == null)
		{
			mysql = new Mysql();
		}
		return mysql;
	}

}
