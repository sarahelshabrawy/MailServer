package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App implements IApp {

	public boolean signin(String email, String password) {
		Operations_Folder folder = new Operations_Folder();
		File f = new File("the parent");
		if (!f.exists()) {
			f.mkdir();
		}
		File myObj = new File("the parent//" + email);
		if (!myObj.exists()) {
			return false;
		} else {

			if (password.equals(folder.ReadFile("the parent//" + email + "//info.txt", 4))) {
				return true;
			} else
				return false;
		}

	}

	public boolean signup(IContact contact) {
		Contact contact1 = (Contact) contact;
		try {
			if (contact1.password_affirmation()) {
				File myObj = new File("the parent//" + contact1.E_mail);
				if (!myObj.exists()) {
					if (myObj.mkdir()) {
						File inbox = new File("the parent//" + contact1.E_mail + "//inbox");
						inbox.mkdirs();
						File trash = new File("the parent//" + contact1.E_mail + "//trash");
						trash.mkdirs();
						File sent = new File("the parent//" + contact1.E_mail + "//sent");
						sent.mkdirs();
						File drafts = new File("the parent//" + contact1.E_mail + "//drafts");
						drafts.mkdirs();
						File filters = new File("the parent//" + contact1.E_mail + "//filters");
						filters.mkdirs();

						File info = new File("the parent//" + contact1.E_mail + "//info.txt"); // <<<<**
						FileWriter fileWriter = new FileWriter("the parent//" + contact1.E_mail + "//info.txt", true);
						BufferedWriter bw = new BufferedWriter(fileWriter);
						bw.write(contact1.First_name + "\n");
						bw.write(contact1.Last_name + "\n");
						bw.write(contact1.E_mail + "\n");
						bw.write(contact1.Password + "\n");
						bw.close();
						return true;
					}
				}
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
		// TODO Auto-generated method stub

	}

	@Override
	public IMail[] listEmails(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmails(ILinkedList mails) {
		LinkedList list = (LinkedList) mails;
		Operations_Folder folder = new Operations_Folder();
		try {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				File file = new File((String) list.get(0));
				if(!file.isDirectory()) {
					file.delete();
				}
				else if (file.exists()) {
					for (File subFile : file.listFiles()) {
						if (subFile.isDirectory()) {
							folder.DeleteFolder(subFile);
						} else {
							subFile.delete();
						}
					}
					file.delete();

				} else {
					throw new RuntimeException("Folder to be deleted doesn't exist");
				}
				list.remove(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void moveEmails(ILinkedList mails, IFolder des) {
		LinkedList list =(LinkedList) mails;
		Folder folder = (Folder) des;
		int size = list.size();
		File des_file = new File("the parent//" + folder.current_user + "//"+folder.folder_name);
		try {
			if(des_file.exists()) {
			for(int i=0;i<size;i++) {
				File file = new File((String) list.get(0));
				File f;
				f = new File(des_file +"//"+file.getName());
			if (file.renameTo(f)) {
				file.delete();
				list.remove(0);           
			} else {
				throw new RuntimeException("Failed to move the file");
			}
			}
			}else {
				throw new RuntimeException("Failed to move the file");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean compose(IMail email) {
		// TODO Auto-generated method stub
		return false;
	}

}
