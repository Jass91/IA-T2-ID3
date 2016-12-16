package data_structure.id3.t2.ia.ufscar;

import java.util.ArrayList;
import java.util.List;

public class AttributeDescription {

	private String label;
	private List<String> possibleValues;
	
	public AttributeDescription(){
		possibleValues = new ArrayList<String>();
	}
	
	public void addValue(String value){
		
		if(!possibleValues.contains(value)){
			possibleValues.add(value);
		}
	}

	public void addLabel(String label) {
		this.label = label;
	}

	public List<String> getPossibleValues(){
		return possibleValues;
	}
	
	public String getLabel(){
		return label;
	}
	
	public int getCardinality(){
		return possibleValues.size();
	}
	
	@Override
	public String toString(){
		String r = "label: " + label + " values: [";
		for(Object obj : possibleValues){
			r += obj.toString() + ", ";
		}
		
		r += "]";
		
		return r;
	}
}
