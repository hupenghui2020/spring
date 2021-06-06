package com.hph.demo.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 10499
 */
public class Test05 implements Runnable{

	private static int count = 0;

	private static CyclicBarrier cyclicBarrier;

	public static void main(String[] args) throws InterruptedException {

		cyclicBarrier = new CyclicBarrier(3, () -> {
			System.out.println("第"+ (++count) +"开始出发。。。。");
		});
		Test05 test05 = new Test05();
		for(int n = 0; n < 12; n++) {
			new Thread(test05, "hph" + n).start();
			Thread.sleep(1);
		}

		AtomicInteger atomicInteger = new AtomicInteger(4);
		atomicInteger.addAndGet(7);
	}

	@Override
	public void run() {

		try {
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("我是第" + count + "组的" + Thread.currentThread().getName());
	}
}
