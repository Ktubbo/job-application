package com.jobapplication.treesoopmodel;

import java.util.HashSet;
import java.util.Set;

public class ConiferTree implements Tree {

    protected Set<Stem> stems = new HashSet<>();
    protected Set<Root> roots = new HashSet<>();

    @Override
    public void growNewStem() {
        stems.add(new Stem());
    }

    @Override
    public void growNewRoot() {
        roots.add(new Root());
    }

    @Override
    public void disperseSeeds() {
        stems.stream()
                .flatMap(stem -> stem.getBranches().stream())
                .flatMap(branch -> branch.getBranchItemSet().stream())
                .filter(branchItem -> branchItem instanceof Seed)
                .map(branchItem -> (Seed) branchItem)
                .forEach(Seed::disperse);
    }

    @Override
    public void processPhotosynthesis() {
        stems.stream()
                .flatMap(stem -> stem.getBranches().stream())
                .flatMap(branch -> branch.getBranchItemSet().stream())
                .filter(branchItem -> branchItem instanceof Leaf)
                .map(branchItem -> (Leaf) branchItem)
                .forEach(Leaf::photosynthesize);
    }

    @Override
    public void gatherNutrients() {
        roots.forEach(Root::gatherNutrients);
    }

    @Override
    public void gatherWater() {
        roots.forEach(Root::gatherWater);
    }
}
