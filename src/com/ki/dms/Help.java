package com.ki.dms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
/*
 * Activity responsible for help.xml view
 */
public class Help extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		WebView web = (WebView) findViewById(R.id.help);
		//loads the html file where the content is located
		web.loadDataWithBaseURL("file:///android_res/drawable/",
				getHTML(getResources().openRawResource(R.raw.help)),
				"text/html", "UTF-8", null);

	}

	//returns the html  where the content is located
	@SuppressWarnings("finally")
	private String getHTML(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return sb.toString();
		}

	}
}
