package com.cignoir.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author noire722
 *
 */
public class InputStreamThread extends Thread {

	private BufferedReader br;
	private List<String> list = new ArrayList<String>();

	/**
	 * Constructor
	 * 
	 * @param is
	 */
	public InputStreamThread(InputStream is) {
		br = new BufferedReader(new InputStreamReader(is));
	}

	/**
	 * Constructor
	 * 
	 * @param is
	 * @param charset
	 */
	public InputStreamThread(InputStream is, String charset) {
		try {
			br = new BufferedReader(new InputStreamReader(is, charset));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void run() {
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> getStringList() {
		return list;
	}
}