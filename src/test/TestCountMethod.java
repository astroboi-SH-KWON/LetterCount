package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class TestCountMethod {

	public static void main(String[] args) {
		//String[] base = {"A","C","G","T"};
		String[] base = {"AA","AAA","T"};
//		String[] base = {"AB","ABC","T"};
		
		StringBuilder sbr = new StringBuilder();
		
//		sbr.append("AAAAAAAAAA");// A 10개
		sbr.append("AAABAAAAAAA");
//		sbr.append("ABBABCAAAA");
		
		for(int i=0 ; i < base.length ; i++) {
			// 1. StringUtils 
//			int count = StringUtils.countMatches(sbr, base[i]);
//			int count = StringUtils.countOccurrencesOf(sbr, base[i]);
			
			// 2. pattern.matcher
/*			Pattern pattern = Pattern.compile(base[i]);
			Matcher matcher = pattern.matcher(sbr);
			int count = 0;
			while (matcher.find()) {
				count++;
			}*/
			
			// 3. 재귀함수
			int sbrLen = sbr.length() - base[i].length()+1;
			int count = 0;
			for( int j = 0 ; j <sbrLen ; j++ ) {
				
				if(match(j,0,sbr,base[i])) {
					count ++;
				}
				  
			}
			
			// 4. 행렬
			
			System.out.println(base[i]+" 갯수 : "+count);
		}

	}

	private static boolean match(int j, int k, StringBuilder sbr, String base) {
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
