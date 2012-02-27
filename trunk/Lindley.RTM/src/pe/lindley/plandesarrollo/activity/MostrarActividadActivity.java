package pe.lindley.plandesarrollo.activity;
import java.util.Calendar;
import java.util.List;

import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.plandesarrollo.to.ActividadTO;
import pe.lindley.plandesarrollo.ws.service.ConsultarActividadProxy;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

public class MostrarActividadActivity extends ListActivityBase {
	
	@InjectView(R.id.actionBar) ActionBar mActionBar;
	@InjectView(R.id.txViewPlan)   TextView txViewPlan;
	@Inject ConsultarActividadProxy consultarActividadProxy;
	@InjectResource(R.string.pd_eliminar_actividad_error) public static String eliminar_actividad_error;
	
	public static final String CODIGO_CLIENTE = "codigo";
	public static final String CODIGO_PLAN = "plan";
	//public static final String NOMBRE_PLAN = "plan_nombre";
	private EfficientAdapter adap;
	
	private static String codigo_cliente = null;
	private static String codigo_plan = null;
	//private static String nombre_plan = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		codigo_cliente = intent.getStringExtra(CODIGO_CLIENTE);
		codigo_plan = intent.getStringExtra(CODIGO_PLAN);
		//nombre_plan = intent.getStringExtra(NOMBRE_PLAN);
		setContentView(R.layout.plandesarrollo_actividades_activity);
		final RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		mActionBar.setTitle(R.string.pd_mostrar_ctividad_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		//txViewPlan.setText(nombre_plan);
		txViewPlan.setVisibility(View.GONE);
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
		consultarActividadProxy.setCodigoCliente(codigo_cliente);
		consultarActividadProxy.setCodigoPlan(codigo_plan);
		consultarActividadProxy.execute();
	}
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarActividadProxy.isExito();
		if (isExito) {
			int status = consultarActividadProxy.getResponse().getStatus();
			if (status == 0) {
				List<ActividadTO> actividades = consultarActividadProxy.getResponse().getActividades();
				adap = new EfficientAdapter(this, actividades);
				setListAdapter(adap);
			}
			else  {
				showToast(consultarActividadProxy.getResponse().getDescripcion());
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
		Intent datosActividad = new Intent(this,DatosActividadActivity.class);
		datosActividad.putExtra(DatosActividadActivity.TIPO_ACCION, DatosActividadActivity.ACCION_NUEVO);
		datosActividad.putExtra(DatosActividadActivity.CODIGO_CLIENTE, codigo_cliente);
		datosActividad.putExtra(DatosActividadActivity.CODIGO_PLAN, codigo_plan);
		startActivity(datosActividad);
	}
	
	public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private Context context;
	    private List<ActividadTO> detalles;
	    
	    public EfficientAdapter(Context context, List<ActividadTO> valores) {
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
	    	ActividadTO actividadesTO = (ActividadTO) getItem(position);
	      ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.plandesarrollo_actividades_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	          
	        holder.txViewCodigo = (TextView) convertView.findViewById(R.id.txViewCodigo); 
	        holder.txViewAct = (TextView) convertView.findViewById(R.id.txViewAct);
	        holder.txViewFecTnt =  (TextView) convertView.findViewById(R.id.txViewFecTnt);
	        holder.chckTerminado =  (CheckBox) convertView.findViewById(R.id.chckTerminado);
	        holder.txViewFchEje =  (TextView) convertView.findViewById(R.id.txViewFchEje);	        
	        holder.txViewSustento = (TextView) convertView.findViewById(R.id.txViewSustento);  
	        holder.txViewResponsable = (TextView) convertView.findViewById(R.id.txViewResponsable);   
	        holder.imgSustento = (ImageView) convertView.findViewById(R.id.imgSustento);  
	        holder.txViewEliminar = (TextView) convertView.findViewById(R.id.txViewEliminar); 
	        holder.txViewEditar  = (TextView) convertView.findViewById(R.id.txViewEditar);
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewCodigo.setText(actividadesTO.getCodigoActividad());     		  
	      holder.txViewAct.setText(actividadesTO.getDescripcionActividad());
	      
	      int mYear,mMonth,mDay;
	      String fecha = actividadesTO.getFechaTentativa();
	      if(fecha.length() > 7)
	      {
	    	  mYear =  Integer.parseInt(fecha.substring(0, 4));
	    	  mMonth  =  Integer.parseInt(fecha.substring(4, 6));
	    	  mDay  =  Integer.parseInt(fecha.substring(6));
	    	  holder.txViewFecTnt.setText(mDay+"/"+mMonth+"/"+mYear);
	     }
	      else
	    	  holder.txViewFecTnt.setText("0");
	      
	      fecha = actividadesTO.getFechaEjecucion();
	      if(fecha.length() > 7)
	      {
	    	  mYear =  Integer.parseInt(fecha.substring(0, 4));
	    	  mMonth  =  Integer.parseInt(fecha.substring(4, 6));
	    	  mDay  =  Integer.parseInt(fecha.substring(6));
	    	  holder.txViewFchEje.setText(mDay+"/"+mMonth+"/"+mYear);
	     }
	      else
	    	  holder.txViewFchEje.setText("0");
	      
	      if(Integer.parseInt(actividadesTO.getEstado().trim())==2)
	      {
	    	  holder.chckTerminado.setChecked(true);	    	  
	      }
	      holder.chckTerminado.setEnabled(false);
	      if(Integer.parseInt(actividadesTO.getCantidadSustento())<=0)
	      {
	    	  holder.imgSustento.setImageResource(R.drawable.icon_rojo);
	      }
	      else
	      {
	    	  holder.imgSustento.setImageResource(R.drawable.icon_verde);
	      }
	      
	      if(actividadesTO.getResponsables().trim().equals(""))
	      {
	    	  holder.txViewResponsable.setText(Html.fromHtml("<a href=''>-----</a>"));
	      }
	      else
	      {
	    	  holder.txViewResponsable.setText(Html.fromHtml("<a href=''>" +actividadesTO.getResponsables()+"</a>"));
	      }
	      
	      holder.txViewEliminar.setOnClickListener(new OnClickListener() {
	    	  ActividadTO actividad = (ActividadTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					System.out.println(actividad.getFechaCreacion());
				    System.out.println(obtenerFecha());
					if(actividad.getFechaCreacion().equals(obtenerFecha()))
				    {
						Intent elimActividad = new Intent(context,EliminarActividadActivity.class);
						elimActividad.putExtra(EliminarActividadActivity.CODIGO_ACTIVIDAD, actividad.getCodigoActividad());						
						context.startActivity(elimActividad);
				    }
					else
					{
						Toast.makeText(context,eliminar_actividad_error,Toast.LENGTH_SHORT).show();
					}					
				}
			});
	      
	      holder.txViewEditar.setOnClickListener(new OnClickListener() {
	    	  ActividadTO actividad = (ActividadTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent editActividad = new Intent(context,DatosActividadActivity.class);
					editActividad.putExtra(DatosActividadActivity.TIPO_ACCION, DatosActividadActivity.ACCION_ACTUALIZAR);
					editActividad.putExtra(DatosActividadActivity.CODIGO_CLIENTE, codigo_cliente);
					editActividad.putExtra(DatosActividadActivity.CODIGO_PLAN, codigo_plan);
					editActividad.putExtra(DatosActividadActivity.CODIGO_ACTIVIDAD, actividad.getCodigoActividad());
					editActividad.putExtra(DatosActividadActivity.DESCRIPCION_ACTIVIDAD, actividad.getDescripcionActividad());		
					editActividad.putExtra(DatosActividadActivity.CANT_SUSTENTO, Integer.parseInt(actividad.getCantidadSustento().trim()));		
					editActividad.putExtra(DatosActividadActivity.FECHA_TENTATIVA, actividad.getFechaTentativa());
					editActividad.putExtra(DatosActividadActivity.ESTADO, Integer.parseInt(actividad.getEstado().trim()));		
					context.startActivity(editActividad);				
				}
			});
	      
	      holder.txViewResponsable.setOnClickListener(new OnClickListener() {
	    	  ActividadTO actividad = (ActividadTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent mostrarResponsable = new Intent(context,MostrarResponsableActivity.class);
					mostrarResponsable.putExtra(MostrarResponsableActivity.CODIGO_CLIENTE, codigo_cliente);
					mostrarResponsable.putExtra(MostrarResponsableActivity.CODIGO_PLAN, codigo_plan);
					mostrarResponsable.putExtra(MostrarResponsableActivity.CODIGO_ACTIVIDAD, actividad.getCodigoActividad());		
					context.startActivity(mostrarResponsable);				
				}
			});
	      
	      holder.txViewSustento.setOnClickListener(new OnClickListener() {
	    	  ActividadTO actividad = (ActividadTO)getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent mostrarSustento = new Intent(context,MostrarSustentoActivity.class);
					mostrarSustento.putExtra(MostrarSustentoActivity.CODIGO_CLIENTE, codigo_cliente);
					mostrarSustento.putExtra(MostrarSustentoActivity.CODIGO_PLAN, codigo_plan);
					mostrarSustento.putExtra(MostrarSustentoActivity.CODIGO_ACTIVIDAD, actividad.getCodigoActividad());		
					context.startActivity(mostrarSustento);				
				}
			});
	      
	      return convertView;
	    }

	    private Object obtenerFecha() {
	    	final Calendar c = Calendar.getInstance();
		    int mYear = c.get(Calendar.YEAR);
	    	int mMonth = c.get(Calendar.MONTH)+1;
	    	int mDay = c.get(Calendar.DAY_OF_MONTH);
	    	String sMonth="0",sDay="0";
	    	
	    	if(mMonth<10)
		        sMonth="0"+mMonth;
	    	else
	    		sMonth=""+mMonth;
	    	
	    	if(mDay<10)
	    		sDay="0"+mDay;
	    	else
	    		sDay=""+mDay;
	    	
	    	return mYear+sMonth+sDay;
		}

		static class ViewHolder {
	    	TextView txViewCodigo;
	    	TextView txViewAct;
	    	TextView txViewResponsable;
	    	TextView txViewFecTnt;	    	
	    	CheckBox chckTerminado;
	    	TextView txViewFchEje;	
	    	TextView txViewSustento;
	    	ImageView imgSustento;
	    	TextView txViewEliminar;
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
