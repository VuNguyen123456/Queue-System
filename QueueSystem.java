public class QueueSystem {
    // Fields
    private static int clock;
    private static int totalWaitingTime;
    private static Client[] clientsWorld;
    private static int waitingLineSize;
    private static Client[] waitingLine;
    private static boolean tvInWaitingArea;
    private static boolean coffeeInWaitingArea;
    private static Queue[] queues;
    private static int totalClientsInSystem;

    //Getters
    /**
     * Get Clock
     * @return
     */
    public static int getClock(){
        return QueueSystem.clock;
    }
    /**
     * Get TotalWaitingTime
     * @return
     */
    public static int getTotalWaitingTime(){
        return QueueSystem.totalWaitingTime;
    }
    /**
     * Get ClientsWorld
     * @return
     */
    public static Client[] getClientsWorld(){
        return QueueSystem.clientsWorld;
    }
    /**
     * Get WaitingLinesSize
     * @return
     */
    public static int getWaitingLineSize(){
        return QueueSystem.waitingLineSize;
    }
    /**
     * Get WaitingLine
     * @return
     */
    public static Client[] getWaitingLine(){
        return QueueSystem.waitingLine;
    }
    /**
     * Get TvInWaitingArea
     * @return
     */
    public static boolean getTvInWaitingArea(){
        return QueueSystem.tvInWaitingArea;
    }
    /**
     * Get CoffeInWaitingArea
     * @return
     */
    public static boolean getCoffeeInWaitingArea(){
        return QueueSystem.coffeeInWaitingArea;
    }
    /**
     * Get Queues
     * @return
     */
    public static Queue[] getQueues(){
        return QueueSystem.queues;
    }
    /**
     * Get TotalClientsInSystem
     * @return
     */
    public static int getTotalClientsInSystem(){
        return QueueSystem.totalClientsInSystem;
    }

    //Setters
    /**
     * Set Clock
     * @param clock
     */
    public static void setClock(int clock){
        QueueSystem.clock = clock;
    }
    /**
     * Set totalWaitingTime
     * @param totalWaitingTime
     */
    public static void setTotalWaitingTime(int totalWaitingTime){
        QueueSystem.totalWaitingTime = totalWaitingTime;
    }
    /**
     * Set ClientsWorld
     * @param clientsWorld
     */
    public static void setClientsWorld(Client[] clientsWorld){
        QueueSystem.clientsWorld = clientsWorld;
    }
    /**
     * Set WaitingLineSize
     * @param waitingLineSize
     */
    public static void setWaitingLineSize(int waitingLineSize){
        QueueSystem.waitingLineSize = waitingLineSize;
    }
    /**
     * Set 
     * @param waitingLine
     */
    public static void setWaitingLine(Client[] waitingLine){
        QueueSystem.waitingLine = waitingLine;
    }
    /**
     * Set TvInWaitingArea
     * @param tvlnWaitingArea
     */
    public static void setTvInWaitingArea(boolean tvlnWaitingArea){
        QueueSystem.tvInWaitingArea = tvlnWaitingArea;
    }
    /**
     * Set CoffeeTinWaitingArea
     * @param coffeeInWaitingArea
     */
    public static void setCoffeeInWaitingArea(boolean coffeeInWaitingArea){
        QueueSystem.coffeeInWaitingArea = coffeeInWaitingArea;
    }
    /**
     * Set Queues
     * @param queues
     */
    public static void setQueues(Queue[] queues){
        QueueSystem.queues = queues;
    }

    /**
     * Set totalClientsInSystem
     */
    public static void setTotalClientsInSystem(){
        int count = 0;
        for(int i = 0; i< waitingLine.length; i++){
            if(waitingLine[i] != null){
                count += 1;
            }
        }
        for(int i = 0; i < queues.length;i++){
            for(int j = 0; j < queues[i].getClientsInQueue().length; j++){
                count += 1;
            }
            if(queues[i].getClientBeingServed() != null){
                count += 1;
            }
        }
        totalClientsInSystem = count;

    }

    //Contructor
    /**
     * Contructor of QSystem
     * @param waitingLineSize
     * @param tvInWaitingArea
     * @param coffeeInWaitingArea
     */
    public QueueSystem (int waitingLineSize, boolean tvInWaitingArea, boolean coffeeInWaitingArea){
        QueueSystem.clock = 0;
        QueueSystem.waitingLine = new Client[waitingLineSize];
        setTvInWaitingArea(coffeeInWaitingArea);
        setCoffeeInWaitingArea(coffeeInWaitingArea);
    }
    //Other methods
    
    /**
     * Move Up the waitingLine when something in it is set to null (so that the first element of the waitingLine is mostly not null all the time)
     */
    public void moveUpWaitingLine(){
        for(int i =0; i < waitingLine.length-1; i++ ){
            if(waitingLine[i] == null){
                waitingLine[i] = waitingLine[i+1]; 
                waitingLine[i+1] = null;
            }
            }
        }
    
    /**
     * Move Up the ClientWorld when something in it is set to null (so that the first element of the ClientWorld is mostly not null all the time)
     */
    public void moveUpClientWorld(){
        for(int i = 0; i < clientsWorld.length-1; i ++){
            if(clientsWorld[i] == null){
                clientsWorld[i] = clientsWorld[i+1];
                clientsWorld[i+1] = null;
            }
        }
    }

    
    /**
     * Find the client that need to be added to queue based on the fifo policy when there is no VIPQueue in the whole system(could be wrong)
     * @return
     */
    public static Client Fifo(){
        // Check if the waitingLine is empty or not
        int count =0;
        for(int i = 0; i < waitingLine.length; i++){
            if(waitingLine[i] == null){
                count += 1;
            }
        }
        if(count == waitingLine.length){
            return null;
        }   
        // Initializing the currentlySelectedClient
        Client currentlySelectedClient = waitingLine[0];
        if(waitingLine != null){
        
        // Finding
        for(int i = 0; i < waitingLine.length; i++){
            if(waitingLine[i] != null){
            if(waitingLine[i].getArrivalTime() < currentlySelectedClient.getArrivalTime() 
            ||((waitingLine[i].getArrivalTime() == currentlySelectedClient.getArrivalTime()) && waitingLine[i].getYearOfBirth() < currentlySelectedClient.getYearOfBirth()) 
            ||(((waitingLine[i].getArrivalTime() == currentlySelectedClient.getArrivalTime()) && waitingLine[i].getYearOfBirth() == currentlySelectedClient.getYearOfBirth()) &&  waitingLine[i].getTotalProcessingTime() < currentlySelectedClient.getTotalProcessingTime())
            ||((((waitingLine[i].getArrivalTime() == currentlySelectedClient.getArrivalTime()) && waitingLine[i].getYearOfBirth() == currentlySelectedClient.getYearOfBirth()) &&  waitingLine[i].getTotalProcessingTime() == currentlySelectedClient.getTotalProcessingTime()) && waitingLine[i].getId() < currentlySelectedClient.getId())){
                currentlySelectedClient = waitingLine[i];
            }
            }
        }
        
    }
        return currentlySelectedClient;

    }

    /**
     * Find the non-VIP client that need to be added to normal queue based on the fifo policy (maybe wrong) 
     * @return
     */
    public static Client FifoNoVIP(){
        // Check if the waitingLine is empty or not
        int count = 0; 
        for(int i = 0; i < waitingLine.length; i++){
            if(waitingLine[i] == null){
                count += 1;
            }
        }
        if(count == waitingLine.length){
            return null;
        }
        // Initializing the currentlySelectedClient
        Client currentlySelectedClient = waitingLine[0];
        for(int i = 0; i < waitingLine.length; i++){
            if((!(waitingLine[i] instanceof VIPClient))){
                currentlySelectedClient = waitingLine[i];
                break;
            }
        }
        //Going through the fifo policy and finding the needed client
        for(int i = 0; i < waitingLine.length; i++){
            if(waitingLine[i] != null){
            if(waitingLine[i] instanceof VIPClient == false){
            if(waitingLine[i].getArrivalTime() < currentlySelectedClient.getArrivalTime() 
            ||((waitingLine[i].getArrivalTime() == currentlySelectedClient.getArrivalTime()) && waitingLine[i].getYearOfBirth() < currentlySelectedClient.getYearOfBirth()) 
            ||(((waitingLine[i].getArrivalTime() == currentlySelectedClient.getArrivalTime()) && waitingLine[i].getYearOfBirth() == currentlySelectedClient.getYearOfBirth()) &&  waitingLine[i].getTotalProcessingTime() < currentlySelectedClient.getTotalProcessingTime())
            ||((((waitingLine[i].getArrivalTime() == currentlySelectedClient.getArrivalTime()) && waitingLine[i].getYearOfBirth() == currentlySelectedClient.getYearOfBirth()) &&  waitingLine[i].getTotalProcessingTime() == currentlySelectedClient.getTotalProcessingTime()) && waitingLine[i].getId() < currentlySelectedClient.getId())){
                currentlySelectedClient = waitingLine[i];
            }
            }   
        }
        }
        return currentlySelectedClient;
        
    }

    /**
     * Find the VIP client that need to be added to VIP queue based on the fifo policy (maybe wrong)
     * @return
     */
    public static Client FifoVIP(){
        int count =0;
        for(int i = 0; i < waitingLine.length; i++){
            if(waitingLine[i] == null){
                count += 1;
            }
        }
        if(count == waitingLine.length){
            return null;
        }   
        // Initializing the currentlySelectedClient
        Client currentlySelectedClient = waitingLine[0];
        int check = 0;
        for(int i = 0; i< waitingLine.length; i++){
            if(waitingLine[i] instanceof VIPClient){
                currentlySelectedClient = waitingLine[i];
                break;
            }
            else{
                check += 1;
            }
        }
        //Going through the fifo policy and finding the needed client
        if(check != waitingLine.length){
        for(int i = 0; i < waitingLine.length; i++){
            if(waitingLine[i] != null){
            if(waitingLine[i] instanceof VIPClient == true){
            if(((VIPClient) waitingLine[i]).getPriority() > (((VIPClient) currentlySelectedClient).getPriority()) 
            ||((VIPClient) waitingLine[i]).getPriority() == (((VIPClient) currentlySelectedClient).getPriority()) && ((VIPClient) waitingLine[i]).getMemberSince() < (((VIPClient) currentlySelectedClient).getMemberSince()) 
            ||((VIPClient) waitingLine[i]).getPriority() == (((VIPClient) currentlySelectedClient).getPriority()) && ((VIPClient) waitingLine[i]).getMemberSince() == (((VIPClient) currentlySelectedClient).getMemberSince()) && waitingLine[i].getArrivalTime() < currentlySelectedClient.getArrivalTime() 
            ||((waitingLine[i].getArrivalTime() == currentlySelectedClient.getArrivalTime()) && waitingLine[i].getYearOfBirth() < currentlySelectedClient.getYearOfBirth()) 
            ||(((waitingLine[i].getArrivalTime() == currentlySelectedClient.getArrivalTime()) && waitingLine[i].getYearOfBirth() == currentlySelectedClient.getYearOfBirth()) &&  waitingLine[i].getTotalProcessingTime() < currentlySelectedClient.getTotalProcessingTime())
            ||((((waitingLine[i].getArrivalTime() == currentlySelectedClient.getArrivalTime()) && waitingLine[i].getYearOfBirth() == currentlySelectedClient.getYearOfBirth()) &&  waitingLine[i].getTotalProcessingTime() == currentlySelectedClient.getTotalProcessingTime()) && waitingLine[i].getId() < currentlySelectedClient.getId())){
                currentlySelectedClient = waitingLine[i];
            }
            }   
        }
        }
        }
        return currentlySelectedClient;
    }
    
    /**
     * If there is no VIP queue in the system at all
     * Get the right queue that the right client needed to add in when there's no vip queue
     * @return
     */
    public static Queue getAppropirateQueue(){
        // Initializing
        Queue currentlySelectedQueue = queues[queues.length-1];
        int j = 0;
        while(currentlySelectedQueue.checkQueueIfFull() || j == queues.length){
            currentlySelectedQueue = queues[j];
            j+= 1;
        }
        // Finding the right Queue
        if(queues != null){
        for(int i = 0; i < queues.length; i++){
            if(!(queues[i].checkQueueIfFull())){
                if(((queues[i].getClosestQueueToServer() < currentlySelectedQueue.getClosestQueueToServer())) || (( queues[i].getClosestQueueToServer() == currentlySelectedQueue.getClosestQueueToServer()) && queues[i].getQueueNumber() < currentlySelectedQueue.getQueueNumber())){
                    currentlySelectedQueue = queues[i];
                }
            }
        }
    }
        return currentlySelectedQueue;
    }

    /**
     * If there is a VIP queue in the System
     * Get the right normal queue for the right normal client to be put in
     * @return
     */
    public static Queue getAppropirateNormalQueue(){
        // Initializing
        Queue currentlySelectedQueue = queues[queues.length-1];
        int j = 0;
        while((currentlySelectedQueue.checkQueueIfFull() || (currentlySelectedQueue instanceof VIPQueue)) || j == queues.length){
            currentlySelectedQueue = queues[j];
            j += 1;
        }
        // Finding the right Queue
        if(queues != null){
        for(int i = 0; i < queues.length; i++){
            if(!(queues[i] instanceof VIPQueue) && !(queues[i].checkQueueIfFull())){
                if(((queues[i].getClosestQueueToServer() < currentlySelectedQueue.getClosestQueueToServer())) || ((queues[i].getClosestQueueToServer() == currentlySelectedQueue.getClosestQueueToServer()) && queues[i].getQueueNumber() < currentlySelectedQueue.getQueueNumber())){
                    currentlySelectedQueue = queues[i];
            }
            }
        }
    }
        return currentlySelectedQueue;

    }

    /**
     * If there is a VIP queue in the System
     * Get the right vipQueue for the right vipClient
     * @return
     */
    public static Queue getAppropirateVIPQueue(){
        // Initializing
        Queue currentlySelectedQueue = queues[queues.length-1];
        int j = 0;
        while((currentlySelectedQueue.checkQueueIfFull() || !(currentlySelectedQueue instanceof VIPQueue)) || j == queues.length){
            currentlySelectedQueue = queues[j];
            j += 1;
        }
        // Finding
        if(queues != null){
        for(int i = 0; i < queues.length; i++){
            if((queues[i] instanceof VIPQueue) && !(currentlySelectedQueue.checkQueueIfFull())){
                if(((queues[i].getClosestQueueToServer() < currentlySelectedQueue.getClosestQueueToServer())) || ((queues[i].getClosestQueueToServer() == currentlySelectedQueue.getClosestQueueToServer()) && queues[i].getQueueNumber() < currentlySelectedQueue.getQueueNumber())){
                currentlySelectedQueue = queues[i];
            }
            }
        }
    }
        return currentlySelectedQueue;
    }


    public void increaseTime(){
        //Increase the ckick time
        QueueSystem.clock += 1;
        for(int i = 0; i < queues.length; i++){
            //Assign client in Queue into server and remove clientBeingServe if they are
            queues[i].updateClientBeingServed();;
            queues[i].decrementPatienceInQueue();
        }
        
        //Check if the system have a vipQueue or not
        boolean vip = false;
        for(int i =0; i < queues.length; i++){
            if((queues[i] instanceof VIPQueue)){                    
                vip = true;
                break;
            }
        }
        //If not
        if(vip == false){
            int totalBlankSpaceInQueues = 0;
            // Get all the blank spot in the queues
            for(int i =0; i < queues.length; i++){
                totalBlankSpaceInQueues += queues[i].getEmptySpots();
            }
            // For each blank spot get one of the client in side waitingLine in
            for(int i = 0; i < totalBlankSpaceInQueues; i++){
                getAppropirateQueue().addClientInsideQueue();
                // Removing the client insaide waitingLine
                for(int j = 0; j < waitingLine.length; j ++){
                    if(waitingLine[j] == QueueSystem.Fifo()){
                        waitingLine[j] = null;
                        moveUpWaitingLine();
                    }
                }
            }
        }
        //TODO: add 1 when all of them is a vipqueue
        else if(vip == true){
            //Looking through normal Queue only then add them up like the previous method but this time only the non vip queue and client
            int totalBlankSpaceInNormalQueues = 0;
            int totalBlankSpaceInVIPQueues = 0;
            for(int i =0; i < queues.length; i++){
                if(!(queues[i] instanceof VIPQueue)){
                    totalBlankSpaceInNormalQueues += queues[i].getEmptySpots();
                }
            }
            for(int i = 0; i < totalBlankSpaceInNormalQueues; i++){
                getAppropirateNormalQueue().addClientInsideNormalQueue();
                for(int j = 0; j < waitingLine.length; j ++){
                    if(waitingLine[j] == QueueSystem.FifoNoVIP()){
                        waitingLine[j] = null;
                        moveUpWaitingLine();
                    }
                }
            }

            //Looking through VIP Queue only then add them up like the previous method but this time only the vip queue and client
            for(int i =0; i < queues.length; i++){
                if((queues[i] instanceof VIPQueue) ==  true){
                    totalBlankSpaceInVIPQueues += queues[i].getEmptySpots();
                }
            }
            for(int i = 0; i < totalBlankSpaceInVIPQueues; i++){
                getAppropirateVIPQueue().addClientInsideVIPQueue();
                for(int j = 0; j < waitingLine.length; j ++){
                    if(waitingLine[j] == QueueSystem.FifoVIP()){
                        waitingLine[j] = null;
                        moveUpWaitingLine();
                    }
                }
            }
        }
            
        // Setting the patience of client in waitingLine
        if(waitingLine != null){
            for(int i =0; i < waitingLine.length; i++){
                if(waitingLine[i] != null){
                    waitingLine[i].setPatience(waitingLine[i].getPatience() -1);
                    if(waitingLine[i].getPatience() < 0){
                        waitingLine[i] = null;
                        moveUpWaitingLine();
                    }
                }
            }
        }

        //Adding the client in ClientWorld into WaitingLine
        for(int i =0; i < clientsWorld.length; i++){
            if(clientsWorld[i] != null){
                if(clientsWorld[i].getArrivalTime() == QueueSystem.clock){
                    for(int j =0; j < waitingLine.length; j++){
                        if(waitingLine[j] == null){
                            waitingLine[j]= clientsWorld[i];
                            waitingLine[j].updatePatience();
                            clientsWorld[i]=null;
                            break;
                        }
                    }
                }
            }
            }
        }
        
    
    /**
     * IncreaseTime() method
     * @param time
     */
    public void increaseTime(int time){
        for(int i =0; i < time; i++){
            increaseTime();
        }
    }

    /**
     * Get the client being serve in each queue right now
     * @return
     */
    public Client[] getClientsBeingServed(){
        int numberOfQueues = 0;
        for(int i=0; i< queues.length; i++){
            if(queues[i].getClientBeingServed() != null){
                numberOfQueues += 1;
            }
        }
        Client[] temp = new Client[numberOfQueues];
        for(int i=0; i< queues.length; i++){
            if(queues[i].getClientBeingServed() != null && temp[i] == null){
                temp[i]=queues[i].getClientBeingServed();
            }
        }
        return temp;
        //TODO:  returns the list of clients being served
    
    }

    /**
     * ToString
     */
    public String toString(){
        String str = "[WaitingLine]-";
        for(int i = 0; i < waitingLine.length; i ++){
            if(waitingLine[i] != null){
                if(waitingLine[i].getTotalProcessingTime() < 10){
                    str += "[0" + waitingLine[i].getTotalProcessingTime() + "]";
                }
                else{
                   str += "[" + waitingLine[i].getTotalProcessingTime() + "]"; 
                }
                }
            else{
                str += "[]";
        }
        }
        str += "\n---\n";
        for(int i = 0; i < queues.length; i++){
            str += queues[i].toString(false);
            str += "\n";
        }
        return str;
        }
        

    /**
     * toString method
     * @param showID
     * @return
     */
    public String toString(boolean showID){
        String str = "";
        if(showID == false){
            str = this.toString();
        }
        else{
            str = "[WaitingLine]-";
        for(int i = 0; i < waitingLine.length; i ++){
            if(waitingLine[i] != null){
            if(waitingLine[i].getId() < 10){
                str += "[0" + waitingLine[i].getId() + "]";
            }
            else{
               str += "[" + waitingLine[i].getId() + "]"; 
            }
            }
            else{
                str += "[]";
            }
        }
        str += "\n---\n";
        for(int i = 0; i < queues.length; i++){
            str += queues[i].toString();
            str += "\n";
        }
        }
        return str;
    }

    
}
