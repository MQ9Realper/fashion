package com.fashion.zillah;

import com.zillah.database.ZillahDatabaseAdapter;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
	public RadioGroup main_radiogroup;
	public RadioButton designers_radio;
	public RadioButton clients_radio;
	public Button login_button;
	public Button signup_button;
	public EditText password_text;
	public EditText username_text;
	public ZillahDatabaseAdapter database_adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set Background for the ActionBar
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.site_bg_maroon));

		// Initializing global variables
		main_radiogroup = (RadioGroup) findViewById(R.id.radioGroup1);
		designers_radio = (RadioButton) findViewById(R.id.designerRadioButton);
		clients_radio = (RadioButton) findViewById(R.id.clientRadioButton);
		login_button = (Button) findViewById(R.id.btnSignin);
		signup_button = (Button) findViewById(R.id.btnSignup);
		password_text = (EditText) findViewById(R.id.txtPass);
		username_text = (EditText) findViewById(R.id.txtUsername);
		database_adapter = new ZillahDatabaseAdapter(getApplicationContext());

		// Set Click Listener for Signup Button
		signupListener();

		// Set Click Listener For Login Button
		signinListener();

	}

	// Client Signin Method
	public void clientSignin() {

		try {
			String storedPassword = database_adapter.clientSignin(username_text
					.getText().toString());
			if (password_text.getText().toString().equals(storedPassword)) {
				Toast.makeText(getApplicationContext(),
						"Login Has Been Successful!", Toast.LENGTH_LONG).show();

				// Reset Textboxes
				username_text.setText("");
				password_text.setText("");

				Intent doc = new Intent(this, ClientDashboardActivity.class);
				startActivity(doc);

			} else {
				Toast.makeText(getApplicationContext(),
						"Username or Password Does Not Exist!",
						Toast.LENGTH_LONG).show();
			}

		} catch (Exception e) {
			AlertDialog.Builder error = new AlertDialog.Builder(
					getApplicationContext());
			error.setTitle("Signin Error");
			error.setIcon(R.drawable.ic_launcher);
			error.setMessage(e.getMessage().toString());
			AlertDialog alert = error.create();
			alert.show();
		}

	}

	// Designer Signin Method
	public void designerSignin() {
		try {
			String storedPassword = database_adapter
					.designerSignin(username_text.getText().toString());
			if (password_text.getText().toString().equals(storedPassword)) {
				Toast.makeText(getApplicationContext(),
						"Login Has Been Successful!", Toast.LENGTH_LONG).show();

				// Reset Textboxes
				username_text.setText("");
				password_text.setText("");

				Intent doc = new Intent(this, DesignerDashboardActivity.class);
				startActivity(doc);

			} else {
				Toast.makeText(getApplicationContext(),
						"Username or Password Does Not Exist!",
						Toast.LENGTH_LONG).show();
			}

		} catch (Exception e) {
			AlertDialog.Builder error = new AlertDialog.Builder(
					getApplicationContext());
			error.setTitle("Signin Error");
			error.setIcon(R.drawable.ic_launcher);
			error.setMessage(e.getMessage().toString());
			AlertDialog alert = error.create();
			alert.show();
		}
	}

	// Signup Button Listener Method
	public void signupListener() {
		signup_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int selected_button = main_radiogroup.getCheckedRadioButtonId();
				if (selected_button == R.id.clientRadioButton) {
					Intent client_intent = new Intent()
							.setClass(getApplicationContext(),
									ClientSignupActivity.class);
					startActivity(client_intent);
				} else if (selected_button == R.id.designerRadioButton) {
					Intent designer_intent = new Intent().setClass(
							getApplicationContext(),
							DesignerSignupActivity.class);
					startActivity(designer_intent);
				}

			}
		});
	}

	// Login Listener Method
	public void signinListener() {
		login_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int selected_button = main_radiogroup.getCheckedRadioButtonId();
				if (selected_button == R.id.clientRadioButton) {
					clientSignin();
				} else if (selected_button == R.id.designerRadioButton) {
					designerSignin();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflate = getMenuInflater();
		inflate.inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_upgrade) {
			AlertDialog.Builder upgradeBuilder = new AlertDialog.Builder(this);
			upgradeBuilder.setIcon(R.drawable.ic_launcher);
			upgradeBuilder.setTitle("Database Upgrade");
			upgradeBuilder
					.setMessage("Are you sure you want to upgrade the database?");
			upgradeBuilder.setPositiveButton("Upgrade",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

							database_adapter.Upgrade();
							Toast.makeText(getApplicationContext(),
									"Upgrade Has Successfully Finished!",
									Toast.LENGTH_LONG).show();
						}
					});
			upgradeBuilder.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
			AlertDialog upgradeDialog = upgradeBuilder.create();
			upgradeDialog.show();

		}

		return super.onOptionsItemSelected(item);
	}
}
