package com.sc.cmk.dao;

import com.sc.cmk.pojo.BabyApp;
import com.sc.framework.base.dao.BaseDao;

public interface BabyAppDao  extends BaseDao<BabyApp>{
	
	public void replace(BabyApp babyApp) throws Exception;
}
