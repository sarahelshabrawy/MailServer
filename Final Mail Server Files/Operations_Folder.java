package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operations_Folder {

	File indexFile;

	public void DeleteFolder(File file) { // to delete folders and files but not txt
		if (file.exists()) {
			for (File subFile : Objects.requireNonNull(file.listFiles())) {
				if (subFile.isDirectory()) {
					DeleteFolder(subFile);
				} else {
					subFile.delete();
				}
			}
			file.delete();

		} else {
			throw new RuntimeException("Folder to be deleted doesn't exist");
		}
	}


	public void CopyFolder(File source ,File target){
		try {
			if(source.isDirectory()) {
				copy(source,target);
			}else {
				Files.copy(source.toPath(), target.toPath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void copy(File source,File target) {
		try {
			if(source.exists()) {
			if(!target.exists()) {
				target.mkdir();
			}
			for(String child : Objects.requireNonNull(source.list())) {
				CopyFolder(new File(source,child),new File(target,child));
			}
			}else {
				throw new RuntimeException("File to be copied doesn't exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public void DeleteFile(LinkedList list) { // to delete files
		int size = list.size();
		try {
			for(int i=0 ; i<size;i++) {
				File file = new File((String) list.get(0));
				if (file.delete()) {
					list.remove(0);     // 0 >>>>>> i
				} else {
					throw new RuntimeException("File to be deleted doesn't exist");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public File CreateFile(String file_path) {
		File f = new File(file_path);
		try {
			f.getParentFile().mkdirs();
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}

	public void CopyFile(File file, File new_path) {
		BufferedReader reader;
		BufferedWriter writer;
		try {
			File copy = CreateFile(new_path.getPath()+"//"+file.getName());
			String line;
			if (copy.createNewFile() || !copy.createNewFile()) {
				reader = new BufferedReader(new FileReader(file));
				writer = new BufferedWriter(new FileWriter(copy, true));
				while ((line = reader.readLine()) != null) {
					writer.write(line + "\n"); // \n &&& append
				}
				reader.close();
				writer.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public boolean Rename(File file, String NewName) {
		try {
			if (file.isFile() && file.exists()) {
				File f = new File(file.getParent() + "//" + NewName + ".txt");
				return file.renameTo(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void MoveFolder(LinkedList list, File NewPath) {         //movefile <<<<<<<<<before
		int size = list.size();
		try {
			for(int i=0;i<size;i++) {
				File file = new File((String) list.get(0));
				File f = new File(NewPath+"//"+file.getName());
			if (file.renameTo(f)) {
				file.delete();
				list.remove(0);             //   ???????????????????
			} else {
				throw new RuntimeException("Failed to move the file");
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String ReadFile(String file_path, int line_num) {
		String line = null;
		try (BufferedReader Reader = new BufferedReader(new FileReader(file_path))) {
			for (int i = 1; i <= line_num; i++) {
				line = Reader.readLine();
			}
			if (line != null) {
				return line;
			} else {
				return "Invalid line number";
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}

	public void WriteFile(String file_path, String Data,boolean append) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file_path, append))) {
			bw.write(Data + "\n");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	//check
	public void sortedFileWriter(String mailBody,String subject,File sorted) {

	single_linkedlist temp = new single_linkedlist();
		try {

			String regex = "\\w+";
			String text = subject +"\n" + mailBody;
			System.out.println(text);
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(text);
			while (matcher.find()) {
					temp.add(matcher.group());
					temp.add(new Point(matcher.start(),matcher.end()-1));
			}
			Object[][] arr = temp.listTo2DArray();
			Sort.sortBodyOfMail(arr);
			BufferedWriter bw  = new BufferedWriter(new FileWriter(sorted.getPath()));
			bw.write(arr.length+" ");//write length of file in the 1st line
			for (Object[] objects : arr) {
				Point point = (Point) objects[1];
				bw.write("\n" +  objects[0] + " " + point.x+ " "+ point.y);
			}
			bw.close();
		}
	catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object[][] sortedFileReader(File sorted) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(sorted));
		String line;
		int i = 0;
			Object[][] sortedArray =new Object[Integer.parseInt(br.readLine().trim())][2];
			while ((line = br.readLine()) != null) {
				String[] temp = line.split(" ");
				sortedArray[i][0]=temp[0];
				sortedArray[i][1]=new Point(Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
				i++;
			}
			br.close();
			return sortedArray;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public LinkedList readIndexFile (Folder folder,Object[] highlights)  {
		indexFile = new File(folder.destination + "\\indexfile.txt");//check with bothaina
		LinkedList indexList = new LinkedList();
		try {
		BufferedReader br = new BufferedReader(new FileReader(indexFile));
		String line;
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd:HH:mm:ss");
			while ((line = br.readLine()) != null) {
				try {
					Mail mail = new Mail(df.parse(line), br.readLine(), br.readLine(), br.readLine(),
							br.readLine(), Double.parseDouble(br.readLine()), br.readLine());
					if(highlights!=null)
						mail.setHighlights(highlights);
					indexList.add(mail);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return indexList;
	}

	public String[] ArrayOfFiles(String FilePath) {
		File files = null;
		String[] paths = null;
		try {

			files = new File(FilePath);
			if(files.exists()) {
				paths = files.list();
				return paths;
			}
			else {
				// return null;
				throw new RuntimeException("file is not exist");
			}
		} catch(Exception e) {
			// if any error occurs
			e.printStackTrace();
		}
		return null;
	}
	public Object[][] file_to_2Darray(String file_path) {
		try {
			LinkedList list = new LinkedList();
			BufferedReader Reader = new BufferedReader(new FileReader(file_path));
			String line = Reader.readLine();
			String str = null;
			int i = 0;
			while (line != null) {
				list.add(line.split("[\\r\\n]+"));
				line = Reader.readLine();
				list.add(line.split("[\\r\\n]+"));
				line = Reader.readLine();
				//i++;
			}

			Reader.close();
			Object[][] objectArray = list.listTo2DArray();
			return objectArray;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
		public static void main(String[] args) {
		Operations_Folder t = new Operations_Folder();
		String mailBody = "Poe’s obsessive theme was the terror of losing sanity – never more dramatically evoked than in this masterpiece. In “The Tell-Tale Heart”, one of Poe’s shortest “tales of the grotesque and arabesque”, and the one that seems most contemporary in the hallucinatory intensity of its narration, an unnamed individual commits a brutal, seemingly unprovoked murder of an old man with whom he lives, disposes of the body by dismembering and burying it beneath the floorboards of the residence they share, and succumbs to madness and self-destruction in the aftermath of guilt. Throughout, the narrator insists on his sanity: “True – nervous – very, very dreadfully nervous I had been and am; but why will you say that I am mad? The disease had sharpened my senses – not destroyed – not dulled them.” That the murder is entirely irrational is acknowledged by the murderer: “Object there was none. Passion there was none. I loved the old man. He had never wronged me. He had never given me insult. For his gold I had no desire. I think it was his eye! yes, it was this! One of his eyes resembled that of a vulture – a pale blue eye, with a film over it.”";
		File mailBodyFile = new File("the parent//mailtrial//mail//body.txt");//elli hakteb fih elsorted
		t.sortedFileWriter(mailBody,"subject",mailBodyFile);
		Object[][] hihi = t.sortedFileReader(mailBodyFile);//elli ha2ra menno elsorted
		System.out.println(Arrays.deepToString(hihi));
		Search search = new Search(new Folder("the parent//mailtrial//mail"),"an");
		System.out.println(Arrays.toString(search.binarySearch(hihi, "the")));

	}

	public Object[] get_col(Object[][] arr,int col) {
		Object array[] = new Object[arr.length];
		for(int i=0;i<arr.length;i++) {
			array[i]=arr[i][col];
		}
		return array;
	}
}
