package pe.lindley.plandesarrollo.activity;

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
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.plandesarrollo.negocio.ParametroBLL;
import pe.lindley.plandesarrollo.to.PlanDesarrolloTO;
import pe.lindley.plandesarrollo.ws.service.ConsultarPlanProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class MostrarPlanDesarrolloActivity extends ListActivityBase {

	@Inject ParametroBLL           		parametroBLL;
	
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@Inject ConsultarPlanProxy consultarPlanProxy;
	public static final String CODIGO_CLIENTE = "codigo";
	public static final String ESTADO_ABIERTO = "Abierto";
	public static final String ESTADO_CERRADO = "Cerrado";
	private EfficientAdapter adap;
	
	private static String codigo_cliente = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		codigo_cliente = intent.getStringExtra(CODIGO_CLIENTE);
		
		setContentView(R.layout.plandesarrollo_principal_activity);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		mActionBar.setTitle(R.string.pd_mostrar_principal_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
	}
	
	/*
	private void llenar_parametro() {
		// TODO Auto-generated method stub
		parametroBLL.deleteAll();
		String json="{'PRM':[{'CTBL':'01','COD':'10','DES':'DESARROLLADOR RTM','NCRT':'DDMM','NLRG':'DDMM RTM'},{'CTBL':'01','COD':'11','DES':'MERCADERISTA RTM','NCRT':'MERC','NLRG':'MERC RTM'},{'CTBL':'01','COD':'14','DES':'VENDEDOR','NCRT':'VEND','NLRG':'VENDEDOR'},{'CTBL':'01','COD':'50','DES':'CLIENTE','NCRT':'CLIE','NLRG':'CLIENTE'},{'CTBL':'02','COD':'01','DES':'VISITA','NCRT':'VIS','NLRG':'VISITA'},{'CTBL':'02','COD':'02','DES':'JUSTIFICACION','NCRT':'JUST','NLRG':'JUSTIFICAC'},{'CTBL':'02','COD':'03','DES':'SUSTENTO','NCRT':'SUST','NLRG':'SUSTENTO'}],'STS':0,'DES':null}";
		DescargarParametrosResponse descargarParametrosRequest = JSONHelper.desSerializar(json, DescargarParametrosResponse.class);

		List<ParametroTO> parametros =	descargarParametrosRequest.getParametros();
						if(parametros!=null){
							parametroBLL.save(parametros);							
						}
	}
	*/
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		processAsync();		
	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		consultarPlanProxy.setCodigo(codigo_cliente);
		consultarPlanProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarPlanProxy.isExito();
		if (isExito) {
			int status = consultarPlanProxy.getResponse().getStatus();
			if (status == 0) {
				List<PlanDesarrolloTO> plan = consultarPlanProxy.getResponse().getpLanDesarrollo();
				adap = new EfficientAdapter(this, plan);
				setListAdapter(adap);
			}
			else  {
				showToast(consultarPlanProxy.getResponse().getDescripcion());
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
	
	public void btnAgregar_click(View view)
	{
		Intent datosPlan = new Intent(this,DatosPlanActivity.class);
		datosPlan.putExtra(DatosPlanActivity.TIPO_ACCION, DatosPlanActivity.ACCION_NUEVO);
		datosPlan.putExtra(DatosPlanActivity.CODIGO_CLIENTE, codigo_cliente);
		startActivity(datosPlan);
	}
	
	public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private Context context;
	    private List<PlanDesarrolloTO> detalles;
	    
	    public EfficientAdapter(Context context, List<PlanDesarrolloTO> valores) {
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
	    	PlanDesarrolloTO planTO = (PlanDesarrolloTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.plandesarrollo_principal_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        
	        holder.txViewCodigo = (TextView) convertView.findViewById(R.id.txViewCodigo);
	        holder.txViewAvance = (TextView) convertView.findViewById(R.id.txViewAvance); 
	        holder.txViewPlan = (TextView) convertView.findViewById(R.id.txViewPlan);
	        holder.txViewObjetivo =  (TextView) convertView.findViewById(R.id.txViewObjetivo);
	        holder.txViewFchCreacion =  (TextView) convertView.findViewById(R.id.txViewFchCreacion);
	        holder.txViewFecInicio = (TextView) convertView.findViewById(R.id.txViewFecInicio);
	        holder.txViewFecFin = (TextView) convertView.findViewById(R.id.txViewFecFin);
	        holder.txViewEstado = (TextView) convertView.findViewById(R.id.txViewEstado);
	        holder.txViewVer = (TextView) convertView.findViewById(R.id.txViewVer);
	        holder.txViewEditar = (TextView) convertView.findViewById(R.id.txViewEditar);
	        
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      
	      
	      holder.txViewCodigo.setText(planTO.getCodigoPLan());  
	      holder.txViewAvance.setText(planTO.getPorcentajeAvance()+" %");
	      holder.txViewPlan.setText(planTO.getDescripcionPLan());		      
	      holder.txViewObjetivo.setText(planTO.getDescripcionObjetivo());  
	      
	      int mYear=0,mMonth=0,mDay=0;
	      String fecha = planTO.getFechaCreacion();
	      if(fecha.length() > 7)
	      {
	    	  mYear =  Integer.parseInt(fecha.substring(0, 4));
	    	  mMonth  =  Integer.parseInt(fecha.substring(4, 6));
	    	  mDay  =  Integer.parseInt(fecha.substring(6));
	    	  holder.txViewFchCreacion.setText(mDay+"/"+mMonth+"/"+mYear);
	     }
	      else
	    	  holder.txViewFchCreacion.setText("0");
	      
	      fecha = planTO.getFechaInicio();
	      if(fecha.length() > 7)
	      {
	    	  mYear =  Integer.parseInt(fecha.substring(0, 4));
	    	  mMonth  =  Integer.parseInt(fecha.substring(4, 6));
	    	  mDay  =  Integer.parseInt(fecha.substring(6));
	    	  holder.txViewFecInicio.setText(mDay+"/"+mMonth+"/"+mYear);
	     }
	      else
	    	  holder.txViewFecInicio.setText("0");
	      
	      fecha = planTO.getFechaFin();
	      if(fecha.length() > 7)
	      {
	    	  mYear =  Integer.parseInt(fecha.substring(0, 4));
	    	  mMonth  =  Integer.parseInt(fecha.substring(4, 6));
	    	  mDay  =  Integer.parseInt(fecha.substring(6));
	    	  holder.txViewFecFin.setText(mDay+"/"+mMonth+"/"+mYear);
	     }
	      else
	    	  holder.txViewFecFin.setText("0");
	      
	      
	      String estado = "";
	      if(planTO.getEstado().equals("1"))
	    	  estado = ESTADO_ABIERTO;
	      else 
	    	  estado = ESTADO_CERRADO;
	      holder.txViewEstado.setText(estado);
	      
	  
	      holder.txViewVer.setOnClickListener(new OnClickListener() {
	    	  PlanDesarrolloTO planTemporal = (PlanDesarrolloTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,MostrarActividadActivity.class);
					profit.putExtra(MostrarActividadActivity.CODIGO_CLIENTE, codigo_cliente);
					profit.putExtra(MostrarActividadActivity.CODIGO_PLAN, planTemporal.getCodigoPLan());
					//profit.putExtra(MostrarActividadActivity.NOMBRE_PLAN, planTemporal.getDescripcionPLan());
					context.startActivity(profit);
				}
			});
	      
	      holder.txViewEditar.setOnClickListener(new OnClickListener() {
	    	  PlanDesarrolloTO planTemporal = (PlanDesarrolloTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent datosPlan = new Intent(context,DatosPlanActivity.class);
					datosPlan.putExtra(DatosPlanActivity.TIPO_ACCION, DatosPlanActivity.ACCION_ACTUALIZAR);
					datosPlan.putExtra(DatosPlanActivity.CODIGO_CLIENTE, codigo_cliente);
					datosPlan.putExtra(DatosPlanActivity.CODIGO_PLAN, planTemporal.getCodigoPLan());					
					datosPlan.putExtra(DatosPlanActivity.DESCRIPCION_PLAN, planTemporal.getDescripcionPLan());
					datosPlan.putExtra(DatosPlanActivity.DESCRIPCION_OBJETIVO, planTemporal.getDescripcionObjetivo());
					datosPlan.putExtra(DatosPlanActivity.CODIGO_ESTADO, planTemporal.getEstado());
					datosPlan.putExtra(DatosPlanActivity.PORCENTAJE_AVANCE, planTemporal.getPorcentajeAvance());
					datosPlan.putExtra(DatosPlanActivity.DATA_FECHA_CREACION, planTemporal.getFechaCreacion());
					datosPlan.putExtra(DatosPlanActivity.DATA_FECHA_FIN, planTemporal.getFechaFin());
					datosPlan.putExtra(DatosPlanActivity.DATA_FECHA_INICIO, planTemporal.getFechaInicio());
					context.startActivity(datosPlan);
				}
			});
	            
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewCodigo;
	    	TextView txViewAvance;
	    	TextView txViewPlan;
	    	TextView txViewObjetivo;
	    	TextView txViewFchCreacion;
	    	TextView txViewFecInicio;
	    	TextView txViewFecFin;
	    	TextView txViewEstado;
	    	TextView txViewVer;	    	
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
