package com.hph.demo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 10499
 */
public class Test1 {

	private Object object1 = new Object();

	private Object object2 = new Object();

	private static List<User> userList = new ArrayList<>();

	public static void main(String[] args) {

		new Test1().test3();
	}

	private void test01(){

		// 测试堆内存溢出，通过dump快照文件进行分析
		while (true) {
			userList.add(new User("hph", 27));
			//new User("hph", 27);
		}
	}

	private void test02 () {

		// 测试死锁，对线程的堆栈进行分析
		Thread thread1 = new Thread(() -> {

			synchronized (object1) {
				System.out.println("thread1获取object1锁...");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (object2) {
					System.out.println("thread1获取object2锁...");
				}
			}
		});

		Thread thread2 = new Thread(() -> {

			synchronized (object2) {
				System.out.println("thread2获取object2锁...");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (object1) {
					System.out.println("thread2获取object1锁...");
				}
			}
		});

		thread1.start();
		thread2.start();
	}

	private void test3() {
		while(true) {
			sum();
		}
	}

	private int sum() {

		int a = 1,b = 2,c = a + b;
		return c;
	}
}
