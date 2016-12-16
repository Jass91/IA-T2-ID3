package data_structure.id3.t2.ia.ufscar;

import java.util.HashMap;

public class AttributeDescriptor {
	
	private HashMap<String, AttributeDescription> attributesDescription;
	
	public AttributeDescriptor(DataSet dataSet){
		attributesDescription = new HashMap<String, AttributeDescription>();
		describeAttributes(dataSet);
	}
	
	public void describeAttributes(DataSet dataSet){
		for(Data data : dataSet){
			for(Attribute a : data.getAttributes()){				
				createOrUpdateDescriptionFor(a);	
			}
		}
	}
	
	public AttributeDescription getAttributeDescription(String attribute){
		return attributesDescription.get(attribute);
	}
	

	private void createOrUpdateDescriptionFor(Attribute attribute) {
		
		AttributeDescription ad = attributesDescription.get(attribute.getLabel());
		if(ad == null){
			ad = new AttributeDescription();
			ad.addLabel(attribute.getLabel());
			ad.addValue(attribute.getValue());
			attributesDescription.put(attribute.getLabel(), ad);
		}else{
			ad.addValue(attribute.getValue());
		}		
	}
}
