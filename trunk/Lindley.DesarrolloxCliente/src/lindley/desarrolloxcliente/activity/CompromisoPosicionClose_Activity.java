package lindley.desarrolloxcliente.activity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.CompromisoPosicionTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.ws.service.ConsultarPosicionCompromisoProxy;
import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MessageBox;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.content.DialogInterface;

import com.google.inject.Inject;

public class CompromisoPosicionClose_Activity extends ListActivityBase {

	public static final String COD_GESTION = "codGestion";
//	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	@Inject ConsultarPosicionCompromisoProxy consultarPosicionCompromisoProxy;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	@InjectView(R.id.txtViewCliente) TextView txtViewCliente;
	public static ClienteTO cliente;
	private EfficientAdapter adap;	
	public static MyApplication application;
	@InjectExtra(COD_GESTION) String codigoGestion;
	public static final String ESTANDAR_ANAQUEL = "03";
	
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	inicializarRecursos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultarposicioncompromisoclose_activity);        
//        mActionBar.setTitle(R.string.consultarposicioncompromisoclose_activity_title);
        application = (MyApplication)getApplicationContext();
		cliente = application.getClienteTO();
		txtViewCliente.setText(cliente.getCodigo() + " - " + cliente.getNombre());
//        mActionBar.setSubTitle(cliente.getNombre());
//        mActionBar.setHomeLogo(R.drawable.header_logo);
        processAsync(); 
    }
    
    @Override
   	protected void process() {
    	consultarPosicionCompromisoProxy.setCodigoCliente(cliente.getCodigo());
    	//consultarPosicionCompromisoProxy.setRespuesta(respuesta);       	
    	consultarPosicionCompromisoProxy.setCodigoGestion(codigoGestion);
    	consultarPosicionCompromisoProxy.execute();
   	}
    
    @Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExito = consultarPosicionCompromisoProxy.isExito();
		if (isExito) {
			int status = consultarPosicionCompromisoProxy.getResponse().getStatus();
			if (status == 0) {
				List<PosicionCompromisoTO> posiciones = consultarPosicionCompromisoProxy
						.getResponse().getListaCompromisos();
				adap = new EfficientAdapter(this, posiciones);				
				final Calendar c = Calendar.getInstance();      
				if(posiciones.size()>0)
					txtViewFecha.setText(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.YEAR));
				setListAdapter(adap);
			}
			else  {
				showToast(consultarPosicionCompromisoProxy.getResponse().getDescripcion());
			}
		}
		else{
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
    
    public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private Context context;
	    private List<PosicionCompromisoTO> detalles;
	    
	    public EfficientAdapter(Context context, List<PosicionCompromisoTO> valores) {
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
	    	final PosicionCompromisoTO posicionTO = (PosicionCompromisoTO) getItem(position);
	    	ViewHolder holder;

	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.consultarposicioncompromisoclose_content, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        
	        holder.TextViewRpsta = (TextView) convertView.findViewById(R.id.TextViewRpsta);
	        holder.txViewVariable = (TextView) convertView.findViewById(R.id.txViewVariable);
			holder.txViewRed = (TextView) convertView.findViewById(R.id.txViewRed);
			holder.txViewMaximo = (TextView) convertView.findViewById(R.id.txViewMaximo);
			holder.txViewDiferencia = (TextView) convertView.findViewById(R.id.txViewDiferencia);
			holder.txViewPuntos = (TextView) convertView.findViewById(R.id.txViewPuntos);
			holder.txViewAccComp = (TextView) convertView.findViewById(R.id.txViewAccComp);
			holder.txViewFecComp = (TextView) convertView.findViewById(R.id.txViewFecComp);
			holder.txViewCnfComp = (TextView) convertView.findViewById(R.id.txViewCnfComp);
			
			holder.btnFotoInicial = (Button) convertView.findViewById(R.id.btnFotoInicial);
			holder.btnFotoExito = (Button) convertView.findViewById(R.id.btnFotoExito);
			holder.btnFotoFinal = (Button) convertView.findViewById(R.id.btnFotoFinal);
						
	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }
	      
	      if(posicionTO.respuesta.equals("S"))
	    	  holder.TextViewRpsta.setText("SI");
	      else
	    	  holder.TextViewRpsta.setText("NO");
	      holder.txViewVariable.setText(posicionTO.getDescripcionVariable());
	      holder.txViewRed.setText(posicionTO.getRed());
	      holder.txViewMaximo.setText(posicionTO.getPtoMaximo());
	      holder.txViewDiferencia.setText(posicionTO.getDiferencia());
	      holder.txViewPuntos.setText(posicionTO.getPuntosSugeridos());
	      holder.txViewAccComp.setText(posicionTO.getAccionCompromiso());	      
	      holder.txViewCnfComp.setText(posicionTO.getConfirmacion());
	      if(posicionTO.getCodigoVariable().compareToIgnoreCase(ESTANDAR_ANAQUEL) == 0)
	      {
	    	  holder.btnFotoExito.setText(R.string.btnExitoText);
	      }
	      
	      int mYear,mMonth,mDay;
	      String fecha = posicionTO.getFechaCompromiso();
	      if(fecha.length() > 7)
	      {
	    	  mYear =  Integer.parseInt(fecha.substring(0, 4));
	    	  mMonth  =  Integer.parseInt(fecha.substring(4, 6));
	    	  mDay  =  Integer.parseInt(fecha.substring(6));
	    	  
	    	  holder.txViewFecComp.setText(pad(mDay)+"/"+ pad(mMonth)+"/"+pad(mYear));
	     }
	      else{
	    	  
	    	  holder.txViewFecComp.setText("");
	      }
	      
	      holder.btnFotoInicial.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(!posicionTO.getFotoInicial().equals(""))
					{
						Intent intent = new Intent("lindley.desarrolloxcliente.webviewverfoto");
						intent.putExtra(WebViewVerFoto_Activity.NOMBRE_FOTO, posicionTO.getFotoInicial());
						context.startActivity(intent);
					}
					else
					{
						MessageBox.showSimpleDialog(context, "Error", "No existe foto registrada.", "Aceptar", new android.content.DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
							}
						});	
					}
				}
			});
	      
	      
	      holder.btnFotoFinal.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(!posicionTO.getFotoFinal().equals(""))
					{
						Intent intent = new Intent("lindley.desarrolloxcliente.webviewverfoto");
						intent.putExtra(WebViewVerFoto_Activity.NOMBRE_FOTO, posicionTO.getFotoFinal());
						context.startActivity(intent);
					}
					else
					{
						MessageBox.showSimpleDialog(context, "Error", "No existe foto registrada.", "Aceptar", new android.content.DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
							}
						});	
					}
				}
			});
	      
	      holder.btnFotoExito.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(posicionTO.getCodigoVariable().compareToIgnoreCase(ESTANDAR_ANAQUEL) == 0)
					{
						application.listCompromiso = posicionTO.getListCompromisos();
						if(application.listCompromiso == null)
							application.listCompromiso = new ArrayList<CompromisoPosicionTO>();
						Intent intent = new Intent("lindley.desarrolloxcliente.vercompromisosclose");
						context.startActivity(intent);	
					}
					else
					{
						Intent intent = new Intent("lindley.desarrolloxcliente.listarfotoexito");
						intent.putExtra(ListarFotoExito_Activity.ID_CLUSTER, cliente.getCluster() );
						context.startActivity(intent);
					}
				}
			});
	      
	      return convertView;
	    }

	    static class ViewHolder {   
	    	public TextView TextViewRpsta;
	    	public TextView txViewVariable;
	    	public TextView txViewRed;
	    	public TextView txViewMaximo;
	    	public TextView txViewDiferencia;
	    	public TextView txViewPuntos;
	    	public Button btnFotoInicial;
	    	public Button btnFotoExito;
	    	public TextView txViewAccComp;
	    	public TextView txViewFecComp;
	    	public Button btnFotoFinal;
	    	public TextView txViewCnfComp;	
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
