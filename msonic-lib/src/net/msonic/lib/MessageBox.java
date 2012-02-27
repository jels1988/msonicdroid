package net.msonic.lib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;


public class MessageBox {
	
	public static void showSimpleDialog(Context context, 
										String title,
										String message,
										String buttonTitle,
										OnClickListener accion){
		
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);

		dlgAlert.setMessage(message);
		dlgAlert.setTitle(title);
		dlgAlert.setPositiveButton(buttonTitle,accion);
		dlgAlert.setCancelable(true);
		dlgAlert.create().show();

	}
	
	public static void showConfirmDialog(Context context, 
			String title,
			String message,
			String buttonOKTitle,
			OnClickListener accionOK,
			String buttonCancelTitle,
			OnClickListener accionCancel){

	AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
	
	dlgAlert.setMessage(message);
	dlgAlert.setTitle(title);
	dlgAlert.setPositiveButton(buttonOKTitle,accionOK);
	dlgAlert.setNegativeButton(buttonCancelTitle, accionCancel);
	dlgAlert.setCancelable(true);
	dlgAlert.create().show();
	
	}
	
	
}
