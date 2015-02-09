package com.makiftasova.java.utils.random;

import java.util.Random;

public class RandomGenerator {
	private int min;
	private int max;
	private Random random = new Random();

	public RandomGenerator(final int max) {
		this(0, max);
	}

	public RandomGenerator(final int min, final int max) {
		this.min = min;
		this.max = max;
		random = new Random();
	}
	
	public RandomGenerator(final int max, final long seed) {
		this(0, max, seed);
	}
	
	public RandomGenerator(final int min, final int max, final long seed) {
		this.min = min;
		this.max = max;
		random = new Random(seed);
	}
	
	public RandomGenerator setMax(final int max) {
		this.max = max;
		return this;
	}
	
	public RandomGenerator setMin(final int min) {
		this.min = min;
		return this;
	}
	
	public int getMax(){
		return this.max;
	}
	
	public int getMin(){
		return this.min;
	}
	
	public int generate(){
		return this.random.nextInt(this.max - this.min) + this.min;
	}

}
