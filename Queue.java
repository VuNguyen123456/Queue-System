/*
 * TODO: Clients cannot join the queue directly from the world. 
 * A client must go to the waitingLine first, then to the queue, 
 * then will be served following the logic described in the increaseTime method of class QueueSystem.
 * 
 * TODO: Note: project has also been changed such that clients CANNOT go directly into a server if a queue is empty 
 * ("A client can join a queue if the queue is not full, and the server is busy. 
 * If the server is not busy, and the queue is empty, the client goes to the server directly" 
 * has been deleted in the second version of the lab spec)
 */
public class Queue {
    // Fields
    private String serverName;
    private int queueSize; 
    private Client clientBeingServed;
    private Request requestInProgress;
    private int processingStartTime;
    private Client[] clientsHistory =new Client[0];
    private Client[] clientsInQueue;

    //Getter
    /**
     * 
     * @return
     */
    public String getServerName (){
        return serverName;
    }
    /**
     * 
     * @return
     */
    public int getQueueSize(){
        return queueSize;
    }
    /**
     * 
     * @return
     */
    public Client getClientBeingServed(){
        return clientBeingServed;
    }
    /**
     * 
     * @return
     */
    public Request getRequestInProgress(){
        return requestInProgress;
    }
    /**
     * 
     * @return
     */
    public int getProcessingStartTime(){
        return processingStartTime;
    }
    /**
     * 
     * @return
     */
    public Client[] getClientsHistory(){
        return clientsHistory;
    }
    /**
     * 
     * @return
     */
    public Client[] getClientsInQueue(){
        return clientsInQueue;
    }

    //Setter
    /**
     * 
     * @param serverName
     */
    public void setServerName(String serverName){
        this.serverName = serverName;
    }
    /**
     * 
     * @param queueSize
     */
    public void setQueueSize(int queueSize){
        this.queueSize = queueSize;
    }
    /**
     * 
     * @param clientBeingServed
     */
    public void setClientBeingServed(Client clientBeingServed){
        this.clientBeingServed = clientBeingServed;
    }
    /**
     * 
     * @param requestInProgress
     */
    public void setRequestInProgress(Request requestInProgress){
        this.requestInProgress = requestInProgress;
    }
    /**
     * 
     * @param processingStartTime
     */
    public void setProcessingStartTime(int processingStartTime){
        this.processingStartTime = processingStartTime;
    }
    /**
     * 
     * @param clientsHistory
     */
    public void setClientsHistory(Client[]clientsHistory){
        this.clientsHistory = clientsHistory;
    }
    /**
     * 
     * @param queueSize
     */
    public void setClientsInQueue(int queueSize){
        clientsInQueue = new Client[queueSize];
    }

    // Constructor
    /**
     * 
     * @param serverName
     * @param queueSize
     */
    public Queue (String serverName, int queueSize){
        setServerName(serverName);
        setClientsInQueue(queueSize);
        setQueueSize(queueSize);
    }

    // Other methods
    /**
     * add client into the queue history
     * @param client
     */
    public void addClientsHistory(Client client){
        Client[] temp = new Client[this.clientsHistory.length + 2];
        for(int i = 0; i < this.clientsHistory.length; i++){
            temp[i] = this.clientsHistory[i];
        }
        this.clientsHistory = temp;
        for(int i = 0; i < this.clientsHistory.length; i++){
            if(this.clientsHistory[i] == null){
                this.clientsHistory[i] = client;
                break;
            }
        }
    }
    /**
     * Moving up the queue (often used when a client has been removed in the queue)
     */
    public void moveUpQueue(){
        for(int i =0; i < clientsInQueue.length-1; i++ ){
            if(clientsInQueue[i] == null){
                clientsInQueue[i] = clientsInQueue[i+1]; 
                clientsInQueue[i+1] = null;
            }
            }
        }

    /**
     * Get the empty spot that a queue have
     * @return
     */
    public int getEmptySpots(){
        int spot =0;
        if(clientsInQueue != null){
        for(int i =0; i < clientsInQueue.length; i++){
            if(clientsInQueue[i] == null){
                spot += 1;
            }
        }
        }
            return spot;
        }

    /**
     * check if the queue is full or not?
     * @return
     */
    public boolean checkQueueIfFull(){
        int check = 0; 
        if(clientsInQueue != null){
            for(int i = 0; i < clientsInQueue.length; i++){
                if(clientsInQueue[i]!= null){
                    check += 1;
                }
            }
            if(check == clientsInQueue.length){
                return true;
            }
        }
        return false;

    }

    /**
     * Check all the queue and find out what is the fewest amount of people in the queue
     * @return
     */
    public int getClosestQueueToServer(){
        int takenSpot = 0;
        if(clientsInQueue != null){
            for(int i =0; i < clientsInQueue.length; i++){
                if(clientsInQueue[i] != null){
                    takenSpot += 1;
                }
            }
        }
        return takenSpot;
    }
    
