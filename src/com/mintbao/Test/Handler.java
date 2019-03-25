package com.mintbao.Test;

import java.util.List;

import org.apache.ibatis.datasource.pooled.PoolState;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mintbao.bean.Account;
import com.mintbao.dao.AccountDao;

public class Handler extends Thread {
	private AccountDao dao;
	private SqlSessionFactory factory;
	public Handler(AccountDao dao,SqlSessionFactory factory) {
		this.dao=dao;
		this.factory=factory;
	}
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			List<Account> list = dao.selectAll();
			List<Account> list2 = dao.selectAll();
			dao.selectAll();
			dao.selectAll();
			dao.selectAll();
			PooledDataSource pd =(PooledDataSource) factory.getConfiguration().getEnvironment().getDataSource();
			PoolState ps = pd.getPoolState();
			System.out.println(ps.getActiveConnectionCount());
		}
	}
}
