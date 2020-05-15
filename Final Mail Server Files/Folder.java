package eg.edu.alexu.csd.datastructure.mailServer;

public class Folder implements IFolder {
	String folder_name;
	String userMail;
	String destination;
	public Folder(String name, String user) {
		folder_name = name;
		userMail = user;
		destination = "the parent\\" + userMail+"\\" + folder_name;
	}
	public Folder(String destination){
		this.destination = destination;
	}
	public String GetDestination(){
		return destination;
	}
}
