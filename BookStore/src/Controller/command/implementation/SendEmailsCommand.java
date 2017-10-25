package Controller.command.implementation;

import java.util.ArrayList;
import java.util.Properties;

import Controller.command.Command;
import Service.ServiceFactory;
import Service.UserService;
import Service.exceptions.ServiceException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.*;
import javax.mail.*;

public class SendEmailsCommand implements Command {

	@Override
	public String execute(String request) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		UserService service = serviceFactory.getUserService();
		String response = null;
		ArrayList<String> emailsList = null;
		final String[] paramsArray = request.split("\\|");
		try {
			emailsList= service.getEmails();
		} catch (ServiceException e) {
			response = "Error";
		}
		int dogPosition = paramsArray[1].indexOf("@");
		String domen = paramsArray[1].substring(dogPosition+1);
		String role = null;
		if (paramsArray[6]=="1")
			role = "�������� �����";
		else
			role = "����������� �����";
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp."+domen);
		properties.put("mail.smtp.socketFactory.port", 465);
		properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port",465);
		
		Session session = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(paramsArray[1], paramsArray[2]);
				
			}
		});
		
		try{
			for (String email : emailsList)
			{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(paramsArray[1]));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("���������� ���� � ����� ��������");
			message.setText("������������, � ��� ����� ��������� ����� �����.\n��������: "+paramsArray[3]+"\n�����: "+paramsArray[4]
					+"\n��� �������: "+paramsArray[5]+"\n���: "+role);
			Transport.send(message);
			}
			response = "OK";
			}catch(Exception ex){
			response = "Error";
		}
		
		
		return response;
	}

}
