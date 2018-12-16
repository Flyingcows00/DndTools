# DndTools

### Database Setup

There are two files needed to set up the database, all located under `src/main/resources/sql/`
1. createDatabase.sql - Drops the database if it exists and re-creates it with fresh tables
2. dataDump.sql - Initial dump of data for monsters and spells

In order to run these scripts, you will need to install mysql.  Make sure to set the root user's password to 'root'.  (Yeah, i know... super secure)

I use a tool called DBeaver to access and run scripts against the database.  You can download it [here](https://dbeaver.io/).  In here, open a connection to your local mysql database, paste the sql scripts in, and run them.  


### Rest Urls

After the database is set up, run Application.java as a Spring Boot app.  You can now query the database using a tool such as [Postman](https://www.getpostman.com/).  The following urls are currently available:

Operation | URL                                                     | Description
--------- | ------------------------------------------------------- | -----------
GET       | http://localhost:8080/dndtools/monsters                 | Returns a list of all of the available monsters in the database.
GET       | http://localhost:8080/dndtools/monsters/[monster_name]  | Returns all of the information about a specific monster.
GET       | http://localhost:8080/dndtools/spells                   | Returns a list of all of the available spells in the database.
GET       | http://localhost:8080/dndtools/spells/[spell_id]        | Returns all of the information about a specific spell.
GET       | http://localhost:8080/dndtools/users                    | Returns a list of all of the usernames as well as if they are an admin or not.
GET       | http://localhost:8080/dndtools/users/[username]         | returns a single username and if they are an admin or not.
