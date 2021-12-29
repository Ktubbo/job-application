package com.jobapplication.treesoopmodel;

import java.util.HashSet;
import java.util.Set;

public class Stem {

    private Set<Branch> branches = new HashSet<>();

    public void growNewBranch() {
        branches.add(new Branch());
    }

    public Set<Branch> getBranches() {
        return branches;
    }
}
