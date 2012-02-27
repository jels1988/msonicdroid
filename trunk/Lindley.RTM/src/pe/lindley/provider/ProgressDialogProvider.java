package pe.lindley.provider;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProgressDialogProvider implements Provider<ProgressDialog> {

	@Inject
    private Context context; 
	
	
	public ProgressDialog get() {
		// TODO Auto-generated method stub
		return new ProgressDialog(context);
	}
	
	

}
