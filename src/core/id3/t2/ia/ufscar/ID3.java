package core.id3.t2.ia.ufscar;

import data_structure.id3.t2.ia.ufscar.DataSet;

public class ID3 {
	
	private DataSet dataSet;
	
	public ID3(DataSet dataSet){
		this.dataSet = dataSet;
	}
	
	
	public void getTree(){
		calculateEntropy(dataSet);
	}
	
	private double calculateEntropy(DataSet dataSet){
		
		double entropy = 0;
		for(String c : dataSet.getAllClasses()){
			int pi = dataSet.getNumberOfDataOfClass(c);
			double log2pi = Math.log10(pi) / Math.log10(2);
			entropy += (-pi * log2pi);
		}
		
		return entropy;
	}
}
