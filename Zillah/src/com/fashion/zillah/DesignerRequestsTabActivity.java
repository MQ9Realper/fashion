package com.fashion.zillah;

import com.zillah.database.RequestsTable;
import com.zillah.database.ZillahDatabaseAdapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DesignerRequestsTabActivity extends Activity {
	public ZillahDatabaseAdapter database_adapter;
	public ListView request_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.designer_requests);
		database_adapter = new ZillahDatabaseAdapter(getApplicationContext());
		request_list = (ListView)findViewById(R.id.designer_requests_list);
		populateList();
	}

	public void populateList(){
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
				destination);
		request_list.setAdapter(adapter);
	}
}
