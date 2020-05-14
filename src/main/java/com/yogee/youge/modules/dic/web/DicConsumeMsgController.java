/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yogee/youge">JeeSite</a> All rights reserved.
 */
package com.yogee.youge.modules.dic.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yogee.youge.common.utils.DateUtils;
import com.yogee.youge.common.utils.excel.ExportExcel;
import com.yogee.youge.modules.dic.entity.DicClient;
import com.yogee.youge.modules.dic.entity.ExportConsumeMsg;
import com.yogee.youge.modules.dic.service.DicClientService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yogee.youge.common.config.Global;
import com.yogee.youge.common.persistence.Page;
import com.yogee.youge.common.web.BaseController;
import com.yogee.youge.common.utils.StringUtils;
import com.yogee.youge.modules.dic.entity.DicConsumeMsg;
import com.yogee.youge.modules.dic.service.DicConsumeMsgService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消费记录Controller
 * @author sunye
 * @version 2020-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/dic/dicConsumeMsg")
public class DicConsumeMsgController extends BaseController {

	@Autowired
	private DicConsumeMsgService dicConsumeMsgService;

	@Autowired
	private DicClientService dicClientService;

	@ModelAttribute
	public DicConsumeMsg get(@RequestParam(required=false) String id) {
		DicConsumeMsg entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dicConsumeMsgService.get(id);
		}
		if (entity == null){
			entity = new DicConsumeMsg();
		}
		return entity;
	}
	
	@RequiresPermissions("dic:dicConsumeMsg:view")
	@RequestMapping(value = {"list", ""})
	public String list(DicConsumeMsg dicConsumeMsg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DicConsumeMsg> page = dicConsumeMsgService.findPage(new Page<DicConsumeMsg>(request, response), dicConsumeMsg);
		String sumMoney = dicConsumeMsgService.sumMoney(dicConsumeMsg);
		model.addAttribute("page", page);
		model.addAttribute("sumMoney", sumMoney);
		return "modules/dic/dicConsumeMsgList";
	}

	@RequiresPermissions("dic:dicConsumeMsg:view")
	@RequestMapping(value = "form")
	public String form(DicConsumeMsg dicConsumeMsg, Model model) {
		List<DicClient> dicClientList =  dicClientService.findList(new DicClient());
		if(dicConsumeMsg.getIsNewRecord()){
			//新增
			model.addAttribute("consumeDate", new Date());
		}else{
			//修改
			model.addAttribute("consumeDate", dicConsumeMsg.getConsumeDate());
		}
		model.addAttribute("dicConsumeMsg", dicConsumeMsg);
		model.addAttribute("dicClientList", dicClientList);
		return "modules/dic/dicConsumeMsgForm";
	}

	@RequiresPermissions("dic:dicConsumeMsg:edit")
	@RequestMapping(value = "save")
	public String save(DicConsumeMsg dicConsumeMsg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dicConsumeMsg)){
			return form(dicConsumeMsg, model);
		}
		dicConsumeMsgService.save(dicConsumeMsg);
		addMessage(redirectAttributes, "保存消费记录成功");
		return "redirect:"+Global.getAdminPath()+"/dic/dicConsumeMsg/?repage";
	}
	
	@RequiresPermissions("dic:dicConsumeMsg:edit")
	@RequestMapping(value = "delete")
	public String delete(DicConsumeMsg dicConsumeMsg, RedirectAttributes redirectAttributes) {
		dicConsumeMsgService.delete(dicConsumeMsg);
		addMessage(redirectAttributes, "删除消费记录成功");
		return "redirect:"+Global.getAdminPath()+"/dic/dicConsumeMsg/?repage";
	}

    /**
     * 导出消费记录
     *
     * @param request
     * @param response
     * @param redirectAttributes
     * @return String
     */
    @RequestMapping(value = "export")
    public String export (HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes){
        try {
            Map map = new HashMap();
            String fileName = "消费明细.xlsx";
            String phone = request.getParameter("phone");
            String name = request.getParameter("name");
            String numberPlate = request.getParameter("numberPlate");
            String sumMoney = request.getParameter("sumMoney");
			DicConsumeMsg dicConsumeMsg = new DicConsumeMsg();
			dicConsumeMsg.setPhone(phone);
			dicConsumeMsg.setName(name);
			dicConsumeMsg.setNumberPlate(numberPlate);
            List<ExportConsumeMsg> childOrders = dicConsumeMsgService.findAllExport(dicConsumeMsg);
            new ExportExcel("消费明细"+"（消费总金额为："+sumMoney+")", ExportConsumeMsg.class, 1).setDataList(childOrders).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "消费明细下载失败！失败信息："+e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/modules/dic/dicConsumeMsgList/?repage";
    }

}