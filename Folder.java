package eg.edu.alexu.csd.datastructure.mailServer;

public class Folder implements IFolder {
	String folder_name;
	String current_user;

	public Folder(String name, String user) {
		folder_name = name;
		current_user = user;

	}

}
