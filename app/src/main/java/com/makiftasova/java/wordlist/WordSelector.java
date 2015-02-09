package com.makiftasova.java.wordlist;

import com.makiftasova.java.utils.random.RandomGenerator;

public class WordSelector {
	private final String[] wordList;
	private RandomGenerator randomGenerator;
	private int wordIndex;

	public WordSelector(final String[] wordList) {
		// TODO Auto-generated constructor stub
		this.wordIndex = 0;
		this.wordList = wordList;
		this.randomGenerator = new RandomGenerator(wordList.length - 1);
	}

	/**
	 * 
	 * Returns word from word list at given {@code index} <br>
	 * 
	 * If {@code index} is lesser than zero first word in word list will be
	 * returned<br>
	 * If {@code index} is greater or equal to word count, last word in the word
	 * list will be returned<br>
	 * 
	 * @param index
	 *            index of requested word
	 * @return word at given index
	 */
	public String getWordAt(int index) {
		if (index >= wordList.length) {
			index = (wordList.length - 1);
		}

		if (index < 0) {
			index = 0;
		}

		return wordList[index];
	}

	public int getCurrentWordIndex() {
		return this.wordIndex;
	}

	public int getWordCount() {
		return wordList.length;
	}

	public String getCurrentWord() {
		return this.getWordAt(wordIndex);
	}

	public String getNextWord() {
		return this.getWordAt(++wordIndex);
	}

	public String getRandomWord() {
		wordIndex = randomGenerator.generate();
		return this.getCurrentWord();
	}

	public void test() {
		System.out.println(getCurrentWord());

		System.out.println("next words: " + getNextWord() + " " + getNextWord()
				+ " " + getNextWord());

		System.out.println("randoms:" + getRandomWord() + " " + getRandomWord()
				+ " " + getRandomWord());

		System.out.println("max words: " + getWordCount());
		System.out.println("last word: " + getWordAt(getWordCount() - 1));
	}

}
