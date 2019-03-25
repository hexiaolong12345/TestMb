package com.mintbao.dao;

import java.util.List;

import com.mintbao.bean.Account;

public interface AccountDao {
	public List<Account> selectAll();
}
