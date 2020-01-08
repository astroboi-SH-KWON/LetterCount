package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.MakeDataList;
import logic.MakeExcel;
import logic.MakeNucleotide;
import vo.LetterCountVo;

public class GetData {

	public static void main(String[] args) {
		
		Date startDate = new Date();
		
		// String path = args[0];
		//String path = "C:\\Users\\astroboi\\Downloads\\chromFa";
		//String path = "C:\\Users\\astroboi\\Downloads\\hg38.chromFa\\chroms";
		//String path = "C:\\Users\\astroboi\\Downloads\\test";
		String path = "C:\\Users\\astroboi\\Downloads\\fa_AAA";
		
		//
		// int nct = args[1];
		int nct = 3;
		
		String[] base = {"A","C","G","T"};
		
		MakeNucleotide mn = new MakeNucleotide();
		ArrayList<String> preFix = mn.getPrefix(base);
		ArrayList<String> ar = mn.getNucleotide(base, preFix, nct);
		
		MakeDataList mdl = new MakeDataList();
		//List<LetterCountVo> datalist = mdl.getData(path,base,nct);
		Map<String, Integer> map = mdl.getData2(path,ar,base,nct);
		
		MakeExcel me = new MakeExcel();
		//me.getExcel(path,ar,datalist);
		me.getExcel2(path,ar,map);
		//System.out.println("map : "+map);

		Date endDate = new Date();
		
		System.out.println((endDate.getTime() - startDate.getTime())/1000+"sec");

	}


}
