package pe.pae.encuesta.activity;

import pe.pae.encuesta.R;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ActivityBase;

public class Tienda_Seleccionar_Activity extends ActivityBase {

@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.tienda_seleccionar_activity);
        mActionBar.setTitle(R.string.tienda_seleccionar_activity_title);
        
    }
    
    public void btnIngresar_onclick(View v){
    	Intent i = new Intent("pae.activity.buscarProducto");
    	startActivity(i);
    }
}
