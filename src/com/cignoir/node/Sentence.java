package com.cignoir.node;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cignoir.utils.RegexParser;


/**
 * Sentenceは一文を表現する。
 * 複数のChunkからSentenceは構成される。
 *
 * @author noire722
 */
public class Sentence {
	private String plainText;
	private List<String> analyzed;
	private List<Chunk> chunks;
	
	private static final int C_ID = 1;
	private static final int C_LINK = 2;
	private static final int C_REL = 3;
	private static final int C_SCORE = 4;
	private static final int C_HEAD = 5;
	private static final int C_FUNC = 6;
	private static final int T_ID = 1;
	private static final int T_READ = 2;
//	private static final int T_BASE = 3;
	private static final int T_POS = 4;
	private static final int T_CTYPE = 5;
	private static final int T_CFORM = 6;
	private static final int T_NE = 7;
	
	/**
	 * Constructor
	 * 
	 * @param plainText
	 * @param analyzed
	 */
	public Sentence(String plainText, List<String> analyzed){
		setPlainText(plainText);
		setAnalyzed(analyzed);
		chunks = createChunks();		
	}

	/**
	 * CaboChaの係り受け解析の結果から
	 * Chunkオブジェクトを生成し、取得する。
	 * 
	 * @return List<Chunk>
	 */
	private List<Chunk> createChunks() {
		List<Chunk> chunks = new ArrayList<Chunk>();
		List<Token> tokens = new ArrayList<Token>();
		
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
				chunk.setTokens(tokens);
				chunks.add(chunk);
				
				chunk = null;
				tokens = new ArrayList<Token>();
			} else if (chunk != null){
				String[] ary = line.split(" ");
				Token token = new Token(
						chunks.size()
						,ary[T_ID]
						,ary[T_READ]
						/*
						 * CaboCha自体にバグがあり、baseを取得できない場合がある(詳細不明)。
						 * そのためbaseだけは元の文章を元に生成する。
						 */
						,RegexParser.removeTags(line)
						,ary[T_POS]
						,ary[T_CTYPE]
						,ary[T_CFORM]
						,ary[T_NE]
				);
				tokens.add(token);
			}
		}
		return chunks;
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

	public void setChunks(List<Chunk> chunks) {
		this.chunks = chunks;
	}

	public List<Chunk> getChunks() {
		return chunks;
	}
	
}
