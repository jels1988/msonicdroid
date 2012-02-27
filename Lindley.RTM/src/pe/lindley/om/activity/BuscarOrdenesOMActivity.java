package pe.lindley.om.activity;

import java.util.Calendar;
import java.util.List;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.om.negocio.OrdenTrabajoBLL;
import pe.lindley.om.to.OrdenTrabajoTO;
import pe.lindley.util.ListActivityBase;
import roboguice.inject.InjectView;

public class BuscarOrdenesOMActivity extends ListActivityBase {

	public static final String CLIENTE_CODIGO_KEY="codigo_cliente";
	public static final String CLIENTE_RAZONSOCIAL_KEY="cliente_descripcion";
	private static int ELIMINAR_ORDENOM=1;
	
	boolean eliminoFicha=false;
	@Override
	protected void process(int accion){
		if(ELIMINAR_ORDENOM==accion){
			ordenTrabajoBLL.delete(this.ordenTrabajoId);
		}
	}
	
	@Override
	protected void processOk(int accion){
		if(ELIMINAR_ORDENOM==accion){
			ordenes.remove(filaRemove);
			adapter.notifyDataSetChanged();
		}
		super.processOk(accion);
	}
	
	public void eliminarFicha(){
		processAsync(ELIMINAR_ORDENOM);
	}
	
	private long ordenTrabajoId;
	public long getOrdenTrabajoId() {
		return ordenTrabajoId;
	}


	public void setOrdenTrabajoId(long ordenTrabajoId) {
		this.ordenTrabajoId = ordenTrabajoId;
	}


	public int getFilaRemove() {
		return filaRemove;
	}


	public void setFilaRemove(int filaRemove) {
		this.filaRemove = filaRemove;
	}

	private int filaRemove;
	
	private int mYear;
    private int mMonth;
    private int mDay;
    static final int DATE_DIALOG_ID = 0;
    
    @InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
    @InjectView(R.id.txtFecha)		TextView txtFecha;
    @InjectView(R.id.cboEstado) 	Spinner 	cboEstado;
	
	
    @Inject OrdenTrabajoBLL ordenTrabajoBLL;
  
    
    private String cliente_codigo = null;
	private String cliente_descripcion = null;
	
