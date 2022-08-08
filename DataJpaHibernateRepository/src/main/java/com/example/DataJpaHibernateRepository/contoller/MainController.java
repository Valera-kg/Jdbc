package com.example.DataJpaHibernateRepository.contoller;

import com.example.DataJpaHibernateRepository.domain.User;
import com.example.DataJpaHibernateRepository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

//----------index--------------------
    @GetMapping("/")
    public String showIndex() {
        return "index";
    }
//----------      --------------------

//----------create--------------------
    @GetMapping("/addUser")
    public String newPersonForm() {
        return "createUser";
    }
    @PostMapping("/addUser")
    public String resultPage(User user) {
        userService.create(user);
        return "redirect:/users";
    }

//--------show All users by list------
    @GetMapping("/users")
    public String readAllUsers(Model model) {
        model.addAttribute("personList", userService.readAll());
        return "userList";
    }
//--------show One user by Id----------
    @GetMapping("/user/{id}")
    public String readUserById(User user, Model model) {
        model.addAttribute("personById", userService.readById(user));
        return "readById";
    }

//-------------update-----------------
    @GetMapping("/update/{id}")
    public String editForm(User user, Model model) {
        model.addAttribute("personEdit", userService.readById(user));
        return "editUser";
    }
    @PostMapping("/updateUser")
    public String updatedUser(User user) {
        userService.update(user);
        return "redirect:/users";
    }

//---------delete by id----------------
    @GetMapping("/delete/{id}")
    public String deleteForm(User user) {
        userService.deleteById(user);
        return "redirect:/users";
    }

//------------delete All users----------
    @GetMapping("/deleteAll")
    public String deleteAllForm() {
        userService.deleteAll();
        return "redirect:/users";
    }
}
