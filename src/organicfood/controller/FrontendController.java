package organicfood.controller;


import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dao.DiscountDao;
import organicfood.bean.Account;
import organicfood.bean.Mailer;
import organicfood.entity.KhachHang;
import organicfood.entity.KhuyenMai;
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
	
//	@RequestMapping("index")
//	public String showIndex(HttpServletRequest request, HttpSession session, ModelMap model) {
//		
//		Account user = (Account) session.getAttribute("user");
//		model.addAttribute("phoneNumber", user.getUsername());
//		return "frontend/index";
//	}
	private static String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
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
		session.setAttribute("cartNumber", 0);
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
			model.addAttribute("phoneError", "H??y nh???p s??? ??i???n tho???i!");
			check = false;
		}

		if(password.equals("")) {
			model.addAttribute("passwordError", "H??y nh???p m???t kh???u!");
			check = false;
		}

		if(check){
			//ma hoa mat khau
			MessageDigest digest = null;
			try {
				digest = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			
			//check exist account
			Session session = factory.getCurrentSession();
			
			String hql = "SELECT name FROM KhachHang WHERE phone = '"+phone+"' AND password = '"+bytesToHex(encodedhash)+"'";
			Query query = session.createQuery(hql);
			Integer result = query.getFirstResult();
			List<Object[]> list = query.list();
			
			if(list.size() != 0) {
				if(b == null) {
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
				}else {
					if(b.equals("on")) {
						Cookie ckUsername = new Cookie("username", a.getUsername());
						ckUsername.setMaxAge(3600);
						response.addCookie(ckUsername);
						
						Cookie ckPassword = new Cookie("password", a.getPassword());
						ckPassword.setMaxAge(3600);
						response.addCookie(ckPassword);
						System.out.print("checked");
					}else {
						System.out.print("not check");
					}
				}
				
				
				
				
				s.setAttribute("user", a);
				model.addAttribute("id", a.getUsername());
				return "redirect:/index.html";  
			}
			this.message = "S??? ??i???n tho???i ho???c m???t kh???u kh??ng ????ng !!!";
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
		session.removeAttribute("user");
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
		return "frontend/loginSignup";
		//return "redirect:/login.html";
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
			errors.rejectValue("name", "user", "Vui l??ng nh???p h??? t??n!");
		}
		if(user.getPhone().trim().isEmpty()) {
			check = false;
			errors.rejectValue("phone", "user", "Vui l??ng nh???p s??? ??i???n tho???i!");
		}
		if(user.getEmail().trim().isEmpty()) {
			check = false;
			errors.rejectValue("email", "user", "Vui l??ng nh???p email!");
		}
		if(user.getAddress().trim().isEmpty()) {
			check = false;
			errors.rejectValue("address", "user", "Vui l??ng nh???p ?????a ch???!");
		}
		if(user.getPassword().isEmpty()) {
			check = false;
			errors.rejectValue("password", "user", "Vui l??ng nh???p m???t kh???u!");
		}
		
		if(repassword.isEmpty()) {
			model.addAttribute("repass","X??c nh???n m???t kh???u");
			check = false;
		}
		else {
			if(!repassword.equals(user.getPassword())) {
				model.addAttribute("repass","M???t kh???u kh??ng ????ng");
				check = false;
			}
		}
		if(!verify) {
			model.addAttribute("capcha", "H??y nh???p capcha");
			check = false;
		}else {
			System.out.print("ok");
		}

		
		if(check) {
			boolean invalidUser = checkExistUser(user.getPhone());
			if(invalidUser) {
				errors.rejectValue("phone", "user", "S??? ??i???n tho???i ???? ???????c ????ng k??!");
			}
			else {
				MessageDigest digest = null;
				try {
					digest = MessageDigest.getInstance("SHA-256");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				byte[] encodedhash = digest.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
				user.setPassword( bytesToHex(encodedhash));
				System.out.print(user.getPassword());
				boolean n = addNewUser(user);
				if(n) {
					this.message = "????ng k?? th??nh c??ng";
					
				}else this.message = "????ng k?? kh??ng th??nh c??ng";
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
			String subject = "L???y l???i m???t kh???u ????ng nh???p";
			String body = "<h3>???????ng link thay ?????i m???t kh???u</h3>  http://localhost/OrganicFood/forgot-password/" + generatedString+".html <br> (Kh??ng chia s??? th??ng tin n??y ????? ?????m b???o an to??n cho t??i kho???n c???a b???n) <br> C???m ??n qu?? kh??ch h??ng ???? s??? d???ng d???ch v??? c???a ch??ng t??i!";
			
			boolean result = send(from, email, subject, body);
			if(result) {
//				this.message = "Email g???i th??nh c??ng";
				this.khachHang = client;
				return "redirect:/forgot-password/success.html";
			}
			else this.message = "X???y ra l???i h??? th???ng";
			
			model.addAttribute("message", this.message);
			System.out.print(this.message);
			return "redirect:/forgot-password/form.html";
		}else {
			if(lang == null || lang == "vi") {
				this.message = "S??? ??i???n tho???i ch??a ???????c ????ng k?? / Unregistered phone number";
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
			model.addAttribute("passworder", "Vui l??ng nh???p m???t kh???u!");
		}
		if(confirm_password.isEmpty()) {
			check = false;
			model.addAttribute("confirmpassworder", "Vui l??ng nh???p m???t kh???u!");
		}
		if(check) {
			if(password.equals(confirm_password)) {
				MessageDigest digest = null;
				try {
					digest = MessageDigest.getInstance("SHA-256");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
				
				this.khachHang.setPassword(bytesToHex(encodedhash));
				int result = updateUser(this.khachHang);
				if(result==1) {
					model.addAttribute("message", "Thay ?????i m???t kh???u th??nh c??ng!");
					this.code = null;
					this.khachHang = null;
					return "frontend/forgotPassword/final";
				}else {
					model.addAttribute("password", password);
					model.addAttribute("confirm_password", confirm_password);
					model.addAttribute("message", "C?? l???i h??? th???ng reset kh??ng th??nh c??ng!");
					return "frontend/forgotPassword/resetPass";
				}
			}else {
				model.addAttribute("confirmpassworder", "M???t kh???u kh??ng ????ng!");
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
	
	//Discount detail
	
	@RequestMapping(value="discount/{id}.html")
	public String discountDetails(ModelMap model, @PathVariable("id") String id){
		KhuyenMai discount = DiscountDao.getDiscount(factory, id);
		model.addAttribute("discount", discount);
		return "frontend/discount/discountDetails";
	}
}