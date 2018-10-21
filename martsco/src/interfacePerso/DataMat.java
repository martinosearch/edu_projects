package interfacePerso;

import java.util.ArrayList;

import function.StaData;
import function.Statistician.MatSta;

public interface DataMat {
	public void setDataMat(StaData data);
	public void addMatiere(String matiere);
	public MatSta getCurrentMatSta();
	public ArrayList<MatSta> getResultMat();
}
