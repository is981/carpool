create user carpool with password '0ad6526861080801291b38afe04ac1238dd45cd8a239c4cc380cbed8b8224831fed2f816b30531e31b9209ce964887d1ee9f6390d50d43acd023bd61368cf37a';

create table public.user (
    id serial primary key not null,
    username varchar not null unique,
    email varchar not null,
    name varchar not null,
    salt varchar not null,
    password varchar not null,
    active boolean not null
);

create table public.domain (
    id serial primary key not null
);


create table public.group (
    id serial primary key not null,
    name varchar not null
);

--Jpa Identity Store Tables

create table public.identity_object_role_type (
    id serial primary key not null,
    varchar name not null
);

create table public.identity_object_type (
    id serial primary key not null,
    varchar name not null
);

create table public.identity_object (
    id serial primary key not null,
    name varchar not null,
    identity_object_type int not null references public.identity_object_type(id)
);

create table public.identity_object_attribute (
    id serial primary key not null,
    identity_object int not null references public.identity_object(id),
    name varchar not null,
    value varchar not null
);

create table public.identity_object_relationship_type (
    id serial primary key not null,
    name varchar not null
);

create table public.identity_object_relationship (
    id serial primary key not null,
    name varchar not null,
    identity_object_relationship_type int not null references public.identity_object_relationship_type(id),
    identity_object_to int not null references public.identity_object(id),
    identity_object_from int not null references public.identity_object(id)
);

create table identity_object_credential_type {
    id serial primary key not null,
    name varchar not null
};

create table identity_object_credential {
    id serial primary key not null,
    identity_object int not null references public.identity_object(id),
    identity_object_credential_type int not null references public.identity_object_credential_type(id),
    value varchar not null
};
