DROP TABLE IF EXISTS contacts;
create table contacts(
  id int auto_increment,
  name varchar(25) not null,
  email varchar(25),
  phone varchar(30) not null,
  primary key(id)
);
ALTER TABLE contacts ADD CONSTRAINT unique_email UNIQUE(email);
