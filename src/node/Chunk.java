package node;

import java.util.List;

import enums.ChunkRelDiv;

public class Chunk {
	private List<Token> tokenList;

	private int id;
	private int link;
	private ChunkRelDiv rel;
	private double score;
	private int head;
	private int func;

	public Chunk(String id, String link, String rel, String score, String head, String func) {
		this.id = Integer.parseInt(id);
		this.link = Integer.parseInt(link);
		this.rel = ChunkRelDiv.getInstance(rel);
		this.score = Double.parseDouble(score);
		this.head = Integer.parseInt(head);
		this.func = Integer.parseInt(func);
	}
	
	public void setTokenList(List<Token> tokenList) {
		this.tokenList = tokenList;
	}

	public List<Token> getTokenList() {
		return tokenList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLink() {
		return link;
	}

	public void setLink(int link) {
		this.link = link;
	}

	public ChunkRelDiv getRel() {
		return rel;
	}

	public void setRel(ChunkRelDiv rel) {
		this.rel = rel;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getFunc() {
		return func;
	}

	public void setFunc(int func) {
		this.func = func;
	}

}
