package com.sc.cmk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.cmk.dao.BabyLatlonDao;
import com.sc.cmk.pojo.BabyLatlon;
import com.sc.framework.base.service.BaseService;
import com.sc.framework.vo.ConditionVO;
import com.sc.framework.vo.Page;

@Service
public class BabyLatlonService extends BaseService<BabyLatlon>{
	@Autowired
	BabyLatlonDao babyLatlonMapper;

	/**
	 * 删除位置
	 * 
	 * @param vo
	 * @return
	 */
	public boolean deleteBabyLatlonById(ConditionVO vo)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		try {
			for (String id : vo.getEntityIds().split(",")) {
				conditionMap.put("sn", id);
				babyLatlonMapper.delete(conditionMap);
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
		return true;
	}
	

	/**
	 * 根据Id得到位置信息
	 * 
	 * @param vo
	 * @return
	 */
	public BabyLatlon getBabyLatlonById(ConditionVO vo) throws Exception {
		return babyLatlonMapper.getById(Long.parseLong(vo.getEntityId()));
	}

	/**
	 * 保存或新增位置
	 * 
	 * @param entity
	 */
	public void saveOrUpdateBabyLatlonInfo(BabyLatlon entity) throws Exception {
		if (entity.getSn() == null) {  
			babyLatlonMapper.insert(entity);
		} else {
			babyLatlonMapper.update(entity);
		}
	}

	/**
	 * 根据条件查询相关位置
	 * 
	 * @param vo
	 * @return
	 */
	public List<BabyLatlon> queryBabyLatlonsByCondition(String sn,String from,String to)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("sn", sn);
		conditionMap.put("from", from);
		conditionMap.put("to", to);
		return babyLatlonMapper.query(conditionMap);
	}

	/**
	 * 根据条件分页查询
	 * 
	 * @param vo
	 * @param page
	 * @return
	 */
	public Page<BabyLatlon> queryBabyLatlonsForPage(ConditionVO vo,
			Page<BabyLatlon> page)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("babyId", vo.getEntityId());
		return super.queryForPage(babyLatlonMapper, conditionMap, page);
	}
	
	/**
	 * 根据条件分页查询
	 * 
	 * @param vo
	 * @param page
	 * @return
	 */
	public Page<BabyLatlon> queryBabyLatlonsPublishForPage(ConditionVO vo,
			Page<BabyLatlon> page)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("name", vo.getNameFilter());
		return super.queryForPage(babyLatlonMapper, conditionMap, page);
	}
	
	/**
	 * 更新并替换
	 * 
	 * @param BabyLatlons
	 * @throws Exception
	 */
	public void replaceBabyLatlons(List<BabyLatlon> BabyLatlons) throws Exception{
		for(BabyLatlon BabyLatlon : BabyLatlons){
			babyLatlonMapper.replace(BabyLatlon);
		}
	}
}
