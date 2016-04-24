package com.venkat.isbst;

import java.util.Arrays;



/*
 * is the given tree a BST - binary search tree
 * where the left side of node is strictly lesser or equal to node
 * and the node itself is less than or equal to the right
 * meaning the value in the nodes. the compareTo method takes care of that.
 */

public class IsBST {

	private class TreeNode implements Comparable<TreeNode>{
		Long value;
		TreeNode left;
		TreeNode right;
		
		TreeNode(Long value, TreeNode left, TreeNode right){
			this.value = value;

			this.left = left;
			this.right = right;
		}	
		public int compareTo(TreeNode other){
			return ( (this.value).compareTo(other.value) );
		}

	}	
	// this is the main data backing store
	private TreeNode rootNode ;
	
	/*
	 * this one takes a sorted array and creates a 
	 * balanced BST
	 */
	private  TreeNode createBalancedBST(long tnarray[]) {
		return addToTree(tnarray, 0, tnarray.length - 1);
	}
	/*
	 * add to Tree -- making sure it satisifies BST condition
	 */
	private  TreeNode addToTree(long arr[], int start, int end){
		if (end < start) {
			return null;
		}
		int mid = start + (end - start)/ 2;
		TreeNode n = new TreeNode(arr[mid],  null, null);
		n.left = addToTree(arr, start, mid - 1);
		n.right = addToTree(arr, mid + 1, end);
		return n;
	}
	public  static boolean isBST(TreeNode root){
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	/*
	 * this is the method that uses recursion to
	 * actually check the BST property of the tree passed to it
	 */
	private static boolean isBST(TreeNode node, long min, long max){
		if (node == null)
			return true ;
		
		if (node.value < min || node.value > max){
			return false;
		}	
		
		if (!isBST(node.left, min, (long) node.value) || !isBST(node.right, (long) node.value, max))
			return false ;
		
		return true ;
	}
	private static String print(TreeNode tn, int level){
		StringBuilder sbud = new StringBuilder();
		StringBuilder lvl_pstring = new StringBuilder();
		
		if (tn == null)
			return "" ;
		
		for(int i =0;i < level ; i++)
			lvl_pstring.append("   ");
		

		sbud.append ("=> value: ");
		sbud.append(tn.value + ")");
		
		if (tn.left != null){
			sbud.append(System.getProperty("line.separator"));
			sbud.append(lvl_pstring);
			sbud.append(" left " + print(tn.left, level+1) );
		}
		
		if (tn.right != null){
			sbud.append(System.getProperty("line.separator"));
			sbud.append(lvl_pstring);
			sbud.append(" righ " + print(tn.right, level+1) );
		}
		
		return ( sbud.toString() );
	}
	public static String toString(TreeNode rootNode){

		TreeNode curr = rootNode;

		return (print(curr,0)) ;

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long[] ldata = {10L,12L,17L,21L,2L,15L,16L,24L,28L,50L,40L,31L,31L,58L,59L};
		IsBST ibst = new IsBST();
		
		Arrays.sort(ldata);
		
		TreeNode rootNode = ibst.createBalancedBST(ldata);
		
		System.out.println("Tree is ");
		System.out.println(  toString(rootNode) );
		
		boolean is_result = isBST(rootNode);
		System.out.println("BST result is " + is_result);
	}

}
