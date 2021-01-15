package ci.ashamaz.sweater.controller

import ci.ashamaz.sweater.model.Role
import ci.ashamaz.sweater.model.User
import ci.ashamaz.sweater.repository.UserRepo
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import java.util.*

@Controller
class RegistrationController {
    @Autowired
    val userRepo: UserRepo? = null
    val logger  = KotlinLogging.logger {  }

    @GetMapping("/registration")
    fun registration(): String {
        return "registration"
    }

    @PostMapping("/registration")
    fun addUser(user: User,  model: HashMap<String, Any>): String {
        logger.info("Регистрируем ${user.username}")
        val userFromDb = userRepo?.findByUsername(user.username)
        if (userFromDb!=null) {
            model["message"] = "User exists"
            return "registration"
        }

        user.active = true
        user.roles = Collections.singleton(Role.USER)
        userRepo?.save(user)
        return "redirect:/login"
    }
}