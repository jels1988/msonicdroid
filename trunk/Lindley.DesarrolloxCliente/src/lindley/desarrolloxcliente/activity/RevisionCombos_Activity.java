package lindley.desarrolloxcliente.activity;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import net.msonic.lib.sherlock.FragmentBase;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

public class RevisionCombos_Activity extends FragmentBase {

	private EvaluacionTO evaluacion;
	private  MyApplication application;
	
	@InjectView(R.id.txtObsMS) EditText txtObsMS;
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
         this.validarConexionInternet=false;
     
         
         if(VISTA_CARGADA==0){
        	 
	 		VISTA_CARGADA=1;
	 		application = (MyApplication) getActivity().getApplicationContext();
	 		evaluacion = application.evaluacionActual;
	 		
	 		radSSSi.setEnabled(false);
	 		radSSNo.setEnabled(false);
	 		
	 		radMSSi.setEnabled(false);
	 		radMSNo.setEnabled(false);
	 		
	 		txtObsMS.setEnabled(false);
	 		
	 		
	 		if(evaluacion.comboSS.equals(ConstantesApp.RESPUESTA_SI)){
	 			radSSSi.setChecked(true);
	 			radSSNo.setChecked(false);
	 		}else{
	 			radSSSi.setChecked(false);
	 			radSSNo.setChecked(true);
	 		}
	 		

	 		if(evaluacion.comboMS.equals(ConstantesApp.RESPUESTA_SI)){
	 			radMSSi.setChecked(true);
	 			radMSNo.setChecked(false);
	 		}else{
	 			radMSSi.setChecked(false);
	 			radMSNo.setChecked(true);
	 		}
	 		
	 		txtObsSS.setText(evaluacion.observacionSS);
	 		txtObsMS.setText(evaluacion.observacionMS);
	 		
         }
         
         txtObsMS.addTextChangedListener(new TextWatcher() {
 			
 			@Override
 			public void onTextChanged(CharSequence s, int start, int before, int count) {
 				// TODO Auto-generated method stub
 				
 			}
 			
 			@Override
 			public void beforeTextChanged(CharSequence s, int start, int count,
 					int after) {
 				// TODO Auto-generated method stub
 				
 			}
 			
 			@Override
 			public void afterTextChanged(Editable s) {
 				// TODO Auto-generated method stub
 				evaluacion.observacionMS=txtObsMS.getText().toString();
 			}
 		});
         
         /*
         radSSSi.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				evaluacion.comboSS=ConstantesApp.RESPUESTA_SI;
			}
		});
         
         radSSNo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
 			
 			@Override
 			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
 				// TODO Auto-generated method stub
 				evaluacion.comboSS=ConstantesApp.RESPUESTA_NO;
 			}
 		});
         
         radMSSi.setOnCheckedChangeListener(new OnCheckedChangeListener() {
  			
  			@Override
  			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
  				// TODO Auto-generated method stub
  				evaluacion.comboMS=ConstantesApp.RESPUESTA_SI;
  			}
  		});
         
         radMSNo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
   			
   			@Override
   			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
   				// TODO Auto-generated method stub
   				evaluacion.comboMS=ConstantesApp.RESPUESTA_NO;
   			}
   		});
        
         
         
        
         
         txtObsSS.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				evaluacion.observacionSS=txtObsSS.getText().toString();
			}
		});*/
         
   
         
	 }
	 
}
