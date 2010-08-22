package node;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author noire722
 *
 */
public class Sentence {
	private String plainText;
	private List<String> analyzed;
	private List<Chunk> chunkList;
	
	private static final int C_ID = 1;
	private static final int C_LINK = 2;
	private static final int C_REL = 3;
	private static final int C_SCORE = 4;
	private static final int C_HEAD = 5;
	private static final int C_FUNC = 6;
	private static final int T_ID = 1;
	private static final int T_READ = 2;
	private static final int T_BASE = 3;
	private static final int T_POS = 4;
	private static final int T_CTYPE = 5;
	private static final int T_CFORM = 6;
	private static final int T_NE = 7;
	
	public Sentence(String plainText, List<String> analyzed){
		setPlainText(plainText);
		setAnalyzed(analyzed);
		chunkList = createChunkList();		
	}

	private List<Chunk> createChunkList() {
		List<Chunk> chunkList = new ArrayList<Chunk>();
		List<Token> tokenList = new ArrayList<Token>();
		
		Chunk chunk = null;
		for(String line : analyzed) {
			line = line.trim();
			Matcher matcher = Pattern.compile("<chunk.*>").matcher(line);
			if(matcher.find()) {
				String[] ary = line.split(" ");
				
				chunk = new Chunk(
						ary[C_ID]
						,ary[C_LINK]
						,ary[C_REL]
						,ary[C_SCORE]
						,ary[C_HEAD]
						,ary[C_FUNC]);
			} else if(line.contains("</chunk>")) {
				chunk.setChildTokenList(tokenList);
				chunkList.add(chunk);
				
				chunk = null;
				tokenList = new ArrayList<Token>();
			} else if (chunk != null){
				String[] ary = line.split(" ");
				Token token = new Token(
						chunkList.size()
						,ary[T_ID]
						,ary[T_READ]
						,ary[T_BASE]
						,ary[T_POS]
						,ary[T_CTYPE]
						,ary[T_CFORM]
						,ary[T_NE]
				);
				tokenList.add(token);
			}
		}
		return chunkList;
	}

	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

	public String getPlainText() {
		return plainText;
	}

	public void setAnalyzed(List<String> analyzed) {
		this.analyzed = analyzed;
	}

	public List<String> getAnalyzed() {
		return analyzed;
	}

	public void setChunkList(List<Chunk> chunkList) {
		this.chunkList = chunkList;
	}

	public List<Chunk> getChunkList() {
		return chunkList;
	}
	
}
