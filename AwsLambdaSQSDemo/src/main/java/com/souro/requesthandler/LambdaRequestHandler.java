/**
 * 
 */
package com.souro.requesthandler;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	QueueServiceDemo msgService;

	public String handleRequest(String input, Context context) {

		context.getLogger().log("Input: " + input);

		@SuppressWarnings({ "resource", "unused" })
		ApplicationContext ctxt = new ClassPathXmlApplicationContext("App-Config.xml");

		msgService.basicSQSOperations();

		return "Message Retrieved Successfully from SQS, by" + input;
	}
}
