insert into user values(10001, 'John', 'Doe');
insert into user values(10002, 'Jack', 'New');
insert into account values(11001, 'SGSavings', 'SAVINGS', 48.01,'AUD', 10001);
insert into transaction values(12001, 47.01, sysdate(),'food expense',0, 11001 , 10001 );
insert into transaction values(12002, 1, sysdate(),'food expense',1, 11001 , 10001 );