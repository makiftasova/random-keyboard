package com.makiftasova.apps.android.randomkeyboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String html = getString(R.string.text_html_keyboard_enable_info);
		Spanned content = Html.fromHtml(html);
		TextView description = (TextView) findViewById(R.id.text_info);
		description.setMovementMethod(LinkMovementMethod.getInstance());
		description.setText(content, TextView.BufferType.SPANNABLE);

		final Button btnImeConfig = (Button)findViewById(R.id.btn_ime_config);
		btnImeConfig.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS), 0);
			}
		});

		final Button btnImeSet = (Button)findViewById(R.id.btn_set_ime);
		btnImeSet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				mgr.showInputMethodPicker();
			}
		});

	}


}
