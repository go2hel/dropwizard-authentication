# Dropwizard- Authentication

In this project we have two roles, admin and user. Functionality of both are described as below,

* User
  * View his/her details
  * set password
* Admin
  * add/remove user
  * view all users
  * get details of any user
  * Can do anything user can do



In this project basic authentication is added. Benefit of authentication is that user cannot view other user's data or update other user's password. User will not be able to access admin's functionality. As admin is also a user he can access user's functionality.



For this authentication, code for authorizer and authenticator is written in com.auth.authenticator package. Following line of code is written to register the authenticator and role based access.



```java
environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                                        .setAuthenticator(new AppBasicAuthenticator())
                                        .setAuthorizer(new AppAuthorizer())
                                        .setRealm("BASIC_AUTH_REALM")
                                        .buildAuthFilter()));
environment.jersey().register(RolesAllowedDynamicFeature.class);
```



For user's functionality we have used @Auth from dropwizard-auth. For this to work we have added following line of code,



```java
environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
```



> The class we are adding to authentication should be implementing Principle Interface.



# Requirements

1. Make table in MySQL consisting of 4 columns {id,name,password,role\}​.
2. Edit the config.properties file according to your database.
3. Add one entry with role of admin in table.

How to start the application
---

1. Update the path in DBUtil.java according to your system.
1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/auth-1.1.jar server config.yml`
1. To check that your application is running enter url according to API in com.auth.client.