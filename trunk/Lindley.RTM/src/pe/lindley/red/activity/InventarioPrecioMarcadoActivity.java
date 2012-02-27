package pe.lindley.red.activity;

import java.util.ArrayList;
import java.util.List;

import pe.lindley.activity.R;
import pe.lindley.red.to.InventarioPrecioMarcadoTO;
import pe.lindley.red.ws.service.ConsultarInventarioPrecioMarcadoProxy;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectExtra;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.google.inject.Inject;
import com.steema.teechart.TChart;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.HorizBar;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.StringList;
import com.steema.teechart.themes.ThemesList;

public class InventarioPrecioMarcadoActivity extends ActivityBase{
	
	public static boolean llamarWS=false;
	private TChart chart;
	private Series series;
	public static final String CODIGO_CLIENTE_KEY = "codigo_cliente";
	public static final String CLIENTE_KEY = "cliente_descripcion";
	public static final String FECHA_ENCUESTA_KEY = "fecha_encuesta";
		
	@InjectExtra(CODIGO_CLIENTE_KEY) public static String cliente_codigo;
	@InjectExtra(CLIENTE_KEY) public static String cliente_descripcion;
	@InjectExtra(FECHA_ENCUESTA_KEY) public static String fecha_encuesta;	
	 
	//@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@Inject ConsultarInventarioPrecioMarcadoProxy precioMarcadoProxy;
	
	List<InventarioPrecioMarcadoTO> detalle; 
	
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
		
		ThemesList.applyTheme(chart.getChart(), 1);	
		/*if(!llamarWS){
			processAsync();
		}else{
			selectSerie(detalle);	
		}*/
		processAsync();
	}
	
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		fecha_encuesta = fecha_encuesta.replace("/","");
		precioMarcadoProxy.setAnioMes(fecha_encuesta);
		precioMarcadoProxy.setCodigoCliente(cliente_codigo);
		precioMarcadoProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = precioMarcadoProxy.isExito();
		
		if (isExito) {
			int status = precioMarcadoProxy.getResponse().getStatus();
			if (status == 0) {		
				llamarWS=true;
				detalle = precioMarcadoProxy.getResponse().getData();							
				selectSerie(detalle);				
			}
			else
			{
				showToast(precioMarcadoProxy.getResponse().getDescripcion());
				finish();
			}
		}
		super.processOk();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub		
		super.processError();
		llamarWS=false;
		showToast(error_generico_process);
	}
	
	public void selectSerie(List<InventarioPrecioMarcadoTO> detalle) {
		
		chart.removeAllSeries();
					
		ArrayList<String> resultados = new ArrayList<String>();
		ArrayList<String> companias = new ArrayList<String>();
		for (InventarioPrecioMarcadoTO precioMarcadoTO : detalle) {
			
			String compania = precioMarcadoTO.getCompania().toUpperCase();
			if(!companias.contains(compania)){
				companias.add(compania.toUpperCase());
			}
			
			String resultado = precioMarcadoTO.getResultado().toUpperCase();
			if(!resultados.contains(resultado)){
				resultados.add(resultado);
			}
		}
		
		int sizeComp = companias.size();
		StringList sLabel = new StringList(sizeComp);
		int pos = 0;
		
		for (String resultado : resultados) {				
			double[] valores = new double[sizeComp];
			
			for(String compania : companias){
				for (InventarioPrecioMarcadoTO precioMarcadoTO : detalle) {
					if(precioMarcadoTO.getResultado().toUpperCase().compareTo(resultado)==0){
						if(compania.compareTo(precioMarcadoTO.getCompania().toUpperCase())==0){
							valores[companias.indexOf(compania)]=precioMarcadoTO.getCantidad();
						}
					}
				}
				sLabel.add(pos, compania);pos++;
			}
			try {
				series = Series.createNewSeries(chart.getChart(),HorizBar.class, null);
				series.add(valores);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			series.setTitle(resultado);
			series.setShowInLegend(true);	
			//chart.addSeries(series);
			//bar.setLabels(sLabel);
			
		}		
		chart.getLegend().setVisible(true);
		chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
		
		chart.getHeader().setText("Precio Marcado x SKU");
		chart.getHeader().getFont().setSize(20);		    
	}
		

}
