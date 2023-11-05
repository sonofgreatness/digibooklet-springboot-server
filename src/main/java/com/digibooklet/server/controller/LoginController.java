package com.digibooklet.server.controller;

import com.digibooklet.server.service_layer.BackupService;
import com.digibooklet.server.service_layer.TransactionService;
import com.digibooklet.server.service_layer.UserManagementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@AllArgsConstructor
public class LoginController {

    @Autowired
    private final BackupService backupService;
    private  final UserManagementService userManagementService;
    private  final TransactionService transactionService;
    @RequestMapping("/login")
    public String loginPage(){
        return  "auth-login";
    }





    @RequestMapping("/home")
    public ModelAndView loginSubmit()
    throws  Exception{

        ModelAndView mav = new ModelAndView("/pages/landing_page");
        mav.addObject("backups", backupService.getAllBackUps().size());
        mav.addObject("users", userManagementService.getAllUsers().size());
        mav.addObject("transactions",transactionService.getAllTransactions().size());
        mav.addObject("user_stats",userManagementService.getUserStats());

        return  mav;
    }
}
