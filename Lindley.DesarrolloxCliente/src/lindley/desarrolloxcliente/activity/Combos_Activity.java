package lindley.desarrolloxcliente.activity;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import net.msonic.lib.sherlock.FragmentBase;

public class Combos_Activity extends FragmentBase {

	private EvaluacionTO evaluacion;
	private  MyApplication application;
	private ClienteTO cliente;
	
	@InjectView(R.id.txtObs) EditText txtObs;
	@InjectView(R.id.txtObsSS) EditText txtObsSS;
	@InjectView(R.id.radSSSi) RadioButton radSSSi;
	@InjectView(R.id.radSSNo) RadioButton radSSNo;
	@InjectView(R.id.radMSSi) RadioButton radMSSi;
	@InjectView(R.id.radMSNo) RadioButton radMSNo;
	
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
	 	
         }
	 }
	 
}
