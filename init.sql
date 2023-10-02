CREATE EXTENSION pg_trgm;

ALTER SCHEMA PUBLIC OWNER TO rinha;

DROP TABLE IF EXISTS PUBLIC."PESSOAS";

CREATE TABLE PUBLIC."PESSOAS" (
    "ID" uuid not null,
    "APELIDO" varchar(32) unique not null,
    "NASCIMENTO" date not null,
    "NOME" varchar(100) not null,
    "STACK" text[],
    "SEARCHTEXT"  text,
    primary key ("ID")
);

CREATE INDEX IF NOT EXISTS IDX_PESSOAS_SEARCHTEXT_TGRM ON PUBLIC."PESSOAS" USING GIST ("SEARCHTEXT" GIST_TRGM_OPS(SIGLEN=64));