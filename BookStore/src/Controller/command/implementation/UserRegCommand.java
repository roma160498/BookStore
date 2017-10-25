package Controller.command.implementation;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Service.ServiceFactory;
import Service.UserService;
import Service.exceptions.ServiceException;
import Controller.command.Command;
import Domain.User;

public class UserRegCommand implements Command{
	public String execute(String request) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		UserService service = serviceFactory.getUserService();
		String response = null;
		
		User user = new User();
		String paramArray[] = request.split("\\|");
		
		user.setEmail(paramArray[2]);
		user.setFullName(paramArray[1]);
		user.setLogin(paramArray[3]);
		user.setPassword(md5Hasher(paramArray[4]));
		user.setIsAdmin(parseBoolean(paramArray[5]));
		
		try {
			if (service.register(user))
			response = user.toString();
		} catch (ServiceException ex) {
			
			response = "Error";
		}
		
		return response;
		
	//	return null;
	}

	private boolean parseBoolean(String string) {
		if (string.equals("true"))
			return true;
		else
			return false;
	}
	private String md5Hasher(String string) {
	    MessageDigest messageDigest = null;
	    byte[] digest = new byte[0];
	 
	    try {
	        messageDigest = MessageDigest.getInstance("MD5");
	        messageDigest.reset();
	        messageDigest.update(string.getBytes());
	        digest = messageDigest.digest();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	 
	    BigInteger bigInt = new BigInteger(1, digest);
	    String md5Hex = bigInt.toString(16);
	 
	    while( md5Hex.length() < 32 ){
	        md5Hex = "0" + md5Hex;
	    }
	 
	    return md5Hex;
	}
	
	
}
