package lindley.desarrolloxcliente.activity;

import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.EvaluacionBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.ws.service.ActualizarEstadoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarCabeceraProxy;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.inject.Inject;

public class ConsultarCabecera_Activity extends net.msonic.lib.sherlock.ListActivityBase  {

	
	@Inject ConsultarCabeceraProxy ConsultarCabeceraProxy;
	@Inject ActualizarEstadoProxy actualizarEstadoProxy;
	@Inject EvaluacionBLL evaluacionBLL;
	
	
	private EfficientAdapter adap;
	private ClienteTO cliente;
	public static MyApplication application;
	
	public static final String FLAG_OPEN_FECHA_ABIERTO = "1";
	public static final String FLAG_OPEN_FECHA_CERRADA = "2";
	
	
	public static final int ACCION_ELIMINAR = 1;
	public static final int ACCION_EDITAR = 2;
	
	public static final int ACCION_CARGAR_EVALUACION = 2;
	public static final int ACCION_VERIFICAR_EVALUACION = 3;
	
	public long evaluacionId=-1;
	
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
		
		registerForContextMenu(getListView());
		getListView().setFocusableInTouchMode(true);
		 
    }


    
    @Override
	protected void onStart() {
		// TODO Auto-generated method stub
    	processAsync();
		super.onStart();
	}

	@Override
	protected void process() {
		evaluacionId=-1;
		cabecera = evaluacionBLL.List(cliente.codigo);
	}
    
	@Override
	protected void processOk() {
		adap = new EfficientAdapter(this, cabecera);
		setListAdapter(adap);
		super.processOk();
	}
	
	
	
	
	
	@Override
	protected void process(int accion) {
		switch (accion) {
		case ACCION_ELIMINAR:
			evaluacionBLL.delete(evaluacionId);
			break;
		case ACCION_EDITAR:
			application.evaluacion = evaluacionBLL.getById(evaluacionId);
		default:
			break;
		}
	}
	
	@Override
	protected void processOk(int accion) {
		switch (accion) {
		case ACCION_ELIMINAR:
			super.processOk(accion);
			processAsync();
			break;
		case ACCION_EDITAR:
			super.processOk(accion);
			Intent compromisoOpen = new Intent(this,EvaluacionTabs_Activity.class);
			startActivity(compromisoOpen);
			break;
		default:
			break;
		}
		
	
	}
	
	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		switch (accion) {
		case ACCION_ELIMINAR:
			showToast(error_generico_process);
			super.processOk();
			break;
		case ACCION_EDITAR:
			showToast(error_generico_process);
			super.processOk();
			break;
		default:
			break;
		}
	}
	
	
	 @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){    
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        int position = info.position;
        if(adap!=null){
        	EvaluacionTO evaluacionTO =  adap.detalles.get(position);
        	evaluacionId = evaluacionTO.id;
        	
        	if(evaluacionTO.fecha.compareTo(ConstantesApp.getFechaSistemaAS400())==0 && 
        	   evaluacionTO.estado.compareTo(ConstantesApp.OPORTUNIDAD_ABIERTA)==0){
        		getMenuInflater().inflate(R.menu.consultarcabecera_menu1, menu);
        	}else{
        		if(evaluacionTO.estado.compareTo(ConstantesApp.OPORTUNIDAD_ABIERTA)==0){
        			getMenuInflater().inflate(R.menu.consultarcabecera_menu2, menu);
        		}else{
        			getMenuInflater().inflate(R.menu.consultarcabecera_menu3, menu);
        		}
        	}
        }
        
	 }
	
     @Override
     public boolean onContextItemSelected(MenuItem item) {
    	 if(item.getItemId()==R.id.mnuEliminar){
    		 processAsync(ACCION_ELIMINAR);
    		 
    	 }else if(item.getItemId()==R.id.mnuEditar){
    		 processAsync(ACCION_EDITAR);
    	 }
         return super.onContextItemSelected(item);
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
				
				/*viewHolder.txViewVerDetalle = (Button) view.findViewById(R.id.txViewVerDetalle);	  
				viewHolder.txViewVerResumen = (Button) view.findViewById(R.id.txViewVerResumen);
				viewHolder.btnEliminar = (Button) view.findViewById(R.id.btnEliminar);
				*/
				
				/*
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
							}else{
								context.processAsync(ACCION_VERIFICAR_EVALUACION);
								//VerficacionTabs_Activity
							}
						}else{
							Intent compromisoClose = new Intent(context, CompromisoPrincipalClose_Resumen.class);
							compromisoClose.putExtra(CompromisoPrincipalClose_Resumen.CODIGO_REGISTRO, evaluacionTO.id);
							context.startActivity(compromisoClose);
						}
					}
				  });*/
				
				/*
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
					
				});*/
				
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
	    	  holder.txViewestado.setText(ConstantesApp.OPORTUNIDAD_ABIERTA_LARGA);
	      else
	    	  holder.txViewestado.setText(ConstantesApp.OPORTUNIDAD_CERRADA_LARGA);
	
		  	return view;
	    }

	    static class ViewHolder {   
	    	TextView txViewCodigo;
	    	TextView txViewFecha;
	    	TextView txViewHora;
	    	TextView txViewFechaCierre;  	    	
	    	TextView txViewHoraCierre;    	    	
	    	TextView txViewestado;	    	
	    }
	

	  }
}
