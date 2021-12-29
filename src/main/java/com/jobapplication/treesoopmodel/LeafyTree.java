package com.jobapplication.treesoopmodel;

public class LeafyTree extends ConiferTree {

    private boolean isDormant=false;

    public void goDormant() {
        isDormant=true;
        System.out.println("I'll go sleep for now.");
    }

    public boolean isDormant() {
        return isDormant;
    }
}
