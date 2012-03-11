package pe.pae.encuesta.activity;

import pe.pae.encuesta.R;
import roboguice.inject.InjectView;

import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import net.msonic.lib.ListActivityBase;

public class Producto_Buscar_Activity extends ListActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        
	        setContentView(R.layout.producto_buscar_activity);
	        mActionBar.setTitle(R.string.producto_buscar_activity_title);
	        mActionBar.setSubTitle("RIPLEY-SAN MIGUEL");
	    }
	   
	   public void btnBuscar_onclick(View v){
		   Intent i = new Intent("pae.activity.custionarioProducto");
	    	startActivity(i);
	   }
}
