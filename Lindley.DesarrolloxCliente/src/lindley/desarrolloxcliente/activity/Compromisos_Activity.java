package lindley.desarrolloxcliente.activity;

import lindley.desarrolloxcliente.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import net.msonic.lib.sherlock.ListBaseFragment;

public class Compromisos_Activity extends ListBaseFragment {

	
	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 return inflater.inflate(R.layout.compromisoopen_activity, container,false);
	 }
	 
	 private static int VISTA_CARGADA=0;
	 
	 @Override public void onActivityCreated(Bundle savedInstanceState) {
         super.onActivityCreated(savedInstanceState);
         if(VISTA_CARGADA==0){
	 		VISTA_CARGADA=1;
	         processAsync();
         }
         
	 }
}
