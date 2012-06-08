package pe.lindley.mmil.titanium;

import pe.lindley.mmil.titanium.ws.service.ClientesCreditosProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.os.Bundle;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ActivityBase;

public class ConsultarClienteActivity extends ActivityBase {

	
	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_DEPOSITO_KEY = "CODIGO_DEPOSITO";
	public static final String CODIGO_SUPERVISOR_KEY = "CODIGO_SUPERVISOR";
	
	@InjectExtra(CODIGO_SUPERVISOR_KEY) String codigoSupervisor;
	@InjectExtra(CODIGO_DEPOSITO_KEY) String codigoCda;
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	
	
	@Inject ClientesCreditosProxy clientesCreditosProxy;
	
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      inicializarRecursos();
      
      setContentView(R.layout.consultarcliente_activity);
      
      mActionBar.setHomeLogo(R.drawable.header_logo);
      mActionBar.setTitle(R.string.consultarcliente_activity_cliente);
      mActionBar.setSubTitle(nombre_cda);
      
      processAsync();
      
  }
}
