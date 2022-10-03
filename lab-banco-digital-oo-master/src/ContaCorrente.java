
public class ContaCorrente extends Conta {

	private double saldoDevedor;


	public ContaCorrente(Cliente cliente) {
		super(cliente);
	}
	
	public void emprestimo(double valor) {
		super.depositar(valor);
		super.saldoDevedor += valor;
	}
	
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Corrente ===");
		super.imprimirInfosComuns();
	}
	public double getSaldoDevedor() {
		return saldoDevedor;
	}
	
	public void setSaldoDevedor(double saldoDevedor) {
		this.saldoDevedor = saldoDevedor;
	}

	
	
}
