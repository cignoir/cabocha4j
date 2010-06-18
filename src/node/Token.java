package node;

import enums.TokenNeDiv;

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

		this.id = Integer.parseInt(id);
		this.read = read;
		this.base = base;
		this.pos = pos;
		this.ctype = ctype;
		this.cform = cform;
		this.ne = TokenNeDiv.getInstance(ne);
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
