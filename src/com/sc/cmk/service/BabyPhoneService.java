package com.sc.cmk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.cmk.dao.BabyPhoneDao;
import com.sc.cmk.pojo.BabyPhone;
import com.sc.framework.base.service.BaseService;
import com.sc.framework.vo.ConditionVO;
import com.sc.framework.vo.Page;

@Service
public class BabyPhoneService extends BaseService<BabyPhone>{
	@Autowired
	BabyPhoneDao babyPhoneMapper;

	/**
	 * 删除电话
	 * 
	 * @param vo
	 * @return
	 */
	public boolean deleteBabyPhoneById(ConditionVO vo)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		try {
			for (String id : vo.getEntityIds().split(",")) {
				conditionMap.put("sn", id);
				babyPhoneMapper.delete(conditionMap);
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
		return true;
	}
	

	/**
	 * 根据Id得到电话信息
	 * 
	 * @param vo
	 * @return
	 */
	public BabyPhone getBabyPhoneById(ConditionVO vo) throws Exception {
		return babyPhoneMapper.getById(Long.parseLong(vo.getEntityId()));
	}

	/**
	 * 保存或新增电话
	 * 
	 * @param entity
	 */
	public void saveOrUpdateBabyPhoneInfo(BabyPhone entity) throws Exception {
		if (entity.getSn() == null) {  
			babyPhoneMapper.insert(entity);
		} else {
			babyPhoneMapper.update(entity);
		}
	}

	/**
	 * 根据条件查询相关电话
	 * 
	 * @param vo
	 * @return
	 */
	public List<BabyPhone> queryBabyPhonesByCondition(ConditionVO vo)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		return babyPhoneMapper.findPage(conditionMap);
	}

	/**
	 * 根据条件分页查询
	 * 
	 * @param vo
	 * @param page
	 * @return
	 */
	public Page<BabyPhone> queryBabyPhonesForPage(ConditionVO vo,
			Page<BabyPhone> page)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("babyId", vo.getEntityId());
		return super.queryForPage(babyPhoneMapper, conditionMap, page);
	}
	
	/**
	 * 根据条件分页查询
	 * 
	 * @param vo
	 * @param page
	 * @return
	 */
	public Page<BabyPhone> queryBabyPhonesPublishForPage(ConditionVO vo,
			Page<BabyPhone> page)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("babyName", vo.getNameFilter());
		return super.queryForPage(babyPhoneMapper, conditionMap, page);
	}
	
	/**
	 * 更新并替换
	 * 
	 * @param BabyPhones
	 * @throws Exception
	 */
	public void replaceBabyPhones(List<BabyPhone> BabyPhones) throws Exception{
		for(BabyPhone BabyPhone : BabyPhones){
			babyPhoneMapper.replace(BabyPhone);
		}
	}
}
