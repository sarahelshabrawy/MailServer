package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Operations_Folder {
		LinkedList list = new LinkedList();

	public void DeleteFolder(File file) { // to delete folders and files but not txt
		if (file.exists()) {
			for (File subFile : file.listFiles()) {
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

//	public void DeleteFile(File file) { // to delete files
//		try {
//			if (file.delete()) {
//
//			} else {
//				throw new RuntimeException("File to be deleted doesn't exist");
//			}
//		} catch (Exception e) {
////			System.err.println(e);
//			e.printStackTrace();
//		}
//
//	}
	/////////////////////////////////////////////
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
	public void copy(File source,File target) throws IOException{
		try {
			if(source.exists()) {
			if(!target.exists()) {
				target.mkdir();
			}
			for(String child : source.list()) {
				CopyFolder(new File(source,child),new File(target,child));
			}
			}else {
				throw new RuntimeException("File to be copied doesn't exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//////////////////////////////////////////////////////
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
			String line = null;
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
				boolean rename = file.renameTo(f);
				return rename;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//	public void MoveFile(File OldPath, String NewPath) {
//		try {
//			if(OldPath.exists()) {
//			CopyFile(OldPath , NewPath);
//			DeleteFile(OldPath);
//		}else {
//			throw new RuntimeException("File to be moved is not found");
//		}
//		} catch (Exception e) {
//			e.printStackTrace(); 
//		}
//	}
////////////////////////////////////////////
//	public void MoveFile(File OldPath, String NewPath) {
//		 Path result = null;
//		try {
//			String s = OldPath.getPath();
//			 result = Files.move(Paths.get(s), Paths.get(NewPath));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if(result != null) {
//	         System.out.println("File moved successfully.");
//	      }else{
//	         System.out.println("File movement failed.");
//	      } 
//	}
////////////////////////////////////////////////	
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
////////////////////////////////////for coping tooo
//	File a = new File(OldPath);
//	if(a.renameTo(new File(a.getParent()+"//"+NewPath))) {
//	System.out.println("File is moved successful!");
//	}else{
//		System.out.println("File is failed to move!");
//	   }
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

	public void WriteFile(String file_path, String Data) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file_path, true))) {
			bw.write(Data + "\n");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	
	public Object[][] file_to_array(String file_path) {
		try {
			LinkedList list = new LinkedList();
			BufferedReader Reader = new BufferedReader(new FileReader(file_path));
			String line = Reader.readLine();
			String str = null;
			int i = 0;
			while (line != null) {
				list.add(line.split("\\s+"));
				list.add(i);
				line = Reader.readLine();
				i++;
			}

			Reader.close();
			list.remove(2 * i - 1);
			list.remove(2 * i - 2);
			i--;
			return list.listtoarray(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String[] file_to_1D_array(String file_path) {
		try {
			LinkedList list = new LinkedList();
			BufferedReader Reader = new BufferedReader(new FileReader(file_path));
			String line = Reader.readLine();
			String str = null;
			int i = 0;
			while (line != null) {
				list.add(line.split("\\s+"));
				line = Reader.readLine();
				i++;
			}

			Reader.close();
			i--;
//			list.remove(i);
			Object[] objectArray = list.listto1Darray(list);
			String[] stringArray = new String[objectArray.length]; 
			for(int p =0 ; p<objectArray.length;p++){
				stringArray[p]=Arrays.toString((String[])objectArray[p]).replace("[","" ).replace("]", "");
				
			}
			return stringArray ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void WriteListInFile(File file, String[] array) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getPath(), true))) {
			int size = array.length;
//			list.toString();
			for (int i = 0; i < size; i++) {
				bw.write(array[i] + "\n");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}
