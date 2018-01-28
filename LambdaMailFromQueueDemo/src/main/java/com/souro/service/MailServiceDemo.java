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
import com.souro.dao.MailDao;

/**
 * @author sourabrata
 *
 */
public class MailServiceDemo {

	public void sendMail(MailDao mailDao) {

		Destination destination = new Destination().withToAddresses("sourabrta.moukherjee@mheducation.com").withCcAddresses(mailDao.getCc()).withBccAddresses(mailDao.getBcc());
		Content subject = new Content().withCharset("UTF-8").withData(mailDao.getSubject());
		Body body = new Body().withHtml(new Content().withCharset("UTF-8").withData(mailDao.getText()));
		String from = mailDao.getFrom();

		Message message = new Message().withBody(body).withSubject(subject).withBody(body);

		SendEmailRequest request = new SendEmailRequest().withDestination(destination).withMessage(message)
				.withSource(from);

		try {
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
					.withRegion(Regions.US_EAST_1).build();

			client.sendEmail(request);
			System.out.println("Email sent! successfully");

		} catch (Exception ex) {
			System.out.println("Error message: " + ex.getMessage());
		}
	}
}
