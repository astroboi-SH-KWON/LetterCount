package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import vo.LetterCountVo;

/**
 * @FileName : MakeDataList.java
 * @Project : LetterCount
 * @date : 2019. 10. 1.
 * @author : terry007x(terry007x@korea.ac.kr)
 * @history : 
 * @explanation : 검색 대상 소스 파일에서 데이터 추출 클래스
 *
 */
public class MakeDataList {

	/**
	 *  path 폴더에 있는 파일의 데이터를 가져온다
	 * 
	 * @Method Name : getData
	 * @date : 2019. 10. 1.
	 * @author : terry007x(terry007x@korea.ac.kr)
	 * @history : 
	 * @param path
	 * @param base
	 * @param nct
	 * @return
	 */
	public List<LetterCountVo> getData(String path,String[] base,int nct) {
		List<LetterCountVo> list = new ArrayList<LetterCountVo>();
		
		final File folder = new File(path);
		List<String> fileNames = listFilesForFolder(folder);
		
		
		int fileNmSize = fileNames.size();
		
		for(int i = 0 ; i < fileNmSize ; i ++ ) {
			LetterCountVo vo = new LetterCountVo();
		
		  File file = new File(path+"\\" + fileNames.get(i)); 
		  
		  String all = "";
		  //StringBuffer sb = new StringBuffer();
		  StringBuilder sbr = new StringBuilder();
		  BufferedReader br = null;
		  String header = "";
			try {
				br = new BufferedReader(new FileReader(file));
				String st = null; 
				boolean flag = true;
				while (( st = br.readLine()) != null) {
					boolean hasChars = existChars(st, base);
					if(flag) {
						header = header + st;
						flag = false;
					}else if(hasChars) {
						//sb.append(st.toUpperCase());
						// TODO 20191001 terry007x data 클 때 append시 outofmemory 현상.
						sbr.append(st.toUpperCase());
						//System.out.println(st);
						//all = all + st.toUpperCase();
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
		//System.out.println("DONE::::::::::::::::::::::::::::::::::::::::::::");
		//sbr.append(all);
		vo.setHeader(header);
		//vo.setSb(sb);
		vo.setSbr(sbr);
		list.add(vo);
		
		}
		return list;
	}

	/**
	 *  st에 base값 유무 판단 
	 * 
	 * @Method Name : existChars
	 * @date : 2019. 10. 1.
	 * @author : terry007x(terry007x@korea.ac.kr)
	 * @history : 
	 * @param st
	 * @param base
	 * @return
	 */
	private boolean existChars(String st, String[] base) {
		for(int j = 0 ; j<base.length; j++) {
			if(StringUtils.containsIgnoreCase(st, base[j])) {
				return true;
			}
		}
		return false;
	}

	/**
	 *  path 폴더에 파일들을 가져온다
	 * 
	 * @Method Name : listFilesForFolder
	 * @date : 2019. 10. 1.
	 * @author : terry007x(terry007x@korea.ac.kr)
	 * @history : 
	 * @param folder
	 * @return 
	 */
	private List<String> listFilesForFolder(File folder) {
		List<String> list =  new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else if (fileEntry.getName().indexOf("_")<0 && fileEntry.getName().indexOf("fa")>0) {
	        //} else {
	            list.add(fileEntry.getName());
	        }
	    }
		return list;
	}

	/**
	 *  path 폴더에 파일을 분석하여 특정 String 갯수를 count하여 map으로 반환한다
	 * 
	 * @Method Name : getData2
	 * @date : 2019. 10. 9.
	 * @author : terry007x(terry007x@korea.ac.kr)
	 * @history : 
	 * @param path
	 * @param ar
	 * @param base
	 * @param nct
	 * @return
	 */
	public Map<String, Integer> getData2(String path, ArrayList<String> ar, String[] base, int nct) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		final File folder = new File(path);
		List<String> fileNames = listFilesForFolder(folder);
		
		
		int fileNmSize = fileNames.size();
		
		for(int i = 0 ; i < fileNmSize ; i ++ ) {
		
		  File file = new File(path+"\\" + fileNames.get(i)); 
		  
		  StringBuilder sbr = new StringBuilder();
		  // IV
		  String iv = "";
		  for(int p=0 ; p<(nct-1);p++) {
			  iv = iv + "X";
		  }
		  sbr.append(iv);
		  BufferedReader br = null;
		  String header = "";
			try {
				br = new BufferedReader(new FileReader(file));
				String st = null; 
				boolean flag = true;
				while (( st = br.readLine()) != null) {
					if(flag) {
						header = header + st;
						flag = false;
					}else  {
						// 이전 자료중 다음 분석에 필요없는 것 삭제
						sbr.delete(0, (sbr.length()-nct+1));
						sbr.append(st.toUpperCase());
						for( int a =0 ; a< ar.size() ; a++) {
							
							// 3. 재귀함수
							int sbrLen = sbr.length() - ar.get(a).length()+1;
							int count = 0;
							for( int j = 0 ; j <sbrLen ; j++ ) {
								if(match(j,0,sbr,ar.get(a))) {
									count ++;
								}
							}
							
							// count 갯수를 map에 담는다. 
							if(map.containsKey(ar.get(a))) {
								int oldV = map.get(ar.get(a));
								map.put(ar.get(a), oldV+count);
							}else {
								map.put(ar.get(a), count);
							}
						}
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
		
		}
		return map;
	}

	/**
	 *  j와 k의 sequence 값으로 순서대로 비교해서 동일 문자열인 경우 true 반환
	 * 
	 * @Method Name : match
	 * @date : 2019. 10. 9.
	 * @author : terry007x(terry007x@korea.ac.kr)
	 * @history : 
	 * @param j 
	 * @param k
	 * @param sbr
	 * @param base
	 * @return
	 */
	private boolean match(int j, int k, StringBuilder sbr, String base) {
		if(sbr.charAt(j) == base.charAt(k)) {
			if(k == (base.length()-1)) {
				return true;
			}else {
				return match(j+1,k+1,sbr,base);
			}
		}
		return false;
	}

}
