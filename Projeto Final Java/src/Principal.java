import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import models.Cliente;
import models.Pedido;
import models.Funcionario;
import javax.swing.Icon;

public class Principal {

	static ArrayList<Pedido> listaPedidos = new ArrayList<>();
	static ArrayList<Cliente> listaClientes = new ArrayList<>();
	static ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
	// 1. Carrega a imagem com a extensão correta (ex: .png)
	static ImageIcon faviconOriginal = new ImageIcon("C:\\Users\\lucas_fernandes155\\Downloads\\PrintGestFavicon.png");

	// 2. Redimensiona para o tamanho ideal de ícone (32x32 pixels) para não quebrar o layout
	static Image imagemRedimensionada = faviconOriginal.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	static Icon favicon = new ImageIcon(imagemRedimensionada);
	
	public static void main(String[] args) {

		String[] opcoesMenuInicio = { "Pedidos", "Clientes", "Funcionários", "Sair" };
		String[] opcoesMenu = { "Adicionar", "Listar", "Atualizar", "Deletar", "Voltar" };
		String[] nomesPapel = { "Couchê Brilho ADC5420", "Couchê Brilho ADC6000", "BOPP Branco", "BOPP Transparente",
				"BOPP Metalizado", "Vinil Fasson Branco", "Vinil Fasson Transparente" };

		double[][] tabelaPrecos = { { 6.4, 6.1, 5.7, 5.0, 4.3, 3.8, 3.5, 3.2 },
				{ 6.8, 6.5, 6.1, 5.3, 4.6, 4.0, 3.7, 3.4 }, { 9.5, 9.1, 8.5, 7.0, 6.0, 5.4, 4.8, 4.3 },
				{ 10.8, 10.3, 9.7, 7.9, 6.9, 6.1, 5.4, 4.9 }, { 11.2, 10.7, 10.0, 8.2, 7.1, 6.3, 5.6, 5.1 },
				{ 15.6, 14.9, 13.9, 11.4, 9.9, 8.8, 7.8, 7.1 }, { 15.6, 14.9, 13.9, 11.4, 9.9, 8.8, 7.8, 7.1 } };

		boolean rodarSistema = true;

		do {
			int opcaoMenuInicio = JOptionPane.showOptionDialog(null, "Escolha qual CRUD você deseja acessar",
					"Menu Principal", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, favicon, opcoesMenuInicio,
					opcoesMenuInicio[0]); 

			switch (opcaoMenuInicio) {
			case 0:
				// ================= CRUD DOS PEDIDOS =================
				boolean rodarSistemaPedido = true;
				do {
					int opcaoMenuPedido = JOptionPane.showOptionDialog(null, "Menu Pedidos - Escolha uma opção",
							"Pedidos", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, favicon, opcoesMenu,
							opcoesMenu[0]);
					switch (opcaoMenuPedido) {
					case 0:
						adicionarPedidos(nomesPapel, tabelaPrecos);
						break;
					case 1:
						listarPedidos();
						break;
					case 2:
						atualizarPedidos(nomesPapel, tabelaPrecos);
						break;
					case 3:
						excluirPedidos();
						break;
					case 4:
					case -1:
						rodarSistemaPedido = false;
					}
				} while (rodarSistemaPedido);
				break;

			case 1:
				// ================= CRUD DOS CLIENTES =================
				boolean rodarSistemaCliente = true;
				do {
					int opcaoMenuCliente = JOptionPane.showOptionDialog(null, "Menu Clientes - Escolha uma opção",
							"Clientes", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, favicon, opcoesMenu,
							opcoesMenu[0]);
					switch (opcaoMenuCliente) {
					case 0:
						adicionarCliente();
						break;
					case 1:
						listarClientes();
						break;
					case 2:
						atualizarClientes();
						break;
					case 3:
						excluirClientes();
						break;
					case 4:
					case -1:
						rodarSistemaCliente = false;
						break;
					}
				} while (rodarSistemaCliente);
				break;

			case 2:
				// ================= CRUD DOS FUNCIONÁRIOS =================
				boolean rodarSistemaFuncionario = true;
				do {
					int opcaoMenuFuncionario = JOptionPane.showOptionDialog(null,
							"Menu Funcionários - Escolha uma opção", "Funcionários", JOptionPane.DEFAULT_OPTION,
							JOptionPane.QUESTION_MESSAGE, favicon, opcoesMenu, opcoesMenu[0]);
					switch (opcaoMenuFuncionario) {
					case 0:
						adicionarFuncionario();
						break;
					case 1:
						listarFuncionarios();
						break;
					case 2:
						atualizarFuncionarios();
						break;
					case 3:
						excluirFuncionarios();
						break;
					case 4:
					case -1:
						rodarSistemaFuncionario = false;
						break;
					}
				} while (rodarSistemaFuncionario);
				break;

			case 3:
			case -1:
				rodarSistema = false;
				break;
			}
		} while (rodarSistema);

		JOptionPane.showMessageDialog(null, "Sistema encerrado.");
	}

