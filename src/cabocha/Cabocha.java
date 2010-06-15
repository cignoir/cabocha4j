package cabocha;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.InputStreamThread;

/**
 * 
 * @author noire722
 * 
 */
public class Cabocha {
	String cabochaPath;
	List<String> exeResult;

	/**
	 * Constructor
	 * 
	 * @param cabochaPath
	 */
	public Cabocha(String cabochaPath, List<String> params) {
		this.cabochaPath = cabochaPath;
		exeResult = printOutput(execute(new ArrayList<String>()));
	}

	// TODO:
	public static void main(String[] args) {
		Cabocha cabocha = new Cabocha(args[0], new ArrayList<String>());
		if(cabocha.exeResult.size() == 0) {
			return;
		}
		
		
	}

	/**
	 * 
	 * @param params
	 * @return
	 */
	Process execute(List<String> params) {
		params.add(0, cabochaPath);
		ProcessBuilder pBuilder = new ProcessBuilder(params);
		Process process = null;
		try {
			process = pBuilder.start();
			System.out.println("start:" + cabochaPath);
		} catch (IOException e) {
			System.out.println("ERROR- cannot start the process:" + cabochaPath);
		}
		return process;
	}

	/**
	 * 
	 * @param process
	 * @return List<String>
	 */
	List<String> printOutput(Process process) {
		InputStreamThread it = new InputStreamThread(process.getInputStream());
		InputStreamThread et = new InputStreamThread(process.getErrorStream());
		it.start();
		et.start();

		try {
			process.waitFor();
			it.join();
			et.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("quit：" + process.exitValue());

		ArrayList<String> result = new ArrayList<String>();
		for (String s : it.getStringList()) {
			result.add(s);
			System.out.println(s);
		}
		for (String s : et.getStringList()) {
			System.err.println(s);
		}
		return result;
	}
}
