/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yogee/youge">JeeSite</a> All rights reserved.
 */
package com.yogee.youge.modules.dic.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yogee.youge.common.config.Global;
import com.yogee.youge.common.persistence.Page;
import com.yogee.youge.common.web.BaseController;
import com.yogee.youge.common.utils.StringUtils;
import com.yogee.youge.modules.dic.entity.DicClient;
import com.yogee.youge.modules.dic.service.DicClientService;

import java.util.List;
import java.util.Map;

/**
 * 客户资料Controller
 * @author sunye
 * @version 2020-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/dic/dicClient")
public class DicClientController extends BaseController {

	@Autowired
	private DicClientService dicClientService;
	
	@ModelAttribute
	public DicClient get(@RequestParam(required=false) String id) {
		DicClient entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dicClientService.get(id);
		}
		if (entity == null){
			entity = new DicClient();
		}
		return entity;
	}
	
	@RequiresPermissions("dic:dicClient:view")
	@RequestMapping(value = {"list", ""})
	public String list(DicClient dicClient, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DicClient> page = dicClientService.findPage(new Page<DicClient>(request, response), dicClient); 
		model.addAttribute("page", page);
		return "modules/dic/dicClientList";
	}

	@RequiresPermissions("dic:dicClient:view")
	@RequestMapping(value = "form")
	public String form(DicClient dicClient, Model model) {
		model.addAttribute("dicClient", dicClient);
		return "modules/dic/dicClientForm";
	}

	@RequiresPermissions("dic:dicClient:edit")
	@RequestMapping(value = "save")
	public String save(DicClient dicClient, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dicClient)){
			return form(dicClient, model);
		}
		String numberPlate = dicClient.getNumberPlate().trim();
		dicClient.setNumberPlate(numberPlate);
		int count = dicClientService.findCountByNumberPlate(numberPlate);
		if(count>0){
			addMessage(redirectAttributes, "保存客户资料失败，当前车牌已添加");
			return "redirect:"+Global.getAdminPath()+"/dic/dicClient/?repage";
		}
		dicClientService.save(dicClient);
		addMessage(redirectAttributes, "保存客户资料成功");
		return "redirect:"+Global.getAdminPath()+"/dic/dicClient/?repage";
	}
	
	@RequiresPermissions("dic:dicClient:edit")
	@RequestMapping(value = "delete")
	public String delete(DicClient dicClient, RedirectAttributes redirectAttributes) {
		dicClientService.delete(dicClient);
		addMessage(redirectAttributes, "删除客户资料成功");
		return "redirect:"+Global.getAdminPath()+"/dic/dicClient/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "getNameAndPhone")
	public DicClient getNameAndPhone(@RequestParam(required=false) String id) {
		DicClient entity = dicClientService.get(id);
		return entity;
	}

}