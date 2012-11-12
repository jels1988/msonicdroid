package lindley.desarrolloxcliente.activity;

import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.EvaluacionBLL;
import lindley.desarrolloxcliente.negocio.OportunidadBLL;
import lindley.desarrolloxcliente.negocio.PosicionBLL;
import lindley.desarrolloxcliente.negocio.PresentacionBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import lindley.desarrolloxcliente.to.upload.OportunidadTO;
import lindley.desarrolloxcliente.to.upload.PosicionTO;
import lindley.desarrolloxcliente.to.upload.PresentacionTO;
import lindley.desarrolloxcliente.to.upload.SkuTO;
import lindley.desarrolloxcliente.ws.service.ConsultarSKUPrioritarioProxy;
import lindley.desarrolloxcliente.ws.service.GuardarNuevoDesarrolloProxy;
import net.msonic.lib.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.inject.Inject;

public class SKUPrioritario_Activity extends net.msonic.lib.sherlock.ListActivityBase {
	
	private EfficientAdapter adap;
	
	private  MyApplication application;
	private ClienteTO cliente;
	private UsuarioTO usuario;
	private EvaluacionTO evaluacion;
	
	@Inject	ConsultarSKUPrioritarioProxy consultarSKUPrioritarioProxy;
    @Inject GuardarNuevoDesarrolloProxy guardarNuevoDesarrolloProxy;
    
	@InjectView(R.id.txtViewFecha) 		TextView txtViewFecha;
	@Inject OportunidadBLL oportunidadBLL;
	@Inject EvaluacionBLL evaluacionBLL;
	@Inject PosicionBLL posicionBLL;
	@Inject PresentacionBLL presentacionBLL;
	
	public static final int ACCION_GUARDAR = 1;
	public static final int ACCION_CARGAR = 2;

	@InjectResource(R.string.confirm_atras_title)	String confirm_atras_title;
	@InjectResource(R.string.confirm_atras_message)	String confirm_atras_message;
	@InjectResource(R.string.confirm_atras_yes)		String confirm_atras_yes;
	@InjectResource(R.string.confirm_atras_no)		String confirm_atras_no;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		this.validarConexionInternet=false;
		
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		
		setContentView(R.layout.skuprioritario_activity);
		
		setTitle(R.string.skuprioritario_activity_title);
		
		application = (MyApplication) getApplicationContext();
		
		cliente = application.cliente;
		usuario = application.usuario;
		evaluacion = application.evaluacionActual;
		
		setSubTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		
		MessageBox.showConfirmDialog(this, "Posicion: ",
				"", "Activos de Lindley",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						evaluacion.activosLindley = ConstantesApp.RESPUESTA_SI;
						processAsync();
					}

				}, "Anaquel", new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						evaluacion.activosLindley = ConstantesApp.RESPUESTA_NO;
						processAsync();
					}

				});
	}

	@Override
	protected void process() {
		
		evaluacion.codigoCliente = cliente.codigo;
		evaluacion.usuarioCreacion = usuario.codigoSap;
		evaluacion.fechaCreacion = ConstantesApp.getFechaSistemaAS400();
		evaluacion.horaCreacion = ConstantesApp.getHoraSistemaAS400();
		evaluacion.estado = ConstantesApp.OPORTUNIDAD_ABIERTA;
		evaluacion.codigoFDE = cliente.cluster;
		evaluacion.fechaCierre = "0";
		evaluacion.horaCierre = "0";
		evaluacion.usuarioCierre = "0";
		

		 List<PosicionTO> posicion = posicionBLL.consultarOportunidadesPosicion(evaluacion);
		 evaluacion.posiciones = posicion;
		 
		 List<PresentacionTO> presentacion = presentacionBLL.consultarOportunidadesPresentacion(evaluacion.codigoCliente);
		 evaluacion.presentaciones = presentacion;
		
		 String cluster = cliente.cluster;
		 evaluacion.skus = oportunidadBLL.consultarSKUPresentacion(cluster);
		adap = new EfficientAdapter(this, evaluacion.skus);
			
		String[] fechas = ConstantesApp.getFechaFactoresAS400(evaluacion.fechaCreacion);
		int anio = Integer.parseInt(fechas[0]);
		int mes = Integer.parseInt(fechas[1]);
		
		String fechaCompromiso = ConstantesApp.getFechaCompromisoAS400();
		
		for (OportunidadTO oportunidadTO : evaluacion.oportunidades) {
			oportunidadTO.anio=anio;
			oportunidadTO.mes= mes;
			oportunidadTO.codigoAccionTrade="0";
			oportunidadTO.fechaCompromiso = fechaCompromiso;
		}
		
		for(PosicionTO posicionTO : evaluacion.posiciones){
			posicionTO.anio=anio;
			posicionTO.mes= mes;
			posicionTO.fechaEncuesta=evaluacion.fechaCreacion;
			posicionTO.fechaCompromiso = fechaCompromiso;
		}
		

		for(PresentacionTO presentacionTO : evaluacion.presentaciones){
			presentacionTO.anio=anio;
			presentacionTO.mes= mes;
			presentacionTO.fechaEncuesta=evaluacion.fechaCreacion;
			presentacionTO.fechaCompromiso = fechaCompromiso;
		}
		
		
		for(SkuTO skuTO : evaluacion.skus){
			skuTO.anio=anio;
			skuTO.mes= mes;
		}
		
		 
	}
	
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		
		setListAdapter(adap);
		txtViewFecha.setText(ConstantesApp.getFechaSistema());
		super.processOk();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}

	@Override
	public void onBackPressed() {
		
		MessageBox.showConfirmDialog(this, confirm_atras_title,
				confirm_atras_message, confirm_atras_yes,
				new android.content.DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//Intent intent = new Intent("lindley.desarrolloxcliente.consultarcliente");
						//startActivity(intent);
						finish();
					}

				}, confirm_atras_no,
				new android.content.DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}

				});
	}
	
	public void btnCancelar_click(View view) {
		
		onBackPressed();
	}

	public void btnGuardar_click(View view) {
		
		
		
		MessageBox.showConfirmDialog(this, "Confirmar", "ÀDesea guardar datos seleccionados?", "Si", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				processAsync(ACCION_GUARDAR);
			
			
			}
		}, "No",null);
		
		
		
		
	}

	
	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_GUARDAR)
		{
			evaluacion.skus = adap.skuPresentaciones;
		
			evaluacionBLL.asignarPuntos(evaluacion,cliente);
			
			
		}
	}
	
	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_GUARDAR)
    	{
			finish();
			Intent compromisoOpen = new Intent(this,EvaluacionTabs_Activity.class);
			startActivity(compromisoOpen);
			super.processOk();
    	}
	}
	
	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError(accion);
		showToast(error_generico_process);
	}
	
	public static class EfficientAdapter extends ArrayAdapter<SkuTO> {
		private Activity context;
		private List<SkuTO> skuPresentaciones;

		public EfficientAdapter(Activity context,List<SkuTO> skuPresentaciones) {
			super(context, R.layout.skuprioritario_content, skuPresentaciones);
			this.context = context;
			this.skuPresentaciones = skuPresentaciones;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.skuprioritario_content, null);
				final ViewHolder viewHolder = new ViewHolder();

				viewHolder.txViewSKU = (TextView) view.findViewById(R.id.txViewSKU);
				viewHolder.chkValActual = (Spinner) view.findViewById(R.id.chkValActual);
				viewHolder.chkValActual.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
						// TODO Auto-generated method stub
						SkuTO skuPresentacion = (SkuTO) viewHolder.chkValActual.getTag();
						if(arg2==0){
							skuPresentacion.marcaActual = ConstantesApp.RESPUESTA_NO;
							skuPresentacion.marcaCompromiso = ConstantesApp.RESPUESTA_NO;
						}else if(arg2==1){
							skuPresentacion.marcaActual = ConstantesApp.RESPUESTA_SI;
							skuPresentacion.marcaCompromiso = ConstantesApp.RESPUESTA_SI;
						}else{
							skuPresentacion.marcaActual = ConstantesApp.RESPUESTA_NO_TIENE ;
							skuPresentacion.marcaCompromiso = ConstantesApp.RESPUESTA_NO_TIENE;
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				});

				ArrayAdapter<CharSequence> adap = ArrayAdapter.createFromResource(context.getApplicationContext(),
																				R.array.sku_estados,
																				android.R.layout.simple_spinner_item);
				
				adap.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
				viewHolder.chkValActual.setAdapter(adap);
				
				view.setTag(viewHolder);
				viewHolder.chkValActual.setTag(this.skuPresentaciones.get(position));

			} else {
				view = convertView;
				((ViewHolder) view.getTag()).chkValActual.setTag(this.skuPresentaciones.get(position));
			}

			ViewHolder holder = (ViewHolder) view.getTag();
			SkuTO skuPresentacion = skuPresentaciones.get(position);

			holder.txViewSKU.setText(skuPresentacion.descripcionSKU);
			
			
		      
			if (this.skuPresentaciones.get(position).marcaActual.equalsIgnoreCase(ConstantesApp.RESPUESTA_NO)) {
				holder.chkValActual.setSelection(0);
			} else if (this.skuPresentaciones.get(position).marcaActual.equalsIgnoreCase(ConstantesApp.RESPUESTA_SI)) {
				holder.chkValActual.setSelection(1);
			}else {
				holder.chkValActual.setSelection(2);
			}

			return view;
		}

		static class ViewHolder {
			TextView txViewSKU;
			Spinner chkValActual;
		}

	}
}
