package enums;

public enum TokenNeDiv {
	B_DATE("B-DATE")
	, I_DATE("I-DATE")
	, B_PERSON("B-PERSON")
	, I_PERSON("I-PERSON")
	, B_ORGANIZATION("B-ORGANIZATION")
	, I_ORGANIZATION("I-ORGANIZATION");
	
	String name;
	
	TokenNeDiv(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
