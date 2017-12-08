package com.sc.cmk.dao;

import com.sc.cmk.pojo.BabyPhone;
import com.sc.framework.base.dao.BaseDao;

public interface BabyPhoneDao extends BaseDao<BabyPhone>{

	public void replace(BabyPhone babyPhone) throws Exception;
}