    /**
     * Get the queue number
     * @return
     */
    public int getQueueNumber(){
        int num =0;
        for(int i = 0; i < QueueSystem.getQueues().length; i++){
            if(QueueSystem.getQueues()[i] == this){
                num = i+1;
                break;
            }
        }
        return num;
    } 

    /**
     * Update the client in the server line
     */
    public void updateClientBeingServed(){
        // If the clientBeing server is not there
        if(clientBeingServed == null){
            if(clientsInQueue[0] != null){
                clientBeingServed = clientsInQueue[0];
                clientsInQueue[0] = null;
                clientBeingServed.setTimeStartServeTime(QueueSystem.getClock());
                clientsInQueue[0] = null;
                moveUpQueue();
            }
        }
        else{
            if(clientBeingServed.getRemainingProcessingTime() == 0){
                //Changing the clientBeingServe if the previous one is done 
                addClientsHistory(clientBeingServed);
                clientBeingServed = null;
                if(clientsInQueue[0] != null){
                clientBeingServed = clientsInQueue[0];
                clientBeingServed.setTimeStartServeTime(QueueSystem.getClock());
                clientsInQueue[0] = null;
                moveUpQueue();
                }
            }
        }
    }
    /**
     * Go into a queue then decrease their patience
     */
    public void decrementPatienceInQueue(){
        if(clientsInQueue != null){
            for(int i =0; i < clientsInQueue.length; i++){
                if(clientsInQueue[i] != null){
                    clientsInQueue[i].setPatience(clientsInQueue[i].getPatience() - 1);
                    if(clientsInQueue[i].getPatience() <0){
                        addClientsHistory(clientsInQueue[i]);
                        clientsInQueue[i] = null;
                        moveUpQueue();
                    }
                }
            }
        }
    }

    /**
     * If there is no VIPQueue
     * Add the correct client inside the queue 
     */
    public void addClientInsideQueue(){
        for(int i = 0; i < clientsInQueue.length; i++){
            if(clientsInQueue[i] == null){
                clientsInQueue[i]= QueueSystem.Fifo();
                break;
            }
        }
    }
    /**
     * If there is VIPQueue
     * Add the correct normal client inside the queue 
     */
    public void addClientInsideNormalQueue(){
        for(int i = 0; i < clientsInQueue.length; i++){
            if(clientsInQueue[i] == null){
                clientsInQueue[i]= QueueSystem.FifoNoVIP();
                break;
            }
        
    }
    }
    /**
     * If there is VIPQueue
     * Add the correct VIP client inside the queue 
     */
    public void addClientInsideVIPQueue(){
        for(int i = 0; i < clientsInQueue.length; i++){
            if(clientsInQueue[i] == null){
                clientsInQueue[i]= QueueSystem.FifoVIP();
                break;
            }
        
    }
    }

    /**
     * ToString
     */
    public String toString(){
        int num =0;
        String str ="";
        for(int i = 0; i < QueueSystem.getQueues().length; i++){
            if(QueueSystem.getQueues()[i] == this){
                num = i+1;
            }
        }
        str = "[Queue:" + num +"]";
        //Check with TA if this is right?
        if(clientBeingServed != null){
            if(clientBeingServed.getId() < 10){
                str += "[0"+ clientBeingServed.getId() +"]";
            }
            else{
                str += "["+ clientBeingServed.getId() +"]";
            }
            }
        else{
            str += "[]";
        }
        str += "-----";
        
        if(clientsInQueue != null){
            for(int i = 0; i < clientsInQueue.length; i ++){
                if(clientsInQueue[i] != null){
                    if(clientsInQueue[i].getId() < 10){
                        str += "[0"+ clientsInQueue[i].getId() +"]";
                    }
                    else{
                        str += "["+ clientsInQueue[i].getId() +"]";
                    }
                }
                else{
                    str += "[" + "]";
                }
            }
        }
        return str;
    }
    /**
     * ToString
     */
    public String toString(boolean showID){
        String str = "";
        if(showID){
            str = this.toString();
        }
        else{
            int num =0;
            for(int i = 0; i < QueueSystem.getQueues().length; i++){
                if(QueueSystem.getQueues()[i] == this){
                    num = i+1;
                }
            }
            str = "[Queue:" + num + "]";
            if(clientBeingServed != null){
                if(clientBeingServed.getRemainingProcessingTime() < 10){
                    str += "[0"+ clientBeingServed.getRemainingProcessingTime() +"]";
                }
                else{
                    str += "["+ clientBeingServed.getRemainingProcessingTime() +"]";
                }
            }
            else{
                str += "[]";
            }
            str += "-----";
            if(clientsInQueue != null){
                for(int i = 0; i < clientsInQueue.length; i ++){
                    if(clientsInQueue[i] != null){
                        if(clientsInQueue[i].getTotalProcessingTime() < 10){
                            str += "[0"+ clientsInQueue[i].getTotalProcessingTime() +"]";
                        }
                        else{
                            str += "["+ clientsInQueue[i].getTotalProcessingTime() +"]";
                        }
                    }
                    else{
                        str += "[]";
                    }
                }
            }
        }
        return str;
    }
}
