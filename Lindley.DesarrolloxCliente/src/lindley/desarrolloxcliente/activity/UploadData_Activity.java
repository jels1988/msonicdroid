package lindley.desarrolloxcliente.activity;

import java.util.List;

import roboguice.inject.InjectView;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.UploadBLL;
import lindley.desarrolloxcliente.to.upload.EvaluacionProcessUploadTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import lindley.desarrolloxcliente.ws.service.UploadEvaluacionesProxy;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.actionbarsherlock.view.Window;
import com.google.inject.Inject;

import net.msonic.lib.MessageBox;
import net.msonic.lib.sherlock.ListActivityBase;

public class UploadData_Activity extends ListActivityBase {

	public static final int CALCULAR_EVALUACIONES=1;
	public static final int LISTAR_EVALUACIONES=2;
	public static final int ACTUALIZAR_EVALUACIONES=3;
	public static final int ACTUALIZAR_EVALUACIONES_PROCESAR=3;
	public static final int UPLOAD_EVALUACION=0;
	
	@Inject UploadEvaluacionesProxy uploadEvaluacionesProxy;
	@Inject UploadBLL uploadBLL;
	@InjectView(R.id.txtTitulo) TextView txtTitulo;
	
	private long cantidadEvaluaciones=0;
	EfficientAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		this.mostrarWaitMessage=false;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		
		boolean isConectadoInternet = isNetworkAvailable();
    	if(!isConectadoInternet){
    		
    		setSupportProgressBarIndeterminateVisibility(false);
			MessageBox.showSimpleDialog(this, "Confirmaci—n", 
					"Verificar conexi—n de Internet.", "Ok", new android.content.DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
				
			});
			
