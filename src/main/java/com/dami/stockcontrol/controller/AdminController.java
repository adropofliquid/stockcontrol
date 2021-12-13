package com.dami.stockcontrol.controller;

import com.dami.stockcontrol.service.AdminRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;


@Controller
public class AdminController {

    @Autowired
    AdminRequestsService adminRequestsService;

    @GetMapping("/admin/upgrade/{id}")
    public String adminRequest(@PathVariable int id, Model model){

        adminRequestsService.makeNewRequest(id);
        return "redirect:/company";
    }

    @Transactional
    @GetMapping("/admin/request/accept/{id}")
    public String acceptRequest(@PathVariable int id, Model model){
        adminRequestsService.acceptRequest(id);
        return "redirect:/dashboard"; // TODO
    }


    @GetMapping("/admin/decline/accept/{id}")
    public String declineRequest(@PathVariable int id, Model model){
        adminRequestsService.declineRequest(id);
        return "redirect:/dashboard"; // TODO
    }

}