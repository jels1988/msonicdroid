 package pe.lindley.profit.activity;

import java.util.ArrayList;
import java.util.Calendar;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ViewFlipper;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.profit.to.HistoryDetalleTO;
import pe.lindley.profit.ws.service.ProfitHistoryProxy;
import pe.lindley.util.ActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import pe.lindley.profit.to.ParametroTO;

public class ProfitHistoryActivity extends ActivityBase {

	public static final String CODIGO_CLIENTE_KEY = "codigo_cliente";
	public static final String CLIENTE_KEY = "cliente_descripcion";

	@InjectView(R.id.actionBar)
	ActionBar mActionBar;

	@InjectView(R.id.cboTipoProfit)
	Spinner cboTipoProfit;

	@InjectView(R.id.cboHistoryAnio)
	Spinner cboHistoryAnio;
	@InjectView(R.id.cboHistoryTipo)
	Spinner cboHistoryTipo;

	@InjectView(R.id.cboHistoryComparativoAnio)
	Spinner cboHistoryComparativoAnio;
	@InjectView(R.id.cboHistoryComparativoTipo)
	Spinner cboHistoryComparativoTipo;

	@InjectView(R.id.cboHistorySemanalAnio)
	Spinner cboHistorySemanalAnio;
	@InjectView(R.id.cboHistorySemanalMes)
	Spinner cboHistorySemanalMes;
	@InjectView(R.id.cboHistorySemanalTipo)
	Spinner cboHistorySemanalTipo;

	// History Familia Marca
	@InjectView(R.id.cboHistoryFamiliaMarcaAnio)
	Spinner cboHistoryFamiliaMarcaAnio;
	@InjectView(R.id.cboHistoryFamiliaMarcaFamilia)
	Spinner cboHistoryFamiliaMarcaFamilia;
	@InjectView(R.id.cboHistoryFamiliaMarca)
	Button cboHistoryFamiliaMarca;
	@InjectView(R.id.cboHistoryFamiliaMarcaTipo)
	Spinner cboHistoryFamiliaMarcaTipo;

	// History Familia Marca Articulo
	@InjectView(R.id.cboHistoryFamiliaMarcaArticuloAnio)
	Spinner cboHistoryFamiliaMarcaArticuloAnio;
	@InjectView(R.id.cboHistoryFamiliaMarcaArticuloFamilia)
	Spinner cboHistoryFamiliaMarcaArticuloFamilia;
	@InjectView(R.id.cboHistoryFamiliaMarcaArticuloMarca)
	Spinner cboHistoryFamiliaMarcaArticuloMarca;
	@InjectView(R.id.cboHistoryFamiliaMarcaArticulo)
	Button cboHistoryFamiliaMarcaArticulo;
	@InjectView(R.id.cboHistoryFamiliaMarcaArticuloTipo)
	Spinner cboHistoryFamiliaMarcaArticuloTipo;

	// History Segmento Marca
	@InjectView(R.id.cboHistorySegmentoMarcaAnio)
	Spinner cboHistorySegmentoMarcaAnio;
	@InjectView(R.id.cboHistorySegmentoMarcaSegmento)
	Spinner cboHistorySegmentoMarcaSegmento;
	@InjectView(R.id.cboHistorySegmentoMarca)
	Button cboHistorySegmentoMarca;
	@InjectView(R.id.cboHistorySegmentoMarcaTipo)
	Spinner cboHistorySegmentoMarcaTipo;

	// History Segmento Marca Articulo
	@InjectView(R.id.cboHistorySegmentoMarcaArticuloAnio)
	Spinner cboHistorySegmentoMarcaArticuloAnio;
	@InjectView(R.id.cboHistorySegmentoMarcaArticuloSegmento)
	Spinner cboHistorySegmentoMarcaArticuloSegmento;
	@InjectView(R.id.cboHistorySegmentoMarcaArticuloMarca)
	Spinner cboHistorySegmentoMarcaArticuloMarca;
	@InjectView(R.id.cboHistorySegmentoMarcaArticulo)
	Button cboHistorySegmentoMarcaArticulo;
	@InjectView(R.id.cboHistorySegmentoMarcaArticuloTipo)
	Spinner cboHistorySegmentoMarcaArticuloTipo;

	// History Articulo
	@InjectView(R.id.cboHistoryArticuloAnio)
	Spinner cboHistoryArticuloAnio;
	@InjectView(R.id.cboHistoryArticulo)
	Button cboHistoryArticulo;
	@InjectView(R.id.cboHistoryArticuloTipo)
	Spinner cboHistoryArticuloTipo;

	@InjectView(R.id.flpRegistrarCliente)
	ViewFlipper flpRegistrarCliente;
	@Inject
	ProfitHistoryProxy profitHistoryProxy;

	@InjectResource(R.string.profit_history_basico_activity_title)
	String profit_history_basic_title;
	@InjectResource(R.string.profit_history_comparativo_activity_title)
	String profit_history_comparativo_title;
	@InjectResource(R.string.profit_history_semanal_activity_title)
	String profit_history_semanal_title;

