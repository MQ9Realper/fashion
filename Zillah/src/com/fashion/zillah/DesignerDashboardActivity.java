package com.fashion.zillah;

import com.zillah.database.ZillahDatabaseAdapter;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class DesignerDashboardActivity extends TabActivity {
	public ZillahDatabaseAdapter database_adapter;
	public ListView designs_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_designer_dashboard);

		database_adapter = new ZillahDatabaseAdapter(getApplicationContext());
		designs_list = (ListView) findViewById(R.id.designsList);

		ActionBar action_bar = getActionBar();
		action_bar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.site_bg_maroon));

		TabHost dashboard_tabhost = getTabHost();

		// My Designs Tab
		Intent my_designs_intent = new Intent().setClass(this,
				MyDesignsActivity.class);
		TabSpec my_designs = dashboard_tabhost.newTabSpec("My Designs");
		my_designs.setIndicator("My Designs");
		my_designs.setContent(my_designs_intent);

		// My Clients Tab
		Intent my_clients_intent = new Intent().setClass(this,
				MyClientsActivity.class);
		TabSpec my_clients_spec = dashboard_tabhost.newTabSpec("Clients");
		my_clients_spec.setIndicator("Clients");
		my_clients_spec.setContent(my_clients_intent);

		// My Requests
		Intent my_requests_intent = new Intent().setClass(this,
				DesignerRequestsTabActivity.class);
		TabSpec my_requests_spec = dashboard_tabhost.newTabSpec("Requests");
		my_requests_spec.setIndicator("Requests");
		my_requests_spec.setContent(my_requests_intent);
		
		// Client Count
		Intent client_count_intent = new Intent().setClass(this, ClientCountActivity.class);
		TabSpec client_count_spec = dashboard_tabhost.newTabSpec("Statistics");
		client_count_spec.setIndicator("Statistics");
		client_count_spec.setContent(client_count_intent);

		// Add All Tabs
		dashboard_tabhost.addTab(my_designs);
		dashboard_tabhost.addTab(my_clients_spec);
		dashboard_tabhost.addTab(my_requests_spec);
		dashboard_tabhost.addTab(client_count_spec);
	}

}
