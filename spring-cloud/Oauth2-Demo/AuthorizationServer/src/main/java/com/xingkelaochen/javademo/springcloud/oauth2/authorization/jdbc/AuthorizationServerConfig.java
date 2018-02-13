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
 * ����Oauth Authorization Server����֧�֣�������������á�
 * 
 * <p>
 * ʹ��jdbc�洢token���Բ���:
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
	 * ������Ȩ��authorization���Լ����ƣ�token���ķ��ʶ˵�����Ʒ���(token services)
	 * 
	 * <p>
	 * 	1. authorizationCodeServices��������Ȩ����񣬴˴�ʹ��jdbc��Ȩ�����
	 * 	2. authenticationManager����֤������������ѡ������Դ���������루password����Ȩ���͵�ʱ���������������ע��һ�� AuthenticationManager ����
	 * 	3. tokenStore��token�洢ʵ������
	 * 	4. userDetailsService������userDetailsServiceʵ��
	 * </p>
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		endpoints.authorizationCodeServices(authorizationCodeServices()).authenticationManager(authenticationManager)
				.tokenStore(tokenStore()).userDetailsService(jdbcUserDetailsManager()).approvalStore(approvalStore());
		
		// ����TokenServices����
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(false);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)); // 30��
        endpoints.tokenServices(tokenServices);
	}

	/**
	 * �������ƶ˵�(Token Endpoint)�İ�ȫԼ��
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

		// �������
		security.passwordEncoder(passwordEncoder);
		
		security.allowFormAuthenticationForClients();
		
		security.checkTokenAccess("permitAll()");
	}

	/**
	 * ���ÿͻ����������ClientDetailsService�����ͻ���������Ϣ��������г�ʼ�������ܹ��ѿͻ���������Ϣд�������������ͨ�����ݿ����洢��ȡ������Ϣ��
	 * 
	 * <p>
	 * authorizedGrantTypes��
	 * 		1. authorization_code����Ȩ�����͡�
	 *		2. implicit����ʽ��Ȩ���͡�
	 *		3. password����Դ�����ߣ����û����������͡�
	 *		4. client_credentials���ͻ���ƾ�ݣ��ͻ���ID�Լ�Key�����͡�
	 *		5. refresh_token��ͨ��������Ȩ��õ�ˢ����������ȡ�µ����ơ�
	 * </p>
	 * 
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		// �����õ�Oauth2�Ŀͻ�����Ϣͨ��JDBCд�����ݿ���У���ṹ��schema.sql�ж���
		clients.jdbc(dataSource).passwordEncoder(passwordEncoder)
			// ʹ����Ȩ��ģʽ
			.withClient("client-by-authorization_code")
				.authorizedGrantTypes("authorization_code")
				.authorities("ROLE_CLIENT")
				.scopes("trust")
				.resourceIds("oauth2-resource-server")
				.secret("xingkelaochen");
			
	}

}
