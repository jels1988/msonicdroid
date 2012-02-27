package pe.lindley.red.activity;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.red.to.DatosFichaTO;
import pe.lindley.red.ws.service.ConsultarFichaProxy;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectView;

public class FichaClienteActivity extends ActivityBase {

	public static final String CODIGO_CLIENTE_KEY="codigo_cliente";
	public static final String CLIENTE_KEY="cliente_descripcion";
	public static final String FECHA_ENCUESTA_KEY="fecha_encuesta";
	
	public static String cliente_codigo;
	public static String cliente_descripcion;
	public static String fecha_encuesta;
	
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@Inject	ConsultarFichaProxy consultarFichaProxy;
	
	@InjectView(R.id.txtFecha) TextView txtFecha;
	@InjectView(R.id.txtCodigo) TextView txtCodigo;
	@InjectView(R.id.txtNombre) TextView txtNombre;
	@InjectView(R.id.txtDireccion) TextView txtDireccion;
	@InjectView(R.id.txtUbicacion) TextView txtUbicacion;
	@InjectView(R.id.txtDistribuidor) TextView txtDistribuidor;
	@InjectView(R.id.txtRuta) TextView txtRuta;
	@InjectView(R.id.txtSegmento) TextView txtSegmento;
	@InjectView(R.id.txtCluster) TextView txtCluster;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.red_fichacliente_activity);
		
		Intent intent = this.getIntent();
		cliente_codigo = intent.getStringExtra(CODIGO_CLIENTE_KEY);
		cliente_descripcion = intent.getStringExtra(CLIENTE_KEY);
		fecha_encuesta = intent.getStringExtra(FECHA_ENCUESTA_KEY);
		
		mActionBar.setTitle(R.string.red_ficha_cliente_title);
		mActionBar.setSubTitle(cliente_descripcion);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
		processAsync();
	}

	@Override
	protected void processOk() {
		boolean isExito = consultarFichaProxy.isExito();
		if (isExito) {
			int status = consultarFichaProxy.getResponse().getStatus();
			
			if (status == 0) {
				DatosFichaTO datosFichaTO = consultarFichaProxy.getResponse().getFichaCliente();
				txtFecha.setText(fecha_encuesta);
				txtCodigo.setText(datosFichaTO.getCodigo());
				txtNombre.setText(datosFichaTO.getNombre());
				txtDireccion.setText(datosFichaTO.getDireccion());
				
				txtUbicacion.setText(datosFichaTO.getCiudad());
				txtDistribuidor.setText(datosFichaTO.getDistribuidor());
				txtRuta.setText(datosFichaTO.getRuta());
				txtSegmento.setText(datosFichaTO.getSegmento());
				txtCluster.setText(datosFichaTO.getCluster());
			}
			
			
		}
		super.processOk();
	}
	@Override
	protected void process() {
		consultarFichaProxy.setCodigoCliente(cliente_codigo);
		consultarFichaProxy.setFechaEncuesta(fecha_encuesta.replace("/", ""));
		consultarFichaProxy.execute();
	}
	
	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
	}

}
