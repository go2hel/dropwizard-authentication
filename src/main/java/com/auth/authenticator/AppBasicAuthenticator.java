package com.auth.authenticator;

import com.auth.db.UserDao;
import com.auth.resources.User;
import com.auth.resources.UserNotFoundException;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class AppBasicAuthenticator implements Authenticator<BasicCredentials, User> {

    @Override
    public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        try {
            User user = UserDao.getInstance().getDetails(basicCredentials.getUsername());

            if(basicCredentials.getPassword().equals(user.getPassword())){
                return Optional.of(user);
            }
        } catch (UserNotFoundException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }
}
