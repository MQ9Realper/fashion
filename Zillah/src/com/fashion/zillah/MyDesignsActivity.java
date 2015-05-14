package com.fashion.zillah;

import com.zillah.database.DesignsTable;
import com.zillah.database.ZillahDatabaseAdapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MyDesignsActivity extends Activity {
	public ZillahDatabaseAdapter database_adapter;
	public ListView designs_list;
	public Button save_design;
	public EditText design_name, design_price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_designs);
		database_adapter = new ZillahDatabaseAdapter(getApplicationContext());
		designs_list = (ListView) findViewById(R.id.designsList);
		design_name = (EditText) findViewById(R.id.txt_new_design_name);
		design_price = (EditText) findViewById(R.id.txt_new_design_price);
		save_design = (Button) findViewById(R.id.btnSaveDesign);
		populateDesignsList();
		save_design.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				database_adapter.addDesign(design_name.getText().toString(),
						"Ksh " + design_price.getText().toString());
				Toast.makeText(getApplicationContext(),
						"New design has been added!", Toast.LENGTH_LONG).show();
				
				//Reset Textboxes
				design_name.setText("");
				design_price.setText("");
				
				//Refresh ListView
				populateDesignsList();
			}
		});

	}

	public void populateDesignsList() {
		Cursor cursor = database_adapter.listDesigns();
		String[] source = new String[] { DesignsTable.DESIGN_ID,
				DesignsTable.DESIGN_NAME, DesignsTable.DESIGN_PRICE };
		int[] destination = new int[] { R.id.txt_design_id,
				R.id.txt_design_name, R.id.txt_design_price };
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				getApplicationContext(), R.layout.my_designs_row, cursor,
				source, destination);
		designs_list.setAdapter(adapter);
	}

}
