package pe.lindley.mmil.titanium;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.steema.teechart.TChart;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.StringAlignment;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.Bar3D;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.CustomBar.MarksLocation;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ActivityBase;

public class ProfitComparativoMensualGrafico extends ActivityBase {

	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_DEPOSITO_KEY = "CODIGO_DEPOSITO";
	public static final String CODIGO_SUPERVISOR_KEY = "CODIGO_SUPERVISOR";
	public static final String CODIGO_CLIENTE_KEY = "CODIGO_CLIENTE";
	public static final String TITULOS_KEY="TITULOS";
	public static final String VALORES_KEY="VALORES";
	public static final String VALORES_ANTERIOR_KEY="VALORES_ANTERIOR";
	public static final String CAMPO_KEY="CAMPO_KEY";
	public static final String ANIO_KEY = "ANIO_KEY";
	
	
	@InjectExtra(CODIGO_SUPERVISOR_KEY) String codigoSupervisor;
	@InjectExtra(CODIGO_DEPOSITO_KEY) String codigoCda;
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	@InjectExtra(CODIGO_CLIENTE_KEY) String codigoCliente;
	@InjectExtra(CAMPO_KEY) String campo;
	@InjectExtra(TITULOS_KEY) String[] titulo;
	@InjectExtra(VALORES_KEY) double[] valores;
	@InjectExtra(VALORES_ANTERIOR_KEY) double[] valores_anterior;
	@InjectExtra(ANIO_KEY) int anio;
	
	
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@InjectView(R.id.linearLayoutTchart) LinearLayout linearLayout;
	
	private TChart chart;
	
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        inicializarRecursos();
	        
	        setContentView(R.layout.tablerro_grafico);
	        
	        mActionBar.setHomeLogo(R.drawable.header_logo);
	        mActionBar.setTitle(R.string.history_mensual_title);
	        mActionBar.setSubTitle(codigoCliente + " / " + campo);
	        
	        chart = new TChart(this);
	        linearLayout.addView(chart);
	        
	        
	        Bar3D bar = new Bar3D(chart.getChart());
			bar.setMarksLocation(MarksLocation.Center);
			bar.setMarksOnBar(true);
			bar.setMarksLocation(MarksLocation.End);
			bar.getMarks().setStyle(MarksStyle.VALUE);
			bar.getMarks().setTransparent(true);
			bar.getMarks().getFont().setColor(Color.white);
			bar.getMarks().getFont().setSize(13);
			bar.setColor(Color.fromCode("#FF0000"));
			bar.setTitle(String.valueOf(anio));
			
			
			Bar3D bar2 = new Bar3D(chart.getChart());
			bar2.setMarksLocation(MarksLocation.Center);
			bar2.setMarksOnBar(true);
			bar2.setMarksLocation(MarksLocation.Center);
			bar2.getMarks().setStyle(MarksStyle.VALUE);
			bar2.getMarks().setTransparent(true);
			bar2.getMarks().getFont().setColor(Color.white);
			bar2.getMarks().getFont().setSize(13);
			bar2.setColor(Color.fromCode("#6d1e7e"));
			bar.setTitle(String.valueOf(anio-1));
			
			for (int i = 0; i < titulo.length; i++) {
				bar.add(valores[i],titulo[i]);
				bar2.add(valores_anterior[i]);
			}
			
			
			chart.getAspect().setView3D(true);
		      
		      chart.getPanel().setColor(Color.WHITE);
		      chart.getWalls().setVisible(false);
		      chart.getAxes().getLeft().getLabels().getFont().setColor(Color.BLACK);
		      chart.getAxes().getBottom().getLabels().getFont().setColor(Color.BLACK);
		      chart.getHeader().getFont().setColor(Color.BLACK);
		      chart.getHeader().getFont().setSize(15);
		      chart.getHeader().setAlignment(StringAlignment.NEAR);
		      chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
		      chart.getLegend().getFont().setColor(Color.BLACK);
		      chart.getLegend().setTransparent(true);
		      chart.getAxes().getLeft().setIncrement(10);
		      chart.getAxes().getBottom().getGrid().setVisible(true);
		      
		      
			chart.getLegend().setVisible(true);
			chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
			//chart.getHeader().setText(this.getResources().getString(R.string.tablero_activity_title));
			chart.getHeader().setVisible(false);
			chart.getHeader().getFont().setSize(20);	
			
	 }
}
