package pe.lindley.mmil.titanium;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

import com.steema.teechart.TChart;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.StringAlignment;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.Bar3D;
import com.steema.teechart.styles.CustomBar.MarksLocation;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.MultiBars;
import com.thira.examples.actionbar.widget.ActionBar;

import android.os.Bundle;
import android.widget.LinearLayout;
import net.msonic.lib.ActivityBase;

public class TableroGraficoActivity extends ActivityBase {
	
	
	public static final String NOMBRE_CDA_KEY="NOMBRE_CDA";
	public static final String CODIGO_CDA_KEY="CODIGO_CDA";
	public static final String TITULOS_KEY="TITULOS";
	public static final String ACTUAL_KEY="ACTUAL";
	public static final String ESPERADO_KEY="ESPERADO";
	
	@InjectExtra(NOMBRE_CDA_KEY) String nombre_cda;
	@InjectExtra(CODIGO_CDA_KEY) String codigo_cda;
	@InjectExtra(TITULOS_KEY) String[] titulo;
	@InjectExtra(ACTUAL_KEY) String[] actual;
	@InjectExtra(ESPERADO_KEY) String[] esperado;
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@InjectView(R.id.linearLayoutTchart) LinearLayout linearLayout;
	
	private TChart chart;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarRecursos();
        
        setContentView(R.layout.tablerro_grafico);
        
        mActionBar.setHomeLogo(R.drawable.header_logo);
        mActionBar.setTitle(R.string.tablero_activity_title);
        mActionBar.setSubTitle(nombre_cda);
        
        chart = new TChart(this);
        linearLayout.addView(chart);
		
		
		
		Bar3D bar = new Bar3D(chart.getChart());
		bar.setMultiBar(MultiBars.STACKED);
		bar.setMarksLocation(MarksLocation.Center);
		  bar.setMarksOnBar(true);
		     bar.setMarksLocation(MarksLocation.End);
		     bar.getMarks().setStyle(MarksStyle.VALUE);
		     bar.getMarks().setTransparent(true);
		     bar.getMarks().getFont().setColor(Color.white);
		     bar.getMarks().getFont().setSize(13);
		     bar.setColor(Color.fromCode("#FF0000"));
		 bar.setTitle("Esperado");
	         
	    Bar3D bar2 = new Bar3D(chart.getChart());
		bar2.setMultiBar(MultiBars.STACKED);
		bar2.setMarksLocation(MarksLocation.Start);
		bar2.setMarksOnBar(true);
		bar2.setMarksLocation(MarksLocation.Center);
		bar2.getMarks().setStyle(MarksStyle.VALUE);
		bar2.getMarks().setTransparent(true);
		bar2.getMarks().getFont().setColor(Color.white);
		bar2.getMarks().getFont().setSize(13);
		bar2.setColor(Color.fromCode("#6d1e7e"));
		bar2.setTitle("Actual");
		
		
		
		for (int i = 0; i < titulo.length; i++) {
			bar.add(Double.parseDouble(esperado[i]),titulo[i]);
			bar2.add(Double.parseDouble(actual[i]));
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
