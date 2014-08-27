package com.ki.dms;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GlucoseMonitoring extends Activity implements View.OnClickListener {

	Button btnYes, btnNo;
	TextView textQuestion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.glucose_monitoring);
		btnYes = (Button) findViewById(R.id.glucose_btn_yes);
		btnNo = (Button) findViewById(R.id.glucose_btn_no);
		textQuestion = (TextView) findViewById(R.id.glucose_text);
		btnYes.setOnClickListener(this);
		btnNo.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		int idClicked = view.getId();
		switch (idClicked) {
		case R.id.glucose_btn_yes:

			break;
		case R.id.glucose_btn_no:

			break;

		default:
			break;
		}

	}

}
