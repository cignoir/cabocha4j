package enums;

/**
 * 品詞を定義
 * VERB("動詞")
 * NOUN("名詞")
 * PRONOUN("代名詞")
 * NOUN_MODIFIER("連体詞")
 * ADJECTIVE("形容詞")
 * ADJECTIVE_VERB("形容動詞")
 * ADVERB("副詞")
 * PREPOSITION("前置詞")
 * CONJUNCTION("接続詞")
 * INTERJECTION("感動詞")
 * PARTICLE("助詞")
 * AUXILIARY_VERB("助動詞")
 * NUMERAL("数詞")
 * ARTICLE("冠詞")
 * UNKNOWN("未知語")
 * SYMBOL("記号")
 * OTHER("その他")
 * CHAINED("連結未知語")
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
	
	public static PosDiv getInstance(String pos) {
		if(pos.contains("-") && pos.startsWith("-") == false) {
			pos = pos.split("-")[0];
		}
		
		PosDiv posDiv = null;
		if (pos.contains("冠詞")) {
			posDiv = PosDiv.ARTICLE;
		} else if (pos.contains("記号")) {
			posDiv = PosDiv.SYMBOL;
		} else if (pos.contains("未知語")) {
			posDiv = PosDiv.UNKNOWN;
		} else if (pos.contains("感動詞")) {
			posDiv = PosDiv.INTERJECTION;
		} else if (pos.contains("前置詞")) {
			posDiv = PosDiv.PREPOSITION;
		} else if (pos.contains("接続詞")) {
			posDiv = PosDiv.CONJUNCTION;
		} else if (pos.contains("副詞")) {
			posDiv = PosDiv.ADVERB;
		} else if (pos.contains("代名詞")) {
			posDiv = PosDiv.PRONOUN;
		} else if (pos.contains("数詞")) {
			posDiv = PosDiv.NUMERAL;
		} else if (pos.contains("連体詞")) {
			posDiv = PosDiv.NOUN_MODIFIER;
		} else if (pos.contains("名詞")) {
			posDiv = PosDiv.NOUN;
		} else if (pos.contains("助動詞")) {
			posDiv = PosDiv.AUXILIARY_VERB;
		} else if (pos.contains("助詞")) {
			posDiv = PosDiv.PARTICLE;
		} else if (pos.contains("形容動詞")) {
			posDiv = PosDiv.ADJECTIVE_VERB;
		} else if (pos.contains("動詞")) {
			posDiv = PosDiv.VERB;
		} else {
			posDiv = PosDiv.OTHER;
		}
		return posDiv;
	}
}
