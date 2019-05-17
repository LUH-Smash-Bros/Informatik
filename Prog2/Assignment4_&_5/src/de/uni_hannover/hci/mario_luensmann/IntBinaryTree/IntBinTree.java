package de.uni_hannover.hci.mario_luensmann.IntBinaryTree;

/**
 * This class represents a binary tree of integers that randomly inserts.
 */
public abstract class IntBinTree
{
	protected int content_;
    private IntBinTree left_;
	private IntBinTree right_;

    /**
     * Constructor for IntBinTree
     * 
     * @param content Content of this node
     * @param left    Left child, null if there is none.
     * @param right   Right child, null if there is none.
     */
    public IntBinTree(int content, IntBinTree left, IntBinTree right)
    {
        this.content_ = content;
        this.setLeft_(left);
        this.setRight_(right);
    }

    /**
     * Getter for Node content.
     * 
     * @return Content of this tree node.
     */
    public int getContent()
    {
        return this.content_;
    }

    /**
     * Getter for left child.
     * 
     * @return Left child of this tree node.
     */
    public IntBinTree getLeft()
    {
        return this.getLeft_();
    }

    /**
     * Getter for right child.
     * 
     * @return Right child of this tree node.
     */
    public IntBinTree getRight()
    {
        return this.getRight_();
    }

    /**
     * Setter for left child.
     * 
     * @param left Left child.
     */
    public void setLeft(IntBinTree left)
    {
        this.setLeft_(left);
    }

    /**
     * Setter for right child.
     * 
     * @param left Right child.
     */
    public void setRight(IntBinTree right)
    {
        this.setRight_(right);
    }

    /**
     * Returns In-Order representation of tree.
     * 
     * @return In-Order String.
     */
    public String inOrder()
    {
        String result = "";
        if (this.getLeft_() != null)
        {
            result += this.getLeft_().inOrder() + " ";
        }
        result += this.content_;
        if (this.getRight_() != null)
        {
            result += " " + this.getRight_().inOrder();
        }
        return result;
    }

    /**
     * Returns String representation of the tree, that preserves the structure.
     * 
     * @return String representation of tree.
     */
    public String toString()
    {
        return "(" + ((this.getLeft_() != null) ? (this.getLeft_().toString() + " ") : ("_ ")) +
                     this.content_ +
                     ((this.getRight_() != null) ? (" " + this.getRight_().toString()) : (" _")) + ")";
    }

    /**
     * Inserts a value into tree. Point to insert is chosen randomly.
     * 
     * @param i Value to insert.
     */
    public abstract void insert(int i);
	/*        {
    if (new java.util.Random().nextBoolean())
        {
            if (this.left_ == null)
            {
                //this.left_ = new IntBinTree(i, null, null);
            }
            else
            {
                this.left_.insert(i);
            }
        }
        else
        {
            if (this.right_ == null)
            {
                //this.right_ = new IntBinTree(i, null, null);
            }
            else
            {
                this.right_.insert(i);
            }
        }
        
    }*/

    /**
     * Searches for a node containing a value.
     * 
     * @param i Value to search for.
     * @return true if i is in tree, false if it isn't.
     */
    public abstract boolean search(int i);
    /*{
        return (this.content_ == i) ||
               ((this.left_ != null) ? (this.left_.search(i)) : false) ||
               ((this.right_ != null) ? (this.right_.search(i)) : false);
    }*/

	/**
	 * @return the left_
	 */
	public IntBinTree getLeft_() {
		return left_;
	}

	/**
	 * @param left_ the left_ to set
	 */
	public void setLeft_(IntBinTree left_) {
		this.left_ = left_;
	}

	/**
	 * @return the right_
	 */
	public IntBinTree getRight_() {
		return right_;
	}

	/**
	 * @param right_ the right_ to set
	 */
	public void setRight_(IntBinTree right_) {
		this.right_ = right_;
	}
}