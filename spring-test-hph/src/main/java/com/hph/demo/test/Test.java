package com.hph.demo.test;


/**
 * @author hph
 */
public class Test implements Runnable{

	private volatile int n = 0;

	private Object o1 = new Object();

	@Override
	public void run() {
		count();
	}

	public synchronized void count(){
		for (int i = 0; i < 10; i++) {
			n++;
			System.out.println(n);
		}
	}

	public static void main(String[] args) {

		Test test = new Test();
		Thread thread1 = new Thread(test);
		Thread thread2 = new Thread(test);
		thread1.start();
		thread2.start();
	}
}
