public class BuyingProducts extends Request {
    // Fields
    private String[] itemsToBuy;

    // Getter
    /**
     * Getter of ItemsToBuy
     * @return
     */
    public String[] getItemsToBuy(){
        return this.itemsToBuy;
    }

    //Setter
    /**
     * Setter of itemsToBuy
     * @param itemsToBuy
     */
    public void setItemsToBuy(String[] itemsToBuy){
        this.itemsToBuy = itemsToBuy;
    }

    // Contructor
    /**
     * Contructor of Item to buy
     * @param description
     * @param priority
     * @param difficulty
     * @param itemsToBuy
     */
    public BuyingProducts (String description, int priority, int difficulty, String[] itemsToBuy){
        setDescription(description);
        setPriority(priority);
        setDifficulty(difficulty);
        setItemsToBuy(itemsToBuy);
        setFactor(2);
        setStatus(Status.NEW); 
        setStartTime(0);
        setEndTime(0);
    }

    @Override
    public int calculateProcessingTime(){
        int processingTime;
        processingTime = this.getDifficulty() * this.getFactor() * this.itemsToBuy.length;
        return processingTime;
    }
}
