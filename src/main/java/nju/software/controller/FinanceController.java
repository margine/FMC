﻿package nju.software.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nju.software.dataobject.Money;
import nju.software.model.OrderInfo;
import nju.software.service.FinanceService;
import nju.software.service.impl.FinanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FinanceController {
	
	@Autowired
	private FinanceService financeService;
	

	// ===========================样衣金确认=================================
	@RequestMapping(value = "/finance/confirmSampleMoneyList.do")
	@Transactional(rollbackFor = Exception.class)
	public String confirmSampleMoneyList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String actorId = FinanceServiceImpl.ACTOR_FINANCE_MANAGER;
		List<OrderInfo> list = financeService
				.getConfirmSampleMoneyList(actorId);
		model.addAttribute("list", list);
		return "/finance/confirmSampleMoneyList";
	}

	
	@RequestMapping(value = "/finance/confirmSampleMoneyDetail.do")
	@Transactional(rollbackFor = Exception.class)
	public String confirmSampleMoneyDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String orderId = request.getParameter("orderId");
		String actorId = FinanceServiceImpl.ACTOR_FINANCE_MANAGER;
		OrderInfo orderInfo = financeService.getConfirmDepositDetail(actorId,
				Integer.parseInt(orderId));
		model.addAttribute("orderInfo", orderInfo);
		return "/finance/confirmSampleMoneyDetail";
	}

	
	@RequestMapping(value = "/finance/confirmSampleMoneySubmit.do")
	@Transactional(rollbackFor = Exception.class)
	public String confirmSampleMoneySubmit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String s_orderId_request = (String) request.getParameter("order_id");
		int orderId_request = Integer.parseInt(s_orderId_request);
		String s_taskId = request.getParameter("task_id");
		long taskId = Long.parseLong(s_taskId);
		boolean receivedsamplejin = Boolean.parseBoolean(request
				.getParameter("receivedsamplejin"));
		Money money = null;

		if (receivedsamplejin) {
			String s_moneyAmount = request.getParameter("money_amount");
			double moneyAmount = Double.parseDouble(s_moneyAmount);
			String moneyState = request.getParameter("money_state");
			String moneyType = request.getParameter("money_type");
			String moneyBank = request.getParameter("money_bank");
			String moneyName = request.getParameter("money_name");
			String moneyNumber = request.getParameter("money_number");
			String moneyRemark = request.getParameter("money_remark");

			if (!(moneyAmount < 0) && (moneyState != null)
					&& (moneyType != null)) {
				money = new Money();
				money.setOrderId(orderId_request);
				money.setMoneyAmount(moneyAmount);
				money.setMoneyState(moneyState);
				money.setMoneyType(moneyType);
				money.setMoneyBank(moneyBank);
				money.setMoneyName(moneyName);
				money.setMoneyNumber(moneyNumber);
				money.setMoneyRemark(moneyRemark);
			}
		}
		financeService.confirmSampleMoneySubmit(account, orderId_request, taskId, processId, receivedsamplejin, money);
		
		return "redirect:/finance/confirmSampleMoneyList.do";
	}
	
	/**
	 * 确认样衣制作金详情
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "finance/confirmSampleMoneyDetail.do", method= RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
	public String confirmSampleMoneyDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		
		System.out.println("sample corfirm ================ show detail");
		OrderModel orderModel = null;
		Account account = (Account) request.getSession().getAttribute("cur_user");
//		String actorId = account.getUserRole();
		String s_orderId_request = (String) request.getParameter("order_id");
		int orderId_request = Integer.parseInt(s_orderId_request);
		String s_taskId = request.getParameter("task_id");
		long taskId = Long.parseLong(s_taskId);
		String s_processId = request.getParameter("process_id");
		long processId = Long.parseLong(s_processId);
		orderModel = orderService.getOrderDetail(orderId_request, taskId, processId);
		model.addAttribute("orderModel", orderModel);

		String actorId = FinanceServiceImpl.ACTOR_FINANCE_MANAGER;
		financeService.confirmSampleMoneySubmit(actorId, taskId,
				receivedsamplejin, money);
		return "forward:/finance/confirmSampleMoneyList.do";

	}

	
	// ===========================定金确认===================================
	@RequestMapping(value = "/finance/confirmDepositList.do")
	@Transactional(rollbackFor = Exception.class)
	public String confirmDepositList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String actorId = FinanceServiceImpl.ACTOR_FINANCE_MANAGER;
		List<OrderInfo> list = financeService.getConfirmDepositList(actorId);
		model.addAttribute("list", list);
		return "/finance/confirmDepositList";
	}

	
	@RequestMapping(value = "/finance/confirmDepositDetail.do", method = RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
	public String confirmDepositDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String orderId = request.getParameter("orderId");
		String actorId = FinanceServiceImpl.ACTOR_FINANCE_MANAGER;
		OrderInfo orderInfo = financeService.getConfirmDepositDetail(actorId,
				Integer.parseInt(orderId));
		model.addAttribute("orderInfo", orderInfo);
		return "/finance/confirmSampleMoneyDetail";
	}

	
	@RequestMapping(value = "/finance/confirmDepositSubmit.do", method = RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
	public String confirmDepositSubmit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

		String s_orderId_request = (String) request.getParameter("order_id");
		int orderId_request = Integer.parseInt(s_orderId_request);
		String s_taskId = request.getParameter("task_id");
		long taskId = Long.parseLong(s_taskId);
		boolean epositok = Boolean.parseBoolean(request
				.getParameter("epositok"));
		Money money = null;

		if (epositok) {
			String s_moneyAmount = request.getParameter("money_amount");
			double moneyAmount = Double.parseDouble(s_moneyAmount);
			String moneyState = request.getParameter("money_state");
			String moneyType = request.getParameter("money_type");
			String moneyBank = request.getParameter("money_bank");
			String moneyName = request.getParameter("money_name");
			String moneyNumber = request.getParameter("money_number");
			String moneyRemark = request.getParameter("money_remark");

			if (!(moneyAmount < 0) && (moneyState != null)
					&& (moneyType != null)) {
				money = new Money();
				money.setOrderId(orderId_request);
				money.setMoneyAmount(moneyAmount);
				money.setMoneyState(moneyState);
				money.setMoneyType(moneyType);
				money.setMoneyBank(moneyBank);
				money.setMoneyName(moneyName);
				money.setMoneyNumber(moneyNumber);
				money.setMoneyRemark(moneyRemark);
			}
		}
		String actorId = FinanceServiceImpl.ACTOR_FINANCE_MANAGER;
		financeService.confirmDepositSubmit(actorId, taskId, epositok, money);
		return "forward:/finance/confirmDepositList.do";
	}

	
	// ===========================尾款确认===================================
	@RequestMapping(value = "finance/confirmFinalPaymentList.do", method = RequestMethod.GET)
	@Transactional(rollbackFor = Exception.class)
	public String confirmFinalPaymentList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

		String actorId = FinanceServiceImpl.ACTOR_FINANCE_MANAGER;
		List<OrderInfo> list = financeService
				.getConfirmFinalPaymentList(actorId);
		model.addAttribute("list", list);
		return "/finance/confirmFinalPaymentList";

	}

	
	@RequestMapping(value = "/finance/confirmFinalPaymentDetail.do", method = RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
	public String confirmFinalPaymentDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String orderId = request.getParameter("orderId");
		String actorId = FinanceServiceImpl.ACTOR_FINANCE_MANAGER;
		OrderInfo orderInfo = financeService.getConfirmFinalPaymentDetail(
				actorId, Integer.parseInt(orderId));
		model.addAttribute("orderInfo", orderInfo);
		return "/finance/confirmFinalPaymentDetail";
	}

	@RequestMapping(value = "/finance/confirmFinalPaymentSubmit.do", method = RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
	public String confirmFinalPaymentSubmit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String s_orderId_request = (String) request.getParameter("order_id");
		int orderId_request = Integer.parseInt(s_orderId_request);
		String s_taskId = request.getParameter("task_id");
		long taskId = Long.parseLong(s_taskId);
		boolean paymentok = Boolean.parseBoolean(request
				.getParameter("paymentok"));
		Money money = null;

		if (paymentok) {
			String s_moneyAmount = request.getParameter("money_amount");
			double moneyAmount = Double.parseDouble(s_moneyAmount);
			String moneyState = request.getParameter("money_state");
			String moneyType = request.getParameter("money_type");
			String moneyBank = request.getParameter("money_bank");
			String moneyName = request.getParameter("money_name");
			String moneyNumber = request.getParameter("money_number");
			String moneyRemark = request.getParameter("money_remark");

			if (!(moneyAmount < 0) && (moneyState != null)
					&& (moneyType != null)) {
				money = new Money();
				money.setOrderId(orderId_request);
				money.setMoneyAmount(moneyAmount);
				money.setMoneyState(moneyState);
				money.setMoneyType(moneyType);
				money.setMoneyBank(moneyBank);
				money.setMoneyName(moneyName);
				money.setMoneyNumber(moneyNumber);
				money.setMoneyRemark(moneyRemark);
			}
		}
		String actorId = FinanceServiceImpl.ACTOR_FINANCE_MANAGER;
		financeService.confirmFinalPaymentSubmit(actorId, taskId, paymentok,
				money);
		return "forward:/finance/confirmFinalPaymentList.do";
	}
}
