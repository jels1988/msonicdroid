package pe.lindley.ficha.activity;

import java.util.List;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ViewFlipper;
import pe.lindley.activity.R;
import pe.lindley.activity.RTMApplication;
import pe.lindley.ficha.to.EncuestaTO;
import pe.lindley.ficha.to.PreguntaTO;
import pe.lindley.ficha.to.SeccionTO;
import pe.lindley.ficha.to.SubSeccionTO;
import pe.lindley.ficha.ws.service.ActualizarEncuestaProxy;
import pe.lindley.ficha.ws.service.GuardarEncuestaProxy;
import pe.lindley.ficha.ws.service.ObtenerEncuestaProxy;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class FichaEncuestaActivity extends ActivityBase {

	public static final String CODIGO_CLIENTE_KEY="codigo_cliente";
	public static final String CLIENTE_KEY="cliente_descripcion";
	public static final String ENCUESTA_KEY="codigo_encuesta";
	public static final String TIPO_ENCUESTA="encuesta_tipo";
	public static final String ESTADO_ENCUESTA="estado";
	
	public static final int GRABAR_ENCUESTA = 1;
	public static final int ACTUALIZAR_ENCUESTA = 2;
	
	@InjectExtra(ENCUESTA_KEY) String	encuesta_codigo;
	@InjectExtra(CODIGO_CLIENTE_KEY) String	cliente_codigo;
	@InjectExtra(CLIENTE_KEY) 		String	cliente_descripcion;
	@InjectExtra(TIPO_ENCUESTA) 	static	String	tipo_encuesta;
	
	@InjectResource(R.string.ficha_guardar_encuesta_ok) String guardar_encuesta_ok;
	@InjectResource(R.string.ficha_actualizar_encuesta_ok) String actualizar_encuesta_ok;
	
	@Inject ObtenerEncuestaProxy obtenerEncuestaProxy;
	@Inject GuardarEncuestaProxy guardarEncuestaProxy;
	@Inject ActualizarEncuestaProxy actualizarEncuestaProxy;
	
	@InjectView(R.id.viewFlipper1) ViewFlipper viewFlipper;
	@InjectView(R.id.actionBar)  		ActionBar 	mActionBar;
	@InjectView(R.id.btnGrabar) Button btnGrabar;
	@InjectView(R.id.btnActualizar) Button btnActualizar;
	
	
	EncuestaTO encuestaTO;
	public static String estado_encuesta = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		estado_encuesta = intent.getStringExtra(ESTADO_ENCUESTA);
		
		System.out.println(tipo_encuesta);
		setContentView(R.layout.ficha_encuesta_activity);
		
		mActionBar.setTitle(R.string.consultar_encuesta_title);
	    mActionBar.setHomeLogo(R.drawable.header_logo);
		mActionBar.setSubTitle(cliente_descripcion);	
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		processAsync();
	}
	
	public void btnPrevious_onclick(View v){
		viewFlipper.showPrevious();
		
		if(viewFlipper.getChildCount()-1==viewFlipper.getDisplayedChild()){
			if(encuestaTO.getCodigoEncuesta()==null)
				btnGrabar.setVisibility(View.VISIBLE);
			else
				btnActualizar.setVisibility(View.VISIBLE);
		}else{
			if(encuestaTO.getCodigoEncuesta()==null)
				btnGrabar.setVisibility(View.GONE);
			else
				btnActualizar.setVisibility(View.GONE);
		}
		
		if(estado_encuesta.equals("2"))
		{
			btnActualizar.setVisibility(View.GONE);
			btnGrabar.setVisibility(View.GONE);
		}
	}
	
	public void btnNext_onclick(View v) {
		viewFlipper.showNext();
		
		if(viewFlipper.getChildCount()-1==viewFlipper.getDisplayedChild()){
			if(encuestaTO.getCodigoEncuesta()==null)
				btnGrabar.setVisibility(View.VISIBLE);
			else
				btnActualizar.setVisibility(View.VISIBLE);
		}else{
			if(encuestaTO.getCodigoEncuesta()==null)
				btnGrabar.setVisibility(View.GONE);
			else
				btnActualizar.setVisibility(View.GONE);
		}
		if(estado_encuesta.equals("2"))
		{
			btnActualizar.setVisibility(View.GONE);
			btnGrabar.setVisibility(View.GONE);
		}
	}
	
	public void btnGrabar_onclick(View view)
	{
		processAsync(GRABAR_ENCUESTA);
	}
	
	public void btnActualizar_onclick(View view)
	{
		processAsync(ACTUALIZAR_ENCUESTA);
	}
	
	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		if(accion == GRABAR_ENCUESTA)
		{
			RTMApplication application = (RTMApplication)getApplicationContext();
			UsuarioTO usuario = application.getUsuarioTO();
			
			guardarEncuestaProxy.setCodigo(cliente_codigo);
			guardarEncuestaProxy.setCodigoEncuesta(encuestaTO.getCodigoEncuesta());
			guardarEncuestaProxy.setDetalleSeccion(encuestaTO.getDetalleSeccion());
			guardarEncuestaProxy.setVersionEncuesta(encuestaTO.getVersionEncuesta());
			guardarEncuestaProxy.setUsuario(usuario.getCodigoSap());
			guardarEncuestaProxy.execute();
		}
		if(accion == ACTUALIZAR_ENCUESTA)
		{
			RTMApplication application = (RTMApplication)getApplicationContext();
			UsuarioTO usuario = application.getUsuarioTO();
			
			actualizarEncuestaProxy.setCodigo(cliente_codigo);
			actualizarEncuestaProxy.setCodigoEncuesta(encuestaTO.getCodigoEncuesta());
			actualizarEncuestaProxy.setDetalleSeccion(encuestaTO.getDetalleSeccion());
			actualizarEncuestaProxy.setUsuario(usuario.getCodigoSap());
			actualizarEncuestaProxy.execute();
		}
		super.process(accion);
		return;
	}

	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion == GRABAR_ENCUESTA)
		{
			boolean isExito = guardarEncuestaProxy.isExito();
			if (isExito) {
				int status = guardarEncuestaProxy.getResponse().getStatus();
				if (status == 0) {
					showToast(guardar_encuesta_ok);
				}
				else  {
					showToast(guardarEncuestaProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError(accion);
			}
		}
		if(accion == ACTUALIZAR_ENCUESTA)
		{
			boolean isExito = actualizarEncuestaProxy.isExito();
			if (isExito) {
				int status = actualizarEncuestaProxy.getResponse().getStatus();
				if (status == 0) {
					showToast(actualizar_encuesta_ok);
				}
				else  {
					showToast(actualizarEncuestaProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError(accion);
			}
		}
		super.processOk(accion);
		finish();
	}

	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError(accion);
		showToast(error_generico_process);
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		obtenerEncuestaProxy.setCodEncuesta(encuesta_codigo);
		obtenerEncuestaProxy.setOpcion(tipo_encuesta);
		obtenerEncuestaProxy.execute();
	}

	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		encuestaTO = obtenerEncuestaProxy.getResponse().getEncuesta();
	
		LayoutInflater mInflater = LayoutInflater.from(getApplicationContext());
		
		for (SeccionTO seccion : encuestaTO.getDetalleSeccion()) {
			View convertView = mInflater.inflate(R.layout.ficha_encuesta_activity_content,null);
			
			TextView txtPrueba = (TextView)convertView.findViewById(R.id.txtPrueba);
			ListView lstSeccion = (ListView)convertView.findViewById(R.id.lstSecciones);
			//lstSeccion.setFocusable(false);
//lstSeccion.setDrawSelectorOnTop(false);
			txtPrueba.setText(seccion.getDescripcionSeccion());
			
			EfficientAdapter f = new EfficientAdapter(getApplicationContext(), seccion.getDetalleSubseccion());
			lstSeccion.setAdapter(f);
			//lstSeccion.setFocusable(false);
			lstSeccion.setDrawSelectorOnTop(false);
			viewFlipper.addView(convertView);
		}
		
		
		
		
		
		super.processOk();
	}

	


public static class EfficientAdapter extends BaseAdapter{

	
	/* public  void setListViewHeightBasedOnChildren(ListView listView) {
	        ListAdapter listAdapter = listView.getAdapter();
	        if (listAdapter == null) {
	            // pre-condition
	            return;
	        }

	        //int totalHeight = 0;
	        //int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
	       /* for (int i = 0; i < listAdapter.getCount(); i++) {
	            View listItem = listAdapter.getView(i, null, listView);
	            listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
	            totalHeight += listItem.getMeasuredHeight();
	        }*/

	      /*  ViewGroup.LayoutParams params = listView.getLayoutParams();
	        //params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	        params.height = listView.getDividerHeight() * (listAdapter.getCount() - 1) + (130 * (listAdapter.getCount() - 1));
	        listView.setLayoutParams(params);
	        listView.requestLayout();

	    }
	 */
		
		private List<SubSeccionTO> subSecciones;
		private LayoutInflater mInflater;
		private Context context;
		
		public EfficientAdapter(Context context,List<SubSeccionTO> subSecciones){
			mInflater = LayoutInflater.from(context);
			this.context = context;
			this.subSecciones = subSecciones;
		}
				
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if(subSecciones==null){
				return 0;
			}else{
				return subSecciones.size();
			}
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return subSecciones.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView,ViewGroup parent) {
			// TODO Auto-generated method stub
			final ViewHolder holder;
			final SubSeccionTO subSeccionTO = (SubSeccionTO)getItem(position);
			
			//if (convertView == null) {
				convertView = mInflater.inflate(R.layout.ficha_encuesta_content,null);
				holder = new ViewHolder();
				holder.txtPregunta = (TextView) convertView.findViewById(R.id.txtPregunta);	
				holder.lstOpciones = (ListView) convertView.findViewById(R.id.lstOpciones);
				//holder.lstOpciones = (ExpandableListView) convertView.findViewById(R.id.explistOpc);
				holder.chkFlag = (CheckBox) convertView.findViewById(R.id.chkFlag);
				holder.txtRp1 = (EditText) convertView.findViewById(R.id.txtMinimo);
				holder.txtRp2 = (EditText) convertView.findViewById(R.id.txtMaximo);
				
				holder.txtSimple = (EditText)convertView.findViewById(R.id.txtSimple);
				holder.trSimple = (TableRow)convertView.findViewById(R.id.trSimple);
				holder.trFlag = (TableRow)convertView.findViewById(R.id.trFlag);
				holder.trRango = (TableRow)convertView.findViewById(R.id.trRango);
				holder.trLista = (TableRow)convertView.findViewById(R.id.trLista);
				convertView.setTag(holder);
				
			/*} else {
				holder = (ViewHolder) convertView.getTag();
			}*/
			
			holder.txtPregunta.setText(subSeccionTO.getDescrSubSeccion());
			
			//SETEAR QUE TIPO DE VISTA VAMOS A CARGAR
			final String tipo = subSeccionTO.getTipoValor();
			
			holder.trSimple.setVisibility(View.GONE);
			holder.trLista.setVisibility(View.GONE);
			holder.trFlag.setVisibility(View.GONE);
			holder.trRango.setVisibility(View.GONE);
			holder.trLista.setVisibility(View.GONE);
			
			if ((tipo.compareTo(SubSeccionTO.TIPO_NUMERICO_SIMPLE)==0) || (tipo.compareTo(SubSeccionTO.TIPO_TEXTO_SIMPLE)==0)){
				
				holder.trSimple.setVisibility(View.VISIBLE);
				if(tipo_encuesta.trim().equals("2"))
					holder.txtSimple.setText(subSeccionTO.getRespuesta1());
				if(tipo.compareTo(SubSeccionTO.TIPO_NUMERICO_SIMPLE)==0)
					holder.txtSimple.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
				holder.txtSimple.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						subSeccionTO.setRespuesta1(s.toString());
					}
				});
				
				if(estado_encuesta.equals("2"))
				{
					holder.txtSimple.setKeyListener(null);
				}
			}else if ((tipo.compareTo(SubSeccionTO.TIPO_LISTA_MULTIPLE)==0) || (tipo.compareTo(SubSeccionTO.TIPO_LISTA_SIMPLE)==0)){
				
				holder.trLista.setVisibility(View.VISIBLE);
				int sizeArreglo = subSeccionTO.getDetallePreguntas().size();
				String[] valores = new String[sizeArreglo];
				boolean seleccionados[] = new boolean[sizeArreglo];
				
				int i=0;
				
				for(PreguntaTO  pregunta : subSeccionTO.getDetallePreguntas() ){
					
					seleccionados[i]=pregunta.isSeleccionado();					 
					valores[i]=pregunta.getPregunta();
					i++;
				}
				
				ArrayAdapter<String> adapter = null;
				
				//CARGAR LOS VALORES EN LOS CONTROLES
				
				if(tipo.compareTo(SubSeccionTO.TIPO_LISTA_MULTIPLE)==0){
					adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_multiple_choice, valores);
					holder.lstOpciones.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
				}else{
					adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_single_choice, valores);
					holder.lstOpciones.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
				}
				
				holder.lstOpciones.setAdapter(adapter);
				
				if(tipo.compareTo(SubSeccionTO.TIPO_LISTA_MULTIPLE)==0){
					for (int j = 0; j < i; j++) {
						holder.lstOpciones.setItemChecked(j, seleccionados[j]);
					}
				}else{
					for (int j = 0; j < i; j++) {
						if(seleccionados[j]){
							holder.lstOpciones.setItemChecked(j, true);
						}
					}
				}
				
				//RECUPERAR LOS VALORES DE LOS CONTROLES
				
				
				holder.lstOpciones.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
						// TODO Auto-generated method stub
						if(tipo.compareTo(SubSeccionTO.TIPO_LISTA_MULTIPLE)==0){
							CheckedTextView chk = (CheckedTextView)arg1;
							
							if(!chk.isChecked()){
								subSeccionTO.getDetallePreguntas().get(arg2).setSeleccionado(true);
							}else{
								subSeccionTO.getDetallePreguntas().get(arg2).setSeleccionado(false);
							}
						}else{
							for (PreguntaTO p : subSeccionTO.getDetallePreguntas()) {
								p.setSeleccionado(false);
							}
							subSeccionTO.getDetallePreguntas().get(arg2).setSeleccionado(true);
						}
					}
					
				});
		        
		        holder.lstOpciones.setMinimumHeight(sizeArreglo * 35);
				holder.trLista.setMinimumHeight(sizeArreglo * 35);
				
				if(estado_encuesta.equals("2"))
				{
					holder.lstOpciones.setEnabled(false);
				}
				
			}else if ((tipo.compareTo(SubSeccionTO.TIPO_FLAG)==0)){
				holder.chkFlag.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						subSeccionTO.setFlag(isChecked);
					}
				});
				
				holder.trFlag.setVisibility(View.VISIBLE);
				if(tipo_encuesta.trim().equals("2"))
					if(subSeccionTO.getRespuesta1().equals("1"))
					{
						holder.chkFlag.setChecked(true);
					}
				if(estado_encuesta.equals("2"))
				{
					holder.chkFlag.setEnabled(false);
				}
				
			}else if ((tipo.compareTo(SubSeccionTO.TIPO_RANGO_CARACTERES)==0) || (tipo.compareTo(SubSeccionTO.TIPO_RANGO_NUMERICOS)==0 || (tipo.compareTo(SubSeccionTO.TIPO_NUMERICO_CARACTER)==0) || (tipo.compareTo(SubSeccionTO.TIPO_CARACTER_NUMERICO)==0))){
				holder.trRango.setVisibility(View.VISIBLE);
				if(tipo_encuesta.trim().equals("2"))
				{
					holder.txtRp1.setText(subSeccionTO.getRespuesta1());
					holder.txtRp2.setText(subSeccionTO.getRespuesta2());
				}
				if(tipo.compareTo(SubSeccionTO.TIPO_RANGO_CARACTERES)==0)
				{
					holder.txtRp1.setInputType(InputType.TYPE_CLASS_TEXT);
					holder.txtRp2.setInputType(InputType.TYPE_CLASS_TEXT);
				}
				else if(tipo.compareTo(SubSeccionTO.TIPO_NUMERICO_CARACTER)==0)
				{
					holder.txtRp1.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
					holder.txtRp2.setInputType(InputType.TYPE_CLASS_TEXT);
					
					int maxLengthRp1 = 10;  
					InputFilter[] FilterArray = new InputFilter[1];  
					FilterArray[0] = new InputFilter.LengthFilter(maxLengthRp1);  
					holder.txtRp1.setFilters(FilterArray);  
					
					int maxLengthRp2 = 50;  
					InputFilter[] FilterArrayRp2 = new InputFilter[1];  
					FilterArrayRp2[0] = new InputFilter.LengthFilter(maxLengthRp2);  
					holder.txtRp1.setFilters(FilterArrayRp2);  
				}
				else if(tipo.compareTo(SubSeccionTO.TIPO_CARACTER_NUMERICO)==0)
				{
					holder.txtRp1.setInputType(InputType.TYPE_CLASS_TEXT);
					holder.txtRp2.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
					
					int maxLengthRp1 = 10;  
					InputFilter[] FilterArray = new InputFilter[1];  
					FilterArray[0] = new InputFilter.LengthFilter(maxLengthRp1);  
					holder.txtRp2.setFilters(FilterArray);  
					
					int maxLengthRp2 = 50;  
					InputFilter[] FilterArrayRp2 = new InputFilter[1];  
					FilterArrayRp2[0] = new InputFilter.LengthFilter(maxLengthRp2);  
					holder.txtRp1.setFilters(FilterArrayRp2);  
				}
				else if(tipo.compareTo(SubSeccionTO.TIPO_NUMERICO_NUMERICO)==0)
				{
					holder.txtRp1.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
					holder.txtRp2.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
					
					int maxLengthRp1 = 10;  
					InputFilter[] FilterArray = new InputFilter[1];  
					FilterArray[0] = new InputFilter.LengthFilter(maxLengthRp1);  
					holder.txtRp2.setFilters(FilterArray);  
					
					int maxLengthRp2 = 50;  
					InputFilter[] FilterArrayRp2 = new InputFilter[1];  
					FilterArrayRp2[0] = new InputFilter.LengthFilter(maxLengthRp2);  
					holder.txtRp1.setFilters(FilterArrayRp2);  
				}
				
				holder.txtRp1.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						subSeccionTO.setRespuesta1(s.toString());
					}
				});
				holder.txtRp2.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						subSeccionTO.setRespuesta2(s.toString());
					}
				});
				
				if(estado_encuesta.equals("2"))
				{
					holder.txtRp1.setKeyListener(null);
					holder.txtRp2.setKeyListener(null);
				}
			}
			
			//holder.lstOpciones.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
			
			//setListViewHeightBasedOnChildren(holder.lstOpciones);
			
			return convertView;		
		}
		
		
		static class ViewHolder {
			TextView txtPregunta;			
			EditText txtSimple;
			CheckBox chkFlag;			
			EditText txtRp1;
			EditText txtRp2;		
			ListView lstOpciones;			
			TableRow trSimple;
			TableRow trFlag;
			TableRow trRango;
			TableRow trLista;			
		}
	}
}
