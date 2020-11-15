package com.javaws.threads.cli;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreadedPrimes implements Primes {

	private final int nbThreads;
	
	
	public ThreadedPrimes(int nbThreads) {
		super();
		this.nbThreads = nbThreads;
	}

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
	
	private List<Long> calculatePrimes(long from, long to) {
		if(from > to) {
			return new ArrayList<>();
		}
		List<Long> primes = new ArrayList<Long>();
		for(long i = from; i < to ; i++) {
			if(isPrime(i)) {
				primes.add(i);
			}
		}
		
		for(long i = 3; i <2; i++) {
			
		}
		return primes;
	}

	
	@Override
	public List<Long> calculate(long number) {

		Set<Thread> threads = new HashSet<>();
		
		long d = number / nbThreads;
		long r = number % nbThreads;
		
		List<Long> primes = calculatePrimes(2, r -1);
		
		for(long i = r; i < number; i+=d) {
			long current = i;
			Thread t = new Thread(() -> {
				// Synchronize using this monitor
				// but this is like sequential
				// no gain over seq primes calculation
				synchronized (this) {
					primes.addAll(calculatePrimes(current, current + d));
				}
			});
			
			threads.add(t);
			t.start();
		}
		
		for(Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}
		
		return primes;
	}
}
