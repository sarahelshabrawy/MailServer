package eg.edu.alexu.csd.datastructure.mailServer;

public class Contact implements IContact {
	public String First_name;
	String Last_name;
	String E_mail;
	String Password;
	String Confirm ;
		public Contact(String first_name,String last_name ,String e_mail ,String password ,String confirm ) {
		 First_name=first_name;
		 Last_name=last_name;
		 E_mail=e_mail;
		 Password=password;
		 Confirm=confirm;
		 
		}
		
		public boolean password_affirmation() {
			return Password.equals(Confirm);
		}

}
