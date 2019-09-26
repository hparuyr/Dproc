package am.dproc.sms.controllers;

import java.lang.reflect.Field;
import java.security.Principal;
import java.time.LocalDateTime;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import am.dproc.sms.models.Student;
import am.dproc.sms.services.interfaces.StudentService;

@Controller
//@RequestMapping(value = "my")
public class LoginController {

	@Autowired
	StudentService studentService;
	
	@RequestMapping("/")
	public String redir(Principal user) {
		System.out.println("========="+currentTime()+">>>>>>>>>>>>>>>>>>>>>>>inside redir");
		
		if(user != null) {
			return "welcome";
		}
		return "redirect:/login";
	}
	
	@GetMapping("home")
	public String home() {
		System.out.println("=========>>"+currentTime()+">>>>>>>>>>>>>>>>>>>>>inside home");
		return "/home";
	}

//	@RequestMapping(value="login", method = RequestMethod.POST)
//	public String login() {
//		System.out.println("=========>>"+currentTime()+">>>>>>>>>>>>>>>>inside post login");
////		System.out.println("username: "+user.getUsername());
////		System.out.println("password: "+user.getPassword());
//		return "/login";
//	}

//	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authenticate(@RequestParam("username") String username, @RequestParam("password") String password) {
//		public String authenticate(User user) {
		System.out.println("=========>>"+currentTime()+">>>>>>>>>>>>>>>>inside authenticate");
//		System.out.println("username: "+user.getUsername());
//		System.out.println("password: "+user.getPassword());
		System.out.println("username: "+username);
		System.out.println("password: "+password);
		return "welcome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("welcome")
	public String welcome() {
		System.out.println("========="+currentTime()+">>>>>>>>>>>>>>>>>>>>>>>inside welcome");
		return "/welcome";
	}

	@GetMapping(value = "registration")
	public String registration(Model model) {
		System.out.println("========="+currentTime()+">>>>>>>>>>>>>>>>>>>>>>>inside registration");
		model.addAttribute("user", new Student());
		model.addAttribute("bdate", "01-01-1990");
		return "/registration";
	}

	@PostMapping(value = "registration")
	public String register(@ModelAttribute Student student, @RequestParam("bdate") String bdate, Model model) {
		System.out.println("this is birth date "+ bdate);
		if(studentService.addStudent(student)>0) {
			return "redirect:/login";
		}
		return registration(model);
	}
		
	@RequestMapping(value = "/testprocess")
	public String processLogin() {
		System.out.println("-=-=============>testprocess works");
		return ("<h1>Welcome to Processing login</h1>");
	}

	
	@RequestMapping(value = "user")
	@ResponseBody
	public String user() {
		return ("<h1>Welcome User</h1>");
	}

	@RequestMapping(value = "admin")
	@ResponseBody
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
