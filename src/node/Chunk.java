package node;

import java.util.ArrayList;
import java.util.List;

import utils.RegexParser;
import enums.ChunkRelDiv;
import enums.PosDiv;

/**
 * 
 * @author noire722
 * 
 */
public class Chunk {
	private List<Token> tokens;

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
	 * Remove tokens match the POS specified by argument.
	 * 
	 * @param posDiv
	 * @return List<Token>
	 */
	public List<Token> remove(PosDiv posDiv){
		List<Token> tokens = getTokens();
		List<Token> result = new ArrayList<Token>();
		for(Token token : tokens) {
			if(token.is(posDiv) == false) {
				result.add(token);
			}
		}
		return result;
	}
	
	/**
	 * Find the token which has a POS specified by an argument.
	 * If there are no tokens match condition, return an empty list. 
	 * 
	 * @param PosDiv posDiv
	 * @return List<Token>
	 */
	public List<Token> find(PosDiv posDiv){
		List<Token> result = new ArrayList<Token>();
		for(Token token : tokens) {
			if(token.is(posDiv)) {
				result.add(token);
			}
		}
		return result;
	}
	
	/**
	 * Return the token sequence.
	 * 
	 * @param start
	 * @param end
	 * @return List<Token>
	 */
	public List<Token> findSeq(PosDiv start, PosDiv end){
		List<Token> tokens = getTokens();
		List<Token> result = new ArrayList<Token>();
		boolean endFlg = false;
		int i = 0;
		while(i < tokens.size()) {
			Token token = tokens.get(i);
			if(token.is(start)) {
				result.add(token);
				i++;
				for(int j = i; j < tokens.size(); j++) {
					result.add(token);
					if(token.is(end)) {
						endFlg = true;
						break;
					}
					
					if(j == tokens.size() - 1) {
						result = new ArrayList<Token>();
						endFlg = true;
					}
				}
			}
			
			if(endFlg) {
				break;
			} else {
				i++;
			}
		}
		return result;
	}
	
	/**
	 * Return TRUE, if this has reference.
	 * 
	 * @return boolean
	 */
	public boolean hasReferTo(){
		return this.link != -1;
	}
	
	/**
	 * Return TRUE, if this is referenced by some chunks.
	 * 
	 * @param chunks
	 * @return boolean
	 */
	public boolean hasReferencedBy(List<Chunk> chunks) {
		return getReferencedBy(chunks).size() != 0;
	}
	
	/**
	 * Judge the instance is Subject(主語).
	 * 
	 * *** CAUTION *** This method is incomplete.
	 * 
	 * @return boolean
	 */
	@Deprecated
	public boolean isSubject() {
		Token last = tokens.get(tokens.size() - 1);
		return tokens.size() > 1 && last.is(PosDiv.PARTICLE)
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
	@Deprecated
	public boolean isPredicate() {
		for (int i = 0; i < tokens.size(); i++) {
			Token token = tokens.get(i);
			if (this.getLink() == -1
					&& (token.is(PosDiv.ADJECTIVE)
							|| token.is(PosDiv.ADJECTIVE_VERB)
							|| token.is(PosDiv.VERB))) {
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
	
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public List<Token> getTokens() {
		return this.tokens;
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
