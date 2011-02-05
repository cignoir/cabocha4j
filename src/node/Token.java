package node;

import utils.RegexParser;
import enums.PosDiv;
import enums.TokenNeDiv;

/**
 * Tokenは品詞単位の最小ノード。
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

	/**
	 * Constructor
	 * 
	 * @param parentChunkId
	 * @param id
	 * @param read
	 * @param base
	 * @param pos
	 * @param ctype
	 * @param cform
	 * @param ne
	 */
	public Token(int parentChunkId, String id, String read, String base,
			String pos, String ctype, String cform, String ne) {
		this.parentChunkId = parentChunkId;

		String dq = RegexParser.DOUBLE_QUATE;
		this.id = Integer.parseInt(RegexParser.getInnerString(id, dq, dq));
		this.read = RegexParser.getInnerString(read, dq, dq);
		this.base = base;
		this.pos = RegexParser.getInnerString(pos, dq, dq);
		this.ctype = RegexParser.getInnerString(ctype, dq, dq);
		this.cform = RegexParser.getInnerString(cform, dq, dq);
		this.ne = TokenNeDiv
				.getInstance(RegexParser.getInnerString(ne, dq, dq));
		this.posDiv = getPosDiv(pos);
	}

	/**
	 * このオブジェクトが持つpos、cform、またはctypeの中から
	 * 引数で指定した文字列を含むものがあった場合TRUEを返す。
	 * 
	 * @param str
	 * @return boolean
	 */
	public boolean has(String str) {
		return this.pos.contains(str)
		|| this.cform.contains(str)
		|| this.ctype.contains(str);
	}
	
	/**
	 * このオブジェクトが引数で指定した品詞posDivを持つTokenであるかを判定する。
	 * 
	 * @param posDiv
	 * @return boolean
	 */
	public boolean is(PosDiv posDiv) {
		return this.posDiv == posDiv;
	}
	
	public boolean is(PosDiv... posDivAry) {
		for(PosDiv posDiv : posDivAry) {
			if(this.is(posDiv)) return true;
		}
		return false;
	}

	/**
	 * このオブジェクトが引数で指定した属性tokenDivを持っているかどうかを判定する。 
	 * @param ne
	 * @return boolean
	 */
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
		if(pos.contains("-") && pos.startsWith("-") == false) {
			pos = pos.split("-")[0];
		}
		return PosDiv.getInstance(pos);
	}
}
