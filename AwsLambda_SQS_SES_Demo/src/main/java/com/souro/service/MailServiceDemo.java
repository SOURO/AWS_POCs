/**
 * 
 */
package com.souro.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

/**
 * @author sourabrata
 *
 */
public class MailServiceDemo {

	static final String FROM = "sourabrta.moukherjee@mheducation.com";
	static final String TO = "sourabrta.moukherjee@mheducation.com";

	static final String BODY = "You are lucky enough to get a mail from Souro, enjoy!!";
	static final String SUBJECT = "Mail from AWS for Souro's Demo";

	public void sendMail() {

		Destination destination = new Destination().withToAddresses(new String[] { TO });
		Content subject = new Content().withData(SUBJECT);
		Content textBody = new Content().withData(BODY);
		Body body = new Body().withText(textBody);

		Message message = new Message().withSubject(subject).withBody(body);

		SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination)
				.withMessage(message);

		try {
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
					.withRegion(Regions.US_EAST_1).build();

			client.sendEmail(request);
			System.out.println("Email sent!");

		} catch (Exception ex) {
			System.out.println("Error message: " + ex.getMessage());
		}
	}
}
