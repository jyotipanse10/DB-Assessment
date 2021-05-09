package com.trade;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TradeController {
	@RequestMapping("trade")
	public String trade() {
		System.out.println("trading.......");
		return "trade.jsp";
	}
	/*
	 * public String add(HttpServletRequest req) { System.out.println("cdt"); String
	 * tradeId= req.getParameter("tradeId"); System.out.println("vjefwegqu   "+
	 * tradeId); return "add.jsp"; }
	 */
	@RequestMapping("add")
	public ModelAndView add(Trade trade) {
		List<Trade> list = TradeService.validateNewTrade(trade);
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj",list);
		System.out.println(list);
		mv.setViewName("add.jsp");
		//Map<String,Object> map = mv.getModel();
		
		return mv;
	}
	
	
	
	@RequestMapping("show")
	public void show() {
		
	}
}
