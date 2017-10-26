package Domain;

public class User {
	private String fullName;
	private String login;
	private String email;
	private String password;
	private boolean isAdmin;

	public String getFullName()
	{
		return fullName;
	}
	public boolean getIsAdmin()
	{
		return isAdmin;
	}
	public String getLogin()
	{
		return login;
	}
	public String getEmail()
	{
		return email;
	}
	public String getPassword()
	{
		return password;
	}
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}
	public void setLogin(String login)
	{
		this.login = login;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public void setIsAdmin(boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}
	@Override	
	public String toString()
	{
		String role;
		if (isAdmin)
			role = "adm";
		else
			role = "usr";
		return getClass()+","+fullName+","+login+","+email+","+password+","+role;
	}
	
	@Override
	public boolean equals(Object obj) { 
        if (obj == this) { 
                return true; 
        } 
        if (obj == null || obj.getClass() != this.getClass()) { 
                return false; 
        } 

        User user = (User) obj; 
        return (isAdmin == user.getIsAdmin() 
                && (fullName == user.fullName || (fullName != null &&fullName.equals(user.getFullName())))                
                && (login == user.login  || (login != null && login .equals(user.getLogin())))
                && (email == user.email  || (email != null && email .equals(user.getEmail())))
                && (password == user.password  || (password != null && password .equals(user.getPassword())))); 
    } 
}
