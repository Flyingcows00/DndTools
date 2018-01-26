# DndTools

### Database Setup

There are three files needed to set up the database, all located under `src/main/resources/sql/database-scripts`
1. createDatabase.sql - Drops the database if it exists and re-creates it
2. createTables.sql - Drops all tables if they exist and re-creates them
3. initialMonsterDataDump.sql - Adds 325 monsters to the database

In order to run these scripts, you will need to add postgres to your path variable.  For me, the location was

```C:\Program Files\PostgreSQL\10\bin```

Once that is done, restart your command prompt and navigate to the sql file locations and run the following commands:

```
psql -U postgres -f createDatabase.sql
psql -d dndtools -U postgres -f createTables.sql
psql -d dndtools -U postgres -f initialMonsterDataDump.sql
```

After each line, you will be prompted for the password for user 'postgres'.  This is set on initial postgres installation.  
