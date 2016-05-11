package our.email.app;


public class Connection {

	
    public static void check(String username)
	{
		
		if (!Database.exists(username)) 
		{
			// System.out.println("Username does not exist...");
			Database.newUser(username);
		}
		
	}
	
	public static void view()
	{
		
	}
	
	public static void send()
	{
		
	}
	
	
	
}
