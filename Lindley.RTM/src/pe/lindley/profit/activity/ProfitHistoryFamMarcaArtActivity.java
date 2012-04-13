package pe.lindley.profit.activity;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.steema.teechart.TChart;
import com.steema.teechart.languages.Language;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.StringList;
import com.steema.teechart.themes.ThemesList;
import com.thira.examples.actionbar.widget.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.profit.to.Articulo;
import pe.lindley.profit.to.HistoryNewDetalleTO;
import pe.lindley.profit.to.HistoryNewTO;
import pe.lindley.profit.to.Marca;
import pe.lindley.profit.ws.service.ProfitFamiliaMarcaArticuloProxy;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

public class ProfitHistoryFamMarcaArtActivity extends ActivityBase{

	private TChart chart;
	
	public static final int CAJAS_FISICAS_ACUMULADAS=0;
	public static final int CAJAS_UNITARIAS_ACUMULADAS=1;
	public static final int IMPORTE_FACTURADO=2;
	public static final int IMPORTE_UTILIDAD=3;

	public static final String ANIO = "anio_chart";
	public static final String CODIDO_FAMILIA = "codigo_familia";
	public static final String NOMBRE_FAMILIA = "nombre_familia";
	public static final String TIPO_CAMPO = "campo_chart";
	public static final String CODIGO_MARCA = "codigo_marca";
	public static final String NOMBRE_MARCA = "nombre_marca";
	public static final String ARTICULOS = "articulo_chart";
	public static final String CIENTE_KEY = "cliente";
	public static final String CODIGO_CLIENTE_KEY = "codigo_cliente";
	
	@InjectExtra(ANIO) 		 		int	num_anio;
	@InjectExtra(TIPO_CAMPO) 		int	campo_tipo;
	@InjectExtra(CODIGO_CLIENTE_KEY)	String	cod_cliente;
	@InjectExtra(CIENTE_KEY) 		String	nombre_cliente;
	@InjectExtra(ARTICULOS) 		String	articulo[];
	@InjectExtra(CODIDO_FAMILIA) 	String	cod_familia;
	@InjectExtra(NOMBRE_FAMILIA) 	String	nom_familia;
	@InjectExtra(CODIGO_MARCA) 		String	cod_marca;
	@InjectExtra(NOMBRE_MARCA) 		String	nom_marca;
	
	private ArrayList<HistoryNewTO> datos;
	private String titulo;
	private int anio;
	private int campo;

	@Inject ProfitFamiliaMarcaArticuloProxy profitFamiliaMarcaArticuloProxy;
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@InjectView(R.id.textTitle)  	TextView 	textTitle;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		profitFamiliaMarcaArticuloProxy.setAnio(num_anio);
		profitFamiliaMarcaArticuloProxy.setCodigoCliente(cod_cliente);
		profitFamiliaMarcaArticuloProxy.setFamilia(cod_familia);
		profitFamiliaMarcaArticuloProxy.setMarca(cod_marca);
		//cboHistoryFamiliaMarcaArticulo

		ArrayList<Articulo> listArticulo = new ArrayList<Articulo>();
		for(String codigo : articulo)
		{
			Articulo art = new Articulo();
			art.setCodigo(codigo);
			listArticulo.add(art);
		}
		/*ArrayList<Articulo> listMarca = new ArrayList<Articulo>();
		Articulo m = new Articulo();
		m.setCodigo("10511312");
		listMarca.add(m);
		
		Articulo m1 = new Articulo();
		m1.setCodigo("10512900");
		listMarca.add(m1);*/
		
		profitFamiliaMarcaArticuloProxy.setListArticulo(listArticulo);
		
		profitFamiliaMarcaArticuloProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = profitFamiliaMarcaArticuloProxy.isExito();
		
		String titulo = null;
		
