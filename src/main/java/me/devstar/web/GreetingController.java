package me.devstar.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

	public static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
	
	@GetMapping({ "", "index", "/index" })
	public String greeting(Model model) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
		
		model.addAttribute("today", strToday);
		return "index";
	}
}
