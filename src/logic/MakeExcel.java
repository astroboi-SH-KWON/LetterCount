package logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import vo.LetterCountVo;

/**
 * @FileName : MakeExcel.java
 * @Project : LetterCount
 * @date : 2019. 10. 1.
 * @author : terry007x(terry007x@korea.ac.kr)
 * @history : 
 * @explanation : 엑셀 만들기 위한 클래스
 *
 */
public class MakeExcel {


	/**
	 *  분석한 데이터를 엑셀로 출력한다.
	 * 
	 * @Method Name : getExcel
	 * @date : 2019. 10. 1.
	 * @author : terry007x(terry007x@korea.ac.kr)
	 * @history : 
	 * @param path
	 * @param ar
	 * @param datalist
	 */
	public void getExcel(String path, ArrayList<String> ar, List<LetterCountVo> datalist) {
		int arSize = ar.size();
		int dtSize = datalist.size();
		System.out.println("arSize:"+arSize);
		
		for( int i = 0 ; i< dtSize ; i ++) {
			String header = StringUtils.substring(datalist.get(i).getHeader(), 1);
			System.out.println("hearder : "+header );
			File file = new File(path + "/"+header+".xls");
			
			try 
			{
				WritableWorkbook workbook = Workbook.createWorkbook(file);			
				WritableSheet sheet = workbook.createSheet("header", 0);
				
				sheet.addCell(new Label(0,0,"file name"));
				sheet.addCell(new Label(1,0,header));
				
				int totCnt = 0;
				int idx = 1;
				for( int j = 0 ; j< arSize ; j++) {
					// 1. StringUtils 
					//int count = StringUtils.countMatches(datalist.get(i).getSbr(), ar.get(j));
					
					// 2. pattern.matcher
/*					Pattern pattern = Pattern.compile(ar.get(j));
					Matcher matcher = pattern.matcher(datalist.get(i).getSbr());
					int count = 0;
					while (matcher.find()) {
						count++;
					}*/
					
					// 3. 재귀함수
					int sbrLen = datalist.get(i).getSbr().length() - ar.get(j).length()+1;
					int count = 0;
					for( int k = 0 ; k <sbrLen ; k++ ) {
						
						if(match(k,0,datalist.get(i).getSbr(),ar.get(j))) {
							count ++;
						}
						  
					}
					
					// 4. 행렬
					
					System.out.println(ar.get(j)+"개수 : "+count);
					sheet.addCell(new Label(0,j+1,ar.get(j)));
					sheet.addCell(new Label(1,j+1,count+""));
					totCnt = totCnt + count;
					idx++;
				}
				System.out.println(datalist.get(i).getHeader()+" 총개수 : "+totCnt);
				sheet.addCell(new Label(0,idx+1,"total "));
				sheet.addCell(new Label(1,idx+1,totCnt+""));
				
				
				workbook.write();
				workbook.close();
	            
			}catch (IOException e) {
				e.printStackTrace();
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
			
			
			
		}
		
	}

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

	public void getExcel2(String path, ArrayList<String> ar, Map<String, Integer> map) {
		int arSize = ar.size();
		System.out.println("arSize:"+arSize);
		
		String header = "result";
		System.out.println("hearder : "+header+" "+arSize );
		File file = new File(path + "/"+header+arSize+".xls");
		
		try 
		{
			WritableWorkbook workbook = Workbook.createWorkbook(file);			
			WritableSheet sheet = workbook.createSheet("arSize "+arSize, 0);
			
			// 총합 갯수는 long으로 받는다. 다른 int변수도 32bit 넘으면 long으로 변경
			long totCnt = 0;
			int idx = 1;
			for( int j = 0 ; j< arSize ; j++) {
				int count = map.get(ar.get(j));
				System.out.println(ar.get(j)+"개수 : "+count);
				sheet.addCell(new Label(0,j+1,ar.get(j)));
				sheet.addCell(new Label(1,j+1,count+""));
				totCnt = totCnt + count;
				idx++;
			}
			System.out.println(" 총개수 : "+totCnt);
			sheet.addCell(new Label(0,idx+1,"total "));
			sheet.addCell(new Label(1,idx+1,totCnt+""));
			
			
			workbook.write();
			workbook.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
	}

}
