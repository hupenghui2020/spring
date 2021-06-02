package com.hph.demo.test;


/**
 * @author hph
 */
public class Test implements Runnable{

	private boolean t = false;

	private final Object o1 = new Object();

	private final Object o2 = new Object();

	@Override
	public void run() {

		if(!t) {
			t = true;
			synchronized (o1) {
				try {
					Thread.sleep(3000);
					synchronized (o2) {
						System.out.println("1111111111");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else {
			synchronized (o2) {
				try {
					Thread.sleep(3000);
					synchronized (o1) {
						System.out.println("22222222222");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
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
