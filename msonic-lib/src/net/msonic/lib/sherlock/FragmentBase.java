package net.msonic.lib.sherlock;


import java.util.HashMap;

import net.msonic.lib.R;
import roboguice.RoboGuice;
import roboguice.activity.event.OnCreateEvent;
import roboguice.event.EventManager;
import roboguice.inject.ContentViewListener;
import roboguice.inject.RoboInjector;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.Provider;

public class FragmentBase extends SherlockFragment   {
	/* @Override
	     public void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);
	         RoboGuice.getInjector(getActivity()).injectMembersWithoutViews(this);
	     }
	 
	     @Override
	     public void onViewCreated(View view, Bundle savedInstanceState) {
	         super.onViewCreated(view, savedInstanceState);
	         RoboGuice.getInjector(getActivity()).injectViewMembers(this);
	     }*/
	
	
	protected ProgressDialog dialogWait;
	@Inject  protected Provider<Context> contextProvider;
	
	
	protected String error_not_network_message; 
	protected String wait_message;
	protected String error_generico_process;
	
	protected EventManager eventManager;
    protected HashMap<Key<?>,Object> scopedObjects = new HashMap<Key<?>,Object>();
    
	
	protected void inicializarRecursos(){
		error_not_network_message = getString(R.string.error_not_network_message);
		wait_message = getString(R.string.wait_message);
		error_generico_process = getString(R.string.error_generico_process);
	}
	
	protected boolean executeAsyncPre(int accion){
		
		return true;
	}
	
	protected void executeAsyncPost(int accion){
		
	}
	

	protected void process(int accion){
		
	}
	
	protected void processOk(int accion){
		if (dialogWait.isShowing())
			dialogWait.dismiss();
	}
	
	protected void processError(int accion){
		if (dialogWait.isShowing())
			dialogWait.dismiss();
	}
	
	final Handler handlerIntOK = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            processOk(msg.what);
        }
    };
    
    
    final Handler handlerIntError = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        	processError(msg.what);
        }
    };
    
	protected final void processAsync(final int accion){
		
		
		if(!executeAsyncPre(accion)) return;
		
		dialogWait = new ProgressDialog(getActivity());
		dialogWait.setIndeterminate(true);
		dialogWait.setMessage(wait_message);
		dialogWait.show();
		
			    
		Thread  thread = new Thread (new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					process(accion);
					
					handlerIntOK.sendEmptyMessage(accion);
					
				}catch (Exception e) {
					// TODO: handle exception
					handlerIntError.sendEmptyMessage(accion);
				}
			}
		});
		thread.start();
	}
	
	protected boolean executeAsyncPre(){
		return true;
	}
	
	protected void executeAsyncPost(){
	}
	
	protected void process(){
		
	}
	
	protected void processOk(){
		if (dialogWait.isShowing())
			dialogWait.dismiss();
	}
	
	protected void processError(){
		if (dialogWait.isShowing())
			dialogWait.dismiss();
	}
	
	
    
	final Handler handlerOK = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        	
            processOk();
        }
    };
    
    final Handler handlerError = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        	processError();
        }
    };

	protected final void processAsync(){
		
		
		if(!isNetworkAvailable()){
			showToast(error_not_network_message);
			return;
		}
		
		if(!executeAsyncPre()) return;
		
		dialogWait = new ProgressDialog(getActivity());
		dialogWait.setIndeterminate(true);
		dialogWait.setMessage(wait_message);
		dialogWait.show();
		
			    
		Thread  thread = new Thread (new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					process();
					
					handlerOK.sendEmptyMessage(0);
					  
					
				}catch (Exception e) {
					// TODO: handle exception
					handlerError.sendEmptyMessage(0);
				}
			}
		});
		thread.start();
	}
	
	public void showToast(CharSequence message){
		Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
		toast.show();
	}
	
	public boolean isNetworkAvailable() {
		   Context context = getActivity().getApplicationContext();
		   ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		   if (connectivity == null) {
		      return false;
		   } else {
		      NetworkInfo[] info = connectivity.getAllNetworkInfo();
		      if (info != null) {
		         for (int i = 0; i < info.length; i++) {
		            if (info[i].getState() == NetworkInfo.State.CONNECTED) {
		               return true;
		            }
		         }
		      }
		   }
		   return false;
		}
	
	protected void ocultarEspera(){
    	if (dialogWait.isShowing())
			dialogWait.dismiss();
    }
	
    protected void mostrarEspera(){
    	dialogWait = new ProgressDialog(getActivity());
		dialogWait.setIndeterminate(true);
		dialogWait.setMessage(wait_message);
		dialogWait.show();
    }

    @Inject
    ContentViewListener ignored; // BUG find a better place to put this

    @Override
	public void onCreate(Bundle savedInstanceState) {
        final RoboInjector injector = RoboGuice.getInjector(getActivity());
        eventManager = injector.getInstance(EventManager.class);
        injector.injectMembersWithoutViews(this);
        super.onCreate(savedInstanceState);
        eventManager.fire(new OnCreateEvent(savedInstanceState));
    }

}
