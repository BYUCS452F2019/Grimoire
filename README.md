# Grimoire

## Project Description

Tabletop RPG spellbook for D&amp;D

The hope is that this will be a generic app that could be used in any tabletop rpg but for now we will be focusing on D&D 5th edition.

## Team

Looking for 2-3 people to help out with data entry and project development

## SQL

We will most likely use MySql or Postgres

## NoSQL

We are currently leaning toward using Firebase's Firestore as it supports both a key-value and document approach

## Project Architecture

- Frontend in Angular/Ionic for native app capabitilies
- Backend will either be in nodejs (using [nestjs](https://nestjs.com)) or a serverless offering such as Google Cloud Functions via Firebase

## Business Plan

Things I think will be useful:

- A way to crowdsource the input of the different spells.
- Filter/Search options to navigate what spells are available to you
- Designation of what books the spells come from
- A place to create and store home-brew spells
- Your own personal spellbook. A place to store what spells your character has and has prepared.

## Legal Details

Legally if we wanted to make this publically available we would need to talk with the actual publishers of the books to get the rights to do this. We might need to have users pay to have access to certain books’ spells so we can pay the owners (and maybe ourselves…).

For personal use though we should be good since those on the team have already purchased D&D 5e Player's Handbook, which has the list of spells we will be using.

# Running Grimoire Locally

Run the following from the project root to get grimoire up and running:

```
docker run -d --rm --name mariadb -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_DATABASE=grimoire -e MYSQL_USER=grimoire -e MYSQL_PASSWORD=grimoire1234 mariadb
docker run -ti --rm --name grimoire -v $(pwd):/usr/src/grimoire -w /usr/src/grimoire -p 8080:8080 --link mariadb:mariadb registry.gitlab.com/byuhbll/apps/gitlab-ci-maven mvn package spring-boot:run
```

To skip building and packaging the frontend, add `-Dfrontend.install.phase=none -Dfrontend.package.phase=none` to the end of the second command.

## Using Docker Compose to run locally

We can use Docker to run all the required pieces without needing to download them individually.

By running `docker-compose up` in the root directory Docker will spin up the server, a database, a web based DBMS, and the web code running in development mode with watching enabled.

In order for the frontend code to work you'll need to run `npm install` in the `src/main/angular` folder.

The frontend code is proxying all requests starting with `/api` to the local backend server.

### Using the DBMS

Navigate to `localhost:8081` and log into the database with the following credentials

- System: `MySQL`
- Server: `db`
- Username: `grimoire`
- Password: `grimoire1234`
- database: `grimoire`