	private EfficientAdapter adapter;
	private List<OrdenTrabajoTO> ordenes;
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		processAsync();
		super.onResume();
		
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.buscarordenesom_activity);
		
		
		
		mActionBar.setTitle(R.string.buscarordenesom_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);
		
		Intent intent = this.getIntent();
		cliente_codigo = intent.getStringExtra(CLIENTE_CODIGO_KEY);
		cliente_descripcion = intent.getStringExtra(CLIENTE_RAZONSOCIAL_KEY);
		
		mActionBar.setSubTitle(cliente_descripcion);
		
		// get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        
        updateDisplay();
        
        
        ArrayAdapter<CharSequence> adapterEstado = ArrayAdapter.createFromResource(getApplicationContext(), R.array.oportunidadm_estado, android.R.layout.simple_spinner_item);
        adapterEstado.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		cboEstado.setAdapter(adapterEstado);
		
		
	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		String fecha = txtFecha.getText().toString();
		String estado = String.valueOf(String.format("0%s", cboEstado.getSelectedItemPosition() + 1) );
		ordenes = ordenTrabajoBLL.list(cliente_codigo,estado,fecha);

	}
	
	public void btnadd_onclick(View view){
		
		Intent intent2 = new Intent(this,NuevaOrdenOMActivity.class);
		intent2.putExtra(NuevaOrdenOMActivity.CLIENTE_CODIGO_KEY, cliente_codigo);
		intent2.putExtra(NuevaOrdenOMActivity.CLIENTE_RAZONSOCIAL_KEY, cliente_descripcion);
		startActivity(intent2);
		
	}
	@Override
	protected void processOk() {
		adapter = new EfficientAdapter(this, ordenes,cliente_codigo,cliente_descripcion);
		setListAdapter(adapter);
		super.processOk();
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DATE_DIALOG_ID:
	        return new DatePickerDialog(this,mDateSetListener,mYear, mMonth, mDay);
	    }
	    return null;
	}
	
	 // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
    			
				@Override
				public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
				}
            };
            
    // updates the date in the TextView
    private void updateDisplay() {
    	txtFecha.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
            		.append(mDay).append("/")
                    .append(mMonth + 1).append("/")
                    .append(mYear).append(""));
    }

	public void btnbuscar_onclick(View view){
		processAsync();
	}
	
	public void btncalendario_onclick(View view){
		showDialog(DATE_DIALOG_ID);

	}

	public static class EfficientAdapter extends BaseAdapter implements Filterable {
		private LayoutInflater mInflater;
		private List<OrdenTrabajoTO> ordenes;
		private Context context;
		private String codigo_cliente;
		private String descripcion_cliente;
		public EfficientAdapter(Context context, List<OrdenTrabajoTO> ordenes,String codigo_cliente,String descripcion_cliente) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
			this.ordenes = ordenes;
			this.context = context;
			this.codigo_cliente=codigo_cliente;
			this.descripcion_cliente=descripcion_cliente;
		}

	public EfficientAdapter(Context context) {
		// Cache the LayoutInflate to avoid asking for a new one each time.
		mInflater = LayoutInflater.from(context);
		this.context = context;
		this.codigo_cliente="";
		this.descripcion_cliente="";
	}

	/**
	 * Make a view to hold each row.
	 * 
	 * @see android.widget.ListAdapter#getView(int, android.view.View,
	 *      android.view.ViewGroup)
	 */
	public View getView(final int position, View convertView,ViewGroup parent) {
		OrdenTrabajoTO ordenTrabajoTO = (OrdenTrabajoTO) getItem(position);
		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.buscarordenesom_content, null);

			// Creates a ViewHolder and store references to the two children
			// views
			// we want to bind data to.
			holder = new ViewHolder();
		
			holder.txtNumeroOrden = (TextView) convertView.findViewById(R.id.txtNumeroOrden);
			holder.txtTipoContacto = (TextView) convertView.findViewById(R.id.txtTipoContacto);
			holder.txtTipoOrden = (TextView) convertView.findViewById(R.id.txtTipoOrden);
			holder.txtSubTipoOrden = (TextView) convertView.findViewById(R.id.txtSubTipoOrden);
			holder.txtMotivo = (TextView) convertView.findViewById(R.id.txtMotivo);
			holder.txtPrioridad = (TextView) convertView.findViewById(R.id.txtPrioridad);
			holder.txtEstado = (TextView) convertView.findViewById(R.id.txtEstado);
			holder.txtAsignado = (TextView) convertView.findViewById(R.id.txtAsignado);
			holder.txtFecha = (TextView) convertView.findViewById(R.id.txtFecha);
			holder.btnEventos = (ImageButton)convertView.findViewById(R.id.btn_eventos);
			holder.btnEliminar = (ImageButton)convertView.findViewById(R.id.btn_eliminar);
			
			convertView.setTag(holder);
		} else {

			holder = (ViewHolder) convertView.getTag();
		}
		
		long idLocal = ordenTrabajoTO.getNuOrd();
		long idServer = ordenTrabajoTO.getNuOrd2();
		if(idServer==0)
			holder.txtNumeroOrden.setText(String.format("%s",idLocal)) ;
		else
			holder.txtNumeroOrden.setText(String.format("%s",idServer)) ;
		
		holder.txtTipoContacto.setText(ordenTrabajoTO.getDsTpC()) ;
		holder.txtTipoOrden.setText(ordenTrabajoTO.getDsTpO()) ;
		holder.txtSubTipoOrden.setText(ordenTrabajoTO.getDsStO()) ;
		holder.txtMotivo.setText(ordenTrabajoTO.getDsMtO()) ;
		holder.txtPrioridad.setText(ordenTrabajoTO.getDsPri()) ;
		holder.txtEstado.setText(ordenTrabajoTO.getDsEsO()) ;
		holder.txtAsignado.setText(ordenTrabajoTO.getDsUsA()) ;
		holder.txtFecha.setText(ordenTrabajoTO.getFeCre()) ;
		
		if(!OrdenTrabajoBLL.CREADO.equalsIgnoreCase(ordenTrabajoTO.getEsOrd())){
			holder.btnEliminar.setVisibility(View.GONE);
		}
		
		holder.btnEventos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				OrdenTrabajoTO ordenTrabajoTO = (OrdenTrabajoTO) getItem(position);
				
				long numeroOrdenServer = ordenTrabajoTO.getNuOrd2();
				long numeroOrdenLocal = ordenTrabajoTO.getNuOrd();
					
				
				Intent intent = new Intent(context,ListadoEventosOMActivity.class);
				intent.putExtra(ListadoEventosOMActivity.ORDEN_TRABAJO_ID,numeroOrdenLocal);
				intent.putExtra(ListadoEventosOMActivity.CLIENTE_CODIGO_KEY,codigo_cliente);
				intent.putExtra(ListadoEventosOMActivity.CLIENTE_RAZONSOCIAL_KEY,descripcion_cliente);
				
				
				if(numeroOrdenServer!=0){
					intent.putExtra(ListadoEventosOMActivity.ORDEN_TRABAJO_CODIGO,String.valueOf(numeroOrdenServer));
				}else{
					intent.putExtra(ListadoEventosOMActivity.ORDEN_TRABAJO_CODIGO,String.valueOf(numeroOrdenLocal));
				}
				
				
				
				context.startActivity(intent);
			}
		});
		
		holder.btnEliminar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				OrdenTrabajoTO ordenTrabajoTO = (OrdenTrabajoTO) getItem(position);
				// TODO Auto-generated method stub
				BuscarOrdenesOMActivity buscarOrdenesOMActivity = ((BuscarOrdenesOMActivity)context);
				buscarOrdenesOMActivity.setOrdenTrabajoId(ordenTrabajoTO.getNuOrd());
				buscarOrdenesOMActivity.setFilaRemove(position);
				buscarOrdenesOMActivity.eliminarFicha();
			}
		});
		
		return convertView;
	}

	static class ViewHolder {

		TextView txtNumeroOrden;
		TextView txtTipoContacto;
		TextView txtTipoOrden;
		TextView txtSubTipoOrden;
		TextView txtMotivo;
		TextView txtPrioridad;
		TextView txtEstado;
		TextView txtAsignado;
		TextView txtFecha;
		ImageButton btnEventos;
		ImageButton btnEliminar;
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
		if (ordenes == null) {
			return 0;
		} else {
			return ordenes.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return ordenes.get(position);
	}

	}

	
	
}
