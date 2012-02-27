package pe.lindley.ficha.activity;

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
import pe.lindley.activity.RTMApplication;
import pe.lindley.ficha.to.EncuestaResumenTO;
import pe.lindley.ficha.ws.service.ConsultarEncuestasProxy;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class ConsultarEncuestaActivity extends ListActivityBase {

	public static final String CODIGO_CLIENTE_KEY="codigo_cliente";
	public static final String CLIENTE_KEY="cliente_descripcion";
	public static final String NUEVA_ENCUESTA = "0";
	public static final String OBTENER_ENCUESTA = "2";
	
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@InjectResource(R.string.ficha_descargarparametros_activity_confirm_dialog_si) 		public static String 	confirm_si;
	@InjectResource(R.string.ficha_descargarparametros_activity_confirm_dialog_no) 		public static String 	confirm_no;
	@InjectResource(R.string.ficha_cerrar_encuesta_activity_confirm_dialog_message) 	public static String 	confirm_message;
	@InjectResource(R.string.ficha_cerrar_encuesta_activity_title) 	public static String ficha_descargar_parametros_title;
	
	@InjectExtra(CODIGO_CLIENTE_KEY) String	cliente_codigo;
	@InjectExtra(CLIENTE_KEY)
	static 		String	cliente_descripcion;
	
	
	
	@Inject	ConsultarEncuestasProxy consultarEncuestaProxy ;
	
	private EfficientAdapter adap;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.ficha_consultarencuesta_activity);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		mActionBar.setTitle(R.string.consultar_encuesta_title);
	    mActionBar.setHomeLogo(R.drawable.header_logo);		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
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
				List<EncuestaResumenTO> encuestas = consultarEncuestaProxy.getResponse().getEncuestas();
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
	
	
	public void btnAgregar_click(View view)
	{
		Intent i = new Intent(this, FichaEncuestaActivity.class);		
		i.putExtra(FichaEncuestaActivity.TIPO_ENCUESTA, NUEVA_ENCUESTA);
		i.putExtra(FichaEncuestaActivity.CODIGO_CLIENTE_KEY, cliente_codigo);
		i.putExtra(FichaEncuestaActivity.CLIENTE_KEY, cliente_descripcion);
		i.putExtra(FichaEncuestaActivity.ESTADO_ENCUESTA, "0");
		i.putExtra(FichaEncuestaActivity.ENCUESTA_KEY, "");
		
		startActivity(i);	
	}
	
	
	
	public static class EfficientAdapter extends BaseAdapter implements Filterable {
	private LayoutInflater mInflater;
	private Context context;
	private List<EncuestaResumenTO> encuestas;

	public EfficientAdapter(Context context, List<EncuestaResumenTO> encuestas) {
		// Cache the LayoutInflate to avoid asking for a new one each time.
		mInflater = LayoutInflater.from(context);
		this.context = context;
		this.encuestas = encuestas;
	}


/**
 * Make a view to hold each row.
 * 
 * @see android.widget.ListAdapter#getView(int, android.view.View,
 *      android.view.ViewGroup)
 */
public View getView(final int position, View convertView,
		ViewGroup parent) {
	EncuestaResumenTO encuestaTO = (EncuestaResumenTO) getItem(position);
	ViewHolder holder;

	if (convertView == null) {
		convertView = mInflater.inflate(R.layout.ficha_consultarencuesta_content, null);

		// Creates a ViewHolder and store references to the two children
		// views
		// we want to bind data to.
		holder = new ViewHolder();
		holder.txtFecha = (TextView) convertView.findViewById(R.id.txtFecha);
		holder.btn_encuesta = (ImageButton)convertView.findViewById(R.id.btn_encuesta);
		holder.btn_cerrar = (ImageButton)convertView.findViewById(R.id.btn_cerrar);
		
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
	
	int mYear,mMonth,mDay;
    String fecha = encuestaTO.getFechaCreacion();
    if(fecha.length() > 7)
    {
  	  mYear =  Integer.parseInt(fecha.substring(0, 4));
  	  mMonth  =  Integer.parseInt(fecha.substring(4, 6));
  	  mDay  =  Integer.parseInt(fecha.substring(6));
  	  holder.txtFecha.setText(mDay+"/"+mMonth+"/"+mYear);
   }
    else
  	  holder.txtFecha.setText("0");
    
	
	if(encuestaTO.getEstado().equals("2"))
	{
		holder.btn_cerrar.setImageResource(R.drawable.btn_candado);
		holder.btn_cerrar.setEnabled(false);
	}
	else
	{
		holder.btn_cerrar.setImageResource(R.drawable.btn_candado_open);
	}
	
	holder.btn_encuesta.setOnClickListener(new OnClickListener() {
		EncuestaResumenTO encuestaTO = (EncuestaResumenTO) getItem(position);		
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(context, FichaEncuestaActivity.class);
			
			i.putExtra(FichaEncuestaActivity.CODIGO_CLIENTE_KEY, encuestaTO.getCodigoCliente());
			i.putExtra(FichaEncuestaActivity.ESTADO_ENCUESTA, encuestaTO.getEstado());
			i.putExtra(FichaEncuestaActivity.CLIENTE_KEY, cliente_descripcion);
			i.putExtra(FichaEncuestaActivity.ENCUESTA_KEY, encuestaTO.getCodigoEncuesta());
			i.putExtra(FichaEncuestaActivity.TIPO_ENCUESTA, OBTENER_ENCUESTA);
			context.startActivity(i);
		}
	});
	
	
	holder.btn_cerrar.setOnClickListener(new OnClickListener() {
		EncuestaResumenTO encuestaTO = (EncuestaResumenTO) getItem(position);		
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(context, CerrarEncuestaActivity.class);
			
			i.putExtra(CerrarEncuestaActivity.CODIGO_ENCUESTA, encuestaTO.getCodigoEncuesta());
			context.startActivity(i);
			
			//Intent intent = ChartFactory.getLineChartIntent(this, getDemoDataset(mostrarDashboardCdaProxy.getResponse().getDashBoard().getDetalle()), getDemoRenderer(colorBelowLine,titulo));
			// View view = super.getLocalActivityManager().startActivity("ReferenceName", i).getDecorView();
			// this.setContentView(view);
		}
	});
	
	
	
	
	return convertView;
}

static class ViewHolder {
	TextView txtFecha;
	ImageButton btn_encuesta;
	ImageButton btn_cerrar;
	
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
