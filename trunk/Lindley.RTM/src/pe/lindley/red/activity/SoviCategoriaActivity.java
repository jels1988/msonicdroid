package pe.lindley.red.activity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.google.inject.Inject;
import com.steema.teechart.TChart;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.StringList;
import com.steema.teechart.themes.ThemesList;
import pe.lindley.activity.R;
import pe.lindley.red.to.SoviCategoriaTO;
import pe.lindley.red.to.SoviPackageTO;
import pe.lindley.red.ws.service.ConsultarSoviCategoriaProxy;
import pe.lindley.util.ActivityGroupBase;
import pe.lindley.util.ArrayUtil;
import roboguice.inject.InjectExtra;

public class SoviCategoriaActivity extends ActivityGroupBase{

	public static boolean llamarWS=false;
	private TChart chart;
	List<SoviCategoriaTO> detalle;
	public static final String CODIGO_CLIENTE_KEY = "codigo_cliente";
	public static final String CLIENTE_KEY = "cliente_descripcion";
	public static final String FECHA_ENCUESTA_KEY = "fecha_encuesta";
		
	@InjectExtra(CODIGO_CLIENTE_KEY) public static String cliente_codigo;
	@InjectExtra(CLIENTE_KEY) public static String cliente_descripcion;
	@InjectExtra(FECHA_ENCUESTA_KEY) public static String fecha_encuesta;	
	 
