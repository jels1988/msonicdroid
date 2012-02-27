package pe.lindley.om.adapter;


import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pe.lindley.om.to.RolTO;
import pe.lindley.util.TOSpinnerAdapter;

public class RolTOAdapter extends TOSpinnerAdapter<RolTO> {

	public RolTOAdapter(Context context, ArrayList<RolTO> data) {
		super(context, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected View getViewTO(int position, View convertView, ViewGroup parent,
			Context context) {
		// TODO Auto-generated method stub
		TextView v = new TextView(this.context);
		v.setTextColor(Color.BLACK);
		v.setText(data.get(position).getNombres());
		return v;
	}
	
	public int findByValue(int asignacionId) {
		int index = 0;
			for (RolTO rolTO : data) {
				if (rolTO.getAsignacionId()==asignacionId) {
					index = data.indexOf(rolTO);
				}
			}
		return index;
	}
}
