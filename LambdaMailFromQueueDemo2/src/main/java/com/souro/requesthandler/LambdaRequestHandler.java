/**
 * 
 */
package com.souro.requesthandler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.souro.service.QueueServiceDemo;

/**
 * @author sourabrata
 *
 */

public class LambdaRequestHandler implements RequestHandler<String, String> {

	private QueueServiceDemo queueService;
	
	public String handleRequest(String input, Context context) {

		context.getLogger().log("Input: " + input);

		@SuppressWarnings({ "resource" })
		ApplicationContext ctxt = new ClassPathXmlApplicationContext("App-Config.xml");
		queueService = ctxt.getBean(QueueServiceDemo.class);
		
		queueService.basicSQSOperations();

		return "Message Retrieved and Mail Sent Successfully from SQS and Using SES, by" + input;
	}
}
