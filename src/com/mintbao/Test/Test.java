package com.mintbao.Test;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.ibatis.datasource.pooled.PoolState;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mintbao.bean.Account;
import com.mintbao.dao.AccountDao;

public class Test {
	public static void main(String[] args) {
		try{
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(new FileInputStream("conf/configeration.xml"));
		SqlSession session = factory.openSession();
		PooledDataSource pd =(PooledDataSource) factory.getConfiguration().getEnvironment().getDataSource();
		PoolState ps = pd.getPoolState();
		System.out.println(ps.getActiveConnectionCount());
		System.out.println(ps.getIdleConnectionCount());
		
		AccountDao dao = session.getMapper(AccountDao.class);
		System.out.println("开始执行查询：");
		List<Account> list = dao.selectAll();
		System.out.println("共有"+list.size()+"记录。");
		System.out.println(ps.getActiveConnectionCount());
		System.out.println(ps.getIdleConnectionCount());
		//ConcurrentHashMap
		//Collections.synchronizedMap()
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
