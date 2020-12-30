package ci.ashamaz.sweater.controller

import ci.ashamaz.sweater.model.Message
import ci.ashamaz.sweater.repository.MessageRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
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
            model: HashMap<String, Any?>
    ): String {
        model["messages"] = messageRepo?.findAll()
        return "main"
    }

    @PostMapping("/main")
    fun saveMessage(
            model: HashMap<String, Any?>,
            @RequestParam text: String?,
            @RequestParam tag: String?
    ): String {
        val message = Message(text = text, tag = tag)
        messageRepo?.save(message)
        model["messages"] = messageRepo?.findAll()
        return "main"
    }

    @PostMapping("filter")
    fun filter( model: HashMap<String, Any?>,
                @RequestParam filter: String?): String {
        var filteredList: List<Message>?
        if (!filter.isNullOrEmpty()) {
            filteredList = messageRepo?.findByTag(filter)
        } else {
            filteredList = messageRepo?.findAll()
        }
        model["messages"] = filteredList
        return "main"
    }
}