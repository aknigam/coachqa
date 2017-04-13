package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Tag;
import com.coachqa.service.TagService;
import com.coachqa.service.UserService;
import com.coachqa.ws.util.WSUtil;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagControllor {

    private static final Integer PAGE_SIZE = 10;
    @Autowired
    TagService tagService;

    @Autowired
    UserService userService;

    /**
     * {"tagName":"java","tagDescription":"This is an OO programming language","noOfTaggedQuestions":0}
     *
     */
    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public Tag addTag(@RequestBody Tag tag, HttpServletRequest request, HttpServletResponse response){

        Principal principal = request.getUserPrincipal();
        String username = principal.getName();

        AppUser appUser =  WSUtil.getUser( userService);
        tag =  tagService.addTag(appUser.getAppUserId(), tag);
        WSUtil.setLocationHeader(request, response, tag.getTagId());

        /*
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(tag.getTagId()).toUri());
        return new ResponseEntity<>(tag, httpHeaders, HttpStatus.CREATED);
        */
        return tag;



    }


    @RequestMapping(value="/get/match",method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getMatchingTagsByName(HttpServletRequest request, @RequestParam("q") String name){

        List<Tag> tags = tagService.findSimilarTags(name, PAGE_SIZE);
        return tags;
    }

    @RequestMapping(value="/get",method = RequestMethod.GET)
    @ResponseBody
    public Tag getTagByName(HttpServletRequest request, @RequestParam("q") String name){

        return tagService.getTagByName(name);
    }
}
