package com.fashion.zillah;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class ClientDashboardActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client_dashboard);
		// Set ActionBar Background
		ActionBar action_bar = getActionBar();
		action_bar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.site_bg_maroon));

		TabHost dashboard_tabhost = getTabHost();

		// My Designs Tab
		Intent my_designs_intent = new Intent().setClass(this,
				DesignersActivity.class);
		TabSpec my_designs = dashboard_tabhost.newTabSpec("Designers");
		my_designs.setIndicator("Designers");
		my_designs.setContent(my_designs_intent);

		// All Designs Tab
		Intent all_designs = new Intent().setClass(this,
				DesignGalleryActivity.class);
		TabSpec all_designs_spec = dashboard_tabhost.newTabSpec("Designs");
		all_designs_spec.setIndicator("Designs");
		all_designs_spec.setContent(all_designs);

		// My Requests
		Intent my_requests_intent = new Intent().setClass(this,
				DesignRequestsActivity.class);
		TabSpec my_requests_spec = dashboard_tabhost.newTabSpec("Requests");
		my_requests_spec.setIndicator("Requests");
		my_requests_spec.setContent(my_requests_intent);

		// Add All Tabs
		dashboard_tabhost.addTab(my_designs);
		dashboard_tabhost.addTab(all_designs_spec);
		dashboard_tabhost.addTab(my_requests_spec);
	}

}
