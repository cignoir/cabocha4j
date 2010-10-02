package enums;

/**
 * 
 * @author noire722
 *
 */
public enum PosDiv {
	 VERB("動詞")
	,NOUN("名詞")
	,PRONOUN("代名詞")
	,NOUN_MODIFIER("連体詞")
	,ADJECTIVE("形容詞")
	,ADJECTIVE_VERB("形容動詞")
	,ADVERB("副詞")
	,PREPOSITION("前置詞")
	,CONJUNCTION("接続詞")
	,INTERJECTION("感動詞")
	,PARTICLE("助詞")
	,AUXILIARY_VERB("助動詞")
	,NUMERAL("数詞")
	,ARTICLE("冠詞")
	,UNKNOWN("未知語")
	,SYMBOL("記号")
	,PARENTHESES("括弧")
	,OTHER("その他")
	,CHAINED("連結未知語")
	;
	
	private String name;
	PosDiv(String name) {
		this.setName(name);
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}
