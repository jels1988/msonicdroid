package pe.lindley.mmil.titanium;

import pe.lindley.mmil.titanium.ws.service.ClientesCreditosProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ActivityBase;

public class ConsultasActivity extends ActivityBase {

	
	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_DEPOSITO_KEY = "CODIGO_DEPOSITO";
	public static final String CODIGO_SUPERVISOR_KEY = "CODIGO_SUPERVISOR";
	
	@InjectExtra(CODIGO_SUPERVISOR_KEY) String codigoSupervisor;
	@InjectExtra(CODIGO_DEPOSITO_KEY) String codigoCda;
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	@InjectResource(R.string.consultas_activity_codigo_empty) String txtcodigo_empty;
	
	@Inject ClientesCreditosProxy clientesCreditosProxy;
	
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@InjectView(R.id.txtCliente)  		EditText 	txtCliente;
	
	  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      inicializarRecursos();
      
      setContentView(R.layout.consultas_activity);
      
      mActionBar.setHomeLogo(R.drawable.header_logo);
      mActionBar.setTitle(R.string.consultas_activity_cliente);
      mActionBar.setSubTitle(nombre_cda);
      
      //processAsync();
      
  }
  

  public void btnUltimoPedido_onClick(View v){
	  	
	  	String codigoCliente = txtCliente.getText().toString();
	  	if(codigoCliente==null||codigoCliente.compareTo("")==0){
	  		txtCliente.setError(txtcodigo_empty);
	  		return;
	  	}
	  
		Intent intent = new Intent(this, ConsultarClienteActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra(ConsultarClienteActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
		intent.putExtra(ConsultarClienteActivity.CODIGO_DEPOSITO_KEY, codigoCda);
		intent.putExtra(ConsultarClienteActivity.NOMBRE_CDA_KEY, nombre_cda);
		intent.putExtra(ConsultarClienteActivity.CODIGO_CLIENTE_KEY, txtCliente.getText().toString());
    	startActivity(intent);
  }
  
  
  public void btnFigurasComerciales_onClick(View v){
	  
	  String codigoCliente = txtCliente.getText().toString();
	  	if(codigoCliente==null||codigoCliente.compareTo("")==0){
	  		txtCliente.setError(txtcodigo_empty);
	  		return;
	  	}
	  
		Intent intent = new Intent(this, FigurasComercialesActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra(ProfitHistoryDatosComercialesActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
		intent.putExtra(ProfitHistoryDatosComercialesActivity.CODIGO_DEPOSITO_KEY, codigoCda);
		intent.putExtra(ProfitHistoryDatosComercialesActivity.NOMBRE_CDA_KEY, nombre_cda);
		intent.putExtra(ProfitHistoryDatosComercialesActivity.CODIGO_CLIENTE_KEY, txtCliente.getText().toString());
		
		startActivity(intent);
  }
  
  public void btnDatosResumen_onClick(View v){
	  String codigoCliente = txtCliente.getText().toString();
	  	if(codigoCliente==null||codigoCliente.compareTo("")==0){
	  		txtCliente.setError(txtcodigo_empty);
	  		return;
	  	}
	  
		Intent intent = new Intent(this, ProfitHistoryDatosComercialesActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra(ProfitHistoryDatosComercialesActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
		intent.putExtra(ProfitHistoryDatosComercialesActivity.CODIGO_DEPOSITO_KEY, codigoCda);
		intent.putExtra(ProfitHistoryDatosComercialesActivity.NOMBRE_CDA_KEY, nombre_cda);
		intent.putExtra(ProfitHistoryDatosComercialesActivity.CODIGO_CLIENTE_KEY, txtCliente.getText().toString());
		intent.putExtra(ProfitHistoryDatosComercialesActivity.TIPO_KEY, ProfitHistoryDatosComercialesActivity.TIPO_DATOS_COMERCIALES);
		startActivity(intent);
  }
  
  public void btnDatosComerciales_onClick(View v){
	  String codigoCliente = txtCliente.getText().toString();
	  	if(codigoCliente==null||codigoCliente.compareTo("")==0){
	  		txtCliente.setError(txtcodigo_empty);
	  		return;
	  	}
	  
		Intent intent = new Intent(this, ProfitHistoryDatosComercialesActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra(ProfitHistoryDatosComercialesActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
		intent.putExtra(ProfitHistoryDatosComercialesActivity.CODIGO_DEPOSITO_KEY, codigoCda);
		intent.putExtra(ProfitHistoryDatosComercialesActivity.NOMBRE_CDA_KEY, nombre_cda);
		intent.putExtra(ProfitHistoryDatosComercialesActivity.CODIGO_CLIENTE_KEY, txtCliente.getText().toString());
		intent.putExtra(ProfitHistoryDatosComercialesActivity.TIPO_KEY, ProfitHistoryDatosComercialesActivity.TIPO_AVANCE_RESUMEN);
		startActivity(intent);
  }
  
  
  public void btnProfit_onClick(View v){
	  
	  String codigoCliente = txtCliente.getText().toString();
	  	if(codigoCliente==null||codigoCliente.compareTo("")==0){
	  		txtCliente.setError(txtcodigo_empty);
	  		return;
	  	}
	  
		Intent intent = new Intent(this, ProfitMensualActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra(ProfitMensualActivity.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
		intent.putExtra(ProfitMensualActivity.CODIGO_DEPOSITO_KEY, codigoCda);
		intent.putExtra(ProfitMensualActivity.NOMBRE_CDA_KEY, nombre_cda);
		intent.putExtra(ProfitMensualActivity.CODIGO_CLIENTE_KEY, txtCliente.getText().toString());
		startActivity(intent);
  }
}
