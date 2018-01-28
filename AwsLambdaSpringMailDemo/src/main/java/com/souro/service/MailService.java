/**
 * 
 */
package com.souro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.souro.dao.MailDao;

/**
 * @author sourabrata
 *
 */
public class MailService {
	
	@Autowired
    private MailSender mailSender;
	@Autowired
    private SimpleMailMessage simpleMail;
	
    public void sendMail(MailDao mailDao)
    {
        simpleMail.setTo(mailDao.getMailTo());
        simpleMail.setSubject(mailDao.getMailSubject());
        simpleMail.setText(mailDao.getMailBody());
        mailSender.send(simpleMail);
    }
}
