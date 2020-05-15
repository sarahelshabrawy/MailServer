package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class App implements IApp {
	Operations_Folder of = new Operations_Folder();
		public App(){
	File f = new File("the parent");
	if (!f.exists()) {
		f.mkdir();
	}

}
static LinkedList indexList;
	public static File getFinalNewDestinationFile(File destinationFolder, File fileToCopy){

		String destFolderPath = destinationFolder.getAbsolutePath()+File.separator;
		File newFile = new File(destFolderPath + fileToCopy.getName());
		String filename=fileToCopy.getName();
		String nameWithoutExtentionOrIncrement;
		String extension = getFileExtension(filename);

		if(extension!=null){
			extension="."+extension;
			int extInd = filename.lastIndexOf(extension);
			nameWithoutExtentionOrIncrement = new StringBuilder(filename).replace(extInd, extInd+extension.length(),"").toString();
		}
		else{
			extension="";
			nameWithoutExtentionOrIncrement = filename;
		}

		int c=0;
		int indexOfClose = nameWithoutExtentionOrIncrement.lastIndexOf(")");
		int indexOfOpen = nameWithoutExtentionOrIncrement.lastIndexOf("(");

		if(indexOfClose != -1 && indexOfClose == nameWithoutExtentionOrIncrement.length() - 1 && indexOfClose > indexOfOpen && indexOfOpen != 0){
			String possibleNumber = nameWithoutExtentionOrIncrement.substring(indexOfOpen+1, indexOfClose);
			try{
				c = Integer.parseInt(possibleNumber);
				nameWithoutExtentionOrIncrement=nameWithoutExtentionOrIncrement.substring(0, indexOfOpen);
			}catch(Exception e){c=0;}
		}

		while(newFile.exists()){
			c++;
			String path = destFolderPath + nameWithoutExtentionOrIncrement +"(" + c + ")" + extension;
			newFile = new File(path);
		}
		return newFile;
	}
	public static String getFileExtension(String filename) {
		if (filename == null) {  return null; }
		int lastUnixPos = filename.lastIndexOf('/');
		int lastWindowsPos = filename.lastIndexOf('\\');
		int indexOfLastSeparator = Math.max(lastUnixPos, lastWindowsPos);
		int extensionPos = filename.lastIndexOf('.');
		int index = indexOfLastSeparator > extensionPos ? -1 : extensionPos;
		if (index == -1) {
			return null;
		} else {
			return filename.substring(index + 1).toLowerCase();
		}
	}



	public boolean signin(String email, String password) {
		File f = new File("the parent");
		if (!f.exists()) {
			f.mkdir();
		}
		File myObj = new File("the parent//" + email);
		if (!myObj.exists()) {
			return false;
		} else {

			return password.equals(of.ReadFile("the parent//" + email + "//info.txt", 4));
		}

	}

	public boolean signup(IContact contact) {
		Contact contact1 = (Contact) contact;
		try {
			if (contact1.password_affirmation()) {
				File myObj = new File("the parent//"+contact1.E_mail);
				if(!myObj.exists()) {
					if (myObj.mkdir()) {
						File inbox = new File("the parent//"+contact1.E_mail+"//inbox");
						inbox.mkdirs();
						PrintWriter writer = new PrintWriter("the parent//"+contact1.E_mail+"//inbox//indexfile.txt", StandardCharsets.UTF_8);

						File trash= new File("the parent//"+contact1.E_mail+"//trash");
						trash.mkdirs();
						 writer = new PrintWriter("the parent//"+contact1.E_mail+"//trash//indexfile.txt", StandardCharsets.UTF_8);

						File sent= new File("the parent//"+contact1.E_mail+"//sent");
						sent.mkdirs();
						writer = new PrintWriter("the parent//"+contact1.E_mail+"//sent//indexfile.txt", StandardCharsets.UTF_8);

						File drafts = new File("the parent//"+contact1.E_mail+"//drafts");
						drafts.mkdirs();
						writer = new PrintWriter("the parent//"+contact1.E_mail+"//drafts//indexfile.txt", StandardCharsets.UTF_8);


						File filters = new File("the parent//"+contact1.E_mail+"//filters");
						filters.mkdirs();
						writer = new PrintWriter("the parent//"+contact1.E_mail+"//filters//indexfile.txt", StandardCharsets.UTF_8);

						writer = new PrintWriter("the parent//"+contact1.E_mail+"//contacts.txt", StandardCharsets.UTF_8);

						// String path = info.getPath();

						of.WriteFile("the parent//"+contact1.E_mail+"//info.txt",contact1.First_name+"\n"+contact1.Last_name+"\n"+contact1.E_mail,true);
						of.WriteFile("the parent//"+contact1.E_mail+"//info.txt",contact1.Password,true);

						return true;
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * This function should be called before reading from the index file
	 * and apply the sort and search parameters
	 * @param folder currently shown, can be null
	 * @param filter to apply search, can be null
	 * @param sort to apply sort
	 */
	@Override
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
		//folder is theParent//userEmail//ex:inbox
		indexList = of.readIndexFile((Folder) folder,null);
		if(filter!=null)
		((Filter) filter).GetFilterResults();//et2akkedi enn el indexlist btet8ayyar
		((Sort) sort).GetSortResults();

	}

	@Override
	public IMail[] listEmails(int page) {
		//starts from page 1
		Mail[] mails = new Mail[10];
		int m = 0;
		if(!indexList.isEmpty()) {
			for (int i = (page - 1) * 10; i < 10; i++) {
				if (i >= indexList.size())
					break;
					mails[m] = (Mail) indexList.get(i);
					m++;
			}
			return mails;
		}
		return null;
	}

	public void deleteEmails(ILinkedList mails) {


		LinkedList list = (LinkedList) mails;

		try {

			int size = list.size();

			for (int i = 0; i < size; i++) {

				File file = new File((String) list.get(0));

				File grandparent = file.getParentFile().getParentFile();
				File f=new File(grandparent.getAbsolutePath()+"//trash//"+file.getName());

				if (file.renameTo(f)) {

					//file.delete();

					list.remove(0);

				}


				else {

					throw new RuntimeException("Folder to be deleted doesn't exist");

				}



			}

		} catch (Exception e) {

			e.printStackTrace();

		}



	}





	public void moveEmails(ILinkedList mails, IFolder des) {

		LinkedList list =(LinkedList) mails;

		Folder folder = (Folder) des;

		int size = list.size();

		File des_file = new File("the parent//" + folder.userMail + "//"+folder.folder_name);

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
	static void copyFileUsingStream(File source, File dest)  {
		InputStream is;
		OutputStream os;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}

			is.close();
			os.close();
		}
		catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}



	}





	public void write_index_file(String file_path,IMail email,String sent_to,File e_mail,String size,boolean append) {
		Mail mail1=(Mail) email;
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd:HH:mm:ss");
		Date date=new Date();
		of.WriteFile(file_path,sdf.format(date),append);
		of.WriteFile(file_path,mail1.Subject,append);
		of.WriteFile(file_path,mail1.from,append);
		of.WriteFile(file_path,sent_to,append);
		of.WriteFile(file_path,mail1.Importance,append);
		of.WriteFile(file_path,size,append);
		of.WriteFile(file_path,mail1.directory,append);

	}







	public boolean compose(IMail email) {
		Mail mail1=(Mail) email;
		String sent_to;
		double bytes=0;

		if(mail1.Mail_validation()) {
			for(int i=0;i<mail1.to.size();i++) {
				sent_to=(String)mail1.to.dequeue();


				File e_mail_from = new File("the parent//"+mail1.from+"//sent//"+sent_to);
				if(!e_mail_from.mkdir()){
					e_mail_from=getFinalNewDestinationFile(e_mail_from.getParentFile(),e_mail_from);
					e_mail_from.mkdir();
				}


				File e_mail_to = new File("the parent//"+sent_to+"//inbox//"+mail1.from);
				if(!e_mail_to.mkdir()) {
					e_mail_to=getFinalNewDestinationFile(e_mail_to.getParentFile(),e_mail_to);
					e_mail_to.mkdir();
				}


				//attachments
				File attachment_sent =new File("the parent//"+mail1.from+"//sent//"+e_mail_from.getName()+"//attachments");
				attachment_sent.mkdir();
				File attachment_inbox =new File("the parent//"+sent_to+"//inbox//"+e_mail_to.getName()+"//attachments");
				attachment_inbox.mkdir();

				for(int j=0;j<mail1.attachments.size();j++) {
					File attachment_src =new File((String)mail1.attachments.get(j));
					File attachment_des1 =new File("the parent//"+sent_to+"//inbox//"+e_mail_to.getName()+"//attachments//"+attachment_src.getName());
					File attachment_des2 =new File("the parent//"+mail1.from+"//sent//"+e_mail_from.getName()+"//attachments//"+attachment_src.getName());
					try {
						attachment_des1.createNewFile();
						attachment_des2.createNewFile();
					} catch (IOException e) {

						e.printStackTrace();
					}
					copyFileUsingStream(attachment_src,attachment_des1);
					copyFileUsingStream(attachment_src,attachment_des2);

					bytes += attachment_src.length();
				}
				mail1.attachments.clear();

				// sorted
				File sorted =new File("the parent//"+mail1.from+"//sent//"+e_mail_from.getName()+"//sorted.txt");
				of.sortedFileWriter(mail1.Body,mail1.Subject,sorted);
				sorted =new File("the parent//"+sent_to+"//inbox//"+e_mail_to.getName()+"//sorted.txt");
				of.sortedFileWriter(mail1.Body,mail1.Subject,sorted);
				//index file
				write_index_file("the parent//"+mail1.from+"//sent//indexfile.txt",email,sent_to,e_mail_from,Double.toString(bytes),true);
				write_index_file("the parent//"+sent_to+"//inbox//indexfile.txt",email,sent_to,e_mail_to,Double.toString(bytes),true);
				write_index_file("the parent//"+mail1.from+"//sent//"+e_mail_from.getName()+"//indexfile.txt",email,sent_to,e_mail_from,Double.toString(bytes),true);
				write_index_file("the parent//"+sent_to+"//inbox//"+e_mail_to.getName()+"//indexfile.txt",email,sent_to,e_mail_to,Double.toString(bytes),true);

				//date
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd:HH:mm:ss");
				Date date=new Date();
				of.WriteFile("the parent//"+mail1.from+"//sent//"+e_mail_from.getName()+"//date.txt",sdf.format(date),true);
				of.WriteFile("the parent//"+sent_to+"//inbox//"+e_mail_to.getName()+"//date.txt",sdf.format(date),true);



				//subject
				of.WriteFile("the parent//"+mail1.from+"//sent//"+e_mail_from.getName()+"//subject.txt",mail1.Subject,true);
				of.WriteFile("the parent//"+sent_to+"//inbox//"+e_mail_to.getName()+"//subject.txt",mail1.Subject,true);



				of.WriteFile("the parent//"+sent_to+"//inbox//"+e_mail_to.getName()+"//from.txt",mail1.from,true);
				of.WriteFile("the parent//"+sent_to+"//inbox//"+e_mail_to.getName()+"//to.txt",sent_to,true);


				//to
				of.WriteFile("the parent//"+mail1.from+"//sent//"+e_mail_from.getName()+"//from.txt",mail1.from,true);
				of.WriteFile("the parent//"+mail1.from+"//sent//"+e_mail_from.getName()+"//to.txt",sent_to,true);


				of.WriteFile("the parent//"+mail1.from+"//sent//"+e_mail_from.getName()+"//priority.txt",mail1.Importance,true);
				of.WriteFile("the parent//"+sent_to+"//inbox//"+e_mail_to.getName()+"//priority.txt",mail1.Importance,true);



				of.WriteFile("the parent//"+mail1.from+"//sent//"+e_mail_from.getName()+"//body.txt",mail1.Body,true);
				of.WriteFile("the parent//"+sent_to+"//inbox//"+e_mail_to.getName()+"//body.txt",mail1.Body,true);




			}
			return true;
		}
		return false;

	}
}
