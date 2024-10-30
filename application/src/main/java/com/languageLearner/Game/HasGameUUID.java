package com.languageLearner.game;

import java.util.UUID;

/**
 * Interface for classes that have their own UUID 
 * but also need a GameUUID to link them to a specific game.
 * This allows for easy association between data types and games
 * whilist working the same as the HasUUID interface.
 */
public interface HasGameUUID {
    UUID getGameUUID();
}
