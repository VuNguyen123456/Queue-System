public class VIPClient extends Client implements Prioritizable {
    // Fields
    private int priority;
    private int memberSince;
    //If there is no VIPQueue, this client behaves like any other client.
    // Getters
    
    /**
     * 
     * @return
     */
    public int getPriority(){
        return priority;
    }
    /**
     * 
     * @return
     */
    public int getMemberSince(){
        return memberSince;
    }

    // Setters
    /** 
     * 
    */
    public void setPriority(int a){
        this.priority = a;
    }
    /**
     * 
     * @param memberSince
     */
    public void setMemberSince(int memberSince){
        this.memberSince = memberSince;
    }

    // Constructor
    /**
     * 
     * @param firstName
     * @param lastName
     * @param birthYear
     * @param gender
     * @param arrivalTime
     * @param patience
     * @param requests
     * @param memberSince
     * @param priority
     */
    public VIPClient (String firstName, String lastName, int birthYear, String gender, int arrivalTime, int patience, Request[] requests, int memberSince, int priority){
        super(firstName, lastName, birthYear, gender, arrivalTime, patience, requests);
        setPriority(priority);
        setMemberSince(memberSince);
    }

    //Other methods
    @Override
    
    public String toString(){
        String str = super.toString();
        str += "** VIP since : " + getMemberSince();
        str += "** priority : "+ getPriority();
        return str;
    }

}
