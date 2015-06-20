package com.coachqa.mvc;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;

/**
 * Created by anigam on 6/19/15.
 */
@Order(1)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
