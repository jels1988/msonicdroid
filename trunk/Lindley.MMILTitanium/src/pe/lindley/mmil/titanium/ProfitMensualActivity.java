package pe.lindley.mmil.titanium;

import java.util.Calendar;

import pe.lindley.mmil.titanium.to.HistoryDetalleTO;
import pe.lindley.mmil.titanium.ws.service.ProfitHistoryProxy;


import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import net.msonic.lib.ActivityBase;
import net.msonic.lib.MessageBox;

public class ProfitMensualActivity extends net.msonic.lib.sherlock.ActivityBase {

	public static final int CAJAS_FISICAS_ACUMULADAS=0;
	public static final int CAJAS_UNITARIAS_ACUMULADAS=1;
	public static final int IMPORTE_FACTURADO=2;
	public static final int IMPORTE_UTILIDAD=3;
	
	
	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_DEPOSITO_KEY = "CODIGO_DEPOSITO";
	public static final String CODIGO_SUPERVISOR_KEY = "CODIGO_SUPERVISOR";
	public static final String CODIGO_CLIENTE_KEY = "CODIGO_CLIENTE";
	
	@InjectExtra(CODIGO_SUPERVISOR_KEY) String codigoSupervisor;
	@InjectExtra(CODIGO_DEPOSITO_KEY) String codigoCda;
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	@InjectExtra(CODIGO_CLIENTE_KEY) String codigoCliente;
	
	//@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	
	
	
	@InjectView(R.id.cboHistoryAnio) Spinner cboHistoryAnio;
	@InjectView(R.id.cboHistoryMes) Spinner cboHistoryMes;
	@InjectView(R.id.cboHistoryTipo) Spinner cboHistoryTipo;
	@InjectView(R.id.cboHistoryCampo) Spinner cboHistoryCampo;
	
	@Inject ProfitHistoryProxy profitHistoryProxy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		inicializarRecursos();
		setContentView(R.layout.profitmensual_activity);
			
		 /*
	      mActionBar.setHomeLogo(R.drawable.header_logo);
	      mActionBar.setTitle(R.string.profitmensual_activity_title);
	      mActionBar.setSubTitle(nombre_cda + " / " + codigoCliente);
	      */
		
		  setTitle(R.string.profitmensual_activity_title);
	      getSupportActionBar().setSubtitle(codigoCliente + " / " + nombre_cda);
	      
	      
	      ArrayAdapter<CharSequence> adapterMes = ArrayAdapter.createFromResource(this, R.array.meses_array, android.R.layout.simple_spinner_item);
	      adapterMes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	      cboHistoryMes.setAdapter(adapterMes);
	      
	      ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(this, R.array.profit_tipo, android.R.layout.simple_spinner_item);
	      adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	      cboHistoryTipo.setAdapter(adapterTipo);
	      
