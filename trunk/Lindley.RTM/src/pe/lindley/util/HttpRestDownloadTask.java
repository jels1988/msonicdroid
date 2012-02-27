package pe.lindley.util;

import java.io.IOException;
import android.os.AsyncTask;
import android.util.Log;

public class HttpRestDownloadTask extends AsyncTask<String, Void, String> {


	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public interface HttpRestDownloadTaskListener {
		void onFinish(int state, String rp);
	}

	private HttpRestDownloadTaskListener httpRestDownloadTaskListener;

	public HttpRestDownloadTaskListener getHttpRestDownloadTaskListener() {
		return httpRestDownloadTaskListener;
	}

	public void setHttpRestDownloadTaskListener(
			HttpRestDownloadTaskListener httpRestDownloadTaskListener) {
		this.httpRestDownloadTaskListener = httpRestDownloadTaskListener;
	}


	
	@Override
	protected void onPreExecute() {

	}

	@Override
	protected String doInBackground(String... arg0) {
		
		 /*try {
		      InetAddress i = InetAddress.getByName(this.getUrl());
		    } catch (UnknownHostException e1) {
		      e1.printStackTrace();
		    }*/

		// TODO Auto-generated method stub
		String rp = null;
		try {
			HttpPoster httpPoster = new HttpPoster();
			httpPoster.setUrl(this.getUrl());
			httpPoster.setRequest(arg0[0]);
			httpPoster.invoke();

			rp = httpPoster.getResponse();

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			cancel(true);
			Log.e("HttpRestDownloadTask", "doInBackground", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("HttpRestDownloadTask", "doInBackground", e);
			cancel(true);
		}

		return rp;
	}

	@Override
	protected void onPostExecute(String result) {

		if (isCancelled()) {
			result = null;
			notifyListener(1, null);
		}

		if (result != null) {
			notifyListener(0, result);
		}

	}

	private void notifyListener(int state, String response) {
		if (null != httpRestDownloadTaskListener) {
			httpRestDownloadTaskListener.onFinish(state, response);
		}
	}

}