		if (isExito) {
			int status = profitFamiliaMarcaArticuloProxy.getResponse().getStatus();
			if (status == 0) {		
				ArrayList<HistoryNewTO> detalle = profitFamiliaMarcaArticuloProxy.getResponse().getDatos();				

					setDatos(detalle);					 
					titulo = "FAMILIA: "+nom_familia+", MARCA: "+nom_marca;
					setTitulo(titulo);
					setAnio(num_anio);
					setCampo(campo_tipo);						
					selectSerie();
					
					
			}
			else
			{
				showToast(profitFamiliaMarcaArticuloProxy.getResponse().getDescripcion());
				finish();
			}
		}
		super.processOk();
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(profitFamiliaMarcaArticuloProxy.getResponse().getDescripcion());
		finish();
	}

	public ArrayList<HistoryNewTO> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<HistoryNewTO> datos) {
		this.datos = datos;
	}
		
	

	public int getCampo() {
		return campo;
	}

	public void setCampo(int campo) {
		this.campo = campo;
	}

	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.chartview);
		mActionBar.setTitle(nombre_cliente);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		LinearLayout group = (LinearLayout) findViewById(R.id.linearLayoutTchart);
		chart = new TChart(this);
		group.addView(chart);
		chart.getPanel().setBorderRound(7);
		chart.getAspect().setView3D(true);
		
		selectTheme(1);		
		processAsync();
		
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
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 0, Menu.NONE, Language.getString("Settings")).setIcon(
				android.R.drawable.ic_menu_manage);
		menu.add(Menu.NONE, 1, Menu.NONE, Language.getString("Theme")).setIcon(
				android.R.drawable.ic_menu_gallery);
		menu.add(Menu.NONE, 2, Menu.NONE, Language.getString("Tools")).setIcon(
				android.R.drawable.ic_menu_preferences);
		menu.add(Menu.NONE, 3, Menu.NONE, Language.getString("Save")).setIcon(
				android.R.drawable.ic_menu_save);
		menu.add(Menu.NONE, 4, Menu.NONE, Language.getString("Description"))
				.setIcon(android.R.drawable.ic_menu_info_details);
		menu.add(Menu.NONE, 5, Menu.NONE, Language.getString("Share")).setIcon(
				android.R.drawable.ic_menu_share);
		return true;
	}
	
	public void selectSerie() {
		
		chart.removeAllSeries();
		StringList sLabel = new StringList(12);
		
		sLabel.add(0, "EN");
		sLabel.add(1, "FE");
		sLabel.add(2, "MA");
		sLabel.add(3, "AB");
		sLabel.add(4, "MA");
		sLabel.add(5, "JN");
		sLabel.add(6, "JL");
		sLabel.add(7, "AG");
		sLabel.add(8, "SE");
		sLabel.add(9, "OC");
		sLabel.add(10, "NV");
		sLabel.add(11, "DC");
		
		String[] titles = new String[] { String.valueOf(this.anio) };
		
		double[] anioActual = new double[12];
		
		for (HistoryNewTO dato : datos) {
			Series bar = new Bar(chart.getChart());
			for(HistoryNewDetalleTO detalle : dato.getDetalle())
			{	
				switch (campo){
				case CAJAS_FISICAS_ACUMULADAS:
					anioActual[detalle.getMes()-1] = detalle.getCajasFacturadas();
					break;
				case CAJAS_UNITARIAS_ACUMULADAS:
					anioActual[detalle.getMes()-1] = detalle.getCajasUnitarias();
					break;
				case IMPORTE_FACTURADO:
					anioActual[detalle.getMes()-1] = detalle.getImporteFacturado();
					break;
				case IMPORTE_UTILIDAD:
					anioActual[detalle.getMes()-1] = detalle.getUtilidad();
					break;
				}	
			}
			bar.setTitle(dato.getDescripcion());
			bar.setShowInLegend(true);
			
			bar.setLabels(sLabel);
			bar.add(anioActual);
		}

		
		//chart.getChart().getSeries(0).setLabels(sLabel);
		
		chart.getLegend().setVisible(true);
		chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
		textTitle.setText(getTitulo());
		chart.getHeader().setText("Hist. Comparativo por Familia - Marca - Articulo");
		chart.getHeader().getFont().setSize(20);		    
	}

}
