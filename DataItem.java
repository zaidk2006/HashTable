
 /* ****************************************************************************
 * Name: Zaid Khoury
 * Date: 12/8/2015 
 * Project Info: CS 182 Lab Project 6
 * Description: This class is the class that I used to store the values in the
 * DataItem.
 * *****************************************************************************
 */

class DataItem {

    private String sData;
    
    private int iData;

    public DataItem(String ii) {
        sData = ii;
    }

    public int getKey() {
        return iData;
    }
    public String getDataItem(){
        return sData;
    }
}
