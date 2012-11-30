package lindley.desarrolloxcliente.activity;

import java.util.List;

import net.msonic.lib.sherlock.ActivityBase;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.DescargaBLL;
import lindley.desarrolloxcliente.negocio.EvaluacionBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.download.ProfitTO;
import lindley.desarrolloxcliente.ws.service.DescargarProfitClienteProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.google.inject.Inject;
import com.steema.teechart.TChart;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.StringAlignment;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.Bar3D;
import com.steema.teechart.styles.CustomBar.MarksLocation;
import com.steema.teechart.styles.StringList;

public class VerProfit_Activity extends ActivityBase {

	private TChart chart;
	
	List<ProfitTO> detalle;

	
	public static final String ANIO = "pfanio";
	public static final String MES = "pfmes";
	public static final String CLIENTE = "pfcliente";
	public static final String ARTICULO = "pcarticulo";
	public static final String NOMBRE_ARTICULO = "nomarticulo";
	
	@InjectExtra(ANIO) String anio;
	@InjectExtra(MES) String mes;
	@InjectExtra(CLIENTE) String codigoCliente;
	@InjectExtra(ARTICULO) String codigoArticulo;
	@InjectExtra(NOMBRE_ARTICULO) String nombre_articulo;
	
	@Inject EvaluacionBLL evaluacionBLL;
	@Inject DescargaBLL descargaBLL;
	@Inject PeriodoTO 	periodoTO;
	@Inject DescargarProfitClienteProxy descargarProfitClienteProxy;
	
	private ClienteTO cliente;
	private MyApplication application;

	@InjectResource(R.string.cajas_fisicas) String cajasFisicas;
	@InjectResource(R.string.margen_actual) String margenActual;
	@InjectResource(R.string.cajas_faltantes) String cajasFaltantes;
	@InjectResource(R.string.margen_faltante) String margenFaltante;

	
	@InjectView(R.id.linearLayoutTchart) LinearLayout linearLayout;
	
