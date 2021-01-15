package ci.ashamaz.sweater.controller

import ci.ashamaz.sweater.model.Role
import ci.ashamaz.sweater.repository.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/user")
open class UserController {

    @Autowired
    val userRepo: UserRepo? = null

    @GetMapping
    fun userList(model: Model): String {
        model.addAttribute("users", userRepo?.findAll())

        return "userList"
    }
    @GetMapping("/{user}")
    fun userEditForm(@PathVariable user: Long, model: Model): String {
        val one = userRepo?.getOne(user)
        model.addAttribute("user", one)
        model.addAttribute("roles", Role.values())
        return "userEdit"
    }

    @PostMapping
    fun saveUser(
            @RequestParam username: String,
            @RequestParam form: HashMap<String, String>,
            @RequestParam("userId") userId: Long

    ):String{
        userRepo?.getOne(userId).let {
            it?.username = username
            val roles: Set<String> = Role.values().map(Role::name).toSet()
            it?.roles?.clear()
            for (key in form.keys) {
                if (key in roles) {
                    it?.roles?.add(Role.valueOf(key))
                }
            }
            userRepo?.save(it!!)
            return "redirect:/user"
        }

    }
}