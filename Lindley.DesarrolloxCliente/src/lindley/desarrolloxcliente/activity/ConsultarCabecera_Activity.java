package lindley.desarrolloxcliente.activity;

import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.DesarrolloClienteTO;
import lindley.desarrolloxcliente.ws.service.ActualizarEstadoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarCabeceraProxy;
import net.msonic.lib.ActivityUtil;
import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MessageBox;
import roboguice.inject.InjectView;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

public class ConsultarCabecera_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarCabeceraProxy ConsultarCabeceraProxy;
	@Inject ActualizarEstadoProxy actualizarEstadoProxy;
	
	private EfficientAdapter adap;
	ClienteTO cliente;
	public static MyApplication application;
	
	public static final String FLAG_OPEN_FECHA_ABIERTO = "1";
	public static final String FLAG_OPEN_FECHA_CERRADA = "2";
	
	
	public static final int ACCION_ELIMINAR = 1;
	
	
	public static String codigoElimnar;
	
	List<DesarrolloClienteTO> cabecera;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cabeceracliente_activity);        
        mActionBar.setTitle(R.string.cabeceracliente_activity_title);
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigo() + " - " + cliente.getNombre());
        mActionBar.setHomeLogo(R.drawable.header_logo);
    }

    
    @Override
    public void onBackPressed() {
    // check if page 2 is open
    	//finish();
    	Intent intent = new Intent("lindley.desarrolloxcliente.consultarcliente");
    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
//    	finish();
    }
    
    @Override
	protected void onStart() {
		// TODO Auto-generated method stub
    	processAsync();
		super.onStart();
	}

	@Override
	protected void process() {
    	ConsultarCabeceraProxy.setCodigoCliente(cliente.getCodigo());
    	ConsultarCabeceraProxy.execute();
	}
    
    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = ConsultarCabeceraProxy.isExito();
		if (isExito) {
			int status = ConsultarCabeceraProxy.getResponse().getStatus();
			if (status == 0) {
				List<DesarrolloClienteTO> cabecera = ConsultarCabeceraProxy
						.getResponse().getListaDesarrolloCliente();
				adap = new EfficientAdapter(this, cabecera);
				setListAdapter(adap);
			}
			else  {
				showToast(ConsultarCabeceraProxy.getResponse().getDescripcion());
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
	protected void process(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_ELIMINAR)
		{
			actualizarEstadoProxy.codigo = codigoElimnar;
			actualizarEstadoProxy.estado = "E";
			actualizarEstadoProxy.execute();
		}
	}
	
	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_ELIMINAR)
		{
			boolean isExito = actualizarEstadoProxy.isExito();
			if (isExito) {
				int status = actualizarEstadoProxy.getResponse().getStatus();
				if (status == 0) {
					showToast("Registro eliminado correctamente.");					
				}
				else  {
					showToast(actualizarEstadoProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError();
			}
			super.processOk();
			processAsync();
		}
	}
	
	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError(accion);
		if(accion == ACCION_ELIMINAR)
		{
			showToast(error_generico_process);
		}
	}
	
	
	
	
	
	public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private Context context;
	    private List<DesarrolloClienteTO> detalles;
	    
	    public EfficientAdapter(Context context, List<DesarrolloClienteTO> valores) {
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
	    	
	    	DesarrolloClienteTO desarrollo = (DesarrolloClienteTO) getItem(position);
	    	
	    	final ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.cabeceracliente_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	        
	        holder.txViewCodigo = (TextView) convertView.findViewById(R.id.txViewCodigo); 
	        holder.txViewFecha = (TextView) convertView.findViewById(R.id.txViewFecha);
	        holder.txViewHora =  (TextView) convertView.findViewById(R.id.txViewHora);
//	        holder.txViewCreado =  (TextView) convertView.findViewById(R.id.txViewCreado);   
	        holder.txViewFechaCierre = (TextView) convertView.findViewById(R.id.txViewFechaCierre);  	    	
	    	holder.txViewHoraCierre = (TextView) convertView.findViewById(R.id.txViewHoraCierre);		    	
//	    	holder.txViewCerrado = (TextView) convertView.findViewById(R.id.txViewCerrado);	          	
	    	holder.txViewestado = (TextView) convertView.findViewById(R.id.txViewestado);	    	
	    	holder.txViewVerDetalle = (Button) convertView.findViewById(R.id.txViewVerDetalle);	  
	    	holder.txViewVerResumen = (Button) convertView.findViewById(R.id.txViewVerResumen);
	    	holder.btnEliminar = (Button) convertView.findViewById(R.id.btnEliminar);
	    	
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      
	      int anio = 0, anioActual;    
  	      int mes = 0, mesActual;  
  	      int dia = 0, diaActual;
  	      String fecha = desarrollo.getFecha();
  	     
  	      
		  Calendar c = Calendar.getInstance();
		  anioActual = c.get(Calendar.YEAR);        
	      mesActual = c.get(Calendar.MONTH) + 1;        
	      diaActual = c.get(Calendar.DAY_OF_MONTH); 
	      dia =  Integer.parseInt(fecha.substring(0, 2));
    	  mes  =  Integer.parseInt(fecha.substring(3, 5));
    	  anio  =  Integer.parseInt(fecha.substring(6));
		    
	    	
	    	if(!(anio == anioActual && mes == mesActual && dia == diaActual))
	    	{
	    		holder.btnEliminar.setVisibility(View.INVISIBLE);
	    	}else{
	    		holder.btnEliminar.setVisibility(View.VISIBLE);
	    	}
	    	
	      
	      
	      
	      holder.txViewCodigo.setText(desarrollo.getCodigo());
	      holder.txViewFecha.setText(desarrollo.getFecha());
	      holder.txViewHora.setText(desarrollo.getHora()); 
//	      holder.txViewCreado.setText(desarrollo.getUserCrea());
	      holder.txViewFechaCierre.setText(desarrollo.getFechaCierre());
	      holder.txViewHoraCierre.setText(desarrollo.getHoraCierre());
//	      holder.txViewCerrado.setText(desarrollo.getUserCierra());
	      
	      //if(position == 0)
	      if(desarrollo.getEstado().equals("A"))
	    	  holder.txViewestado.setText("Abierto");
	      else
	    	  holder.txViewestado.setText("Cerrado");
	      
	      holder.txViewVerResumen.setOnClickListener(new OnClickListener() {
	    	  DesarrolloClienteTO desarrolloTemp = (DesarrolloClienteTO) getItem(position);
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent compromisoOpen = new Intent(context, ConsultarResumen_Activity.class);
				compromisoOpen.putExtra(ConsultarResumen_Activity.CODIGO_REGISTRO_KEY, desarrolloTemp.getCodigo());							
				context.startActivity(compromisoOpen);		
			}
	    	  
	      });
	      
	      holder.txViewVerDetalle.setOnClickListener(new OnClickListener() {
	    	  DesarrolloClienteTO desarrolloTemp = (DesarrolloClienteTO) getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//if(position == 0)
					
					int anio = 0, anioActual;    
		    	    int mes = 0, mesActual;  
		    	    int dia = 0, diaActual;
		    	    String fecha = desarrolloTemp.getFecha();

					Calendar c = Calendar.getInstance();
		    	    anioActual = c.get(Calendar.YEAR);        
			    	mesActual = c.get(Calendar.MONTH);        
			    	diaActual = c.get(Calendar.DAY_OF_MONTH); 
			    	
			    	if(fecha.length() >= 10)
					{
			    		dia =  Integer.parseInt(fecha.substring(0, 2));
				    	mes  =  Integer.parseInt(fecha.substring(3, 5))-1;
				    	anio  =  Integer.parseInt(fecha.substring(6));
				    }
			    	
			    	application.anio = ActivityUtil.pad(anio);
					application.mes = ActivityUtil.pad(mes+1);
					application.dia = ActivityUtil.pad(dia);
					
					/*
					Log.v("ConsultarCabecera_Activity", anio+"");
					Log.v("ConsultarCabecera_Activity", anioActual+"");
					Log.v("ConsultarCabecera_Activity", mes+"");
					Log.v("ConsultarCabecera_Activity", mesActual+"");
					Log.v("ConsultarCabecera_Activity", dia+"");
					Log.v("ConsultarCabecera_Activity", diaActual+"");*/
					
					
					if(desarrolloTemp.getEstado().equals("A"))
					{											
						if(anio == anioActual && mes == mesActual && dia == diaActual)
						{
							Intent compromisoOpen = new Intent(context, CompromisoPrincipalOpen_Resumen.class);
							compromisoOpen.putExtra(CompromisoPrincipalOpen_Resumen.CODIGO_REGISTRO, desarrolloTemp.getCodigo());
							compromisoOpen.putExtra(CompromisoPrincipalOpen_Resumen.ORIGEN_REGISTRO, "A");	
							context.startActivity(compromisoOpen);							
						}
						else
						{				
							Intent compromisoOpenFalse = new Intent(context, CompromisoPrincipalOpenFalse_Resumen.class);
							compromisoOpenFalse.putExtra(CompromisoPrincipalOpenFalse_Resumen.CODIGO_REGISTRO, desarrolloTemp.getCodigo());
							context.startActivity(compromisoOpenFalse);
						}						
					}
					else
					{
						Intent compromisoClose = new Intent(context, CompromisoPrincipalClose_Resumen.class);
						compromisoClose.putExtra(CompromisoPrincipalClose_Resumen.CODIGO_REGISTRO, desarrolloTemp.getCodigo());
						context.startActivity(compromisoClose);
					}
				}
			});
	      
	      
	      
	    	
	    	
	    	  
	      holder.btnEliminar.setOnClickListener(new OnClickListener() {
	    	  DesarrolloClienteTO desarrolloTemp = (DesarrolloClienteTO) getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//if(position == 0)
					
					int anio = 0, anioActual;    
		    	    int mes = 0, mesActual;  
		    	    int dia = 0, diaActual;
		    	    String fecha = desarrolloTemp.getFecha();

					Calendar c = Calendar.getInstance();
		    	    anioActual = c.get(Calendar.YEAR);        
			    	mesActual = c.get(Calendar.MONTH);        
			    	diaActual = c.get(Calendar.DAY_OF_MONTH); 
			    	
			    	if(fecha.length() >= 10)
					{
			    		dia =  Integer.parseInt(fecha.substring(0, 2));
				    	mes  =  Integer.parseInt(fecha.substring(3, 5))-1;
				    	anio  =  Integer.parseInt(fecha.substring(6));
				    }
			    	
			    	application.anio = ActivityUtil.pad(anio);
					application.mes = ActivityUtil.pad(mes+1);
					application.dia = ActivityUtil.pad(dia);
					
					/*
					Log.v("ConsultarCabecera_Activity", anio+"");
					Log.v("ConsultarCabecera_Activity", anioActual+"");
					Log.v("ConsultarCabecera_Activity", mes+"");
					Log.v("ConsultarCabecera_Activity", mesActual+"");
					Log.v("ConsultarCabecera_Activity", dia+"");
					Log.v("ConsultarCabecera_Activity", diaActual+"");
					*/
					
					if(desarrolloTemp.getEstado().equals("A"))
					{											
						if(anio == anioActual && mes == mesActual && dia == diaActual)
						{	
							MessageBox.showConfirmDialog(context, "Confirmacion", "ÀDesea eliminar el registro?", "Si",
									new android.content.DialogInterface.OnClickListener() {
								
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub	
									/*Intent intent = new Intent("lindley.desarrolloxcliente.verfoto");
									intent.putExtra(VerFoto_Activity.FILE_NAME, posicionTO.fotoInicial);
									context.startActivity(intent);*/
									
									ConsultarCabecera_Activity.codigoElimnar = desarrolloTemp.getCodigo();
									((ConsultarCabecera_Activity)context).processAsync(ConsultarCabecera_Activity.ACCION_ELIMINAR);
									
//									ConsultarCabecera_Activity cabecera_Activity = (ConsultarCabecera_Activity)context;
//									cabecera_Activity.processAsync(ConsultarCabecera_Activity.ACCION_ELIMINAR);									
								}
								
							}, "No", new android.content.DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									
								}
								
							});  
						}
						else
						{				
							holder.btnEliminar.setEnabled(false);
						}						
					}
					else
					{
						holder.btnEliminar.setEnabled(false);
					}
				}
			});
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewCodigo;
	    	TextView txViewFecha;
	    	TextView txViewHora;
//	    	TextView txViewCreado;
	    	TextView txViewFechaCierre;  	    	
	    	TextView txViewHoraCierre;    	
//	    	TextView txViewCerrado;    	
	    	TextView txViewestado;
	    	Button txViewVerDetalle;	    	
	    	
	    	Button txViewVerResumen;
	    	Button btnEliminar;
	    	
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
