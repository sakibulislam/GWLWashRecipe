package main;

import oracle.adf.view.rich.component.rich.data.RichTable;

public class Main {
    private RichTable recipeDetailsTable;

    public Main() {
    }

    public void setRecipeDetailsTable(RichTable recipeDetailsTable) {
        this.recipeDetailsTable = recipeDetailsTable;
    }

    public RichTable getRecipeDetailsTable() {
        return recipeDetailsTable;
    }
}
