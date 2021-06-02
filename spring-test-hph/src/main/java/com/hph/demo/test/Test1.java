package com.hph.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 {

	private static ReentrantLock lock = new ReentrantLock(true);

	public static void main(String[] args) throws InterruptedException {

		List<Thread> threadList = new ArrayList<>();
		Task task = new Task();
		for(int n = 0; n < 10; n++) {
			Thread thread = new Thread(task, String.valueOf(n));
			threadList.add(thread);
			thread.start();
		}
		Thread.sleep(1000);
		threadList.get(3).interrupt();
		System.out.println("线程中断了！！！！");
	}

	static class Task implements Runnable {

		@Override
		public void run() {
			lock.lock();
			try {
				System.out.println("线程"+ Thread.currentThread().getName() +"进来了!!!");
				while(true) {
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}
