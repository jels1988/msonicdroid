package pe.pae.encuesta.activity;

import pe.pae.encuesta.R;
import pe.pae.encuesta.adapter.ClienteAdapter;
import pe.pae.encuesta.negocio.ClienteBLL;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ActivityBase;

public class Tienda_Seleccionar_Activity extends ActivityBase {

@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
@Inject 						ClienteBLL 	clienteBLL; 
@InjectView(R.id.cboCliente)   	Spinner 	cboCliente;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.tienda_seleccionar_activity);
        mActionBar.setTitle(R.string.tienda_seleccionar_activity_title);
        
        
        ClienteAdapter adapter = new ClienteAdapter(this, clienteBLL.listarClientes());
        cboCliente.setAdapter(adapter);
        
        
    }
    
    public void btnIngresar_onclick(View v){
    	Intent i = new Intent("pae.activity.buscarProducto");
    	startActivity(i);
    }
}
