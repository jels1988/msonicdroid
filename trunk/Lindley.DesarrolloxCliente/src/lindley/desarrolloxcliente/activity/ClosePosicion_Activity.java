package lindley.desarrolloxcliente.activity;

import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.FotoBLL;
import lindley.desarrolloxcliente.negocio.PosicionBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import lindley.desarrolloxcliente.to.upload.PosicionTO;
import net.msonic.lib.MessageBox;
import net.msonic.lib.UploadFileUtil;
import net.msonic.lib.sherlock.ListBaseFragment;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;

public class ClosePosicion_Activity extends ListBaseFragment  {

	private EvaluacionTO evaluacion;
	private  MyApplication application;
	private ClienteTO cliente;
	
	@Inject PosicionBLL posicionBLL;
	@Inject FotoBLL fotoBLL;
	@InjectView(R.id.txtViewFecha) TextView txtViewFecha;
	
	
	

	EfficientAdapter oportunidades;
	
	public static String file_name="";
	private static final int TAKE_PHOTO_INICIAL_CODE = 1;
	private static final int TAKE_PHOTO_FINAL_CODE = 2;
	
	 @Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			 return inflater.inflate(R.layout.revisionposicion_activity, container,false);
		 }
		 
		 public static int VISTA_CARGADA=0;
		 
		 @Override public void onActivityCreated(Bundle savedInstanceState) {
	         super.onActivityCreated(savedInstanceState);
	         inicializarRecursos();
	         this.validarConexionInternet=false;
	         
	         //if(VISTA_CARGADA==0){
		 		VISTA_CARGADA=1;
		 		application = (MyApplication) getActivity().getApplicationContext();
		 		evaluacion = application.evaluacionActual;
		 		cliente = application.cliente;
		 		txtViewFecha.setText(ConstantesApp.formatFecha(evaluacion.fechaCreacion));
		 		processAsync();
		 		
	         //}
		 }
		 
		 
		 
		 @Override
		protected void process() {
			// TODO Auto-generated method stub
			 oportunidades = new EfficientAdapter(this,cliente, evaluacion.posiciones);
		}

		

		@Override
		protected void processOk() {
			// TODO Auto-generated method stub
			setListAdapter(oportunidades);
			super.processOk();
		}

		private PosicionTO posicionCompromisoFotoTO;
		
		
		@Override
		public void onActivityResult(int requestCode, int resultCode,Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			
				if (resultCode == Activity.RESULT_OK) {
		    		switch(requestCode){
		    			case TAKE_PHOTO_INICIAL_CODE:{
		    				savePhoto(TAKE_PHOTO_INICIAL_CODE);
		    				break;
		    			}
		    			case TAKE_PHOTO_FINAL_CODE:{
		    				savePhoto(TAKE_PHOTO_FINAL_CODE);
		    				break;
		    			}
		    		}
	
		    }
		}
		
		public void savePhoto(int accion){
			if(TAKE_PHOTO_INICIAL_CODE==accion)
			{
				this.posicionCompromisoFotoTO.fotoInicial = file_name;
				fotoBLL.save(file_name);
			}
			else
			{
				this.posicionCompromisoFotoTO.fotoInicial = file_name;
				fotoBLL.save(file_name);
			}
		}



		public void takePhoto(int accion,PosicionTO posicionTO ){
	    	
	    	this.posicionCompromisoFotoTO = posicionTO;
	    	file_name = UploadFileUtil.GenerarFileName(12,"jpg");
	    	 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    	intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(ConstantesApp.getTempFile(getActivity(),file_name))); 
	    	intent.putExtra(MediaStore.EXTRA_MEDIA_TITLE, "TITULO");
	    	//getActivity().startActivityForResult(intent, accion);
	    	
	    	startActivityForResult(intent, accion);
	    
	    }

		

		public static class EfficientAdapter extends ArrayAdapter<PosicionTO>{
			
		    
		    
			 private final List<PosicionTO> detalle;
			 private final Activity context;
			 private final ClienteTO cliente;
			 private final ClosePosicion_Activity posicion_Activity;
			 
			  public EfficientAdapter(ClosePosicion_Activity posicion_Activity,ClienteTO cliente,List<PosicionTO> detalle){
					super(posicion_Activity.getActivity(), R.layout.closeposicion_content, detalle);
					this.detalle = detalle;
					this.context = posicion_Activity.getActivity();
					this.cliente=cliente;
					this.posicion_Activity=posicion_Activity;
				}
			  
				public int getCount() {
					// TODO Auto-generated method stub
					if (detalle == null) {
						return 0;
					} else {
						return detalle.size();
					}
				}
				
				public PosicionTO getItem(int position) {
					// TODO Auto-generated method stub
					return this.detalle.get(position);
				}
				
				public long getItemId(int position) {
					// TODO Auto-generated method stub
					return 0;
				}
				
				  public View getView(final int position, View convertView, ViewGroup parent) {

						View view = null;	
						
						if (convertView == null) {
							
							
							LayoutInflater inflator = context.getLayoutInflater();
							view = inflator.inflate(R.layout.closeposicion_content, null);
							
							
							final ViewHolder holder = new ViewHolder();
							
							holder.TextViewRpsta = (TextView) view.findViewById(R.id.TextViewRpsta);
							holder.txViewPuntos = (TextView) view.findViewById(R.id.txViewPuntos);
							
							holder.btnFotoInicial = (Button) view.findViewById(R.id.btnFotoInicial);
							holder.btnFotoExito = (Button) view.findViewById(R.id.btnFotoExito);
							holder.btnFotoFinal = (Button) view.findViewById(R.id.btnFotoFinal);
							
							holder.txViewRed = (TextView) view.findViewById(R.id.txViewRed);
							holder.txViewMaximo = (TextView) view.findViewById(R.id.txViewMaximo);				
							
						
							
							holder.txViewAccComp = (TextView) view.findViewById(R.id.txViewAccComp);
							holder.txEditFecha = (TextView) view.findViewById(R.id.txEditFecha);
							holder.chkCumplio = (TextView) view.findViewById(R.id.chkCumplio);
						
							
							holder.btnFotoExito.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									PosicionTO posicionCompromisoTO = (PosicionTO) holder.TextViewRpsta.getTag();
									
									if(posicionCompromisoTO.codigoVariable.compareToIgnoreCase(ConstantesApp.VARIABLE_RED_ESTANDAR_ANAQUEL) == 0)
									{
										
										
										Intent intent = new Intent(context,VerCompromisosClose_Activity.class);
										intent.putExtra(VerCompromisosOpen_Activity.POSICION_KEY, 0);
										context.startActivity(intent);
										
									}
									else
									{
										Intent intent = new Intent(context,ListarFotoExito_Activity.class);
										intent.putExtra(ListarFotoExito_Activity.ID_CLUSTER, cliente.cluster);
										context.startActivity(intent);
									}
								}
							});
							
							
							holder.btnFotoInicial.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									final PosicionTO posicionCompromisoTO = (PosicionTO) holder.TextViewRpsta.getTag();
									
									if((posicionCompromisoTO.fotoInicial==null)||(TextUtils.isEmpty(posicionCompromisoTO.fotoInicial))||(posicionCompromisoTO.fotoInicial.compareTo("")==0)){					
										MessageBox.showSimpleDialog(context, "Error", "No existe foto registrada.", "Aceptar", new android.content.DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog, int which) {
												// TODO Auto-generated method stub
											}
										});	
									}else{

										Intent intent = new Intent("lindley.desarrolloxcliente.verfoto");
										/*intent.putExtra(WebViewVerFoto_Activity.NOMBRE_FOTO, posicionCompromisoTO.fotoInicial);
										intent.putExtra(WebViewVerFoto_Activity.TITULO_FOTO, "Foto Inicial Compromiso.");*/
										intent.putExtra(VerFoto_Activity.FILE_NAME, posicionCompromisoTO.fotoInicial);
										context.startActivity(intent);

								    }						
							     }
							});
							
							holder.btnFotoFinal.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									final PosicionTO posicionCompromisoTO = (PosicionTO) holder.TextViewRpsta.getTag();
									
									if((posicionCompromisoTO.fotoFinal==null)||(TextUtils.isEmpty(posicionCompromisoTO.fotoFinal))||(posicionCompromisoTO.fotoFinal.compareTo("")==0)){					
										MessageBox.showSimpleDialog(context, "Error", "No existe foto registrada.", "Aceptar", new android.content.DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog, int which) {
												// TODO Auto-generated method stub
											}
										});	
									}else{

										Intent intent = new Intent("lindley.desarrolloxcliente.verfoto");
										intent.putExtra(VerFoto_Activity.FILE_NAME, posicionCompromisoTO.fotoFinal);
										//intent.putExtra(WebViewVerFoto_Activity.NOMBRE_FOTO, posicionCompromisoTO.fotoFinal);
										//intent.putExtra(WebViewVerFoto_Activity.TITULO_FOTO, "Foto Final Compromiso.");
										context.startActivity(intent);

								    }		
									/*
									if((posicionCompromisoTO.fotoFinal==null)||(TextUtils.isEmpty(posicionCompromisoTO.fotoFinal)) || (posicionCompromisoTO.fotoFinal.trim().equalsIgnoreCase(""))){		
										posicion_Activity.takePhoto(TAKE_PHOTO_FINAL_CODE, posicionCompromisoTO);
									}else{

										MessageBox.showConfirmDialog(context, "Confirmacion", "ÀDesea reemplazar la foto?", "Si",
												new android.content.DialogInterface.OnClickListener() {
											
											public void onClick(DialogInterface dialog, int which) {
												// TODO Auto-generated method stub	
												posicion_Activity.takePhoto(TAKE_PHOTO_FINAL_CODE, posicionCompromisoTO);
											}
											
										}, "No", new android.content.DialogInterface.OnClickListener() {

											public void onClick(DialogInterface dialog, int which) {
												// TODO Auto-generated method stub
												Intent intent = new Intent("lindley.desarrolloxcliente.verfoto");
												intent.putExtra(VerFoto_Activity.FILE_NAME, posicionCompromisoTO.fotoFinal);
												context.startActivity(intent);
												
											}
											
										});  
									}*/
								}
							});
							
							
							holder.txViewAccComp.addTextChangedListener(new TextWatcher() {
								
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
									PosicionTO posicionCompromisoTO = (PosicionTO) holder.TextViewRpsta.getTag();
									posicionCompromisoTO.observacion=s.toString();
								}
							});
							
							/*holder.chkCumplio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
								
								@Override
								public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
									// TODO Auto-generated method stub
									PosicionTO posicionCompromisoTO = (PosicionTO) holder.TextViewRpsta.getTag();
									if(isChecked)
										posicionCompromisoTO.confirmacion=ConstantesApp.RESPUESTA_SI;
									else
										posicionCompromisoTO.confirmacion=ConstantesApp.RESPUESTA_NO;
								}
							});*/
							
							view.setTag(holder);
					    	holder.TextViewRpsta.setTag(this.detalle.get(position));
							
						}else{
							view = convertView;
							((ViewHolder) view.getTag()).TextViewRpsta.setTag(this.detalle.get(position));
						}
						
						ViewHolder holder = (ViewHolder) view.getTag();
						
						PosicionTO posicionTO = getItem(position);
						
						if(posicionTO.activosLindley.equals(ConstantesApp.RESPUESTA_SI))
					    	  holder.TextViewRpsta.setText(ConstantesApp.RESPUESTA_SI_LARGA);
					      else
					    	  holder.TextViewRpsta.setText(ConstantesApp.RESPUESTA_NO_LARGA);
						
						holder.txViewAccComp.setText(posicionTO.observacion);
						holder.txViewRed.setText(posicionTO.sovir);
						holder.txViewMaximo.setText(posicionTO.sovirMaximo);
						holder.txViewPuntos.setText(posicionTO.puntosSugeridos);
						holder.txEditFecha.setText(ConstantesApp.formatFecha(posicionTO.fechaCompromiso));
						
						
						if(posicionTO.codigoVariable.compareToIgnoreCase(ConstantesApp.VARIABLE_RED_ESTANDAR_ANAQUEL) == 0)
						{
							holder.btnFotoExito.setText(R.string.btnExitoText);
						}else{
							holder.btnFotoExito.setText(R.string.ver_fotoExito);
						}
						
						
						if(ConstantesApp.isSI(posicionTO.confirmacion)){
							holder.chkCumplio.setText(ConstantesApp.RESPUESTA_SI_LARGA);
						}else{
							holder.chkCumplio.setText(ConstantesApp.RESPUESTA_NO_LARGA);
						}
						
						
						return view;
				  }
				  
				  
				   static class ViewHolder {   
				    	TextView TextViewRpsta;
				    	TextView txViewRed;
				    	TextView txViewMaximo;
				    	TextView txViewPuntos;  	
				    	Button   btnFotoInicial;
				    	Button 	 btnFotoExito;
				    	Button 	 btnFotoFinal;
				    	TextView txViewAccComp;
				    	TextView txEditFecha;
				    	TextView chkCumplio;
				    }
			  
		 }
}
