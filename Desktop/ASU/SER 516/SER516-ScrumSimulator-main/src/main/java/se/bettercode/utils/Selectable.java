package se.bettercode.utils;

import java.util.HashMap;

public class Selectable<T> {

    private HashMap<String, T> map = new HashMap<>();

    public T get(String key) {
        return map.get(key);
    }

    public void put(String key, T value) {
        map.put(key, value);
    }

    public String[] getKeys() {
        return map.keySet().toArray(new String[map.size()]);
    }
}
