package pe.lindley.mmil.activity;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import com.google.inject.Inject;
import pe.lindley.mmil.to.DashBoardDetalleTO;
import pe.lindley.mmil.ws.service.MostrarDashboardCdaProxy;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import pe.lindley.util.ActivityGroupBase;

public class MostrarDashBoardActivity extends ActivityGroupBase {
	
	public static final String EFICIENCIA_GLOBAL = "EFICIENCIA_GLOBAL";
	public static final String EFICIENCIA_PREVENTA = "EFECTIVIDAD_PREVENTA";
	public static final String PLAN_VISITA = "CUMPLIMIENTO_VISITAS";
	public static final String VOLUMEN = "VOLUMEN";
	
	public static final String EFICIENCIA_GLOBAL_SUPERVISOR = "EfiGlob";
	public static final String EFICIENCIA_PREVENTA_SUPERVISOR = "EfiPrev";
	public static final String PLAN_VISITA_SUPERVISOR = "PlanVis";
	public static final String VOLUMEN_SUPERVISOR = "Volumen";
	
	public static final String NOMBRE_INDICADOR = "indicador";
	public static final String TIPO_DASHBOARD = "dashboard";
	public static final String CODIGO_CDA = "codigo";
	public static final String CODIGO_SUPERVISOR = "cod_superv";
	
	@Inject MostrarDashboardCdaProxy mostrarDashboardCdaProxy; 
	
	private int tipo = 0;
	private String codigo_cda = null;
	private String indicador = null;
	private int codigo_supervisor = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		tipo = intent.getIntExtra(TIPO_DASHBOARD, 0);
		codigo_cda = intent.getStringExtra(CODIGO_CDA);
		indicador = intent.getStringExtra(NOMBRE_INDICADOR);
		codigo_supervisor = intent.getIntExtra(CODIGO_SUPERVISOR,0);
		processAsync();
	}

	@Override
	protected void process() {
		
		
		mostrarDashboardCdaProxy.setCodigo(codigo_cda);
		mostrarDashboardCdaProxy.setGrafico(indicador);
		mostrarDashboardCdaProxy.setTipo(tipo);
		mostrarDashboardCdaProxy.setSupervisor(codigo_supervisor);
		mostrarDashboardCdaProxy.execute();
		
	}
	
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = mostrarDashboardCdaProxy.isExito();
		if (isExito) {
			int status = mostrarDashboardCdaProxy.getResponse().getStatus();
			if (status == 0) {	
				int colorBelowLine = Color.BLACK;
				String titulo = mostrarDashboardCdaProxy.getResponse().getDashBoard().getAvance() + "/"+
						mostrarDashboardCdaProxy.getResponse().getDashBoard().getCuota() + " - " +
						mostrarDashboardCdaProxy.getResponse().getDashBoard().getTipo();
				if(mostrarDashboardCdaProxy.getResponse().getDashBoard().getTipo().equals(EFICIENCIA_GLOBAL) || mostrarDashboardCdaProxy.getResponse().getDashBoard().getTipo().equals(EFICIENCIA_GLOBAL_SUPERVISOR))			
					colorBelowLine = Color.RED;				
				else if(mostrarDashboardCdaProxy.getResponse().getDashBoard().getTipo().equals(EFICIENCIA_PREVENTA) || mostrarDashboardCdaProxy.getResponse().getDashBoard().getTipo().equals(EFICIENCIA_PREVENTA_SUPERVISOR))
					colorBelowLine = Color.DKGRAY;				
				else if(mostrarDashboardCdaProxy.getResponse().getDashBoard().getTipo().equals(PLAN_VISITA) || mostrarDashboardCdaProxy.getResponse().getDashBoard().getTipo().equals(PLAN_VISITA_SUPERVISOR))
					colorBelowLine = Color.parseColor("#48CC2D");//Color.GREEN;
				else if(mostrarDashboardCdaProxy.getResponse().getDashBoard().getTipo().equals(VOLUMEN) || mostrarDashboardCdaProxy.getResponse().getDashBoard().getTipo().equals(VOLUMEN_SUPERVISOR))
					colorBelowLine = Color.BLUE;				  
				Intent intent = ChartFactory.getLineChartIntent(this, getDemoDataset(mostrarDashboardCdaProxy.getResponse().getDashBoard().getDetalle()), getDemoRenderer(colorBelowLine,titulo));
				 View view = getLocalActivityManager().startActivity("ReferenceName", intent).getDecorView();
				 this.setContentView(view);
				
			} else {
				showToast(mostrarDashboardCdaProxy.getResponse()
						.getDescripcion());
			}
		} else {
			processError();
		}
		super.processOk();
	}
	
	
	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
	
	private XYMultipleSeriesRenderer getDemoRenderer(int colorBelowLine, String titulo) {
	    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	    renderer.setChartTitle(titulo);
	    renderer.setAxisTitleTextSize(16);
	    renderer.setChartTitleTextSize(20);
	    renderer.setLabelsTextSize(15);
	    renderer.setLegendTextSize(15);
	    renderer.setPointSize(5f);
	    renderer.setMargins(new int[] {25,15,0,5});
	    XYSeriesRenderer r = new XYSeriesRenderer();
	    r.setColor(colorBelowLine);	    
	    //r.setPointStyle(PointStyle.SQUARE);	    
	    r.setFillBelowLine(true);
	    r.setFillBelowLineColor(colorBelowLine);
	    r.setFillPoints(true);
	    renderer.addSeriesRenderer(r);
	    r = new XYSeriesRenderer();	    
	    r.setColor(Color.BLACK);
	    renderer.addSeriesRenderer(r);	    
	    renderer.setAxesColor(Color.parseColor("#6d1e7e"));
	    renderer.setLabelsColor(Color.GREEN);	    
	    renderer.setShowLegend(false);
	    renderer.setXLabels(13);

	    renderer.setXAxisMin(7);
	    renderer.setXAxisMax(19);
	    renderer.setYLabels(5);
	    return renderer;
	  }
	
	private XYMultipleSeriesDataset getDemoDataset(ArrayList<DashBoardDetalleTO> detalleDashboard) {
	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	    XYSeries series = new XYSeries("");	    
	    for(DashBoardDetalleTO data : detalleDashboard)
	    {
	    	series.add(Double.parseDouble(data.getHora()),data.getAvance());
	    }		
	    dataset.addSeries(series);	    
	    XYSeries seriesLine = new XYSeries("");
	    seriesLine.add(7, 0);
	    seriesLine.add(19, 100);
	    dataset.addSeries(seriesLine);
	    return dataset;
	  }
	
}
