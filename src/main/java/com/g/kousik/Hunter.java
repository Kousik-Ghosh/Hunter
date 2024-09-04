package com.g.kousik;

import java.util.HashMap;
import java.util.LinkedList;

@SuppressWarnings("all")
public class Hunter<T>{
    private HashMap<T, T> hashStore = new HashMap<>();
    
    /**
     * Associates the specified value with the specified key.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     */
    T insert(T key, T value){
        return hashStore.put(key, value);
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     */
    T get(T Key){
        return hashStore.get(Key);
    }

      /**
     * Removes the mapping for the specified key, if present.
     *
     * @param  key key whose mapping is to be removed from the map
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     */
    T delete(T key){
        return hashStore.remove(key);
    }

    /**
     * Sets time to live for a key value pair
     *
     * @param  key key whose mapping is to be removed from the map
     * @param  miliSeconds time to live in miliseconds
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     */
    void setTTL(T key, int miliSeconds) {
        TimeToLive ttl = new TimeToLive(key, miliSeconds, this);
        ttl.startTTL();
    }

    /**
     * Creates a new list, if not present and associates with the specified key
     *
     * @param  key key whose mapping is to be removed from the map
     * @param  value value to be inserted in the list.
     * @return int - size of the list. {@code -1} if new value is of diffrent type
     *         than existing list type.
     */
    int listPush(T key, T value){
       if (hashStore.get(key) == null){
           LinkedList<T> _list = new LinkedList<>();
           _list.add(value);
           hashStore.put(key, (T)_list);
           return 1;
       }else{
        LinkedList<T> _list = (LinkedList)hashStore.get(key);
           if(_list.getFirst().getClass().getName() != value.getClass().getName()){
               return -1;
           }
           _list.addLast(value);
           hashStore.put(key, (T)_list);
           return _list.size();
       }
    }

    /**
     * Creates a new list, if not present and associates with the specified key
     * and set time to live
     *
     * @param  key key whose mapping is to be removed from the map
     * @param  value value to be inserted in the list.
     * @param  miliSeconds time to live in miliseconds
     * @return  int - size of the list. {@code -1} if new value is of diffrent type
     *         than existing list type.
     */
    int listPushWithTTL(T key, T value, int miliSeconds){
        int _listPush = listPush(key, value);
        if (_listPush == -1){
            return -1;
        }
        setTTL(key, miliSeconds);
        return _listPush;
    }
    


}
