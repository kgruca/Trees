
public class FHtreeNode<E> {

	protected FHtreeNode<E> firstChild, sib, prev;
	protected E data;
	protected FHtreeNode<E> myRoot;
	
	public FHtreeNode(E d, FHtreeNode<E> sb, FHtreeNode<E> chld, FHtreeNode<E> prv) {
		firstChild = chld;
		sib = sb;
		prev = prv;
		data = d;
		myRoot = null;
	}
	
	public FHtreeNode() {
		this(null, null, null, null);
	}
	
	public E getData() {
		return data;
	}
	
	protected FHtreeNode(E d, FHtreeNode<E> sb, FHtreeNode<E> chld, FHtreeNode<E> prv, FHtreeNode<E> root) {
		this(d, sb, chld, prv);
		myRoot = root;
	}
}



