public class InformationRequest extends Request {
    // Fields
    private String[] questions;

    // Getter
    /**
     * Getter of questions
     * @return
     */
    public String[] getQuestions(){
        return this.questions;
    }

    //Setter
    /**
     * Setter of questions
     * @param questions
     */
    public void setQuestions(String[] questions){
        this.questions = questions;
    }

    /**
     * Constructor of InformationRequest
     * @param description
     * @param priority
     * @param difficulty
     * @param questions
     */
    public InformationRequest (String description, int priority, int difficulty, String[] questions){
        setDescription(description);
        setPriority(priority);
        setDifficulty(difficulty);
        setQuestions(questions);
        setFactor(1);
        setStatus(Status.NEW);
        setStartTime(0);
        setEndTime(0);
    }

    @Override
    public int calculateProcessingTime(){
        int processingTime;
        processingTime = this.getDifficulty() * this.getFactor() * this.questions.length;
        return processingTime;
    }
}
