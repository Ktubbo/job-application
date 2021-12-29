package com.jobapplication.treesoopmodel;

import java.util.HashSet;
import java.util.Set;

public class Branch {

    private Set<BranchItem> branchItemSet = new HashSet<>();

    public void growNewBranchItem(BranchItem branchItem) {
        branchItemSet.add(branchItem);
    }

    public Set<BranchItem> getBranchItemSet() {
        return branchItemSet;
    }
}
