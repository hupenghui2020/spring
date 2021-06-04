package com.hph.demo.test;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;

public class Test2 {

	public static void main(String[] args) throws InterruptedException {

		ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(10);

		for(int n = 0; n< 5; n++) {
			abq.put(n + "");
		}
		abq.take();
		Iterator<String> iterator = abq.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
