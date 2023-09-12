package model;

/**
 * Enum state to represent the state of the game.
 * DOOR_CLOSED : the game during which the player can move
 * DOOR_OPEN : the player have enough diamond ti pass the door
 * DOOR_PASSED : the player has passed the door with enough diamond and can go to the next level
 * GAME_OVER : the player got crushed by a rock or diamond or abandoned
 */
public enum State {
    DOOR_CLOSED, DOOR_OPEN, DOOR_PASSED, GAME_OVER;
}