	public static String cliente_actual = null;

	private String cliente_codigo = null;
	private String cliente_descripcion = null;

	public static final String TABLA_TIPO_FAMILIA = "01";
	public static final String TABLA_TIPO_LINEA_MARCA = "02";
	public static final String TABLA_TIPO_SEGMENTO = "03";
	public static final String TABLA_TIPO_MARCA = "04";
	public static final String TABLA_TIPO_ARTICULO = "05";

	public static final int PROFIT_FAMILIA_MARCA = 3;
	public static final int PROFIT_FAMILIA_MARCA_ARTICULO = 4;
	public static final int PROFIT_SEGMENTO_MARCA = 5;
	public static final int PROFIT_SEGMENTO_MARCA_ARTICULO = 6;
	public static final int PROFIT_ARTICULO = 7;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.profithistory_activity);

		mActionBar.setTitle(R.string.profit_clientes_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);

		ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter
				.createFromResource(getApplicationContext(),
						R.array.profit_tipo,
						android.R.layout.simple_spinner_item);
		adapterTipo
				.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		cboTipoProfit.setAdapter(adapterTipo);

		String[] anios = new String[4];

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);

		for (int i = 0; i < 4; i++) {
			anios[i] = String.format("%s", year - i);
		}

		ArrayAdapter<String> adapterAnios = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_spinner_item,
				anios);
		adapterAnios
				.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		cboHistoryAnio.setAdapter(adapterAnios);
		cboHistoryComparativoAnio.setAdapter(adapterAnios);
		cboHistorySemanalAnio.setAdapter(adapterAnios);
		cboHistoryFamiliaMarcaAnio.setAdapter(adapterAnios);
		cboHistoryFamiliaMarcaArticuloAnio.setAdapter(adapterAnios);
		cboHistorySegmentoMarcaAnio.setAdapter(adapterAnios);
		cboHistorySegmentoMarcaArticuloAnio.setAdapter(adapterAnios);
		cboHistoryArticuloAnio.setAdapter(adapterAnios);

		ArrayAdapter<CharSequence> adapterCampos = ArrayAdapter
				.createFromResource(getApplicationContext(),
						R.array.profit_campo,
						android.R.layout.simple_spinner_item);
		adapterCampos
				.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		cboHistoryTipo.setAdapter(adapterCampos);
		cboHistoryComparativoTipo.setAdapter(adapterCampos);
		cboHistorySemanalTipo.setAdapter(adapterCampos);
		cboHistoryFamiliaMarcaTipo.setAdapter(adapterCampos);
		cboHistoryFamiliaMarcaArticuloTipo.setAdapter(adapterCampos);
		cboHistorySegmentoMarcaTipo.setAdapter(adapterCampos);
		cboHistorySegmentoMarcaArticuloTipo.setAdapter(adapterCampos);
		cboHistoryArticuloTipo.setAdapter(adapterCampos);

		ArrayAdapter<CharSequence> adapterMes = ArrayAdapter
				.createFromResource(getApplicationContext(),
						R.array.meses_array,
						android.R.layout.simple_spinner_item);
		adapterMes
				.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		cboHistorySemanalMes.setAdapter(adapterMes);

		final RTMApplication application = (RTMApplication) contextProvider
				.get().getApplicationContext();

		cboTipoProfit.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub

				if (arg2 == 3) {
					cboHistoryFamiliaMarcaFamilia.setAdapter(application
							.getAdapterParametrosProfit(TABLA_TIPO_FAMILIA));
					cboHistoryFamiliaMarcaFamilia
							.setOnItemSelectedListener(new OnItemSelectedListener() {
								@Override
								public void onItemSelected(
										AdapterView<?> parentView,
										View selectedItemView, int position,
										long id) {
									// your code here
									
									cboHistoryFamiliaMarca.setText("--Seleccionar--");
							    	selectedFamiliaMarca = new ArrayList<ParametroTO>();							    	
									
									if (position <= 0)
										familiaMarca = application
												.getAdapterListParametrosProfit(
														TABLA_TIPO_LINEA_MARCA,
														"");
										/*cboHistoryFamiliaMarca.setAdapter(application
												.getAdapterParametrosProfit(
														TABLA_TIPO_LINEA_MARCA,
														""));*/
									else
										familiaMarca = application
										.getAdapterListParametrosProfit(
												TABLA_TIPO_LINEA_MARCA,
												((ParametroTO) cboHistoryFamiliaMarcaFamilia
														.getSelectedItem())
														.getCodigo());
										/*cboHistoryFamiliaMarca.setAdapter(application
												.getAdapterParametrosProfit(
														TABLA_TIPO_LINEA_MARCA,
														((ParametroTO) cboHistoryFamiliaMarcaFamilia
																.getSelectedItem())
																.getCodigo()));*/

									if(familiaMarca.size() > 0)
										cboHistoryFamiliaMarca.setEnabled(true);
							    	else
							    		cboHistoryFamiliaMarca.setEnabled(false);
								}

								@Override
								public void onNothingSelected(
										AdapterView<?> parentView) {
									// your code here
								}

							});
				}
				if (arg2 == 4) {
					cboHistoryFamiliaMarcaArticuloFamilia.setAdapter(application
							.getAdapterParametrosProfit(TABLA_TIPO_FAMILIA));
					cboHistoryFamiliaMarcaArticuloFamilia
							.setOnItemSelectedListener(new OnItemSelectedListener() {
								@Override
								public void onItemSelected(
										AdapterView<?> parentView,
										View selectedItemView, int position,
										long id) {
									// your code here
									if (position <= 0)
										cboHistoryFamiliaMarcaArticuloMarca.setAdapter(application
												.getAdapterParametrosProfit(
														TABLA_TIPO_LINEA_MARCA,
														""));
									else
										cboHistoryFamiliaMarcaArticuloMarca.setAdapter(application
												.getAdapterParametrosProfit(
														TABLA_TIPO_LINEA_MARCA,
														((ParametroTO) cboHistoryFamiliaMarcaArticuloFamilia
																.getSelectedItem())
																.getCodigo()));
								}

								@Override
								public void onNothingSelected(
										AdapterView<?> parentView) {
									// your code here
								}
							});

					cboHistoryFamiliaMarcaArticuloMarca
							.setOnItemSelectedListener(new OnItemSelectedListener() {
								@Override
								public void onItemSelected(
										AdapterView<?> parentView,
										View selectedItemView, int position,
										long id) {
									// your code here
									
									cboHistoryFamiliaMarcaArticulo.setText("--Seleccionar--");
									selectedFamiliaMarcaArticulo = new ArrayList<ParametroTO>();						    	
																		
									familiaMarcaArticulo = application
											.getAdapterListParametrosProfit(
													TABLA_TIPO_ARTICULO);
									
									if(familiaMarcaArticulo.size() > 0)
										cboHistoryFamiliaMarcaArticulo.setEnabled(true);
							    	else
							    		cboHistoryFamiliaMarcaArticulo.setEnabled(false);
								}

								@Override
								public void onNothingSelected(
										AdapterView<?> parentView) {
									// your code here
								}
							});
				} else if (arg2 == 5) {
					cboHistorySegmentoMarcaSegmento.setAdapter(application
							.getAdapterParametrosProfit(TABLA_TIPO_SEGMENTO));
					segmentoMarca = application
							.getAdapterListParametrosProfit(TABLA_TIPO_MARCA);
					
					cboHistorySegmentoMarca.setText("--Seleccionar--");
			    	selectedSegmentoMarca = new ArrayList<ParametroTO>();
			    	if(segmentoMarca.size() > 0)
			    		cboHistorySegmentoMarca.setEnabled(true);
			    	else
			    		cboHistorySegmentoMarca.setEnabled(false);
					/*cboHistorySegmentoMarca.setAdapter(application
							.getAdapterParametrosProfit(TABLA_TIPO_MARCA));*/
				} else if (arg2 == 6) {
					cboHistorySegmentoMarcaArticuloSegmento.setAdapter(application
							.getAdapterParametrosProfit(TABLA_TIPO_SEGMENTO));
					cboHistorySegmentoMarcaArticuloMarca.setAdapter(application
							.getAdapterParametrosProfit(TABLA_TIPO_MARCA));
					
					segmentoMarcaArticulo = application
							.getAdapterListParametrosProfit(TABLA_TIPO_ARTICULO);
					
					cboHistorySegmentoMarcaArticulo.setText("--Seleccionar--");
			    	selectedSegmentoMarcaArticulo = new ArrayList<ParametroTO>();
			    	if(segmentoMarcaArticulo.size() > 0)
			    		cboHistorySegmentoMarcaArticulo.setEnabled(true);
			    	else
			    		cboHistorySegmentoMarcaArticulo.setEnabled(false);
					/*cboHistorySegmentoMarcaArticulo.setAdapter(application
							.getAdapterParametrosProfit(TABLA_TIPO_ARTICULO));*/
					
					/*cboHistorySegmentoMarcaArticuloMarca
							.setOnItemSelectedListener(new OnItemSelectedListener() {
								@Override
								public void onItemSelected(
										AdapterView<?> parentView,
										View selectedItemView, int position,
										long id) {
									// your code here
									if (position <= 0)
										cboHistorySegmentoMarcaArticulo.setAdapter(application
												.getAdapterParametrosProfit(
														TABLA_TIPO_ARTICULO, ""));
									else
										cboHistorySegmentoMarcaArticulo.setAdapter(application
												.getAdapterParametrosProfit(
														TABLA_TIPO_ARTICULO,
														((ParametroTO) cboHistorySegmentoMarcaArticuloMarca
																.getSelectedItem())
																.getCodigo()));
								}

								@Override
								public void onNothingSelected(
										AdapterView<?> parentView) {
									// your code here
								}
							});*/
				} else if (arg2 == 7) {
					articulos = application.getAdapterListParametrosProfit(TABLA_TIPO_ARTICULO);
					cboHistoryArticulo.setText("--Seleccionar--");
			    	selectedArticulos = new ArrayList<ParametroTO>();
			    	if(articulos.size() > 0)
			    		cboHistoryArticulo.setEnabled(true);
			    	else
			    		cboHistoryArticulo.setEnabled(false);
			    	
					//cboHistoryArticulo.setAdapter(application.getAdapterParametrosProfit(TABLA_TIPO_ARTICULO));
				}

				flpRegistrarCliente.setDisplayedChild(arg2);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		Intent intent = this.getIntent();
		cliente_codigo = intent.getStringExtra(CODIGO_CLIENTE_KEY);
		cliente_descripcion = intent.getStringExtra(CLIENTE_KEY);

		mActionBar.setSubTitle(cliente_descripcion);
		// txtCliente.setText(cliente_descripcion);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.profit_descargarparametros_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.mnuDescargar: {
			Intent descargarParametros = new Intent(this,
					DescargarParametrosActivity.class);
			startActivity(descargarParametros);
			return true;
		}
		}
		return true;
	}

	public void btnHistoryAceptar_onclick(View view) {
		processAsync();
	}

	public void btnHistoryComparativoAceptar_onclick(View view) {
		processAsync();
	}

	public void btnHistorySemanalAceptar_onclick(View view) {
		processAsync();
	}

	// Profit History Familia Marca
	public void btnHistoryFamiliaMarcaAceptar_onclick(View view) {
		if(cboHistoryFamiliaMarcaFamilia.getSelectedItemPosition() != 0 && selectedFamiliaMarca.size() != 0)
		{
			Intent profit = new Intent(this,ProfitHistoryFamiliaMarcaActivity.class);
			profit.putExtra(ProfitHistoryFamiliaMarcaActivity.CIENTE_KEY, cliente_descripcion);
			profit.putExtra(ProfitHistoryFamiliaMarcaActivity.CODIGO_CLIENTE_KEY, cliente_codigo);
			profit.putExtra(ProfitHistoryFamiliaMarcaActivity.ANIO, Integer.parseInt(cboHistoryFamiliaMarcaAnio.getSelectedItem().toString()));
			ParametroTO familia = ((ParametroTO) cboHistoryFamiliaMarcaFamilia.getSelectedItem());
			profit.putExtra(ProfitHistoryFamiliaMarcaActivity.CODIDO_FAMILIA, familia.getCodigo());
			profit.putExtra(ProfitHistoryFamiliaMarcaActivity.NOMBRE_FAMILIA, familia.getDescripcion());
			profit.putExtra(ProfitHistoryFamiliaMarcaActivity.TIPO_CAMPO, cboHistoryFamiliaMarcaTipo.getSelectedItemPosition());
			
			
			int count = selectedFamiliaMarca.size();
			String codMarca[] = new String[count];
			for(int i=0; i<count; i++)
			{
				codMarca[i] = ((ParametroTO) selectedFamiliaMarca.get(i)).getCodigo(); 
			}
			profit.putExtra(ProfitHistoryFamiliaMarcaActivity.MARCAS, codMarca);
			// profit.putExtra(MostrarDashBoardActivity.TIPO_DASHBOARD, 1);
			startActivity(profit);
			// processAsync(PROFIT_FAMILIA_MARCA);
		}
		else
		{
			MessageBox.showSimpleDialog(this, "Seleccionar parametros", "Debe seleccionar los parametros.", "Aceptar", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub					
				}
			});
		}
	}

	// Profit History Familia Marca Articulo	
	public void btnHistoryFamiliaMarcaArticuloAceptar_onclick(View view) {
		if(cboHistoryFamiliaMarcaArticuloFamilia.getSelectedItemPosition() != 0 && cboHistoryFamiliaMarcaArticuloMarca.getSelectedItemPosition() != 0 && selectedFamiliaMarcaArticulo.size() != 0)
		{
			// processAsync(PROFIT_FAMILIA_MARCA_ARTICULO);
			Intent profit = new Intent(this, ProfitHistoryFamMarcaArtActivity.class);
			profit.putExtra(ProfitHistoryFamMarcaArtActivity.CIENTE_KEY, cliente_descripcion);
			profit.putExtra(ProfitHistoryFamMarcaArtActivity.CODIGO_CLIENTE_KEY, cliente_codigo);
			profit.putExtra(ProfitHistoryFamMarcaArtActivity.ANIO, Integer.parseInt(cboHistoryFamiliaMarcaArticuloAnio.getSelectedItem().toString()));
			ParametroTO familia = ((ParametroTO) cboHistoryFamiliaMarcaArticuloFamilia.getSelectedItem());
			profit.putExtra(ProfitHistoryFamMarcaArtActivity.CODIDO_FAMILIA, familia.getCodigo());
			profit.putExtra(ProfitHistoryFamMarcaArtActivity.NOMBRE_FAMILIA, familia.getDescripcion());
			ParametroTO marca = ((ParametroTO) cboHistoryFamiliaMarcaArticuloMarca.getSelectedItem());
			profit.putExtra(ProfitHistoryFamMarcaArtActivity.TIPO_CAMPO, cboHistoryFamiliaMarcaArticuloTipo.getSelectedItemPosition());
			profit.putExtra(ProfitHistoryFamMarcaArtActivity.CODIGO_MARCA, marca.getCodigo());
			profit.putExtra(ProfitHistoryFamMarcaArtActivity.NOMBRE_MARCA, marca.getDescripcion());
			
			int count = selectedFamiliaMarcaArticulo.size();
			String codArticulo[] = new String[count];
			for(int i=0; i<count; i++)
			{
				codArticulo[i] = ((ParametroTO) selectedFamiliaMarcaArticulo.get(i)).getCodigo(); 
			}
			 
			profit.putExtra(ProfitHistoryFamMarcaArtActivity.ARTICULOS, codArticulo);
			// profit.putExtra(MostrarDashBoardActivity.TIPO_DASHBOARD, 1);
			startActivity(profit);
		}
		else
		{
			MessageBox.showSimpleDialog(this, "Seleccionar parametros", "Debe seleccionar los parametros.", "Aceptar", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub					
				}
			});
		}
	}

		
	// Profit History Segmento Marca
	public void btnHistorySegmentoMarcaAceptar_onclick(View view) {
		if(cboHistorySegmentoMarcaSegmento.getSelectedItemPosition() != 0 && selectedSegmentoMarca.size() != 0)
		{
			// processAsync(PROFIT_SEGMENTO_MARCA);
			Intent profit = new Intent(this, ProfitHistorySegmentoMarcaActivity.class);
			profit.putExtra(ProfitHistorySegmentoMarcaActivity.CIENTE_KEY, cliente_descripcion);
			profit.putExtra(ProfitHistorySegmentoMarcaActivity.CODIGO_CLIENTE_KEY, cliente_codigo);
			profit.putExtra(ProfitHistorySegmentoMarcaActivity.ANIO, Integer.parseInt(cboHistorySegmentoMarcaAnio.getSelectedItem().toString()));
			profit.putExtra(ProfitHistorySegmentoMarcaActivity.TIPO_CAMPO, cboHistorySegmentoMarcaTipo.getSelectedItemPosition());
			ParametroTO segmento = ((ParametroTO) cboHistorySegmentoMarcaSegmento.getSelectedItem());
			profit.putExtra(ProfitHistorySegmentoMarcaActivity.CODIGO_SEGMENTO, segmento.getCodigo());
			profit.putExtra(ProfitHistorySegmentoMarcaActivity.NOMBRE_SEGMENTO, segmento.getDescripcion());
			
			int count = selectedSegmentoMarca.size();
			String codMarca[] = new String[count];
			for(int i=0; i<count; i++)
			{
				codMarca[i] = ((ParametroTO) selectedSegmentoMarca.get(i)).getCodigo(); 
			}
			
			profit.putExtra(ProfitHistorySegmentoMarcaActivity.MARCAS, codMarca);
			// profit.putExtra(MostrarDashBoardActivity.TIPO_DASHBOARD, 1);
			startActivity(profit);
		}
		else
		{
			MessageBox.showSimpleDialog(this, "Seleccionar parametros", "Debe seleccionar los parametros.", "Aceptar", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub					
				}
			});
		}
	}

	
	// Profit History Segmento Marca Articulo
	public void btnHistorySegmentoMarcaArticuloAceptar_onclick(View view) {
		if(cboHistorySegmentoMarcaArticuloSegmento.getSelectedItemPosition() != 0 && cboHistorySegmentoMarcaArticuloMarca.getSelectedItemPosition() != 0 && selectedSegmentoMarcaArticulo.size() != 0)
		{
			// processAsync(PROFIT_SEGMENTO_MARCA_ARTICULO);
			Intent profit = new Intent(this, ProfitHistorySegMarArtActivity.class);
			profit.putExtra(ProfitHistorySegMarArtActivity.CIENTE_KEY, cliente_descripcion);
			profit.putExtra(ProfitHistorySegMarArtActivity.CODIGO_CLIENTE_KEY, cliente_codigo);
			profit.putExtra(ProfitHistorySegMarArtActivity.ANIO, Integer.parseInt(cboHistorySegmentoMarcaArticuloAnio.getSelectedItem().toString()));
			ParametroTO segmento = ((ParametroTO) cboHistorySegmentoMarcaArticuloSegmento.getSelectedItem());
			profit.putExtra(ProfitHistorySegMarArtActivity.CODIGO_SEGMENTO, segmento.getCodigo());
			profit.putExtra(ProfitHistorySegMarArtActivity.NOMBRE_SEGMENTO, segmento.getDescripcion());
			ParametroTO marca = ((ParametroTO) cboHistorySegmentoMarcaArticuloMarca.getSelectedItem());
			profit.putExtra(ProfitHistorySegMarArtActivity.CODIGO_MARCA, marca.getCodigo());
			profit.putExtra(ProfitHistorySegMarArtActivity.NOMBRE_MARCA, marca.getDescripcion());
			profit.putExtra(ProfitHistorySegMarArtActivity.TIPO_CAMPO, cboHistorySegmentoMarcaArticuloTipo.getSelectedItemPosition());
			
			int count = selectedSegmentoMarcaArticulo.size();
			String codArticulo[] = new String[count];
			for(int i=0; i<count; i++)
			{
				codArticulo[i] = ((ParametroTO) selectedSegmentoMarcaArticulo.get(i)).getCodigo(); 
			}
			
			//String codArticulo[] = new String[1];
			//codArticulo[0] = ((ParametroTO) cboHistorySegmentoMarcaArticulo.getSelectedItem()).getCodigo(); 
			profit.putExtra(ProfitHistorySegMarArtActivity.ARTICULOS, codArticulo);
			// profit.putExtra(MostrarDashBoardActivity.TIPO_DASHBOARD, 1);
			startActivity(profit);
		}
		else
		{
			MessageBox.showSimpleDialog(this, "Seleccionar parametros", "Debe seleccionar los parametros.", "Aceptar", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub					
				}
			});
		}
	}

	
	// Profit History Articulo
	public void btnHistoryArticuloAceptar_onclick(View view) {
		if(selectedArticulos.size() > 0)
		{
			// processAsync(PROFIT_ARTICULO);
			Intent profit = new Intent(this, ProfitHistoryArticuloActivity.class);
			profit.putExtra(ProfitHistoryArticuloActivity.CIENTE_KEY, cliente_descripcion);
			profit.putExtra(ProfitHistoryArticuloActivity.CODIGO_CLIENTE_KEY, cliente_codigo);
			profit.putExtra(ProfitHistoryArticuloActivity.ANIO, Integer.parseInt(cboHistoryArticuloAnio.getSelectedItem().toString()));
			profit.putExtra(ProfitHistoryArticuloActivity.TIPO_CAMPO, cboHistoryArticuloTipo.getSelectedItemPosition());
			
			int count = selectedArticulos.size();
			String codArticulo[] = new String[count];
			for(int i=0; i<count; i++)
			{
				codArticulo[i] = ((ParametroTO) selectedArticulos.get(i)).getCodigo(); 
			}
			
			
			//codArticulo[0] = ((ParametroTO) cboHistoryArticulo.getSelectedItem()).getCodigo(); 
			profit.putExtra(ProfitHistoryArticuloActivity.ARTICULOS, codArticulo);
			// profit.putExtra(MostrarDashBoardActivity.TIPO_DASHBOARD, 1);
			startActivity(profit);
		}
		else
		{
			MessageBox.showSimpleDialog(this, "Seleccionar parametros", "Debe seleccionar los parametros.", "Aceptar", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub					
				}
			});
		}
	}
	
	public void btnCancelar_onclick(View view) {
		finish();
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		int viewIndex = flpRegistrarCliente.getDisplayedChild();
		int anio = 0;
		if (viewIndex == 0) {
			anio = Integer
					.parseInt(cboHistoryAnio.getSelectedItem().toString());

		} else if (viewIndex == 1) {
			anio = Integer.parseInt(cboHistoryComparativoAnio.getSelectedItem()
					.toString());

		} else if (viewIndex == 2) {
			anio = Integer.parseInt(cboHistorySemanalAnio.getSelectedItem()
					.toString());
		}

		int tipo = flpRegistrarCliente.getDisplayedChild();

		if (anio != profitHistoryProxy.getAnio()
				|| tipo != profitHistoryProxy.getTipo()
				|| ProfitHistoryActivity.cliente_actual != cliente_codigo) {
			profitHistoryProxy.setCodigo(cliente_codigo);
			profitHistoryProxy.setAnio(anio);
			profitHistoryProxy.setTipo(tipo);
			profitHistoryProxy.execute();

		}
		ProfitHistoryActivity.cliente_actual = cliente_codigo;
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = profitHistoryProxy.isExito();

		String titulo = null;

		if (isExito) {
			int status = profitHistoryProxy.getResponse().getStatus();
			if (status == 0) {

				ArrayList<HistoryDetalleTO> detalle = profitHistoryProxy
						.getResponse().getDetalle();

				int tipo = flpRegistrarCliente.getDisplayedChild();
				if (tipo == 0) {
					ProfitHistoryBasicoActivity profitHistoryActivity = new ProfitHistoryBasicoActivity();
					profitHistoryActivity.setDetalle(detalle);

					titulo = String.format(profit_history_basic_title,
							cliente_descripcion);
					profitHistoryActivity.setTitulo(titulo);
					int anio = Integer.parseInt(cboHistoryAnio.getSelectedItem().toString());
					
					profitHistoryActivity.setAnio(anio);

					int campo = cboHistoryTipo.getSelectedItemPosition();
					profitHistoryActivity.setCampo(campo);

					Intent intent = profitHistoryActivity
							.execute(getApplicationContext());
					startActivity(intent);
				} else if (tipo == 1) {
					ProfitHistoryComparativoActivity profitComparativoActivity = new ProfitHistoryComparativoActivity();
					profitComparativoActivity.setDetalle(detalle);

					titulo = String.format(profit_history_comparativo_title,
							cliente_descripcion, cliente_descripcion);
					profitComparativoActivity.setTitulo(titulo);

					int anio = Integer.parseInt(cboHistoryComparativoAnio
							.getSelectedItem().toString());
					
					profitComparativoActivity.setAnio(anio);

					int campo = cboHistoryComparativoTipo.getSelectedItemPosition();
					profitComparativoActivity.setCampo(campo);

					Intent intent = profitComparativoActivity
							.execute(getApplicationContext());
					startActivity(intent);

				} else if (tipo == 2) {
					ProfitHistorySemanalActivity profitHistorySemanalActivity = new ProfitHistorySemanalActivity();
					profitHistorySemanalActivity.setDetalle(detalle);

					titulo = String.format(profit_history_semanal_title,
							cliente_descripcion, cliente_descripcion);
					profitHistorySemanalActivity.setTitulo(titulo);

					int anio = Integer.parseInt(cboHistorySemanalAnio
							.getSelectedItem().toString());
					profitHistorySemanalActivity.setAnio(anio);

					int mes = cboHistorySemanalMes.getSelectedItemPosition() + 1;
					profitHistorySemanalActivity.setMes(mes);

					int campo = cboHistorySemanalTipo.getSelectedItemPosition();
					profitHistorySemanalActivity.setCampo(campo);

					Intent intent = profitHistorySemanalActivity
							.execute(getApplicationContext());
					startActivity(intent);
				}
			}
		}
		super.processOk();
	}
	
	
	//History Articulo ---------------------------------------------------------
	protected ArrayList<ParametroTO> articulos;
	protected ArrayList<ParametroTO> selectedArticulos = new ArrayList<ParametroTO>();

	public void btnHistoryArticulo_onclick(View view)
	{
		showSelectHistoryArticuloDialog();
	}
	
	protected void onChangeSelectedHistoryArticulo() {	
		cboHistoryArticulo.setText(selectedArticulos.size() + " seleccionados.");
	}
	
	protected void showSelectHistoryArticuloDialog() {
		int count = articulos.size();
		boolean[] checkedColours = new boolean[count];	

		for(int i = 0; i < count; i++)
			checkedColours[i] = selectedArticulos.contains((ParametroTO)articulos.get(i));

		DialogInterface.OnMultiChoiceClickListener coloursDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				if(isChecked)
					selectedArticulos.add((ParametroTO)articulos.get(which));
				else
					selectedArticulos.remove((ParametroTO)articulos.get(which));

				onChangeSelectedHistoryArticulo();
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Seleccionar Articulos");
				
		CharSequence[] temp = new CharSequence[count];
		
		for(int i=0;i<count;i++)
		{
			temp[i] = ((ParametroTO) articulos.get(i)).getDescripcion();
		}
		
		builder.setMultiChoiceItems(temp, checkedColours, coloursDialogListener);

		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	//------------------------------------------------------------------

	
	//History Familia Marca ---------------------------------------------------------
	protected ArrayList<ParametroTO> familiaMarca;
	protected ArrayList<ParametroTO> selectedFamiliaMarca = new ArrayList<ParametroTO>();

	public void btnHistoryFamiliaMarca_onclick(View view)
	{
		showSelectHistoryFamiliaMarcaDialog();
	}
	
	protected void onChangeSelectedHistoryFamiliaMarca() {	
		cboHistoryFamiliaMarca.setText(selectedFamiliaMarca.size() + " seleccionados.");
	}
	
	protected void showSelectHistoryFamiliaMarcaDialog() {
		int count = familiaMarca.size();
		boolean[] checkedColours = new boolean[count];	

		for(int i = 0; i < count; i++)
			checkedColours[i] = selectedFamiliaMarca.contains((ParametroTO) familiaMarca.get(i));

		DialogInterface.OnMultiChoiceClickListener familiaMarcaDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				if(isChecked)
					selectedFamiliaMarca.add((ParametroTO) familiaMarca.get(which));
				else
					selectedFamiliaMarca.remove((ParametroTO) familiaMarca.get(which));

				onChangeSelectedHistoryFamiliaMarca();
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Seleccionar Marcas");
				
		CharSequence[] temp = new CharSequence[count];
		
		for(int i=0;i<count;i++)
		{
			temp[i] = ((ParametroTO) familiaMarca.get(i)).getDescripcion();
		}
		
		builder.setMultiChoiceItems(temp, checkedColours, familiaMarcaDialogListener);

		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	//------------------------------------------------------------------

	//History Familia Marca Articulo ---------------------------------------------------------
	protected ArrayList<ParametroTO> familiaMarcaArticulo;
	protected ArrayList<ParametroTO> selectedFamiliaMarcaArticulo = new ArrayList<ParametroTO>();

	public void btnHistoryFamiliaMarcaArticulo_onclick(View view)
	{
		showSelectHistoryFamiliaMarcaArticuloDialog();
	}
	
	protected void onChangeSelectedHistoryFamiliaMarcArticulo() {	
		cboHistoryFamiliaMarcaArticulo.setText(selectedFamiliaMarcaArticulo.size() + " seleccionados.");
	}
	
	protected void showSelectHistoryFamiliaMarcaArticuloDialog() {
		int count = familiaMarcaArticulo.size();
		boolean[] checkedColours = new boolean[count];	

		for(int i = 0; i < count; i++)
			checkedColours[i] = selectedFamiliaMarcaArticulo.contains((ParametroTO) familiaMarcaArticulo.get(i));

		DialogInterface.OnMultiChoiceClickListener familiaMarcaArticuloDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				if(isChecked)
					selectedFamiliaMarcaArticulo.add((ParametroTO) familiaMarcaArticulo.get(which));
				else
					selectedFamiliaMarcaArticulo.remove((ParametroTO) familiaMarcaArticulo.get(which));

				onChangeSelectedHistoryFamiliaMarcArticulo();
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Seleccionar Articulos");
				
		CharSequence[] temp = new CharSequence[count];
		
		for(int i=0;i<count;i++)
		{
			temp[i] = ((ParametroTO) familiaMarcaArticulo.get(i)).getDescripcion();
		}
		
		builder.setMultiChoiceItems(temp, checkedColours, familiaMarcaArticuloDialogListener);

		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	//------------------------------------------------------------------
	

	//History Segmento Marca ---------------------------------------------------------
	protected ArrayList<ParametroTO> segmentoMarca;
	protected ArrayList<ParametroTO> selectedSegmentoMarca = new ArrayList<ParametroTO>();

	public void btnHistorySegmentoMarca_onclick(View view)
	{
		showSelectHistorySegmentoMarcaDialog();
	}
	
	protected void onChangeSelectedHistorySegmentoMarca() {	
		cboHistorySegmentoMarca.setText(selectedSegmentoMarca.size() + " seleccionados.");
	}
	
	protected void showSelectHistorySegmentoMarcaDialog() {
		int count = segmentoMarca.size();
		boolean[] checkedColours = new boolean[count];	

		for(int i = 0; i < count; i++)
			checkedColours[i] = selectedSegmentoMarca.contains((ParametroTO)segmentoMarca.get(i));

		DialogInterface.OnMultiChoiceClickListener coloursDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				if(isChecked)
					selectedSegmentoMarca.add((ParametroTO)segmentoMarca.get(which));
				else
					selectedSegmentoMarca.remove((ParametroTO)segmentoMarca.get(which));

				onChangeSelectedHistorySegmentoMarca();
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Seleccionar Marcas");
				
		CharSequence[] temp = new CharSequence[count];
		
		for(int i=0;i<count;i++)
		{
			temp[i] = ((ParametroTO) segmentoMarca.get(i)).getDescripcion();
		}
		
		builder.setMultiChoiceItems(temp, checkedColours, coloursDialogListener);

		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	//------------------------------------------------------------------
	
	//History Segmento Marca Articulo ---------------------------------------------------------
	protected ArrayList<ParametroTO> segmentoMarcaArticulo;
	protected ArrayList<ParametroTO> selectedSegmentoMarcaArticulo = new ArrayList<ParametroTO>();

	public void btnHistorySegmentoMarcaArticulo_onclick(View view)
	{
		showSelectHistorySegmentoMarcaArticuloDialog();
	}
	
	protected void onChangeSelectedHistorySegmentoMarcaArticulo() {	
		cboHistorySegmentoMarcaArticulo.setText(selectedSegmentoMarcaArticulo.size() + " seleccionados.");
	}
	
	protected void showSelectHistorySegmentoMarcaArticuloDialog() {
		int count = segmentoMarcaArticulo.size();
		boolean[] checkedColours = new boolean[count];	

		for(int i = 0; i < count; i++)
			checkedColours[i] = selectedSegmentoMarcaArticulo.contains((ParametroTO)segmentoMarcaArticulo.get(i));

		DialogInterface.OnMultiChoiceClickListener coloursDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				if(isChecked)
					selectedSegmentoMarcaArticulo.add((ParametroTO)segmentoMarcaArticulo.get(which));
				else
					selectedSegmentoMarcaArticulo.remove((ParametroTO)segmentoMarcaArticulo.get(which));

				onChangeSelectedHistorySegmentoMarcaArticulo();
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Seleccionar Articulos");
				
		CharSequence[] temp = new CharSequence[count];
		
		for(int i=0;i<count;i++)
		{
			temp[i] = ((ParametroTO) segmentoMarcaArticulo.get(i)).getDescripcion();
		}
		
		builder.setMultiChoiceItems(temp, checkedColours, coloursDialogListener);

		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	//------------------------------------------------------------------	
}
