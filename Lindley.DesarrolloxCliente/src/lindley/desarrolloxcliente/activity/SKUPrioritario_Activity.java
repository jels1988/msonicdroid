package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
import lindley.desarrolloxcliente.ws.service.ConsultarSKUPrioritarioProxy;
import lindley.desarrolloxcliente.ws.service.GuardarNuevoDesarrolloProxy;
import net.msonic.lib.ActivityUtil;
import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.content.DialogInterface;
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
import com.thira.examples.actionbar.widget.ActionBar;

public class SKUPrioritario_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)
	ActionBar mActionBar;
	private EfficientAdapter adap;
	public static MyApplication application;
	ClienteTO cliente;
	UsuarioTO usuario;
	@Inject	ConsultarSKUPrioritarioProxy consultarSKUPrioritarioProxy;
    @Inject GuardarNuevoDesarrolloProxy guardarNuevoDesarrolloProxy;
	@InjectView(R.id.txtViewFecha) 		TextView txtViewFecha;
	public static final String RESPUESTA_SI = "S";
	public static final String RESPUESTA_NO = "N";
	public static final String RESPUESTA_NO_TIENE = "T";
	public final int ACCION_GUARDAR = 1;

	@InjectResource(R.string.confirm_atras_title)
	String confirm_atras_title;
	@InjectResource(R.string.confirm_atras_message)
	String confirm_atras_message;
	@InjectResource(R.string.confirm_atras_yes)
	String confirm_atras_yes;
	@InjectResource(R.string.confirm_atras_no)
	String confirm_atras_no;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.skuprioritario_activity);
		mActionBar.setTitle(R.string.skuprioritario_activity_title);
		application = (MyApplication) getApplicationContext();
		cliente = application.getClienteTO();
		usuario = application.getUsuarioTO();
		mActionBar.setSubTitle(cliente.getCodigo() + " - "
				+ cliente.getNombre());
		mActionBar.setHomeLogo(R.drawable.header_logo);
		MessageBox.showConfirmDialog(this, "Posicion: ",
				"", "Activos de Lindley",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						application.activosLindley = RESPUESTA_SI;
						processAsync();
					}

				}, "Anaquel", new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						application.activosLindley = RESPUESTA_NO;
						processAsync();
					}

				});
	}

	@Override
	protected void process() {
		consultarSKUPrioritarioProxy.codigoCluster = cliente.getCluster();
		consultarSKUPrioritarioProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarSKUPrioritarioProxy.isExito();
		if (isExito) {
			int status = consultarSKUPrioritarioProxy.getResponse().getStatus();
			if (status == 0) {
				List<SKUPresentacionTO> listaSKUPresentacion = consultarSKUPrioritarioProxy
						.getResponse().listaSKUPresentacion;
				adap = new EfficientAdapter(this, listaSKUPresentacion);
				final Calendar c = Calendar.getInstance();
				if (listaSKUPresentacion.size() > 0)
					txtViewFecha.setText(ActivityUtil.pad(c
							.get(Calendar.DAY_OF_MONTH))
							+ "/"
							+ ActivityUtil.pad((c.get(Calendar.MONTH) + 1))
							+ "/" + c.get(Calendar.YEAR));
				setListAdapter(adap);
			} else {
				showToast(consultarSKUPrioritarioProxy.getResponse()
						.getDescripcion());
			}
		} else {
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
		finish();
		onBackPressed();
	}

	public void btnGuardar_click(View view) {
		List<SKUPresentacionTO> skuPresentaciones = application.guardarSKUPresentacion;

		if (skuPresentaciones == null) {
			skuPresentaciones = new ArrayList<SKUPresentacionTO>();
		} else {
			skuPresentaciones.clear();
		}

		EfficientAdapter adap = (EfficientAdapter) getListAdapter();

		if (adap == null) {
			adap = new EfficientAdapter(this,
					new ArrayList<SKUPresentacionTO>());
		}
		
		application.guardarSKUPresentacion = adap.skuPresentaciones;
		processAsync(ACCION_GUARDAR);
	}

	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_GUARDAR)
		{
			guardarNuevoDesarrolloProxy.oportunidadSistema = application.guardarOportunidades;
			guardarNuevoDesarrolloProxy.listSKUPresentacion = application.guardarSKUPresentacion;
			guardarNuevoDesarrolloProxy.activosLindley = application.activosLindley;
			guardarNuevoDesarrolloProxy.codigoCliente = cliente.getCodigo();
			guardarNuevoDesarrolloProxy.codigoUsuario = usuario.getCodigoSap();
			guardarNuevoDesarrolloProxy.codigoFDE = cliente.getCluster();
			guardarNuevoDesarrolloProxy.execute();
		}
	}
	
	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion == ACCION_GUARDAR)
    	{
			boolean isExito = guardarNuevoDesarrolloProxy.isExito();
			if (isExito) {
				int status = guardarNuevoDesarrolloProxy.getResponse().getStatus();
				if (status == 0) {
					String idRegistro = guardarNuevoDesarrolloProxy.getResponse().getCodCabecera();
					finish();
					Intent compromisoOpen = new Intent("lindley.desarrolloxcliente.compromisoprincipalopen");
					compromisoOpen.putExtra(CompromisoPrincipalOpen_Resumen.CODIGO_REGISTRO, idRegistro);					
					compromisoOpen.putExtra(CompromisoPrincipalOpen_Resumen.ORIGEN_REGISTRO, "N");
					startActivity(compromisoOpen);
				}
				else  {
					showToast(guardarNuevoDesarrolloProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError();
			}
			super.processOk();
    	}
	}
	
	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError(accion);
		showToast(error_generico_process);
	}
	
	public static class EfficientAdapter extends
			ArrayAdapter<SKUPresentacionTO> {
		private Activity context;
		private List<SKUPresentacionTO> skuPresentaciones;

		public EfficientAdapter(Activity context,
				List<SKUPresentacionTO> skuPresentaciones) {
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

				viewHolder.txViewSKU = (TextView) view
						.findViewById(R.id.txViewSKU);
				viewHolder.chkValActual = (Spinner) view
						.findViewById(R.id.chkValActual);
				
				viewHolder.chkValActual.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						SKUPresentacionTO skuPresentacion = (SKUPresentacionTO) viewHolder.chkValActual
								.getTag();
						if(arg2==0){
							skuPresentacion.valorActual = RESPUESTA_NO;
						}else if(arg2==1){
							skuPresentacion.valorActual = RESPUESTA_SI;
						}else{
							skuPresentacion.valorActual = RESPUESTA_NO_TIENE ;
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				});

				view.setTag(viewHolder);
				viewHolder.chkValActual.setTag(this.skuPresentaciones
						.get(position));

			} else {
				view = convertView;
				((ViewHolder) view.getTag()).chkValActual
						.setTag(this.skuPresentaciones.get(position));
			}

			ViewHolder holder = (ViewHolder) view.getTag();
			SKUPresentacionTO skuPresentacion = skuPresentaciones.get(position);

			holder.txViewSKU.setText(skuPresentacion.getDescripcionSKU());
			
			ArrayAdapter<CharSequence> adap = ArrayAdapter.createFromResource(application.getApplicationContext(),R.array.sku_estados,android.R.layout.simple_spinner_item);
			adap.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
			holder.chkValActual.setAdapter(adap);
		      
			if (this.skuPresentaciones.get(position).valorActual.equalsIgnoreCase(RESPUESTA_NO)) {
				holder.chkValActual.setSelection(0);
			} else if (this.skuPresentaciones.get(position).valorActual.equalsIgnoreCase(RESPUESTA_SI)) {
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