	boolean unIntento=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inicializarRecursos();
		super.onCreate(savedInstanceState);
		this.validarConexionInternet=false;
		this.mostrarWaitMessage=false;
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.profitchartview_activity);
		
		
		application = (MyApplication) getApplicationContext();
		cliente = application.getClienteTO();
		
		setTitle(String.format("%s - %s", cliente.codigo ,cliente.nombre));
		setSubTitle("Profit Story x SKU - " + nombre_articulo);
		chart = new TChart(this);
		linearLayout.addView(chart);
		chart.getAxes().getLeft().getLabels().setValueFormat("#,##0.00;(#,##0.00)");
		processAsync();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
	
	MenuItem menuEnviar;
	
	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		
		
		
		menuEnviar = menu.add("Refresh");
		menuEnviar
            .setIcon(R.drawable.reload)
            .setVisible(false)
            .setOnMenuItemClickListener(new OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(final com.actionbarsherlock.view.MenuItem item) {
					// TODO Auto-generated method stub
					setSupportProgressBarIndeterminateVisibility(true);
					unIntento=false;
					item.setVisible(false);
					processAsync();
					return true;
				}
			})
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		return true;
	
	}
	


	@Override
	protected boolean executeAsyncPre() {
		// TODO Auto-generated method stub
		return super.executeAsyncPre();
	}

	@Override
	protected void process()  throws Exception{
		// TODO Auto-generated method stub}
		periodoTO.cliente=codigoCliente;
		detalle = evaluacionBLL.consultarProfit(anio, mes, codigoCliente, codigoArticulo);
		
		if(!unIntento){
			unIntento=true;
			if(detalle.size()<=0){
				descargarProfitClienteProxy.execute();
				descargaBLL.saveProfitCliente(codigoCliente, descargarProfitClienteProxy.getResponse().profit);
				detalle = evaluacionBLL.consultarProfit(anio, mes, codigoCliente, codigoArticulo);
			}
		}
		
	}
	


	
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		menuEnviar.setVisible(true);
		setSupportProgressBarIndeterminateVisibility(false);
		selectSerie();
		super.processOk();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		menuEnviar.setVisible(true);
		setSupportProgressBarIndeterminateVisibility(false);
		super.processError();
		showToast("Error descargando profit, por favor reintente.");
	}





	public void selectSerie() {

		chart.removeAllSeries();
		
		StringList sLabel = new StringList(4);
		sLabel.add(0, "Proyecto CFS");
		sLabel.add(1, "Ideal");
		sLabel.add(2, "A–o Pasado");
		sLabel.add(3, "Winner");
		
		Bar3D bar = new Bar3D(chart.getChart());
		bar.setMarksLocation(MarksLocation.Center);
		bar.getMarks().getFont().setColor(Color.BLACK);
		bar.getMarks().getFont().setSize(20);
		bar.setColor(Color.fromCode("#FF0000"));
		bar.setValueFormat("###,###");
		bar.setTitle(cajasFisicas);
		//bar.setLabels(sLabel);
	       
	    Bar3D bar2 = new Bar3D(chart.getChart());
		bar2.getMarks().getFont().setColor(Color.BLACK);
		bar2.getMarks().getFont().setSize(20);
		bar2.setColor(Color.fromCode("#6d1e7e"));
		bar2.setValueFormat("###,###");
		bar2.setTitle(margenActual);
		//bar2.setLabels(sLabel);
		
		Bar3D bar3 = new Bar3D(chart.getChart());
		bar3.getMarks().getFont().setColor(Color.BLACK);
		bar3.getMarks().getFont().setSize(20);
		bar3.setColor(Color.fromCode("#66CD00"));
		bar3.setValueFormat("###,###");
		bar3.setTitle(cajasFaltantes);
		//bar3.setLabels(sLabel);
		
		Bar3D bar4 = new Bar3D(chart.getChart());
		//bar4.setMultiBar(MultiBars.STACKED);
		//bar4.setMarksLocation(MarksLocation.Start);
		//bar4.setMarksOnBar(true);
		//bar4.setMarksLocation(MarksLocation.Center);
		//bar4.getMarks().setStyle(MarksStyle.VALUE);
		//bar4.getMarks().setTransparent(true);
		bar4.getMarks().getFont().setColor(Color.BLACK);
		bar4.getMarks().getFont().setSize(20);
		bar4.setValueFormat("###,###");
		bar4.setColor(Color.fromCode("#00688B"));
		bar4.setTitle(margenFaltante);
		//bar4.setLabels(sLabel);
		
		
		
		Bar3D bar5 = new Bar3D(chart.getChart());
		bar5.setShowInLegend(false);
		bar5.setLabels(sLabel);
		
		bar5.add();
		bar5.add();
		bar5.add();
		bar5.add();


		chart.addSeries(bar);
		

		for (ProfitTO profitTO : detalle) {
				bar.add(profitTO.cajasFisica);
				bar2.add(profitTO.margenActual);
				bar3.add(profitTO.cajasFisicasFaltante);
				bar4.add(profitTO.margenFaltante);
		}
		


	      chart.getAspect().setView3D(true);
	      
	      chart.getPanel().setColor(Color.WHITE);
	      chart.getWalls().setVisible(false);
	      chart.getAxes().getLeft().getLabels().getFont().setColor(Color.BLACK);
	      chart.getAxes().getBottom().getLabels().getFont().setColor(Color.BLACK);
	      
	      
	      chart.getHeader().getFont().setColor(Color.BLACK);
	      chart.getHeader().getFont().setSize(20);
	      chart.getHeader().setAlignment(StringAlignment.NEAR);
	      chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
	      chart.getLegend().getFont().setColor(Color.BLACK);
	      chart.getLegend().setTransparent(true);
	      chart.getAxes().getLeft().setIncrement(20);
	      chart.getAxes().getBottom().getGrid().setVisible(true);
	      
		chart.getLegend().setVisible(true);
		chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
		chart.getLegend().getFont().setSize(20);
		chart.getHeader().setVisible(false);
		
		
		chart.getFooter().getFont().setSize(20);
		chart.getAxes().getLeft().setIncrement(5);
		chart.getAxes().getLeft().getLabels().getFont().setSize(20);
		chart.getAxes().getBottom().getLabels().getFont().setSize(20);
		
		//chart.getHeader().setText("Profit Story x SKU - " + nombre_articulo);
		
		/*Series bar = new Bar(chart.getChart());
		bar.setTitle(variables.get(0).toString());
		bar.setShowInLegend(true);
		bar.add(varCajaFisica);
		bar.getMarks().getFont().setSize(20);
		bar.setValueFormat("###,###");
		chart.addSeries(bar);

		bar = new Bar(chart.getChart());
		bar.setTitle(variables.get(1).toString());
		bar.getMarks().getFont().setSize(20);
		bar.setValueFormat("###,###");
		bar.setShowInLegend(true);
		bar.add(varMargenActual);
		
		chart.addSeries(bar);
		
		bar = new Bar(chart.getChart());
		bar.setTitle(variables.get(2).toString());
		bar.getMarks().getFont().setSize(20);
		bar.setValueFormat("###,###");
		bar.setShowInLegend(true);
		bar.add(varCajaFaltante);
		chart.addSeries(bar);

		bar = new Bar(chart.getChart());
		bar.setTitle(variables.get(3).toString());
		bar.getMarks().getFont().setSize(20);
		bar.setValueFormat("###,###");
		bar.setShowInLegend(true);
		bar.add(varMargenFaltante);
		chart.addSeries(bar);

		
		bar = new Bar(chart.getChart());
		bar.setTitle("");
		bar.setShowInLegend(false);
		bar.setLabels(sLabel);
		
		bar.add();
		bar.add();
		bar.add();
		bar.add();

		chart.addSeries(bar);
		
		chart.getLegend().setVisible(true);
		chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
		chart.getLegend().getFont().setSize(15);
		chart.getAxes().getLeft().setIncrement(10);
		
		chart.getHeader().setText("Profit Story x SKU - " + nombre_articulo);
		chart.getHeader().getFont().setSize(20);
		
		//chart.getFooter().getFont().setSize(20);*/
	}

}