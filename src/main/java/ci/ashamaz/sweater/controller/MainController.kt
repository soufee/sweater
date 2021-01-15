package ci.ashamaz.sweater.controller

import ci.ashamaz.sweater.model.Message
import ci.ashamaz.sweater.model.User
import ci.ashamaz.sweater.repository.MessageRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MainController {
    @Autowired
    val messageRepo: MessageRepo? = null

    @GetMapping("/")
    fun greeting(
            model: HashMap<String, Any>
    ): String {
        return "greeting"
    }

    @GetMapping("/main")
    fun main(
            @RequestParam(required = false, defaultValue = "") filter: String,
            model: Model
    ): String {
        var filteredList: Iterable<Message>?

        if (!filter.isEmpty()) {
            filteredList = messageRepo?.findByTag(filter)
        } else {
            filteredList = messageRepo?.findAll()
        }
        model.addAttribute("messages", filteredList)
        model.addAttribute("filter", filter)
        return "main"
    }

    @PostMapping("/main")
    fun saveMessage(
            model: HashMap<String, Any?>,

            @AuthenticationPrincipal user: User?,
            @RequestParam text: String?,
            @RequestParam tag: String?
    ): String {
        val message = Message(text = text, tag = tag, author = user)
        messageRepo?.save(message)
        model["messages"] = messageRepo?.findAll()
        return "main"
    }

    @PostMapping("filter")
    fun filter(model: HashMap<String, Any?>): String {

        return "main"
    }
}