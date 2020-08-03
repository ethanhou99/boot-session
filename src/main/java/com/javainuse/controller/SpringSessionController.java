package com.javainuse.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.javainuse.service.SessionService;
import com.javainuse.session.Session;
import com.javainuse.session.SessionManager;

/**
 * @ClassName: SpringSessionController
 * @Description: Spring Boot Session Controller
 * @author Yicun Hou
 */
@Controller
public class SpringSessionController {
	@Autowired
	SessionService sessionService; 

	/**
	 * @Name: home
	 * @Description: Home page of controller
	 * @author Yicun Hou
	 */
	@GetMapping("/")
	public String home(Model model) {
		SessionManager.getInstance();
		List<Session> sessionList = SessionManager.getSessions();
		if (sessionList == null) {
			sessionList = new ArrayList<>();
		}
		model.addAttribute("sessionList", sessionList);
		return "index";
	}

	/**
	 * @Name: startSession
	 * @Description: receive post to start sessions
	 * @author Yicun Hou
	 */
	@PostMapping("/start")
	public String startSession(HttpServletRequest request) {
		String inputN = request.getParameter("num").replaceAll("[^\\d.]", "");
		String inputT = request.getParameter("time").replaceAll("[^\\d.]", "");
		Integer nums = Integer.valueOf(inputN);
		Integer time = 0;
		if (inputT != null && !inputT.isEmpty()) {
			time = Integer.valueOf(inputT);
		} else {
			time = 1;
		}
		sessionService.startSession(nums, time);
		try {
			Thread.sleep((nums + 1) * 1000);
		} catch (InterruptedException e) {
			//Do nothing
		}
		return "redirect:/";
	}
	
	/**
	 * @Name: stopSession
	 * @Description: receive post to stop sessions or stop program gracefully
	 * @author Yicun Hou
	 */
	@PostMapping("/stop")
    public String stopSession(HttpServletRequest request){
		String input = request.getParameter("id");
		if (input == "") {
			sessionService.stopAll();
		} else {
			Integer id = Integer.valueOf(input.replaceAll("[^\\d.]", ""));
	        sessionService.stopSession(id);
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				//Do nothing
			}
		}
        return "redirect:/";
    }
	
	/**
	 * @Name: adjustSession
	 * @Description: receive post to adjust session life cycle
	 * @author Yicun Hou
	 */
	@PostMapping("/adjust")
    public String adjustSession(HttpServletRequest request){
		String inputID = request.getParameter("id");
		String inputMin = request.getParameter("min");
		Integer id = Integer.valueOf(inputID.replaceAll("[^\\d.]", ""));
		Integer min = Integer.valueOf(inputMin);
        sessionService.adjustSession(id, min);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			//Do nothing
		}
        return "redirect:/";
    }
}