package node;

public class Token {
	private String token;
	private int parentChunkId;
	
	private int id;
	private String read;
	private String base;
	private String pos;
	private String ctype;
	private String cform;
	private String ne;
	
	public Token(String token, int parentChunkId, int id, String read, String base, String pos, String ctype, String cform, String ne) {
		this.token = token;
		this.parentChunkId = parentChunkId;
		
		this.id = id;
		this.read = read;
		this.base = base;
		this.pos = pos;
		this.ctype = ctype;
		this.cform = cform;
		this.ne = ne;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getNe() {
		return ne;
	}

	public void setNe(String ne) {
		this.ne = ne;
	}

	
	
}
