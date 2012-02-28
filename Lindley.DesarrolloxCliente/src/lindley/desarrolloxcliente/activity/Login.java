package lindley.desarrolloxcliente.activity;

import roboguice.inject.InjectView;

import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ActivityBase;
import lindley.desarrolloxcliente.R;
import android.os.Bundle;

public class Login extends ActivityBase {
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        
        mActionBar.setTitle(R.string.login_activity_title);
        mActionBar.setHomeLogo(R.drawable.header_logo);
    }
}