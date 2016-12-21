package data_structure.id3.t2.ia.ufscar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

public class DataSet extends HashSet<Data>{

	private AttributeDescriptor attributeDescriptor;
	private static final long serialVersionUID = 1L;
	private boolean isInitialized;

	private LinkedHashMap<String, Integer> numberOfClass;

	public DataSet(){
		numberOfClass = new LinkedHashMap<String, Integer>();
	}

	public List<String> getAllClasses(){
		return attributeDescriptor.getAttributeDescription("Classe").getPossibleValues();
	}

	public int getNumberOfDataOfClass(String c) {
		return numberOfClass.get(c);
	}

	public AttributeDescriptor getAttributeDescriptor(){
		return attributeDescriptor;
	}

	public void addAtributeDescriptor(AttributeDescriptor attributeDescriptor) {
		this.attributeDescriptor = attributeDescriptor;

	}

	public void initialize() {
		calculateNumberOfDataForEachClass();
		this.isInitialized = true;
	}

	public boolean isInitialized(){
		return this.isInitialized;
	}

	private void calculateNumberOfDataForEachClass(){

		AttributeDescription classDescription = attributeDescriptor.getAttributeDescription("Classe");
		for(String c : classDescription.getPossibleValues()){
			int cont = 0;
			for(Data d : this){
				String attributeClass = d.getAttribute("Classe").getValue();
				if(attributeClass.equals(c))
					cont++;
			}

			numberOfClass.put(c, cont);
		}
	}

	public List<Data> getDataList() {
		ArrayList<Data> list = new ArrayList<Data>();
		for(Data data : this){
			list.add(data);
		}

		return list;
	}

	@Override
	public String toString(){
		String r = "[\n";
		for(Data d : this){
			r +=  d.toString() + "\n";
		}

		r += "]";

		return r;
	}

	public String findCommomValueTo(AttributeDescription attribute) {
		
		String commonValue = "";
		int lastCount = 0;
		
		for(String v : attribute.getPossibleValues()){
			int count = 0;
			for(Data data : this){
				Attribute attr = data.getAttribute(attribute.getLabel());
				if(attr.getValue().equals(v)){
					count++;
				}
			}

			if(count > lastCount){
				commonValue = v;
			}
		}
		
		return commonValue;
	}
}
