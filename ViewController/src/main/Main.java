package main;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.domain.Number;

public class Main {
    private RichTable recipeDetailsTable;
    private RichInputText avgWeightInputText;
    private RichInputText lotQtyInputText;
    private RichInputText totalWeightInputText;
    private RichInputComboboxListOfValues chemicalAmount;
    private RichInputComboboxListOfValues waterAmount;
    private RichInputText chemRatio;

    public Main() {
    }

    public void setRecipeDetailsTable(RichTable recipeDetailsTable) {
        this.recipeDetailsTable = recipeDetailsTable;
    }

    public RichTable getRecipeDetailsTable() {
        return recipeDetailsTable;
    }

    public void setAvgWeightInputText(RichInputText avgWeightInputText) {
        this.avgWeightInputText = avgWeightInputText;
    }

    public RichInputText getAvgWeightInputText() {
        return avgWeightInputText;
    }

    public void setLotQtyInputText(RichInputText lotQtyInputText) {
        this.lotQtyInputText = lotQtyInputText;
    }

    public RichInputText getLotQtyInputText() {
        return lotQtyInputText;
    }

    public void setTotalWeightInputText(RichInputText totalWeightInputText) {
        this.totalWeightInputText = totalWeightInputText;
    }

    public RichInputText getTotalWeightInputText() {
        return totalWeightInputText;
    }

    public void valueChanListAvgWeight(ValueChangeEvent valueChangeEvent) {
        try {
            System.out.println("Changed Avg Weight: " + valueChangeEvent.getNewValue());
            Double changedAvgWeight = Double.parseDouble(valueChangeEvent.getNewValue().toString());
            callCalculateTotWeightForAvgW(changedAvgWeight);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void valueChanListLotQty(ValueChangeEvent valueChangeEvent) {
        try {
            System.out.println("Changed Lot Qty: " + valueChangeEvent.getNewValue());
            Double changedLotQty = Double.parseDouble(valueChangeEvent.getNewValue().toString());
            callCalculateTotWeightForLotQty(changedLotQty);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void callCalculateTotWeightForAvgW(Double avgWeight){
        try {
            Double calculatedValue = 0.0;
            Double lotQty = 0.0;
            try {
                lotQty = Double.parseDouble(getLotQtyInputText().getValue().toString());
            } catch (Exception e) {
                lotQty = 0.0;
            }
            calculatedValue = lotQty * avgWeight;
            System.out.println("calculatedValue: " + calculatedValue);
            totalWeightInputText.setValue(new Number(calculatedValue));
            AdfFacesContext.getCurrentInstance().addPartialTarget(totalWeightInputText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void callCalculateTotWeightForLotQty(Double lotQty){
        try {
            Double calculatedValue = 0.0;
            Double avgWeight = 0.0;
            try {
                avgWeight = Double.parseDouble(getAvgWeightInputText().getValue().toString());
            } catch (Exception e) {
                avgWeight = 0.0;
            }
            calculatedValue = lotQty * avgWeight;
            System.out.println("calculatedValue: " + calculatedValue);
            totalWeightInputText.setValue(new Number(calculatedValue));
            AdfFacesContext.getCurrentInstance().addPartialTarget(totalWeightInputText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setChemicalAmount(RichInputComboboxListOfValues chemicalAmount) {
        this.chemicalAmount = chemicalAmount;
    }

    public RichInputComboboxListOfValues getChemicalAmount() {
        return chemicalAmount;
    }

    public void setWaterAmount(RichInputComboboxListOfValues waterAmount) {
        this.waterAmount = waterAmount;
    }

    public RichInputComboboxListOfValues getWaterAmount() {
        return waterAmount;
    }

    public void valueChanListWaterAmount(ValueChangeEvent valueChangeEvent) {
        try {
            System.out.println("Changed Water Amount: " + valueChangeEvent.getNewValue());
            Double changedWaterAmount = Double.parseDouble(valueChangeEvent.getNewValue().toString());
            callCalculateChemRatioForWaterAmount(changedWaterAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void valueChanListChemAmount(ValueChangeEvent valueChangeEvent) {
        try {
            System.out.println("Changed Chem Amount: " + valueChangeEvent.getNewValue());
            Double changedChemAmount = Double.parseDouble(valueChangeEvent.getNewValue().toString());
            callCalculateChemRatioForChemAmount(changedChemAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void callCalculateChemRatioForWaterAmount(Double waterAmount){
        try {
            Double calculatedValue = 0.0;
            Double chemAmount = 0.0;
            try {
                chemAmount = Double.parseDouble(getChemicalAmount().getValue().toString());
            } catch (Exception e) {
                chemAmount = 0.0;
            }
            calculatedValue = chemAmount / waterAmount;
            System.out.println("calculatedValue: " + calculatedValue);
            chemRatio.setValue(new Number(calculatedValue));
            AdfFacesContext.getCurrentInstance().addPartialTarget(chemRatio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void callCalculateChemRatioForChemAmount(Double chemAmount){
        try {
            Double calculatedValue = 0.0;
            Double waterAmount = 0.0;
            try {
                waterAmount = Double.parseDouble(getWaterAmount().getValue().toString());
            } catch (Exception e) {
                waterAmount = 0.0;
            }
            calculatedValue = chemAmount / waterAmount;
            System.out.println("calculatedValue: " + calculatedValue);
            chemRatio.setValue(new Number(calculatedValue));
            AdfFacesContext.getCurrentInstance().addPartialTarget(chemRatio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setChemRatio(RichInputText chemRatio) {
        this.chemRatio = chemRatio;
    }

    public RichInputText getChemRatio() {
        return chemRatio;
    }
}
