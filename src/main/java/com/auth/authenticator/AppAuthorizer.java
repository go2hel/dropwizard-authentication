package com.auth.authenticator;

import com.auth.resources.User;
import io.dropwizard.auth.Authorizer;

public class AppAuthorizer implements Authorizer<User> {
    @Override
    public boolean authorize(User user, String role) {
        return user.getRole()!=null && user.getRole().equals(role);
    }
}