	//@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	
	@Inject ConsultarSoviCategoriaProxy consultarSoviCategoriaProxy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.red_chartview);
		//mActionBar.setTitle(R.string.consultar_encuesta_title);
	    //mActionBar.setHomeLogo(R.drawable.header_logo);
	    //mActionBar.setSubTitle(cliente_descripcion);
		LinearLayout group = (LinearLayout) findViewById(R.id.linearLayoutTchart);
		chart = new TChart(this);
		group.addView(chart);
		chart.getPanel().setBorderRound(7);
		chart.getAspect().setView3D(true);
		
		selectTheme(1);		
		
		if(!llamarWS){
			processAsync();
		}else{
			execute(getApplicationContext());
		}
	}	
	
	public void execute(Context context) {
		if(!consultarSoviCategoriaProxy.isExito()){
			if(consultarSoviCategoriaProxy.getResponse().getStatus()!=0){
				showToast("Error consultando información, por favor vuelva a intentar. ");
				return;
			}
			
		}
		
		if(consultarSoviCategoriaProxy.getResponse().getData()==null){
			finish();
		}
		
		llamarWS=true;
		detalle = consultarSoviCategoriaProxy.getResponse().getData();	
		selectSerie(detalle);
	  }
	 
	

		// With one of the recovered integers we can select  theme
		public void selectTheme(int themeSelection) {

			switch (themeSelection) {
			case 0: // Opera Theme Selected
				ThemesList.applyTheme(chart.getChart(), 0);
				break;
			case 1: // Black is back Theme Selected
				ThemesList.applyTheme(chart.getChart(), 1);
				break;
			case 2: // Default Theme Selected
				ThemesList.applyTheme(chart.getChart(), 2);
				break;
			case 3: // Excel Theme Selected
				ThemesList.applyTheme(chart.getChart(), 3);
				break;
			case 4: // Classic Theme Selected
				ThemesList.applyTheme(chart.getChart(), 4);
				break;
			case 5: // XP Theme Selected
				ThemesList.applyTheme(chart.getChart(), 5);
				break;
			case 6: // Web Theme Selected
				ThemesList.applyTheme(chart.getChart(), 6);
				break;
			case 7: // Business Theme Selected
				ThemesList.applyTheme(chart.getChart(), 7);
				break;
			case 8: // BlueSky Theme Selected
				ThemesList.applyTheme(chart.getChart(), 8);
				break;
			case 9: // Grayscale Theme Selected
				ThemesList.applyTheme(chart.getChart(), 9);
				break;

			}
		}

		@Override
		protected void process() {
			// TODO Auto-generated method stub}
			fecha_encuesta = fecha_encuesta.replace("/","").substring(0,6);
			consultarSoviCategoriaProxy.setAnioMes(fecha_encuesta);
			consultarSoviCategoriaProxy.setCodigoCliente(cliente_codigo);
			consultarSoviCategoriaProxy.execute();
		}

		@Override
		protected void processOk() {
			// TODO Auto-generated method stub
			boolean isExito = consultarSoviCategoriaProxy.isExito();
			
			if (isExito) {
				int status = consultarSoviCategoriaProxy.getResponse().getStatus();
				if (status == 0) {		
					llamarWS=true;
					detalle = consultarSoviCategoriaProxy.getResponse().getData();							
					selectSerie(detalle);				
				}
				else
				{
					showToast(consultarSoviCategoriaProxy.getResponse().getDescripcion());
					finish();
				}
			}
			super.processOk();
		}

		@Override
		protected void processError() {
			// TODO Auto-generated method stub
			super.processError();
			showToast(error_generico_process);
			llamarWS=false;
		}
		
		public void selectSerie(List<SoviCategoriaTO> detalle) {
						
			chart.removeAllSeries();
						
			ArrayList<String> familias = new ArrayList<String>();
			ArrayList<String> meses = new ArrayList<String>();
			for (SoviCategoriaTO soviCategoriaTO : detalle) {
				
				String nombreFamilia = soviCategoriaTO.getFamilia().toUpperCase();
				if(!familias.contains(nombreFamilia)){
					familias.add(nombreFamilia.toUpperCase());
				}
				
				String datoMes = soviCategoriaTO.getMes().toUpperCase();
				if(!meses.contains(datoMes)){
					meses.add(datoMes);
				}
			}
			
			/*int sizeMMeses = meses.size();
			for (String nombre : familias) {
				String title = "";
				Series bar = new Bar(chart.getChart());			
				double[] valores = new double[sizeMMeses];
				for(String mes : meses){
				
					for (SoviCategoriaTO soviCategoriaTO : detalle) {
						if(nombre.compareTo(soviCategoriaTO.getFamilia().toUpperCase())==0){
						if(soviCategoriaTO.getMes().toUpperCase().compareTo(mes)==0){
								title = mes;
								System.out.println(title);
								BigDecimal bd = new BigDecimal(soviCategoriaTO.getPorcentajeFamilia()*100);
							    BigDecimal rounded = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
							    valores[meses.indexOf(mes)] = rounded.doubleValue();
							}
						}
					}
					
				}
				//bar.setTitle(title);
				//bar.setShowInLegend(true);				
				bar.add(valores);				
			}*/
			int sizeFamilia = familias.size();
			StringList sLabel = new StringList(sizeFamilia);
			int pos = 0;
			
			for(String mes : meses){
							
				double[] valores = new double[sizeFamilia];
				
				for (String nombre : familias) {
					for (SoviCategoriaTO soviCategoriaTO : detalle) {
						if(soviCategoriaTO.getMes().toUpperCase().compareTo(mes)==0){
							if(nombre.compareTo(soviCategoriaTO.getFamilia().toUpperCase())==0){
								BigDecimal bd = new BigDecimal(soviCategoriaTO.getPorcentajeFamilia()*100);
							    BigDecimal rounded = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
								valores[familias.indexOf(nombre)]=rounded.doubleValue();
							}
						}
					}
					sLabel.add(pos, nombre);
					pos++;
				}
				
				Series bar = new Bar(chart.getChart());
				bar.setTitle(mes);
				bar.setShowInLegend(true);				
				bar.add(valores);		
				//bar.setLabels(sLabel);
			}
			 
			

			chart.getLegend().setVisible(true);
			chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
			
			chart.getHeader().setText("% SOVI MultiCategoria");
			chart.getHeader().getFont().setSize(20);		    
		}
			
}
