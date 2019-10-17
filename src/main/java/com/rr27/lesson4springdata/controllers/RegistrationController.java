package com.rr27.lesson4springdata.controllers;

import com.rr27.lesson4springdata.entities.User;
import com.rr27.lesson4springdata.services.UserService;
import com.rr27.lesson4springdata.utils.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //дополнительный проверщик, убирающий пробелы из строки и проверяющий "сухой" остаток на длину
    // ______ - это не notNul, НО длина тоже не равна 6 (шесть пробелов)
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String showMyLoginPage(Model model){
        model.addAttribute("systemUser", new SystemUser());
        return "registration-form";
    }

    //Аннотация @Valid - соверашет проверку пришедшей к нам информации
    //сразу после тог, что проверяем надо писать BindingResult, ничего не прописывая между ними, иначе работать не будет
    //в BindingResult улетают все ошибки допущенные при заполнении полей
    @PostMapping("/process")
    public String processRegistrationForm(@Valid @ModelAttribute("systemUser") SystemUser systemUser, BindingResult bindingResult, Model model) {
        String username = systemUser.getUsername();
        //если допущены ошибки, то возвращаемся к форме регистрации по-новой
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }
        User existing = userService.findByUserName(username);
        //отлавливаем, что пользователь с таким логином уже есть
        if (existing != null) {
            // theSystemUser.setUserName(null);      //если не хотим ничего вбивать заново, кроме имени, то мы занулим только его
            model.addAttribute("systemUser", systemUser);
            model.addAttribute("registrationError", "Пользователь с таким логином уже существует");
            return "registration-form";
        }
        userService.save(systemUser);
        return "registration-confirmation";
    }

}
