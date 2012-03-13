package pe.lindley.prospector.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import pe.lindley.activity.R;
import pe.lindley.prospector.negocio.ClienteBLL;
import pe.lindley.prospector.to.ClienteTO;
import pe.lindley.util.ListActivityBase;
import pe.lindley.util.MessageBox;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class ConsultarClienteLocalActivity extends ListActivityBase {

	private static int ELIMINAR_FICHA=1;
	
	@InjectView(R.id.actionBar) ActionBar mActionBar;
	@InjectView(R.id.txtCodigo) TextView txtCodigo;
	@InjectView(R.id.txtRuc) TextView txtRuc;
	@InjectView(R.id.txtDni) TextView txtDni;
	
	@InjectResource(R.string.consultar_cliente_local_activity_title) 					String consultar_cliente_local_title;
	@InjectResource(R.string.consultar_cliente_local_activity_delete_confirm_dialog) 	String 	consultar_cliente_local_delete;
	@InjectResource(R.string.fichasrechazadas_clientes_activity_confirm_dialog_si) 		String 	confirm_si;
	@InjectResource(R.string.fichasrechazadas_clientes_activity_confirm_dialog_no) 		String 	confirm_no;
	@InjectResource(R.string.fichasrechazadas_clientes_activity_confirm_dialog_ok) 		String 	confirm_ok;
	
	
	//consultar_cliente_local_activity_delete_confirm_dialog
	@Inject ClienteBLL clienteBLL;
	ArrayList<ClienteTO> resultadoBusqueda;
	private EfficientAdapter adap;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.consultarcliente_activity);

		mActionBar.setTitle(R.string.consultar_cliente_local_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);

	}
	
	public void btnbuscar_onclick(View view) {
		processAsync();
	}
	
	
	boolean eliminoFicha=false;
	@Override
	protected void process(int accion){
		
		if(ELIMINAR_FICHA==accion){
			eliminoFicha = clienteBLL.delete(this.fichaId);
		}
	}
	
	private int fichaId;
	private int filaRemove;

	public int getFichaId() {
		return fichaId;
	}

	public void setFichaId(int fichaId) {
		this.fichaId = fichaId;
	}

	public int getFilaRemove() {
		return filaRemove;
	}

	public void setFilaRemove(int filaRemove) {
		this.filaRemove = filaRemove;
	}

	@Override
	protected void processOk(int accion){
		if(ELIMINAR_FICHA==accion){
			if(eliminoFicha){
				resultadoBusqueda.remove(filaRemove);
				adap.notifyDataSetChanged();
			}
		}
		
		super.processOk(accion);
	}
	
	
	public void eliminarFicha(){
		processAsync(ELIMINAR_FICHA);
	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		String codigo = txtCodigo.getText().toString();
		String ruc = txtRuc.getText().toString();
		String dni = txtDni.getText().toString();

		resultadoBusqueda = clienteBLL.list(codigo, dni, ruc);
	}
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		adap = new EfficientAdapter(this, resultadoBusqueda);
		setListAdapter(adap);
		
		super.processOk();
	}

	@Override
	protected void processError() {
		// TODO Auto-generated method stub
		super.processError();
	}

	
	
	
	
	public static class EfficientAdapter extends BaseAdapter implements Filterable {
		private LayoutInflater mInflater;
		private Context context;
		private List<ClienteTO> clientes;
		
		public EfficientAdapter(Context context, List<ClienteTO> clientes) {
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
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ClienteTO cliente = (ClienteTO) getItem(position);
			ViewHolder holder;
		
			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.consultarclientelocal_content, null);
		
				// Creates a ViewHolder and store references to the two children
				// views
				// we want to bind data to.
				holder = new ViewHolder();
				holder.txtRazonSocial = (TextView) convertView.findViewById(R.id.txtRazonSocial);
				holder.txtRuc = (TextView) convertView.findViewById(R.id.txtRuc);
				holder.txtDni = (TextView) convertView.findViewById(R.id.txtDni);
				holder.txtCodigo = (TextView) convertView.findViewById(R.id.txtCodigo);
				holder.txtDireccion = (TextView) convertView.findViewById(R.id.txtDireccion);
				holder.txtRepresentante = (TextView) convertView.findViewById(R.id.txtRepresentante);
				holder.txtMotivo = (TextView) convertView.findViewById(R.id.txtMotivo);
				holder.txtObservacion = (TextView) convertView.findViewById(R.id.txtObservacion);
				holder.imgEliminar = (ImageButton) convertView.findViewById(R.id.btn_eliminar);
				holder.imgEditar = (ImageButton) convertView.findViewById(R.id.btn_editar);
				holder.imgDocumentos = (ImageButton) convertView.findViewById(R.id.btn_documentos);
		
				convertView.setTag(holder);
			} else {
				// Get the ViewHolder back to get fast access to the TextView
				// and the ImageView.
				holder = (ViewHolder) convertView.getTag();
			}
		
			holder.txtRazonSocial.setText(cliente.getRazonSocial());
			holder.txtRuc.setText(cliente.getRuc());
			holder.txtDni.setText(cliente.getDni());
			holder.txtCodigo.setText(cliente.getCodigo());
			holder.txtDireccion.setText(cliente.getDireccionEntrega());
			holder.txtMotivo.setText(cliente.getMotivo());
			holder.txtObservacion.setText(cliente.getObservacion());
			holder.txtRepresentante.setText(cliente.getNombreComercial());
			
			final String titulo = ((ConsultarClienteLocalActivity)this.context).consultar_cliente_local_title;
			final String pregunta = ((ConsultarClienteLocalActivity)this.context).consultar_cliente_local_delete;
			final String pregunta_si = ((ConsultarClienteLocalActivity)this.context).confirm_si;
			final String pregunta_no = ((ConsultarClienteLocalActivity)this.context).confirm_no;
			
			holder.imgDocumentos.setOnClickListener(new OnClickListener() {
				ClienteTO clienteTemporal = (ClienteTO) getItem(position);
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent verDocumentos = new Intent(context, RegistrarDocumentosActivity.class);
					verDocumentos.putExtra(RegistrarDocumentosActivity.CLIENTE_ID, clienteTemporal.getClienteId());
					verDocumentos.putExtra(RegistrarDocumentosActivity.CLIENTE_NOMBRES, clienteTemporal.getRazonSocial());
					context.startActivity(verDocumentos);
				}
			});
			
			holder.imgEliminar.setOnClickListener(new OnClickListener() {
				ClienteTO clienteTemporal = (ClienteTO) getItem(position);
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					MessageBox.showConfirmDialog(context,
								titulo,
								pregunta,
								pregunta_si,
								new android.content.DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										ConsultarClienteLocalActivity activity = ((ConsultarClienteLocalActivity)context); 
										activity.setFichaId(clienteTemporal.getClienteId());
										activity.setFilaRemove(position);
										activity.eliminarFicha();
									}
								},
								pregunta_no,
								null);
					
					
				}
			});
			
			holder.imgEditar.setOnClickListener(new OnClickListener() {
				ClienteTO clienteTemporal = (ClienteTO) getItem(position);
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent registrarCliente = new Intent(context,RegistrarClienteActivity.class);
					registrarCliente.putExtra(RegistrarClienteActivity.CLIENTE_ID_KEY ,clienteTemporal.getClienteId());
					registrarCliente.putExtra(RegistrarClienteActivity.CODIGO_CLIENTE_ACCION, RegistrarClienteActivity.CLIENTE_CARGAR_FROM_BD);
					registrarCliente.putExtra(RegistrarClienteActivity.CLIENTE_REFERENCIA_ID_KEY,clienteTemporal.getCodigoReferencia());
					registrarCliente.putExtra(RegistrarClienteActivity.CLIENTE_REFERENCIA_RAZONSOCIAL_KEY,clienteTemporal.getRazonSocialRef());
				
					context.startActivity(registrarCliente);
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
			TextView txtRepresentante;
			TextView txtMotivo;
			TextView txtObservacion;
			
			ImageButton imgEliminar;
			ImageButton imgEditar;
			ImageButton imgDocumentos;
			
			
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
