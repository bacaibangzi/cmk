package com.sc.cmk.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sc.cmk.dao.BabyAppDao;
import com.sc.cmk.dao.BabyLatlonDao;
import com.sc.cmk.dao.BabyPhoneDao;
import com.sc.cmk.dao.ChildrenDao;
import com.sc.cmk.dao.ParentDao;
import com.sc.cmk.pojo.BabyApp;
import com.sc.cmk.pojo.BabyLatlon;
import com.sc.cmk.pojo.BabyPhone;
import com.sc.cmk.pojo.Children;
import com.sc.cmk.pojo.Parent;
import com.sc.framework.base.action.BaseAction;
import com.sc.framework.utils.StringUtil;
import com.sc.framework.vo.ConditionVO;
import com.sc.system.interfaces.IMerchantUser;
import com.sc.system.pojo.UserInfo;
import com.sc.system.service.UserRoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cmkRest")
public class CmkRest  extends BaseAction{
	
	@Autowired
	ChildrenDao childrenMapper;
	@Autowired
	ParentDao parentMapper;
	@Autowired
	private IMerchantUser iMerchantUser;  
	@Autowired
	UserRoleService userRoleService;
	@Autowired
	BabyAppDao babyAppMapper;
	@Autowired
	BabyPhoneDao babyPhoneMapper;
	@Autowired
	BabyLatlonDao  babyLatlonDao;
	
	
	@RequestMapping(value = "/dl-mobile", method = RequestMethod.POST)
	public void dl(@RequestBody String content,
			HttpServletRequest request,HttpServletResponse response){

		JSONObject jsonObject = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			jsonObject = JSONObject.fromObject(content);
			String username = jsonObject.getString("username");
			String password = jsonObject.getString("password");
			
			if(username==null||password==null){
				throw new Exception();
			}
			Map<String, Object> conditionMap = new HashMap<String, Object>();
			conditionMap.put("phone", username);
			conditionMap.put("password", StringUtil.encodeStr(password));
			List list1 = parentMapper.query(conditionMap);
			
			conditionMap.clear();
			conditionMap.put("mail", username);
			conditionMap.put("password", StringUtil.encodeStr(password));
			List list2 = parentMapper.query(conditionMap);
			
			 
			if((list1==null||list1.size()==0)&&(list2==null||list2.size()==0)){
				throw new Exception("用户密码错误");
			}
			map.put("success", true);
			response.setStatus(200);
		}catch(Exception err){ 
			err.printStackTrace();
			map.put("message", err.getMessage());
			map.put("success", false);
		}
		
