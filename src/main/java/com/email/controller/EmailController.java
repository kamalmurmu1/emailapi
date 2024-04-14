package com.email.controller;

import com.email.model.EmailRequest;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;
    @RequestMapping("/welcome")
    public String welcome(){
        return "This is my rest api";
    }

    //api to send mail
    @RequestMapping(value = "/sendmail",method = RequestMethod.POST)
    public ResponseEntity<?> sendEamil(@RequestBody EmailRequest request){
        boolean result = this.emailService.sendEmail(request.getSubject(),request.getMessage(),request.getTo());
        if(result){
            return  ResponseEntity.ok("Email is sent successfully");
        }
        else{
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent");
        }
    }
}
