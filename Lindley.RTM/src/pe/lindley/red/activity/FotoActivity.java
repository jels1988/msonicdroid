package pe.lindley.red.activity;

import com.google.inject.Inject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import pe.lindley.activity.R;
import pe.lindley.red.ws.service.ConsultarFotoProxy;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectView;

public class FotoActivity extends ActivityBase{

	@InjectView(R.id.txtNomFoto) TextView txtNomFoto;
	@InjectView(R.id.imgFotoRed) ImageView imgFotoRed;
	@Inject ConsultarFotoProxy consultarFotoProxy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.red_foto);
		processAsync();
	}
	@Override
	protected void process() {
		// TODO Auto-generated method stub
		consultarFotoProxy.setPeriodo("");
		consultarFotoProxy.execute();
	}
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		boolean isExitoGiro = consultarFotoProxy.isExito();
		
		if (isExitoGiro) {
			int status = consultarFotoProxy.getResponse().getStatus();
			if (status == 0) {
				txtNomFoto.setText(consultarFotoProxy.getResponse().getCaption());
				byte[] data = Base64.decode(consultarFotoProxy.getResponse().getFile(), Base64.DEFAULT);
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
				imgFotoRed.setImageBitmap(bitmap);
			}
			else  {
				showToast(consultarFotoProxy.getResponse().getDescripcion());
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
		showToast(error_generico_process);
		super.processError();
	}

}
