package node;

import java.util.ArrayList;
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

	public Chunk(String id, String link, String rel, String score, String head,
			String func) {
		String dq = RegexParser.DOUBLE_QUATE;
		this.id = Integer.parseInt(RegexParser.getInnerString(id, dq, dq));
		this.link = Integer.parseInt(RegexParser.getInnerString(link, dq, dq));
		this.rel = ChunkRelDiv.getInstance(RegexParser.getInnerString(rel, dq, dq));
		this.score = Double.parseDouble(RegexParser.getInnerString(score, dq, dq));
		this.head = Integer.parseInt(RegexParser.getInnerString(head, dq, dq));
		this.func = Integer.parseInt(RegexParser.getInnerString(func, dq, dq));
	}

	/**
	 * Judge the instance is Subject(主語).
	 * 
	 * *** CAUTION *** This method is incomplete.
	 * 
	 * @return boolean
	 */
	public boolean isSubject() {
		Token last = childTokenList.get(childTokenList.size() - 1);
		return childTokenList.size() > 1 && last.getPos().startsWith("助詞")
				&& (last.getBase().equals("は") || last.getBase().equals("が"))
				&& this.getLink() != -1;
	}

	/**
	 * Judge the instance is Predicate(述語).This method is incomplete.
	 * 
	 * *** CAUTION *** This method is incomplete.
	 * 
	 * @return boolean
	 */
	public boolean isPredicate() {
		for (int i = 0; i < childTokenList.size(); i++) {
			Token token = childTokenList.get(i);
			if (this.getLink() == -1
					&& (token.getPos().contains("形容") || token.getPos()
							.contains("動詞"))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return the List<Chunk> that this instance is referenced by.
	 * 
	 * @param List<Chunk> searchFrom
	 * @return List<Chunk>
	 */
	public List<Chunk> getReferencedBy(List<Chunk> searchFrom){
		List<Chunk> result = new ArrayList<Chunk>();
		for(Chunk chunk : searchFrom) {
			if(chunk.getLink() == this.id) {
				result.add(chunk);
			}
		}
		return result; 
	}
	
	/**
	 * Return the chunk that this instance refers to.
	 * If there are no chunk to return, this method returns null.
	 * 
	 * @param List<Chunk> searchFrom
	 * @return Chunk
	 */
	public Chunk getReferTo(List<Chunk> searchFrom) {
		Chunk result = null;
		for(Chunk chunk : searchFrom) {
			if(chunk.getId() == this.link) {
				result = chunk;
			}
		}
		return result;
	}
	
	public void setChildTokenList(List<Token> childTokenList) {
		this.childTokenList = childTokenList;
	}

	public List<Token> getChildTokenList() {
		return this.childTokenList;
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
