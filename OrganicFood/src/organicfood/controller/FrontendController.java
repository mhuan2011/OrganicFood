package organicfood.controller;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import organicfood.bean.Account;
import organicfood.bean.Mailer;
import organicfood.entity.KhachHang;
import organicfood.model.RecCaptchaResponse;
import organicfood.recaptcha.RecaptchaVerification;

@Controller
@Transactional
public class FrontendController {
	public static String message;
	private String code = "";
	private KhachHang khachHang = null;
	@Autowired
	SessionFactory factory;

	

	@RequestMapping("")
	public String index() {
		return "frontend/index";
	}
	
	@RequestMapping("index")
	public String showIndex(HttpServletRequest request, HttpSession session, ModelMap model) {
		
		Account user = (Account) session.getAttribute("user");
		model.addAttribute("phoneNumber", user.getUsername());
		return "frontend/index";
	}

	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showlogin(ModelMap model, HttpSession session, HttpServletRequest request) {
		Account account = checkCookie(request);
		if (account == null) {
			model.addAttribute("user", new Account());
			this.message = null;
			model.addAttribute("message", this.message);
			
		} else {
			model.addAttribute("user", account);
			model.addAttribute("phone", account.getUsername());
			model.addAttribute("password", account.getPassword());
			System.out.print(account.getUsername());
			
		}
		
		return "frontend/loginSignup";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginAccess(@ModelAttribute("user") Account a, HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession s) {
		String phone = a.getUsername();
		String password = a.getPassword();
		String b = request.getParameter("rememberMe");
		//validation 
		
		
		
		boolean check = true;
		if(phone.trim().equals("")) {
			model.addAttribute("phoneError", "Hãy nhập số điện thoại!");
			check = false;
		}

		if(password.equals("")) {
			model.addAttribute("passwordError", "Hãy nhập mật khẩu!");
			check = false;
		}

		if(check){
			//check exist account
			Session session = factory.getCurrentSession();
			String hql = "SELECT name FROM KhachHang WHERE phone = '"+phone+"' AND password = '"+password+"'";
			Query query = session.createQuery(hql);
			Integer result = query.getFirstResult();
			List<Object[]> list = query.list();
			
			if(list.size() != 0) {
				if(b=="on") {
					Cookie ckUsername = new Cookie("username", a.getUsername());
					ckUsername.setMaxAge(3600);
					response.addCookie(ckUsername);
					Cookie ckPassword = new Cookie("password", a.getPassword());
					ckPassword.setMaxAge(3600);
					response.addCookie(ckPassword);
				}else {
					
				}
				
				
				
				s.setAttribute("user", a);
				return "redirect:/index.html";  
			}
			this.message = "Số điện thoại hoặc mật khẩu không đúng !!!";
			model.addAttribute("message", this.message);
//			if(result != null) {
//				return "redirect:/index.html"; 
//			}
			
			
		}
		model.addAttribute("phone", a.getUsername());
		model.addAttribute("password", a.getPassword());
		
		return "frontend/loginSignup";
		//return "redirect:/login.html";
		
	}
//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	public String login(@ModelAttribute(value = "account") Account account, ModelMap modelMap, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
//		AccountModel accountModel = new AccountModel();
//		if (accountModel.login(account.getUsername(), account.getPassword())) {
//			session.setAttribute("username", account.getUsername());
//			if (request.getParameter("remember") != null) {
//				Cookie ckUsername = new Cookie("username", account.getUsername());
//				ckUsername.setMaxAge(3600);
//				response.addCookie(ckUsername);
//				Cookie ckPassword = new Cookie("password", account.getPassword());
//				ckPassword.setMaxAge(3600);
//				response.addCookie(ckPassword);
//			}
//			return "account/welcome";
//		} else {
//			modelMap.put("error", "Account's Invalid");
//			return "account/index";
//		}
//	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		// Remove session
		session.removeAttribute("username");
		// Remove cookie
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equalsIgnoreCase("username")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			if (cookie.getName().equalsIgnoreCase("password")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		return "redirect:/account/login";
	}
	
	public Account checkCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		
		Account account = null;
		String username = "", password = "";
		if(cookies==null) return account;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase("username")) {
				username = cookie.getValue();
			}
			if (cookie.getName().equalsIgnoreCase("password")) {
				password = cookie.getValue();
			}
		}
		if (!username.isEmpty() && !password.isEmpty()) {
			account = new Account(username, password);
		}
		return account;
	}
	//signup
	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String showSignUp(ModelMap model, HttpSession session, HttpServletRequest request) {
		
		model.addAttribute("user", new KhachHang());
		return "frontend/signup";
	}
	@RequestMapping(value = "create-account", method = RequestMethod.POST)
	public String createAccount(ModelMap model, HttpSession session, HttpServletRequest request, @ModelAttribute("user") KhachHang user, BindingResult errors) throws IOException {
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verify = RecaptchaVerification.verify(gRecaptchaResponse);
		
		
		
		String repassword = request.getParameter("repassword");
		user.setPhone(request.getParameter("phone"));
		model.addAttribute("phone", request.getParameter("phone"));
		boolean check = true;
		if(user.getName().trim().isEmpty()) {
			check = false;
			errors.rejectValue("name", "user", "Vui lòng nhập họ tên!");
		}
		if(user.getPhone().trim().isEmpty()) {
			check = false;
			errors.rejectValue("phone", "user", "Vui lòng nhập số điện thoại!");
		}
		if(user.getEmail().trim().isEmpty()) {
			check = false;
			errors.rejectValue("email", "user", "Vui lòng nhập email!");
		}
		if(user.getAddress().trim().isEmpty()) {
			check = false;
			errors.rejectValue("address", "user", "Vui lòng nhập địa chỉ!");
		}
		if(user.getPassword().isEmpty()) {
			check = false;
			errors.rejectValue("password", "user", "Vui lòng nhập mật khẩu!");
		}
		
		if(repassword.isEmpty()) {
			model.addAttribute("repass","Xác nhận mật khẩu");
			check = false;
		}
		else {
			if(!repassword.equals(user.getPassword())) {
				model.addAttribute("repass","Mật khẩu không đúng");
				check = false;
			}
		}
		if(!verify) {
			model.addAttribute("capcha", "Hãy nhập capcha");
			check = false;
		}else {
			System.out.print("ok");
		}
		
		if(check) {
			boolean invalidUser = checkExistUser(user.getPhone());
			if(invalidUser) {
				errors.rejectValue("phone", "user", "Số điện thoại đã được đăng ký!");
			}
			else {
				boolean n = addNewUser(user);
				if(true) {
					this.message = "Đăng ký thành công";
					
				}else this.message = "Đăng ký không thành công";
				model.addAttribute("message", this.message);
			}
		}
		model.addAttribute("user", user);
		return "frontend/signup";
	}
	//add user
	private boolean addNewUser(KhachHang user) {
		Session session = factory.openSession();
		try {	
			Transaction t = session.beginTransaction(); 
			session.save(user);
			t.commit();
			return true;
		} catch (Exception e) {
			session.close();
		}
		return false;
	}
	//check exits user 
	private boolean checkExistUser(String phoneNumber) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhachHang WHERE phone = '"+phoneNumber+"'";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		
		if(list.size() != 0) {
			return true;
		}
		return false;
	}
	//forgot password
	@RequestMapping("forgot-password/form")
	public String forgotPasswordForm() {
		return "frontend/forgotPassword/form";
	}
	
	
	@RequestMapping(value="forgot-password/send-email-verify", method = RequestMethod.POST)
	public String  forgotPassword(ModelMap model, @RequestParam("phone") String phone, HttpServletRequest request) {
		KhachHang client = getUser(phone);
		String lang = request.getParameter("language");
		if(client != null) {
			//gui email cho khach hang link thay doi mat khau
			
			int leftLimit = 48; // letter 'a'
		    int rightLimit = 57; // letter 'z'
		    int targetStringLength = 20;
		    Random random = new Random();

		    String generatedString = random.ints(leftLimit, rightLimit + 1)
		      .limit(targetStringLength)
		      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		      .toString();

		    this.code = generatedString;
			String from = "phongthietbiptit@gmail.com";
		    String email = client.getEmail();
			String subject = "Lấy lại mật khẩu đăng nhập";
			String body = "http://localhost/OrganicFood/forgot-password/" + generatedString+".html";
			
			boolean result = send(from, email, subject, body);
			if(result) {
//				this.message = "Email gửi thành công";
				this.khachHang = client;
				return "redirect:/forgot-password/success.html";
			}
			else this.message = "Xảy ra lỗi hệ thống";
			
			model.addAttribute("message", this.message);
			System.out.print(this.message);
			return "redirect:/forgot-password/form.html";
		}else {
			if(lang == null || lang == "vi") {
				this.message = "Số điện thoại chưa được đăng ký / Unregistered phone number";
			}else {
				this.message = "Unregistered phone number";
			}
			
			
		}
		model.addAttribute("phone", phone);
		
		model.addAttribute("message", this.message);
		String referer = request.getHeader("Referer");
		//return "frontend/forgotPassword/form";
		
		return "redirect:" + referer;
	}
	@RequestMapping(value="forgot-password/{code}.html", method = RequestMethod.GET)
	public String verify(@PathVariable("code") String codeClient) {
		System.out.println(this.code);
		System.out.print(codeClient);
		
		if(codeClient.equals(this.code)) {
			return "frontend/forgotPassword/resetPass";
		}else {
			return "error404";
		}
		
	}
	
	
	private KhachHang getUser(String phoneNumber) {
		KhachHang kh = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM KhachHang WHERE phone = '"+phoneNumber+"'";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		if(list.size() != 0){
			kh = (KhachHang) query.list().get(0);
		}
		
		return kh;
	}
	
	//send email
	@Autowired
	Mailer mailer;
	
	public boolean send( String from, String to,String subject, String body ) {
		try {
			mailer.send(from, to, subject, body);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	//success
	@RequestMapping(value="forgot-password/success.html", method = RequestMethod.GET)
	public String successChange(ModelMap model) {
		model.addAttribute("name", this.khachHang.getName());
		return "frontend/forgotPassword/success";
		
	}
	
	@RequestMapping("forgot-password/reset.html")
	public String resetPassword(ModelMap model, HttpServletRequest request) {
		if(khachHang==null) return "error404";
		
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm-password");
		boolean check = true;
		if(password.isEmpty()) {
			check = false;
			model.addAttribute("passworder", "Vui lòng nhập mật khẩu!");
		}
		if(confirm_password.isEmpty()) {
			check = false;
			model.addAttribute("confirmpassworder", "Vui lòng nhập mật khẩu!");
		}
		if(check) {
			if(password.equals(confirm_password)) {
				this.khachHang.setPassword(password);
				int result = updateUser(this.khachHang);
				if(result==1) {
					model.addAttribute("message", "Thay đổi mật khẩu thành công!");
					this.code = null;
					this.khachHang = null;
					return "frontend/forgotPassword/final";
				}else {
					model.addAttribute("password", password);
					model.addAttribute("confirm_password", confirm_password);
					model.addAttribute("message", "Có lỗi hệ thống reset không thành công!");
					return "frontend/forgotPassword/resetPass";
				}
			}else {
				model.addAttribute("confirmpassworder", "Mật khẩu không đúng!");
				model.addAttribute("password", password);
				model.addAttribute("confirm_password", confirm_password);
				return "frontend/forgotPassword/resetPass";
			}
		}
		
		return "";
	}
	public Integer updateUser(KhachHang user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(user);
			t.commit();
			return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();	
		} finally {
			session.close();
		}
		return 0;
	}
}
