// Not actually sure if this one is right??????
// Can only be used by VIPClient, cannot accept clients who are not VIPClient
public class VIPQueue extends Queue{
    public VIPQueue (String serverName, int queueSize){
        super(serverName, queueSize);
    }
    
}
