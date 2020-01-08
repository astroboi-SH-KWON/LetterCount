package logic;

import java.util.ArrayList;

/**
 * @FileName : MakeNucleotide.java
 * @Project : LetterCount
 * @date : 2019. 10. 1.
 * @author : terry007x(terry007x@korea.ac.kr)
 * @history : 
 * @explanation : 초기 데이터값 설정 클래스
 *
 */
public class MakeNucleotide {

	/**
	 *  prefix 설정
	 * 
	 * @Method Name : getPrefix
	 * @date : 2019. 10. 1.
	 * @author : terry007x(terry007x@korea.ac.kr)
	 * @history : 
	 * @param base
	 * @return
	 */
	public ArrayList<String> getPrefix(String[] base) {
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i < base.length;i++) {
			list.add(base[i]);
		}
		return list;
	}

	/**
	 *  중복순열의 초기 데이터값 설정
	 * 
	 * @Method Name : getNucleotide
	 * @date : 2019. 10. 1.
	 * @author : terry007x(terry007x@korea.ac.kr)
	 * @history : 
	 * @param base
	 * @param results
	 * @param nct
	 * @return
	 */
	public ArrayList<String> getNucleotide(String[] base, ArrayList<String> results, int nct) {
		if(nct==1) {
			return results;
		}else {
			ArrayList<String> process = new ArrayList<String>();
			for(int i=0;i<base.length;i++) {
				String st1 = base[i];
				for(int j=0; j<results.size();j++) {
					String st2 = results.get(j);
					process.add(st1+st2);
				}
			}
			return getNucleotide(base,process,nct-1);
		}
	}

}
