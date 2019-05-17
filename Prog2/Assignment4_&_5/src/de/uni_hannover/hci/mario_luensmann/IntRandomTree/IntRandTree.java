package de.uni_hannover.hci.mario_luensmann.IntRandomTree;

import java.util.Random;

import de.uni_hannover.hci.mario_luensmann.IntBinaryTree.IntBinTree;

public class IntRandTree extends IntBinTree {

	public IntRandTree(int content, IntRandTree left, IntRandTree right) {
		super(content, left, right);
	}

	@Override
	public void insert(int i) {
		if (new Random().nextBoolean())
        {
            if (this.getLeft_() == null)
            {
                this.setLeft_(new IntRandTree(i, null, null));
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
                this.setRight_(new IntRandTree(i, null, null));
            }
            else
            {
                this.getRight_().insert(i);
            }
        }
	}

	@Override
	public boolean search(int i) {
		{
	        return (this.content_ == i) ||
	               ((this.getLeft_() != null) ? (this.getLeft_().search(i)) : false) ||
	               ((this.getRight_() != null) ? (this.getRight_().search(i)) : false);
	    }
	}
	
	

}
