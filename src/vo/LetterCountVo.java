package vo;

import java.util.List;


/**
 * @FileName : LetterCountVo.java
 * @Project : LetterCount
 * @date : 2019. 10. 1.
 * @author : terry007x(terry007x@korea.ac.kr)
 * @history : 
 * @explanation : 데이터 분석위한 vo
 *
 */
public class LetterCountVo {
	private String header = "";
	private StringBuffer sb = null;
	private StringBuilder sbr = null;
	
	
	private List<LetterCountVo> list = null;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public StringBuffer getSb() {
		return sb;
	}

	public void setSb(StringBuffer sb) {
		this.sb = sb;
	}

	public List<LetterCountVo> getList() {
		return list;
	}

	public void setList(List<LetterCountVo> list) {
		this.list = list;
	}

	public StringBuilder getSbr() {
		return sbr;
	}

	public void setSbr(StringBuilder sbr) {
		this.sbr = sbr;
	}
	
	
}
