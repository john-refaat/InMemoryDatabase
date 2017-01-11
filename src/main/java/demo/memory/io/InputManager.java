package demo.memory.io;

import java.io.BufferedReader;
import java.io.IOException;

import demo.memory.exceptions.DatabaseRuntimeException;

public class InputManager {

	private BufferedReader br;
	
	public InputManager(BufferedReader br) {
		this.br = br;
	}
	
	public String read() {
		try {
			return br.readLine();
		} catch (IOException e) {
			throw new DatabaseRuntimeException(e);
		}
	}
	
}
