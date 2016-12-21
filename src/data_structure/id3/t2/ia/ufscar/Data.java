package data_structure.id3.t2.ia.ufscar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Data {
	
	private LinkedHashMap<String, Attribute> attributes;
	private String label;
	
	public Data(String label){
		attributes = new LinkedHashMap<String, Attribute>();
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public void addAttribute(Attribute attribute){
		attributes.put(attribute.getLabel(), attribute);
	}
	
	
	public Attribute getAttribute(String key){
		return attributes.get(key);
	}
	
	public Attribute getAttribute(int pos){
		return new ArrayList<Attribute>(attributes.values()).get(pos);
	}
	
	public List<Attribute> getAttributes(){
		return new ArrayList<Attribute>(attributes.values());
	}
	
	@Override
	public String toString(){
		String r = label + ": ";
		int i = 0;
		for(Attribute a : attributes.values()){			
			r += a.getValue();
			if(i < attributes.size() - 1)
				r +=  ", ";
			
			i++;
		}
		
		return r;
	}
	
}
