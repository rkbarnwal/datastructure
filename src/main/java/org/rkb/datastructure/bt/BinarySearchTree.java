package org.rkb.datastructure.bt;

import java.util.Comparator;

public class BinarySearchTree<T extends Comparable<T>> {

	public static void main(String[] args) {
		Integer[] a = { 9, 5, 2, 7, 4 };
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (Integer data : a) {
			bst.insert(data);
		}
		bst.preOrderTraversal();
		System.out.println();
		bst.inOrderTraversal();

		Comparator<Integer> comp = new MyComparator();

		// testing comparator
		// build a mirror BST with a rule: Left > Parent > Right
		// code for the comparator at the bottom of the file
		bst = new BinarySearchTree<Integer>(comp);
		for (Integer n : a)
			bst.insert(n);
		System.out.println();
		bst.preOrderTraversal();
		System.out.println();
		bst.inOrderTraversal();
		System.out.println();
		System.out.println(bst.search(5));
		System.out.println(bst.search(90));
	}

	private Node<T> root;
	private Comparator<T> comparator;

	public BinarySearchTree() {
		root = null;
		comparator = null;
	}

	public BinarySearchTree(Comparator<T> comp) {
		root = null;
		comparator = comp;
	}

	public void insert(T data) {
		root = insert(root, data);
	}

	private Node<T> insert(Node<T> node, T dataToBeInserted) {
		if (node == null)
			return new Node<T>(dataToBeInserted);

		if (compare(dataToBeInserted, node.data) == 0)
			return node;

		if (compare(dataToBeInserted, node.data) < 0)
			node.left = insert(node.left, dataToBeInserted);
		else
			node.right = insert(node.right, dataToBeInserted);

		return node;

	}
	
	 public void delete(T toDelete) {
		 delete(root, toDelete);
	 }
	
	private Node<T> delete(Node<T> node, T toDelete) {
		if(node == null) {
			throw new RuntimeException("cannot delete.");
		}
		else if(compare(node.data,toDelete)<0) {
			node.left = delete(node.left,toDelete);
		} else if(compare(node.data,toDelete)>0) {
			node.left = delete(node.right,toDelete);
		} else {
			 if (node.left == null) return node.right;
	         else
	         if (node.right == null) return node.left;
	         else
	         {
	         // get data from the rightmost node in the left subtree
	            node.data = retrieveData(node.left);
	         // delete the rightmost node in the left subtree
	            node.left =  delete(node.left, node.data) ;
	         }
		}
		return node;
	}

	private T retrieveData(Node<T> p) {
		while (p.right != null) p = p.right;
			return p.data;
	}

	public boolean search(T dataToSearch) {
		return search(root,dataToSearch);
	}

	private boolean search(Node<T> root, T dataToSearch) {
		if(root == null) {
			return false;
		}
		if(compare(root.data,dataToSearch )==0) {
			return true;
		}
		else if(compare(dataToSearch,root.data)<0) {
			return search(root.left,dataToSearch);
		}
		else {
			return search(root.right, dataToSearch);
		}
	}

	private int compare(T nodeValue, T newValue) {
		return nodeValue.compareTo(newValue);
	}

	private void preOrderTraversal() {
		preOrderHelper(root);
	}

	private void inOrderTraversal() {
		inOrderHelper(root);
	}

	private void preOrderHelper(Node node) {
		if (node != null) {
			System.out.print(node.data + " ");
			preOrderHelper(node.left);
			preOrderHelper(node.right);
		}
	}

	private void inOrderHelper(Node node) {
		if (node != null) {
			inOrderHelper(node.left);
			System.out.print(node.data + " ");
			inOrderHelper(node.right);
		}
	}

	private class Node<T> {
		private T data;
		private Node<T> left, right;

		private Node(T data, Node<T> l, Node<T> r) {
			left = l;
			right = r;
			this.data = data;
		}

		private Node(T data) {
			this(data, null, null);
		}
	}

}

class MyComparator implements Comparator<Integer> {
	public int compare(Integer x, Integer y) {
		return y - x;
	}
}