		renderJson(map,response);
		
	}
	
	
	@RequestMapping(value = "/zc-mobile", method = RequestMethod.POST)
	public void zc(@RequestBody String content,
			HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			jsonObject = JSONObject.fromObject(content);
			String username = jsonObject.getString("username");
			String password = jsonObject.getString("password");
			String mail = jsonObject.getString("mail");
			String answer1 = jsonObject.getString("answer1");
			String answer2 = jsonObject.getString("answer2");
			String answer3 = jsonObject.getString("answer3");
			
			if(username==null||password==null){
				throw new Exception();
			}
			
			Map<String, Object> conditionMap = new HashMap<String, Object>();
			conditionMap.put("phone", username);
			
			List<Parent> list = parentMapper.query(conditionMap);
			if(list!=null&&list.size()>0){
				throw new Exception("手机号码已经注册");
			}
			
			conditionMap.put("mail", mail);
			
			list = parentMapper.query(conditionMap);
			if(list!=null&&list.size()>0){
				throw new Exception("mail已经注册");
			}
			
			/*
			List<UserInfo> userInfoList = userInfoService.queryUserInfosByCondition(conditionMap);
			if(userInfoList==null||userInfoList.size()>0){
				throw new Exception();
			}
			
			UserInfo userInfo = new UserInfo();
			userInfo.setUiLoginName(username);
			userInfo.setUiPassword(password);
			
			userInfoService.saveOrUpdateUserInfoInfo(userInfo);
			*/
			Parent parent = new Parent();
			parent.setPhone(username);
			parent.setMail(mail);
			parent.setPassword( StringUtil.encodeStr(password));
			parent.setAnswer1(answer1);
			parent.setAnswer2(answer2);
			parent.setAnswer3(answer3);
			parent.setDate(new Date());
			
			parentMapper.insert(parent);
			
			// data: "uiMobile=" + uiMobile + "&code=" + code + "&uiLoginName=" + uiLoginName+"&uiPassword="+uiPassword+"&uiCompany="+uiCompany+"&uiAddress="+uiAddress+"&uiTelphone="+uiTelphone,
            
			// 注册到云端
			UserInfo userInfo = new UserInfo();
			userInfo.setUiMobile(username);
			userInfo.setUiLoginName(username);
			userInfo.setUiPassword(password);
			userInfo.setUiTelphone(username);
			iMerchantUser.saveMerchantUser(userInfo);
			
			/*
			UserRole userRole = null;
			for(String riId:vo.getEntityIds().split(",")){
				userRole = new UserRole();
				userRole.setRiId(Long.parseLong(riId));
				userRole.setUiId(vo.getId());
				userRoleMapper.insert(userRole);
			}*/
			userInfo = iMerchantUser.getSystemUserByLoginName(username);
			
			/*
			UserRole userRole = new UserRole();
			userRole.setRiId(3L);
			userRole.setUiId(userInfo.getUiId());
			userRoleService.saveOrUpdateUserRole(userRole);
			*/
			
			ConditionVO vo = new ConditionVO();
			vo.setId(userInfo.getUiId());
			vo.setEntityIds("3");
			userRoleService.saveOrUpdateUserRole(vo);
			
			map.put("success", true);
			response.setStatus(200);
		}catch(Exception err){ 
			err.printStackTrace();
			map.put("message", err.getMessage());
			map.put("success", false);
		}
		
		renderJson(map,response);
	
	}	
	
	
	@RequestMapping(value = "/up-mobile", method = RequestMethod.POST)
	public void up(@RequestBody String content,
			HttpServletRequest request,HttpServletResponse response){
		JSONArray jsonArray = null;
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject object = null;
		JSONObject jsonObjct = null;
		String phone = null;
		List<Children> childrens = new ArrayList<Children>();
		try {
			//content = content.replace("\\", "");
			System.out.println("---->"+content);
			jsonObjct = JSONObject.fromObject(content);
			//System.out.println("---->"+jsonObjct.getString("babys"));
			
			// 用户信息
			jsonArray = JSONArray.fromObject(jsonObjct.getString("babys"));
			phone  = jsonObjct.getString("phone");
			Iterator<Object> it = jsonArray.iterator(); 
			
			
			String name ="" ;
			String sex ="" ;
			String birthday ="" ;
			String pwd ="" ;
			String apps ="" ;
			String photo ="" ;
			String timeflag ="" ;
			String time1 ="" ;
			String time2 ="" ;
			String id ="" ;
			
			while (it.hasNext()) {
				object = (JSONObject) it.next();
				name = object.getString("name");
				sex = object.getString("sex");
				birthday = object.getString("birthday");
				pwd = object.getString("pwd");
				 
				try {
					apps = object.getString("apps");
				} catch (Exception e) {
				}
				try {
					photo = object.getString("photo");
				} catch (Exception e) {
				}
				try {
					timeflag = object.getString("timeflag");
				} catch (Exception e) {
				}
				try {
					time1 = object.getString("time1");
				} catch (Exception e) {
				}
				try {
					time2 = object.getString("time2");
				} catch (Exception e) {
				}
				id = object.getString("id");
				 
				 
				 String parent1Id = phone;
				 Children children = new Children();
				 children.setName(name);
				 children.setSex(sex);
				 children.setBirthday(birthday);
				 children.setPwd(pwd);
				 children.setParent1Id(parent1Id);
				 
				 children.setApps(apps);
				 children.setPhoto(photo);
				 children.setTimeflag(timeflag);
				 children.setTime1(time1);
				 children.setTime2(time2);
				 children.setId(id);
				 
				 childrens.add(children); 
				 childrenMapper.replace(children);
			}
			
			// app信息
			jsonArray = JSONArray.fromObject(jsonObjct.getString("babyApps"));
			String babyId = "";
			String appName = "";
			String pckName = "";
			
			it = jsonArray.iterator(); 
			while (it.hasNext()) {
				object = (JSONObject) it.next();
				babyId = object.getString("babyId"); 
				appName = object.getString("appName");
				pckName = object.getString("pckName");
				
				BabyApp babyApp = new BabyApp();
				babyApp.setBabyId(babyId);
				babyApp.setAppName(appName);
				babyApp.setParentPhone(phone);
				babyApp.setExtend1(pckName);
				
				babyAppMapper.insert(babyApp);
			}
			
			// phone信息
			jsonArray = JSONArray.fromObject(jsonObjct.getString("babyPhones"));

			String babyName = "";
			String babyPhone = "";
			it = jsonArray.iterator(); 
			while (it.hasNext()) {
				object = (JSONObject) it.next();
				babyId = object.getString("babyId");
				babyName = object.getString("babyName");
				babyPhone = object.getString("babyPhone");
				
				BabyPhone bbyPhone = new BabyPhone();
				bbyPhone.setBabyId(babyId);
				bbyPhone.setBabyName(babyName);
				bbyPhone.setBabyPhone(babyPhone);
				bbyPhone.setParentPhone(phone);
				
				babyPhoneMapper.insert(bbyPhone);
			}
			
			
			// 把当前的手机号码的数据下载
			Map<String, Object> conditionMap = new HashMap<String, Object>();
			conditionMap.put("parent1Id", phone);
			conditionMap.put("babyPhone", phone);
			List<Children> childrenList = childrenMapper.query(conditionMap);
			List<BabyApp> babyAppList = babyAppMapper.query(conditionMap);
			List<BabyPhone> babyPhoneList = babyPhoneMapper.query(conditionMap);

			map.put("babys", childrenList);
			map.put("apps", babyAppList);
			map.put("phones", babyPhoneList);
			
			map.put("success", true);
			response.setStatus(200);
		}catch(Exception err){ 
			err.printStackTrace();
			map.put("message", err.getMessage());
			map.put("success", false);
		}
		
		renderJson(map,response);
	
	}	
	
	
	@RequestMapping(value = "/receiveLocation-mobile", method = RequestMethod.POST)
	public void receiveLocation(@RequestBody String content,
			HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObjct = null;
		try{
			System.out.println("content :"+content);
			jsonObjct = JSONObject.fromObject(content);
			//String babyId = jsonObjct.getString("babyId");
			//String parentPhone = jsonObjct.getString("parentPhone");
			Double  lat =  jsonObjct.getDouble("lat");
			Double  lon =  jsonObjct.getDouble("lon");
			String extend1 = jsonObjct.getString("extend1");
			BabyLatlon babyLatlon = new BabyLatlon();
			//babyLatlon.setBabyId(babyId);
			//babyLatlon.setParentPhone(parentPhone);
			babyLatlon.setLat(lat);
			babyLatlon.setLon(lon);
			babyLatlon.setExtend1(extend1);
			
			babyLatlonDao.insert(babyLatlon);
			
		}catch(Exception err){
			err.printStackTrace();
		}
		
	}
	
	
}
