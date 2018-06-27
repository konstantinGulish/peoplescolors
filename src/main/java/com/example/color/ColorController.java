package com.example.color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class ColorController {

    @Autowired
    PersonRepository people;

    @Autowired
    ColorRepository colors;

    @RequestMapping("/")
    public String index(Model model)
    {

        model.addAttribute("colors",colors.findAll());
        model.addAttribute("people",people.findAll());
        return "index";
    }

    @RequestMapping("/addcolor")
    public String addColor(Model model)
    {
        model.addAttribute("aColor", new Color());
        model.addAttribute("people",people.findAll());
        return "color";
    }

    @RequestMapping("/savecolor")
    public String saveColor(@ModelAttribute("aColor") Color color, Model model)
    {
        colors.save(color);
        return "redirect:/";
    }

    @PostConstruct
    public void fillTables()
    {
        Person p = new Person();
        p.setMyName("John Smith");
        people.save(p);

        p = new Person();
        p.setMyName("Owen Richards");
        people.save(p);

        p= new Person();
        p.setMyName("Ama Baidoo");
        people.save(p);
    }
}
