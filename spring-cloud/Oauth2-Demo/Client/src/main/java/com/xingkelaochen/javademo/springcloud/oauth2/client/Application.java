package com.xingkelaochen.javademo.springcloud.oauth2.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

@SpringBootApplication
public class Application {

//	@Autowired
//	private OAuth2ClientContext oAuth2ClientContext;
//	
//	protected OAuth2ProtectedResourceDetails resource() {
//	    ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
//	    List scopes = new ArrayList<String>(2);
//	    scopes.add("trust");
//	    resource.setAccessTokenUri("http://localhost:10000/auth/oauth/token");
//	    resource.setClientId("xingkelaochen");
//	    resource.setClientSecret("123456");
//	    resource.setGrantType("authorization_code");
//	    resource.setScope(scopes);
//
//	    return resource;
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
