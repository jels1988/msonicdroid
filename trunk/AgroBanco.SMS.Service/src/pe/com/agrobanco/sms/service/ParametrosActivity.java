package pe.com.agrobanco.sms.service;

import pe.edu.agrobanco.sms.service.R;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ParametrosActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.parametros_activty);
		addPreferencesFromResource(R.xml.preferences);
	}

	
	
	
}
