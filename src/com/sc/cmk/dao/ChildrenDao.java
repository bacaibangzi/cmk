package com.sc.cmk.dao;

import com.sc.cmk.pojo.Children;
import com.sc.framework.base.dao.BaseDao;

public interface ChildrenDao  extends BaseDao<Children>{
	
	public void replace(Children children) throws Exception;
}