	// ================= FUNÇÕES DOS PEDIDOS =================
	
	public static void adicionarPedidos(String[] nomesPapel, double[][] tabelaPrecos) {
		if (listaClientes.isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Não é possível criar um pedido sem antes cadastrar pelo menos um cliente!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Seleção do Cliente para o Pedido
		String[] nomesClientes = new String[listaClientes.size()];
		for (int i = 0; i < listaClientes.size(); i++) {
			nomesClientes[i] = "ID: " + listaClientes.get(i).getId() + " - " + listaClientes.get(i).getNome();
		}
		String clienteEscolhidoStr = (String) JOptionPane.showInputDialog(null, "Selecione o cliente para este pedido:",
				"Clientes Disponíveis", JOptionPane.PLAIN_MESSAGE, favicon, nomesClientes, nomesClientes[0]);
		if (clienteEscolhidoStr == null)
			return;

		int idCliente = Integer.parseInt(clienteEscolhidoStr.split(" ")[1]);
		Cliente clienteEscolhido = null;
		for (Cliente c : listaClientes) {
			if (c.getId() == idCliente) {
				clienteEscolhido = c;
				break;
			}
		}

		String papelEscolhido = (String) JOptionPane.showInputDialog(null, "Selecione o tipo de papel",
				"Tipos de Papel", JOptionPane.PLAIN_MESSAGE, favicon, nomesPapel, nomesPapel[0]);
		if (papelEscolhido == null)
			return;

		int papelIndex = -1;
		for (int i = 0; i < nomesPapel.length; i++) {
			if (nomesPapel[i].equals(papelEscolhido)) {
				papelIndex = i;
				break;
			}
		}

		try {
			Double alturaRotulo = Double
					.parseDouble(JOptionPane.showInputDialog("Qual a altura do rótulo?").replace(",", "."));
			Double larguraRotulo = Double
					.parseDouble(JOptionPane.showInputDialog("Qual a largura do rótulo?").replace(",", "."));
			Integer qntdRotulos = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de rótulos?"));

			double rotulosPorFolha = calcularMelhorAproveitamento(alturaRotulo, larguraRotulo);
			double qntdFolha = Math.ceil(qntdRotulos / rotulosPorFolha);
			double precoFinal = calcularPreco(tabelaPrecos[papelIndex], qntdFolha);

			listaPedidos.add(new Pedido(nomesPapel[papelIndex], alturaRotulo, larguraRotulo, qntdRotulos, qntdFolha,
					precoFinal, clienteEscolhido));
			JOptionPane.showMessageDialog(null, "Pedido adicionado com sucesso!");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Entrada inválida ou operação cancelada.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void listarPedidos() {
		StringBuilder lista = new StringBuilder();
		for (Pedido p : listaPedidos) {
			lista.append(p.resumoPedido()).append("\n");
		}

		if (lista.length() == 0) {
			JOptionPane.showMessageDialog(null, "Nenhum orçamento cadastrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		exibirEmScroll(lista.toString(), "Listagem de Pedidos");
	}

	public static void atualizarPedidos(String[] nomesPapel, double[][] tabelaPrecos) {
		if (listaPedidos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhum pedido cadastrado para alterar.");
			return;
		}
		try {
			int idAlterar = Integer.parseInt(JOptionPane.showInputDialog("Insira o ID do pedido que deseja alterar:"));
			Pedido pedidoAlterar = null;

			for (Pedido p : listaPedidos) {
				if (p.getId().equals(idAlterar)) {
					pedidoAlterar = p;
					break;
				}
			}

			if (pedidoAlterar == null) {
				JOptionPane.showMessageDialog(null, "Pedido com ID " + idAlterar + " não encontrado.");
				return;
			}

			String[] nomesClientes = new String[listaClientes.size()];
			for (int i = 0; i < listaClientes.size(); i++) {
				nomesClientes[i] = "ID: " + listaClientes.get(i).getId() + " - " + listaClientes.get(i).getNome();
			}
			
			String clienteAlteradoStr= (String) JOptionPane.showInputDialog(null, "Selecione o cliente para este pedido:",
					"Clientes Disponíveis", JOptionPane.PLAIN_MESSAGE, favicon, nomesClientes, nomesClientes[0]);
			if (clienteAlteradoStr == null)
				return;

			int idClienteAlterado = Integer.parseInt(clienteAlteradoStr.split(" ")[1]);
			Cliente clienteAlterado = null;
			for (Cliente c : listaClientes) {
				if (c.getId() == idClienteAlterado) {
					clienteAlterado = c;
					break;
				}
			}
			
			String papelAlterado = (String) JOptionPane.showInputDialog(null, "Selecione o tipo de papel",
					"Tipos de Papel", JOptionPane.PLAIN_MESSAGE, favicon, nomesPapel, pedidoAlterar.getNomePapel());
			if (papelAlterado == null)
				return;

			int papelIndex = -1;
			for (int j = 0; j < nomesPapel.length; j++) {
				if (nomesPapel[j].equals(papelAlterado)) {
					papelIndex = j;
					break;
				}
			}

			Double alturaRotuloAlterado = Double.parseDouble(JOptionPane
					.showInputDialog("Qual a altura do rótulo?", pedidoAlterar.getAlturaRotulo()).replace(",", "."));
			Double larguraRotuloAlterado = Double.parseDouble(JOptionPane
					.showInputDialog("Qual a largura do rótulo?", pedidoAlterar.getLarguraRotulo()).replace(",", "."));
			Integer qntdRotulosAlterado = Integer.parseInt(
					JOptionPane.showInputDialog("Qual a quantidade de rótulos?", pedidoAlterar.getQntdRotulos()));

			double rotulosPorFolhaAlterado = calcularMelhorAproveitamento(alturaRotuloAlterado, larguraRotuloAlterado);
			double qntdFolhaAlterado = Math.ceil(qntdRotulosAlterado / rotulosPorFolhaAlterado);
			double precoFinalAlterado = calcularPreco(tabelaPrecos[papelIndex], qntdFolhaAlterado);

			pedidoAlterar.setCliente(clienteAlterado);
			pedidoAlterar.setNomePapel(nomesPapel[papelIndex]);
			pedidoAlterar.setAlturaRotulo(alturaRotuloAlterado);
			pedidoAlterar.setLarguraRotulo(larguraRotuloAlterado);
			pedidoAlterar.setQntdRotulos(qntdRotulosAlterado);
			pedidoAlterar.setQntdFolha(qntdFolhaAlterado);
			pedidoAlterar.setValor(precoFinalAlterado);

			JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Entrada inválida ou operação cancelada.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void excluirPedidos() {
		try {
			int idExcluir = Integer.parseInt(JOptionPane.showInputDialog("Insira o ID do pedido que deseja excluir:"));
			boolean removido = listaPedidos.removeIf(p -> p.getId().equals(idExcluir));

			if (removido) {
				JOptionPane.showMessageDialog(null, "Pedido excluído com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Pedido não encontrado.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Operação inválida ou cancelada.");
		}
	}

	// ================= FUNÇÕES DOS CLIENTES =================

	public static void adicionarCliente() {
		try {
			String nome = JOptionPane.showInputDialog("Nome do Cliente:");
			if (nome == null || nome.trim().isEmpty())
				return;
			String telefone = JOptionPane.showInputDialog("Telefone do Cliente:");
			if (telefone == null)
				return;

			listaClientes.add(new Cliente(nome, telefone));
			JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao adicionar cliente.");
		}
	}

	public static void listarClientes() {
		StringBuilder lista = new StringBuilder();
		for (Cliente c : listaClientes) {
			lista.append("ID: ").append(c.getId()).append(" | ").append(c).append("\n");
		}
		if (lista.length() == 0) {
			JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
			return;
		}
		exibirEmScroll(lista.toString(), "Listagem de Clientes");
	}

	public static void atualizarClientes() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente que deseja atualizar:"));
			Cliente cliente = null;
			for (Cliente c : listaClientes) {
				if (c.getId().equals(id)) {
					cliente = c;
					break;
				}
			}
			if (cliente == null) {
				JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
				return;
			}
			String novoNome = JOptionPane.showInputDialog("Novo Nome:", cliente.getNome());
			String novoTelefone = JOptionPane.showInputDialog("Novo Telefone:", cliente.getTelefone());

			if (novoNome != null && !novoNome.trim().isEmpty())
				cliente.setNome(novoNome);
			if (novoTelefone != null)
				cliente.setTelefone(novoTelefone);

			JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Operação cancelada ou dados inválidos.");
		}
	}

	public static void excluirClientes() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente a ser excluído:"));
			boolean removido = listaClientes.removeIf(c -> c.getId().equals(id));

			if (removido) {
				// Remove também os pedidos atrelados a esse cliente para não quebrar o sistema
				listaPedidos.removeIf(p -> p.getCliente().getId().equals(id));
				JOptionPane.showMessageDialog(null, "Cliente e seus pedidos foram removidos com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Operação inválida.");
		}
	}

	// ================= FUNÇÕES DOS FUNCIONÁRIOS =================

	public static void adicionarFuncionario() {
		try {
			String nome = JOptionPane.showInputDialog("Nome do Funcionário:");
			if (nome == null || nome.trim().isEmpty())
				return;
			String cargo = JOptionPane.showInputDialog("Cargo do Funcionário:");
			if (cargo == null)
				return;

			listaFuncionarios.add(new Funcionario(nome, cargo));
			JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário.");
		}
	}

	public static void listarFuncionarios() {
		StringBuilder lista = new StringBuilder();
		for (Funcionario f : listaFuncionarios) {
			lista.append(f).append("\n");
		}
		if (lista.length() == 0) {
			JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado.");
			return;
		}
		exibirEmScroll(lista.toString(), "Listagem de Funcionários");
	}

	public static void atualizarFuncionarios() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do funcionário que deseja atualizar:"));
			Funcionario func = null;
			for (Funcionario f : listaFuncionarios) {
				if (f.getId().equals(id)) {
					func = f;
					break;
				}
			}
			if (func == null) {
				JOptionPane.showMessageDialog(null, "Funcionário não encontrado.");
				return;
			}
			String novoNome = JOptionPane.showInputDialog("Novo Nome:", func.getNome());
			String novoCargo = JOptionPane.showInputDialog("Novo Cargo:", func.getCargo());

			if (novoNome != null && !novoNome.trim().isEmpty())
				func.setNome(novoNome);
			if (novoCargo != null)
				func.setCargo(novoCargo);

			JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Operação cancelada ou dados inválidos.");
		}
	}

	public static void excluirFuncionarios() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do funcionário a ser excluído:"));
			boolean removido = listaFuncionarios.removeIf(f -> f.getId().equals(id));

			if (removido) {
				JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Funcionário não encontrado.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Operação inválida.");
		}
	}

	// ================= FUNÇÕES AUXILIARES =================
	public static double calcularMelhorAproveitamento(double alturaRotulo, double larguraRotulo) {
		double op1 = Math.floor(46.5 / alturaRotulo) * Math.floor(31.5 / larguraRotulo);
		double op2 = Math.floor(46.5 / larguraRotulo) * Math.floor(31.5 / alturaRotulo);
		return Math.max(op1, op2);
	}

	public static double calcularPreco(double[] tabelaPrecos, double qntdFolha) {
		if (qntdFolha <= 0)
			return 0.0;
		double precoUnitario;
		if (qntdFolha <= 20)
			precoUnitario = tabelaPrecos[0];
		else if (qntdFolha <= 50)
			precoUnitario = tabelaPrecos[1];
		else if (qntdFolha <= 100)
			precoUnitario = tabelaPrecos[2];
		else if (qntdFolha <= 200)
			precoUnitario = tabelaPrecos[3];
		else if (qntdFolha <= 300)
			precoUnitario = tabelaPrecos[4];
		else if (qntdFolha <= 400)
			precoUnitario = tabelaPrecos[5];
		else if (qntdFolha <= 500)
			precoUnitario = tabelaPrecos[6];
		else
			precoUnitario = tabelaPrecos[7];

		return (precoUnitario * qntdFolha) + 10.0;
	}

	private static void exibirEmScroll(String conteudo, String titulo) {
		JTextArea textArea = new JTextArea(conteudo);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new java.awt.Dimension(650, 250));
		JOptionPane.showMessageDialog(null, scrollPane, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
}
