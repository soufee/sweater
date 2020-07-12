package ci.ashamaz.sweater.controller;

import ci.ashamaz.sweater.domain.Role;
import ci.ashamaz.sweater.domain.User;
import ci.ashamaz.sweater.repository.UserRepository;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User u = userRepo.findByUsername(user.getUsername());
        if (u!=null) {
            model.put("message", "User exists!");
            return "registration";
        } else {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepo.save(user);
            return "redirect:/login";
        }

    }
}
