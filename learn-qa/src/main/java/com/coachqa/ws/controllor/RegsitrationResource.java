package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.UserTypeEnum;
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


    /*

    This endpoint should be used by the users to register themselves. Only application_users can be added in this way
    To enforce the security we can issue a registration token to each user when the fee is paid.
    We can also have quota for a code.

     */
    @ResponseBody
    @PostMapping
    public AppUser registration(@RequestBody AppUser user, HttpServletRequest request, HttpServletResponse response)
    {


//        AppUser createdBy = WSUtil.getUser(userService);
		/*
		 * Steps:
		 * 1. register the user
		 */
		if(user.getUserType() != null && user.getUserType() != UserTypeEnum.application_user) {
		    throw new RuntimeException("Invalid request. Only application users can be registered");
        }
        user.setUserType(UserTypeEnum.application_user);
        AppUser newUser = userService.addUser(user);
        // todo save the user's notification preference
        NotificationPreference preference = new NotificationPreference(newUser.getAppUserId(), NotificationTypeEnum.APP);
//		If the preference is not set then notification system will use the defualt pref type.
//		userService.addOrUpdateUserNotificationPreference(preference);
        WSUtil.setLocationHeader(request, response, newUser.getAppUserId());

        return newUser;
    }
}
