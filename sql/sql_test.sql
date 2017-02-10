-- query test

-- insert
insert 
  into guestbook
values ( seq_guestbook.nextval, '티모', '티모','티모버섯~', sysdate );

commit;


-- delete

commit;

-- select
select no, name, content, to_char(reg_date, 'yyyy-mm-dd') from guestbook order by reg_date desc;