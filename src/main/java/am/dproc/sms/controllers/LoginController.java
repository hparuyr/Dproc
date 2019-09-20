package am.dproc.sms.controllers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import am.dproc.sms.models.Student;

@Controller
//@RequestMapping(value = "my")
public class LoginController {

	@RequestMapping("/")
	public String redir() {
		System.out.println("========="+currentTime()+">>>>>>>>>>>>>>>>>>>>>>>inside redir");
		return "redirect:/login";
	}
	
	@GetMapping("home")
	public String home() {
		System.out.println("=========>>"+currentTime()+">>>>>>>>>>>>>>>>>>>>>inside home");
		return "/home";
	}

	@GetMapping("login")
	public String login() {
		logTime();
		System.out.println("=========>>"+currentTime()+">>>>>>>>>>>>>>>>inside login");
		return "/login";
	}
	
	@GetMapping("welcome")
	public String welcome() {
		System.out.println("========="+currentTime()+">>>>>>>>>>>>>>>>>>>>>>>inside welcome");
		return "/welcome";
	}


	@RequestMapping(value = "registration")
	public String registration(Model model) {
		System.out.println("========="+currentTime()+">>>>>>>>>>>>>>>>>>>>>>>inside registration");
		model.addAttribute("user", new Student());
		return "/registration";
	}

	@RequestMapping(value = "user")
	public String user() {
		return ("<h1>Welcome User</h1>");
	}

	@RequestMapping(value = "admin")
	public String admin() {
		return ("<h1>Welcome Admin</h1>");
	}

	// todo delete time utils
	private void logTime() {
		System.out.println(currentTime());;
	}
	
	private String currentTime() {
		return LocalDateTime.now().toString().replace("T", " ");
	}
	
}
