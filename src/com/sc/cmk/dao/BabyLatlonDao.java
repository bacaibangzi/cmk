package com.sc.cmk.dao;

import com.sc.cmk.pojo.BabyLatlon;
import com.sc.framework.base.dao.BaseDao;

public interface BabyLatlonDao extends BaseDao<BabyLatlon>{

	public void replace(BabyLatlon babyLatlon) throws Exception;
}
