package net.msonic.gps.ws.service;



import roboguice.inject.InjectResource;
import net.msonic.gps.ws.bean.GpsPosReponse;
import net.msonic.gps.ws.bean.GpsPosRequest;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class GPSServiceProxy extends ProxyBase<GpsPosReponse> {

	@InjectResource(net.msonic.gpsservice.R.string.urlwsGps) protected String urlWS;
	
	
	
	private String telefono;
	private String latitud;
	private String longitud;
	private String fixes;
	private String altitud;
	private String accuracy;
	private String timeStamp;

	
	public String getFixes() {
		return fixes;
	}

	public void setFixes(String fixes) {
		this.fixes = fixes;
	}

	public String getAltitud() {
		return altitud;
	}

	public void setAltitud(String altitud) {
		this.altitud = altitud;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	

	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		
		
		return urlWS + "/GuardarPosicion";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		
		GpsPosRequest gpsPosRequest = new GpsPosRequest();
		gpsPosRequest.setLatitud(latitud);
		gpsPosRequest.setLongitud(longitud);
		gpsPosRequest.setTelefono(telefono);
		gpsPosRequest.setFixes(fixes);
		gpsPosRequest.setAltitud(altitud);
		gpsPosRequest.setAccuracy(accuracy);
		gpsPosRequest.setTimeStamp(timeStamp);
		
		String request = JSONHelper.serializar(gpsPosRequest);
		return request;
	}

	@Override
	protected GpsPosReponse responseText(String arg0) {
		// TODO Auto-generated method stub
		GpsPosReponse gpsPosReponse = JSONHelper.desSerializar(arg0, GpsPosReponse.class);
		return gpsPosReponse;
		
	}

}
