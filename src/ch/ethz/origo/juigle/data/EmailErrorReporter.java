/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 *  
 *    Copyright (C) 2009 - 2010 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.data;

import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jdesktop.swingx.error.ErrorInfo;
import org.jdesktop.swingx.error.ErrorReporter;

/**
 * Email reporter. Via this class can users and developers sending error report
 * to JUIGLE developers.
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/29/2010)
 * @since 0.1.0 (1/29/2010)
 * @see ErrorReporter
 * 
 */
public class EmailErrorReporter implements ErrorReporter {

	private String mailhost = "smtp.gmail.com";
	private String recipients = "juigle.errors@gmail.com";
	private String sender = "juigle.errors@gmail.com";

	@Override
	public void reportError(ErrorInfo info) throws NullPointerException {

		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", mailhost);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props
				.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("juigle.errors", "juiglezcu");
					}
				});

		MimeMessage message = new MimeMessage(session);
		try {
			message.setSender(new InternetAddress(sender));
			message.setSubject(info.getTitle());
			message.setContent(info.getErrorException().toString(), "text/plain");
			message.setRecipients(Message.RecipientType.TO, InternetAddress
					.parse(recipients));
			if (recipients.indexOf(',') > 0)
				message.setRecipients(Message.RecipientType.TO, InternetAddress
						.parse(recipients));
			else
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(
						recipients));

			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
