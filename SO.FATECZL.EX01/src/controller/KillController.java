package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {
	public KillController() {
		super();
	}

	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}

	public void listaProcessos() {
		Process p = null;
		if (os().contains("Windows")) {
			try {
				p = Runtime.getRuntime().exec("TASKLIST /FO TABLE");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				p = Runtime.getRuntime().exec("ps -ef");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha;

			linha = buffer.readLine();

			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mataPid(String pid) {
		String cmdPid;
		if (os().contains("Windows")) {
			cmdPid = "TASKKILL /PID";
		} else {
			cmdPid = "kill -9";
		}
		StringBuffer buffer = new StringBuffer();
		Process p = null;
		try {
			buffer.append(cmdPid);
			buffer.append(" ");
			buffer.append(pid);
			p = Runtime.getRuntime().exec(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(buffer);
	}

	public void mataNome(String nome) {
		String cmdNome;
		if (os().contains("Windows")) {
			cmdNome = "TASKKILL /IM";
		} else {
			cmdNome = "pkill -f";
		}
		StringBuffer buffer = new StringBuffer();
		Process p = null;
		try {
			buffer.append(cmdNome);
			buffer.append(" ");
			buffer.append(nome);
			p = Runtime.getRuntime().exec(buffer.toString());
		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println(buffer);
	}
}
