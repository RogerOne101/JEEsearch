begin;
drop table if exists profiles;
create table profiles (
  id     bigserial          not null,
  login  varchar(32) unique not null,
  passwd varchar(32)        not null,

  primary key(id)
);

insert into profiles(login, passwd) values ('john' , '12345' );
insert into profiles(login, passwd) values ('admin', 'root'  );
insert into profiles(login, passwd) values ('anna' , 'qwerty');

commit;
