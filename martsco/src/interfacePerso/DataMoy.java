package interfacePerso;

import java.util.ArrayList;

import function.StaData;
import function.Statistician.MoySta;

public interface DataMoy {
	public void setDataMoy(StaData data);
	public void addClasse(String classe);
	public MoySta getCurrentMoySta();
	public ArrayList<MoySta> getResultMoy();
}
