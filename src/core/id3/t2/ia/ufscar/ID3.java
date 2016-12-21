package core.id3.t2.ia.ufscar;

import java.util.ArrayList;
import java.util.List;
import data_structure.id3.t2.ia.ufscar.Attribute;
import data_structure.id3.t2.ia.ufscar.AttributeDescription;
import data_structure.id3.t2.ia.ufscar.Data;
import data_structure.id3.t2.ia.ufscar.DataSet;
import data_structure.id3.t2.ia.ufscar.Tree;
import data_structure.id3.t2.ia.ufscar.TreeNode;

public class ID3 {

	private DataSet dataSet;

	public ID3(DataSet dataSet){
		if(!dataSet.isInitialized())
			dataSet.initialize();

		this.dataSet = dataSet;
	}
	
	public Tree getTree(){
		TreeNode root = getTreeAux(dataSet, dataSet.getAttributeDescriptor().getAttributeDescriptionList());
		return new Tree(root);
	}
	
	private TreeNode getTreeAux(DataSet dtSet, List<AttributeDescription> attributes){

		if(dtSet.size() == 0)
			return null;
		
		String maxAttribute = "";
		double maxGain = 0;

		// calcular a entropia do conjunto
		double setEntropy = calculateSetEntropy(dtSet);

		// se existe apenas um elemento ou a entropia do conjunto for 0
		// cria um no folha contendo como classe, a classe de algum elemento do conjunto
		if(dtSet.size() == 1 || setEntropy == 0.0){
			Attribute a = dtSet.getDataList().get(0).getAttribute("Classe");
			return new TreeNode(a.getValue());
		}

		
		// para cada attributo Ai, calcula o ganho e anota o atributo de maior ganho
		for(AttributeDescription attributeDescription : attributes){
			
			// ignora o atributo classe
			if(attributeDescription.getLabel().equals("Classe"))
				continue;
			
			//recupera os subconjuntos baseado no atributo e seu valor
			List<DataSet> subSets = new ArrayList<DataSet>();
			for(String v : attributeDescription.getPossibleValues()){
				DataSet subSet = partitionSet(dtSet, new Attribute(attributeDescription.getLabel(), v));
				subSets.add(subSet);
			}
		
			double gain = calculateAttributeGain(dtSet, subSets, attributeDescription, setEntropy);
			if(gain >= maxGain){
				maxAttribute = attributeDescription.getLabel();
				maxGain = gain;
			}
		}

		// recupera o atributo com o maior ganho
		AttributeDescription attributeDescription = dtSet.getAttributeDescriptor().getAttributeDescription(maxAttribute);

		TreeNode node = new TreeNode(attributeDescription.getLabel());
		for(String v : attributeDescription.getPossibleValues()){
			DataSet subSet = partitionSet(dtSet, new Attribute(attributeDescription.getLabel(), v));
					
			// refaz o processo para cada subarvore
			TreeNode n = getTreeAux(subSet, attributes);
			if(n != null)
				node.addChild(v, n);
			
			// se retornou nulo, encontra o valor mais comum para o atributo
			else{
				TreeNode tn = new TreeNode(dtSet.findCommomValueTo(attributeDescription));
				node.addChild(v, tn);
			}
		}
		
		return node;

	}

	private double calculateSetEntropy(DataSet dataSet){

		if(dataSet.size() == 0)
			return 0;
		
		double entropy = 0;
		int dataSetSize = dataSet.size();
		for(String c : dataSet.getAllClasses()){
			int numberOfobjectsOfClassC = dataSet.getNumberOfDataOfClass(c);
			double pi =  new Double(numberOfobjectsOfClassC) / new Double(dataSetSize);
			double log2pi = pi != 0 ? (Math.log10(pi) / Math.log10(2)) : 0;
			entropy += (-pi * log2pi);
		}

		return entropy;
	}

	private double calculateAttributeGain(DataSet dtSet, List<DataSet> sets, AttributeDescription attribute, double setEntropy){

		double sumTerms = 0;
		for(DataSet s : sets){
			double subSetEntropy = calculateSetEntropy(s);
			double factor = new Double(s.size()) / new Double(dtSet.size());
			sumTerms +=   factor * subSetEntropy;
		}

		return setEntropy - sumTerms;
	}

	private DataSet partitionSet(DataSet dtSet, Attribute attribute){
		DataSet subSet = new DataSet();
		String attrLabel = attribute.getLabel();
		String attrValue = attribute.getValue();
		for(Data d : dtSet){
			if(d.getAttribute(attrLabel).getValue().equals(attrValue)){
				subSet.add(d);
			}
		}

		subSet.addAtributeDescriptor(dataSet.getAttributeDescriptor());
		subSet.initialize();
		
		return subSet;
	}

}