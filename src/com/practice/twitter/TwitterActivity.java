package com.practice.twitter;

import twitter4j.TwitterException;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TwitterActivity extends Activity {

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				(new AsyncTask<Void, Void, Void> () {
					protected Void doInBackground(Void... params) {
						executeOath();
						return null;
					}
				}).execute();
			}
		});
	}
	
	private void executeOath() {
		Configuration conf = ConfigurationContext.getInstance();
		OAuthAuthorization oauth = new OAuthAuthorization(conf);
		oauth.setOAuthConsumer("Xs3ydWMUNnNZ4gZJAQM5w", "Ml5j1P2dQ96Iiuns29h2K5H4ncPHgvYFoAZhoIPGg");
		try {
			RequestToken req = oauth.getOAuthRequestToken("hqtp://request_callback");
			String uri = req.getAuthorizationURL();
			startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)), 0);
		} catch (TwitterException e) {
			e.printStackTrace();
			return;
		}
	}
}



