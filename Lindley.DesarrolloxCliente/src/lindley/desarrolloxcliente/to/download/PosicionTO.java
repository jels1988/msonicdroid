package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.SerializedName;

public class PosicionTO {

	public long id;
	
	@SerializedName("CLI")
	public String codigoCliente;
	

	@SerializedName("CON")
	public String confirmacion;
	
	@SerializedName("TAG")
	public String tipoAgrupacion;
	
	
	@SerializedName("VRD")
	public String variableRed;
	
	
	@SerializedName("FRD")
	public int fechaRed;
	
	
	@SerializedName("SRD")
	public double soviRed;

	
	@SerializedName("SMX")
	public double soviMaximo;
	
	@SerializedName("SDF")
	public double soviDiferencia;
	
	
	@SerializedName("PSG")
	public int puntosSugeridos;
	
	@SerializedName("PBN")
	public int puntosBonus;
	
	
	
	
	/*
	 * 
	 CREATE TABLE "posicion_cliente" (
"clienteposicionId" INTEGER PRIMARY KEY  NOT NULL ,
"anio" INTEGER,
"mes" INTEGER,
"codigoCliente" VARCHAR(10),
"confirmacion" VARCHAR(1),
"tipoAgrupacion" VARCHAR(1),
"variableRed" VARCHAR(2),
"fechaRed" INTEGER,
"soviRed" numeric(8,2),
"soviMaximo" numeric(8,2),
"soviDiferencia" numeric(8,2),
"puntosSugeridos" INTEGER,
"puntosBonus" INTEGER,
"puntosGanados" INTEGER,
"fechaProceso"	INTEGER
)
	 * */
}
