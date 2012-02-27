package net.msonic.lib;

import java.util.ArrayList;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

public abstract class  TOSpinnerAdapter<T> implements SpinnerAdapter {

	protected ArrayList<T> data; 
	protected Context context;
	
	public TOSpinnerAdapter(Context context,ArrayList<T> data){
		this.context = context;
		this.data = data;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return android.R.layout.simple_spinner_dropdown_item;
	}
	
	
	
	@Override
	public int getItemViewType(int arg0) {
		// TODO Auto-generated method stub
		return android.R.layout.simple_spinner_dropdown_item;
	}

	protected abstract View getViewTO(int position, View convertView, ViewGroup parent,Context context);
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getViewTO(position,convertView,parent,this.context);
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return android.R.layout.simple_spinner_dropdown_item;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public View getDropDownView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return this.getView(arg0, arg1, arg2);
	}

}
