package com.fashion.zillah;

import com.zillah.database.ZillahDatabaseAdapter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ClientSignupActivity extends Activity {
	public EditText first_name, last_name, phone_number, location, username,
			password;
	public Button create_account, cancel;
	public ZillahDatabaseAdapter database_adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client_signup);

		// Set ActionBar Background
		ActionBar action_bar = getActionBar();
		action_bar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.site_bg_maroon));

		// Initialize global variables
		first_name = (EditText) findViewById(R.id.txtClientFirstName);
		last_name = (EditText) findViewById(R.id.txtClientLastName);
		phone_number = (EditText) findViewById(R.id.txtClientPhoneNumber);
		location = (EditText) findViewById(R.id.txtClientLocation);
		username = (EditText) findViewById(R.id.txtClientUserName);
		password = (EditText) findViewById(R.id.txtClientPassword);

		create_account = (Button) findViewById(R.id.btnClientCreateAccount);
		cancel = (Button) findViewById(R.id.btnClientCancel);
		database_adapter = new ZillahDatabaseAdapter(getApplicationContext());

		// Setting up listeners
		createAccountListener();
		cancelSignupListener();

	}

	// Create Account Button Listener
	public void createAccountListener() {
		create_account.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						"Registering Client.......", Toast.LENGTH_LONG).show();

				database_adapter.addClient(first_name.getText().toString(),
						last_name.getText().toString(), phone_number.getText()
								.toString(), location.getText().toString(),
						username.getText().toString(), password.getText()
								.toString());
				Toast.makeText(
						getApplicationContext(),
						first_name.getText().toString() + " "
								+ last_name.getText().toString()
								+ " has been successfully registered",
						Toast.LENGTH_LONG).show();

				// Reset Textboxes
				first_name.setText("");
				last_name.setText("");
				phone_number.setText("");
				location.setText("");
				username.setText("");
				password.setText("");

				// Move to the Clients dashboard
				Intent dashboard = new Intent().setClass(
						getApplicationContext(), ClientDashboardActivity.class);
				startActivity(dashboard);
			}
		});

	}

	// Cancelling Signup Listener
	public void cancelSignupListener() {
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						"Cancelling Signup.......", Toast.LENGTH_LONG).show();

				Intent go_to_main = new Intent().setClass(
						getApplicationContext(), MainActivity.class);
				startActivity(go_to_main);

			}
		});

	}
}