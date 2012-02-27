package pe.lindley.om.dao;

import java.util.ArrayList;

import pe.lindley.om.to.EventoTO;
import pe.lindley.om.to.OrdenTrabajoTO;
import pe.lindley.util.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class OrdenTrabajoDAO {


	@Inject protected DBHelper dbHelper;
	@Inject protected EventoDAO eventoDAO;
	
	public String getEstadoOrden(long ordenTrabajoId){
		
		String SQL = "SELECT ESORD FROM OM_ORDENTRABAJO WHERE ordentrabajoid = ?";
		String[] parametros = new String[] { String.valueOf(ordenTrabajoId)};
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		String estado="";
		
		if(cursor.moveToNext()){
			estado = cursor.getString(cursor.getColumnIndex("esord"));
		}
		
		cursor.close();
		
		return estado;
	}
	
	public void deleteAllPendientesWithEventos(){
		dbHelper.getDataBase().execSQL("delete from om_evento where nuord in (select ordentrabajoId from om_ordentrabajo WHERE (cdsta IN ('0','1') and flagmod=1) or (cdsta=0 and flagmod=0))");
		dbHelper.getDataBase().execSQL("delete from om_ordentrabajo WHERE (cdsta IN ('0','1') and flagmod=1) or (cdsta=0 and flagmod=0)");
	}
	
	public ArrayList<OrdenTrabajoTO> listAllWithEventos(){
		String SQL = "SELECT ordentrabajoid,CASE ifnull(nuord2,0) WHEN 0 then ordentrabajoid else nuord2  end as nuord,TPCON,DSTPC,TPORD,DSTPO,STORD,DSSTO,MTORD,DSMTO,CDPRI,DSPRI,ESORD," +
        "DSESO,TPRCR,USRCR,DSUSC,FECRE,HRCRE,TPREA,USREA,DSUSA,TPREU,USREU, " +
        "DSUSU,FEMOD,HRMOD,CDCLI,CDSTA,DSOBS " +
		"FROM OM_ORDENTRABAJO WHERE (cdsta IN ('0','1') and flagmod=1) or (cdsta=0 and flagmod=0)";
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL,null);
		
		OrdenTrabajoTO ordenTrabajoTO=null;
		
		ArrayList<OrdenTrabajoTO> ordenes = new ArrayList<OrdenTrabajoTO>();
		
		while(cursor.moveToNext()){
		
			ordenTrabajoTO = new OrdenTrabajoTO();
			ordenTrabajoTO.setNuOrd(cursor.getLong(cursor.getColumnIndex("nuord")));
			ordenTrabajoTO.setTpCon(cursor.getString(cursor.getColumnIndex("tpcon")));
			ordenTrabajoTO.setDsTpC(cursor.getString(cursor.getColumnIndex("dstpc")));
			ordenTrabajoTO.setTpOrd(cursor.getString(cursor.getColumnIndex("tpord")));
			ordenTrabajoTO.setDsTpO(cursor.getString(cursor.getColumnIndex("dstpo")));
			ordenTrabajoTO.setStOrd(cursor.getString(cursor.getColumnIndex("stord")));
			ordenTrabajoTO.setDsStO(cursor.getString(cursor.getColumnIndex("dssto")));
			ordenTrabajoTO.setMtOrd(cursor.getString(cursor.getColumnIndex("mtord")));
			ordenTrabajoTO.setDsMtO(cursor.getString(cursor.getColumnIndex("dsmto")));
			ordenTrabajoTO.setCdPri(cursor.getString(cursor.getColumnIndex("cdpri")));
			ordenTrabajoTO.setDsPri(cursor.getString(cursor.getColumnIndex("dspri")));
			ordenTrabajoTO.setEsOrd(cursor.getString(cursor.getColumnIndex("esord")));
			ordenTrabajoTO.setDsEsO(cursor.getString(cursor.getColumnIndex("dseso")));
			ordenTrabajoTO.setTpRCr(cursor.getString(cursor.getColumnIndex("tprcr")));
			ordenTrabajoTO.setUsRCr(cursor.getString(cursor.getColumnIndex("usrcr")));
			ordenTrabajoTO.setDsUsC(cursor.getString(cursor.getColumnIndex("dsusc")));
			ordenTrabajoTO.setFeCre(cursor.getString(cursor.getColumnIndex("fecre")));
			ordenTrabajoTO.setHrCre(cursor.getString(cursor.getColumnIndex("hrcre")));
			ordenTrabajoTO.setTpReA(cursor.getString(cursor.getColumnIndex("tprea")));
			ordenTrabajoTO.setUsReA(cursor.getString(cursor.getColumnIndex("usrea")));
			ordenTrabajoTO.setDsUsA(cursor.getString(cursor.getColumnIndex("dsusa")));
			ordenTrabajoTO.setTpReU(cursor.getString(cursor.getColumnIndex("tpreu")));
			ordenTrabajoTO.setUsReU(cursor.getString(cursor.getColumnIndex("usreu")));
			ordenTrabajoTO.setDsUsU(cursor.getString(cursor.getColumnIndex("dsusu")));
			ordenTrabajoTO.setFeMod(cursor.getString(cursor.getColumnIndex("femod")));
			ordenTrabajoTO.setHrMod(cursor.getString(cursor.getColumnIndex("hrmod"))); 
			ordenTrabajoTO.setCdCli(cursor.getString(cursor.getColumnIndex("cdcli")));
			ordenTrabajoTO.setCdStA(cursor.getInt(cursor.getColumnIndex("cdsta")));
			ordenTrabajoTO.setDsObs(cursor.getString(cursor.getColumnIndex("dsobs")));
			
			long ordenTrabajoId = cursor.getLong(cursor.getColumnIndex("ordentrabajoid"));
			ArrayList<EventoTO> eventos = eventoDAO.listWithEventos(ordenTrabajoId, ordenTrabajoTO.getNuOrd());
			ordenTrabajoTO.setEventos(eventos);
			
			ordenes.add(ordenTrabajoTO);
		
		}
		cursor.close();
		
		
		return ordenes;
	}
	
	public OrdenTrabajoTO listWithEventos(long ordenTrabajoId){
		String SQL = "SELECT ordentrabajoid,CASE ifnull(nuord2,0) WHEN 0 then ordentrabajoid else nuord2  end as nuord,TPCON,DSTPC,TPORD,DSTPO,STORD,DSSTO,MTORD,DSMTO,CDPRI,DSPRI,ESORD," +
        "DSESO,TPRCR,USRCR,DSUSC,FECRE,HRCRE,TPREA,USREA,DSUSA,TPREU,USREU, " +
        "DSUSU,FEMOD,HRMOD,CDCLI,CDSTA,DSOBS " +
		"FROM OM_ORDENTRABAJO WHERE ordentrabajoid = ?";
		
		String[] parametros = new String[] { String.valueOf(ordenTrabajoId)};
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		OrdenTrabajoTO ordenTrabajoTO=null;
		
		
		if(cursor.moveToNext()){
		
			ordenTrabajoTO = new OrdenTrabajoTO();
			ordenTrabajoTO.setNuOrd(cursor.getLong(cursor.getColumnIndex("nuord")));
			ordenTrabajoTO.setTpCon(cursor.getString(cursor.getColumnIndex("tpcon")));
			ordenTrabajoTO.setDsTpC(cursor.getString(cursor.getColumnIndex("dstpc")));
			ordenTrabajoTO.setTpOrd(cursor.getString(cursor.getColumnIndex("tpord")));
			ordenTrabajoTO.setDsTpO(cursor.getString(cursor.getColumnIndex("dstpo")));
			ordenTrabajoTO.setStOrd(cursor.getString(cursor.getColumnIndex("stord")));
			ordenTrabajoTO.setDsStO(cursor.getString(cursor.getColumnIndex("dssto")));
			ordenTrabajoTO.setMtOrd(cursor.getString(cursor.getColumnIndex("mtord")));
			ordenTrabajoTO.setDsMtO(cursor.getString(cursor.getColumnIndex("dsmto")));
			ordenTrabajoTO.setCdPri(cursor.getString(cursor.getColumnIndex("cdpri")));
			ordenTrabajoTO.setDsPri(cursor.getString(cursor.getColumnIndex("dspri")));
			ordenTrabajoTO.setEsOrd(cursor.getString(cursor.getColumnIndex("esord")));
			ordenTrabajoTO.setDsEsO(cursor.getString(cursor.getColumnIndex("dseso")));
			ordenTrabajoTO.setTpRCr(cursor.getString(cursor.getColumnIndex("tprcr")));
			ordenTrabajoTO.setUsRCr(cursor.getString(cursor.getColumnIndex("usrcr")));
			ordenTrabajoTO.setDsUsC(cursor.getString(cursor.getColumnIndex("dsusc")));
			ordenTrabajoTO.setFeCre(cursor.getString(cursor.getColumnIndex("fecre")));
			ordenTrabajoTO.setHrCre(cursor.getString(cursor.getColumnIndex("hrcre")));
			ordenTrabajoTO.setTpReA(cursor.getString(cursor.getColumnIndex("tprea")));
			ordenTrabajoTO.setUsReA(cursor.getString(cursor.getColumnIndex("usrea")));
			ordenTrabajoTO.setDsUsA(cursor.getString(cursor.getColumnIndex("dsusa")));
			ordenTrabajoTO.setTpReU(cursor.getString(cursor.getColumnIndex("tpreu")));
			ordenTrabajoTO.setUsReU(cursor.getString(cursor.getColumnIndex("usreu")));
			ordenTrabajoTO.setDsUsU(cursor.getString(cursor.getColumnIndex("dsusu")));
			ordenTrabajoTO.setFeMod(cursor.getString(cursor.getColumnIndex("femod")));
			ordenTrabajoTO.setHrMod(cursor.getString(cursor.getColumnIndex("hrmod"))); 
			ordenTrabajoTO.setCdCli(cursor.getString(cursor.getColumnIndex("cdcli")));
			ordenTrabajoTO.setCdStA(cursor.getInt(cursor.getColumnIndex("cdsta")));
			ordenTrabajoTO.setDsObs(cursor.getString(cursor.getColumnIndex("dsobs")));
			
			ArrayList<EventoTO> eventos = eventoDAO.listWithEventos(ordenTrabajoId, ordenTrabajoTO.getNuOrd());
			ordenTrabajoTO.setEventos(eventos);
		
		}
		cursor.close();
		
		
		return ordenTrabajoTO;
	}
	public OrdenTrabajoTO list(long ordenTrabajoId){
		
		String SQL = "SELECT ordentrabajoid,nuord2,TPCON,DSTPC,TPORD,DSTPO,STORD,DSSTO,MTORD,DSMTO,CDPRI,DSPRI,ESORD," +
			        "DSESO,TPRCR,USRCR,DSUSC,FECRE,HRCRE,TPREA,USREA,DSUSA,TPREU,USREU, " +
			        "DSUSU,FEMOD,HRMOD,CDCLI,CDSTA,DSOBS " +
					"FROM OM_ORDENTRABAJO WHERE ordentrabajoid = ?";
		
		String[] parametros = new String[] { String.valueOf(ordenTrabajoId)};
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		OrdenTrabajoTO ordenTrabajoTO=null;
		
		if(cursor.moveToNext()){
			
			ordenTrabajoTO = new OrdenTrabajoTO();
			ordenTrabajoTO.setNuOrd(cursor.getLong(cursor.getColumnIndex("ordentrabajoid")));
			ordenTrabajoTO.setNuOrd2(cursor.getLong(cursor.getColumnIndex("nuord2")));
			ordenTrabajoTO.setTpCon(cursor.getString(cursor.getColumnIndex("tpcon")));
	        ordenTrabajoTO.setDsTpC(cursor.getString(cursor.getColumnIndex("dstpc")));
	        ordenTrabajoTO.setTpOrd(cursor.getString(cursor.getColumnIndex("tpord")));
	        ordenTrabajoTO.setDsTpO(cursor.getString(cursor.getColumnIndex("dstpo")));
	        ordenTrabajoTO.setStOrd(cursor.getString(cursor.getColumnIndex("stord")));
	        ordenTrabajoTO.setDsStO(cursor.getString(cursor.getColumnIndex("dssto")));
	        ordenTrabajoTO.setMtOrd(cursor.getString(cursor.getColumnIndex("mtord")));
	        ordenTrabajoTO.setDsMtO(cursor.getString(cursor.getColumnIndex("dsmto")));
	        ordenTrabajoTO.setCdPri(cursor.getString(cursor.getColumnIndex("cdpri")));
	        ordenTrabajoTO.setDsPri(cursor.getString(cursor.getColumnIndex("dspri")));
	        ordenTrabajoTO.setEsOrd(cursor.getString(cursor.getColumnIndex("esord")));
	        ordenTrabajoTO.setDsEsO(cursor.getString(cursor.getColumnIndex("dseso")));
	        ordenTrabajoTO.setTpRCr(cursor.getString(cursor.getColumnIndex("tprcr")));
	        ordenTrabajoTO.setUsRCr(cursor.getString(cursor.getColumnIndex("usrcr")));
	        ordenTrabajoTO.setDsUsC(cursor.getString(cursor.getColumnIndex("dsusc")));
	        ordenTrabajoTO.setFeCre(cursor.getString(cursor.getColumnIndex("fecre")));
	        ordenTrabajoTO.setHrCre(cursor.getString(cursor.getColumnIndex("hrcre")));
	        ordenTrabajoTO.setTpReA(cursor.getString(cursor.getColumnIndex("tprea")));
	        ordenTrabajoTO.setUsReA(cursor.getString(cursor.getColumnIndex("usrea")));
	        ordenTrabajoTO.setDsUsA(cursor.getString(cursor.getColumnIndex("dsusa")));
	        ordenTrabajoTO.setTpReU(cursor.getString(cursor.getColumnIndex("tpreu")));
	        ordenTrabajoTO.setUsReU(cursor.getString(cursor.getColumnIndex("usreu")));
	        ordenTrabajoTO.setDsUsU(cursor.getString(cursor.getColumnIndex("dsusu")));
	        ordenTrabajoTO.setFeMod(cursor.getString(cursor.getColumnIndex("femod")));
	        ordenTrabajoTO.setHrMod(cursor.getString(cursor.getColumnIndex("hrmod"))); 
	        ordenTrabajoTO.setCdCli(cursor.getString(cursor.getColumnIndex("cdcli")));
	        ordenTrabajoTO.setCdStA(cursor.getInt(cursor.getColumnIndex("cdsta")));
	        ordenTrabajoTO.setDsObs(cursor.getString(cursor.getColumnIndex("dsobs")));
	        
		}
		cursor.close();
		
		return ordenTrabajoTO;

	}
	
	public ArrayList<OrdenTrabajoTO> listPendientes(){
		ArrayList<OrdenTrabajoTO> ordenes = new ArrayList<OrdenTrabajoTO>();
		
		final String SQL = "SELECT ordentrabajoid,ifnull(nuord2,0) as nuord2," +
						   "dstpc,dstpo, dssto, dsmto,dspri,dseso, dsusa, fecre,cdsta, cdcli,esord" +
						   " FROM om_ordentrabajo WHERE (cdsta IN ('0','1') and flagmod=1) or (cdsta=0 and flagmod=0)";
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL,null);
		
		OrdenTrabajoTO ordenTrabajoTO;
		
		while (cursor.moveToNext()){
			ordenTrabajoTO = new OrdenTrabajoTO();
			
			ordenTrabajoTO.setNuOrd(cursor.getLong(cursor.getColumnIndex("ordentrabajoid")));
			ordenTrabajoTO.setNuOrd2(cursor.getLong(cursor.getColumnIndex("nuord2")));
			ordenTrabajoTO.setDsTpC(cursor.getString(cursor.getColumnIndex("dstpc")));
			ordenTrabajoTO.setDsTpO(cursor.getString(cursor.getColumnIndex("dstpo")));
			ordenTrabajoTO.setDsStO(cursor.getString(cursor.getColumnIndex("dssto")));
			ordenTrabajoTO.setDsMtO(cursor.getString(cursor.getColumnIndex("dsmto")));
			
			ordenTrabajoTO.setDsPri(cursor.getString(cursor.getColumnIndex("dspri")));
			ordenTrabajoTO.setDsEsO(cursor.getString(cursor.getColumnIndex("dseso")));
			ordenTrabajoTO.setDsUsA(cursor.getString(cursor.getColumnIndex("dsusa")));
			ordenTrabajoTO.setFeCre(cursor.getString(cursor.getColumnIndex("fecre")));
			ordenTrabajoTO.setCdStA(cursor.getInt(cursor.getColumnIndex("cdsta")));
			ordenTrabajoTO.setEsOrd(cursor.getString(cursor.getColumnIndex("esord")));
			
			ordenes.add(ordenTrabajoTO);
		}
		
		cursor.close();
		return ordenes;
		
	}
	public ArrayList<OrdenTrabajoTO> list(String codigoCliente,String estado,String fecha){
		ArrayList<OrdenTrabajoTO> ordenes = new ArrayList<OrdenTrabajoTO>();
		
		String SQL = "SELECT  ordentrabajoid,nuord2,DSTPC,DSTPO, DSSTO, DSMTO,DSPRI,DSESO, DSUSA, FECRE,CDSTA,esord " +
					 " FROM OM_ORDENTRABAJO WHERE CDCLI= ? AND ESORD=? AND FECRE>=?";
							
		String[] parametros = new String[] { codigoCliente,estado,fecha };
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		OrdenTrabajoTO ordenTrabajoTO;
		
		while (cursor.moveToNext()){
			ordenTrabajoTO = new OrdenTrabajoTO();
			
			ordenTrabajoTO.setNuOrd(cursor.getLong(cursor.getColumnIndex("ordentrabajoid")));
			ordenTrabajoTO.setNuOrd2(cursor.getLong(cursor.getColumnIndex("nuord2")));
			ordenTrabajoTO.setDsTpC(cursor.getString(cursor.getColumnIndex("dstpc")));
			ordenTrabajoTO.setDsTpO(cursor.getString(cursor.getColumnIndex("dstpo")));
			ordenTrabajoTO.setDsStO(cursor.getString(cursor.getColumnIndex("dssto")));
			ordenTrabajoTO.setDsMtO(cursor.getString(cursor.getColumnIndex("dsmto")));
			
			ordenTrabajoTO.setDsPri(cursor.getString(cursor.getColumnIndex("dspri")));
			ordenTrabajoTO.setDsEsO(cursor.getString(cursor.getColumnIndex("dseso")));
			ordenTrabajoTO.setDsUsA(cursor.getString(cursor.getColumnIndex("dsusa")));
			ordenTrabajoTO.setFeCre(cursor.getString(cursor.getColumnIndex("fecre")));
			ordenTrabajoTO.setCdStA(cursor.getInt(cursor.getColumnIndex("cdsta")));
			ordenTrabajoTO.setEsOrd(cursor.getString(cursor.getColumnIndex("esord")));
			
			ordenes.add(ordenTrabajoTO);
			
			
		}
		
		cursor.close();
		
		return ordenes;
	}
	
	
	public void update(final OrdenTrabajoTO ordenTrabajoTO){
		
		ContentValues parametros = new ContentValues();
		
		parametros.put("FEMOD", ordenTrabajoTO.getFeMod());
        parametros.put("HRMOD", ordenTrabajoTO.getHrMod());
        parametros.put("ESORD", ordenTrabajoTO.getEsOrd());
        parametros.put("DSESO", ordenTrabajoTO.getDsEsO());
        parametros.put("TPREU", ordenTrabajoTO.getTpReU());
        parametros.put("USREU", String.format("%10s", ordenTrabajoTO.getUsReU()).replace(' ', '0'));
        parametros.put("DSUSU", ordenTrabajoTO.getDsUsU());
        parametros.put("TPREA", ordenTrabajoTO.getTpReA());
        parametros.put("USREA", String.format("%10s", ordenTrabajoTO.getUsReA()).replace(' ', '0'));
        parametros.put("DSUSA", ordenTrabajoTO.getDsUsA());
        parametros.put("FLAGMOD",1);
        
		String[] valores = new String[] { String.valueOf(ordenTrabajoTO.getNuOrd())};

		dbHelper.getDataBase().update("om_ordentrabajo", parametros, "OrdenTrabajoId = ?",valores);
	}
	
	
	public long getOrdenTrabajoId(long nuOrd2){
		String SQL = "select ordentrabajoid from om_ordentrabajo where nuord2 = ?";
		
		String[] parametros = new String[] {String.valueOf(nuOrd2)};
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		long ordenTrabajoId=0;
		
		if(cursor.moveToNext()){
			ordenTrabajoId = cursor.getLong(cursor.getColumnIndex("ordentrabajoid"));
		}
		
		cursor.close();
		
		return ordenTrabajoId;
		
	}
	public void save(final OrdenTrabajoTO ordenTrabajoTO){
		ContentValues parametros = new ContentValues();

	 	parametros.put("TPCON", ordenTrabajoTO.getTpCon());
        parametros.put("DSTPC", ordenTrabajoTO.getDsTpC());
        parametros.put("TPORD", ordenTrabajoTO.getTpOrd());
        parametros.put("DSTPO", ordenTrabajoTO.getDsTpO());
        parametros.put("STORD", ordenTrabajoTO.getStOrd());
        parametros.put("DSSTO", ordenTrabajoTO.getDsStO());
        parametros.put("MTORD", ordenTrabajoTO.getMtOrd());
        parametros.put("DSMTO", ordenTrabajoTO.getDsMtO());
        parametros.put("CDPRI", ordenTrabajoTO.getCdPri());
        parametros.put("DSPRI", ordenTrabajoTO.getDsPri());
        parametros.put("ESORD", ordenTrabajoTO.getEsOrd());
        parametros.put("DSESO", ordenTrabajoTO.getDsEsO());
        parametros.put("TPRCR", ordenTrabajoTO.getTpRCr());
        parametros.put("USRCR", String.format("%10s", ordenTrabajoTO.getUsRCr()).replace(' ', '0'));
        parametros.put("DSUSC", ordenTrabajoTO.getDsUsC());
        parametros.put("FECRE", ordenTrabajoTO.getFeCre());
        parametros.put("HRCRE", ordenTrabajoTO.getHrCre());
        parametros.put("TPREA", ordenTrabajoTO.getTpReA());
        parametros.put("USREA", String.format("%10s", ordenTrabajoTO.getUsReA()).replace(' ', '0'));
        parametros.put("DSUSA", ordenTrabajoTO.getDsUsA());
        
        if(ordenTrabajoTO.getTpReU()!=null){
	        parametros.put("TPREU", ordenTrabajoTO.getTpReU());
	        parametros.put("USREU", String.format("%10s", ordenTrabajoTO.getUsReU()).replace(' ', '0'));
	        parametros.put("DSUSU", ordenTrabajoTO.getDsUsU());
        }else{
        	parametros.put("TPREU", "");
	        parametros.put("USREU", "");
	        parametros.put("DSUSU", "");
        }    
        parametros.put("FEMOD", ordenTrabajoTO.getFeMod());
        parametros.put("HRMOD", ordenTrabajoTO.getHrMod());
        
        parametros.put("CDCLI", String.format("%8s", ordenTrabajoTO.getCdCli()).replace(' ', '0'));
        parametros.put("CDSTA", ordenTrabajoTO.getCdStA());
        
        if(ordenTrabajoTO.getDsObs()!=null){
        	parametros.put("DSOBS", ordenTrabajoTO.getDsObs());
        }else{
        	parametros.put("DSOBS", "");
        }
        
        if(ordenTrabajoTO.getNuOrd2()!=0){
        	parametros.put("NUORD2", ordenTrabajoTO.getNuOrd2());
        }
        parametros.put("FLAGMOD", 0);
            
            
        long id = dbHelper.getDataBase().insertOrThrow("om_ordentrabajo", null, parametros);
        ordenTrabajoTO.setNuOrd(id);
	}
	
	public void delete(long ordenTrabajoId){
		System.out.println("registros a borrar: "+ordenTrabajoId);
		String[] args = new String[] { String.valueOf(ordenTrabajoId)};
		dbHelper.getDataBase().execSQL("delete from om_evento where nuord in (?)",args);
		dbHelper.getDataBase().execSQL("delete FROM om_ordentrabajo where ordentrabajoid = ?", args);
	}
	
	public void delete(){
		
		dbHelper.getDataBase().execSQL("delete FROM om_ordentrabajo");
	}
	
}
