package it.uniroma1.textadv.exceptions;

public class RoomWithoutDescriptionException extends Exception{
    public RoomWithoutDescriptionException() {
        super("Every room must have a name and a description");
    }
}
