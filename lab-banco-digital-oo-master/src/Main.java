
public class Main {

	public static void main(String[] args) {
		Cliente venilton = new Cliente();
		venilton.setNome("Venilton");

		ContaCorrente cc = new ContaCorrente(venilton);
		Conta poupanca = new ContaPoupanca(venilton);

		cc.depositar(100);
		cc.transferir(100, poupanca);

		cc.imprimirExtrato();
		poupanca.imprimirExtrato();

		cc.emprestimo(200);
		cc.imprimirExtrato();

		poupanca.depositar(200);
		poupanca.transferir(200, cc);
		cc.imprimirExtrato();
		poupanca.imprimirExtrato();
	}

}