	      cboHistoryTipo.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				executeWS=true;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	      });
	      
	      ArrayAdapter<CharSequence> adapterCampo = ArrayAdapter.createFromResource(this, R.array.profit_campo, android.R.layout.simple_spinner_item);
	      adapterCampo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	      cboHistoryCampo.setAdapter(adapterCampo);
	      
	      
	     
	      String[] anios = new String[4];

			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);

			for (int i = 0; i < 4; i++) {
				anios[i] = String.format("%s", year - i);
			}

			ArrayAdapter<String> adapterAnios = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,anios);
			adapterAnios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			cboHistoryAnio.setAdapter(adapterAnios);
			cboHistoryAnio.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					executeWS=true;
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
	}

	
	
	 @Override
		protected void process() {
		 profitHistoryProxy.anio=Integer.parseInt(cboHistoryAnio.getSelectedItem().toString());
		 profitHistoryProxy.codigo=codigoCliente;
		 profitHistoryProxy.tipo=cboHistoryTipo.getSelectedItemPosition();
		 profitHistoryProxy.execute();
		 }
	
	 
	 	boolean executeWS=true;
	 	
	 	public void mostrarData(){
	 		
	 		String campo = cboHistoryCampo.getSelectedItem().toString();
	 		int campoIndex = cboHistoryCampo.getSelectedItemPosition();
	 		int anio = Integer.parseInt(cboHistoryAnio.getSelectedItem().toString());
			int tipo = cboHistoryTipo.getSelectedItemPosition();
	 		int mes = cboHistoryMes.getSelectedItemPosition();
	 		
	 		if(tipo==0){
	 			double[] anioActual = new double[12];
	 			
				for (HistoryDetalleTO data : profitHistoryProxy.getResponse().detalle) {			
						switch (campoIndex){
							case CAJAS_FISICAS_ACUMULADAS:
								anioActual[data.mes-1] = data.cajasFacturadas;
								break;
							case CAJAS_UNITARIAS_ACUMULADAS:
								anioActual[data.mes-1] = data.cajasUnitarias;
								break;
							case IMPORTE_FACTURADO:
								anioActual[data.mes-1] = data.importeFacturado;
								break;
							case IMPORTE_UTILIDAD:
								anioActual[data.mes-1] = data.utilidad;
								break;
						}
						
				}	
				
				
				Intent intent = new Intent(this,HistoryMensualGrafico.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra(HistoryMensualGrafico.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
				intent.putExtra(HistoryMensualGrafico.CODIGO_DEPOSITO_KEY, codigoCda);
				intent.putExtra(HistoryMensualGrafico.NOMBRE_CDA_KEY, nombre_cda);
				intent.putExtra(HistoryMensualGrafico.CODIGO_CLIENTE_KEY, codigoCliente);
				intent.putExtra(HistoryMensualGrafico.TITULOS_KEY, this.getResources().getStringArray(R.array.meses_abrev_array));
				intent.putExtra(HistoryMensualGrafico.VALORES_KEY, anioActual);
				intent.putExtra(HistoryMensualGrafico.CAMPO_KEY, campo);
				
				startActivity(intent);
	 		}else if(tipo==1){
	 			
		 			double[] anioActual = new double[12];
		 			double[] anioAnterior = new double[12];
		 			
		 			
		 			for (HistoryDetalleTO data : profitHistoryProxy.getResponse().detalle) {			
						switch (campoIndex){
							case CAJAS_FISICAS_ACUMULADAS:
								if(anio==data.anio){
									anioActual[data.mes-1] = data.cajasFacturadas;
								}else{
									anioAnterior[data.mes-1] = data.cajasFacturadas;
								}
								break;
							case CAJAS_UNITARIAS_ACUMULADAS:
								if(anio==data.anio){
									anioActual[data.mes-1] = data.cajasUnitarias;
								}else{
									anioAnterior[data.mes-1] = data.cajasUnitarias;
								}
										
								break;
							case IMPORTE_FACTURADO:
								if(anio==data.anio){
									anioActual[data.mes-1] = data.importeFacturado;
								}else{
									anioAnterior[data.mes-1] = data.importeFacturado;
								}
								break;
							case IMPORTE_UTILIDAD:
								if(anio==data.anio){
									anioActual[data.mes-1] = data.utilidad;
								}else{
									anioAnterior[data.mes-1] = data.utilidad;
								}
								break;
						}
						
				}
		 			
	 			Intent intent = new Intent(this,ProfitComparativoMensualGrafico.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra(ProfitComparativoMensualGrafico.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
				intent.putExtra(ProfitComparativoMensualGrafico.CODIGO_DEPOSITO_KEY, codigoCda);
				intent.putExtra(ProfitComparativoMensualGrafico.NOMBRE_CDA_KEY, nombre_cda);
				intent.putExtra(ProfitComparativoMensualGrafico.CODIGO_CLIENTE_KEY, codigoCliente);
				intent.putExtra(ProfitComparativoMensualGrafico.TITULOS_KEY, this.getResources().getStringArray(R.array.meses_abrev_array));
				intent.putExtra(ProfitComparativoMensualGrafico.VALORES_KEY, anioActual);
				intent.putExtra(ProfitComparativoMensualGrafico.VALORES_ANTERIOR_KEY, anioAnterior);
				intent.putExtra(ProfitComparativoMensualGrafico.CAMPO_KEY, campo);
				intent.putExtra(ProfitComparativoMensualGrafico.ANIO_KEY, anio);
				startActivity(intent);
	 			
	 		}else{
	 			
	 			double[] anioActual = new double[5];
	 			String[] tituloSemana = new String[5];
	 			int index=0;
	 			
				for (HistoryDetalleTO data : profitHistoryProxy.getResponse().detalle) {		
					if (data.mes == mes+1) {
						
						tituloSemana[index] = String.valueOf(data.semana) ;
						
						
						switch (campoIndex){
							case CAJAS_FISICAS_ACUMULADAS:
								anioActual[index] = data.cajasFacturadas;
								break;
							case CAJAS_UNITARIAS_ACUMULADAS:
								anioActual[index] = data.cajasUnitarias;
								break;
							case IMPORTE_FACTURADO:
								anioActual[index] = data.importeFacturado;
								break;
							case IMPORTE_UTILIDAD:
								anioActual[index] = data.utilidad;
								break;
						}
						
						index++;
					}
					
				}	
				
				
				Intent intent = new Intent(this,ProfitSemanalGrafico.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra(ProfitSemanalGrafico.CODIGO_SUPERVISOR_KEY, codigoSupervisor);
				intent.putExtra(ProfitSemanalGrafico.CODIGO_DEPOSITO_KEY, codigoCda);
				intent.putExtra(ProfitSemanalGrafico.NOMBRE_CDA_KEY, nombre_cda);
				intent.putExtra(ProfitSemanalGrafico.CODIGO_CLIENTE_KEY, codigoCliente);
				intent.putExtra(ProfitSemanalGrafico.TITULOS_KEY, tituloSemana);
				intent.putExtra(ProfitSemanalGrafico.VALORES_KEY, anioActual);
				intent.putExtra(ProfitSemanalGrafico.CAMPO_KEY, campo);
				
				startActivity(intent);
				
	 		}
			
			
	 	}
	 
		@Override
		protected void processOk() {
			// TODO Auto-generated method stub
			boolean isExito = profitHistoryProxy.isExito();
			if (isExito) {
				int status = profitHistoryProxy.getResponse().getStatus();
			    if (status == 0) {
					if(profitHistoryProxy.getResponse().detalle.size()>0){
						executeWS=false;
						mostrarData();
						super.processOk();
					}else{
						executeWS=true;
						super.processOk();
						MessageBox.showSimpleDialog(this, "Informaci—n", "No existe informaci—n", "OK", null);
					}
					
							
			    
			}else{
				executeWS=true;
				super.processError();
			}
				
			
		}
	}
	 
		
		@Override
		protected void processError() {
			String message;
			if(profitHistoryProxy.getResponse()!=null){
				String error = profitHistoryProxy.getResponse().getDescripcion();
				message = error;
			}else{
				message = error_generico_process;
			}
			super.processError();
			showToast(message);
		}
	 
	public void btnHistoryAceptar_onclick(View v){
		if(executeWS){
			processAsync();
		}else{
			mostrarData();
		}
		
		
		
	}
	
}
