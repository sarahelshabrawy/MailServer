package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.util.Date;

public class Mail implements IMail {
	String from;
	Queue_List to = new Queue_List();
	single_linkedlist attachments = new single_linkedlist();
	String Subject;
	String Body;
	String Importance;
	Date date;
	String To;
	double AttachmentsSize;
	String directory;
	Object[] highlights;

	public void setHighlights(Object[] highlights) {
		this.highlights = highlights;
	}

	public Mail() {
	}
	//1)Subject 2)From 3)To 4)Priority
	public Mail(Date date,String Subject, String From ,String To, String Priority, double AttachmentsSize,String directory){
		this.date = date;
		this.Subject = Subject;
		this.from= From;
		this.To = To;
		Importance = Priority;
		this.AttachmentsSize = AttachmentsSize;
		this.directory = directory;
	}
	public Object getAttribute (EFilterTypes filterName){
		switch (filterName){
			case Date: return this.date;
			case To: return this.to;
			case From: return this.from;
			case Subject: return this.Subject;
			case Priority: return this.Importance;
			case Attachments: return this.AttachmentsSize;
		}
		return null;
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
	//////////////


		public void get_attachment(File[] files) {
			attachments.clear();
			for (File file : files) {
				attachments.add(file.getPath());

			}

		}

		public void get_sender(String[] sender) {

			for (String s : sender) {
				to.enqueue(s);
			}
		}

		public void get_subject(String subject) {
			Subject=subject;
		}

		public void get_body(String body) {
			Body=body;
		}

		public void get_priority(String importance) {
			Importance=importance;
		}


		public void from (String email) {


			from=email;

		}


	}