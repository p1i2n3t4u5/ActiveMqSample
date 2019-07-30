package com.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
//@EnableJms  this annotation is not mandatory in spring boot
public class JmsConfig {

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;
	
	@Value("${spring.activemq.user}")
	private String username;
	
	@Value("${spring.activemq.password}")
	private String password;
	
	@Autowired
	JmsErrorHandler  jmsErrorHandler;

	@Bean
	public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);
		activeMQConnectionFactory.setUserName(username);
		activeMQConnectionFactory.setPassword(password);
		return activeMQConnectionFactory;
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(receiverActiveMQConnectionFactory());
		factory.setErrorHandler(jmsErrorHandler);
		return factory;
	}
// this bean declaration is not required if @Component annotation is present over Receiver  class
//  @Bean
//  public Receiver receiver() {
//    return new Receiver();
//  }
}
