package models;

public class Funcionario {

	private static int incremento = 1;

	private Integer id;
	private String nome;
	private String cargo;

	public Funcionario(String nome, String cargo) {
		this.nome = nome;
		this.cargo = cargo;
		this.id = incremento++;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return "ID Func: " + id + " | Nome: " + nome + " | Cargo: " + cargo;
	}
}