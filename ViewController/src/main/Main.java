package main;

import javax.faces.event.ValueChangeEvent;

import model.services.AppModuleImpl;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.ApplicationModule;
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
    
    public ApplicationModule getAppM() {
        DCBindingContainer bindingContainer =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        //BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContainer.findDataControl("AppModuleDataControl"); // Name of application module in datacontrolBinding.cpx
        AppModuleImpl appM = (AppModuleImpl)dc.getDataProvider();
        return appM;
    }
    AppModuleImpl appM = (AppModuleImpl)this.getAppM();

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
            Double changedAvgWeight = 0.0;
            try {
                changedAvgWeight = Double.parseDouble(valueChangeEvent.getNewValue().toString());
            } catch (Exception e) {
                changedAvgWeight = 0.0;
            }
            callCalculateTotWeightForAvgW(changedAvgWeight);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void valueChanListLotQty(ValueChangeEvent valueChangeEvent) {
        try {
            System.out.println("Changed Lot Qty: " + valueChangeEvent.getNewValue());
            Double changedLotQty = 0.0;
            try {
                changedLotQty = Double.parseDouble(valueChangeEvent.getNewValue().toString());
            } catch (Exception e) {
                changedLotQty = 0.0;
            }
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
                if (calculatedValue.toString().equals("Infinity")){
                        totalWeightInputText.setValue(new Number(0));
                }
                else{
                    totalWeightInputText.setValue(new Number(calculatedValue));
                }
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
                if (calculatedValue.toString().equals("Infinity")){
                        totalWeightInputText.setValue(new Number(0));
                }
                else{
                    totalWeightInputText.setValue(new Number(calculatedValue));
                }
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
            Double changedWaterAmount = 0.0;
            try {
                changedWaterAmount = Double.parseDouble(valueChangeEvent.getNewValue().toString());
            } catch (Exception e) {
                changedWaterAmount = 0.0;
            }
            callCalculateChemRatioForWaterAmount(changedWaterAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void valueChanListChemAmount(ValueChangeEvent valueChangeEvent) {
        try {
            System.out.println("Changed Chem Amount: " + valueChangeEvent.getNewValue());
            Double changedChemAmount = 0.0;
            try {
                changedChemAmount = Double.parseDouble(valueChangeEvent.getNewValue().toString());
            } catch (Exception e) {
                changedChemAmount = 0.0;
            }
            
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
            if (!calculatedValue.toString().equals("Infinity")){
                try {
                    appM.getMjGwlRecipeDetailVO1().getCurrentRow().setAttribute("ChemicalRatio", calculatedValue);
                } catch (Exception e) {
                    appM.getMjGwlRecipeDetailVO1().getCurrentRow().setAttribute("ChemicalRatio", new Number(0));
                }
            }else{
                appM.getMjGwlRecipeDetailVO1().getCurrentRow().setAttribute("ChemicalRatio", new Number(0));
            }
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
            try {
                calculatedValue = chemAmount / waterAmount;
            } catch (Exception e) {
                calculatedValue = 0.0;
            }
            System.out.println("calculatedValue: " + calculatedValue);
            if (!calculatedValue.toString().equals("Infinity")){
                try {
                    appM.getMjGwlRecipeDetailVO1().getCurrentRow().setAttribute("ChemicalRatio", calculatedValue);
                } catch (Exception e) {
                    appM.getMjGwlRecipeDetailVO1().getCurrentRow().setAttribute("ChemicalRatio", new Number(0));
                }
            }else{
                appM.getMjGwlRecipeDetailVO1().getCurrentRow().setAttribute("ChemicalRatio", new Number(0));
            }
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
