
public class FHtree<E> implements Cloneable{

	protected int mSize;
	FHtreeNode<E> mRoot;
	
	public FHtree() {
		clear();
	}
	
	public boolean empty() {
		return (mSize == 0);
	}
	
	public int size() {
		return mSize;
	}
	
	public void clear() {
		mSize = 0;
		mRoot = null;
	}
	
	public FHtreeNode<E> find(E x){
		return find(mRoot, x, 0);
	}
	
	public boolean remove(E x) {
		return remove(mRoot, x);
	}
	
	public void display() {
		display(mRoot, 0);
	}
	
	public <F extends Traverser< ? super E>> void traverse(F func) {
		traverse(func, mRoot, 0);
	}
	
	public FHtreeNode<E> addChild(FHtreeNode<E> treeNode, E x){
		if(mSize ==0) {
			if(treeNode != null)
				return null;
			mRoot = new FHtreeNode<E>(x, null, null, null);
			mRoot.myRoot = mRoot;
			mSize = 1;
			return mRoot;
		}
		if(treeNode == null) {
			return null;
		}
		if(treeNode.myRoot != mRoot)
			return null;
		
		FHtreeNode<E> newNode = new FHtreeNode<E>(x, treeNode.firstChild, null, treeNode, mRoot);
		treeNode.firstChild = newNode;
		if(newNode.sib != null)
			newNode.sib.prev = newNode;
		++mSize;
		return newNode;
	}
	
	public FHtreeNode<E> find(FHtreeNode<E> root, E x, int level){
		FHtreeNode<E> retVal;
		
		if(mSize == 0 || root == null)
			return null;
		
		if(root.data.equals(x))
			return root;
		
		if(level > 0 && (retVal = find(root.sib, x, level)) != null)
			return retVal;
		
		return find(root.firstChild, x, ++level);
	}
	
	public boolean remove(FHtreeNode<E> root, E x) {
		FHtreeNode<E> tn = null;
		
		if(mSize == 0 || root == null)
			return false;
		
		if((tn = find(root, x, 0)) != null) {
			removeNode(tn);
			mSize--;
			return true;
		}
		
		return false;
	}
	
	private void removeNode(FHtreeNode<E> nodeToDelete) {
		if(nodeToDelete == null || mRoot == null)
			return;
		if(nodeToDelete.myRoot != mRoot)
			return;
		while(nodeToDelete.firstChild != null)
			removeNode(nodeToDelete.firstChild);
		if(nodeToDelete.prev == null)
			mRoot = null;
		else if(nodeToDelete.prev.sib == nodeToDelete)
			nodeToDelete.prev.sib = nodeToDelete.sib;
		else
			nodeToDelete.prev.firstChild = nodeToDelete.sib;
		
		if(nodeToDelete.sib != null)
			nodeToDelete.sib.prev = nodeToDelete.prev;
		--mSize;
	}
	
	public Object clone() throws CloneNotSupportedException{
		FHtree<E> newObject = (FHtree<E>)super.clone();
		newObject.clear();
		
		newObject.mRoot = cloneSubTree(mRoot);
		newObject.mSize = mSize;
		newObject.setMyRoots(newObject.mRoot);
		
		return newObject;
	}
	
	public FHtreeNode<E> cloneSubTree(FHtreeNode<E> root){
		FHtreeNode<E> newNode;
		if(root == null)
			return null;
		
		newNode = new FHtreeNode<E>(root.data, cloneSubTree(root.sib), 
				cloneSubTree(root.firstChild), null);
		
		if(newNode.sib != null)
			newNode.sib.prev = newNode;
		if(newNode.firstChild != null)
			newNode.firstChild.prev = newNode;
		
		return newNode;
	}
	
	private void setMyRoots(FHtreeNode<E> treeNode) {
		if(treeNode == null)
			return;
		
		treeNode.myRoot = mRoot;
		setMyRoots(treeNode.sib);
		setMyRoots(treeNode.firstChild);
	}
	
	final static String blankString = "                                                    ";
	
	public void display(FHtreeNode<E> treeNode, int level) {
		String indent;
		
		if(level > (int)blankString.length() - 1) {
			System.out.println(blankString + "...");
			return;
		}
			
		if(treeNode == null)
			return;
		
		indent = blankString.substring(0, level);
		
		System.out.println(indent + treeNode.data);
		
		display(treeNode.firstChild, level + 1);
		if(level > 0)
			display(treeNode.sib, level);
	}
	
	public <F extends Traverser<? super E>> void traverse(F func, FHtreeNode<E> treeNode, int level) {
		if(treeNode == null)
			return;
		
		func.visit(treeNode.data);
		
		traverse(func, treeNode.firstChild, level + 1);
		
		if(level > 0)
			traverse(func, treeNode.sib, level);
	}
}

interface Traverser<E>{
	public void visit(E x);
}

class PrintString implements Traverser<String>{
	public void visit(String s) {
		System.out.print(s + " ");
	}
}
