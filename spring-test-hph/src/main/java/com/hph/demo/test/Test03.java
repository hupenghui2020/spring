package com.hph.demo.test;

import java.util.concurrent.Semaphore;

public class Test03 {

	public static void main(String[] args) throws InterruptedException {

		Semaphore semaphore = new Semaphore(1);
		// 主线获取所有的信号量
		semaphore.acquire(1);
		Thread.sleep(1);
		for(int n = 0; n < 5; n++) {
			Thread thread = new Thread(() ->{
				try {
					semaphore.acquire();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					semaphore.release();
				}
			}, n + "");
			Thread.sleep(1000);
			thread.start();
		}
		// 子线程全部堵塞后进行信号量的释放
		semaphore.release(1);
	}
}
