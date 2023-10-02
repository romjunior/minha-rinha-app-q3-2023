SELECT "ID", "APELIDO", "NASCIMENTO", "NOME", "STACK"
FROM public."PESSOAS";

select count(*) from public."PESSOAS";

select * from "PESSOAS" p where p."APELIDO" = 'teste' or p."NOME" = 'teste' or p."STACK" && array ['Node'] limit  10;

select * from "PESSOAS" p where 'Node'=any(p."STACK");

select count(*) from PUBLIC."PESSOAS";

select * from PUBLIC."PESSOAS" LIMIT 10;

select * from PUBLIC."PESSOAS" p WHERE p."SEARCHTEXT" ILIKE '%javascript%';