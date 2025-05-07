package fr.diginamic.geoff.exception;

public class DuplicatePaysException extends DuplicateEntityException{
    public DuplicatePaysException(String message) {
        super(message);
    }
}