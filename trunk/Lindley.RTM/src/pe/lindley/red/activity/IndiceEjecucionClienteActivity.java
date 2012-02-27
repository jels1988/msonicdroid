package pe.lindley.red.activity;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import com.google.inject.Inject;

import pe.lindley.activity.R;
import pe.lindley.red.to.IndiceEjecucionAnioTO;
import pe.lindley.red.to.IndiceEjecucionClienteTO;
import pe.lindley.red.to.IndiceEjecucionMatrizTO;
import pe.lindley.red.ws.service.ConsultarIndiceEjecucionAnioProxy;
import pe.lindley.red.ws.service.ConsultarIndiceEjecucionClienteProxy;
import pe.lindley.red.ws.service.ConsultarIndiceEjecucionMatrizProxy;
import pe.lindley.util.ActivityGroupBase;
import pe.lindley.util.ArrayUtil;
import roboguice.inject.InjectResource;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.View;

public class IndiceEjecucionClienteActivity extends ActivityGroupBase {
	
	public static boolean llamarWS=false;
	public static final String CLIENTE_DESCRIPCION_KEY="descripcion_cliente";
	public static final String CLIENTE_CODIGO_KEY="codigo_cliente";
	public static final String PERIODO_KEY="periodo";
	
	
	@Inject ConsultarIndiceEjecucionClienteProxy consultarIndiceEjecucionClienteProxy;
	@InjectResource(R.string.custom_barras_lila) String color_Lila;
	@InjectResource(R.string.custom_barras_verde) String color_Verde;
	@InjectResource(R.string.custom_barras_azul_marino) String color_azul_marino;
	@InjectResource(R.string.custom_barras_mostaza) String color_mostaza;
	@InjectResource(R.string.custom_barras_naranja) String color_naranja;
	@InjectResource(R.string.red_ejecucion_anio) String red_ejecucion_anio;
	
	private String cliente;
	private String periodo;
	private String codigoCliente;


	@Override
	protected void process() {
		
		consultarIndiceEjecucionClienteProxy.setCodigoCliente(codigoCliente);
		consultarIndiceEjecucionClienteProxy.setPeriodo(this.periodo);
		consultarIndiceEjecucionClienteProxy.execute();
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
		cliente = intent.getStringExtra(CLIENTE_DESCRIPCION_KEY);
		periodo = intent.getStringExtra(PERIODO_KEY);
		codigoCliente = intent.getStringExtra(CLIENTE_CODIGO_KEY);
		
		periodo = periodo.replace("/","").substring(0,6);
		
		if(!llamarWS){
			processAsync();
		}else{
			execute(getApplicationContext());
		}
	}
	 

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
		    

		  }
	 
	protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
	    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	    
	    renderer.setShowLegend(true);
	    renderer.setLegendHeight(50);
	    renderer.setAxisTitleTextSize(20);
	    renderer.setChartTitleTextSize(20);
	    renderer.setLabelsTextSize(20);
	    renderer.setMargins(new int[] {25,15,0,5});
	    
	    int length = colors.length;
	    for (int i = 0; i < length; i++) {
	      SimpleSeriesRenderer r = new SimpleSeriesRenderer();
	      r.setColor(colors[i]);
	      renderer.addSeriesRenderer(r);
	      renderer.setChartValuesTextSize(20);
	      
	    }
	    return renderer;
	  }
	
	
	protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	   
	    int length = titles.length;
	    for (int i = 0; i < length; i++) {
	      CategorySeries series = new CategorySeries(titles[i]);
	      double[] v = values.get(i);
	      int seriesLength = v.length;
	      for (int k = 0; k < seriesLength; k++) {
	        series.add(v[k]);
	      }
	      dataset.addSeries(series.toXYSeries());
	    }
	    return dataset;
	  }
	
	public void execute(Context context) {
		if(!consultarIndiceEjecucionClienteProxy.isExito()){
			if(consultarIndiceEjecucionClienteProxy.getResponse().getStatus()!=0){
				showToast("Error consultando informaci—n, por favor vuelva a intentar. ");
				return;
			}
			
		}
		
		if(consultarIndiceEjecucionClienteProxy.getResponse().getData()==null){
			finish();
		}
		
		String[] titles = new String[] { "EJE", "INV","POS","PRE" };
		
		List<IndiceEjecucionClienteTO> data = consultarIndiceEjecucionClienteProxy.getResponse().getData();
		
		int index=0;
		List<double[]> values = new ArrayList<double[]>();
		
		int size = data.size();
		double[] eje = new double[size];
		double[] inv = new double[size];
		double[] pos = new double[size];
		double[] pre = new double[size];
		String[] meses = new String[size];
		
		for (IndiceEjecucionClienteTO indiceEjecucionClienteTO : data) {
			eje[index]=ArrayUtil.round(indiceEjecucionClienteTO.getEjecucion(), 2);
			inv[index]=ArrayUtil.round(indiceEjecucionClienteTO.getInventario(), 2);
			pos[index]=ArrayUtil.round(indiceEjecucionClienteTO.getPosicion(), 2);
			pre[index]=ArrayUtil.round(indiceEjecucionClienteTO.getPresentacion(), 2);
			meses[index]=String.valueOf(indiceEjecucionClienteTO.getMes());
			index++;
		}
		
	    values.add(eje);
	    values.add(inv);
	    values.add(pos);
	    values.add(pre);
	    
	    double[] maximos = new double[]{ArrayUtil.getMaxValue(eje),
	    								ArrayUtil.getMaxValue(inv),
	    								ArrayUtil.getMaxValue(pos),
	    								ArrayUtil.getMaxValue(pre)};
	    
	    double max = ArrayUtil.getMaxValue(maximos);
	    
	    int[] colors = new int[] { Color.parseColor(color_Lila), 
	    							Color.parseColor(color_Verde),	
	    							Color.parseColor(color_azul_marino),	
	    							Color.parseColor(color_mostaza)};
	    
	    String titulo_grafico = String.format(red_ejecucion_anio, cliente);
	    
	    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
	    setChartSettings(renderer, titulo_grafico, "A–os", "", 0,2, 0,max * 1.05, 
	    			Color.parseColor(color_Lila), 
	    			Color.parseColor(color_Verde));
	    
	    renderer.setXLabels(0);
	    
	    int indexanio=1;
	    
	    for (String mes : meses) {
	    	renderer.addTextLabel(indexanio++, mes);
		}

	    
	    renderer.setYLabels(10);
	    renderer.setDisplayChartValues(true);
	    renderer.setXLabelsAlign(Align.CENTER);
	    renderer.setYLabelsAlign(Align.LEFT);
	    // renderer.setPanEnabled(false);
	    // renderer.setZoomEnabled(false);
	    renderer.setZoomRate(1.1f);
	    renderer.setBarSpacing(0.5);
	    
	    Intent i = (ChartFactory.getBarChartIntent(context, buildBarDataset(titles, values), renderer,Type.STACKED));
	    
	    
	   View view = getLocalActivityManager().startActivity("ReferenceName", i).getDecorView();
	   this.setContentView(view);

	   
	    //return (ChartFactory.getBarChartIntent(context, buildBarDataset(titles, values), renderer,Type.STACKED));

	      
	  }
	
}
