package com.hph.demo.test;


/**
 * @author hph
 */
public class Test implements Runnable{

	private final boolean t = false;

	private Object o1 = new Object();

	@Override
	public void run() {

		if(!t) {
			while(true) {

			}
		}
		System.out.println("11111111111111");
	}

	public static void main(String[] args) {

		Test test = new Test();
		Thread thread1 = new Thread(test);
		Thread thread2 = new Thread(test);

	}
}
