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
}
