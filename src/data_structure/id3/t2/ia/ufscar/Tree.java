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
	
	public void showTree(){
		showTreeAux(root, 0);
	}
	
	private void showTreeAux(TreeNode node, int level){

		for(int i = 0; i < level; i++){
			System.out.print("\t");
		}

		System.out.println(node.getLabel());
		for(TreeNode n : node.getChildren()){
			showTreeAux(n, level + 1);
		}
	}
	
	public String classifyData(Data data){
		return classifyDataAux(data, root);
	}
	
	private String classifyDataAux(Data data, TreeNode node){

		if(node.getChildren().size() == 0)
			return node.getLabel();

		Attribute attr = data.getAttribute(node.getLabel());

		TreeNode n = node.getChild(attr);

		return classifyDataAux(data, n);
	}
}
