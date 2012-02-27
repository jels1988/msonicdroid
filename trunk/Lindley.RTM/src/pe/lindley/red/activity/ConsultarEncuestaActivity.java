package pe.lindley.red.activity;

import java.util.List;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.red.to.EncuestaTO;
import pe.lindley.red.ws.service.ConsultarEncuestaProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class ConsultarEncuestaActivity extends ListActivityBase {

	public static final String CODIGO_CLIENTE_KEY="codigo_cliente";
	public static final String CLIENTE_KEY="cliente_descripcion";
	
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	
	public static String cliente_codigo;
	public static String cliente_descripcion;
	
	@Inject	ConsultarEncuestaProxy consultarEncuestaProxy;
	
	private EfficientAdapter adap;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.consultarencuesta_activity);
		mActionBar.setTitle(R.string.consultar_encuesta_title);
	    mActionBar.setHomeLogo(R.drawable.header_logo);
		
	    Intent intent = this.getIntent();
		cliente_codigo = intent.getStringExtra(CODIGO_CLIENTE_KEY);
		cliente_descripcion = intent.getStringExtra(CLIENTE_KEY);
		
		mActionBar.setSubTitle(cliente_descripcion);
		
		processAsync();
	}
	

	@Override
	protected boolean executeAsyncPre() {
		return true;
		
	}
	@Override
	protected void process() {
		consultarEncuestaProxy.setCodigoCliente(cliente_codigo);
		consultarEncuestaProxy.execute();
		
	}
	@Override
	protected void processOk() {
		boolean isExito = consultarEncuestaProxy.isExito();
		if (isExito) {
			int status = consultarEncuestaProxy.getResponse().getStatus();
			
			if (status == 0) {
				List<EncuestaTO> encuestas = consultarEncuestaProxy.getResponse().getEncuestas();
				adap = new EfficientAdapter(this, encuestas);
				setListAdapter(adap);
			}
		}
		
		super.processOk();
	}
	
	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
	}
	
	
	
	
	
	
	public static class EfficientAdapter extends BaseAdapter implements Filterable {
	private LayoutInflater mInflater;
	private Context context;
	private List<EncuestaTO> encuestas;

	public EfficientAdapter(Context context, List<EncuestaTO> encuestas) {
		// Cache the LayoutInflate to avoid asking for a new one each time.
		mInflater = LayoutInflater.from(context);
		this.context = context;
		this.encuestas = encuestas;
	}

	public EfficientAdapter(Context context) {
		// Cache the LayoutInflate to avoid asking for a new one each time.
		mInflater = LayoutInflater.from(context);
		this.context = context;
	}

/**
 * Make a view to hold each row.
 * 
 * @see android.widget.ListAdapter#getView(int, android.view.View,
 *      android.view.ViewGroup)
 */
public View getView(final int position, View convertView,
		ViewGroup parent) {
	EncuestaTO encuestaTO = (EncuestaTO) getItem(position);
	ViewHolder holder;

	if (convertView == null) {
		convertView = mInflater.inflate(R.layout.consultarencuesta_content, null);

		// Creates a ViewHolder and store references to the two children
		// views
		// we want to bind data to.
		holder = new ViewHolder();
		holder.txtFecha = (TextView) convertView.findViewById(R.id.txtFecha);
		holder.imgResumen1 = (ImageButton)convertView.findViewById(R.id.btn_resumen1);
		holder.imgResumen2 = (ImageButton)convertView.findViewById(R.id.btn_resumen2);
		holder.imgResumen3 = (ImageButton)convertView.findViewById(R.id.btn_resumen3);
		holder.imgResumen4 = (ImageButton)convertView.findViewById(R.id.btn_resumen4);
		holder.imgResumen5 = (ImageButton)convertView.findViewById(R.id.btn_resumen5);
		holder.imgResumen6 = (ImageButton)convertView.findViewById(R.id.btn_resumen6);
		
		convertView.setTag(holder);
	} else {
		// Get the ViewHolder back to get fast access to the TextView
		// and the ImageView.
		holder = (ViewHolder) convertView.getTag();
	}
	
	int colorPos = position % 2;
	if(colorPos==0){
		convertView.setBackgroundResource(R.color.custom_theme_color_amarillo_alterno);
	}

	holder.txtFecha.setText(encuestaTO.getFecha());
	
	holder.imgResumen1.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EncuestaTO encuestaTO = (EncuestaTO)getItem(position);
			
			Intent intent = new Intent(context,ResumenActivity.class);
			intent.putExtra(ResumenActivity.CODIGO_CLIENTE_KEY, ConsultarEncuestaActivity.cliente_codigo);
			intent.putExtra(ResumenActivity.CLIENTE_KEY, ConsultarEncuestaActivity.cliente_descripcion);
			intent.putExtra(ResumenActivity.FECHA_ENCUESTA_KEY, encuestaTO.getFecha());
			context.startActivity(intent);
			
		}
	});

	holder.imgResumen2.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EncuestaTO encuestaTO = (EncuestaTO)getItem(position);
			
			Intent intent = new Intent(context,ResumenActivity1.class);
			intent.putExtra(ResumenActivity1.CODIGO_CLIENTE_KEY, ConsultarEncuestaActivity.cliente_codigo);
			intent.putExtra(ResumenActivity1.CLIENTE_KEY, ConsultarEncuestaActivity.cliente_descripcion);
			intent.putExtra(ResumenActivity1.FECHA_ENCUESTA_KEY, encuestaTO.getFecha());
			context.startActivity(intent);
			
		}
	});
	
	
	holder.imgResumen3.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EncuestaTO encuestaTO = (EncuestaTO)getItem(position);
			
			Intent intent = new Intent(context,ResumenActivity2.class);
			intent.putExtra(ResumenActivity1.CODIGO_CLIENTE_KEY, ConsultarEncuestaActivity.cliente_codigo);
			intent.putExtra(ResumenActivity1.CLIENTE_KEY, ConsultarEncuestaActivity.cliente_descripcion);
			intent.putExtra(ResumenActivity1.FECHA_ENCUESTA_KEY, encuestaTO.getFecha());
			context.startActivity(intent);
			
		}
	});
	
	holder.imgResumen4.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EncuestaTO encuestaTO = (EncuestaTO)getItem(position);
			
			Intent intent = new Intent(context,ResumenActivity3.class);
			intent.putExtra(ResumenActivity1.CODIGO_CLIENTE_KEY, ConsultarEncuestaActivity.cliente_codigo);
			intent.putExtra(ResumenActivity1.CLIENTE_KEY, ConsultarEncuestaActivity.cliente_descripcion);
			intent.putExtra(ResumenActivity1.FECHA_ENCUESTA_KEY, encuestaTO.getFecha());
			context.startActivity(intent);
			
		}
	});
	
	holder.imgResumen5.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EncuestaTO encuestaTO = (EncuestaTO)getItem(position);			
			Intent intent = new Intent(context,ResumenActivity4.class);
			intent.putExtra(ResumenActivity4.CODIGO_CLIENTE_KEY, ConsultarEncuestaActivity.cliente_codigo);
			intent.putExtra(ResumenActivity4.CLIENTE_KEY, ConsultarEncuestaActivity.cliente_descripcion);
			intent.putExtra(ResumenActivity4.FECHA_ENCUESTA_KEY, encuestaTO.getFecha());
			context.startActivity(intent);
			
		}
	});
	
	holder.imgResumen6.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EncuestaTO encuestaTO = (EncuestaTO)getItem(position);			
			Intent intent = new Intent(context,ResumenActivity5.class);
			intent.putExtra(ResumenActivity5.CODIGO_CLIENTE_KEY, ConsultarEncuestaActivity.cliente_codigo);
			intent.putExtra(ResumenActivity5.CLIENTE_KEY, ConsultarEncuestaActivity.cliente_descripcion);
			intent.putExtra(ResumenActivity5.FECHA_ENCUESTA_KEY, encuestaTO.getFecha());
			context.startActivity(intent);
		}
	});
	
	return convertView;
}

static class ViewHolder {
	TextView txtFecha; 
	ImageButton imgResumen1;
	ImageButton imgResumen2;
	ImageButton imgResumen3;
	ImageButton imgResumen4;
	ImageButton imgResumen5;
	ImageButton imgResumen6;
}

@Override
public Filter getFilter() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public long getItemId(int position) {
	// TODO Auto-generated method stub
	return position;
}

@Override
public int getCount() {
	// TODO Auto-generated method stub
	// return data.length;
	if (encuestas == null) {
		return 0;
	} else {
		return encuestas.size();
	}
}

@Override
public Object getItem(int position) {
	// TODO Auto-generated method stub
	return encuestas.get(position);
}

}
}
