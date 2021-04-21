create table accounts (
   id serial primary key not null,
   login varchar(2000) not null,
   password varchar(2000) not null
);

create table sites (
   id serial primary key not null,
   name varchar(2000) not null,
   account_id integer not null references accounts(id)
);

create table shortcuts (
   id serial primary key not null,
   full_url varchar(2000) not null,
   short_url varchar(2000) not null,
   created_date date not null default CURRENT_DATE,
   called_times integer not null default 0,
   site_id integer not null references sites(id)
);
