package zhedron.comments.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zhedron.comments.models.User;
import zhedron.comments.services.UserService;

@Controller
@RequestMapping ("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public String findAll (Model model) {
        model.addAttribute("user", service.findAll());

        return "users";
    }

    @GetMapping("/registration")
    public String newUser (@ModelAttribute ("user") User user) {
        return "registration";
    }

    @PostMapping ()
    public String registration (@ModelAttribute ("user") User user) {
        service.save(user);
        return "redirect:/users";
    }

    @GetMapping ("/{id}")
    public String findUser (@PathVariable int id, Model model) {
        model.addAttribute("user", service.findUser(id));

        return "user";
    }

    @PostMapping("/{id}")
    public String addComment (@PathVariable int id, @ModelAttribute ("user") User user) {
        User users = service.findUser(id);

        String currentComments = users.getComments();

        String newComments = user.getText();

        if (currentComments != null) {
            currentComments += "\n" + users.getName() + ": " + newComments;
        } else {
            currentComments = newComments;
        }

        System.out.println("\n" + users.getName() + ": " + newComments);

        users.setComments(currentComments);
        users.setText(user.getText());
        service.save(users);
        return "redirect:/users/{id}";
    }
}
