package node;

import utils.RegexParser;
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

	public Token(int parentChunkId, String id, String read, String base, String pos, String ctype, String cform, String ne) {
		this.parentChunkId = parentChunkId;

		String dq = RegexParser.DOUBLE_QUATE;
		this.id = Integer.parseInt(RegexParser.getInnerString(id, dq, dq));
		this.read = RegexParser.getInnerString(read, dq, dq);
		this.base = RegexParser.getInnerString(base, dq, dq);
		this.pos = RegexParser.getInnerString(pos, dq, dq);
		this.ctype = RegexParser.getInnerString(ctype, dq, dq);
		this.cform = RegexParser.getInnerString(cform, dq, dq);
		this.ne = TokenNeDiv.getInstance(RegexParser.getInnerString(ne, dq, dq));
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
	

}
