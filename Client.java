/*
 * Business rules:
Regardless of the method used, the object state needs to be consistent and have
correct values at all times. The validation and business rules are the following:
- arrivalTime cannot be negative number and must be greater or equal to 1.
- departureTime is either 0 (default value) or needs to be greater than or equal
to arrivalTime + waitingTime + timeInQueue. We would observe
departureTime = arrivalTime + waitingTime + timeInQueue only if the client
left without being served.
- The patience value of a client might increase once joining the waiting line
based on the availability of a tv and/or coffee maker in the QueueSystem;
- The client will exit the system if his time in the waitingLine and a server
queue exceeds his patience value.
- waitingTime cannot be a negative number.
- timeInQueue cannot be a negative number.

 */
public class Client {
    private static int keepingTrackOfId = 0;
    private int id;
    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private Gender gender;
    private Request[] requests;
    private int arrivalTime;
    private int waitingTime;
    private int timeInQueue;
    private int serviceTime; 
    private int departureTime = 0;
    private int patience;
    private int timeStartServeTime;
    private int clientRequestsStartTime;
    // Getters
    /**
     * Get the total processingTime of all request a client have
     * @return
     */
    public int getTotalProcessingTime(){
        int sum = 0;
        if(requests != null){
        for(int i =0; i < requests.length; i++){
            if(requests[i] != null){
            sum += requests[i].calculateProcessingTime();
        }
    }
}
        return sum;
    
    }
    /**
     * Get the remainingTime of the client that is being served
     * @return
     */
    public int getRemainingProcessingTime(){
        int remain =  0;
        //Method: reminTime = totalTime - (clockNow - clockWhenAddedToServer) 
        remain = this.getTotalProcessingTime() - getTimePassSinceServeTime();
        return remain;
    }
    /**
     * (clockNow - clockWhenAddedToServer)
     * @return
     */
    public int getTimePassSinceServeTime(){
        return QueueSystem.getClock() - timeStartServeTime; 
    }
    /**
     * 
     * @return
     */
    public int getId(){
        return id;
    }
    /**
     * 
     * @return
     */
    public int getClientRequestsStartTime (){
        return clientRequestsStartTime;
    }
    /**
     * 
     * @return
     */
    public String getFirstName(){
        return firstName;
    }
    /**
     * 
     * @return
     */
    public String getLastName(){
        return lastName;
    }
    /**
     * 
     * @return
     */
    public int getYearOfBirth(){
        return yearOfBirth;
    }
    /**
     * 
     * @return
     */
    public Gender getGender(){
        return gender;
    }
    /**
     * 
     * @return
     */
    public Request[] getRequests(){
        return requests;
    }
    /**
     * 
     * @return
     */
    public int getArrivalTime(){
        return arrivalTime;
    }
    /**
     * 
     * @return
     */
    public int getWaitingTime(){
        return waitingTime;
    }
    /**
     * 
     * @return
     */
    public int getTimeInQueue(){
        return timeInQueue;
    }
    /**
     * 
     * @return
     */
    public int getServiceTime(){
        return serviceTime;
    }
    /**
     * 
     * @return
     */
    public int getDepartureTime(){
        return departureTime;
    }
    /**
     * 
     * @return
     */
    public int getPatience(){
        return patience;
    }


