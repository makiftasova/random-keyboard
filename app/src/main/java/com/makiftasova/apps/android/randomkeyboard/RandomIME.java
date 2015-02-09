package com.makiftasova.apps.android.randomkeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

import com.makiftasova.apps.android.keyboard.WordLists;
import com.makiftasova.apps.android.keyboard.WordSelector;

public class RandomIME extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

	private KeyboardView keyboardView;
	private Keyboard keyboard;
	private boolean capslock = false;
    private WordSelector randomWords;

	@Override
	public View onCreateInputView() {
        randomWords = new WordSelector(WordLists.ENGLISH);
		keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
		//keyboard = new Keyboard(this, R.xml.qwerty);
        keyboard = new Keyboard(this, R.xml.dualkey);
		keyboardView.setKeyboard(keyboard);
		keyboardView.setOnKeyboardActionListener(this);
		return keyboardView;
	}

	@Override
	public void onPress(int primaryCode) {

	}

	@Override
	public void onRelease(int primaryCode) {

	}

	@Override
	public void onKey(int primaryCode, int[] keyCodes) {
		InputConnection inputConnection = getCurrentInputConnection();
		playClick(primaryCode);
		switch (primaryCode) {
			case Keyboard.KEYCODE_DELETE:
				inputConnection.deleteSurroundingText(1, 0);
				break;
            case Keyboard.KEYCODE_DONE:
                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                String randomWord = randomWords.getRandomWord();
                randomWord += " ";
                inputConnection.commitText(randomWord, randomWord.length() + 1);

			/*case Keyboard.KEYCODE_SHIFT:
				capslock = !capslock;
				keyboard.setShifted(capslock);
				keyboardView.invalidateAllKeys();
				break;
			case Keyboard.KEYCODE_DONE:
				inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
				break;
			default:
				char code = (char) primaryCode;
				if (Character.isLetter(code) && capslock) {
					code = Character.toUpperCase(code);
				}
				inputConnection.commitText(String.valueOf(code), 1);*/
		}
	}

	@Override
	public void onText(CharSequence text) {

	}

	@Override
	public void swipeLeft() {
		InputConnection inputConnection = getCurrentInputConnection();
		int n = 1;
		Integer prevLength = 0;
		CharSequence read;
		do {
			read = inputConnection.getTextBeforeCursor(n, 0);
			if ((null == read) || (0 == read.length()) || (Character.isSpaceChar(read.charAt(0)))
					    || (prevLength.equals(read.length()))) {
				break;
			}

			prevLength = read.length();

			++n;
		}
		while (true);

		inputConnection.deleteSurroundingText(n, 0);
	}

	@Override
	public void swipeRight() {

	}

	@Override
	public void swipeDown() {

	}

	@Override
	public void swipeUp() {

	}

	private void playClick(int keyCode) {
		AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
		switch (keyCode) {
			case 32:
				am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
				break;
			case Keyboard.KEYCODE_DONE:
			case 10:
				am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
				break;
			case Keyboard.KEYCODE_DELETE:
				am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
				break;
			default:
				am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
		}
	}
}
