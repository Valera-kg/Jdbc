package com.example.JdbcAPI.contoller;

import com.example.JdbcAPI.dao.UserDAO;
import com.example.JdbcAPI.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserDAO userDAO;

//----------create--------------------
    @GetMapping("/addUser")
    public String newPersonForm() {
        return "createUser";
    }
    @PostMapping("/addUser")
    public String resultPage(User user) {
        userDAO.create(user);
        return "redirect:/users";
    }

//--------read All users by list------
    @GetMapping("/users")
    public String readAllUsers(Model model) {
        model.addAttribute("personList", userDAO.readAll());
        return "userList";
    }
//--------read One user by Id----------
    @GetMapping("/user/{id}")
    public String readUserById(User user, Model model) {
        model.addAttribute("personById", userDAO.readById(user));
        return "readById";
    }

//-------------update-----------------
    @GetMapping("/update/{id}")
    public String editForm(User user, Model model) {
        model.addAttribute("personEdit", userDAO.readById(user));
        return "editUser";
    }
    @PostMapping("/updateUser")
    public String updatedUser(User user) {
        userDAO.update(user);
        return "redirect:/users";
    }

//---------delete by id----------------
    @GetMapping("/delete/{id}")
    public String deleteForm(User user) {
        userDAO.deleteById(user);
        return "redirect:/users";
    }

//------------delete All users----------
    @GetMapping("/deleteAll")
    public String deleteAllForm() {
        userDAO.deleteAll();
        return "redirect:/users";
    }
}
