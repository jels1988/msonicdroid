package net.msonic.lib;

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

	private String url;
	private String request;
	private String response;
	private String contentType = "application/xml";
	private int timeOut = 60000;
	private int status;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getStatus() {
		return status;
	}

	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public String getResponse() {
		return response;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	 private  String HEADER_BEGIN_TAG = "<string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/\">";
     private  String HEADER_END_TAG = "</string>";
     
	public void invoke() throws ClientProtocolException, IOException {
		
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, this.timeOut);
		HttpConnectionParams.setSoTimeout(httpParams, this.timeOut);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);

		HttpPost httpPost = new HttpPost(this.url);
		httpPost.addHeader("Content-Type", this.contentType);

		Log.d("RQ",request);
		
		StringBuffer sf = new StringBuffer();
		sf.append(HEADER_BEGIN_TAG);
		sf.append(this.request);
		sf.append(HEADER_END_TAG);
		
		StringEntity stringRequest = new StringEntity(sf.toString(),HTTP.UTF_8);
		
		httpPost.setEntity(stringRequest);

		HttpResponse response = httpClient.execute(httpPost);

		this.status = response.getStatusLine().getStatusCode();
		
		Log.d("STATUS",String.valueOf(this.status));
		
		if (HttpStatus.SC_OK == this.status) {
			String rp = stream2String(response.getEntity().getContent());
				
			if(rp!=null){
				Log.d("RS",rp);
				
				sf = new StringBuffer(rp);
				sf.replace(0,HEADER_BEGIN_TAG.length(), "");
				sf.replace(sf.length()-HEADER_END_TAG.length(),sf.length(), "");
				Log.d("RS_FORMAT",sf.toString());
			}else{
				Log.d("RS","null");
				
			}
			
			
			this.response = sf.toString();
		}
	}

	private String stream2String(InputStream input) throws IOException {
		
		BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
		StringBuilder sb = new StringBuilder();
		String line;

		while ((line = buffer.readLine()) != null) {
			sb.append(line);
		}

		return sb.toString();
	}
	
}