    // Setter
    /**
     * 
     * @param timeStartServeTime
     */
    public void setTimeStartServeTime(int timeStartServeTime){
        this.timeStartServeTime = timeStartServeTime;
    }
    /**
     * setId
     */
    public void setId (){
        keepingTrackOfId += 1;
        this.id = keepingTrackOfId;
    }
    /**
     * 
     * @param firstName
     */
    public void setFirstName (String firstName){
        this.firstName = firstName;
    }
    /**
     * 
     * @param lastName
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    /**
     * 
     * @param yearOfBirth
     */
    public void setYearOfBirth(int yearOfBirth){
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * 
     * @param gender
     */
    public void setGender(Gender gender){
        this.gender = gender;
    }
    /**
     * 
     * @param requests
     */
    public void setRequests(Request[] requests){
        this.requests = requests;
    }
    /**
     * 
     * @param arrivalTime
     */
    public void setArrivalTime(int arrivalTime){
        if(arrivalTime >= 1){
            this.arrivalTime = arrivalTime;
        }
        else{
            this.arrivalTime = 1 + QueueSystem.getClock() ;
        }
    }
    
    /**
     * 
     * @param waitingTime
     */
    public void setWaitingTime(int waitingTime){
        if(waitingTime >= 0){
            this.waitingTime = waitingTime;
        }
    }
    /**
     * 
     * @param timeInQueue
     */
    public void setTimeInQueue(int timeInQueue){
        if(timeInQueue >= 0){
            this.timeInQueue = timeInQueue;
        }
    }
    
    /**
     * Set the service time of the client
     * @param serviceTime
     */
    public void setServiceTime(int serviceTime){
        int sum =0;
        for(int i =0; i < requests.length; i++){
            sum += requests[i].calculateProcessingTime();
        }
        this.serviceTime = sum;
    }
    /**
     * 
     * @param departureTime
     */
    public void setDepartureTime(int departureTime){
        if(departureTime == 0 || departureTime >= (arrivalTime + waitingTime + timeInQueue)){
            this.departureTime = departureTime;
        }
    }
    /**
     * 
     * @param patience
     */
    public void setPatience(int patience){
        this.patience = patience;
    }

    // Constructors
    /**
     * 
     * @param firstName
     * @param lastName
     * @param yearOfBirth
     * @param gender
     * @param arrivalTime
     * @param patience
     * @param requests
     */
    public Client (String firstName, String lastName, int yearOfBirth, String gender, int arrivalTime, int patience, Request[] requests){
        setFirstName(firstName);
        setLastName(lastName);
        setYearOfBirth(yearOfBirth);
        if(gender.equals("MALE")){
            this.gender = Gender.MALE;
        }
        else if(gender.equals("FEMALE")){
            this.gender = Gender.FEMALE;
        }
        setArrivalTime(arrivalTime);
        this.patience = patience;
        setRequests(requests);
        setId();;
        this.waitingTime = 0;
        this.timeInQueue = 0;
        this.serviceTime = 0;
        this.departureTime = 0;
    }

    /**
     * 
     * @param firstName
     * @param lastName
     * @param yearOfBirth
     * @param gender
     * @param patience
     * @param requests
     */
    public Client (String firstName, String lastName, int yearOfBirth, String gender, int patience, Request[] requests){
        setFirstName(firstName);
        setLastName(lastName);
        setYearOfBirth(yearOfBirth);
        if(gender.equals("MALE")){
            this.gender = Gender.MALE;
        }
        else if(gender.equals("FEMALE")){
            this.gender = Gender.FEMALE;
        }
        this.patience = patience;
        setRequests(requests);
        this.id += 1;
        this.arrivalTime = 1 + QueueSystem.getClock();
        this.waitingTime = 0;
        this.timeInQueue = 0;
        this.serviceTime = 0;
        this.departureTime = 0;
    }

    /**
     * 
     * @param firstName
     * @param lastName
     * @param yearOfBirth
     * @param gender
     * @param patience
     */
    public Client (String firstName, String lastName, int yearOfBirth, String gender, int patience){
        setFirstName(firstName);
        setLastName(lastName);
        setYearOfBirth(yearOfBirth);
        if(gender.equals("MALE") || gender.equals(null)){
            this.gender = Gender.MALE;
        }
        else if(gender.equals("FEMALE")){
            this.gender = Gender.FEMALE;
        }
        this.patience = patience;
        this.id += 1;
        this.arrivalTime = 1 + QueueSystem.getClock();
        this.waitingTime = 0;
        this.timeInQueue = 0;
        this.serviceTime = 0;
        this.departureTime = 0;

    }
    // Other methods
    /**
     * Esitmating the serviceLevel of a client
     * @return
     */
    public int estimateServiceLevel(){
        int valueEvaluation = 0; 
        if(QueueSystem.getClientsWorld() == null){
            return 0;
        }
        for(int i =0; i < QueueSystem.getClientsWorld().length; i++){
            if(QueueSystem.getClientsWorld()[i] == this){
                return -1;
            }
        }
        if(waitingTime <= 4){
            valueEvaluation += 0;
        }
        else if(waitingTime <=6){
            valueEvaluation += 1;
        }
        else if(waitingTime <=8){
            valueEvaluation += 2;
        }
        else if(waitingTime <=10){
            valueEvaluation += 3;
        }
        else if(waitingTime <=12){
            valueEvaluation += 4;
        }
        else {
            valueEvaluation += 5;
        }

        if(timeInQueue <= 4){
            valueEvaluation += 0;
        }
        else if(timeInQueue <=6){
            valueEvaluation += 1;
        }
        else if(timeInQueue <=8){
            valueEvaluation += 2;
        }
        else if(timeInQueue <=10){
            valueEvaluation += 3;
        }
        else if(timeInQueue<=12){
            valueEvaluation += 4;
        }
        else {
            valueEvaluation += 5;
        }
        return 10-valueEvaluation;

    }

    /**
     * Update the patince of a client when they get into a waitingLine
     */
    public void updatePatience(){
        if(QueueSystem.getTvInWaitingArea()){
            this.patience = this.patience+  20;
        }
        if(QueueSystem.getCoffeeInWaitingArea() && yearOfBirth <= 2005){
            this.patience = this.patience+  15;
        }
    }

    /**
     * Get the server of a client
     * @return
     */
    public String servedBy(){
        if(QueueSystem.getQueues() == null){
            return "";
        }
        for(int i = 0; i < QueueSystem.getQueues().length; i++){
            if(QueueSystem.getQueues()[i] == null || QueueSystem.getQueues()[i].getClientsInQueue() == null){
                for(int j = 0; j < QueueSystem.getQueues()[i].getClientsInQueue().length; ){
                    if(QueueSystem.getQueues()[i].getClientsInQueue()[j] == this){
                        return QueueSystem.getQueues()[i].getServerName();
                    }
                }
            }
        }
        return "";
    }

    /**
     * ToString
     */
    public String toString(){
        String str = "Clients: ";
        str += lastName + ", " + firstName;
        str += "** Arrival Time : " + getArrivalTime();
        str += "** Waiting Time : " + getWaitingTime();
        str += "** Time In Queue : " + getTimeInQueue();
        str += "** Service Time : " + getServiceTime();
        str += "** Departure Time : " + getDepartureTime();
        str += "** Served By Server : " + servedBy(); 
        str += "Service Level : " + this.estimateServiceLevel(); 
        return str;
    }


}
