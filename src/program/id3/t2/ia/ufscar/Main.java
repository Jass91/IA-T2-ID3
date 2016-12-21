package program.id3.t2.ia.ufscar;

import core.id3.t2.ia.ufscar.ID3;
import data_structure.id3.t2.ia.ufscar.Attribute;
import data_structure.id3.t2.ia.ufscar.Data;
import data_structure.id3.t2.ia.ufscar.DataSet;
import data_structure.id3.t2.ia.ufscar.Tree;
import data_structure.id3.t2.ia.ufscar.TreeNode;
import util.id3.t2.ia.ufscar.InputReader;

public class Main {

	public static void main(String args[]) throws Exception{
		
		if(args.length < 2 || args.length > 3){
			System.out.println("Usage: ID3 <dataset_treino.txt> <dataset_teste.txt>");
			System.exit(1);
		}
		
		String trainingDataFilePath = args[0];
		String testDataFilePath = args[1];

		DataSet trainingDataSet = InputReader.readTrainingDataAsSet(trainingDataFilePath);
		ID3 id3 = new ID3(trainingDataSet);
		Tree tree = id3.getTree();
		System.out.println("Árvore de decisao:");
		printTree(tree.getRoot(), 0);

		System.out.println();
		System.out.println("Novas classificacoes:");
		DataSet testDataSet = InputReader.readTestDataAsSet(testDataFilePath);
		for(Data data : testDataSet){
			String cls = classifyData(data, tree.getRoot());
			System.out.println(data.getLabel() + " -> " + cls);
		}
	}

	public static String classifyData(Data data, TreeNode node){

		if(node.getChildren().size() == 0)
			return node.getLabel();

		Attribute attr = data.getAttribute(node.getLabel());

		TreeNode n = node.getChild(attr);

		return classifyData(data, n);
	}

	public static void printTree(TreeNode node, int level){

		for(int i = 0; i < level; i++){
			System.out.print("\t");
		}

		System.out.println(node.getLabel());
		for(TreeNode n : node.getChildren()){
			printTree(n, level + 1);
		}
	}
}
