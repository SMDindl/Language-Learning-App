package com.languageLearner.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * A generic map that associates a UUID with an object of a specified type.
 * Object can be anysort of data type.
 *
 * @param <T> the type of object to be stored in the map
 */
public class UUIDMap<T> {

    private final HashMap<UUID, T> uuidMap;

    /**
     * Constructs a new, empty UUIDMap.
     */
    public UUIDMap() {
        this.uuidMap = new HashMap<>();
    }

    /**
     * Adds an object to the map with a specified UUID as the key,
     * only if the UUID already exists in the map.
     *
     * @param uuid the UUID to associate with the object
     * @param object the object to add
     */
    @SuppressWarnings("unchecked")
    public void add(UUID uuid, T object) {
        if (!(object instanceof ArrayList) && uuidMap.get(uuid) instanceof ArrayList) { // for handing the need to place obj into array
            ((ArrayList<T>) uuidMap.get(uuid)).add((T) object);
        } else if (uuidMap.containsKey(uuid)) {
            uuidMap.put(uuid, object);
        }
    }

    /**
     * Retrieves the object associated with a specific UUID.
     *
     * @param uuid the UUID of the object to retrieve
     * @return the object associated with the given UUID, or null if no such object exists
     */
    public T get(UUID uuid) {
        return uuidMap.get(uuid);
    }

    /**
     * Removes the object associated with a specific UUID from the map.
     *
     * @param uuid the UUID of the object to remove
     * @return the removed object, or null if no object was associated with the given UUID
     */
    public T remove(UUID uuid) {
        return uuidMap.remove(uuid);
    }

    /**
     * Checks if the map contains an object associated with the specified UUID.
     *
     * @param uuid the UUID to check for in the map
     * @return true if an object is associated with the given UUID, false otherwise
     */
    public boolean containsUUID(UUID uuid) {
        return uuidMap.containsKey(uuid);
    }

    /**
     * Returns an ArrayList associated with the specified UUID,
     * if it exists and is an ArrayList. Otherwise, returns null.
     *
     * @param uuid the UUID to look up
     * @return the ArrayList associated with the UUID, or null if not found or not an ArrayList
     */
    public ArrayList<?> getAllPerUUID(UUID uuid) {
        T object = uuidMap.get(uuid);
        if (object instanceof ArrayList) {
            return (ArrayList<?>) object;
        }
        System.out.println("Returned null"); // debug statement
        return null; // or could return new ArrayList<>() if we want an empty list
    }

    /**
     * Returns a copy of the internal map, containing all UUID-object associations.
     *
     * @return a new HashMap containing all entries in this UUIDMap
     */
    public HashMap<UUID, T> getAll() {
        return new HashMap<>(uuidMap);
    }

    // /**
    //  * Adds an object to the map with a randomly generated UUID as the key.
    //  *
    //  * @param object the object to add
    //  * @return the UUID generated and associated with the added object
    //  */
    // public UUID add(T object) {
    //     UUID uuid = UUID.randomUUID();
    //     uuidMap.put(uuid, object);
    //     return uuid;
    // }

}