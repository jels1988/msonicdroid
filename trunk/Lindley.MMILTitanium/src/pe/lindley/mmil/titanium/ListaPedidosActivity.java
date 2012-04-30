package pe.lindley.mmil.titanium;

import roboguice.inject.InjectView;

import com.thira.examples.actionbar.widget.ActionBar;

import android.os.Bundle;
import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MapActivityBase;

public class ListaPedidosActivity extends MapActivityBase {
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        inicializarRecursos();
	        
	        setContentView(R.layout.login_activity);
	        
	        mActionBar.setHomeLogo(R.drawable.header_logo);
	        mActionBar.setTitle(R.string.lista_pedidos_activity_title);
	    }
}
