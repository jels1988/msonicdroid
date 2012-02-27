package pe.lindley.red.activity;

import java.text.DecimalFormat;
import java.util.List;


import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.inject.Inject;

import pe.lindley.activity.R;
import pe.lindley.red.to.SoviEmpresaTO;
import pe.lindley.red.ws.service.ConsultarSoviEmpresaProxy;
import pe.lindley.util.ActivityGroupBase;
import roboguice.inject.InjectResource;

public class MixSoviEmpresaActivity extends ActivityGroupBase {

	public static boolean llamarWS=false;
	public static final String CLIENTE_DESCRIPCION_KEY="descripcion_cliente";
	public static final String PERIODO_KEY="periodo";
	public static final String CLIENTE_CODIGO_KEY="codigo_cliente";
	
	@Inject ConsultarSoviEmpresaProxy consultarSoviEmpresaProxy;
	
	@InjectResource(R.string.custom_barras_lila) String color_Lila;
	@InjectResource(R.string.custom_barras_verde) String color_Verde;
	@InjectResource(R.string.custom_barras_azul_marino) String color_azul_marino;
	@InjectResource(R.string.custom_barras_mostaza) String color_mostaza;
	@InjectResource(R.string.custom_barras_naranja) String color_naranja;
	
	@InjectResource(R.string.custom_barras_plomo) String color_plomo;
	@InjectResource(R.string.custom_barras_verde_agua) String color_verde_agua;
	
	@InjectResource(R.string.red_ejecucion_anio) String red_ejecucion_anio;
	@InjectResource(R.string.red_core_brands_title) String red_core_brands_title;
	
	
	
	private String cliente;
	private String periodo;
	private String codigoCliente;
	private String titulo_grafico;

	@Override
	protected void process() {
		
		consultarSoviEmpresaProxy.setCodigoCliente(codigoCliente);
		consultarSoviEmpresaProxy.setPeriodo(this.periodo);
		consultarSoviEmpresaProxy.execute();
	}
	
	@Override
	protected void processOk() {
		super.processOk();
		execute(getApplicationContext());
		llamarWS=true;
	}
	
	@Override
	protected void processError() {
		super.processOk();
		llamarWS=false;
	}
	
	 @Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			
			Intent intent = getIntent();
			codigoCliente = intent.getStringExtra(CLIENTE_CODIGO_KEY);
			cliente = intent.getStringExtra(CLIENTE_DESCRIPCION_KEY);
			periodo = intent.getStringExtra(PERIODO_KEY);
			periodo = periodo.replace("/","").substring(0,6);
			
			if(!llamarWS){
				processAsync();
			}else{
				execute(getApplicationContext());
			}
		}
	 
	 /*
	 protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
		      String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
		      int labelsColor) {
		  
		  
		    renderer.setChartTitle(title);
		    renderer.setXTitle(xTitle);
		    renderer.setYTitle(yTitle);
		    renderer.setXAxisMin(xMin);
		    renderer.setXAxisMax(xMax);
		    renderer.setYAxisMin(yMin);
		    renderer.setYAxisMax(yMax);
		    
		    renderer.setYLabelsAlign(Align.CENTER);
			renderer.setShowGrid(true);
			renderer.setShowLegend(true);
			renderer.setAxesColor(axesColor);
			renderer.setLabelsColor(labelsColor);
		    

		  }*/
	 
	 
	public void execute(Context context) {
		if(!consultarSoviEmpresaProxy.isExito()){
			if(consultarSoviEmpresaProxy.getResponse().getStatus()!=0){
				showToast("Error consultando informaci—n, por favor vuelva a intentar. ");
				return;
			}
			
		}
		
		if(consultarSoviEmpresaProxy.getResponse().getData()==null){
			finish();
		}
		
		
		List<SoviEmpresaTO> data = consultarSoviEmpresaProxy.getResponse().getData();
		
		int index=0;
		int indexColor=0;
		 
		int[] colorsBase = new int[] { 
				Color.BLUE, Color.GREEN, Color.MAGENTA, Color.LTGRAY, Color.CYAN,Color.RED,Color.DKGRAY,Color.LTGRAY,
				Color.parseColor(color_plomo),
				Color.parseColor(color_verde_agua),
				Color.parseColor(color_Lila), 
				Color.parseColor(color_Verde),	
				};

		
		
		int size = data.size();
		double[] porcentaje = new double[size];
		double[] carasVisibles = new double[size];
		String[] compania = new String[size];
		int[] colors = new int[size];
		
		
		for (SoviEmpresaTO soviEmpresaTO : data) {
			porcentaje[index]=soviEmpresaTO.getPorcentaje();
			carasVisibles[index]=soviEmpresaTO.getCarasVisibles();
			
			DecimalFormat formatter = new DecimalFormat("## %");
			
			compania[index]=soviEmpresaTO.getCompania() + '-' + soviEmpresaTO.getCarasVisibles() + '-' + formatter.format(soviEmpresaTO.getPorcentaje());
			
			if(indexColor==colorsBase.length) indexColor=0;
			colors[index]=colorsBase[indexColor++];
			
			index++;
		}

	   
	    
	    titulo_grafico = String.format(red_core_brands_title, cliente + "-Periodo:" + periodo);
	
	    DefaultRenderer renderer = buildCategoryRenderer(colors);
	    
	    
	    renderer.setLabelsTextSize(30);
	
	    
	    Intent i = (ChartFactory.getPieChartIntent(context, buildCategoryDataset("rrr",porcentaje,compania), renderer,"Titulo"));
	    
	    
	   View view = getLocalActivityManager().startActivity("ReferenceName", i).getDecorView();
	   this.setContentView(view);

	  }
	
	
	  protected DefaultRenderer buildCategoryRenderer(int[] colors) {
		    DefaultRenderer renderer = new DefaultRenderer();
		    
		    renderer.setLabelsTextSize(30);
		  
		    renderer.setChartTitle(titulo_grafico);
		    renderer.setChartTitleTextSize(20);
		    
		    
		    renderer.setMargins(new int[] {25,15,0,5});
		    renderer.setExternalZoomEnabled(true);
		    renderer.setShowLabels(true);
		    renderer.setFitLegend(true);
		    renderer.setShowAxes(true);
		    renderer.setShowGrid(true);
		    renderer.setZoomEnabled(true);
		    renderer.setZoomRate(1.1f);
		    
		    renderer.setLegendHeight(50);
		    renderer.setShowLabels(true);
		    renderer.setShowCustomTextGrid(true);
		    

			renderer.setAxesColor(Color.BLACK);
			renderer.setLabelsColor(Color.BLACK);
			renderer.setLegendTextSize(15);
			renderer.setLabelsColor(Color.BLACK);
		    
		    renderer.setMargins(new int[] { 10, 10, 10, 10 });
		    for (int color : colors) {
		      SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		     r.setDisplayChartValues(true);
		     
		      r.setColor(color);
		      renderer.addSeriesRenderer(r);
		    }
		    return renderer;
		  }
	  
	  protected CategorySeries buildCategoryDataset(String title, double[] values,String[] titulos) {
		    CategorySeries series = new CategorySeries("RPUASFAS");
		    int k = 0;
		    for (double value : values) {
		      series.add(titulos[k++], value);
		    }

		    return series;
		  }
	 
}
