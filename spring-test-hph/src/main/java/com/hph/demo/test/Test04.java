package com.hph.demo.test;

import com.hph.demo.inject.C;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class Test04 implements Runnable{

	static CountDownLatch countDownLatch = new CountDownLatch(3);

	public static void main(String[] args) throws InterruptedException {

		Test04 test04 = new Test04();
		for(int n = 0; n < 5; n++) {
			new Thread(test04).start();
			Thread.sleep(1);
		}
		Thread.sleep(5000);
		for(int n = 0; n < 5; n++) {
			countDownLatch.countDown();
		}
	}

	@Override
	public void run() {

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("当前线程是：" + Thread.currentThread().getName());
	}
}
