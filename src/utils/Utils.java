package utils;

import java.util.ArrayList;
import java.util.List;

import node.Chunk;
import node.Token;
import enums.PosDiv;

/**
 * 
 * @author noire722
 * 
 */
public class Utils {
	
	/**
	 * Chain words which have Unknown-POS-.
	 * 
	 * @param List<Chunk> chunks
	 * @return List<Chunk>
	 */
	public static List<Chunk> chainUnknown(List<Chunk> chunks) {
		List<Chunk> result = new ArrayList<Chunk>();
		for(Chunk chunk : chunks) {
			List<Token> seq = chunk.findSeq(PosDiv.UNKNOWN, PosDiv.UNKNOWN);
			if(seq.size() != 0) {
				List<Token> tokens = chunk.getTokens();
				List<Token> chained = new ArrayList<Token>();
				for(int i = 0; i < tokens.size(); i++) {
					if(tokens.get(i).is(PosDiv.UNKNOWN)) {
						StringBuffer base = new StringBuffer();
						for(Token part : seq) {
							base.append(part.getBase());
						}
						seq.get(0).setBase(base.toString());
						chained.add(seq.get(0));
						i += (seq.size() - 1);
					} else {
						chained.add(tokens.get(i));
					}
				}
				chunk.setTokens(chained);
			}
			result.add(chunk);
		}
		return result;
	}
	
	/**
	 * Remove tokens match the POS specified by argument.
	 * 
	 * @param posDiv
	 * @return List<Token>
	 */
	public static List<Token> remove(List<Token> tokens, PosDiv posDiv){
		List<Token> result = new ArrayList<Token>();
		for(Token token : tokens) {
			if(token.is(posDiv) == false) {
				result.add(token);
			}
		}
		return result;
	}

	/**
	 * Return the token sequence.
	 * 
	 * @param chunk
	 * @param start
	 * @param end
	 * @return List<Token>
	 */
	public List<Token> findSeq(Chunk chunk, PosDiv start, PosDiv end){
		List<Token> tokens = chunk.getTokens();
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
}
