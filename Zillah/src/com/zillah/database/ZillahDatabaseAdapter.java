package com.zillah.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ZillahDatabaseAdapter extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "zillah.db";
	public static final int DATABASE_VERSION = 1;

	public ZillahDatabaseAdapter(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create Clients Table
		db.execSQL("CREATE TABLE " + ClientsTable.TABLE_NAME + "("
				+ ClientsTable.CLIENT_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ ClientsTable.FIRST_NAME + " TEXT," + ClientsTable.LAST_NAME
				+ " TEXT," + ClientsTable.PHONE_NUMBER + " VARCHAR,"
				+ ClientsTable.LOCATION + " TEXT," + ClientsTable.USERNAME
				+ " TEXT," + ClientsTable.PASSWORD + " TEXT" + ");");

		// Create Designers Table
		db.execSQL("CREATE TABLE " + DesignersTable.TABLE_NAME + "("
				+ DesignersTable.DESIGNER_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ DesignersTable.FIRST_NAME + " TEXT,"
				+ DesignersTable.LAST_NAME + " TEXT,"
				+ DesignersTable.PHONE_NUMBER + " TEXT,"
				+ DesignersTable.LOCATION + " TEXT," + DesignersTable.USERNAME
				+ " TEXT," + DesignersTable.PASSWORD + " TEXT" + ");");

		// Create Designs Table
		db.execSQL("CREATE TABLE " + DesignsTable.TABLE_NAME + "("
				+ DesignsTable.DESIGN_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ DesignsTable.DESIGN_NAME + " TEXT,"
				+ DesignsTable.DESIGN_PRICE + " CURRENCY" + ");");

		// Create Requests Table
		db.execSQL("CREATE TABLE " + RequestsTable.TABLE_NAME + "("
				+ RequestsTable.REQUEST_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ RequestsTable.REQUEST_NAME + " TEXT,"
				+ RequestsTable.REQUEST_DESCRIPTION + " TEXT,"
				+ RequestsTable.HEIGHT + " INTEGER," + RequestsTable.WAIST
				+ " INTEGER," + RequestsTable.BUST + " INTEGER" + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	// Wrapper method for adding a new design
	public long addDesign(String design_name, String design_price) {

		ContentValues designs = new ContentValues();
		designs.put(DesignsTable.DESIGN_NAME, design_name);
		designs.put(DesignsTable.DESIGN_PRICE, design_price);

		SQLiteDatabase db = getWritableDatabase();
		long results = db.insert(DesignsTable.TABLE_NAME,
				DesignsTable.DESIGN_NAME, designs);

		return results;

	}

	// Wrapper method for adding a new Client
	public long addClient(String first_name, String last_name,
			String phone_number, String location, String username,
			String password) {

		ContentValues users = new ContentValues();
		users.put(ClientsTable.FIRST_NAME, first_name);
		users.put(ClientsTable.LAST_NAME, last_name);
		users.put(ClientsTable.PHONE_NUMBER, phone_number);
		users.put(ClientsTable.LOCATION, location);
		users.put(ClientsTable.USERNAME, username);
		users.put(ClientsTable.PASSWORD, password);

		SQLiteDatabase db = getWritableDatabase();
		long result = db.insert(ClientsTable.TABLE_NAME,
				ClientsTable.FIRST_NAME, users);

		return result;

	}

	// Wrapper method for adding a new Designer
	public long addDesigner(String first_name, String last_name,
			String phone_number, String location, String username,
			String password) {
		ContentValues designers = new ContentValues();
		designers.put(DesignersTable.FIRST_NAME, first_name);
		designers.put(DesignersTable.LAST_NAME, last_name);
		designers.put(DesignersTable.PHONE_NUMBER, phone_number);
		designers.put(DesignersTable.LOCATION, location);
		designers.put(DesignersTable.USERNAME, username);
		designers.put(DesignersTable.PASSWORD, password);

		SQLiteDatabase db = getWritableDatabase();
		long saved_designers = db.insert(DesignersTable.TABLE_NAME,
				DesignersTable.FIRST_NAME, designers);

		return saved_designers;
	}

	// Wrapper method for adding a new Request
	public long addRequest(String request_name, String request_description,
			String height, String waist, String bust) {
		ContentValues requests = new ContentValues();
		requests.put(RequestsTable.REQUEST_NAME, request_name);
		requests.put(RequestsTable.REQUEST_DESCRIPTION, request_description);
		requests.put(RequestsTable.HEIGHT, height);
		requests.put(RequestsTable.WAIST, waist);
		requests.put(RequestsTable.BUST, bust);

		SQLiteDatabase db = getWritableDatabase();
		long saved_requests = db.insert(RequestsTable.TABLE_NAME,
				RequestsTable.REQUEST_NAME, requests);

		return saved_requests;
	}

	// Client Login
	public String clientSignin(String username) {
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(ClientsTable.TABLE_NAME, null,
				ClientsTable.USERNAME + "=?", new String[] { username }, null,
				null, null);
		if (cursor.getCount() < 1) {
			cursor.close();
			return "Client Does Not Exist";
		}
		cursor.moveToFirst();
		String password = cursor.getString(cursor
				.getColumnIndex(ClientsTable.PASSWORD));

		return password;

	}

	// Designer Login
	public String designerSignin(String username) {
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(DesignersTable.TABLE_NAME, null,
				DesignersTable.USERNAME + "=?", new String[] { username },
				null, null, null);
		if (cursor.getCount() < 1) {
			cursor.close();
			return "Designer Does Not Exist";
		}
		cursor.moveToFirst();
		String password = cursor.getString(cursor
				.getColumnIndex(DesignersTable.PASSWORD));

		return password;
	}

	// List Designs
	public Cursor listDesigns() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor designs = db.query(DesignsTable.TABLE_NAME, new String[] {
				DesignsTable.DESIGN_ID, DesignsTable.DESIGN_NAME, DesignsTable.DESIGN_PRICE}, null, null,
				null, null, null);
		return designs;

	}
	

	// List Designers
	public Cursor listDesigners() {
		SQLiteDatabase database = getWritableDatabase();
		Cursor designers = database.query(DesignersTable.TABLE_NAME,
				new String[] { DesignersTable.DESIGNER_ID,
						DesignersTable.FIRST_NAME, DesignersTable.LAST_NAME,
						DesignersTable.PHONE_NUMBER, DesignersTable.LOCATION },
				null, null, null, null, null);
		return designers;
	}

	// List Clients
	public Cursor listClients() {
		SQLiteDatabase database = getWritableDatabase();
		Cursor clients = database.query(ClientsTable.TABLE_NAME, new String[] {
				ClientsTable.CLIENT_ID, ClientsTable.FIRST_NAME,
				ClientsTable.LAST_NAME, ClientsTable.PHONE_NUMBER, ClientsTable.LOCATION }, null, null, null, null, null);
		return clients;

	}

	public Cursor listRequests() {
		SQLiteDatabase database = getWritableDatabase();
		Cursor requests = database.query(RequestsTable.TABLE_NAME,
				new String[] { RequestsTable.REQUEST_ID,
						RequestsTable.REQUEST_NAME,
						RequestsTable.REQUEST_DESCRIPTION,
						RequestsTable.HEIGHT, RequestsTable.WAIST,
						RequestsTable.BUST }, null, null, null, null, null);
		return requests;
	}

	public void Upgrade() {
		SQLiteDatabase db = getWritableDatabase();
		Log.w("LOG_TAG", "Upgrading database");
		// KILL PREVIOUS
		db.execSQL("DROP TABLE IF EXISTS " + DesignersTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + DesignsTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + ClientsTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + RequestsTable.TABLE_NAME);
		onCreate(db);
	}

}
