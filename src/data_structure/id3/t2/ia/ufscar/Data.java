package data_structure.id3.t2.ia.ufscar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {
	private HashMap<String, Attribute> attributes;
	
	public Data(){
		attributes = new HashMap<String, Attribute>();
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
		String r = "[";
		for(Attribute a : attributes.values()){
			r += a.toString() + ", ";
		}
		
		r += "]";
		
		return r;
	}
	
}
