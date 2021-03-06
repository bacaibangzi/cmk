package com.sc.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.framework.vo.ConditionVO;
import com.sc.system.dao.UserRoleMapper;
import com.sc.system.pojo.UserRole;

/**
 * 用户角色service
 * 
 * @author cuibin
 * 
 */
@Service
public class UserRoleService {
	@Autowired
	UserRoleMapper userRoleMapper;

	/**
	 * 根据条件查找用户角色信息
	 * 
	 * @param vo
	 * @return
	 */
	public List<UserRole> queryUserRoleByCondition(ConditionVO vo) throws Exception {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		// 默认加载为空数据
		if(vo.getId()==null){
			conditionMap.put("uiId", -1L);
		}else{
			conditionMap.put("uiId", vo.getId());
		}
		return userRoleMapper.query(conditionMap);
	}
	
	
	/**
	 * 保存用户角色信息
	 * @param vo
	 */
	public void saveOrUpdateUserRole(ConditionVO vo)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		// 先删除用户角色信息
		conditionMap.put("uiId", vo.getId());
		userRoleMapper.delete(conditionMap);
		
		UserRole userRole = null;
		for(String riId:vo.getEntityIds().split(",")){
			userRole = new UserRole();
			userRole.setRiId(Long.parseLong(riId));
			userRole.setUiId(vo.getId());
			userRoleMapper.insert(userRole);
		}
	}
	
}