    		return;
    	}
		
		setContentView(R.layout.uploaddata_activity);
		setSupportProgressBarIndeterminateVisibility(true);
		processAsync();
	}
	
	MenuItem menuEnviar;

	@Override
	protected void process() throws Exception {
			List<EvaluacionTO> evaluaciones = uploadBLL.listarEvaluaciones();
			adapter = new EfficientAdapter(this, evaluaciones);
	}
	
	
	
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		
		txtTitulo.setText(String.format("Evaluaciones Pendientes %s", adapter.detalle.size()));
		setListAdapter(adapter);
		setSupportProgressBarIndeterminateVisibility(false);
		super.processOk();
	}




	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		
		final Context context = this;
		
		menuEnviar = menu.add("Refresh");
		menuEnviar
            .setIcon(R.drawable.reload)
            .setOnMenuItemClickListener(new OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(final com.actionbarsherlock.view.MenuItem item) {
					// TODO Auto-generated method stub
					
					MessageBox.showConfirmDialog(context, 
											"confirmacion", 
											"ÀDese enviar las evaluaciones pendientes?", 
											"Si", new OnClickListener() {
												
												@Override
												public void onClick(DialogInterface dialog, int which) {
													// TODO Auto-generated method stub
													processAsync(CALCULAR_EVALUACIONES);
													item.setVisible(false);
													setSupportProgressBarIndeterminateVisibility(true);
												}
											}, 
											"No", new OnClickListener() {
												
												@Override
												public void onClick(DialogInterface dialog, int which) {
													// TODO Auto-generated method stub
													
												}
											});
					
					
					return true;
				}
			})
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		return true;
	
	}

	
	
	@Override
	protected void process(int accion) throws Exception {
		switch (accion) {
		case CALCULAR_EVALUACIONES:
			cantidadEvaluaciones = uploadBLL.getCantidadEvaluaciones();
			break;
		case LISTAR_EVALUACIONES:
			uploadEvaluacionesProxy.evaluciones = uploadBLL.listarEvaluaciones(10);
			break;
		case UPLOAD_EVALUACION:
			uploadEvaluacionesProxy.execute();
			break;
		case ACTUALIZAR_EVALUACIONES:
			boolean isExito = uploadEvaluacionesProxy.isExito();
			if(uploadEvaluacionesProxy.getResponse()!=null){
				int status = uploadEvaluacionesProxy.getResponse().getStatus();
				if ((isExito) && (status == 0)) {
					List<EvaluacionProcessUploadTO> ids = uploadEvaluacionesProxy.getResponse().ids;
					if(ids!=null){
						uploadBLL.updateEvaluacionServerId(ids);
						
						List<EvaluacionTO> evaluaciones = adapter.detalle;
						
						for (EvaluacionProcessUploadTO evaluacionProcessUploadTO : ids) {
							for (EvaluacionTO evaluacionTO : evaluaciones) {
								if(evaluacionTO.id==evaluacionProcessUploadTO.clientId){
									evaluacionTO.serverId=evaluacionProcessUploadTO.serverId;
									evaluacionTO.tieneCambio=ConstantesApp.EVALUACION_NO_TIENE_CAMBIOS;
								}
							}
						}
						
						
					}
				}
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void processOk(int accion) {
		switch (accion) {
		case CALCULAR_EVALUACIONES:
			
			txtTitulo.setText(String.format("Evaluaciones Pendientes %s", cantidadEvaluaciones));
			Log.d("Evaluaciones Pendientes", String.valueOf(cantidadEvaluaciones));
			
			if(cantidadEvaluaciones>0){
				processAsync(LISTAR_EVALUACIONES);
				Intent servicioFoto = new Intent("lindley.desarrolloxcliente.uploadFileService");
   				startService(servicioFoto);
			}else{
				menuEnviar.setVisible(true);
				setSupportProgressBarIndeterminateVisibility(false);
			}
			break;
		case LISTAR_EVALUACIONES:
			processAsync(UPLOAD_EVALUACION);
			break;
		case UPLOAD_EVALUACION:
			processAsync(ACTUALIZAR_EVALUACIONES);
			break;
		case ACTUALIZAR_EVALUACIONES:
			processAsync(CALCULAR_EVALUACIONES);
			adapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void processError(int accion) {
		setSupportProgressBarIndeterminateVisibility(false);
		// TODO Auto-generated method stub
		Log.d("error", "=============");
		Log.d("error", String.valueOf(accion));
		Log.d("error", "=============");
		super.processError();
	}
	
	
	public static class EfficientAdapter extends ArrayAdapter<EvaluacionTO>{
		
		 private final List<EvaluacionTO> detalle;
		 private final UploadData_Activity context;
		 
		 public EfficientAdapter(UploadData_Activity context,List<EvaluacionTO> detalle){
			 super(context, R.layout.uploaddata_content, detalle);
			 this.detalle = detalle;
				this.context =context;
		 }
		 
		  public View getView(final int position, View convertView, ViewGroup parent) {

				View view = null;	
				
				if (convertView == null) {
					
					
					LayoutInflater inflator = context.getLayoutInflater();
					view = inflator.inflate(R.layout.uploaddata_content, null);
					
					
					final ViewHolder holder = new ViewHolder();
					holder.imgIndicador = (ImageView)view.findViewById(R.id.imgIndicador);
					holder.txtServerId = (TextView)view.findViewById(R.id.txtServerId);
					holder.txtCodigo = (TextView)view.findViewById(R.id.txtCodigo);
					holder.txtDescripcion = (TextView)view.findViewById(R.id.txtDescripcion);
					holder.txtFecha = (TextView)view.findViewById(R.id.txtFecha);
					
					view.setTag(holder);
			    	holder.txtServerId.setTag(this.detalle.get(position));
					
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtServerId.setTag(this.detalle.get(position));
				}
				
				ViewHolder holder = (ViewHolder) view.getTag();
				
				EvaluacionTO evaluacionTO = this.detalle.get(position);
				holder.txtServerId.setText(String.valueOf(evaluacionTO.serverId));
				holder.txtCodigo.setText(evaluacionTO.codigoCliente);
				holder.txtDescripcion.setText(evaluacionTO.cliente);
				
				String fecha = String.format("%s %s", ConstantesApp.formatFecha(String.valueOf(evaluacionTO.fechaCreacion)),
									   ConstantesApp.formatHora(String.valueOf(evaluacionTO.horaCreacion)));
									   
				holder.txtFecha.setText(fecha);
			      
				if(evaluacionTO.tieneCambio==ConstantesApp.EVALUACION_TIENE_CAMBIOS){
					holder.imgIndicador.setImageResource(R.drawable.icon_amarrillo);
				}else{
					holder.imgIndicador.setImageResource(R.drawable.icon_verde);
				}
				
				return view;
		  }
		static class ViewHolder {   
			ImageView imgIndicador;
			TextView txtServerId;
			TextView txtCodigo;
			TextView txtDescripcion;
			TextView txtFecha;
		}
	}
	
}
