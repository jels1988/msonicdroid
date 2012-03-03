package lindley.desarrolloxcliente.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.ws.service.ActualizarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.CerrarCompromisoProxy;
import lindley.desarrolloxcliente.ws.service.ConsultarCompromisoProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ListActivityBase;

public class CompromisoOpen_Activity extends ListActivityBase {

	public final static String CODIGO_REGISTRO = "codigo_reg";

	private static final int ACCION_CERRAR = 1;

	private static final int ACCION_ACTUALIZAR = 2;

	@InjectExtra(CODIGO_REGISTRO) String codigoRegistro;
	@InjectView(R.id.actionBar)   ActionBar 	mActionBar;
	@Inject ConsultarCompromisoProxy consultarCompromisoProxy;
	@Inject CerrarCompromisoProxy cerrarCompromisoProxy;
	@Inject ActualizarCompromisoProxy actualizarCompromisoProxy;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	private EfficientAdapter adap;
	ClienteTO cliente;
	public static MyApplication application;
	List<CompromisoTO> compromisos;
	private static final int DATE_DIALOG_ID = 0;
	static int anio;    
	static int mes;  
	static int dia;
	    
	
	
	/** Called when the activity is first created. */
    @Override 
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
    	compromisos = new ArrayList<CompromisoTO>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compromisoopen_activity);        
        mActionBar.setTitle(R.string.compromiso_activity_title);
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
        mActionBar.setSubTitle(cliente.getNombre());
        mActionBar.setHomeLogo(R.drawable.header_logo);
        
        
        
        processAsync();
    }
    
    public EditText txtFecha;
    
    @Override
    protected Dialog onCreateDialog(int id) {  
        switch (id) {    
        case DATE_DIALOG_ID:     
        	
            return new DatePickerDialog(this,dateSetListener,anio, mes,dia );    
        }    
        return null;
    }
    
	private static String pad(int c) {
        if (c >= 10)
                return String.valueOf(c);
            else
                    return "0" + String.valueOf(c);
            }
	
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
			// TODO Auto-generated method stub

	    	  txtFecha.setText(String.valueOf(pad(dayOfMonth)) + "/"+ String.valueOf(pad(monthOfYear+1)) + "/" + String.valueOf(year));
		}
	};
    
    public void btnCerrar_click(View view)
    {
    	processAsync(ACCION_CERRAR);
    }

    public void btnGuardar_click(View view)
    {
    	processAsync(ACCION_ACTUALIZAR);
    }
    
    @Override
	protected void process(int accion) {
    	if(accion == ACCION_CERRAR)
    	{
    		cerrarCompromisoProxy.setCodigoCabecera(codigoRegistro);
    		cerrarCompromisoProxy.execute();
    	}
    	else if(accion == ACCION_ACTUALIZAR)
    	{
    		actualizarCompromisoProxy.setCompromisos(compromisos);
    		actualizarCompromisoProxy.execute();
    	}
    		
	}
    
    @Override
	protected void process() {
    	consultarCompromisoProxy.setCodigoCliente(cliente.getCodigo());
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
				compromisos = consultarCompromisoProxy
						.getResponse().getListaCompromiso();
				if(compromisos.size()>0)
					txtViewFecha.setText(compromisos.get(0).getFecha());
				adap = new EfficientAdapter(this, compromisos);
				setListAdapter(adap);
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
    
    @Override
   	protected void processOk(int accion) {
   		// TODO Auto-generated method stub
    	if(accion == ACCION_CERRAR)
    	{
    		boolean isExito = cerrarCompromisoProxy.isExito();
       		if (isExito) {
       			int status = cerrarCompromisoProxy.getResponse().getStatus();
       			if (status == 0) {
       				showToast("Los registros se cerrarón satisfactoriamente.");
       				finish();
       			}
       			else  {
       				showToast(cerrarCompromisoProxy.getResponse().getDescripcion());
       			}
       		}
       		else{
       			processError();
       		}
    	}
    	else if(accion == ACCION_ACTUALIZAR)
    	{
    		boolean isExito = actualizarCompromisoProxy.isExito();
       		if (isExito) {
       			int status = actualizarCompromisoProxy.getResponse().getStatus();
       			if (status == 0) {
       				showToast("Los registros se actualizaron correctamente.");
       				finish();
       			}
       			else  {
       				showToast(actualizarCompromisoProxy.getResponse().getDescripcion());
       			}
       		}
       		else{
       			processError();
       		}	
    	}   		
   		super.processOk();
   	} 
    
    @Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
    
    @Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError();
		showToast(error_generico_process);
	}
    
    public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private Context context;
	    private List<CompromisoTO> detalles;
	    
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

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.compromisoopen_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        	        	    		    	
	        holder.txViewPro = (TextView) convertView.findViewById(R.id.txViewPro); 
	        holder.cboConcrecion = (Spinner) convertView.findViewById(R.id.cboConcrecion);
	        holder.chkConcrecion = (CheckBox) convertView.findViewById(R.id.chkConcrecion);
	        holder.txViewSOVI =  (EditText) convertView.findViewById(R.id.txViewSOVI);
	        holder.chkSOVI = (CheckBox) convertView.findViewById(R.id.chkSOVI);
	        holder.cboCumPrecio =  (Spinner) convertView.findViewById(R.id.cboCumPrecio);
	        holder.chkPrecio = (CheckBox) convertView.findViewById(R.id.chkPrecio);
	        holder.txViewSabores = (EditText) convertView.findViewById(R.id.txViewSabores);
	        holder.chkSabores = (CheckBox) convertView.findViewById(R.id.chkSabores);
	        
	        
	    	holder.txViewPuntos = (TextView) convertView.findViewById(R.id.txViewPuntos);		    	
	    	holder.txViewAccTrade = (TextView) convertView.findViewById(R.id.txViewAccTrade);	          	
	    	holder.txViewFecha = (EditText) convertView.findViewById(R.id.txViewFecha);	    	
	    	holder.btnFecha = (ImageButton) convertView.findViewById(R.id.btnFecha);
	    	holder.chkCumplio = (CheckBox) convertView.findViewById(R.id.chkCumplio);
	    	holder.txViewProfit = (TextView) convertView.findViewById(R.id.txViewProfit);
	        
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      holder.txViewPro.setText(compromiso.getDescripcionProducto());
	      //holder.txViewConcrecion.setText(compromiso.getConcrecion());
	      ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter
					.createFromResource(application.getApplicationContext(),
							R.array.confirmacion,
							android.R.layout.simple_spinner_item);
			adapterTipo
					.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		  holder.cboConcrecion.setAdapter(adapterTipo);
		  if(compromiso.getConcrecion().equals("S"))holder.cboConcrecion.setSelection(0);
		  else holder.cboConcrecion.setSelection(1);
	      
	      if(compromiso.getConfirmacionConcrecion().equals("1")) holder.chkConcrecion.setChecked(true);
	      else holder.chkConcrecion.setChecked(false);
	      
	      holder.txViewSOVI.setText(compromiso.getSovi());
	      if(compromiso.getConfirmacionSovi().equals("1")) holder.chkSOVI.setChecked(true);
	      else holder.chkSOVI.setChecked(false);
	      
	      holder.cboCumPrecio.setAdapter(adapterTipo);
		  if(compromiso.getConcrecion().equals("S"))holder.cboConcrecion.setSelection(0);
		  else holder.cboCumPrecio.setSelection(1);
	      
	      if(compromiso.getConfirmacionCumplePrecio().equals("1")) holder.chkPrecio.setChecked(true);
	      else holder.chkPrecio.setChecked(false);
	      
	      holder.txViewSabores.setText(compromiso.getNumeroSabores());
	      if(compromiso.getConfirmacionNumeroSabores().equals("1")) holder.chkSabores.setChecked(true);
	      else holder.chkSabores.setChecked(false);
	      
	      holder.txViewPuntos.setText(compromiso.getPuntosBonus());
	      holder.txViewAccTrade.setText(compromiso.getDescripcionAccion());
	      
	      
	      holder.btnFecha = (ImageButton)convertView.findViewById(R.id.btnFecha);
	    	
	    	holder.btnFecha.setOnClickListener(new OnClickListener() {
				
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					   String fecha = compromiso.getFechaCompromiso();
					      if(fecha.length() > 7)
					      {
					    	  CompromisoOpen_Activity.anio =  Integer.parseInt(fecha.substring(0, 4));
					    	  CompromisoOpen_Activity.mes  =  Integer.parseInt(fecha.substring(4, 6));
					    	  CompromisoOpen_Activity.dia  =  Integer.parseInt(fecha.substring(6));
					    	  
					      }else{
					    	  final Calendar c = Calendar.getInstance();        
					    	  CompromisoOpen_Activity.anio = c.get(Calendar.YEAR);        
					    	  CompromisoOpen_Activity.mes = c.get(Calendar.MONTH);        
					    	  CompromisoOpen_Activity.dia = c.get(Calendar.DAY_OF_MONTH); 
					          
					      }
					
					  
					
					
					((CompromisoOpen_Activity)context).txtFecha = holder.txViewFecha;
					((Activity)context).showDialog(0);
				}
			});
	    	
	    	
	      int mYear,mMonth,mDay;
	      String fecha = compromiso.getFechaCompromiso();
	      if(fecha.length() > 7)
	      {
	    	  mYear =  Integer.parseInt(fecha.substring(0, 4));
	    	  mMonth  =  Integer.parseInt(fecha.substring(4, 6));
	    	  mDay  =  Integer.parseInt(fecha.substring(6));
	    	  
	    	
	          
	    	  holder.txViewFecha.setText(pad(mDay)+"/"+ pad(mMonth)+"/"+pad(mYear));
	     }
	      else{
	    	  
	    	  holder.txViewFecha.setText("");
	      }
	      
	      if(compromiso.getCumplio().equals("1")) holder.chkCumplio.setChecked(true);
	      else holder.chkCumplio.setChecked(false);
	      
	      holder.txViewFecha.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//showDialog(DATE_DIALOG_ID);					
				}
			});
	      
	      
	      holder.cboConcrecion.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if(arg2==0) 
						compromiso.setConcrecion("S");
					else
						compromiso.setConcrecion("N");
				}
				
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
	      
	      
	      holder.txViewProfit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					/*Intent profit = new Intent(context,MostrarVendedorActivity.class);
					profit.putExtra(MostrarVendedorActivity.TIPO_SUPERVISOR, 0);
					profit.putExtra(MostrarVendedorActivity.CODIGO_CDA, codigo_cda);
					profit.putExtra(MostrarVendedorActivity.CODIGO_SUPERVISOR, supervisorTemporal.getCodigo());
					profit.putExtra(MostrarVendedorActivity.NOMBRE_SUPERVISOR, supervisorTemporal.getNombre());
					context.startActivity(profit);*/
				}
			});
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	TextView txViewPro;
	    	Spinner cboConcrecion;
	    	CheckBox chkConcrecion;
	    	EditText txViewSOVI;
	    	CheckBox chkSOVI;
	    	Spinner cboCumPrecio;
	    	CheckBox chkPrecio;
	    	EditText txViewSabores;
	    	CheckBox chkSabores;
	    	TextView txViewPuntos;   	    	
	    	TextView txViewAccTrade;    	
	    	EditText txViewFecha;
	    	ImageButton btnFecha;
	    	CheckBox chkCumplio;
	    	TextView txViewProfit;
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
