package com.fashion.zillah;

import com.zillah.database.ClientsTable;
import com.zillah.database.ZillahDatabaseAdapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MyClientsActivity extends Activity {
	public ZillahDatabaseAdapter database_adapter;
	public ListView client_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_clients);
		database_adapter = new ZillahDatabaseAdapter(getApplicationContext());
		client_list = (ListView) findViewById(R.id.clientsList);
		populateClientList();
	}

	// Populate Client List
	public void populateClientList() {
		Cursor cursor = database_adapter.listClients();
		String[] source = new String[] { ClientsTable.CLIENT_ID,
				ClientsTable.FIRST_NAME, ClientsTable.LAST_NAME,
				ClientsTable.PHONE_NUMBER, ClientsTable.LOCATION };
		int[] destination = new int[] { R.id.txt_client_id,
				R.id.txt_client_firstname, R.id.txt_client_lastname,
				R.id.txt_client_contacts, R.id.txt_client_location };
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				getApplicationContext(), R.layout.clients_row, cursor, source,
				destination);
		client_list.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_clients, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
