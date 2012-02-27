package pe.lindley.ficha.activity;

import java.util.List;
import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import pe.lindley.activity.DireccionActivity;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.ficha.negocio.OpcionMultipleBLL;
import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.lanzador.ws.service.ConsultarClienteProxy;
import pe.lindley.plandesarrollo.activity.MostrarPlanDesarrolloActivity;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class ConsultarClienteActivity extends ListActivityBase {
	
	@Inject ConsultarClienteProxy consultarClienteProxy;
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@InjectView(R.id.txtCodigo)TextView txtCodigo;
	@InjectView(R.id.txtRuc)TextView txtRuc;
	@InjectView(R.id.txtDni)TextView txtDni;
	@InjectResource(R.string.ficha_consultar_cliente_activity_empty) String parametros_empty;
	@InjectResource(R.string.consultar_cliente_activity_sub_title) String subtitulo;
	@InjectResource(R.string.consultar_cliente_activity_title) String titulo;
	private EfficientAdapter adap;
	
	@Inject OpcionMultipleBLL parametroBLL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.ficha_consultar_cliente_activity);
				
		mActionBar.setTitle(titulo);
		mActionBar.setSubTitle(subtitulo);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		txtCodigo.addTextChangedListener(txtCodigoTextWatcher);
		txtRuc.addTextChangedListener(txtRucTextWatcher);
		txtDni.addTextChangedListener(txtDniTextWatcher);
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.ficha_descargarparametros_menu, menu);
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
	
	/*private void llenar_parametro() {
		// TODO Auto-generated method stub
		parametroBLL.deleteAll();
		
		String json = "{'OPC':[{'CTBL':'02','COD':'013','DES':'HAMBURGUESA'},{'CTBL':'02','COD':'014','DES':'PIZZA'},{'CTBL':'02','COD':'015','DES':'POLLO'},{'CTBL':'02','COD':'016','DES':'MEJICANO'},{'CTBL':'02','COD':'017','DES':'SANDUCHES/EMPNADAS'},{'CTBL':'02','COD':'021','DES':'HOTELES/MOTEL/POSADA'},{'CTBL':'02','COD':'022','DES':'PROVEEDURIAS MILITARES'},{'CTBL':'02','COD':'023','DES':'CASINOS MILITARES'},{'CTBL':'02','COD':'026','DES':'PRIMARIO'},{'CTBL':'02','COD':'028','DES':'SECUNDARIA/POLIMODAL'},{'CTBL':'02','COD':'033','DES':'CINES'},{'CTBL':'02','COD':'101','DES':'SUPERMERC CADENA NACIO/INT.'},{'CTBL':'02','COD':'102','DES':'SUPERMERCADO INDEPENDIENTE'},{'CTBL':'02','COD':'104','DES':'CADENA NACIONAL/INTERN.'},{'CTBL':'02','COD':'105','DES':'MINIMARKET INDEPENDIENTE'},{'CTBL':'02','COD':'106','DES':'EST.SERVICIO/CADENA S/MART'},{'CTBL':'02','COD':'107','DES':'CARNICERIA'},{'CTBL':'02','COD':'108','DES':'PANADERIA/PASTELERIA'},{'CTBL':'02','COD':'109','DES':'DELICATESSEN'},{'CTBL':'02','COD':'110','DES':'LOCALES NATURISTAS'},{'CTBL':'02','COD':'111','DES':'FRUTERIA/VERDULERIA'},{'CTBL':'02','COD':'112','DES':'OTROS VIVERES ESPEC.'},{'CTBL':'02','COD':'116','DES':'BODEGAS'},{'CTBL':'02','COD':'118','DES':'ENTREGA A DOMICILIO'},{'CTBL':'02','COD':'120','DES':'LICORES/CERVERZA/VINOS'},{'CTBL':'02','COD':'121','DES':'BEBIDAS EN GENERAL'},{'CTBL':'02','COD':'123','DES':'TIENDA POR DEPARTAMENTOS'},{'CTBL':'02','COD':'124','DES':'MUEBLES/ARTICULOS DEL HOGAR'},{'CTBL':'02','COD':'125','DES':'ALMACEN DE ROPA Y ACCESORIOS'},{'CTBL':'02','COD':'126','DES':'ELECTRONICA/COMPUTACION'},{'CTBL':'02','COD':'127','DES':'LIBRERIAS/REVISTAS/TABAQ.'},{'CTBL':'02','COD':'128','DES':'FERRETERIA/HERRAMIENTAS'},{'CTBL':'02','COD':'131','DES':'OTROS ALMACENES'},{'CTBL':'02','COD':'133','DES':'CADENAS DE FARMACIAS'},{'CTBL':'02','COD':'135','DES':'PELUQUERIA/SALON DE BELLEZA'},{'CTBL':'02','COD':'136','DES':'LAVANDERIA/TINTORERIA'},{'CTBL':'02','COD':'137','DES':'BANCOS/FINANCIERAS'},{'CTBL':'02','COD':'138','DES':'SERVIC.PROFESION.PARA OF.'},{'CTBL':'02','COD':'139','DES':'PLAYAS DE ESTACIONAMIENTO'},{'CTBL':'02','COD':'140','DES':'AGENCIAS DE TURISMO'},{'CTBL':'02','COD':'141','DES':'VIDEO CLUB'},{'CTBL':'02','COD':'142','DES':'BICICLETAS/MOTOS'},{'CTBL':'02','COD':'143','DES':'SERVICIOS MARITIMOS/NAUTICA'},{'CTBL':'02','COD':'144','DES':'ALMACENES DE REVISTA Y PER.'},{'CTBL':'02','COD':'145','DES':'BOUTIQUE'},{'CTBL':'02','COD':'146','DES':'OTRAS VENTAS Y SERVICIOS'},{'CTBL':'02','COD':'148','DES':'DONUTS'},{'CTBL':'02','COD':'150','DES':'OTROS COMIDAS RAPIDAS'},{'CTBL':'02','COD':'151','DES':'RESTAURANTE FINO CATEGORIA'},{'CTBL':'02','COD':'152','DES':'RESTAURANTES CATEGORIA MEDIA'},{'CTBL':'02','COD':'153','DES':'OTROS RESTAURANTES'},{'CTBL':'02','COD':'154','DES':'KIOSKOS'},{'CTBL':'02','COD':'155','DES':'CARRETILLAS'},{'CTBL':'02','COD':'157','DES':'CLUBES NOCTURNOS'},{'CTBL':'02','COD':'158','DES':'BARES'},{'CTBL':'02','COD':'159','DES':'PUBS'},{'CTBL':'02','COD':'163','DES':'CAFE NET/INTERNET'},{'CTBL':'02','COD':'164','DES':'SNACK BAR'},{'CTBL':'02','COD':'165','DES':'CAFE BAR'},{'CTBL':'02','COD':'166','DES':'HELADERIAS'},{'CTBL':'02','COD':'167','DES':'COMIDA SELF-SERVICE'},{'CTBL':'02','COD':'168','DES':'PARQUE DE DIVERSIONES'},{'CTBL':'02','COD':'169','DES':'PARQUES/PICNIC/BALNEARIOS'},{'CTBL':'02','COD':'170','DES':'ZOOLIGO/MUSEOS/ACUARIOS'},{'CTBL':'02','COD':'171','DES':'BOWLING/BILLARES'},{'CTBL':'02','COD':'172','DES':'JUEGOS ELECTRONICOS'},{'CTBL':'02','COD':'174','DES':'DISCOTECAS'},{'CTBL':'02','COD':'175','DES':'CASINOS/JUEGOS DE AZAR/BINGO'},{'CTBL':'02','COD':'176','DES':'TEATROS'},{'CTBL':'02','COD':'177','DES':'AREAS COMUNALES'},{'CTBL':'02','COD':'178','DES':'RELIGIOSO/IGLESIAS/CARIDAD'},{'CTBL':'02','COD':'179','DES':'CLUB ORGANIZACION SOCIAL'},{'CTBL':'02','COD':'180','DES':'CLUB/ORGANIZACION JUVENIL'},{'CTBL':'02','COD':'181','DES':'COUNTRY CLUB/CLUBS DEPORTIVO'},{'CTBL':'02','COD':'182','DES':'FERIAS/EVENTOS'},{'CTBL':'02','COD':'183','DES':'EXPOSICIONES'},{'CTBL':'02','COD':'184','DES':'SHOW RODANTES(CIRCOS/RODEOS)'},{'CTBL':'02','COD':'185','DES':'DESFILES/FESTIVALES'},{'CTBL':'02','COD':'186','DES':'OTRAS RECREACIONES'},{'CTBL':'02','COD':'187','DES':'HIPODROMOS/CIRCUITOS CARRER'},{'CTBL':'02','COD':'189','DES':'OTROS(GIMNASIOS)'},{'CTBL':'02','COD':'190','DES':'AEROPUERTOS'},{'CTBL':'02','COD':'191','DES':'AEROLINEAS'},{'CTBL':'02','COD':'192','DES':'TRENES'},{'CTBL':'02','COD':'193','DES':'TERMINAL DE TRENES'},{'CTBL':'02','COD':'194','DES':'LINEAS DE BUSES'},{'CTBL':'02','COD':'195','DES':'TERMINAL TERRESTRE'},{'CTBL':'02','COD':'196','DES':'TERMINAL TRANPORTE CARGA TER'},{'CTBL':'02','COD':'197','DES':'TRANSPORTE PUBLICO'},{'CTBL':'02','COD':'198','DES':'PEAJES'},{'CTBL':'02','COD':'199','DES':'CRUCEROS/LINEAS MARITIMA'},{'CTBL':'02','COD':'200','DES':'PUERTO MARITIMO'},{'CTBL':'02','COD':'201','DES':'CARGA MARITIMA'},{'CTBL':'02','COD':'203','DES':'OTROS SISTEMAS DE TRANSPORTE'},{'CTBL':'02','COD':'205','DES':'LUGARES PARA CAMPING'},{'CTBL':'02','COD':'206','DES':'OTROS ALOJAMIENTOS'},{'CTBL':'02','COD':'209','DES':'ESCUELAS DE NEGOCIOS/ADMINIS'},{'CTBL':'02','COD':'210','DES':'TECNICOS'},{'CTBL':'02','COD':'211','DES':'ACADEMIAS /OTROS INSTITUTOS'},{'CTBL':'02','COD':'214','DES':'UNIVERSIDADES'},{'CTBL':'02','COD':'215','DES':'UNIVERSIDADES C/MASTERADO/DO'},{'CTBL':'02','COD':'219','DES':'MILITAR-OTROS'},{'CTBL':'02','COD':'220','DES':'MANUFACTURA/INDUSTRIA'},{'CTBL':'02','COD':'221','DES':'AGRICOLA/GANADERA/PESCA'},{'CTBL':'02','COD':'222','DES':'SERVIC PUBLIC(LUZ,AGUA,TEL'},{'CTBL':'02','COD':'223','DES':'CONSTRUCCION/OTROS'},{'CTBL':'02','COD':'224','DES':'DEPENDENCIA GOB.NACIONAL'},{'CTBL':'02','COD':'225','DES':'DEPENDENCIA GOB.LOCAL'},{'CTBL':'02','COD':'226','DES':'CORREOS'},{'CTBL':'02','COD':'227','DES':'POLICIA/CARCELES/BOMBEROS'},{'CTBL':'02','COD':'228','DES':'OTRAS DEPENDENCIAS'},{'CTBL':'02','COD':'229','DES':'HOSPITALES'},{'CTBL':'02','COD':'230','DES':'CLINICAS/SANATORIOS'},{'CTBL':'02','COD':'231','DES':'ASILOS /HOSPICIOS'},{'CTBL':'02','COD':'232','DES':'GERIATRICOS/LUGARES DE RET'},{'CTBL':'02','COD':'233','DES':'OTROS SALUD/HOSPITALES'},{'CTBL':'02','COD':'234','DES':'MAYORISTAS'},{'CTBL':'02','COD':'238','DES':'VENTA EN PLANTA'},{'CTBL':'02','COD':'239','DES':'EXPORTACION'},{'CTBL':'02','COD':'242','DES':'MAQUINAS VENDING'},{'CTBL':'02','COD':'243','DES':'CATERING/SERVICIO DE BUFFET'},{'CTBL':'02','COD':'244','DES':'CONCESIONARIO DE COMIDA'},{'CTBL':'02','COD':'245','DES':'VENDING DE TERCEROS'},{'CTBL':'02','COD':'249','DES':'DISTRIBUIDORES'},{'CTBL':'02','COD':'250','DES':'VENTA A OTROS EMBOTELLADORES'},{'CTBL':'02','COD':'251','DES':'HOGARES/PARTICULARES'},{'CTBL':'02','COD':'252','DES':'COMIDA LOCAL/TRADICIONAL'},{'CTBL':'02','COD':'255','DES':'EST.SERVIC.INDEPENDI S/MART'},{'CTBL':'02','COD':'260','DES':'OTROS TERCEROS'},{'CTBL':'02','COD':'501','DES':'MINIMARKET CADENA'},{'CTBL':'02','COD':'503','DES':'EST.SERVIC.INDEPENDI C/MART'},{'CTBL':'02','COD':'507','DES':'CABINAS TELEFONICAS/LOCUTOR'},{'CTBL':'02','COD':'508','DES':'FUNERARIA'},{'CTBL':'02','COD':'510','DES':'CARNES Y PARRILLADAS'},{'CTBL':'02','COD':'512','DES':'ESTADIOS/COLISEOS'},{'CTBL':'02','COD':'513','DES':'GIMNASIOS/DEFENSA PERSONAL'},{'CTBL':'02','COD':'515','DES':'NIDO/PRE-ESCOLAR'},{'CTBL':'02','COD':'517','DES':'SERVICIO AUTOMOTRIZ'},{'CTBL':'02','COD':'518','DES':'HIPERMERCADOS'},{'CTBL':'02','COD':'519','DES':'MINIMARKET INDEPENDIENTE'},{'CTBL':'02','COD':'520','DES':'EST.SERVICIO/CADENA CON MART'},{'CTBL':'02','COD':'522','DES':'OFICINAS DE NEGOCIOS PROFESI'},{'CTBL':'02','COD':'524','DES':'HOT DOG S'},{'CTBL':'02','COD':'526','DES':'POLLERIAS'},{'CTBL':'02','COD':'527','DES':'PESCADOS Y MARISCOS'},{'CTBL':'02','COD':'528','DES':'CRIOLLO/PICANTERIA'},{'CTBL':'02','COD':'529','DES':'CHIFA'},{'CTBL':'02','COD':'536','DES':'NEGOCIOS EN MERCADOS'},{'CTBL':'02','COD':'537','DES':'OPERADOR TRICIBOTELLAS'},{'CTBL':'02','COD':'540','DES':'MINERAS/CANTERAS'},{'CTBL':'02','COD':'544','DES':'EJERCITO'},{'CTBL':'02','COD':'545','DES':'MARINA'},{'CTBL':'02','COD':'546','DES':'FUERZA AEREA'},{'CTBL':'02','COD':'548','DES':'LUSTRABOTAS'},{'CTBL':'02','COD':'549','DES':'CRUCERISTA/CARAMELEROS'},{'CTBL':'01','COD':'01','DES':'TITULAR'},{'CTBL':'01','COD':'02','DES':'HERMANO'},{'CTBL':'01','COD':'03','DES':'CONYUGUE'}],'STS':0,'DES':null}";
		ObtenerOpcionMultipleResponse obtenerOpcionMultipleResponse = JSONHelper.desSerializar(json, ObtenerOpcionMultipleResponse.class);
		
		List<OpcionMultipleTO> parametros =	obtenerOpcionMultipleResponse.getObtenerOpciones();
		
		if(parametros!=null){
			parametroBLL.save(parametros);							
		}
	}*/


	@Override
	protected boolean executeAsyncPre() {
		// TODO Auto-generated method stub
		boolean isCodigo = txtCodigo.getText().toString().trim().equalsIgnoreCase("");
		boolean isRuc = txtRuc.getText().toString().trim().equalsIgnoreCase("");
		boolean isDni = txtDni.getText().toString().trim().equalsIgnoreCase("");

		if (isCodigo && isRuc && isDni) {
			txtCodigo.setError(parametros_empty);
			txtRuc.setError(parametros_empty);
			txtDni.setError(parametros_empty);
			return false;
		} else {
			txtCodigo.setError(null);
			txtRuc.setError(null);
			txtDni.setError(null);
		}

		return true;

	}
	
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		String codigo = txtCodigo.getText().toString();
		String ruc = txtRuc.getText().toString();
		String dni = txtDni.getText().toString();

		consultarClienteProxy.setCodigo(codigo);
		consultarClienteProxy.setRuc(ruc);
		consultarClienteProxy.setDni(dni);

		consultarClienteProxy.execute();

	}
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarClienteProxy.isExito();
		if (isExito) {
			int status = consultarClienteProxy.getResponse().getStatus();
			if (status == 0) {
				List<ClienteResumenTO> clientes = consultarClienteProxy.getResponse().getClientes();
				RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
				application.setClienteTO(clientes.get(0));
				adap = new EfficientAdapter(this, clientes);
				setListAdapter(adap);
			}
		}
		super.processOk();
	}
	
	
	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
	}
	
	
	public void btnbuscar_onclick(View view){
		processAsync();	
	}

	
	
	private TextWatcher txtRucTextWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (txtRuc.getText() != null) {

				txtCodigo.removeTextChangedListener(txtCodigoTextWatcher);
				txtDni.removeTextChangedListener(txtDniTextWatcher);

				txtCodigo.setText(null);
				txtDni.setText(null);

				txtCodigo.addTextChangedListener(txtCodigoTextWatcher);
				txtDni.addTextChangedListener(txtDniTextWatcher);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};

	private TextWatcher txtDniTextWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (txtDni.getText() != null) {
				txtCodigo.removeTextChangedListener(txtCodigoTextWatcher);
				txtRuc.removeTextChangedListener(txtRucTextWatcher);

				txtCodigo.setText(null);
				txtRuc.setText(null);

				txtCodigo.addTextChangedListener(txtCodigoTextWatcher);
				txtRuc.addTextChangedListener(txtRucTextWatcher);

			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};
	private TextWatcher txtCodigoTextWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (txtCodigo.getText() != null) {

				txtRuc.removeTextChangedListener(txtRucTextWatcher);
				txtDni.removeTextChangedListener(txtDniTextWatcher);

				txtRuc.setText(null);
				txtDni.setText(null);

				txtRuc.addTextChangedListener(txtRucTextWatcher);
				txtDni.addTextChangedListener(txtDniTextWatcher);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};
	
	
	
	
	
	
	
	
	
	public static class EfficientAdapter extends BaseAdapter implements
	Filterable {
private LayoutInflater mInflater;
private Context context;
private List<ClienteResumenTO> clientes;

public EfficientAdapter(Context context, List<ClienteResumenTO> clientes) {
	// Cache the LayoutInflate to avoid asking for a new one each time.
	mInflater = LayoutInflater.from(context);
	this.context = context;
	this.clientes = clientes;
}

public EfficientAdapter(Context context) {
	// Cache the LayoutInflate to avoid asking for a new one each time.
	mInflater = LayoutInflater.from(context);
	this.context = context;
}

/**
 * Make a view to hold each row.
 * 
 * @see android.widget.ListAdapter#getView(int, android.view.View,
 *      android.view.ViewGroup)
 */
@Override
public View getView(final int position, View convertView,
		ViewGroup parent) {
	ClienteResumenTO cliente = (ClienteResumenTO) getItem(position);
	ViewHolder holder;

	if (convertView == null) {
		convertView = mInflater.inflate(
				R.layout.ficha_consultar_cliente_content, null);

		// Creates a ViewHolder and store references to the two children
		// views
		// we want to bind data to.
		holder = new ViewHolder();
		holder.txtRazonSocial = (TextView) convertView
				.findViewById(R.id.txtRazonSocial);
		holder.txtRuc = (TextView) convertView
				.findViewById(R.id.txtRuc);
		holder.txtDni = (TextView) convertView
				.findViewById(R.id.txtDni);
		holder.txtCodigo = (TextView) convertView
				.findViewById(R.id.txtCodigo);
		holder.txtDireccion = (TextView) convertView
				.findViewById(R.id.txtDireccion);
		holder.txtCda = (TextView) convertView
				.findViewById(R.id.txtCda);
		holder.txtRepresentante = (TextView) convertView
				.findViewById(R.id.txtRepresentante);
		holder.txtSubCanal = (TextView) convertView
				.findViewById(R.id.txtSubCanal);
		holder.txtRuta = (TextView) convertView
				.findViewById(R.id.txtRuta);
		holder.txtFecCreacion = (TextView) convertView
				.findViewById(R.id.txtFecCreacion);
		holder.txtFecActualizacion = (TextView) convertView.findViewById(R.id.txtFecActualizacion);
		holder.txtFecSuspencion = (TextView) convertView.findViewById(R.id.txtFecSuspencion);
		
		
		holder.imgDireccion = (ImageButton) convertView.findViewById(R.id.btn_buscar);
		holder.imgFicha = (ImageButton) convertView.findViewById(R.id.btn_ficha);
		holder.imgFigura = (ImageButton) convertView.findViewById(R.id.btn_figura);
		holder.imgContacto = (ImageButton) convertView.findViewById(R.id.btn_contacto);
		holder.imgComercial = (ImageButton) convertView.findViewById(R.id.btn_comercial);
		holder.imgEncuesta = (ImageButton) convertView.findViewById(R.id.btn_encuesta);
		holder.imgPlan =  (ImageButton) convertView.findViewById(R.id.btn_plan);
		
		
		convertView.setTag(holder);
	} else {
		// Get the ViewHolder back to get fast access to the TextView
		// and the ImageView.
		holder = (ViewHolder) convertView.getTag();
	}

	holder.txtRazonSocial.setText(cliente.getRazonSocial());
	holder.txtRuc.setText(cliente.getRuc());
	holder.txtDni.setText(cliente.getDni());
	holder.txtCodigo.setText(cliente.getCodigoCliente());
	holder.txtDireccion.setText(cliente.getDireccion());
	holder.txtCda.setText(cliente.getCodigoCda());
	holder.txtRepresentante.setText(cliente.getNombreCliente());
	holder.txtSubCanal.setText(String.format("%s - %s",cliente.getSubCanal(), cliente.getSubCanalDes()));
	holder.txtRuta.setText(cliente.getRuta());
	holder.txtFecCreacion.setText(cliente.getFechaCreacion());
	holder.txtFecActualizacion.setText(cliente.getFechaActualizacion());
	holder.txtFecSuspencion.setText(cliente.getFechaSuspencion());


	
	double lat = cliente.getLatitud();
	double lng = cliente.getLongitud();

	if (lat != 0 && lng != 0) {
		holder.imgDireccion.setVisibility(View.VISIBLE);
	}

	holder.imgDireccion.setOnClickListener(new OnClickListener() {
		ClienteResumenTO clienteTemporal = (ClienteResumenTO) getItem(position);

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent direccionCliente = new Intent(context,DireccionActivity.class);
			direccionCliente.putExtra(DireccionActivity.LATITUD_KEY,clienteTemporal.getLatitud());
			direccionCliente.putExtra(DireccionActivity.LONGITUD_KEY,clienteTemporal.getLongitud());
			direccionCliente.putExtra(DireccionActivity.DIRECCION_KEY,clienteTemporal.getDireccion());
			context.startActivity(direccionCliente);
		}
	});

	holder.imgFicha.setOnClickListener(new OnClickListener() {
		ClienteResumenTO clienteTemporal = (ClienteResumenTO) getItem(position);

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent fichaCliente = new Intent(context,FichaClienteActivity.class);
			fichaCliente.putExtra(FichaClienteActivity.CODIGO_CLIENTE, clienteTemporal.getCodigoCliente());
			
			
			context.startActivity(fichaCliente);
		}
	});

	holder.imgFigura.setOnClickListener(new OnClickListener() {
		ClienteResumenTO clienteTemporal = (ClienteResumenTO) getItem(position);

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent profit = new Intent(context,FiguraComercialActivity.class);
			profit.putExtra(FiguraComercialActivity.CODIGO_CLIENTE_KEY,clienteTemporal.getCodigoCliente());
			context.startActivity(profit);
		}
	});

	holder.imgContacto.setOnClickListener(new OnClickListener() {
				ClienteResumenTO clienteTemporal = (ClienteResumenTO) getItem(position);

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context,MostrarContactoActivity.class);
					profit.putExtra(MostrarContactoActivity.CODIGO_CLIENTE_KEY,clienteTemporal.getCodigoCliente());
					context.startActivity(profit);
				}
			});

	holder.imgComercial.setOnClickListener(new OnClickListener() {
		ClienteResumenTO clienteTemporal = (ClienteResumenTO) getItem(position);

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent profit = new Intent(context,ComercialActivity.class);
			profit.putExtra(ComercialActivity.CODIGO_CLIENTE_KEY,clienteTemporal.getCodigoCliente());
			context.startActivity(profit);
		}
	});

	holder.imgEncuesta.setOnClickListener(new OnClickListener() {
		ClienteResumenTO clienteTemporal = (ClienteResumenTO) getItem(position);
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent consultarEncuestas = new Intent(context,ConsultarEncuestaActivity.class);
			consultarEncuestas.putExtra(ConsultarEncuestaActivity.CODIGO_CLIENTE_KEY, clienteTemporal.getCodigoCliente());
			consultarEncuestas.putExtra(ConsultarEncuestaActivity.CLIENTE_KEY, clienteTemporal.getRazonSocial());
			
			context.startActivity(consultarEncuestas);
		}
	});
	
	holder.imgPlan.setOnClickListener(new OnClickListener() {
		ClienteResumenTO clienteTemporal = (ClienteResumenTO) getItem(position);
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent consultarPlanDesarrollo = new Intent(context,MostrarPlanDesarrolloActivity.class);
			consultarPlanDesarrollo.putExtra(MostrarPlanDesarrolloActivity.CODIGO_CLIENTE, clienteTemporal.getCodigoCliente());
			context.startActivity(consultarPlanDesarrollo);
		}
	});
	return convertView;
}

static class ViewHolder {
	TextView txtRazonSocial;
	TextView txtRuc;
	TextView txtDni;
	TextView txtCodigo;
	TextView txtDireccion;
	TextView txtCda;
	TextView txtRepresentante;
	TextView txtSubCanal;
	TextView txtRuta;
	TextView txtFecCreacion;
	TextView txtFecActualizacion;
	TextView txtFecSuspencion;	
	ImageButton imgDireccion;
	ImageButton imgFicha;
	ImageButton imgFigura;
	ImageButton imgContacto;	
	ImageButton imgComercial;
	ImageButton imgEncuesta;
	ImageButton imgPlan;

}

@Override
public Filter getFilter() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public long getItemId(int position) {
	// TODO Auto-generated method stub
	return position;
}

@Override
public int getCount() {
	// TODO Auto-generated method stub
	// return data.length;
	if (clientes == null) {
		return 0;
	} else {
		return clientes.size();
	}
}

@Override
public Object getItem(int position) {
	// TODO Auto-generated method stub
	return clientes.get(position);
}

}

}
