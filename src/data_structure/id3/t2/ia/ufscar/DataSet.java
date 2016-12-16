package data_structure.id3.t2.ia.ufscar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DataSet extends HashSet<Data>{

	private AttributeDescriptor attributeDescriptor;
	private static final long serialVersionUID = 1L;

	private HashMap<String, Integer> numberOfClass;

	public DataSet(){
		attributeDescriptor = new AttributeDescriptor(this);
		calculateNumberOfDataForEachClass();
	}

	private void calculateNumberOfDataForEachClass(){

		for(String c : attributeDescriptor.getAttributeDescription("Class").getPossibleValues()){
			int cont = 0;
			for(Data d : this){
				String attributeClass = d.getAttribute("Class").getValue();
				if(attributeClass.equals(c))
					cont++;
			}
			
			numberOfClass.put(c, cont);
		}
	}

	public List<String> getAllClasses(){
		return attributeDescriptor.getAttributeDescription("Class").getPossibleValues();
	}
	
	public int getNumberOfDataOfClass(String c) {
		return numberOfClass.get(c);
	}
}
