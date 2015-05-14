package com.fashion.zillah;

import com.zillah.database.RequestsTable;
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

public class DesignRequestsActivity extends Activity {
	public ZillahDatabaseAdapter database_adapter;
	public EditText request_name, request_description, height, waist, bust;
	public Button save_request;
	public ListView request_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_design_requests);

		save_request = (Button) findViewById(R.id.btnSaveRequest);
		database_adapter = new ZillahDatabaseAdapter(getApplicationContext());
		request_name = (EditText) findViewById(R.id.txtRequestName);
		request_description = (EditText) findViewById(R.id.txtRequestDescription);
		height = (EditText) findViewById(R.id.txtHeight);
		waist = (EditText) findViewById(R.id.txtWaist);
		bust = (EditText) findViewById(R.id.txtBust);
		request_list = (ListView) findViewById(R.id.requestsList);
		populateRequestList();

		save_request.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				database_adapter.addRequest(request_name.getText().toString(),
						request_description.getText().toString(), height
								.getText().toString() + " meters", waist.getText()
								.toString() + " inches", bust.getText().toString() + " inches");
				populateRequestList();
				Toast.makeText(getApplicationContext(),
						"Request Has Been Submitted!", Toast.LENGTH_LONG)
						.show();

			}
		});
	}

	public void populateRequestList() {
		Cursor cursor = database_adapter.listRequests();
		String[] source = new String[] { RequestsTable.REQUEST_ID,
				RequestsTable.REQUEST_NAME, RequestsTable.REQUEST_DESCRIPTION,
				RequestsTable.HEIGHT, RequestsTable.WAIST, RequestsTable.BUST };
		int[] destination = new int[] { R.id.txt_request_id,
				R.id.txt_request_name, R.id.txt_request_description,
				R.id.txt_request_height, R.id.txt_request_waist,
				R.id.txt_request_bust };
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				getApplicationContext(), R.layout.request_row, cursor, source,
				destination,0);
		request_list.setAdapter(adapter);
	}

}
