package view;

import javax.swing.JOptionPane;
import controller.KillController;
import controller.RedesController;

public class Main {
	public static void main(String[] args) {
		RedesController rc = new RedesController();
		KillController kc = new KillController();
		int seletor = 0;
		while (seletor != 9) {
			seletor = Integer.parseInt(JOptionPane.showInputDialog(
					"O que deseja fazer: \n 1 - Ver o IPv4 \n 2 - Teste de Ping \n 3 - Lista de Processos \n 4 - Matar processo por PID \n 5 - Matar processo por nome \n 9 - Encerrar"));
			switch (seletor) {
			case 1:
				rc.ip();
				break;

			case 2:
				rc.ping();
				break;
			case 3:
				kc.listaProcessos();
				break;
			case 4:
				String pid = JOptionPane.showInputDialog("Qual o PID do processo a ser encerrado");
				kc.mataPid(pid);
				break;
			case 5:
				String nome = JOptionPane.showInputDialog("Qual o nome do processo a ser encerrado");
				kc.mataNome(nome);
				break;
			case 9:
				break;
			default:
				break;
			}
		}
	}
}
