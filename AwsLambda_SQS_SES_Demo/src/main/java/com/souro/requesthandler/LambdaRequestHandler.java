/**
 * 
 */
package com.souro.requesthandler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.souro.service.MailServiceDemo;
import com.souro.service.QueueServiceDemo;

/**
 * @author sourabrata
 *
 */

public class LambdaRequestHandler implements RequestHandler<String, String> {

	private QueueServiceDemo queueService;
	
	private MailServiceDemo mailService;

	public String handleRequest(String input, Context context) {

		context.getLogger().log("Input: " + input);

		@SuppressWarnings({ "resource" })
		ApplicationContext ctxt = new ClassPathXmlApplicationContext("App-Config.xml");
		
		queueService = ctxt.getBean(QueueServiceDemo.class);
		mailService = ctxt.getBean(MailServiceDemo.class);

		queueService.basicSQSOperations();
		mailService.sendMail();

		return "Message Retrieved and Mail Sent Successfully from SQS and Using SES, by" + input;
	}
}
