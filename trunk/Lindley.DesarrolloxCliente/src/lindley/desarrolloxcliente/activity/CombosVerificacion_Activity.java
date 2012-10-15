package lindley.desarrolloxcliente.activity;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import net.msonic.lib.sherlock.FragmentBase;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CombosVerificacion_Activity  extends FragmentBase {

	private EvaluacionTO evaluacion;
	private  MyApplication application;
	private ClienteTO cliente;
	
	@InjectView(R.id.txtObs) EditText txtObs;
	@InjectView(R.id.txtObsSS) EditText txtObsSS;
	
	@InjectView(R.id.radGrSS) RadioGroup radGrSS;
	@InjectView(R.id.radGrMS) RadioGroup radGrMS;
	
	
	 @Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			 return inflater.inflate(R.layout.informacionadicional_activity, container,false);
		 }
	 
	 public static int VISTA_CARGADA=0;
	 
	 
	 @Override public void onActivityCreated(Bundle savedInstanceState) {
         super.onActivityCreated(savedInstanceState);
         inicializarRecursos();
        // this.validarConexionInternet=false;
         
         if(VISTA_CARGADA==0){
	 		VISTA_CARGADA=1;
	 		application = (MyApplication) getActivity().getApplicationContext();
	 		evaluacion = application.evaluacion;
	 		cliente = application.cliente;
	 		
	 		txtObs.setEnabled(true);
	 		txtObsSS.setEnabled(false);
	 		radGrSS.setEnabled(false);
	 		radGrMS.setEnabled(false);
         }
	 }
	 
}
