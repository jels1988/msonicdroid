package pe.lindley.preliquidacion;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.thira.examples.actionbar.widget.ActionBar;

import pe.lindley.preliquidacion.negocio.DocumentoBLL;
import pe.lindley.preliquidacion.to.DocumentoRequestTO;
import pe.lindley.preliquidacion.to.DocumentoResponseTO;
import pe.lindley.preliquidacion.to.DocumentoTO;
import pe.lindley.preliquidacion.to.UsuarioTO;
import pe.lindley.preliquidacion.ws.proxy.CalificarDocumentoProxy;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.app.Activity;
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
import net.msonic.lib.ListActivityBase;
import net.msonic.lib.MessageBox;

public class DocumentoActivity extends ListActivityBase {
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	
	
	public  DocumentoTO documentoTO;
	public UsuarioTO usuarioTO;
	
	public static final String CODIGO_CLIENTE_KEY="codigo_cliente";
	public static final int ENTREGAR_DOCUMENTO = 1;
	public static final int RECHAZAR_DOCUMENTO = 2;
	public static final int ACTIVAR_DOCUMENTO = 3;
	public static final int ENVIAR_TODOS = 4;
	
	@InjectExtra(CODIGO_CLIENTE_KEY) String codigoCliente;
	
	@Inject DocumentoBLL documentoBLL;
	private List<DocumentoTO> documentos;
	@InjectView(R.id.txtCli) TextView txtCli;
	@Inject CalificarDocumentoProxy calificarDocumentoProxy;
	@InjectResource(R.string.calificar_documento_entregado_title) 	String calificar_documento_entregado_title;
	@InjectResource(R.string.calificar_documento_entregado_message)	String calificar_documento_entregado_message;
	@InjectResource(R.string.calificar_documento_rechazado_title) 	String calificar_document_rechazado_title;
	@InjectResource(R.string.calificar_documento_rechazado_message)	String calificar_documento_rechazado_message;
	@InjectResource(R.string.calificar_document_activado_title)	String calificar_document_activado_title;
	@InjectResource(R.string.calificar_documento_activado_message)	String calificar_documento_activado_message;
	@InjectResource(R.string.calificar_documento_entregado_todos_message)	String calificar_documento_entregado_todos_message;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		inicializarRecursos();
		
		RTMApplication application = (RTMApplication)contextProvider.get().getApplicationContext();
		usuarioTO = application.getUsuarioTO();	
		
		setContentView(R.layout.documento_actitivy);
		
