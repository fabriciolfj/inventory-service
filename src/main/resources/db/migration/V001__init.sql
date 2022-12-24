create table inventory (
id BIGSERIAL        PRIMARY KEY NOT NULL,
code                varchar(100) not null,
product             varchar(100) not null,
entrance            int not null,
exit                int not null,
balance             int not null,
date_registration   timestamp not null,
unique(code)
);