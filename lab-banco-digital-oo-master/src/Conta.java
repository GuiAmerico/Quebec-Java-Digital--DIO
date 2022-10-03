
public abstract class Conta implements IConta {

	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected double saldoDevedor;
	protected Cliente cliente;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		if (this.saldo >= valor) {

			this.saldo -= valor;
		} else {
			System.out.println("Saldo Insuficiente!");
		}
	}

	@Override
	public void depositar(double valor) {
		if (this.saldoDevedor > 0) {
			System.out.println("Débito Automatico");
			this.saldoDevedor -= valor;
			return;
		}
		this.saldo += valor;

	}

	@Override
	public void transferir(double valor, Conta contaDestino) {
		if(this.saldo >= valor) {
			this.sacar(valor);
			contaDestino.depositar(valor);
			
		}
	}

	public void transferir(double valor, ContaCorrente contaDestino) {
		this.sacar(valor);
		if (contaDestino.getSaldoDevedor() > 0) {
			contaDestino.setSaldoDevedor(contaDestino.getSaldoDevedor() - valor);
			if(contaDestino.getSaldoDevedor() < 0) {
				valor = Math.abs( contaDestino.getSaldoDevedor() );
				contaDestino.depositar(valor);
				contaDestino.setSaldoDevedor(0);
			}
		}else {
			contaDestino.depositar(valor);
			
		}
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
		System.out.println(String.format("Saldo Devedor: %.2f", this.saldoDevedor));
	}
}
