package com.coachqa.ws.controllor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by a.nigam on 12/02/16.
 */
@RestController
@RequestMapping("/api/client")
public class ClientAppControlllor {


    @GetMapping(value= "/register")
    public String register(String token){

        return "success";
    }



}
