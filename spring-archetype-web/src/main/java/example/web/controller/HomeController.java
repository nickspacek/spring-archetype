package example.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	/**
	 * If not logged in (principal == null), show the welcome/introduction/home
	 * view. Otherwise, once logged in should redirect to user's dashboard.
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping("/")
	public String home(Principal principal) {
		if (principal == null) {
			return "home";
		}

		return "redirect:/dashboard";
	}
}
