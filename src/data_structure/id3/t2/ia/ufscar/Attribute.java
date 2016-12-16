package data_structure.id3.t2.ia.ufscar;

public class Attribute {

	private String label;
	private String value;
	
	public Attribute(String label, String value){
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString(){
		return "{label: " + label + ", value: " + value + "}";
	}
}
