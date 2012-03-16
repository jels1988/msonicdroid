package pe.pae.encuesta.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pe.pae.encuesta.to.ClienteTO;
import net.msonic.lib.TOSpinnerAdapter;

public class ClienteAdapter extends TOSpinnerAdapter<ClienteTO> {

	public ClienteAdapter(Context context, ArrayList<ClienteTO> data) {
		super(context, data);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected View getViewTO(int position, View convertView, ViewGroup parent,
			Context context) {
		// TODO Auto-generated method stub
		TextView v = new TextView(this.context);
		v.setTextColor(Color.BLACK);
		v.setText(data.get(position).descripcion);
		return v;
	}
	
}
