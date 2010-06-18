package node;

import java.util.List;

import utils.RegexParser;
import enums.ChunkRelDiv;

/**
 * 
 * @author noire722
 *
 */
public class Chunk {
	private List<Token> childTokenList;

	private int id;
	private int link;
	private ChunkRelDiv rel;
	private double score;
	private int head;
	private int func;

	public Chunk(String id, String link, String rel, String score, String head, String func) {
		char dq = RegexParser.DOUBLE_QUATE;
		this.id = Integer.parseInt(RegexParser.getInnerString(id, dq));
		this.link = Integer.parseInt(RegexParser.getInnerString(link, dq));
		this.rel = ChunkRelDiv.getInstance(RegexParser.getInnerString(rel, dq));
		this.score = Double.parseDouble(RegexParser.getInnerString(score, dq));
		this.head = Integer.parseInt(RegexParser.getInnerString(head, dq));
		this.func = Integer.parseInt(RegexParser.getInnerString(func, dq));
		
		String tab = "\t";
		System.out.println("*" + this.id + tab + this.link + tab + this.rel + tab + this.score + tab + this.head + tab + this.func);
	}
	
	public void setChildTokenList(List<Token> childTokenList) {
		this.childTokenList = childTokenList;
	}

	public List<Token> getChildTokenList() {
		return childTokenList;
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
