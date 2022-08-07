1) Во вкладке maven -> 'Database', добавить базу 'Postgres'.
2) Открыть консоль 'Query Console' 'Postgres', это меню БД над названием БД.
3) Записать в неё схему и стартануть её, чтобы создалась таблица:
create table persondb
(
    person_id serial not null constraint persondb_pkey primary key,
    name      varchar(100) default 'person_null'::character varying not null
);
4) Старт приложения и перейти по url: 'http://localhost:8080/'.
