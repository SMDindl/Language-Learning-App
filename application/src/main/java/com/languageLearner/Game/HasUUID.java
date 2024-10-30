package com.languageLearner.game;

import java.util.UUID;

/**
 * Interface for classes that contain a UUID.
 * Implementing this interface makes it easy to access UUIDs
 * by allowing a simple "instanceof" check for HasUUID.
 */
public interface HasUUID {
    UUID getUUID();
}
