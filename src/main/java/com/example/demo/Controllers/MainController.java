package com.example.demo.Controllers;



import com.example.demo.model.Performer;
import com.example.demo.model.Users;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.EventService;
import com.example.demo.services.LoginService;
import com.example.demo.services.PerformerService;
import com.example.demo.valid.AppUserForm;
import com.example.demo.valid.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {


	private PerformerService performerService;
	private LoginService loginService;
	private EventService eventService;
	private UserRepository partRepository;

	@Autowired
	private UserValidator userValidator;

    public MainController(PerformerService performerService, LoginService loginService, EventService eventService, UserRepository partRepository) {
        super();
        this.performerService = performerService;
        this.loginService = loginService;
        this.eventService = eventService;
        this.partRepository = partRepository;
    }



	@RequestMapping(value = "/logoutSuccesful", method = RequestMethod.GET)
	public String logout(Model model) {
		model.addAttribute("title", "Logout");
		return "login";
	}

	@RequestMapping({ "/start", "", "/","/home"})
	public String userInfo(Model model) {
		model.addAttribute("userInfo", loginService.getUserInfo());
		model.addAttribute("events", eventService.getUsersEvent());
		return "index";
	}

	@RequestMapping(value = "/addperf", method = RequestMethod.GET)
	public String addPerformer(Model model) {
		Performer perf = new Performer();
		model.addAttribute("performer", perf);
		return "addPerformer";

	}
	@RequestMapping(value = "/addperf", method = RequestMethod.POST)
	public String savePerformer( @ModelAttribute("performer") Performer performer, Model model, final RedirectAttributes redirectAttributes) {

		try {
			performerService.createNewPerformer(performer);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			return "addPerformer";
		}

		redirectAttributes.addFlashAttribute("performers", performerService.getPerformers());
		return "redirect:/home";
	}
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUsers(Model model) {

		model.addAttribute("users", partRepository.findAll());
		return "users";
	}

	@RequestMapping("/registerSuccessful")
	public String viewRegisterSuccessful(Model model) {

		return "registerSuccessful";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegister(Model model) {

		AppUserForm form = new AppUserForm();
		model.addAttribute("appUserForm", form);
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveRegister(Model model, @ModelAttribute("appUserForm") @Validated AppUserForm appUserForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {


		if (result.hasErrors()) {
			return "register";
		}
		Users newUser = null;
		try {
			newUser = loginService.createNewUser(appUserForm);
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			return "register";
		}

		redirectAttributes.addFlashAttribute("flashUser", newUser);

		return "redirect:/registerSuccessful";
	}

	@RequestMapping(value = "users/{id}/makeadmin", method = RequestMethod.GET)
	public String userToAdmin(@PathVariable String id, Model model) {
		loginService.makeAdmin(id);
		model.addAttribute("users", partRepository.findAll());
		return "/users";
	}

}
