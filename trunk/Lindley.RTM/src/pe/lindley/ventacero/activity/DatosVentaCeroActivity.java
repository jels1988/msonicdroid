package pe.lindley.ventacero.activity;

import java.util.List;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Spinner;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.ficha.to.FiguraComercialTO;
import pe.lindley.ficha.ws.service.ObtenerFiguraComercialProxy;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.util.ListActivityBase;
import pe.lindley.util.MessageBox;
import pe.lindley.ventacero.to.ParametroTO;
import pe.lindley.ventacero.ws.service.GuardarVtaCeroMotivoProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class DatosVentaCeroActivity extends ListActivityBase {

	@Inject GuardarVtaCeroMotivoProxy guardarVtaCeroMotivoProxy;
	@Inject ObtenerFiguraComercialProxy obtenerFiguraComercialProxy;
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@InjectResource(R.string.ventacero_guardado_ok) String guardado_ok;
	@InjectResource(R.string.ventacero_guardar_motivo_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.ventacero_guardar_motivo_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.ventacero_guardar_motivo_activity_confirm_dialog_message) 			String 	confirm_message;
	@InjectResource(R.string.ventacero_guardar_motivo_activity_title) 	String 	ficha_guardar_motivo_title;
	
	@InjectView(R.id.txtCodigo)     	EditText txtCodigo;
	@InjectView(R.id.txtNombre)     	EditText txtNombre;
	@InjectView(R.id.txtDireccion)      EditText txtDireccion;
	@InjectView(R.id.txtRuta)     		EditText txtRuta;
	@InjectView(R.id.txtSegmento)     	EditText txtSegmento;
	@InjectView(R.id.cboMotActual) 		Spinner cboMotActual;
	@InjectView(R.id.txtObsActual)     	EditText txtObsActual;
	@InjectView(R.id.txtMotAnterior)    EditText txtMotAnterior;
	@InjectView(R.id.txtObsAnterior)    EditText txtObsAnterior;	
	
	protected static final String LISTA_MOTIVO =  "01";
	protected static final int ACCION_GUARDAR =  1;
	
	protected static final String ANIO   = "anio";
	protected static final String MES    = "mes";
	protected static final String SEMANA = "semana";
	protected static final String CODIGO_DEPOSITO = "cod_deposito";
	
	protected static final String CODIGO_CLIENTE = "cod_cliente";
	protected static final String NOMBRE_CLIENTE = "nom_cliente";
	protected static final String DIRECCION_PLAN = "direc_cliente";
	protected static final String RUTA = "cod_ruta";
	protected static final String SEGMENTO = "cod_segmento";
	protected static final String MOTIVO_ACTUAL = "mot_actual";
	protected static final String OBSERVACION_ACTUAL = "obs_actual";
	protected static final String MOTIVO_ANTERIOR = "mot_anterior";
	protected static final String OBSERVACION_ANTERIOR = "obs_anterior";
	
	@InjectExtra(ANIO)	 		 	String	vtacero_anio;
	@InjectExtra(MES) 			 	String	vtacero_mes;
	@InjectExtra(SEMANA) 			String	vtacero_semana;
	@InjectExtra(CODIGO_DEPOSITO) 	String	codigo_deposito;
	
	@InjectExtra(CODIGO_CLIENTE) 		 String	codigo_cliente;
	@InjectExtra(NOMBRE_CLIENTE) 		 String	nombre_cliente;
	@InjectExtra(DIRECCION_PLAN) 		 String	direccion_cliente;
	@InjectExtra(RUTA) 			 		 String	codigo_ruta;
	@InjectExtra(SEGMENTO) 		 		 String	codigo_segmento;
	@InjectExtra(MOTIVO_ACTUAL) 		 String	motivo_actual;
	@InjectExtra(OBSERVACION_ACTUAL) 	 String	observacion_actual;
	@InjectExtra(MOTIVO_ANTERIOR) 		 String	motivo_anterior;
	@InjectExtra(OBSERVACION_ANTERIOR) 	 String	observacion_anterior;
		
	private EfficientAdapter adap;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ventacero_datos_activity);
		mActionBar.setTitle(R.string.vtacero_datos_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		txtCodigo.setKeyListener(null);
		txtNombre.setKeyListener(null);
		txtDireccion.setKeyListener(null);
		txtRuta.setKeyListener(null);
		txtSegmento.setKeyListener(null);		
		txtMotAnterior.setKeyListener(null);
		txtObsAnterior.setKeyListener(null);
		
		txtCodigo.setText(codigo_cliente);
		txtNombre.setText(nombre_cliente);
		txtDireccion.setText(direccion_cliente);
		txtRuta.setText(codigo_ruta);
		txtSegmento.setText(codigo_segmento);
		txtMotAnterior.setText(motivo_anterior);
		txtObsAnterior.setText(observacion_anterior);
		txtObsActual.setText(observacion_actual);
				
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		cboMotActual.setAdapter(application.getAdapterParametrosVentaCero(LISTA_MOTIVO));
		
		if(!motivo_actual.equals("") && motivo_actual!=null)
		{
			cboMotActual.setSelection(application.getAdapterParametrosVentaCero(LISTA_MOTIVO).findByValue(motivo_actual));
		}		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		processAsync();
		super.onStart();
	}

	public void btnGuardar_onclick(View view)
	{
		final Context context = this;
	
		MessageBox.showConfirmDialog(context, 
			ficha_guardar_motivo_title, 
			confirm_message, 
			confirm_si,
			new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					processAsync(ACCION_GUARDAR);
				}
			},
			confirm_no,
			null);		
	}
	
	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_GUARDAR)
		{
			RTMApplication application = (RTMApplication)getApplicationContext();
			UsuarioTO usuario = application.getUsuarioTO();
			
			guardarVtaCeroMotivoProxy.setAnio(vtacero_anio);
			guardarVtaCeroMotivoProxy.setMes(vtacero_mes);
			guardarVtaCeroMotivoProxy.setSemana(vtacero_semana);		
			guardarVtaCeroMotivoProxy.setCodCliente(codigo_cliente);
			guardarVtaCeroMotivoProxy.setCodDeposito(codigo_deposito);		 
			guardarVtaCeroMotivoProxy.setMotivo(((ParametroTO)cboMotActual.getSelectedItem()).getCodigo());
			guardarVtaCeroMotivoProxy.setObservacion(txtObsActual.getText().toString());		
			guardarVtaCeroMotivoProxy.setUsuario(usuario.getCodigoSap());
			guardarVtaCeroMotivoProxy.execute();
		}
	}
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		obtenerFiguraComercialProxy.setCliente(codigo_cliente);
		obtenerFiguraComercialProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = obtenerFiguraComercialProxy.isExito();
		if (isExito) {
			int status = obtenerFiguraComercialProxy.getResponse().getStatus();
			if (status == 0) {
				List<FiguraComercialTO> figuraComercial = obtenerFiguraComercialProxy
						.getResponse().getFiguracomercial();
				adap = new EfficientAdapter(this, figuraComercial);
				setListAdapter(adap);
			}
			else  {
				showToast(obtenerFiguraComercialProxy.getResponse().getDescripcion());
				}			
		}
		else{
			processError();
		}
		super.processOk();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}

	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_GUARDAR)
		{
			 boolean isExito = guardarVtaCeroMotivoProxy.isExito();
				if (isExito) {
					int status = guardarVtaCeroMotivoProxy.getResponse().getStatus();
					if (status == 0) {
						showToast(guardado_ok);
					}
					else  {
						showToast(guardarVtaCeroMotivoProxy.getResponse().getDescripcion());
					}
				}
				else{
					processError();
				}
			super.processOk();
			finish();
		}
	}
	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError(accion);
		showToast(error_generico_process);
	}
	
	
	
	//-----------
	public static class EfficientAdapter extends BaseAdapter implements Filterable {

	    private LayoutInflater mInflater;
	    private List<FiguraComercialTO> detalles;
	    
	    public EfficientAdapter(Context context, List<FiguraComercialTO> valores) {
		      // Cache the LayoutInflate to avoid asking for a new one each time.
		      mInflater = LayoutInflater.from(context);
		      this.detalles = valores;
		    }
	    

	    /**
	     * Make a view to hold each row.
	     * 
	     * @see android.widget.ListAdapter#getView(int, android.view.View,
	     *      android.view.ViewGroup)
	     */
	    @Override
		public View getView(final int position, View convertView, ViewGroup parent) {
	    	FiguraComercialTO historyValueTO = (FiguraComercialTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.ventacero_datos_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        holder.txtId = (TextView) convertView.findViewById(R.id.txtId);
	        holder.txtValor = (TextView) convertView.findViewById(R.id.txtValor);
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      
	      holder.txtId.setText(historyValueTO.getDescripcion());
	      holder.txtValor.setText(historyValueTO.getValor());
	      
	      return convertView;
	    }

	    static class ViewHolder {
	    	TextView txtId;
	    	TextView txtValor;
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
	      //return data.length;
	    	if(detalles==null){
	    		return 0;
	    	}else{
	    		return detalles.size();
	    	}
	    }

	    @Override
	    public Object getItem(int position) {
	      // TODO Auto-generated method stub
	    	return detalles.get(position);
	    }

	  }
	
}
