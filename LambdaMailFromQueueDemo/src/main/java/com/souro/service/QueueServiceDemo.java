/**
 * 
 */
package com.souro.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.souro.dao.MailDao;

/**
 * @author sourabrata
 *
 */

public class QueueServiceDemo {

	@Autowired
	private MailServiceDemo mailService;

	public void basicSQSOperations() {

		AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

		try {

			String myQueueUrl = "https://sqs.us-east-1.amazonaws.com/711200063112/POC_QUEUE";

			ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);

			List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
			for (Message message : messages) {

				ObjectMapper mapper = new ObjectMapper();
				MailDao mailDao = mapper.readValue(message.getBody(), MailDao.class);
				mailService.sendMail(mailDao);
			}

		} catch (AmazonServiceException ase) {
			System.out.println("Error Message:    " + ase.getMessage());
		} catch (AmazonClientException ace) {
			System.out.println("Error Message: " + ace.getMessage());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
