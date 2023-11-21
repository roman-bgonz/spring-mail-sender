package com.spring.mail.sender.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

	@Value("${email.sender}")
	private String emailUser;
	
	@Value("${email.password}")
	private String emailPsd;

	/**
	 * Configures all properties to our mail sender
	 * 
	 * @return
	 */
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(emailUser);
		/**
		 * 1. Activate Two factor authentication in gmail
		 * 2. Generate a generic password using "Applications' passwords"
		 * 3. Generated password should be used here
		 */
		mailSender.setPassword(emailPsd);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		// We are enabling authentication (if not mail will be not sent)
		props.put("mail.smtp.auth", "true");
		// We are ciphering the communication with the server
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		return mailSender;
	}
}
