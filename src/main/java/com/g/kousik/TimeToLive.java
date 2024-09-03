package com.g.kousik;

@SuppressWarnings("all")
public class TimeToLive<T> implements Runnable{
    private T key;
    private int timeToLive;
    private Hunter callerHunterInstance;

    TimeToLive(T keyToDelete, int miliSeconds, Hunter hunterInstance){
        key = keyToDelete;
        timeToLive = miliSeconds;
        callerHunterInstance = hunterInstance;
    }

    void startTTL(){
        try {
            Thread ttlThread = new Thread(this);
            ttlThread.start();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void run(){
        try {
          Thread.sleep(timeToLive);
        } catch(Exception e){
            System.out.println(e.toString());
        }
        callerHunterInstance.delete(key);
    }
}
