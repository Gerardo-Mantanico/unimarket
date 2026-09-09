create table products (
    id bigserial primary key,
    name varchar(120) not null,
    description varchar(500) not null,
    price numeric(12,2) not null,
    stock integer not null,
    category varchar(60) not null
);

CREATE TABLE categories (
    id bigserial primary key,
    name varchar(60) not null,
    active boolean not null default true,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

INSERT INTO categories (name) VALUES
('Útiles escolares'),
('Dibujo técnico'),
('Electrónica'),
('Libros'),
('Accesorios');

