package enums;

/**
 * 
 * @author noire722
 *
 */
public enum ChunkRelDiv {
	D("D"), O("O"), NONE("");
	
	String name;
	
	ChunkRelDiv(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static ChunkRelDiv getInstance(String rel) {
		ChunkRelDiv div = null;
		if(rel.equals("D")) {
			div = ChunkRelDiv.D;
		} else if(rel.equals("O")) {
			div = ChunkRelDiv.O;
		} else {
			div = ChunkRelDiv.NONE;
		}
		return div;
	}
}
