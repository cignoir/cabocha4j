package node;

import java.util.List;

public class Sentense {
	private int sentenseId;
	private String plainText;
	private List<String> analyzed;
	private List<Chunk> chunkList;
	
	public Sentense(int id, String plainText, List<String> analyzed) {
		setSentenseId(sentenseId);
		setPlainText(plainText);
		setAnalyzed(analyzed);
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

	public void setSentenseId(int sentenseId) {
		this.sentenseId = sentenseId;
	}

	public int getSentenseId() {
		return sentenseId;
	}
}
