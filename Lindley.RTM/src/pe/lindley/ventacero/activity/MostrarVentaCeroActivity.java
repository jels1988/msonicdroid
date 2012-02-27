package pe.lindley.ventacero.activity;

import java.util.List;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.util.ListActivityBase;
import pe.lindley.ventacero.to.VentaCeroTO;
import pe.lindley.ventacero.ws.service.ObtenerVentaCeroProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

public class MostrarVentaCeroActivity extends ListActivityBase {

	@Inject ObtenerVentaCeroProxy obtenerVentaCeroProxy;  
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	
	public static final String CODIGO_DEPOSITO = "cod_deposito";
	public static final String CODIGO_SAP = "cod_sap";
	public static final String SEGMENTO = "cod_segmento";
	public static final String CODIGO_CLIENTE = "cod_cliente";
	public static final String NOMBRE_CLIENTE = "raz_cliente";
	public static final String DNI = "ruc_cliente";
	public static final String RUC = "dni_cliente";
	public static final String ANIO = "cod_anio";
	public static final String MES = "cod_mes";
	public static final String RUTA = "cod_ruta";
	public static final String SEMANA = "cod_semana";
	
	@InjectExtra(ANIO) 		String	anio;
	@InjectExtra(MES) 		String	mes;
	@InjectExtra(SEMANA) 	String	semana;
	@InjectExtra(RUTA) 		String	ruta;
	
	@InjectExtra(CODIGO_DEPOSITO) 	String	codigo_deposito;
	@InjectExtra(CODIGO_SAP) 		String	codigo_sap;
	@InjectExtra(SEGMENTO) 		 	String	segmento;
	@InjectExtra(CODIGO_CLIENTE) 	String	codigo_cliente;
	@InjectExtra(NOMBRE_CLIENTE)	String	nombre_cliente;
	@InjectExtra(DNI) 		 	 	String	dni;
	@InjectExtra(RUC) 		 	 	String	ruc;
	
