/**
 * 
 */
package com.souro.requesthandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.souro.dao.MailDao;
import com.souro.service.MailService;

/**
 * @author sourabrata
 *
 */

public class LambdaRequestHandler implements RequestHandler<String, String> {

	@Autowired
	MailService mailService;

	@Autowired
	MailDao mailDao;

	public String handleRequest(String input, Context context) {

		context.getLogger().log("Input: " + input);
		
		@SuppressWarnings({ "resource", "unused" })
        ApplicationContext ctxt = new ClassPathXmlApplicationContext("App-Config.xml");

		mailDao.setMailTo("soura1990@gmail.com");
		mailDao.setMailFrom("souro.nitd@gmail.com");
		mailDao.setMailSubject("Demo Subject");
		mailDao.setMailBody("You are lucky enough to get a mail from Souro, enjoy!!");

		mailService.sendMail(mailDao);

		return "Mail Sent Successfully, by" + input;
	}
}
