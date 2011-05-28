package com.cignoir.cabocha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.cignoir.enums.PosDiv;
import com.cignoir.node.Chunk;
import com.cignoir.node.Sentence;
import com.cignoir.node.Token;
import com.cignoir.utils.CabochaUtils;




/**
 * 
 * 
 * @author noire722
 */
public class Cabocha {

	private static final String SHIFT_JIS = "Shift-JIS";
	private static final String OPTION_F3 = "-f3";

	private String cabochaPath;

	/**
	 * Constructor
	 * example: "C:\\Program Files\\CaboCha\\bin\\cabocha.exe"
	 * 
	 * @param absolutePathOfCabocha
	 */
	public Cabocha(String absolutePathOfCabocha) {
		setCabochaPath(absolutePathOfCabocha);
	}

	/**
	 * サンプルコードを記述。
	 * ライブラリとしてcabocha4jを使用する場合は無視してよい。
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
				List<Chunk> chunkList = sentence.getChunks();
				// Sample 1
				System.out.println("▼品詞を表示させる");
				for (Chunk chunk : chunkList) {
					List<Token> tokens = chunk.getTokens();
					for (Token token : tokens) {
						System.out.println(token.getBase() + ": " + token.getPos());
					}
				}
				System.out.println();

				// Sample 2
				System.out.println("▼名詞を連結させる");
				chunkList = CabochaUtils.chainPos(chunkList, PosDiv.NOUN);
				for (Chunk chunk : chunkList) {
					List<Token> tokens = chunk.getTokens();
					for (Token token : tokens) {
						System.out.println(token.getBase() + ": " + token.getPos());
					}
				}
				System.out.println();
				
				// Sample 3
				System.out.println("▼「魅力的である」に係っているものを抽出");
				for (Chunk chunk : chunkList) {
					if(chunk.getBase().contains("魅力的である")) {
						List<Chunk> referencedBy = chunk.getReferencedBy(chunkList);
						for(Chunk refs : referencedBy) {
							System.out.println(refs.getBase());
						}
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 引数で指定した文字列に対してCaboCha.exeを実行する。
	 * 解析結果からSenetenceを生成し、取得する。
	 * execute the process that is specified in constructor's argument.
	 * 
	 * @param targetToAnalyze
	 * @return Sentence
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

	/**
	 * CaboCha.exeのパスをセットする。
	 * 
	 * @param cabochaPath
	 */
	public void setCabochaPath(String cabochaPath) {
		this.cabochaPath = cabochaPath;
	}

}
