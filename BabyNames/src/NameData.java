public class NameData {
    //fields
    private String BabyName;
    private int occurrence;

    //constructor, creates a NameData object, and initialises the BabyName and occurrence
    public NameData(String BabyName, int occurrence){
        this.BabyName = BabyName;
        this.occurrence = occurrence;
    }

    //getters - returns the BabyName/Occurrence
    public String getBabyName(){return this.BabyName;}
    public int getOccurrence(){ return this.occurrence; }

    //setters, sets the BabyName and occurrence to a specified parameter
    public void setBabyName(String BabyName){ this.BabyName = BabyName; }
    public void setOccurrence(int occurrence){ this.occurrence = occurrence; }

    public String toString(){ return "Name: " + this.BabyName + ", Occurrence: " + this.occurrence; }

    /**
     *
     * @param other, the other NameData object we want to compare this NameData object to
     * @return int, returns num > 0 if other comes after this, 0 if they are equal, and < 0 if other comes before this
     */
    public int compareTo(NameData other){ return this.BabyName.compareToIgnoreCase(other.BabyName); }

    /**
     * this method increases the occurrence of a BabyName by a value.
     * @param value, the amount we want to increase occurrence by
     */
    public void increment(int value){ this.occurrence += value; }
}
