package lindley.desarrolloxcliente.activity;

import java.util.List;

import net.msonic.lib.MessageBox;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.EvaluacionBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.ws.service.ActualizarEstadoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarCabeceraProxy;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;

public class ConsultarCabecera_Activity extends net.msonic.lib.sherlock.ListActivityBase  {

	//@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarCabeceraProxy ConsultarCabeceraProxy;
	@Inject ActualizarEstadoProxy actualizarEstadoProxy;
	@Inject EvaluacionBLL evaluacionBLL;
	
	private EfficientAdapter adap;
	private ClienteTO cliente;
	public static MyApplication application;
	
	public static final String FLAG_OPEN_FECHA_ABIERTO = "1";
	public static final String FLAG_OPEN_FECHA_CERRADA = "2";
	
	
	public static final int ACCION_ELIMINAR = 1;
	public static final int ACCION_CARGAR_EVALUACION = 2;
	public static final int ACCION_VERIFICAR_EVALUACION = 3;
	public long evaluacionId=0;
	
	public static String codigoElimnar;
	
	List<EvaluacionTO> cabecera;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        
        setContentView(R.layout.cabeceracliente_activity);        
        setTitle(R.string.cabeceracliente_activity_title);
        application = (MyApplication)getApplicationContext();
		cliente = application.cliente;
		
