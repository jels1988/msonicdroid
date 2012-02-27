package pe.lindley.mmil.ws.bean;

import com.google.gson.annotations.SerializedName;
import pe.lindley.mmil.to.DashBoardTO;
import pe.lindley.util.ResponseBase;

public class MostrarDashboardResponse extends ResponseBase {

	@SerializedName("DAB")
	private DashBoardTO dashBoard;

	public DashBoardTO getDashBoard() {
		return dashBoard;
	}

	public void setDashBoard(DashBoardTO dashBoard) {
		this.dashBoard = dashBoard;
	}
}
