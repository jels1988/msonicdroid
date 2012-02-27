package pe.lindley.util;


import java.io.IOException;
import android.util.Log;
import pe.lindley.util.HttpPoster;
import pe.lindley.util.HttpRestDownloadTask;
import pe.lindley.util.ResponseBase;


public abstract class ProxyBase<T extends ResponseBase> {

	protected abstract String getUrl();

	public ProxyListener<T> proxyListener;
	
	public void setProxyListener(ProxyListener<T> proxyListener) {
		this.proxyListener = proxyListener;
	}

	protected void notifyListener(T responseBase) {
		if (null != proxyListener) {
			proxyListener.onFinish(responseBase);
		}
	}
	
	protected abstract String requestText();
	protected abstract T responseText(String json);
	
	private T response;
	private boolean exito;
	

	public boolean isExito() {
		return exito;
	}

	public T getResponse() {
		return response;
	}

	public void executeAsyn(){
			
			HttpRestDownloadTask downloadTask = new HttpRestDownloadTask();
			downloadTask.setUrl(getUrl());

			downloadTask.setHttpRestDownloadTaskListener(new HttpRestDownloadTask.HttpRestDownloadTaskListener() {
				
				//@Override
				public void onFinish(int state, String rp) {
					// TODO Auto-generated method stub
					T responseBase = responseText(rp);
					response = responseBase;
					exito=(state==0);
					notifyListener(responseBase);
				}
			});
			
			String request = requestText();
			downloadTask.execute(request);

	}

	public void execute(){
		
		String rp = null;
		try {
			HttpPoster httpPoster = new HttpPoster();
			httpPoster.setUrl(this.getUrl());
			httpPoster.setRequest(this.requestText());
			httpPoster.invoke();
			rp = httpPoster.getResponse();
			
			T responseBase = responseText(rp);
			response = responseBase;
			exito=true;
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			response = null;
			exito=false;
			Log.e("ProxyBase", "execute", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			response = null;
			exito=false;
			Log.e("ProxyBase", "execute", e);
		}
		
		
	}
	
	
	
}
