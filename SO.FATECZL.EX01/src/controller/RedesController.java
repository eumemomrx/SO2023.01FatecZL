package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	public RedesController() {
		super();
	}
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	public void ip() {
		String ad = "";
		Process p = null;
		if (os().contains("Windows")) {
			try {
				p = Runtime.getRuntime().exec("IPCONFIG");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				p = Runtime.getRuntime().exec("IFCONFIG");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				if (linha.contains("Adaptador")) {
					ad = linha;
				}
				if (linha.contains("IPv4")) {
					System.out.println(ad);
					System.out.println(linha);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void ping() {
		String process;
		if (os().contains("Windows")) {
			process = "PING -4 -n 10 www.google.com.br";
		} else {
			process = "PING -4 -c 10 www.google.com.br";
		}
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				if (linha.contains("dia")) {
					String media[] = linha.split(",");
					System.out.println(media[2]);
				}
				linha = buffer.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}