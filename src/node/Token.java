package node;

import utils.RegexParser;
import enums.PosDiv;
import enums.TokenNeDiv;

/**
 * 
 * @author noire722
 * 
 */
public class Token {
	private int parentChunkId;

	private int id;
	private String read;
	private String base;
	private String pos;
	private String ctype;
	private String cform;
	private TokenNeDiv ne;
	private PosDiv posDiv;

	public Token(int parentChunkId, String id, String read, String base,
			String pos, String ctype, String cform, String ne) {
		this.parentChunkId = parentChunkId;

		String dq = RegexParser.DOUBLE_QUATE;
		this.id = Integer.parseInt(RegexParser.getInnerString(id, dq, dq));
		this.read = RegexParser.getInnerString(read, dq, dq);
		this.base = RegexParser.getInnerString(base, dq, dq);
		this.pos = RegexParser.getInnerString(pos, dq, dq);
		this.ctype = RegexParser.getInnerString(ctype, dq, dq);
		this.cform = RegexParser.getInnerString(cform, dq, dq);
		this.ne = TokenNeDiv
				.getInstance(RegexParser.getInnerString(ne, dq, dq));
		this.posDiv = getPosDiv(pos);
	}

	public boolean has(String elem) {
		return this.pos.contains(elem)
		|| this.cform.contains(elem)
		|| this.ctype.contains(elem);
	}
	
	public boolean is(PosDiv posDiv) {
		return this.posDiv == posDiv;
	}
	
	public boolean is(TokenNeDiv ne) {
		return this.ne == ne;
	}
	
	public void setParentChunkId(int parentChunkId) {
		this.parentChunkId = parentChunkId;
	}

	public int getParentChunkId() {
		return parentChunkId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCform() {
		return cform;
	}

	public void setCform(String cform) {
		this.cform = cform;
	}

	public TokenNeDiv getNe() {
		return ne;
	}

	public void setNe(TokenNeDiv ne) {
		this.ne = ne;
	}

	public void setPosDiv(PosDiv posDiv) {
		this.posDiv = posDiv;
	}

	public PosDiv getPosDiv() {
		return this.posDiv;
	}

	PosDiv getPosDiv(String pos) {
		PosDiv posDiv = null;
		if (has("冠詞")) {
			posDiv = PosDiv.ARTICLE;
		} else if (has("括弧")) {
			posDiv = PosDiv.PARENTHESES;
		} else if (has("記号")) {
			posDiv = PosDiv.SYMBOL;
		} else if (has("未知語")) {
			posDiv = PosDiv.UNKNOWN;
		} else if (has("感動詞")) {
			posDiv = PosDiv.INTERJECTION;
		} else if (has("前置詞")) {
			posDiv = PosDiv.PREPOSITION;
		} else if (has("接続詞")) {
			posDiv = PosDiv.CONJUNCTION;
		} else if (has("副詞")) {
			posDiv = PosDiv.ADVERB;
		} else if (has("代名詞")) {
			posDiv = PosDiv.PRONOUN;
		} else if (has("数詞")) {
			posDiv = PosDiv.NUMERAL;
		} else if (has("連体詞")) {
			posDiv = PosDiv.NOUN_MODIFIER;
		} else if (has("名詞")) {
			posDiv = PosDiv.NOUN;
		} else if (has("助動詞")) {
			posDiv = PosDiv.AUXILIARY_VERB;
		} else if (has("助詞")) {
			posDiv = PosDiv.PARTICLE;
		} else if (has("形容動詞")) {
			posDiv = PosDiv.ADJECTIVE_VERB;
		} else if (has("動詞")) {
			posDiv = PosDiv.VERB;
		} else {
			posDiv = PosDiv.OTHER;
		}
		return posDiv;
	}
}
