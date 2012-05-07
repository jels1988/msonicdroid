package pe.com.agrobanco.sms.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import android.util.Log;

public class HttpPoster {

	private final static String TAG=HttpPoster.class.getName();
	
	public String url;
	public String request;
	public String response;
	public String contentType = "application/xml";
	public int timeOut = 60000;
	private int status;
	
	 private static final String HEADER_BEGIN_TAG = "<string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/\">";
     private static final String HEADER_END_TAG = "</string>";
     
 	public void invoke() throws ClientProtocolException, IOException {
 		
 		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, this.timeOut);
		HttpConnectionParams.setSoTimeout(httpParams, this.timeOut);
		
		DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);

		HttpPost httpPost = new HttpPost(this.url);
		httpPost.addHeader("Content-Type", this.contentType);
		
		Log.d(TAG ," RQ:" + request);
		
		StringBuffer sf = new StringBuffer();
		sf.append(HEADER_BEGIN_TAG);
		sf.append(this.request);
		sf.append(HEADER_END_TAG);
		
		
		StringEntity stringRequest = new StringEntity(sf.toString(),HTTP.UTF_8);
		httpPost.setEntity(stringRequest);
		HttpResponse response = httpClient.execute(httpPost);
		
		this.status = response.getStatusLine().getStatusCode();
		
		Log.d(TAG , " STATUS:" +String.valueOf(this.status));
		
		if (HttpStatus.SC_OK == this.status) {
			String rp = stream2String(response.getEntity().getContent());
			if(rp!=null){
				sf = new StringBuffer(rp);
				sf.replace(0,HEADER_BEGIN_TAG.length(), "");
				sf.replace(sf.length()-HEADER_END_TAG.length(),sf.length(), "");
				Log.d(TAG , " RS_FORMAT:" + sf.toString());
			}else{
				Log.d(TAG , " RS: null");
				
			}
			this.response = sf.toString();
		}
		
 	}
 	
 	private String stream2String(InputStream input) throws IOException {
		
		BufferedReader buffer = new BufferedReader(new InputStreamReader(input),8 * 1024);
		StringBuilder sb = new StringBuilder();
		String line;

		while ((line = buffer.readLine()) != null) {
			sb.append(line);
		}

		return sb.toString();
	}
     
}
