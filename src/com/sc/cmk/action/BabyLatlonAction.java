package com.sc.cmk.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sc.cmk.pojo.BabyLatlon;
import com.sc.cmk.service.BabyLatlonService;
import com.sc.framework.base.action.BaseAction;
import com.sc.framework.vo.ConditionVO;
import com.sc.framework.vo.Page;

@Controller
@RequestMapping("/babyLatlon")
public class BabyLatlonAction  extends BaseAction{
	@Autowired
	BabyLatlonService babyLatlonService;

	/**
	 * 轨迹页面
	 * @return
	 */
	@RequestMapping(value = "/main.htm", method = RequestMethod.GET)
	public String toMain(@ModelAttribute ConditionVO vo,HttpServletRequest request) {
		//Map<Long,String> user_sh = new HashMap<Long,String>(); 
		request.setAttribute("vo", vo);
		return "cmk/babyLatlonMain";
	}

	/**
	 * 加载轨迹信息
	 * @param vo
	 * @param page
	 * @param response
	 */
	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
	@ResponseBody
	public String list(@ModelAttribute ConditionVO vo,HttpServletRequest request) throws Exception {

		//Page<BabyLatlon> list = babyLatlonService.queryBabyLatlonsForPage(vo, page);
		//super.readerPage2Json(list, response);
		String sn = request.getParameter("sn");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		
		List<BabyLatlon> list = babyLatlonService.queryBabyLatlonsByCondition(sn,from,to);
		String str = "";
		for(BabyLatlon babyLatlon : list){
			str += babyLatlon.getLon()+","+babyLatlon.getLat()+","+babyLatlon.getShijian()+",0;";
		}
		return str;
	}

	/**
	 * BabyLatlon编辑页面queryBabyLatlonsPublishForPage
	 * @param vo
	 * @param BabyLatlon
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/eidt.htm", method = RequestMethod.GET)
	public String eidt(@ModelAttribute ConditionVO vo,@ModelAttribute("form") BabyLatlon BabyLatlon,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		if(vo.getEntityId()!=null){
			BeanUtils.copyProperties(babyLatlonService.getBabyLatlonById(vo),BabyLatlon);
		}else{ 
		}
		return "cmk/babyLatlonEidt";
	}
	
	/**
	 * BabyLatlon详细信息页面
	 * @param vo
	 * @param BabyLatlon
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public String detail(@ModelAttribute ConditionVO vo,@ModelAttribute("form") BabyLatlon BabyLatlon,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		BeanUtils.copyProperties(babyLatlonService.getBabyLatlonById(vo),BabyLatlon);
		return "cmk/babyLatlonDetail";
	}
	
	/**
	 * 保存BabyLatlon信息
	 * @param vo
	 * @param BabyLatlon
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save.htm", method = RequestMethod.POST)
	public String save(@ModelAttribute ConditionVO vo,@ModelAttribute BabyLatlon BabyLatlon,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		babyLatlonService.saveOrUpdateBabyLatlonInfo(BabyLatlon);
		return "cmk/BabyLatlonMain";
	}
	
	/**
	 * 删除BabyLatlon信息
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete.htm", method = RequestMethod.GET)
	public String delete(@ModelAttribute ConditionVO vo,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		babyLatlonService.deleteBabyLatlonById(vo);
		return "cmk/BabyLatlonMain";
	}
}
