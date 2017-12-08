package com.sc.cmk.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sc.cmk.pojo.BabyPhone;
import com.sc.cmk.service.BabyPhoneService;
import com.sc.framework.base.action.BaseAction;
import com.sc.framework.vo.ConditionVO;
import com.sc.framework.vo.Page;

@Controller
@RequestMapping("/babyPhone")
public class BabyPhoneAction  extends BaseAction{
	@Autowired
	BabyPhoneService babyPhoneService;

	/**
	 * 电话页面
	 * @return
	 */
	@RequestMapping(value = "/main.htm", method = RequestMethod.GET)
	public String toMain(@ModelAttribute ConditionVO vo,HttpServletRequest request) {
		//Map<Long,String> user_sh = new HashMap<Long,String>(); 
		request.setAttribute("vo", vo);
		return "cmk/babyPhoneMain";
	}

	/**
	 * 加载电话信息
	 * @param vo
	 * @param page
	 * @param response
	 */
	@RequestMapping(value = "/list.htm", method = RequestMethod.POST)
	@ResponseBody
	public void list(@ModelAttribute ConditionVO vo,
			@ModelAttribute Page<BabyPhone> page, HttpServletResponse response) throws Exception {
		super.setPageInfo(page);
		Page<BabyPhone> list = babyPhoneService.queryBabyPhonesForPage(vo, page);
		super.readerPage2Json(list, response);

	}

	/**
	 * BabyPhone编辑页面queryBabyPhonesPublishForPage
	 * @param vo
	 * @param BabyPhone
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/eidt.htm", method = RequestMethod.GET)
	public String eidt(@ModelAttribute ConditionVO vo,@ModelAttribute("form") BabyPhone BabyPhone,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		if(vo.getEntityId()!=null){
			BeanUtils.copyProperties(babyPhoneService.getBabyPhoneById(vo),BabyPhone);
		}else{ 
		}
		return "cmk/BabyPhoneEidt";
	}
	
	/**
	 * BabyPhone详细信息页面
	 * @param vo
	 * @param BabyPhone
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public String detail(@ModelAttribute ConditionVO vo,@ModelAttribute("form") BabyPhone BabyPhone,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		BeanUtils.copyProperties(babyPhoneService.getBabyPhoneById(vo),BabyPhone);
		return "cmk/babyPhoneDetail";
	}
	
	/**
	 * 保存BabyPhone信息
	 * @param vo
	 * @param BabyPhone
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save.htm", method = RequestMethod.POST)
	public String save(@ModelAttribute ConditionVO vo,@ModelAttribute BabyPhone BabyPhone,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		babyPhoneService.saveOrUpdateBabyPhoneInfo(BabyPhone);
		return "cmk/BabyPhoneMain";
	}
	
	/**
	 * 删除BabyPhone信息
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete.htm", method = RequestMethod.GET)
	public String delete(@ModelAttribute ConditionVO vo,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		babyPhoneService.deleteBabyPhoneById(vo);
		return "cmk/BabyPhoneMain";
	}
}
