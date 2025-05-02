# Project JPA: IMDB Data Importer

## Overview

This application reads raw IMDB-like data from JSON files and persists it into a relational database using Java, JPA, and Hibernate.  
It is designed for efficient bulk data import and demonstrates best practices in database modeling, JPA entity mapping, and data processing.

---

## Features

- **Bulk Import:** Efficiently loads large datasets into the database.
- **JPA/Hibernate:** Uses modern Java persistence best practices.
- **Normalized Data Model:** Follows a well-structured, normalized schema.
- **Extensible:** Easily adaptable for additional data sources or schema changes.
- **Clear Separation:** DTOs, entities, and mappers are cleanly separated.

---

## Data Model

The application is based on a normalized data model inspired by IMDB, supporting films, people (actors, directors), genres, languages, and more.

### Conceptual Model (MCD)

![MCD](./conception/Projet_JPA_Film-MCD.jpg)

### Logical Model (MLD)

![MLD](./conception/Projet_JPA_Film-MLD.jpg)

### Physical Model (MPD)

![MPD](./conception/Projet_JPA_Film-MPD.jpg)

---

## Technologies Used

- **Java 17+**
- **JPA (Hibernate)**
- **Jackson (for JSON parsing)**
- **MySQL** 
- **Maven** 
- **LogBack (for log generation)**
- **Jupiter (unit testing)**

---

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven or Gradle
- A supported RDBMS (H2, MySQL, PostgreSQL, etc.)

### Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/project-jpa-imdb.git
   cd project-jpa-imdb
   ```

2. **Configure the database:**
    - Edit `src/main/resources/application.properties` (or `persistence.xml`) to set your database connection.

3. **Place your data files:**
    - Put your JSON data files in `src/main/resources/`.

4. **Build the project:**
   ```bash
   mvn clean install
   ```

5. **Run the application:**
   ```bash
   mvn exec:java -Dexec.mainClass="fr.diginamic.geoff.Main"
   ```

---

## Usage

- The application will read the JSON files, map them to DTOs, convert them to entities, and persist them in the database.
- Progress and errors will be logged to log files.

---

## Project Structure
```
main
|-- java
|   `-- fr
|       `-- diginamic
|           `-- geoff
|               |-- Main.java
|               |-- dao
|               |   |-- ActeurDAO.java
|               |   |-- FilmDAO.java
|               |   |-- GenreDAO.java
|               |   |-- LieuDAO.java
|               |   |-- PaysDAO.java
|               |   |-- PersonneDAO.java
|               |   |-- RealisateurDAO.java
|               |   `-- RoleDAO.java
|               |-- deserializer
|               |   |-- FloatDeserializer.java
|               |   |-- LocalDateDeserializer.java
|               |   `-- YearDeserializer.java
|               |-- dto
|               |   |-- ActeurDTO.java
|               |   |-- FilmDTO.java
|               |   |-- LieuTournageDTO.java
|               |   |-- NaissanceDTO.java
|               |   |-- PaysDto.java
|               |   |-- PersonneDTO.java
|               |   |-- RoleDTO.java
|               |   `-- parser
|               |       `-- JsonParser.java
|               |-- entity
|               |   |-- Acteur.java
|               |   |-- Film.java
|               |   |-- Genre.java
|               |   |-- Lieu.java
|               |   |-- Pays.java
|               |   |-- Personne.java
|               |   |-- Realisateur.java
|               |   `-- Role.java
|               `-- mapper
|                   |-- ActeurMapper.java
|                   |-- EntityMapper.java
|                   |-- FilmMapper.java
|                   |-- GenreMapper.java
|                   |-- LieuMapper.java
|                   |-- PersonneMapper.java
|                   |-- RealisateurMapper.java
|                   `-- RoleMapper.java
`-- resources
    |-- META-INF
    |   `-- persistence.xml
|-- acteurs.csv
|-- castingPrincipal.csv
|-- film_realisateurs.csv
|-- films.csv
|-- films.json
|-- pays.csv
|-- realisateurs.csv
`-- roles.csv
``` 