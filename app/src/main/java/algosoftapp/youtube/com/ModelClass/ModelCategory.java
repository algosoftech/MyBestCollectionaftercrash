package algosoftapp.youtube.com.ModelClass;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by abc on 12/12/2018.
 */

public class ModelCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    private String type;
    private boolean isSelected;
    private ArrayList<String> setList = new ArrayList<>();

    public ModelCategory(){

    }
    public ModelCategory(String type){
        this.type=type;
    }
    public ModelCategory(String type,boolean isSelected){
        this.type=type;
        this.isSelected=isSelected;
    }
    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type=type;
    }
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public ArrayList<String> getSetList() {
        return setList;
    }

    public void setSetList(ArrayList<String> setList) {
        this.setList = setList;
    }
}
