package data_structure.id3.t2.ia.ufscar;

public class Tree {
	private TreeNode root;
	private int numberOfNodes;
	
	public Tree(TreeNode root){
		this.root = root;
		numberOfNodes = 0;
	}
	
	public int getNumberOfNodes(){
		return numberOfNodes;
	}
	
	public TreeNode getRoot(){
		return root;
	}
}
