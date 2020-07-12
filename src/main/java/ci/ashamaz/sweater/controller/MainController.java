package ci.ashamaz.sweater.controller;

import ci.ashamaz.sweater.domain.Message;
import ci.ashamaz.sweater.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepository repository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        List<Message> all = repository.findAll();
        model.put("messages", all);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text,
                      @RequestParam String tag,
                      Map<String, Object> model) {
        Message message = new Message(text, tag);
        repository.save(message);
        List<Message> all = repository.findAll();
        model.put("messages", all);
        return "main";

    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter,
                         Map<String, Object> model) {
        List<Message> tags;
        if (filter==null || filter.isEmpty()) {
            tags   = repository.findAll();
        } else {
            tags = repository.findByTag(filter);
        }
        model.put("messages", tags);
        return "main";

    }
}
