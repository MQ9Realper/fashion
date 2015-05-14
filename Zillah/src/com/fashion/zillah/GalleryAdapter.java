package com.fashion.zillah;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {
	private ArrayList<Integer> listDesign;
	private Activity activity;

	public GalleryAdapter(Activity activity, ArrayList<Integer> listDesign) {
		super();

		this.listDesign = listDesign;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listDesign.size();
	}

	@Override
	public Integer getItem(int position) {
		// TODO Auto-generated method stub
		return listDesign.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static class ViewHolder {
		public ImageView imgViewFlag;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder view;
		LayoutInflater inflator = activity.getLayoutInflater();

		if (convertView == null) {
			view = new ViewHolder();
			convertView = inflator.inflate(R.layout.gridview_row, null);

			view.imgViewFlag = (ImageView) convertView
					.findViewById(R.id.imageView1);

			convertView.setTag(view);
		} else {
			view = (ViewHolder) convertView.getTag();
		}

		view.imgViewFlag.setImageResource(listDesign.get(position));

		return convertView;
	}

}
