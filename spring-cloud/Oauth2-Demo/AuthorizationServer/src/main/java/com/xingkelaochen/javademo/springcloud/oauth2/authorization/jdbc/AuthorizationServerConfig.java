package com.xingkelaochen.javademo.springcloud.oauth2.authorization.jdbc;

import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

/**
 * 开启Oauth Authorization Server服务支持，并进行相关配置。
 * 
 * <p>
 * 使用jdbc存储token可以参照:
 * 	https://github.com/spring-projects/spring-security-oauth/tree/master/samples/oauth2
 * 	https://github.com/jiangchao123/spring-cloud-security-oauth2-jdbc
 * </p>
 *
 *
 * @author xingkelaochen
 * 
 *         <p>
 *         E-MAIL: admin@xingkelaochen.com <br />
 *         SITE: http://www.xingkelaochen.com
 *         </p>
 *
 */
@Configuration
@EnableOAuth2Client
@EnableAuthorizationServer
@Profile(value="jdbc")
@Transactional
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private AuthenticationManager authenticationManager;

	@Bean
	public JdbcTokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}
	
	@Bean
	public ApprovalStore approvalStore() {
		TokenApprovalStore approvalStore = new TokenApprovalStore();
		approvalStore.setTokenStore(tokenStore());
		return approvalStore;
	}  

	@Bean
	protected AuthorizationCodeServices authorizationCodeServices() {
		return new JdbcAuthorizationCodeServices(dataSource);
	}
	
	@Bean
	protected JdbcUserDetailsManager jdbcUserDetailsManager() {
		
		JdbcUserDetailsManager jdbcUserDetailsManager =  new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		return jdbcUserDetailsManager;
	}

	/**
	 * 配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
	 * 
	 * <p>
	 * 	1. authorizationCodeServices：设置授权码服务，此处使用jdbc授权码服务。
	 * 	2. authenticationManager：认证管理器，当你选择了资源所有者密码（password）授权类型的时候，请设置这个属性注入一个 AuthenticationManager 对象。
	 * 	3. tokenStore：token存储实现设置
	 * 	4. userDetailsService：设置userDetailsService实现
	 * </p>
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		endpoints.authorizationCodeServices(authorizationCodeServices()).authenticationManager(authenticationManager)
				.tokenStore(tokenStore()).userDetailsService(jdbcUserDetailsManager()).approvalStore(approvalStore());
		
		// 配置TokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(false);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)); // 30天
        endpoints.tokenServices(tokenServices);
	}

	/**
	 * 配置令牌端点(Token Endpoint)的安全约束
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

		// 密码加密
		security.passwordEncoder(passwordEncoder);
		
		security.allowFormAuthenticationForClients();
		
		security.checkTokenAccess("permitAll()");
	}

	/**
	 * 配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
	 * 
	 * <p>
	 * authorizedGrantTypes：
	 * 		1. authorization_code：授权码类型。
	 *		2. implicit：隐式授权类型。
	 *		3. password：资源所有者（即用户）密码类型。
	 *		4. client_credentials：客户端凭据（客户端ID以及Key）类型。
	 *		5. refresh_token：通过以上授权获得的刷新令牌来获取新的令牌。
	 * </p>
	 * 
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		// 将配置的Oauth2的客户端信息通过JDBC写入数据库表中，表结构在schema.sql中定义
		clients.jdbc(dataSource).passwordEncoder(passwordEncoder)
			// 使用授权码模式
			.withClient("client-by-authorization_code")
				.authorizedGrantTypes("authorization_code")
				.authorities("ROLE_CLIENT")
				.scopes("trust")
				.resourceIds("oauth2-resource-server")
				.secret("xingkelaochen");
			
	}

}
