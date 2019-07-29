CREATE TABLE GITHUB_PROJECT(
    id IDENTITY  NOT NULL PRIMARY KEY,
    org_name VARCHAR(50) NOT NULL,
    repo_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE GENRE(
    id IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(250)
);

CREATE TABLE READING_ENTRY(
     id IDENTITY  NOT NULL PRIMARY KEY,
     title VARCHAR(100) NOT NULL UNIQUE,
     genre_id INTEGER NOT NULL,
     type VARCHAR(20),
     medium VARCHAR(20),
     language VARCHAR(50),
     publisher VARCHAR(100),
     edition VARCHAR(15)
);

CREATE TABLE READING_RECORD(
    id IDENTITY NOT NULL PRIMARY KEY,
    reading_entry_id INTEGER NOT NULL,
    status VARCHAR(25),
    percentage_complete DOUBLE,
    start_date DATE,
    end_date DATE,
    completion_time DOUBLE,
    notes VARCHAR(500)
);

CREATE TABLE AUTHOR(
    id IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    bio VARCHAR(250),
    website VARCHAR(100),
    notes VARCHAR(250)
);

CREATE TABLE AUTHORSHIP(
    id IDENTITY NOT NULL PRIMARY KEY,
    reading_entry_id INTEGER NOT NULL,
    author_id INTEGER NOT NULL
);

CREATE INDEX idx_title
    on READING_ENTRY (title);

CREATE INDEX idx_author
    on AUTHOR(name);

CREATE INDEX idx_repo_name
    ON GITHUB_PROJECT (repo_name);
