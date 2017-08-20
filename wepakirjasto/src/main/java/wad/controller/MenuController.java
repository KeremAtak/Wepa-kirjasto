package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wad.domain.Person;
import wad.repository.PersonRepository;

@Controller
@RequestMapping("/")
public class MenuController {
    
    @Autowired
    private PersonRepository personRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewMenu(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            model.addAttribute("person", personRepository.findByUsername(authentication.getName()));
        }
        return "index";
    }
}
