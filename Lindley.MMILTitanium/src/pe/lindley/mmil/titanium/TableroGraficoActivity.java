package pe.lindley.mmil.titanium;

import roboguice.inject.InjectView;

import com.thira.examples.actionbar.widget.ActionBar;

import android.os.Bundle;
import net.msonic.lib.ActivityBase;

public class TableroGraficoActivity extends ActivityBase {
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarRecursos();
        
        setContentView(R.layout.tablero_activity);
        
        mActionBar.setHomeLogo(R.drawable.header_logo);
        mActionBar.setTitle(R.string.tablero_grafico_activity_title);
    }
}
