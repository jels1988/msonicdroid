package pe.lindley.preliquidacion;

import roboguice.inject.InjectView;

import com.thira.examples.actionbar.widget.ActionBar;

import android.os.Bundle;
import net.msonic.lib.ActivityBase;

public class AvanceActivity extends ActivityBase {

	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avance_activity);
		
		mActionBar.setTitle(R.string.login_activity_sub_title);
        mActionBar.setHomeLogo(R.drawable.header_logo);
    	
	}

}
