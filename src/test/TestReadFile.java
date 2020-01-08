package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import vo.LetterCountVo;

public class TestReadFile {

	public static void main(String[] args) {
		String path = "C:\\Users\\astroboi\\Downloads\\chromFaTest";
		
		final File folder = new File(path);
		List<String> fileNames = listFilesForFolder(folder);

		int fileNmSize = fileNames.size();
		
		for(int i = 0 ; i < fileNmSize ; i ++ ) {
			LetterCountVo vo = new LetterCountVo();
		
		  File file = new File(path+"\\" + fileNames.get(i)); 
		  
		  String all = "";
		  StringBuilder sbr = new StringBuilder();
		  FileInputStream fis = null;
		  String header = "";
			try {
				fis = new FileInputStream(file);
				byte[] data = new byte[(int) file.length()];
				fis.read(data);
				fis.close();

				all = new String(data, "UTF-8");
				//System.out.println("::::::start::::::::"+all+"::::::::end::::::::");
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(fis!=null) {
						fis.close();
					}
				} catch (Exception e2) {
				}
			}
		System.out.println("DONE::::::::::::::::::::::::::::::::::::::::::::");
		sbr.append(all.replaceAll("\r", "").replaceAll("\n", "").toUpperCase().trim());
		vo.setHeader(header);
		//vo.setSb(sb);
		vo.setSbr(sbr);
		
		}
	}

	private static List<String> listFilesForFolder(File folder) {
		List<String> list =  new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else if (fileEntry.getName().indexOf("_")<0) {
	            list.add(fileEntry.getName());
	        }
	    }
		return list;
	}

}
