package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import vo.LetterCountVo;

public class TestMakeDataList {

	public static void main(String[] args) {
		
		String path = "C:\\Users\\astroboi\\Downloads\\test";
		int nct = 3;
		String[] base = {"A","C","G","T"};

		List<LetterCountVo> list = new ArrayList<LetterCountVo>();
		
		final File folder = new File(path);
		List<String> fileNames = listFilesForFolder(folder);
		
		
		int fileNmSize = fileNames.size();
		
		for(int i = 0 ; i < fileNmSize ; i ++ ) {
			LetterCountVo vo = new LetterCountVo();
		
		  File file = new File(path+"\\" + fileNames.get(i)); 
		  
		  String all = "";
		  StringBuffer sb = new StringBuffer();
		  StringBuilder sbr = new StringBuilder();
		  BufferedReader br = null;
		  String header = "";
			try {
				br = new BufferedReader(new FileReader(file));
				String st = null; 
				boolean flag = true;
				while (( st = br.readLine()) != null) {
					boolean hasChars = false;
					for(int j = 0 ; j<base.length; j++) {
						if(StringUtils.containsIgnoreCase(st, base[j])) {
							hasChars = true;
							break;
						}
					}
					if(flag) {
						header = header + st;
						flag = false;
					}else if(hasChars) {
						//sb.append(st.toUpperCase());
						//sbr.append(st.toUpperCase());
						//System.out.println(st.toUpperCase());
						all = all + st.toUpperCase();
					}
				}
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(br!=null) {
						br.close();
					}
				} catch (Exception e2) {
				}
			}
		System.out.println("DONE::::::::::::::::::::::::::::::::::::::::::::");
		sbr.append(all);
		vo.setHeader(header);
		//vo.setSb(sb);
		vo.setSbr(sbr);
		list.add(vo);
		
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
