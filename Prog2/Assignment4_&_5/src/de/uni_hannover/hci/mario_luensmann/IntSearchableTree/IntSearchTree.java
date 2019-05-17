package de.uni_hannover.hci.mario_luensmann.IntSearchableTree;

import de.uni_hannover.hci.mario_luensmann.IntBinaryTree.IntBinTree;

/**
 * This class represents a binary tree of integers that randomly inserts.
 */
public class IntSearchTree extends IntBinTree
{
    /**
     * Constructor for IntSearchTree
     * 
     * @param content Content of this node
     * @param left    Left child, null if there is none.
     * @param right   Right child, null if there is none.
     */
    public IntSearchTree(int content, IntBinTree left, IntBinTree right)
    {
        super(content, left, right);
    }

    /**
     * Inserts a value into tree. Point to insert is chosen randomly.
     * 
     * @param i Value to insert.
     */
    public void insert(int i)
    {
        if (this.content_ == i)
        {
            return;
        }
        else if (i < this.content_)
        {
            if (this.getLeft_() == null)
            {
                this.setLeft_(new IntSearchTree(i, null, null));
            }
            else
            {
                this.getLeft_().insert(i);
            }
        }
        else
        {
            if (this.getRight_() == null)
            {
                this.setRight_(new IntSearchTree(i, null, null));
            }
            else
            {
                this.getRight_().insert(i);
            }
        }
    }

    /**
     * Searches for a node containing a value.
     * 
     * @param i Value to search for.
     * @return true if i is in tree, false if it isn't.
     */
    public boolean search(int i)
    {
        if (this.content_ == i)
        {
            return true;
        }
        else if (i < this.content_)
        {
            if (this.getLeft_() != null)
            {
                return this.getLeft_().search(i);
            }
            else
            {
                return false;
            }
        }
        else
        {
            if (this.getRight_() != null)
            {
                return this.getRight_().search(i);
            }
            else
            {
                return false;
            }
        }
    }
}