package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.PedidosServiceRequest;
import pe.lindley.mmil.titanium.ws.bean.PedidosServiceResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class PedidoServiceProxy extends ProxyBase<PedidosServiceResponse> {

@InjectResource(R.string.urlwsMMILVendedor)protected String urlWS;
	
	public String codigoDeposito;
	public String codigoVendedor;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarPedidos";
	}
	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		PedidosServiceRequest pedidosServiceRequest = new PedidosServiceRequest();
		pedidosServiceRequest.codigoDeposito = codigoDeposito;
		pedidosServiceRequest.codigoVendedor = codigoVendedor;
		
		String request = JSONHelper.serializar(pedidosServiceRequest);
		return request;
	}

	@Override
	protected PedidosServiceResponse responseText(String json) {
		// TODO Auto-generated method stub
		PedidosServiceResponse response = JSONHelper.desSerializar(json,PedidosServiceResponse.class);
		return response;
	}

}
