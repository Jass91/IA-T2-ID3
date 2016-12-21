package data_structure.id3.t2.ia.ufscar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AttributeDescriptor {
	
	private LinkedHashMap<String, AttributeDescription> attributesDescription;
	
	public AttributeDescriptor(){
		attributesDescription = new LinkedHashMap<String, AttributeDescription>();
	}
	
	public AttributeDescription getAttributeDescription(String attribute){
		return attributesDescription.get(attribute);
	}
	
	public void addAttributeDescription(AttributeDescription ad){
		attributesDescription.put(ad.getLabel(), ad);
	}
	
	public List<AttributeDescription> getAttributeDescriptionList(){
		return new ArrayList<AttributeDescription>(attributesDescription.values());
	}
	
	public List<String> getAttributeList(){
		return new ArrayList<String>(attributesDescription.keySet());
	}
	
	public List<String> getAttributeLabelList(){
		return new ArrayList<String>(attributesDescription.keySet());
	}
	
	public List<String> getAttributeValues(String attribute){
		return attributesDescription.get(attribute).getPossibleValues();
	}
	
	public int getNumberOfClass(){
		return attributesDescription.get("Classe").getCardinality();
	}
}
