package com.javaws.threads.cli;

public class Program {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		Primes pc = new ThreadedPrimes(20);
		
		long number = 9999999;
		
		System.out.println("Number of primes : " + pc.calculate(number).size());

		System.out.println("That took " + (System.currentTimeMillis() - startTime) + " milliseconds ... Kiss goodbye !");
	}
}
