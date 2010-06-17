package node;

import java.util.List;

public class Chunk {
	private int parentSentenceId;
	private List<Token> tokenList;

	public Chunk(int parentSentenceId, List<String> analyzed) {
		this.parentSentenceId = parentSentenceId;
	}
	
	public void setTokenList(List<Token> tokenList) {
		this.tokenList = tokenList;
	}

	public List<Token> getTokenList() {
		return tokenList;
	}

	public void setParentSentenceId(int parentSentenceId) {
		this.parentSentenceId = parentSentenceId;
	}

	public int getParentSentenceId() {
		return parentSentenceId;
	}
}
