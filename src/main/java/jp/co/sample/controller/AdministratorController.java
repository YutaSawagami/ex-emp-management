package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private AdministratorService admistService;
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	}
	
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm insertAdministratorForm) {
		//Formクラスをドメインに変換してserviceのinsertに渡す処理
		System.out.println(insertAdministratorForm.getName());
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(insertAdministratorForm, administrator);
		admistService.insert(administrator);
		return "redirect:/";
	}
	
	@RequestMapping("")
	public String toLogin() {
		return "administrator/login";
	}
	
	@RequestMapping("/login")
	public String login(LoginForm loginForm, Model model) {
		try {
		System.out.println(loginForm.getMailAddress());
		Administrator administrator = admistService.login(loginForm.getMailAddress(), loginForm.getPassword());
		System.out.println(administrator);
		session.setAttribute("administratorName", administrator.getName());
		System.out.println("成功");
		return "redirect:/employee/showList";
		}catch (EmptyResultDataAccessException e) {
			System.out.println("データがないです。");
			model.addAttribute("message", "メールアドレスかパスワードが間違ってます。");
			return "administrator/login";
		}
	}
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
}
