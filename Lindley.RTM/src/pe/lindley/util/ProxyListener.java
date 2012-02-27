package pe.lindley.util;
import pe.lindley.util.ResponseBase;

public interface ProxyListener<T extends ResponseBase> {
	void onFinish(T loginResponse);
}
