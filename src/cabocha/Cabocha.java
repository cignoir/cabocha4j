package cabocha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import node.Sentense;

/**
 * 
 * @author noire722
 * 
 */
public class Cabocha {

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
	 * execute the process that is specified in constructor's argument.
	 * 
	 * @param targetToAnalyze
	 * @return List<String>
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ParsingException
	 */
	public Sentense execute(String targetToAnalyze) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder(cabochaPath, "-f3");
		Process process = pb.start();
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(process.getOutputStream(), "Shift-JIS");
		outputStreamWriter.write(targetToAnalyze);
		outputStreamWriter.close();

		InputStream is = process.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "Shift-JIS"));
		String line;
		List<String> result = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			result.add(line);
			System.out.println(line);
		}
		process.destroy();
		process.waitFor();

		return new Sentense(targetToAnalyze, result);
	}

	public void setCabochaPath(String cabochaPath) {
		this.cabochaPath = cabochaPath;
	}

}
