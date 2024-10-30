package com.languageLearner.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * A generic map that associates a UUID with a list of objects of a specified type.
 * Each UUID key is mapped to an ArrayList containing elements of type T.
 *
 * @param <T> the type of object to be stored in the map
 */
public class UUIDMap<T> {

    private final HashMap<UUID, ArrayList<T>> uuidMap;

    /**
     * Constructs a new, empty UUIDMap.
     */
    public UUIDMap() {
        this.uuidMap = new HashMap<>();
    }

    /**
     * Adds an object to the list associated with a specified UUID.
     * If no list exists for the UUID, a new list is created and the object is added to it.
     *
     * @param uuid the UUID to associate with the object
     * @param object the object to add
     */
    public void add(UUID uuid, T object) {
        uuidMap.computeIfAbsent(uuid, k -> new ArrayList<>()).add(object);
    }

    /**
     * Retrieves the list of objects associated with a specific UUID.
     *
     * @param uuid the UUID of the list to retrieve
     * @return the list of objects associated with the given UUID, or null if no such list exists
     */
    public ArrayList<T> getList(UUID uuid) {
        return uuidMap.getOrDefault(uuid, new ArrayList<>());
    }

    /**
     * Removes the list of objects associated with a specific UUID from the map.
     *
     * @param uuid the UUID of the list to remove
     * @return the removed list, or null if no list was associated with the given UUID
     */
    public ArrayList<T> remove(UUID uuid) {
        return uuidMap.remove(uuid);
    }

    /**
     * Checks if the map contains a list associated with the specified UUID.
     *
     * @param uuid the UUID to check for in the map
     * @return true if a list is associated with the given UUID, false otherwise
     */
    public boolean containsUUID(UUID uuid) {
        return uuidMap.containsKey(uuid);
    }

    /**
     * Checks if any list in the map contains the specified object.
     *
     * @param object the object to check for in the map's lists
     * @return true if any list contains the specified object, false otherwise
     */
    public boolean contains(T object) {
        // Iterate over each ArrayList<T> in the map and check if it contains the object
        for (ArrayList<T> list : uuidMap.values()) {
            if (list.contains(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a copy of the internal map, containing all UUID-ArrayList associations.
     *
     * @return a new HashMap containing all entries in this UUIDMap
     */
    public HashMap<UUID, ArrayList<T>> getAll() {
        return new HashMap<>(uuidMap);
    }
}
