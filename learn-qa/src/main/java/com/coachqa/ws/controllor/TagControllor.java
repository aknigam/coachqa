package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Tag;
import com.coachqa.service.TagService;
import com.coachqa.service.UserService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagControllor {

    @Autowired
    TagService tagService;

    @Autowired
    UserService userService;

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter();
        Writer w = new StringWriter();
        Tag tag = new Tag(1, "java", "This is an OO programming language", 0);
        mapper.writeValue(w, tag);
        System.out.println(w.toString());

        Tag tg = mapper.readValue(w.toString(), Tag.class);
        System.out.println(tg);
    }

    /**
     * {"tagName":"java","tagDescription":"This is an OO programming language","noOfTaggedQuestions":0}
     *
     */
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public Integer addTag(@RequestBody Tag tag, HttpServletRequest request){

        Principal principal = request.getUserPrincipal();
        String username = principal.getName();

        AppUser appUser = userService.getUserByEmail(username);

        return tagService.addTag(appUser.getAppUserId(), tag);
    }


    @RequestMapping(value="/get/match",method = RequestMethod.GET)
    public List<Tag> getMatchingTagsByName(HttpServletRequest request, @RequestParam("q") String name){

        return tagService.findSimilarTags(name, 6);
    }

    @RequestMapping(value="/get",method = RequestMethod.GET)
    public Tag getTagByName(HttpServletRequest request, @RequestParam("q") String name){

        return tagService.getTagByName(name);
    }
}