		getSupportActionBar().setSubtitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		

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
    	//ConsultarCabeceraProxy.setCodigoCliente(cliente.codigo);
    	//ConsultarCabeceraProxy.execute();
		cabecera = evaluacionBLL.List(cliente.codigo);
	}
    
	@Override
	protected void processOk() {
		adap = new EfficientAdapter(this, cabecera);
		setListAdapter(adap);
		super.processOk();
	}
	
	
	
	
	/*
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
	}*/
	
	
	
	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		/*if(accion == ACCION_ELIMINAR)
		{
			actualizarEstadoProxy.codigo = codigoElimnar;
			actualizarEstadoProxy.estado = "E";
			actualizarEstadoProxy.execute();
		}*/
		
		
		if(ACCION_CARGAR_EVALUACION==accion || ACCION_VERIFICAR_EVALUACION==accion){
			application.evaluacion = evaluacionBLL.GetById(evaluacionId);
		}
	}
	
	@Override
	protected void processOk(int accion) {
		
		if(ACCION_CARGAR_EVALUACION==accion){
			super.processOk();
			Intent evaluacionTabsActivity = new Intent(this, EvaluacionTabs_Activity.class);
			evaluacionTabsActivity.putExtra(EvaluacionTabs_Activity.CODIGO_REGISTRO, "0");
			evaluacionTabsActivity.putExtra(EvaluacionTabs_Activity.ORIGEN_REGISTRO, "0");
			
			//compromisoOpen.putExtra(ConsultarResumen_Activity.CODIGO_REGISTRO_KEY, this.evaluacionId);							
			startActivity(evaluacionTabsActivity);
		}else if(ACCION_VERIFICAR_EVALUACION==accion){
			super.processOk();
			Intent verificacionTabsActivity = new Intent(this, VerificacionTabs_Activity.class);
			verificacionTabsActivity.putExtra(EvaluacionTabs_Activity.CODIGO_REGISTRO, "0");
			verificacionTabsActivity.putExtra(EvaluacionTabs_Activity.ORIGEN_REGISTRO, "0");
			startActivity(verificacionTabsActivity);
		}
		
		
		// TODO Auto-generated method stub
		/*if(accion == ACCION_ELIMINAR)
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
		}*/
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
	
	
	
	
	
	public static class EfficientAdapter extends ArrayAdapter<EvaluacionTO> {
	   
	    private ConsultarCabecera_Activity 		context;
	    private List<EvaluacionTO> detalles;
	    
	    public EfficientAdapter(ConsultarCabecera_Activity context, List<EvaluacionTO> valores) {
		      // Cache the LayoutInflate to avoid asking for a new one each time.
	    		super(context, R.layout.cabeceracliente_content, valores);
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
	    	
	    	
	    
	    	View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.cabeceracliente_content, null);
				final ViewHolder viewHolder = new ViewHolder();
				
				viewHolder.txViewCodigo = (TextView) view.findViewById(R.id.txViewCodigo); 
				viewHolder.txViewFecha = (TextView) view.findViewById(R.id.txViewFecha);
				viewHolder.txViewHora =  (TextView) view.findViewById(R.id.txViewHora);
				viewHolder.txViewFechaCierre = (TextView) view.findViewById(R.id.txViewFechaCierre);  	    	
				viewHolder.txViewHoraCierre = (TextView) view.findViewById(R.id.txViewHoraCierre);		    		          	
				viewHolder.txViewestado = (TextView) view.findViewById(R.id.txViewestado);	    	
				viewHolder.txViewVerDetalle = (Button) view.findViewById(R.id.txViewVerDetalle);	  
				viewHolder.txViewVerResumen = (Button) view.findViewById(R.id.txViewVerResumen);
				viewHolder.btnEliminar = (Button) view.findViewById(R.id.btnEliminar);
				
				
				
				viewHolder.txViewVerResumen.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						EvaluacionTO evaluacionTO = (EvaluacionTO) viewHolder.txViewCodigo.getTag();
						Intent compromisoOpen = new Intent(context, ConsultarResumen_Activity.class);
						compromisoOpen.putExtra(ConsultarResumen_Activity.CODIGO_REGISTRO_KEY, evaluacionTO.id);							
						context.startActivity(compromisoOpen);	
						
								
					}
			    	  
			      });
				
				viewHolder.txViewVerDetalle.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						EvaluacionTO evaluacionTO = (EvaluacionTO) viewHolder.txViewCodigo.getTag();
						
						context.evaluacionId = evaluacionTO.id;
						
						if(evaluacionTO.estado.equals(ConstantesApp.OPORTUNIDAD_ABIERTA)){
							if(evaluacionTO.fecha.equals(ConstantesApp.getFechaSistemaAS400())){
								
								
								context.processAsync(ACCION_VERIFICAR_EVALUACION);
								
								//context.processAsync(ACCION_CARGAR_EVALUACION);
								/*
								Intent compromisoOpen = new Intent(context, CompromisoPrincipalOpen_Resumen.class);
								compromisoOpen.putExtra(CompromisoPrincipalOpen_Resumen.CODIGO_REGISTRO, evaluacionTO.evaluacionId);
								compromisoOpen.putExtra(CompromisoPrincipalOpen_Resumen.ORIGEN_REGISTRO, "A");	
								context.startActivity(compromisoOpen);	
								*/
							}else{
								context.processAsync(ACCION_VERIFICAR_EVALUACION);
								//VerficacionTabs_Activity
								/*Intent compromisoOpenFalse = new Intent(context, CompromisoPrincipalOpenFalse_Resumen.class);
								compromisoOpenFalse.putExtra(CompromisoPrincipalOpenFalse_Resumen.CODIGO_REGISTRO, evaluacionTO.evaluacionId);
								context.startActivity(compromisoOpenFalse);*/
							}
						}else{
							Intent compromisoClose = new Intent(context, CompromisoPrincipalClose_Resumen.class);
							compromisoClose.putExtra(CompromisoPrincipalClose_Resumen.CODIGO_REGISTRO, evaluacionTO.id);
							context.startActivity(compromisoClose);
						}
					}
				  });
				
				
				viewHolder.btnEliminar.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						final EvaluacionTO evaluacionTO = (EvaluacionTO) viewHolder.txViewCodigo.getTag();
						
						if(evaluacionTO.estado.equals(ConstantesApp.OPORTUNIDAD_ABIERTA)){
							if(evaluacionTO.fecha.equals(ConstantesApp.getFechaSistemaAS400())){
								MessageBox.showConfirmDialog(context, "Confirmacion", "ÀDesea eliminar el registro?", "Si",
										new android.content.DialogInterface.OnClickListener() {
									
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub	
										
										ConsultarCabecera_Activity.codigoElimnar = String.valueOf(evaluacionTO.serverId) ;
										((ConsultarCabecera_Activity)context).processAsync(ConsultarCabecera_Activity.ACCION_ELIMINAR);
						
									}
									
								}, "No", new android.content.DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										
									}
									
								});  
							}
						}
					}
					
				});
				
				view.setTag(viewHolder);
				
				viewHolder.txViewCodigo.setTag(this.detalles.get(position));
				
				
				
			}else{
				view = convertView;
				((ViewHolder) view.getTag()).txViewCodigo.setTag(this.detalles.get(position));
			}
	    	
			ViewHolder holder = (ViewHolder) view.getTag();
			EvaluacionTO evaluacionTO = detalles.get(position);
	    	
		  holder.txViewCodigo.setText(String.valueOf(evaluacionTO.serverId));
	      holder.txViewFecha.setText(ConstantesApp.formatFecha(String.valueOf(evaluacionTO.fecha)));
	      holder.txViewHora.setText(ConstantesApp.formatHora(String.valueOf(evaluacionTO.hora))); 
	      holder.txViewFechaCierre.setText(ConstantesApp.formatFecha(String.valueOf(evaluacionTO.fechaCierre)));
	      holder.txViewHoraCierre.setText(ConstantesApp.formatHora(String.valueOf(evaluacionTO.horaCierre)));
		      
	      if(evaluacionTO.estado.equals(ConstantesApp.OPORTUNIDAD_ABIERTA))
	    	  holder.txViewestado.setText("Abierto");
	      else
	    	  holder.txViewestado.setText("Cerrado");
			
	      if(!(evaluacionTO.fecha.equals(ConstantesApp.getFechaSistemaAS400())))
	    	{
	    		holder.btnEliminar.setVisibility(View.INVISIBLE);
	    	}else{
	    		holder.btnEliminar.setVisibility(View.VISIBLE);
	    	}
	    	
	      
	      
			
			
	    	
		  	return view;
	    }

	    static class ViewHolder {   
	    	TextView txViewCodigo;
	    	TextView txViewFecha;
	    	TextView txViewHora;
	    	TextView txViewFechaCierre;  	    	
	    	TextView txViewHoraCierre;    	    	
	    	TextView txViewestado;
	    	Button txViewVerDetalle;	    	
	    	Button txViewVerResumen;
	    	Button btnEliminar;
	    	
	    }
	

	  }
}
