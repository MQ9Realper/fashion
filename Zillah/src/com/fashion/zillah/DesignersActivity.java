package com.fashion.zillah;

import com.zillah.database.DesignersTable;
import com.zillah.database.ZillahDatabaseAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DesignersActivity extends Activity {
	public ZillahDatabaseAdapter database_adapter;
	public ListView designer_list;
	public TextView client_list_firstname, client_list_lastname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_designers);

		database_adapter = new ZillahDatabaseAdapter(getApplicationContext());
		designer_list = (ListView) findViewById(R.id.designersList);
		populateDesignersList();
		registerForContextMenu(designer_list);
	}

	public void populateDesignersList() {
		Cursor cursor = database_adapter.listDesigners();
		String[] source = new String[] { DesignersTable.DESIGNER_ID,
				DesignersTable.FIRST_NAME, DesignersTable.LAST_NAME, DesignersTable.PHONE_NUMBER, DesignersTable.LOCATION};
		int[] destination = new int[] { R.id.txt_designer_id,
				R.id.txt_designer_firstname, R.id.txt_designer_lastname, R.id.txt_designer_contacts, R.id.txt_designer_location};
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				getApplicationContext(), R.layout.designers_row, cursor,
				source, destination);
		designer_list.setAdapter(adapter);

	}
	
	public void designRequest(){
		final EditText request_name = new EditText(getApplicationContext());
		final EditText request_description = new EditText(getApplicationContext());
		request_name.setHint("Enter Request Name");
		request_description.setHint("Enter Request Description");
		
		
		AlertDialog.Builder newDesignAlert = new AlertDialog.Builder(this);
		newDesignAlert.setIcon(R.drawable.ic_launcher);
		newDesignAlert.setTitle("Design Request");
		newDesignAlert.setView(request_name);
		newDesignAlert.setView(request_description);
		newDesignAlert.setPositiveButton("Submit",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
//						database_adapter.addRequest(request_name.getText().toString(), request_description.getText().toString());
						Toast.makeText(getApplicationContext(), "Request Has Been Submitted!", Toast.LENGTH_LONG).show();

					}
				});
		newDesignAlert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				});
		AlertDialog builder = newDesignAlert.create();
		builder.show();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {
		if (view.getId() == R.id.designersList) {
			menu.setHeaderTitle("Design Request");
			menu.setHeaderIcon(R.drawable.ic_launcher);
			String[] menuItems = new String[] { "Make A Design Request" };
			for (int i = 0; i < menuItems.length; i++) {
				menu.add(menuItems[i]);
			}

		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Make A Design Request") {
			designRequest();
		}
		return true;
	}

}