		mActionBar.setTitle(R.string.login_activity_sub_title);
        mActionBar.setHomeLogo(R.drawable.header_logo);
    	
        
		processAsync();
	}

	@Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			Intent i = data;
			i.getStringExtra("codigo");
			if(documentoTO!=null){
				documentoBLL.rechazarDocumento(documentoTO);
				String motivo = data.getStringExtra(ListaMotivosActivity.MOTIVO_SELECCIONADO);
				documentoTO.setMotivo(motivo);
				processAsync(RECHAZAR_DOCUMENTO);
			}
			
	 }
	
	@Override
	protected void process() {
		documentos = documentoBLL.consultarDocumentoxCodigo(codigoCliente);
	}

	public void entregarDocumento(){
		if(documentoTO!=null){
			processAsync(ENTREGAR_DOCUMENTO);
				
		}
	}
	
	
	@Override
	protected void process(int accion) {
		// TODO Auto-generated method stub
		if(accion == ENTREGAR_DOCUMENTO)
		{
				
			documentos.add(documentoTO);
			
			ArrayList<DocumentoRequestTO> docRequest = new ArrayList<DocumentoRequestTO>();
			
			DocumentoRequestTO documentoRequestTO = new DocumentoRequestTO();
			documentoRequestTO.setNroDocumento(documentoTO.getNroDocumento()+"");
			documentoRequestTO.setEstado(DocumentoBLL.ESTADO_ENTREGA_TOTAL);
			documentoRequestTO.setDeposito(usuarioTO.getDeposito());
			documentoRequestTO.setUsuario(usuarioTO.getUsuario());
			documentoRequestTO.setLatitud("0.00");
			documentoRequestTO.setLongitud("0.00");
			documentoRequestTO.setTipo(documentoTO.getTipoDocumento());
			documentoRequestTO.setMotivo("");				
			docRequest.add(documentoRequestTO);
			
			calificarDocumentoProxy.setDocumentos(docRequest);
			calificarDocumentoProxy.execute();
			
			
		}
		else if(accion == RECHAZAR_DOCUMENTO)
		{
			ArrayList<DocumentoTO> documentos = new ArrayList<DocumentoTO>();			
			documentos.add(documentoTO);
			ArrayList<DocumentoRequestTO> docRequest = new ArrayList<DocumentoRequestTO>();
			for(DocumentoTO documento : documentos)
			{				
				DocumentoRequestTO documentoRequestTO = new DocumentoRequestTO();
				documentoRequestTO.setNroDocumento(documento.getNroDocumento()+"");
				documentoRequestTO.setEstado(DocumentoBLL.ESTADO_RECHAZO);
				documentoRequestTO.setDeposito(usuarioTO.getDeposito());
				documentoRequestTO.setUsuario(usuarioTO.getUsuario());
				documentoRequestTO.setLatitud("0.00");
				documentoRequestTO.setLongitud("0.00");
				documentoRequestTO.setTipo(documento.getTipoDocumento());
				documentoRequestTO.setMotivo(documento.getMotivo());				
				docRequest.add(documentoRequestTO);
			}
			calificarDocumentoProxy.setDocumentos(docRequest);
			calificarDocumentoProxy.execute();
		}
		else if(accion == ACTIVAR_DOCUMENTO)
		{
			ArrayList<DocumentoTO> documentos = new ArrayList<DocumentoTO>();			
			documentos.add(documentoTO);
			ArrayList<DocumentoRequestTO> docRequest = new ArrayList<DocumentoRequestTO>();
			for(DocumentoTO documento : documentos)
			{				
				DocumentoRequestTO documentoRequestTO = new DocumentoRequestTO();
				documentoRequestTO.setNroDocumento(documento.getNroDocumento()+"");
				documentoRequestTO.setEstado(DocumentoBLL.ESTADO_PENDIENTE);
				documentoRequestTO.setDeposito(usuarioTO.getDeposito());
				documentoRequestTO.setUsuario(usuarioTO.getUsuario());
				documentoRequestTO.setLatitud("0.00");
				documentoRequestTO.setLongitud("0.00");
				documentoRequestTO.setTipo(documento.getTipoDocumento());
				documentoRequestTO.setMotivo("");				
				docRequest.add(documentoRequestTO);
			}
			calificarDocumentoProxy.setDocumentos(docRequest);
			calificarDocumentoProxy.execute();
		}
		else if(accion == ENVIAR_TODOS)
		{
			
			ArrayList<DocumentoRequestTO> docRequest = new ArrayList<DocumentoRequestTO>();		
			
			
			for(DocumentoTO documentoEnviar : documentos)
			{				
				DocumentoRequestTO documentoRequestTO = new DocumentoRequestTO();
				documentoRequestTO.setNroDocumento(documentoEnviar.getNroDocumento()+"");
				documentoRequestTO.setEstado(DocumentoBLL.ESTADO_ENTREGA_TOTAL);
				documentoRequestTO.setDeposito(usuarioTO.getDeposito());
				documentoRequestTO.setUsuario(usuarioTO.getUsuario());
				documentoRequestTO.setLatitud("0.00");
				documentoRequestTO.setLongitud("0.00");
				documentoRequestTO.setTipo(documentoEnviar.getTipoDocumento());
				documentoRequestTO.setMotivo("");				
				docRequest.add(documentoRequestTO);
			}
			
			calificarDocumentoProxy.setDocumentos(docRequest);
			calificarDocumentoProxy.execute();
		}
	}

	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(accion == ENTREGAR_DOCUMENTO)
		{
			boolean isExito = calificarDocumentoProxy.isExito();
			
			if (isExito) {
				int status = calificarDocumentoProxy.getResponse().getStatus();
				if (status == 0) {
					List<DocumentoResponseTO> documentosTO =	calificarDocumentoProxy.getResponse().getDocumentos();
					
					if(documentosTO!=null){
						documentoBLL.cerrarDocumento(documentosTO);
						documentoBLL.entregarDocumento(documentoTO);
					}
					
					final Context context = this;
					
					MessageBox.showSimpleDialog(context, calificar_documento_entregado_title, calificar_documento_entregado_message, "Aceptar", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							processAsync();
						}
					});	
				}
				else  {
					showToast(calificarDocumentoProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError(accion);
			}		
		}
		else if(accion == RECHAZAR_DOCUMENTO)
		{
			boolean isExito = calificarDocumentoProxy.isExito();
			
			if (isExito) {
				int status = calificarDocumentoProxy.getResponse().getStatus();
				if (status == 0) {
					List<DocumentoResponseTO> documentosTO =	calificarDocumentoProxy.getResponse().getDocumentos();
					if(documentosTO!=null){
						//documentoBLL.entregarDocumento(documentoTO);		
						documentoBLL.cerrarDocumento(documentosTO);
					}
					
					final Context context = this;
					
					MessageBox.showSimpleDialog(context, calificar_document_rechazado_title, calificar_documento_rechazado_message, "Aceptar", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							processAsync();
						}
					});	
				}
				else  {
					showToast(calificarDocumentoProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError(accion);
			}		
		}
		else if(accion == ACTIVAR_DOCUMENTO)
		{
			boolean isExito = calificarDocumentoProxy.isExito();
			
			if (isExito) {
				int status = calificarDocumentoProxy.getResponse().getStatus();
				if (status == 0) {
					List<DocumentoResponseTO> documentosTO =	calificarDocumentoProxy.getResponse().getDocumentos();
					if(documentosTO!=null){
						documentoBLL.cerrarDocumento(documentosTO);
					}
					
					final Context context = this;
					
					MessageBox.showSimpleDialog(context, calificar_document_activado_title, calificar_documento_activado_message, "Aceptar", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							processAsync();
						}
					});	
				}
				else  {
					showToast(calificarDocumentoProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError(accion);
			}		
		}
		else if(accion == ENVIAR_TODOS){
			
			
			
			boolean isExito = calificarDocumentoProxy.isExito();
			
			if (isExito) {
				int status = calificarDocumentoProxy.getResponse().getStatus();
				if (status == 0) {
					List<DocumentoResponseTO> documentosTO = calificarDocumentoProxy.getResponse().getDocumentos();
					
					if(documentosTO!=null){
						documentoBLL.cerrarDocumento(documentosTO);
					}
					
					if(documentos!=null){
						documentoBLL.entregarDocumento(documentos);
					}
					
					
					
					final Context context = this;
					
					MessageBox.showSimpleDialog(context, calificar_documento_entregado_title, calificar_documento_entregado_todos_message, "Aceptar", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							processAsync();
						}
					});	
				}
				else  {
					showToast(calificarDocumentoProxy.getResponse().getDescripcion());
				}
			}
			else{
				processError(accion);
			}		
			
			
			

			
		}
		
		
		
		super.processOk(accion);		
	}

	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		showToast(error_generico_process);
		super.processError(accion);
	}

	@Override
	protected void processOk() {
		if(documentos.size()<=0){
			showToast("El cliente ingresado " + codigoCliente + " no se encuentra registrado.");
			finish();
		}else{
			EfficientAdapter adap = new EfficientAdapter(this,documentos);
			setListAdapter(adap);
			if(documentos.size()>0)
				txtCli.setText(codigoCliente + " - " + documentos.get(0).getNombre());
		}
		
		
		
		super.processOk();	
	}
	
	@Override
	protected void processError() {
		super.processError();
	}

	public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private List<DocumentoTO> documentos;
	    private Activity context;
	    
	    public EfficientAdapter(Context context, List<DocumentoTO> documentos) {
		      // Cache the LayoutInflate to avoid asking for a new one each time.
		      mInflater = LayoutInflater.from(context);
		      this.documentos = documentos;
		      this.context = (Activity)context;
		    }
	    
	    
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	DocumentoTO documentoTO = (DocumentoTO) getItem(position);
	    	
		    ViewHolder holder;
		    
		    if (convertView == null) {
		        convertView = mInflater.inflate(R.layout.documento_content, null);
		        holder = new ViewHolder();
		        
		        holder.txtSunat = (TextView) convertView.findViewById(R.id.txtNroDocumento);
		        holder.txtEstado = (TextView) convertView.findViewById(R.id.txtEstado);
		        holder.txtImporte = (TextView) convertView.findViewById(R.id.txtImporte);
		        holder.btnRechazar = (ImageButton) convertView.findViewById(R.id.btn_rechzar);
		        holder.btnEntregaTotal = (ImageButton) convertView.findViewById(R.id.btn_entregaTotal);
		        holder.btnActivar = (ImageButton) convertView.findViewById(R.id.btn_activar);
		        holder.btn_enviarAll = (ImageButton) convertView.findViewById(R.id.btn_enviarAll);
		        
		        convertView.setTag(holder);
		      } else {
		        // Get the ViewHolder back to get fast access to the TextView
		        // and the ImageView.
		        holder = (ViewHolder) convertView.getTag();
		      }
		    
		    holder.txtSunat.setText(documentoTO.getNroDocumento());
		    holder.txtEstado.setText(documentoTO.getEstadoDes());
		    holder.txtImporte.setText(String.valueOf(documentoTO.getImporte()));
		    
		    if (documentoTO.getEstado().compareTo(DocumentoBLL.ESTADO_ENTREGA_TOTAL)==0){
		    	holder.btnEntregaTotal.setVisibility(View.GONE);
		    	holder.btnRechazar.setVisibility(View.GONE);
		    	holder.btn_enviarAll.setVisibility(View.GONE);
		    }else{
		    	holder.btnActivar.setVisibility(View.GONE);
		    }
		    
		    
		    holder.btnEntregaTotal.setOnClickListener(new OnClickListener() {
				
		    	DocumentoActivity documentoActivity = (DocumentoActivity)context;
		    	DocumentoTO documentoTOSeleccionado = (DocumentoTO) getItem(position);
		    	
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					documentoActivity.documentoTO = documentoTOSeleccionado;					
					documentoActivity.entregarDocumento();
				}
			});
		    
		    holder.btnRechazar.setOnClickListener(new OnClickListener() {
		    
		    	DocumentoActivity documentoActivity = (DocumentoActivity)context;
		    	DocumentoTO documentoTOSeleccionado = (DocumentoTO) getItem(position);
		    	
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(documentoActivity, ListaMotivosActivity.class);
					documentoActivity.startActivityForResult(i, 0);
					documentoActivity.documentoTO = documentoTOSeleccionado;
					
				}
			});
		    
		    holder.btnActivar.setOnClickListener(new OnClickListener() {
			    
		    	DocumentoActivity documentoActivity = (DocumentoActivity)context;
		    	DocumentoTO documentoTOSeleccionado = (DocumentoTO) getItem(position);
		    	
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					documentoActivity.documentoTO = documentoTOSeleccionado;
					documentoActivity.documentoBLL.activarDocumento(documentoActivity.documentoTO);
					documentoActivity.processAsync(ACTIVAR_DOCUMENTO);
				}
			});
		    
		    holder.btn_enviarAll.setOnClickListener(new OnClickListener() {
		    	DocumentoActivity documentoActivity = (DocumentoActivity)context;
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					documentoActivity.processAsync(ENVIAR_TODOS);
				}
			});
		    
		    return convertView;
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
	    	if(documentos==null){
	    		return 0;
	    	}else{
	    		return documentos.size();
	    	}
	    }

	    @Override
	    public Object getItem(int position) {
	      // TODO Auto-generated method stub
	    	return documentos.get(position);
	    }
	    
	    static class ViewHolder {
	    	TextView txtSunat;
	    	TextView txtEstado;
	    	TextView txtImporte;
	    	ImageButton btnRechazar;
	    	ImageButton btnEntregaTotal;
	    	ImageButton btnActivar;
	    	ImageButton btn_enviarAll;
	    }

	}
	
	
}


    
    