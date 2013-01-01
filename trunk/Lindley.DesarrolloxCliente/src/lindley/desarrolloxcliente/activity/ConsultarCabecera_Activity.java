package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import net.msonic.lib.MessageBox;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.DescargaBLL;
import lindley.desarrolloxcliente.negocio.EvaluacionBLL;
import lindley.desarrolloxcliente.negocio.UploadBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.download.ResumenTO;
import lindley.desarrolloxcliente.ws.service.ActualizarEstadoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarCabeceraProxy;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
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
	@Inject UploadBLL uploadBLL;
	@Inject DescargaBLL descargaBLL;
	
	private EfficientAdapter adap;
	private ClienteTO cliente;
	public  MyApplication application;
	
	
	//EVALUACION_ID_KEY
	public static final int ACCION_ELIMINAR = 1;
	public static final int ACCION_EDITAR = 2;
	public static final int ACCION_DETALLE= 3;
	public static final int ACCION_CERRAR= 4;
	public static final int ACCION_RESUMEN= 5;
	public static final int ACCION_CERRAR_EN_CERO= 6;
	
	public static final int ACCION_CARGAR_EVALUACION = 2;
	public static final int ACCION_VERIFICAR_EVALUACION = 3;
	
	public long evaluacionId=-1;
	
	public static String codigoElimnar;
	
	List<EvaluacionTO> cabecera;
	List<ResumenTO> motivos;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        inicializarRecursos();
        this.validarConexionInternet=false;
        
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        
        setContentView(R.layout.cabeceracliente_activity);        
        setTitle(R.string.cabeceracliente_activity_title);
        application = (MyApplication)getApplicationContext();
		cliente = application.cliente;
		
		getSupportActionBar().setSubtitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		motivos = evaluacionBLL.consultarMotivos();
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
	
	
	
	
	
	


	String fechaCierreEvaluacion="";
	
	
	@Override
	protected void process(int accion) {
		String fechaCreacion = "0";
		String[] factores = null;
		GregorianCalendar calendar = null;
		int fechaMinCierre = 0;
		int fechaActual = 0;
		switch (accion) {
		case ACCION_ELIMINAR:
			evaluacionBLL.delete(evaluacionId);
			break;
		case ACCION_EDITAR:
			application.evaluacionActual = uploadBLL.listarEvaluacionById(evaluacionId);
			break;
		case ACCION_DETALLE:
			application.evaluacionActual = uploadBLL.listarEvaluacionById(evaluacionId);
			break;
		case ACCION_CERRAR:
			application.evaluacionActual = uploadBLL.listarEvaluacionById(evaluacionId);
			fechaCreacion = application.evaluacionActual.fechaCreacion;
			factores = ConstantesApp.getFechaFactoresAS400(fechaCreacion);
			calendar = new GregorianCalendar(Integer.parseInt(factores[0]),Integer.parseInt(factores[1])-1,1);
			calendar.add(Calendar.MONTH, 1);
			
			fechaMinCierre = Integer.parseInt(DateFormat.format("yyyyMMdd", calendar).toString());
			fechaActual = Integer.parseInt(ConstantesApp.getFechaSistemaAS400());
			fechaCierreEvaluacion="";
			
			if(application.evaluacionActual.tieneCambio==ConstantesApp.EVALUACION_TIENE_CAMBIOS){
				if(fechaActual>=fechaMinCierre){
					descargaBLL.cerrarEvaluacion(application.evaluacionActual, application.usuario,application.cliente);
	
				}else{
					fechaCierreEvaluacion = ConstantesApp.formatFecha(String.valueOf(fechaMinCierre));
				}
			}
			break;
		case ACCION_CERRAR_EN_CERO:
			application.evaluacionActual = uploadBLL.listarEvaluacionById(evaluacionId);
			fechaCreacion = application.evaluacionActual.fechaCreacion;
			factores = ConstantesApp.getFechaFactoresAS400(fechaCreacion);
			calendar = new GregorianCalendar(Integer.parseInt(factores[0]),Integer.parseInt(factores[1])-1,1);
			calendar.add(Calendar.MONTH, 1);
			fechaMinCierre = Integer.parseInt(DateFormat.format("yyyyMMdd", calendar).toString());
			fechaActual = Integer.parseInt(ConstantesApp.getFechaSistemaAS400());
			fechaCierreEvaluacion="";
			
				if(fechaActual>=fechaMinCierre){
					//descargaBLL.cerrarEvaluacion(application.evaluacionActual, application.usuario);
					ResumenTO motivo = motivos.get(item_selected);			
					application.evaluacionActual = uploadBLL.listarEvaluacionById(evaluacionId);
					application.evaluacionActual.motivoId=motivo.valor;
					application.evaluacionActual.motivo=motivo.descripcion;
					descargaBLL.cerrarEnCero(application.evaluacionActual, application.usuario);
	
				}else{
					fechaCierreEvaluacion = ConstantesApp.formatFecha(String.valueOf(fechaMinCierre));
				}
			break;
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
			Intent evaluacionActivity = new Intent(this,EvaluacionTabs_Activity.class);
			startActivity(evaluacionActivity);
			break;
		case ACCION_DETALLE:
			super.processOk(accion);
			if(application.evaluacionActual.estado.compareTo(ConstantesApp.OPORTUNIDAD_ABIERTA)==0){
				Intent verificacionActivity = new Intent(this,RevisionTabs_Activity.class);
				startActivity(verificacionActivity);
			}else{
				Intent verificacionActivity = new Intent(this,CloseTabs_Activity.class);
				startActivity(verificacionActivity);
			}
			break;
		case ACCION_CERRAR:
			super.processOk(accion);
			
			if(application.evaluacionActual.tieneCambio==ConstantesApp.EVALUACION_TIENE_CAMBIOS){
				if(fechaCierreEvaluacion.equalsIgnoreCase("")){
					processAsync();
				}else{
				 showToast("La evaluaci—n no puede cerrarse hasta el d’a: " + 	fechaCierreEvaluacion);
				}
			}else{
				showToast("La evaluaci—n debe ser editada antes de cerrarse");
			}
			break;
		case ACCION_CERRAR_EN_CERO:
			super.processOk(accion);
			
			
			
				if(fechaCierreEvaluacion.equalsIgnoreCase("")){
					processAsync();
				}else{
				 showToast("La evaluaci—n no puede cerrarse hasta el d’a: " + 	fechaCierreEvaluacion);
				}
	
			
			
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
		case ACCION_CERRAR:
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
        	   evaluacionTO.estado.compareTo(ConstantesApp.OPORTUNIDAD_ABIERTA)==0 && 
        	   evaluacionTO.serverId==0){
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
	
	 int item_selected = 0; // select at 0
	 
	 @Override
		protected Dialog onCreateDialog(int id) {
			 
			 List<String> lista = new ArrayList<String>();
				
				
				for (ResumenTO motivo : motivos) {
					lista.add(motivo.descripcion);
				}
			
				
				final String[] values = lista.toArray(new String[lista.size()]);
				ArrayAdapter<String> arrAdap = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, android.R.id.text1, values);
				

				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("ÀCerrar en Cero?");
				
				builder.setSingleChoiceItems(arrAdap,item_selected, new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int item) {
				    	item_selected = item;
				    	
				    }
				});
				
				builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
					
			        public void onClick(DialogInterface dialog, int id) {
			        	/*if(item_selected>-1){
				        	Intent i = new Intent(getApplicationContext(),ListaPedidosActivity.class);
				        	i.putExtra(ListaPedidosActivity.CODIGO_CDA_KEY, codigoCda);
				        	i.putExtra(ListaPedidosActivity.CODIGO_VENDEDOR_KEY, codigoVendedor);
				        	i.putExtra(ListaPedidosActivity.NOMBRE_VENDEDOR_KEY, nombreVendedor);
				        	startActivity(i);
				        	
			        	}*/
			        	dialog.dismiss();
			        	processAsync(ACCION_CERRAR_EN_CERO);
			        }
			    });

				builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

			        public void onClick(DialogInterface dialog, int id) {
			        	 dialog.dismiss();
			        }
			    });
				
				AlertDialog alert = builder.create();
				alert.setCanceledOnTouchOutside(true);
				return builder.create();
				
		 }
	 
     @Override
     public boolean onContextItemSelected(MenuItem item) {
    	 switch (item.getItemId()) {
		case R.id.mnuEliminar:
			
			MessageBox.showConfirmDialog(this, "Confirmaci—n: ",
					"ÀDeseas eliminar el registro seleccionado?", "Si",
					new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							processAsync(ACCION_ELIMINAR);
						}

					}, "No", new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
						}

					});
			
			
			break;
		case R.id.mnuEditar:
			 processAsync(ACCION_EDITAR);
			break;
		case R.id.mnuDetalle:
			 processAsync(ACCION_DETALLE);
			break;
		case R.id.mnuCerrar:
			 
			 
			 MessageBox.showConfirmDialog(this, "Confirmaci—n: ",
						"ÀDeseas cerrar el registro seleccionado?", "Si",
						new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								processAsync(ACCION_CERRAR);
							}

						}, "No", new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
							}

						});
			 
			break;
		case R.id.mnuResumen:
			Intent intent = new Intent(this,ConsultarResumen_Activity.class);
			intent.putExtra(ConsultarResumen_Activity.EVALUACION_ID_KEY, evaluacionId);
			this.startActivity(intent);
			break;
		case R.id.mnuCerrarEnCero:
			 showDialog(0);
			break;
		default:
			break;
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
