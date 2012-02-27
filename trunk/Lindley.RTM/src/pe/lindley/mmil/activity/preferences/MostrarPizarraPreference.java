package pe.lindley.mmil.activity.preferences;

import pe.lindley.activity.R;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MostrarPizarraPreference extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.layout.mmil_mostrar_pizarra_preference);
	}

}
