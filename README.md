# Proiectul Shop Management

Acest proiect este o aplicație de management pentru un magazin, construită utilizând **Spring Boot** și **Log4j2** pentru logare. Proiectul oferă funcționalități pentru gestionarea produselor și a prețurilor într-o maniera care stochează istoricul acestora, autentificare utilizator și roluri de securitate.

## Funcționalități
- Adăugare, ștergere și preluare produse.
- Modificarea prețurilor produselor.
- Autentificare și autorizare utilizatori cu roluri distincte (USER și ADMIN).
- Logare în consolă și fișiere.

## Configurare
1. Clonați proiectul:
    ```
    git clone https://github.com/nume-utilizator/shop-management.git
    ```
2. Navigați în directorul proiectului:
    ```
    cd shop-management
    ```
3. Adăugați configurația pentru baza de date în fișierul `application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/shop
    spring.datasource.username=user
    spring.datasource.password=parola
    ```

## Tehnologii utilizate
- **Java 17**
- **Spring Boot**
- **Hibernate / JPA**
- **PostgreSQL**
- **Log4j2**
- **Maven**

## Autori
Acest proiect este dezvoltat de Diaconu Andrei-Florin


