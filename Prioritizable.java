public interface Prioritizable{
    /**
     * Abstract class to set priority
     * @param a
     */
    public void setPriority(int a);
    /**
     * Abstract class to get priority
     * @return
     */
    public int getPriority();
}
// will be inplement by: Request and VIPClient