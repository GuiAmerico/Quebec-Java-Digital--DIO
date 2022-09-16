package com.primeiroprograma.calculadora;

import java.util.Scanner;

public class Calculadora {

	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		int a, b;
		
		System.out.println("Digite o 1º valor:");
		a = entrada.nextInt();
		System.out.println("Digite o 2º valor:");
		b = entrada.nextInt();
		
		double soma = soma(a,b);
		double subtracao = subtracao(a,b);
		double multiplicacao = multiplicacao(a,b); 
		double divisao = divisao(a,b);
		
		System.out.println("Soma: " + soma);
		System.out.println("Subtração: " + subtracao);
		System.out.println("Multiplicação: " + multiplicacao);
		System.out.println("Divisão: " + divisao);
	}
	
	public static int soma(int a, int b) {
		return a + b;
	}
	public static int subtracao(int a, int b) {
		return a - b;
	}
	public static double divisao(int a, int b) {
		return a / b;
	}
	public static double multiplicacao(int a, int b) {
		return a *  b;
	}

}
