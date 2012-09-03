package lindley.desarrolloxcliente.activity;

import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.inject.Inject;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.MyApplication;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.FotoBLL;
import lindley.desarrolloxcliente.negocio.PosicionBLL;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import net.msonic.lib.MessageBox;
import net.msonic.lib.UploadFileUtil;
import net.msonic.lib.sherlock.ListBaseFragment;

public class Posicion_Activity extends ListBaseFragment  {

	private EvaluacionTO evaluacion;
	private  MyApplication application;
	private ClienteTO cliente;
	
	@Inject PosicionBLL posicionBLL;
	@Inject FotoBLL fotoBLL;
	
	
	
	

	EfficientAdapter oportunidades;
	
	public static String file_name="";
	private static final int TAKE_PHOTO_INICIAL_CODE = 1;
	private static final int TAKE_PHOTO_FINAL_CODE = 2;
	
	 @Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			 return inflater.inflate(R.layout.consultarposicioncompromisoopen_activity, container,false);
		 }
		 
		 private static int VISTA_CARGADA=0;
		 
		 @Override public void onActivityCreated(Bundle savedInstanceState) {
	         super.onActivityCreated(savedInstanceState);
	         this.validarConexionInternet=false;
	         
	         if(VISTA_CARGADA==0){
		 		VISTA_CARGADA=1;
		 		application = (MyApplication) getActivity().getApplicationContext();
		 		evaluacion = application.evaluacion;
		 		cliente = application.cliente;
		 		
		 		processAsync();
		 		
	         }
		 }
		 
		 
		 
		 @Override
		protected void process() {
			// TODO Auto-generated method stub
			 List<PosicionCompromisoTO> detalle = posicionBLL.consultarOportunidadesPoscion(evaluacion.codigoCliente);
			 evaluacion.posiciones = detalle;
			 oportunidades = new EfficientAdapter(this,cliente, detalle);
		}

		

		@Override
		protected void processOk() {
			// TODO Auto-generated method stub
			setListAdapter(oportunidades);
			super.processOk();
		}

		private PosicionCompromisoTO posicionCompromisoFotoTO;
		
		
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



		public void takePhoto(int accion,PosicionCompromisoTO posicionTO ){
	    	
	    	this.posicionCompromisoFotoTO = posicionTO;
	    	file_name = UploadFileUtil.GenerarFileName(12,"jpg");
	    	 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    	intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(ConstantesApp.getTempFile(getActivity(),file_name))); 
	    	intent.putExtra(MediaStore.EXTRA_MEDIA_TITLE, "TITULO");
	    	//getActivity().startActivityForResult(intent, accion);
	    	
	    	startActivityForResult(intent, accion);
	    
	    }

		

		public static class EfficientAdapter extends ArrayAdapter<PosicionCompromisoTO>{
			
		    
		    
			 private final List<PosicionCompromisoTO> detalle;
			 private final Activity context;
			 private final ClienteTO cliente;
			 private final Posicion_Activity posicion_Activity;
			 
			  public EfficientAdapter(Posicion_Activity posicion_Activity,ClienteTO cliente,List<PosicionCompromisoTO> detalle){
					super(posicion_Activity.getActivity(), R.layout.consultarposicioncompromisoopen_content, detalle);
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
				
				public PosicionCompromisoTO getItem(int position) {
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
							view = inflator.inflate(R.layout.consultarposicioncompromisoopen_content, null);
							
							
							final ViewHolder holder = new ViewHolder();
							
							holder.TextViewRpsta = (TextView) view.findViewById(R.id.TextViewRpsta);
							holder.txViewPuntos = (TextView) view.findViewById(R.id.txViewPuntos);
							
							holder.btnFotoInicial = (Button) view.findViewById(R.id.btnFotoInicial);
							holder.btnFotoExito = (Button) view.findViewById(R.id.btnFotoExito);
							
							holder.txViewRed = (TextView) view.findViewById(R.id.txViewRed);
							holder.txViewMaximo = (TextView) view.findViewById(R.id.txViewMaximo);				
							
							holder.btnFecha = (ImageButton) view.findViewById(R.id.btnFecha);
							
							holder.txViewAccComp = (EditText) view.findViewById(R.id.txViewAccComp);
							holder.txEditFecha = (EditText) view.findViewById(R.id.txEditFecha);
							
							holder.btnFecha.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									PosicionCompromisoTO posicionCompromisoTO = (PosicionCompromisoTO) holder.TextViewRpsta.getTag();
									 int[] factores = ConstantesApp.getFechaFactores(posicionCompromisoTO.fechaCompromiso);
									 

									 DatePickerDialog picker = new DatePickerDialog(context, new OnDateSetListener() {
										
										@Override
										public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
											// TODO Auto-generated method stub
											PosicionCompromisoTO posicionCompromisoTO = (PosicionCompromisoTO) holder.TextViewRpsta.getTag();
											String mes = ConstantesApp.RPad(String.valueOf(monthOfYear+1),2,'0');
											String dia = ConstantesApp.RPad(String.valueOf(dayOfMonth),2,'0');
											String fecha = String.format("%s/%s/%s", dia,mes, year);
											posicionCompromisoTO.fechaCompromiso=fecha;
											holder.txEditFecha.setText(posicionCompromisoTO.fechaCompromiso);
										}
									}, factores[0],factores[1], factores[2]);
									 
									 picker.show();
								}
							});
							
							holder.btnFotoExito.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									PosicionCompromisoTO posicionCompromisoTO = (PosicionCompromisoTO) holder.TextViewRpsta.getTag();
									
									if(posicionCompromisoTO.codigoVariable.compareToIgnoreCase(ConstantesApp.CODIGO_ESTANDAR_ANAQUEL) == 0)
									{
										MyApplication application = (MyApplication)context.getApplicationContext();
										application.compromisoPosicion=position;
										
										/*
										application.listCompromiso = posicionCompromisoTO.listCompromisos;
										if(application.listCompromiso == null)
											application.listCompromiso = new ArrayList<CompromisoPosicionTO>();
										*/
										Intent intent = new Intent(context,VerCompromisosOpen_Activity.class);
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
									final PosicionCompromisoTO posicionCompromisoTO = (PosicionCompromisoTO) holder.TextViewRpsta.getTag();
									
									if((posicionCompromisoTO.fotoInicial==null)||(posicionCompromisoTO.fotoInicial.compareTo("")==0)){
										
										
										posicion_Activity.takePhoto(TAKE_PHOTO_INICIAL_CODE, posicionCompromisoTO);
										//((Posicion_Activity)context).takePhoto(TAKE_PHOTO_INICIAL_CODE, posicionCompromisoTO);						
										
										
									}else{

										MessageBox.showConfirmDialog(context, "Confirmacion", "ÀDesea reemplazar la foto?", "Si",
												new android.content.DialogInterface.OnClickListener() {
											
											public void onClick(DialogInterface dialog, int which) {
												// TODO Auto-generated method stub	
												posicion_Activity.takePhoto(TAKE_PHOTO_INICIAL_CODE, posicionCompromisoTO);
												//((Posicion_Activity)context).takePhoto(TAKE_PHOTO_INICIAL_CODE, posicionCompromisoTO);
												
											}
											
										}, "No", new android.content.DialogInterface.OnClickListener() {

											public void onClick(DialogInterface dialog, int which) {
												// TODO Auto-generated method stub
												
												
												Intent intent = new Intent("lindley.desarrolloxcliente.verfoto");
												intent.putExtra(VerFoto_Activity.FILE_NAME, posicionCompromisoTO.fotoInicial.toString());
												context.startActivity(intent);
											}
											
										});  

								    }						
							     }
							});
							 
							view.setTag(holder);
					    	holder.TextViewRpsta.setTag(this.detalle.get(position));
							
						}else{
							view = convertView;
							((ViewHolder) view.getTag()).TextViewRpsta.setTag(this.detalle.get(position));
						}
						
						ViewHolder holder = (ViewHolder) view.getTag();
						
						PosicionCompromisoTO posicionTO = getItem(position);
						
						if(posicionTO.respuesta.equals(ConstantesApp.RESPUESTA_SI))
					    	  holder.TextViewRpsta.setText(ConstantesApp.RESPUESTA_SI_LARGA);
					      else
					    	  holder.TextViewRpsta.setText(ConstantesApp.RESPUESTA_NO_LARGA);
						
						holder.txViewAccComp.setText(posicionTO.observacion);
						holder.txViewRed.setText(posicionTO.red);
						holder.txViewMaximo.setText(posicionTO.ptoMaximo);
						holder.txViewPuntos.setText(posicionTO.puntosSugeridos);
						
						if(posicionTO.codigoVariable.compareToIgnoreCase(ConstantesApp.CODIGO_ESTANDAR_ANAQUEL) == 0)
						{
							holder.btnFotoExito.setText(R.string.btnExitoText);
						}else{
							holder.btnFotoExito.setText(R.string.ver_fotoExito);
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
				    	EditText txViewAccComp;
				    	EditText txEditFecha;
				    	ImageButton 	 btnFecha;
				    }
			  
		 }
}
