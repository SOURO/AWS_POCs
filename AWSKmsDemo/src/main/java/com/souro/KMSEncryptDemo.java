/**
 * 
 */
package com.souro;

import java.nio.ByteBuffer;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.EncryptRequest;
import com.amazonaws.services.kms.model.EncryptResult;

/**
 * @author sourabrata
 *
 */
public class KMSEncryptDemo{
	public String handleRequest() {
		AWSKMS client = AWSKMSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
		EncryptRequest request = new EncryptRequest().withKeyId("arn:aws:kms:us-east-1:711200063112:key/75ebcd56-cb54-441b-9834-a0197f56cbe8").withPlaintext(ByteBuffer.wrap("demotextbysouro".getBytes()));
		EncryptResult response = client.encrypt(request);
		//System.out.println(response);
		return response.toString();
	}
}