package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.service.UserService;
import com.coachqa.ws.util.WSUtil;
import notification.entity.NotificationPreference;
import notification.enums.NotificationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/signup")
public class RegsitrationResource {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping
    public AppUser registration(@RequestBody AppUser user, HttpServletRequest request, HttpServletResponse response)
    {
		/*
		 * Steps:
		 * 1. register the user
		 */
        AppUser newUser = userService.addUser(user);
        // todo save the user's notification preference
        NotificationPreference preference = new NotificationPreference(newUser.getAppUserId(), NotificationTypeEnum.APP);
//		If the preference is not set then notification system will use the defualt pref type.
//		userService.addOrUpdateUserNotificationPreference(preference);
        WSUtil.setLocationHeader(request, response, newUser.getAppUserId());

        return newUser;
    }
}
