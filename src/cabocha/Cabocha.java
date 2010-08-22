package cabocha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import node.Chunk;
import node.Sentence;
import node.Token;

/**
 * 
 * @author noire722
 * 
 */
public class Cabocha {

	private static final String SHIFT_JIS = "Shift-JIS";
	private static final String OPTION_F3 = "-f3";

	private String cabochaPath;

	/**
	 * example: "C:\\Program Files\\CaboCha\\bin\\cabocha.exe"
	 * 
	 * @param absolutePathOfCabocha
	 */
	public Cabocha(String absolutePathOfCabocha) {
		setCabochaPath(absolutePathOfCabocha);
	}

	/**
	 * for test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("invalid argument.");
		}

		Cabocha cabocha = new Cabocha(args[0]);
		
		try {
			for (int i = 1; i < args.length; i++) {
				Sentence sentence = cabocha.execute(args[i]);
				List<Chunk> chunkList = sentence.getChunkList();
				for (Chunk chunk : chunkList) {
					List<Token> tokens = chunk.getChildTokenList();
					for (Token token : tokens) {
						System.out.print(token.getBase());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * execute the process that is specified in constructor's argument.
	 * 
	 * @param targetToAnalyze
	 * @return List<String>
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public Sentence execute(String targetToAnalyze) throws IOException,
			InterruptedException {
		ProcessBuilder pb = new ProcessBuilder(cabochaPath, OPTION_F3);
		Process process = pb.start();
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(process
				.getOutputStream(), SHIFT_JIS);
		outputStreamWriter.write(targetToAnalyze);
		outputStreamWriter.close();

		InputStream is = process.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,
				SHIFT_JIS));
		String line;
		List<String> result = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			result.add(line);
			System.out.println(line);
		}
		process.destroy();
		process.waitFor();

		return new Sentence(targetToAnalyze, result);
	}

	public void setCabochaPath(String cabochaPath) {
		this.cabochaPath = cabochaPath;
	}

}
