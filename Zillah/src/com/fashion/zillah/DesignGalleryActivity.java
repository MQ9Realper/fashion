package com.fashion.zillah;

import java.util.ArrayList;

import com.zillah.database.ZillahDatabaseAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

public class DesignGalleryActivity extends Activity {
	public GalleryAdapter gadapter;
	private ArrayList<Integer> listDesign;
	public ZillahDatabaseAdapter database_adapter;
	public ListView designs_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_design_gallery);
		database_adapter = new ZillahDatabaseAdapter(getApplicationContext());
		populateGrid();
		gadapter = new GalleryAdapter(this, listDesign);
		GridView view = (GridView) findViewById(R.id.gridView1);
		registerForContextMenu(view);
		view.setAdapter(gadapter);
		view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						"Matching Of Designs Has Been Done!", Toast.LENGTH_LONG)
						.show();

			}
		});
	}

	public void populateGrid() {
		listDesign = new ArrayList<Integer>();
		listDesign.add(R.drawable.fashion);
		listDesign.add(R.drawable.fashion);
		listDesign.add(R.drawable.fashion2);
		listDesign.add(R.drawable.fashion2);
		listDesign.add(R.drawable.fashion3);
		listDesign.add(R.drawable.fashion3);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {
		if (view.getId() == R.id.gridView1) {
			menu.setHeaderTitle("Expand Matched Designs");
			menu.setHeaderIcon(R.drawable.ic_launcher);
			String[] menuItems = new String[] { "Expand Design Images",
					"Expand Design Details" };
			for (int i = 0; i < menuItems.length; i++) {
				menu.add(menuItems[i]);
			}

		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Expand Design Images") {
			// EditText view for adding a new design
			final GridView designView = new GridView(this);
			designView.setAdapter(gadapter);

			AlertDialog.Builder newDesignAlert = new AlertDialog.Builder(this);
			newDesignAlert.setIcon(R.drawable.ic_launcher);
			newDesignAlert.setTitle("Expanded Design Images");
			newDesignAlert.setView(designView);
			newDesignAlert.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

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
		return true;
	}

}
