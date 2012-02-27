package pe.lindley.ficha.activity;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.ficha.to.ComercialTO;
import pe.lindley.ficha.ws.service.ObtenerComercialProxy;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectView;

public class ComercialActivity extends ActivityBase {

	@InjectView(R.id.mActionBar)ActionBar mActionBar;
	@Inject ObtenerComercialProxy obtenerComercialProxy; 
	
	@InjectView(R.id.txtCanal)TextView txtCanal;
	@InjectView(R.id.txtSubCanal)TextView txtSubCanal;
	@InjectView(R.id.txtCnlRTM)TextView txtCnlRTM;
	@InjectView(R.id.txtSubRTM)TextView txtSubRTM;
	@InjectView(R.id.txtGiro)TextView txtGiro;
	@InjectView(R.id.txtCluster)TextView txtCluster;
	@InjectView(R.id.txtMatriz)TextView txtMatriz;
	@InjectView(R.id.txtModelo)TextView txtModelo;
	
	public static final String CODIGO_CLIENTE_KEY="codigo_cliente";
	private String codigo_cliente = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ficha_comercial_activity);
		Intent intent = this.getIntent();
		codigo_cliente = intent.getStringExtra(CODIGO_CLIENTE_KEY);
		RTMApplication application = (RTMApplication)getApplicationContext();
		ClienteResumenTO cliente = application.getClienteTO();
		mActionBar.setSubTitle(cliente.getCodigoCliente()+"-"+cliente.getRazonSocial());
		mActionBar.setTitle(R.string.ficha_comercial_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
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
		obtenerComercialProxy.setCliente(codigo_cliente);
		obtenerComercialProxy.execute();
		super.process();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = obtenerComercialProxy.isExito();
		if (isExito) {
			int status = obtenerComercialProxy.getResponse().getStatus();
			if (status == 0) {
				ComercialTO comercial = obtenerComercialProxy
						.getResponse().getComercial();
				
				txtCanal.setText(comercial.getCanal());
				txtSubCanal.setText(comercial.getSubCanal());
				txtCnlRTM.setText(comercial.getCanalRTM());
				txtSubRTM.setText(comercial.getSubCanalRTM());
				txtGiro.setText(comercial.getDescripcionGiroSec());
				txtCluster.setText(comercial.getCluster());
				txtMatriz.setText(comercial.getMatriz());
				txtModelo.setText(comercial.getModeloRTM());
			}
			else  {
				showToast(obtenerComercialProxy.getResponse().getDescripcion());
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
	
	public void btn_editarGiro(View view)
	{
		Intent intent = new Intent(this,ActualizarComercialActivity.class);
		intent.putExtra(ActualizarComercialActivity.CODIGO_CLIENTE, codigo_cliente);
		intent.putExtra(ActualizarComercialActivity.CODIGO_GIRO, obtenerComercialProxy.getResponse().getComercial().getCodGiroSecundario());
		startActivity(intent);
		
		
		//profit.putExtra(ProfitHistoryDatosComercialesActivity.CODIGO_CLIENTE_KEY,clienteTO.getCodigo());
		//profit.putExtra(ProfitHistoryDatosComercialesActivity.CLIENTE_KEY,clienteTO.getRazonSocial());
		//profit.putExtra(ProfitHistoryDatosComercialesActivity.TIPO_KEY,ProfitHistoryDatosComercialesActivity.TIPO_AVANCE_RESUMEN);
		
	}

}
