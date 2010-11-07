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
	 * 引数で指定したChunkリスト中の連続した品詞を連結させる。
	 * 連結されたTokenはひとつのTokenとして扱われる。
	 * 第二引数で指定するPosDivは可変長引数であり、複数指定することが可能。
	 * Chain words which have POSes specified with the Second argument
	 * from the ChunkList.
	 * 
	 * @param List<Chunk> chunks 
	 * @param PosDiv... chains ここで指定したPosDivを連結させる。
	 * @return List<Chunk>
	 */
	public static List<Chunk> chainPos(List<Chunk> chunks, PosDiv... chains) {
		List<Chunk> result = new ArrayList<Chunk>();
		for(Chunk chunk : chunks) {
			List<Token> seq = chunk.find(chains);
			if(seq.size() != 0) {
				List<Token> tokens = chunk.getTokens();
				List<Token> chained = new ArrayList<Token>();
				for(int i = 0; i < tokens.size(); i++) {
					if(tokens.get(i).is(chains)) {
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
	 * tokensから指定したposDiv(品詞)を持つTokenを取り除く。
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
	 * chunk中のTokenリストからある品詞startからある品詞endまでの連続したtokenの並びを取得する。
	 * Return the token sequence.
	 * 
	 * @param chunk
	 * @param start
	 * @param end
	 * @return List<Token>
	 */
	public static List<Token> findSeq(Chunk chunk, PosDiv start, PosDiv end){
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
					Token nextToken = tokens.get(j);
					if(j == tokens.size() - 1 || nextToken.is(end) == false) {
						result.add(nextToken);
					} else {
						endFlg = true;
						break;
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
