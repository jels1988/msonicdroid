package net.msonic.gpsservice;

import java.util.List;

import net.msonic.gps.ws.service.GPSServiceProxy;

import com.google.inject.Module;

import roboguice.application.RoboApplication;
import roboguice.config.AbstractAndroidModule;

public class GPSServiceApplication extends RoboApplication  {

	@Override
	protected void addApplicationModules(List<Module> modules){	
		
		modules.add(new AbstractAndroidModule(){
			@Override
			protected void configure() {

				requestStaticInjection(GPSServiceProxy.class);
			}
		});
	}
}
