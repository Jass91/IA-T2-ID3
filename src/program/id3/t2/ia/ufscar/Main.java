package program.id3.t2.ia.ufscar;

import core.id3.t2.ia.ufscar.ID3;
import data_structure.id3.t2.ia.ufscar.DataSet;
import util.id3.t2.ia.ufscar.InputReader;

public class Main {

	public static void main(String args[]) throws Exception{
		
		String filepath = args[0];
		DataSet dataSet = InputReader.readFileAsSet(filepath);
		
		ID3 id3 = new ID3(dataSet);
		
		id3.getTree();
		
	}
}
