package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Tag;
import com.coachqa.service.TagService;
import com.coachqa.service.UserService;
import com.coachqa.service.impl.QuestionServiceImpl;
import com.coachqa.ws.util.WSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagControllor {

    private static final Integer PAGE_SIZE = 10;

    private static Logger LOGGER = LoggerFactory.getLogger(TagControllor.class);

    @Autowired
    TagService tagService;

    @Autowired
    UserService userService;

    /**
     * {"tagName":"java","tagDescription":"This is an OO programming language","noOfTaggedQuestions":0}
     *
     */
    @PostMapping
    @ResponseBody
    public Tag addTag(@RequestBody Tag tag, HttpServletRequest request, HttpServletResponse response){

        Principal principal = request.getUserPrincipal();
        String username = principal.getName();

        AppUser appUser =  WSUtil.getUser( userService);
        LOGGER.info("Tagservice {} ", tagService);
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


    @RequestMapping(value="/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getMatchingTagsByName(HttpServletRequest request, @RequestParam("q") String name){

        List<Tag> tags = tagService.findSimilarTags(name, PAGE_SIZE);
        return tags;
    }

    @RequestMapping(value="/{name}",method = RequestMethod.GET)
    @ResponseBody
    public Tag getTagByName(HttpServletRequest request, @PathVariable("name") String name){

        return tagService.getTagByName(name);
    }
}
