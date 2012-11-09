package lindley.desarrolloxcliente.activity;

import java.util.Calendar;
import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectFragment;
import roboguice.inject.InjectView;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.AccionTradeTO;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.ws.service.ConsultarCabeceraProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarCompromisoProxy;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import com.google.inject.Inject;

import net.msonic.lib.ActivityUtil;
import net.msonic.lib.sherlock.ListBaseFragment;




//@ContentView(R.layout.compromisoopen_activity)
public class TestTAbItem extends ListBaseFragment {
	
	
	
	public final static String CODIGO_REGISTRO = "codigo_reg";
	
	private static final int ACCION_CERRAR = 1;
	private static final int ACCION_ACTUALIZAR = 2;

	public static final String TIPO_PRESENTACION = "3";
	public static final String TIPO_POSICION = "2";
	public static final String NO = "N";
	public static final String SI = "S";
	
	public static final String NO_DATO = "N0";
	public static final String SI_DATO = "SI";
	
	public static ClienteTO cliente;
	public static MyApplication application;
	List<CompromisoTO> compromisos;
	
	@Inject ConsultarCompromisoProxy consultarCompromisoProxy;
	String codigoRegistro;
	
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	@InjectView(R.id.txtViewCliente) TextView txtViewCliente;
	
		
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 
			 return inflater.inflate(R.layout.compromisoopen_activity, container,false);
		 
		 }
	
	private static int VISTA_CARGADA=0;
	
	
	 @Override public void onActivityCreated(Bundle savedInstanceState) {
         super.onActivityCreated(savedInstanceState);
         //07-22 23:06:43.945: D/RQ(11544): {"CLI":"602","CODR":"4210"}
         application = (MyApplication)getActivity().getApplicationContext();
	 	 cliente = application.getClienteTO();
	 	 codigoRegistro = getArguments().getString(CODIGO_REGISTRO);
	 	txtViewCliente.setText(String.format("%s - %s", cliente.codigo ,cliente.nombre));
	 	
         
         if(VISTA_CARGADA==0){
	 		VISTA_CARGADA=1;
	         processAsync();
         }
         
	 }
	
	 
	 




	@Override
	protected void process()  throws Exception{
    	consultarCompromisoProxy.setCodigoCliente(cliente.codigo);
    	consultarCompromisoProxy.setCodigoRegistro(codigoRegistro);
    	consultarCompromisoProxy.execute();
	}

    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarCompromisoProxy.isExito();
		if (isExito) {
			int status = consultarCompromisoProxy.getResponse().getStatus();
			if (status == 0) {
				compromisos = consultarCompromisoProxy.getResponse().getListaCompromiso();
				
				if(compromisos.size()>0)
				{
					if(application.dia == null)
					{
						final Calendar c = Calendar.getInstance();
						txtViewFecha.setText(ActivityUtil.pad(c.get(Calendar.DAY_OF_MONTH)) + "/" + ActivityUtil.pad((c.get(Calendar.MONTH) + 1)) + "/" + c.get(Calendar.YEAR));
					}
					else{
						txtViewFecha.setText(application.dia+ "/" + application.mes + "/" + application.anio);
					}
						
				}
				application.openAdapter2 = new EfficientAdapter(getActivity(), compromisos);
				setListAdapter(application.openAdapter2);
			}
			else  {
				showToast(consultarCompromisoProxy.getResponse().getDescripcion());
			}
		}
		else{
			processError();
		}
		super.processOk();
	}



	 
	 
	 
	 
	 
		private static String pad(int c) {
			if (c >= 10)
				return String.valueOf(c);
			else
				return "0" + String.valueOf(c);
		}
	 
	 
	 public static class EfficientAdapter extends BaseAdapter{
	    	
  	   public static EditText txtFecha;
  	   public static CompromisoTO compromisoTO;
  	 
  		 
  		 
  	    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
  			
  			@Override
  			public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
  				// TODO Auto-generated method stub

  					EfficientAdapter.txtFecha.setText(String.valueOf(pad(dayOfMonth)) + "/"+ String.valueOf(pad(monthOfYear+1)) + "/" + String.valueOf(year));
  		    	  if(EfficientAdapter.compromisoTO!=null){
  		    		  EfficientAdapter.compromisoTO.fechaCompromiso = String.valueOf(year) + String.valueOf(pad(monthOfYear+1)) + String.valueOf(pad(dayOfMonth));
  		    	  }
  			}
  		};
  		
	    private LayoutInflater mInflater;
	    private Context context;
	    public List<CompromisoTO> detalles;
	    
	    public EfficientAdapter(Context context, List<CompromisoTO> valores) {
		      // Cache the LayoutInflate to avoid asking for a new one each time.
		      mInflater = LayoutInflater.from(context);
		      this.context = context;
		      this.detalles = valores;
		    }
	    

	    /**
	     * Make a view to hold each row.
	     * 
	     * @see android.widget.ListAdapter#getView(int, android.view.View,
	     *      android.view.ViewGroup)
	     */
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	final CompromisoTO compromiso = (CompromisoTO) getItem(position);
	    	final ViewHolder holder;

	      //if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.compromisoopen_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	        	    		    	
	        holder.txViewPuntos = (TextView) convertView.findViewById(R.id.txViewPuntos);	
	        holder.btnProfit = (ImageButton) convertView.findViewById(R.id.btnProfit);	   
	        holder.txViewLegacy = (TextView) convertView.findViewById(R.id.txViewLegacy);
	        holder.txViewPro = (TextView) convertView.findViewById(R.id.txViewPro); 
	        
	        holder.cboConcrecion = (TextView) convertView.findViewById(R.id.cboConcrecion);
	        holder.cboConcrecionCmp = (Spinner) convertView.findViewById(R.id.cboConcrecionCmp);
	        
	        holder.txViewSOVI =  (EditText) convertView.findViewById(R.id.txViewSOVI);
	        holder.txViewSOVICmp =  (EditText) convertView.findViewById(R.id.txViewSOVICmp);
	        
	        holder.cboCumPrecio =  (Spinner) convertView.findViewById(R.id.cboCumPrecio);
	        holder.cboCumPrecioCmp =  (Spinner) convertView.findViewById(R.id.cboCumPrecioCmp);
	        
	        holder.txViewSabores = (TextView) convertView.findViewById(R.id.txViewSabores);
	        holder.cboSaboresCmp =  (Spinner) convertView.findViewById(R.id.cboSaboresCmp);	        
	    		    	
	    	holder.txViewAccTrade = (Spinner) convertView.findViewById(R.id.txViewAccTrade);	
	    	holder.txEditFecha = (EditText) convertView.findViewById(R.id.txEditFecha);	    	
	    	holder.btnFecha = (ImageButton) convertView.findViewById(R.id.btnFecha);
	    	
	        convertView.setTag(holder);
	      
	      holder.txViewPuntos.setText(compromiso.puntosSugeridos);
	      
	      holder.btnProfit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent profit = new Intent(context, VerProfit_Activity.class);
					profit.putExtra(VerProfit_Activity.ANIO, "");
					profit.putExtra(VerProfit_Activity.MES, "");
					profit.putExtra(VerProfit_Activity.CLIENTE, cliente.codigo);
					profit.putExtra(VerProfit_Activity.ARTICULO, compromiso.codigoProducto);
					profit.putExtra(VerProfit_Activity.NOMBRE_ARTICULO, compromiso.descripcionProducto);
					context.startActivity(profit);
				}
			});
	      
	      holder.txViewPro.setText("  " + compromiso.descripcionProducto);
	      holder.txViewLegacy.setText(compromiso.codigoLegacy);
	      
	      if(compromiso.concrecion.equalsIgnoreCase(SI))
	    	  holder.cboConcrecion.setText(SI_DATO);
	      else
	    	  holder.cboConcrecion.setText(NO_DATO);
	      
	      holder.cboConcrecionCmp.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if(arg2==0){
						compromiso.concrecionActual = NO;
					}else{
						compromiso.concrecionActual = SI;
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
	      
	      
	      holder.txViewSOVI.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				compromiso.sovi = holder.txViewSOVI.getText().toString();
			}
		});
	      
	      holder.txViewSOVI.setText(compromiso.sovi);
	      holder.txViewSOVICmp.setText(compromiso.soviActual);
	      
	      holder.txViewSOVICmp.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					compromiso.soviActual = holder.txViewSOVICmp.getText().toString();
				}
			});
	      	      
	      
		  holder.cboCumPrecio.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(arg2==0){
					compromiso.cumplePrecio = NO;
				}else{
					compromiso.cumplePrecio = SI;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		  
		  holder.cboCumPrecioCmp.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if(arg2==0){
						compromiso.cumplePrecioActual = NO;
					}else{
						compromiso.cumplePrecioActual = SI;
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		  
		  holder.txViewSabores.setText(compromiso.numeroSabores);
		  
		 
		  
		  holder.cboSaboresCmp.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if(arg2==0){
						compromiso.numeroSaboresActual = 2;
					} else if(arg2 == 1){
						compromiso.numeroSaboresActual = 3;
					} else if(arg2 == 2){
						compromiso.numeroSaboresActual = 4;
					}
					else{
						compromiso.numeroSaboresActual = 0;
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		  
		  
	      ArrayAdapter<CharSequence> adap = ArrayAdapter.createFromResource(application.getApplicationContext(),R.array.confirmacion,android.R.layout.simple_spinner_item);
		  adap.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
	      holder.cboCumPrecio.setAdapter(adap);
	      holder.cboCumPrecioCmp.setAdapter(adap);
	      holder.cboConcrecionCmp.setAdapter(adap);
	      
	      ArrayAdapter<CharSequence> adapSabores = ArrayAdapter.createFromResource(application.getApplicationContext(),R.array.numero_sabores,android.R.layout.simple_spinner_item);
	      adapSabores.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		  holder.cboSaboresCmp.setAdapter(adapSabores);
		  
		  if(compromiso.concrecionActual.equalsIgnoreCase(NO))
	    	  holder.cboConcrecionCmp.setSelection(0);
	      else
	    	  holder.cboConcrecionCmp.setSelection(1);
	      	      
		  if(compromiso.numeroSaboresActual == 2)
			  holder.cboSaboresCmp.setSelection(0);
		  else  if(compromiso.numeroSaboresActual == 3)
			  holder.cboSaboresCmp.setSelection(1);
		  else
			  holder.cboSaboresCmp.setSelection(2);
		  
		  if(compromiso.cumplePrecio.equals(NO))holder.cboCumPrecio.setSelection(0);
		  else holder.cboCumPrecio.setSelection(1);
		  
		  if(compromiso.cumplePrecioActual.equals(SI))
			  holder.cboCumPrecioCmp.setSelection(1);
		  else 
			  holder.cboCumPrecioCmp.setSelection(0);
		  
		  
		  //holder.txViewAccTrade.setAdapter(application.getAdapterAccionTrade(compromiso.listaAccionesTrade));

	      if(holder.txViewAccTrade.getCount() > 1)
	    	  holder.txViewAccTrade.setSelection(1);
	      
	      holder.txViewAccTrade.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if(arg2 > 0){
						compromiso.descAccionTrade = (((AccionTradeTO)arg0.getSelectedItem()).getDescripcion());
						compromiso.codigoAccionTrade = (((AccionTradeTO)arg0.getSelectedItem()).getCodigo());
					}else{
						compromiso.descAccionTrade  = " ";
						compromiso.codigoAccionTrade = "0";
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					compromiso.descAccionTrade  = " ";
					compromiso.codigoAccionTrade = "0";
				}
				
			});
	      
			String fecha = compromiso.fechaCompromiso;
			int anio;
			int mes;
			int dia;
			final Calendar c = Calendar.getInstance();
			if (fecha.length() >= 7) {
				anio = Integer.parseInt(fecha.substring(0, 4));
				mes = Integer.parseInt(fecha.substring(4, 6)) - 1;
				dia = Integer.parseInt(fecha.substring(6));
				c.set(anio, mes, dia);
				if (dia >= 30 && mes == 1)
					dia = c.get(Calendar.DAY_OF_MONTH);
				else if (dia >= 30)
					dia = c.get(Calendar.DAY_OF_MONTH);
				holder.txEditFecha.setText(ActivityUtil.pad(dia)+"/"+ActivityUtil.pad(mes+1)+"/"+anio);
			}
	      
	      //holder.txViewAccTrade.setText(compromiso.getDescripcionAccion());
	     	      
	      holder.btnFecha = (ImageButton)convertView.findViewById(R.id.btnFecha);
	    	
	    	holder.btnFecha.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					   int anio;    
			    	    int mes;  
			    	    int dia;
			    	   
					   String fecha = compromiso.fechaCompromiso;
					   final Calendar c = Calendar.getInstance();
					      if(fecha.length() >= 7)
					      {
					    	  anio =  Integer.parseInt(fecha.substring(0, 4));
					    	  mes  =  Integer.parseInt(fecha.substring(4, 6))-1;
					    	  dia  =  Integer.parseInt(fecha.substring(6));
					    	  c.set(anio, mes, dia);					    	  
					    	  if (dia>=30 && mes == 1)
					    		  dia = c.get(Calendar.DAY_OF_MONTH);
					    	  else if (dia>=30)
					    		  dia = c.get(Calendar.DAY_OF_MONTH);
					    	  
					      }else{					    	          
					    	  anio = c.get(Calendar.YEAR);        
					    	  mes = c.get(Calendar.MONTH);        
					    	  dia = c.get(Calendar.DAY_OF_MONTH); 
					      }
					      
					      
					      EfficientAdapter.txtFecha = holder.txEditFecha;
					      EfficientAdapter.compromisoTO = compromiso;
					
					      DatePickerDialog p = new DatePickerDialog(context, dateSetListener, anio,mes--, dia);
					      p.show();
				}
			});	    	
	    
	      
	      
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewPuntos;
	    	ImageButton btnProfit;
	    	TextView txViewLegacy;
	    	TextView txViewPro;
	    	
	    	TextView cboConcrecion;
	    	Spinner cboConcrecionCmp;
	    	
	    	EditText txViewSOVI;
	    	EditText txViewSOVICmp;
	    	
	    	Spinner cboCumPrecio;
	    	Spinner cboCumPrecioCmp;
	    	
	    	TextView txViewSabores;
	    	Spinner cboSaboresCmp;
	    	   	    	
	    	Spinner txViewAccTrade;    	
	    	
	    	EditText txEditFecha;
	    	ImageButton btnFecha;
	    	
	    }
	    
	   
	    @Override
	    public long getItemId(int position) {
	      // TODO Auto-generated method stub
	      return position;
	    }

	    @Override
	    public int getCount() {
	      // TODO Auto-generated method stub
	      //return data.length;
	    	if(detalles==null){
	    		return 0;
	    	}else{
	    		return detalles.size();
	    	}
	    }

	    @Override
	    public Object getItem(int position) {
	      // TODO Auto-generated method stub
	    	return detalles.get(position);
	    }

	  }
	 
	 
	
}
