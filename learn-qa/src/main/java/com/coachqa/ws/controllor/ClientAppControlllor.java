package com.coachqa.ws.controllor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by a.nigam on 12/02/16.
 */
@RestController("/api/client")
public class ClientAppControlllor {


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String token){

        return "success";
    }



}