package pe.pae.encuesta.activity;

import java.util.ArrayList;

import pe.pae.encuesta.R;
import pe.pae.encuesta.negocio.ClienteBLL;
import pe.pae.encuesta.negocio.EncuestaBLL;
import pe.pae.encuesta.to.ClienteTO;
import pe.pae.encuesta.to.TiendaTO;
import pe.pae.encuesta.ws.service.EncuestaProxy;
import roboguice.inject.InjectView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ActivityBase;

public class Tienda_Seleccionar_Activity extends ActivityBase {

@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
@Inject 						ClienteBLL 	clienteBLL; 
@Inject 						EncuestaBLL encuestaBLL;
@InjectView(R.id.cboCliente)   	Spinner 	cboCliente;
@InjectView(R.id.cboTienda)   	Spinner 	cboTienda;

@Inject 						EncuestaProxy 	encuestaProxy;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarRecursos();
        
        setContentView(R.layout.tienda_seleccionar_activity);
        mActionBar.setTitle(R.string.tienda_seleccionar_activity_title);
     
        
        ArrayList<ClienteTO> clientes = clienteBLL.listarClientes();
        ClienteTO clienteTO = new ClienteTO();
        clienteTO.clienteId=0;
        clienteTO.descripcion="--Seleccionar--";
        clientes.add(0,clienteTO);
        
        ArrayAdapter<ClienteTO> adap = new ArrayAdapter<ClienteTO>(this, android.R.layout.simple_spinner_item,clientes);
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cboCliente.setAdapter(adap);
        
    
        final Context ctx = this;
        cboCliente.setOnItemSelectedListener(new OnItemSelectedListener() {
        	
			public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
				// TODO Auto-generated method stub
				ArrayList<TiendaTO> tiendas = null;
				ArrayAdapter<TiendaTO> tiendasAdapter = null;
				if (position >0){
					int clienteId = ((ClienteTO) cboCliente.getSelectedItem()).clienteId;
					tiendas = clienteBLL.listarTiendas(clienteId);
					
					
				}else{
					tiendas = new ArrayList<TiendaTO>();
					
				}
				
				TiendaTO tiendaTO = new TiendaTO();
				tiendaTO.tiendaId=0;
				tiendaTO.nombre="--Seleccionar--";
				tiendas.add(0, tiendaTO);
				
				tiendasAdapter = new ArrayAdapter<TiendaTO>(ctx, android.R.layout.simple_spinner_item,tiendas);
				tiendasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				cboTienda.setAdapter(tiendasAdapter);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    public void btnIngresar_onclick(View v){
    	processAsync();
    }

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		
		ClienteTO clienteTO = ((ClienteTO) cboCliente.getSelectedItem());
		MiApp miApp = (MiApp)getApplication();
		
		
		encuestaProxy.clienteId=clienteTO.clienteId;
		encuestaProxy.usuarioId=miApp.getUsuarioTO().usuarioId;
		encuestaProxy.execute();
		
		super.process();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		
		boolean isExito = encuestaProxy.isExito();
		if (isExito) {
			int status = encuestaProxy.getResponse().getStatus();
				if (status == 0) {
					
					encuestaBLL.saveProductos(encuestaProxy.getResponse().productos);
					encuestaBLL.saveEncuestas(encuestaProxy.getResponse().encuestas);
					
					Intent i = new Intent("pae.activity.buscarProducto");
			    	startActivity(i);
				}
		}
		
		
		super.processOk();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
	}
    
    
}
