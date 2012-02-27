package pe.lindley.om.dao;

import java.util.ArrayList;

import pe.lindley.om.to.EventoTO;
import pe.lindley.util.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class EventoDAO {

	@Inject
	protected DBHelper dbHelper;

	public ArrayList<EventoTO> listWithEventos(long ordenTrabajoId,long numeroOrden){
		ArrayList<EventoTO> listado = new ArrayList<EventoTO>();
		
		String SQL = "SELECT ifnull(nueve2,eventoid) as numeve, nuord, tpeve," +
					"dstpe,tpmot,dstpm,tprcr,usrcr,dsusc,fecre,hrcre,tprea,usrea,dsusa,tpreu," +
					"usreu, dsusu, femod,hrmod,dsobs " +
					" FROM OM_evento  where nuord = ?";
		String[] parametros = new String[] {String.valueOf(ordenTrabajoId )};
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		EventoTO eventoTO;
		
		while (cursor.moveToNext()) {
			eventoTO = new EventoTO();
			eventoTO.setNuEve(cursor.getInt(cursor.getColumnIndex("numeve")));
			
			eventoTO.setNuOrd(numeroOrden);
			
			eventoTO.setDsTpE(cursor.getString(cursor.getColumnIndex("dstpe")));
			eventoTO.setTpEve(cursor.getString(cursor.getColumnIndex("tpeve")));
			
			eventoTO.setTpMot(cursor.getString(cursor.getColumnIndex("tpmot")));
			eventoTO.setDsTpM(cursor.getString(cursor.getColumnIndex("dstpm")));
			eventoTO.setTpRCr(cursor.getString(cursor.getColumnIndex("tprcr")));
			eventoTO.setUsRCr(cursor.getString(cursor.getColumnIndex("usrcr")));
			eventoTO.setDsUsC(cursor.getString(cursor.getColumnIndex("dsusc")));
			eventoTO.setFeCre(cursor.getString(cursor.getColumnIndex("fecre")));
			eventoTO.setHrCre(cursor.getString(cursor.getColumnIndex("hrcre")));
			eventoTO.setTpReA(cursor.getString(cursor.getColumnIndex("tprea")));
			eventoTO.setUsReA(cursor.getString(cursor.getColumnIndex("usrea")));
			eventoTO.setDsUsA(cursor.getString(cursor.getColumnIndex("dsusa")));
			eventoTO.setTpReU(cursor.getString(cursor.getColumnIndex("tpreu")));
			eventoTO.setUsReU(cursor.getString(cursor.getColumnIndex("usreu")));
			eventoTO.setDsUsU(cursor.getString(cursor.getColumnIndex("dsusu")));
			eventoTO.setFeMod(cursor.getString(cursor.getColumnIndex("femod")));
			eventoTO.setHrMod(cursor.getString(cursor.getColumnIndex("hrmod")));
			eventoTO.setDsObs(cursor.getString(cursor.getColumnIndex("dsobs")));
			
			listado.add(eventoTO);
		}
		
		cursor.close();
		return listado;
	}
	public ArrayList<EventoTO> list(long ordenTrabajoId){
		
		ArrayList<EventoTO> listado = new ArrayList<EventoTO>();
		
		String SQL = "SELECT dstpm,dsusc,dsusa,fecre,hrcre,dsobs FROM OM_evento  where nuord = ?";
		String[] parametros = new String[] {String.valueOf(ordenTrabajoId )};
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		EventoTO eventoTO;
		
		while (cursor.moveToNext()) {
			eventoTO = new EventoTO();
			eventoTO.setDsTpM(cursor.getString(cursor.getColumnIndex("dstpm")));
			eventoTO.setDsUsC(cursor.getString(cursor.getColumnIndex("dsusc")));
			eventoTO.setFeCre(cursor.getString(cursor.getColumnIndex("fecre")));
			eventoTO.setHrCre(cursor.getString(cursor.getColumnIndex("hrcre")));
			eventoTO.setDsUsA(cursor.getString(cursor.getColumnIndex("dsusa")));
			eventoTO.setDsObs(cursor.getString(cursor.getColumnIndex("dsobs")));
			
			listado.add(eventoTO);
		}
		
		cursor.close();
		return listado;
	}
	
	public void save(long nuOrd,final EventoTO eventoTO){
		
		ContentValues parametros = new ContentValues();
		
	    parametros.put("NUORD", nuOrd);
        parametros.put("TPEVE", eventoTO.getTpEve());
        parametros.put("DSTPE", eventoTO.getDsTpE());
        parametros.put("TPMOT", eventoTO.getTpMot());
        parametros.put("DSTPM", eventoTO.getDsTpM());
        parametros.put("TPRCR", eventoTO.getTpRCr());
        parametros.put("USRCR", String.format("%10s", eventoTO.getUsRCr()).replace(' ', '0'));
        parametros.put("DSUSC", eventoTO.getDsUsC());
        parametros.put("FECRE", eventoTO.getFeCre());
        parametros.put("HRCRE", eventoTO.getHrCre());
        parametros.put("TPREA", eventoTO.getTpReA());
        parametros.put("USREA", String.format("%10s", eventoTO.getUsReA()).replace(' ', '0'));
        parametros.put("DSUSA", eventoTO.getDsUsA());
        parametros.put("TPREU", eventoTO.getTpReU());
        parametros.put("USREU", String.format("%10s", eventoTO.getUsReU()).replace(' ', '0'));
        parametros.put("DSUSU", eventoTO.getDsUsU());
        parametros.put("FEMOD", eventoTO.getFeMod());
        parametros.put("HRMOD", eventoTO.getHrMod());
        parametros.put("DSOBS", eventoTO.getDsObs());
        
        if(eventoTO.getNuEve2()!=null && eventoTO.getNuEve2().equalsIgnoreCase("")){
        	//parametros.put("nueve2", "");
        }else{
        	parametros.put("nueve2", eventoTO.getNuEve2());
        }
        dbHelper.getDataBase().insertOrThrow("om_evento", null, parametros);
  
	}
	
	
	public void deleteEventos(){
		dbHelper.getDataBase().execSQL("delete FROM om_evento");
	}
	public void deleteEventos(long ordenTrabajoId){
		String[] args = new String[] { String.valueOf(ordenTrabajoId)};
		dbHelper.getDataBase().execSQL("delete FROM om_evento where nuord = ?", args);
	}
}
