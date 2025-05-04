package fr.diginamic.geoff.dto;


/**
 * Defines a method that allows DTO to return a field parameter considered as a unicity identifier
 */
public interface NaturalIdentifiable {
    String getNaturalId();
}