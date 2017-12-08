package com.sc.cmk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.cmk.dao.BabyAppDao;
import com.sc.cmk.pojo.BabyApp;
import com.sc.framework.base.service.BaseService;
import com.sc.framework.vo.ConditionVO;
import com.sc.framework.vo.Page;

@Service
public class BabyAppService extends BaseService<BabyApp>{
	@Autowired
	BabyAppDao babyAppMapper;

	/**
	 * 删除APP
	 * 
	 * @param vo
	 * @return
	 */
	public boolean deleteBabyAppById(ConditionVO vo)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		try {
			for (String id : vo.getEntityIds().split(",")) {
				conditionMap.put("sn", id);
				babyAppMapper.delete(conditionMap);
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
		return true;
	}
	

	/**
	 * 根据Id得到APP信息
	 * 
	 * @param vo
	 * @return
	 */
	public BabyApp getBabyAppById(ConditionVO vo) throws Exception {
		return babyAppMapper.getById(Long.parseLong(vo.getEntityId()));
	}

	/**
	 * 保存或新增APP
	 * 
	 * @param entity
	 */
	public void saveOrUpdateBabyAppInfo(BabyApp entity) throws Exception {
		if (entity.getSn() == null) {  
			babyAppMapper.insert(entity);
		} else {
			babyAppMapper.update(entity);
		}
	}

	/**
	 * 根据条件查询相关APP
	 * 
	 * @param vo
	 * @return
	 */
	public List<BabyApp> queryBabyAppsByCondition(ConditionVO vo)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		return babyAppMapper.findPage(conditionMap);
	}

	/**
	 * 根据条件分页查询
	 * 
	 * @param vo
	 * @param page
	 * @return
	 */
	public Page<BabyApp> queryBabyAppsForPage(ConditionVO vo,
			Page<BabyApp> page)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("babyId", vo.getEntityId());
		return super.queryForPage(babyAppMapper, conditionMap, page);
	}
	
	/**
	 * 根据条件分页查询
	 * 
	 * @param vo
	 * @param page
	 * @return
	 */
	public Page<BabyApp> queryBabyAppsPublishForPage(ConditionVO vo,
			Page<BabyApp> page)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("appName", vo.getNameFilter());
		return super.queryForPage(babyAppMapper, conditionMap, page);
	}
	
	/**
	 * 更新并替换
	 * 
	 * @param BabyApps
	 * @throws Exception
	 */
	public void replaceBabyApps(List<BabyApp> BabyApps) throws Exception{
		for(BabyApp BabyApp : BabyApps){
			babyAppMapper.replace(BabyApp);
		}
	}
}
