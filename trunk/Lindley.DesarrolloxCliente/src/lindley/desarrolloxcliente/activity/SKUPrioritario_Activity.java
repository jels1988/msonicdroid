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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

public class SKUPrioritario_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)
	ActionBar mActionBar;
	private EfficientAdapter adap;
	private MyApplication application;
	ClienteTO cliente;
	UsuarioTO usuario;
	@Inject	ConsultarSKUPrioritarioProxy consultarSKUPrioritarioProxy;
    @Inject GuardarNuevoDesarrolloProxy guardarNuevoDesarrolloProxy;
	@InjectView(R.id.txtViewFecha) 		TextView txtViewFecha;
	public final String RESPUESTA_SI = "S";
	public final String RESPUESTA_NO = "N";
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
		MessageBox.showConfirmDialog(this, "Posición-Activos",
				"¿Tiene activos de la empresa ?", "SI",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						application.activosLindley = RESPUESTA_SI;
						processAsync();
					}

				}, "NO", new android.content.DialogInterface.OnClickListener() {
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

	public void btnCancelar_click(View view) {
		MessageBox.showConfirmDialog(this, confirm_atras_title,
				confirm_atras_message, confirm_atras_yes,
				new android.content.DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(
								"lindley.desarrolloxcliente.consultarcliente");
						startActivity(intent);
					}

				}, confirm_atras_no,
				new android.content.DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}

				});
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
					
					Intent compromisoOpen = new Intent("lindley.desarrolloxcliente.compromisoprincipalopen");
					compromisoOpen.putExtra(CompromisoPrincipalOpen_Resumen.CODIGO_REGISTRO, idRegistro);
					compromisoOpen.putExtra(CompromisoPrincipalOpen_Resumen.FLAG_FECHA, CompromisoPrincipalOpen_Resumen.FLAG_OPEN_FECHA_ABIERTO);
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
				viewHolder.chkValActual = (CheckBox) view
						.findViewById(R.id.chkValActual);

				viewHolder.chkValActual
						.setOnCheckedChangeListener(new OnCheckedChangeListener() {

							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {
								// TODO Auto-generated method stub
								SKUPresentacionTO skuPresentacion = (SKUPresentacionTO) viewHolder.chkValActual
										.getTag();
								skuPresentacion.seleccionado = isChecked;
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
			if (this.skuPresentaciones.get(position).seleccionado) {
				holder.chkValActual.setChecked(true);
			} else {
				holder.chkValActual.setChecked(false);
			}

			return view;
		}

		static class ViewHolder {
			TextView txViewSKU;
			CheckBox chkValActual;
		}

	}
}
