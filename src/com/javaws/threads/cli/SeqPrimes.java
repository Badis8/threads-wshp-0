package com.javaws.threads.cli;

import java.util.ArrayList;
import java.util.List;

public class SeqPrimes implements Primes {

	public boolean isPrime(long number) {
		if (number % 2 == 0) {
			return false;
		}
		for (int i = 3; i * i <= number; i += 2) {
			if (number % i == 0)
				return false;
		}
		return true;
	}
	
	
	@Override
	public List<Long> calculate(long number) {
		List<Long> primes = new ArrayList<Long>();
		
		for(long i = number; i > 1; i--) {
			if(isPrime(i)) {
				primes.add(i);
			}
		}
		
		return primes;
	}

}
