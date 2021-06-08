package com.auth;

import com.auth.authenticator.AppAuthorizer;
import com.auth.authenticator.AppBasicAuthenticator;
import com.auth.client.AdminAPI;
import com.auth.client.UserAPI;
import com.auth.resources.User;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class authApplication extends Application<authConfiguration> {

    public static void main(final String[] args) throws Exception {
        new authApplication().run(args);
    }

    @Override
    public String getName() {
        return "auth";
    }

    @Override
    public void initialize(final Bootstrap<authConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final authConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application

        environment.jersey().register(new UserAPI());
        environment.jersey().register(new AdminAPI());

        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                                        .setAuthenticator(new AppBasicAuthenticator())
                                        .setAuthorizer(new AppAuthorizer())
                                        .setRealm("BASIC_AUTH_REALM")
                                        .buildAuthFilter()
        ));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }

}
