public class ReturningItems extends Request {
    // Fields
    private String[] itemsToReturn;

    // Getter
    /**
     * Getter of ItemsToReturn
     * @return
     */
    public String[] getItemsToReturn(){
        return this.itemsToReturn;
    }

    //Setter
    /**
     * Setter of ItemsToReturn
     * @param itemsToReturn
     */
    public void setItemsToReturn(String[] itemsToReturn){
        this.itemsToReturn = itemsToReturn;
    }

    // Contructor
    /**
     * Constructor of ReturningItems
     * @param description
     * @param priority
     * @param difficulty
     * @param itemsToReturn
     */
    public ReturningItems (String description, int priority, int difficulty, String[] itemsToReturn){
        setDescription(description);
        setPriority(priority);
        setDifficulty(difficulty);
        setItemsToReturn(itemsToReturn);
        setFactor(3);
        setStatus(Status.NEW);
        setStartTime(0);
        setEndTime(0);
    }

    @Override
    public int calculateProcessingTime(){
        int processingTime;
        processingTime = this.getDifficulty() * this.getFactor() * this.itemsToReturn.length;
        return processingTime;
    }
}
