/**
 * 
 */
package com.souro.methodhandler;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * @author sourabrata
 *
 */

public class LambdaMethodHandler {
	public String handleRequest(String input, Context context) {
		context.getLogger().log("Input: " + input);
		return "Hello World - " + input;
	}
}
