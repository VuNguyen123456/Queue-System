
public abstract class Request implements Prioritizable{
    // Fields
    private String description;
    private int priority;
    private int difficulty;
    private int factor;
    private int startTime;
    private int endTime;
    private Status status;

    // Getters
    public String getDescription(){
        return description;
    }
    public int getPriority(){
        return priority;
    }
    public int getDifficulty(){
        return difficulty;
    }
    public int getFactor(){
        return factor;
    }
    public int getStartTime(){
        return startTime;
    }
    public int getEndTime(){
        return endTime;
    }
    public Status getStatus(){
        return status;
    }

    // Setters
    /**
     * Set Description
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
    }
    
    public void setPriority (int priority){
        this.priority = priority;
    }
    /**
     * Set Difficulty
     * @param difficulty
     */
    public void setDifficulty (int difficulty){
        this.difficulty = difficulty;
    }
    /**
     * Set Factor
     * @param factor
     */
    public void setFactor (int factor){
        this.factor = factor;
    }
    /**
     * Set StartTime
     * @param startTime
     */
    public void setStartTime (int startTime){
        this.startTime = startTime;
    }
    /**
     * Set EndTime
     * @param endTime
     */
    public void setEndTime (int endTime){
        this.endTime = endTime;
    }
    /**
     * Set Status
     * @param status
     */
    public void setStatus(Status status){
        this.status = status;
    }

    public abstract int calculateProcessingTime();
    /*The time required to process a request depends on the difficulty level of this
request and on the type of request. Compute this time using the following
formula:
- Information request:
Processing time = Request difficulty x factor x numberOfQuestions
- Returning item:
Processing time = Request difficulty x factor x numberOfItems
- Buying product:
Processing time = Request difficulty x factor x numberOfItems */
}