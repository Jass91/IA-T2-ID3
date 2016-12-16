package core.id3.t2.ia.ufscar;

import data_structure.id3.t2.ia.ufscar.DataSet;

public class ID3 {
	
	private DataSet dataSet;
	
	public ID3(DataSet dataSet){
		this.dataSet = dataSet;
		this.dataSet.initialize();
	}
	
	
	public void getTree(){
		calculateEntropy(dataSet);
	}
	
	private double calculateEntropy(DataSet dataSet){
		
		double entropy = 0;
		int dataSetSize = dataSet.size();
		for(String c : dataSet.getAllClasses()){
			int numberOfobjectsOfClassC = dataSet.getNumberOfDataOfClass(c);
			double pi =  numberOfobjectsOfClassC / dataSetSize;
			double log2pi = pi != 0 ? (Math.log10(pi) / Math.log10(2)) : 0;
			entropy += (-pi * log2pi);
		}
		
		return entropy;
	}
}
