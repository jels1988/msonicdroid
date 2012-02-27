package net.msonic.lib;


public interface ProxyListener<T extends ResponseBase> {
	void onFinish(T loginResponse);
}
