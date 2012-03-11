package pe.lindley.prospector.ws.bean;

import java.io.IOException;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.ResponseCache;
import java.net.URI;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class UploadFileResponse extends ResponseCache {

	@Override
	public CacheResponse get(URI arg0, String arg1,
			Map<String, List<String>> arg2) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheRequest put(URI uri, URLConnection connection)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
