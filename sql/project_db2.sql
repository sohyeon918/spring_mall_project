create table member_role (
	role_seq number(10) primary key,
	id varchar2(20) not null,
	role varchar2(20) not null
);

create sequence role_seq start with 1;