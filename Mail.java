package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;

public class Mail implements IMail {
	String from;
	Queue_List to = new Queue_List();
	single_linkedlist attachments = new single_linkedlist();
	String Subject;
	String Body;
	String Importance;

	public Mail(File[] files, String[] sender, String subject, String body, String importance, Contact contact1) {
		for (int i = 0; i < files.length; i++) {
			attachments.add(files[i].getPath());

		}
		for (int i = 0; i < sender.length; i++) {
			to.enqueue(sender[i]);
		}
		Subject = subject;
		Body = body;
		Importance = importance;
		from = contact1.E_mail;

	}

	public boolean Mail_validation() {
		for (int i = 0; i < to.size(); i++) {
			String sent_to = (String) to.dequeue();
			File myObj = new File("the parent//" + sent_to);
			if (!myObj.exists()) {
				return false;
			}
			to.enqueue(sent_to);
		}
		return true;

	}
}