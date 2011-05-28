package com.cignoir.enums;

/**
 * Tokenの属性を定義
 * @author noire722
 *
 */
public enum TokenNeDiv {
	B_DATE("B-DATE")
	, I_DATE("I-DATE")
	, B_PERSON("B-PERSON")
	, I_PERSON("I-PERSON")
	, B_ORGANIZATION("B-ORGANIZATION")
	, I_ORGANIZATION("I-ORGANIZATION")
	, NONE("");
	
	String name;
	
	TokenNeDiv(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static TokenNeDiv getInstance(String ne) {
		TokenNeDiv div = null;
		if(ne.equals("B-PERSON")) {
			div = TokenNeDiv.B_PERSON;
		} else if(ne.equals("I-PERSON")) {
			div = TokenNeDiv.I_PERSON;
		}else if(ne.equals("B-DATE")) {
			div = TokenNeDiv.B_DATE;
		} else if(ne.equals("I-DATE")) {
			div = TokenNeDiv.I_DATE;
		} else if(ne.equals("B-ORGANIZATION")) {
			div = TokenNeDiv.B_ORGANIZATION;
		} else if(ne.equals("I-ORGANIZATION")) {
			div = TokenNeDiv.I_ORGANIZATION;
		} else {
			div = TokenNeDiv.NONE;
		}
		return div;
	}
}
