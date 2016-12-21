package data_structure.id3.t2.ia.ufscar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TreeNode {

	private LinkedHashMap<String, TreeNode> children;
	private String label;
	
	public TreeNode(String label){
		children = new LinkedHashMap<String, TreeNode>();
		this.label = label;
	}

	public String getLabel(){
		return label;
	}
	
	public TreeNode getChild(Attribute attr){
		return children.get(attr.getValue());
	}

	public void addChild(String key, TreeNode child){
		children.put(key, child);
	}

	public List<TreeNode> getChildren() {
		return new ArrayList<TreeNode>(children.values());
	}

}
