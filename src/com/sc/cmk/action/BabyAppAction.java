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

import com.sc.cmk.pojo.BabyApp;
import com.sc.cmk.service.BabyAppService;
import com.sc.framework.base.action.BaseAction;
import com.sc.framework.vo.ConditionVO;
import com.sc.framework.vo.Page;

@Controller
@RequestMapping("/babyApp")
public class BabyAppAction  extends BaseAction{
	@Autowired
	BabyAppService babyAppService;

	/**
	 * APP页面
	 * @return
	 */
	@RequestMapping(value = "/main.htm", method = RequestMethod.GET)
	public String toMain(@ModelAttribute ConditionVO vo,HttpServletRequest request) {
		//Map<Long,String> user_sh = new HashMap<Long,String>(); 
		request.setAttribute("vo", vo);
		return "cmk/babyAppMain";
	}

	/**
	 * 加载APP信息
	 * @param vo
	 * @param page
	 * @param response
	 */
	@RequestMapping(value = "/list.htm", method = RequestMethod.POST)
	@ResponseBody
	public void list(@ModelAttribute ConditionVO vo,
			@ModelAttribute Page<BabyApp> page, HttpServletResponse response) throws Exception {
		super.setPageInfo(page);
		Page<BabyApp> list = babyAppService.queryBabyAppsForPage(vo, page);
		super.readerPage2Json(list, response);

	}

	/**
	 * BabyApp编辑页面queryBabyAppsPublishForPage
	 * @param vo
	 * @param BabyApp
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/eidt.htm", method = RequestMethod.GET)
	public String eidt(@ModelAttribute ConditionVO vo,@ModelAttribute("form") BabyApp BabyApp,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		if(vo.getEntityId()!=null){
			BeanUtils.copyProperties(babyAppService.getBabyAppById(vo),BabyApp);
		}else{ 
		}
		return "cmk/babyAppEidt";
	}
	
	/**
	 * BabyApp详细信息页面
	 * @param vo
	 * @param BabyApp
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public String detail(@ModelAttribute ConditionVO vo,@ModelAttribute("form") BabyApp BabyApp,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		BeanUtils.copyProperties(babyAppService.getBabyAppById(vo),BabyApp);
		return "cmk/babyAppDetail";
	}
	
	/**
	 * 保存BabyApp信息
	 * @param vo
	 * @param BabyApp
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save.htm", method = RequestMethod.POST)
	public String save(@ModelAttribute ConditionVO vo,@ModelAttribute BabyApp BabyApp,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		babyAppService.saveOrUpdateBabyAppInfo(BabyApp);
		return "cmk/babyAppMain";
	}
	
	/**
	 * 删除BabyApp信息
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete.htm", method = RequestMethod.GET)
	public String delete(@ModelAttribute ConditionVO vo,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		babyAppService.deleteBabyAppById(vo);
		return "cmk/babyAppMain";
	}
}
