package lindley.desarrolloxcliente.activity;

import lindley.desarrolloxcliente.R;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;

import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ListActivityBase;

public class ConsultarCliente_Activity extends ListActivityBase {

@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultarcliente_activity);
        
        mActionBar.setTitle(R.string.consultarcliente_activity_title);
        mActionBar.setHomeLogo(R.drawable.header_logo);
    }
    
    
    public void btnbuscar_onclick(View view){
    	
    }
}
