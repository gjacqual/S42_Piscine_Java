package edu.school21.chat.repositories;

public class NotSavedSubEntityException extends RuntimeException {
    public String toString() {
        return ("Error: ID is not exist or these IDs are null");
    }
}