	private EfficientAdapter adap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ventacero_mostrar_activity);
		mActionBar.setTitle(R.string.ventacero_mostrar_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
		

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		processAsync();
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub	
		/*obtenerVentaCeroProxy.setCodDeposito("C4");
		obtenerVentaCeroProxy.setCodSap("8650");
		obtenerVentaCeroProxy.setAnio("2011");
		obtenerVentaCeroProxy.setMes("09");
		obtenerVentaCeroProxy.setSemana("02");
		obtenerVentaCeroProxy.setCodRuta("29");
		obtenerVentaCeroProxy.setSegmento("");
		obtenerVentaCeroProxy.setCodCliente("");
		obtenerVentaCeroProxy.setNombreCliente("");
		obtenerVentaCeroProxy.setRuc("");
		obtenerVentaCeroProxy.setDni("");*/
		obtenerVentaCeroProxy.setCodDeposito(codigo_deposito);
		obtenerVentaCeroProxy.setCodSap(codigo_sap);
		
		obtenerVentaCeroProxy.setAnio(anio);
		obtenerVentaCeroProxy.setMes(mes);
		obtenerVentaCeroProxy.setSemana(semana);
		obtenerVentaCeroProxy.setCodRuta(ruta);
		
		obtenerVentaCeroProxy.setSegmento(segmento);
		obtenerVentaCeroProxy.setCodCliente(codigo_cliente);
		obtenerVentaCeroProxy.setNombreCliente(nombre_cliente);
		obtenerVentaCeroProxy.setRuc(ruc);
		obtenerVentaCeroProxy.setDni(dni);
		obtenerVentaCeroProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = obtenerVentaCeroProxy.isExito();
		if (isExito) {
			int status = obtenerVentaCeroProxy.getResponse().getStatus();
			if (status == 0) {
				List<VentaCeroTO> ventaCero = obtenerVentaCeroProxy.getResponse().getListaVentaCero();
				adap = new EfficientAdapter(this, ventaCero);
				setListAdapter(adap);
			}
			else  {
				showToast(obtenerVentaCeroProxy.getResponse().getDescripcion());
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

	public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private Context context;
	    private List<VentaCeroTO> detalles;
	    
	    public EfficientAdapter(Context context, List<VentaCeroTO> valores) {
		      // Cache the LayoutInflate to avoid asking for a new one each time.
		      mInflater = LayoutInflater.from(context);
		      this.context = context;
		      this.detalles = valores;
		    }
	    

	    /**
	     * Make a view to hold each row.
	     * 
	     * @see android.widget.ListAdapter#getView(int, android.view.View,
	     *      android.view.ViewGroup)
	     */
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	VentaCeroTO ventaCeroTO = (VentaCeroTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.ventacero_mostrar_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	    	
	        holder.txViewCodigo = (TextView) convertView.findViewById(R.id.txViewCodigo); 
	        holder.txViewCliente = (TextView) convertView.findViewById(R.id.txViewCliente);
	        holder.txViewDireccion =  (TextView) convertView.findViewById(R.id.txViewDireccion);
	        holder.txViewRuta =  (TextView) convertView.findViewById(R.id.txViewRuta);
	        holder.txViewSegmento =  (TextView) convertView.findViewById(R.id.txViewSegmento);
	        holder.txViewEqf =  (TextView) convertView.findViewById(R.id.txViewEqf);
	        holder.txViewCantidad =  (TextView) convertView.findViewById(R.id.txViewCantidad);
	        holder.txViewEstado =  (TextView) convertView.findViewById(R.id.txViewEstado);
	        holder.txViewMotivoActual =  (TextView) convertView.findViewById(R.id.txViewMotivoActual);
	        holder.txViewObsActual =  (TextView) convertView.findViewById(R.id.txViewObsActual);
	        holder.txViewMotivoAnterior =  (TextView) convertView.findViewById(R.id.txViewMotivoAnterior);
	        holder.txViewObsAnterior =  (TextView) convertView.findViewById(R.id.txViewObsAnterior);
	        //holder.txViewFigCom =  (TextView) convertView.findViewById(R.id.txViewFigCom);
	        holder.txViewEditar =  (TextView) convertView.findViewById(R.id.txViewEditar);
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewCodigo.setText(ventaCeroTO.getCodCliente());
	      holder.txViewCliente.setText(ventaCeroTO.getNombreCliente());
	      holder.txViewDireccion.setText(ventaCeroTO.getDireccion());
	      holder.txViewRuta.setText(ventaCeroTO.getCodRuta());
	      holder.txViewSegmento.setText(ventaCeroTO.getCodSegmento());
	      holder.txViewEqf.setText(ventaCeroTO.getFlequ());
	      holder.txViewCantidad.setText(ventaCeroTO.getCaeQu());
	      holder.txViewEstado.setText(ventaCeroTO.getStatus());
	      holder.txViewMotivoActual.setText(ventaCeroTO.getDescMotv());
	      holder.txViewObsActual.setText(ventaCeroTO.getObservacion());
	      holder.txViewMotivoAnterior.setText(ventaCeroTO.getDescMotvAnt());
	      holder.txViewObsAnterior.setText(ventaCeroTO.getObservacionAnt());
	      
	      /*holder.txViewFigCom.setOnClickListener(new OnClickListener() {
	    	  VentaCeroTO ventaCeroto = (VentaCeroTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent figuraComercial = new Intent(context,MostrarFiguraComercialActivity.class);
					figuraComercial.putExtra(MostrarFiguraComercialActivity.CODIGO_CLIENTE_KEY,ventaCeroto.getCodCliente());
					context.startActivity(figuraComercial);
				}
			});*/
	      
	      holder.txViewEditar.setOnClickListener(new OnClickListener() {
	    	  VentaCeroTO ventaCeroto = (VentaCeroTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent ventaCero = new Intent(context,DatosVentaCeroActivity.class);
					ventaCero.putExtra(DatosVentaCeroActivity.ANIO, ventaCeroto.getAnio());
					ventaCero.putExtra(DatosVentaCeroActivity.MES, ventaCeroto.getMes());
					ventaCero.putExtra(DatosVentaCeroActivity.SEMANA, ventaCeroto.getSemana());
					ventaCero.putExtra(DatosVentaCeroActivity.CODIGO_DEPOSITO, ventaCeroto.getCodDeposito());					
					ventaCero.putExtra(DatosVentaCeroActivity.CODIGO_CLIENTE, ventaCeroto.getCodCliente());
					ventaCero.putExtra(DatosVentaCeroActivity.NOMBRE_CLIENTE, ventaCeroto.getNombreCliente());
					ventaCero.putExtra(DatosVentaCeroActivity.DIRECCION_PLAN, ventaCeroto.getNombreCliente());
					ventaCero.putExtra(DatosVentaCeroActivity.RUTA, ventaCeroto.getCodRuta());
					ventaCero.putExtra(DatosVentaCeroActivity.SEGMENTO, ventaCeroto.getCodSegmento());
					ventaCero.putExtra(DatosVentaCeroActivity.MOTIVO_ACTUAL, ventaCeroto.getCodMotivo());
					ventaCero.putExtra(DatosVentaCeroActivity.OBSERVACION_ACTUAL, ventaCeroto.getObservacion());
					ventaCero.putExtra(DatosVentaCeroActivity.MOTIVO_ANTERIOR, ventaCeroto.getDescMotvAnt());
					ventaCero.putExtra(DatosVentaCeroActivity.OBSERVACION_ANTERIOR, ventaCeroto.getObservacionAnt());					
					context.startActivity(ventaCero);			
				}
			});
	      
	      return convertView;
	    }

		static class ViewHolder {
	    	TextView txViewCodigo;
	    	TextView txViewCliente;
	    	TextView txViewDireccion;
	    	TextView txViewRuta;	    	
	    	TextView txViewSegmento;
	    	TextView txViewEqf;
	    	TextView txViewCantidad;
	    	TextView txViewEstado;
	    	TextView txViewMotivoActual;
	    	TextView txViewObsActual;
	    	TextView txViewMotivoAnterior;
	    	TextView txViewObsAnterior;
	    	//TextView txViewFigCom;
	    	TextView txViewEditar;
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

