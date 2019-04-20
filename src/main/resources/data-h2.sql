insert into TYPE_BIERE (nom) values ('Blonde'), ('Ambrée');

insert into FABRICANT (nom) values ('BFM'), ('Cardinal');

insert into STOCK_ARTICLE (description,stock) values ('Stock BFM Salamandres',20);
insert into STOCK_ARTICLE (description,stock) values ('Stock BFM Saint-Bon Chien',100);

insert into ARTICLE (actif,description,numero,prix,fabricant_id,stock_id) values (1,'Biere: BFM, La Salamandre','aaaaaaaa-f0e8-4386-9628-cccccccccccc',6.90,1,1);
insert into BIERE (contenance_l,nom,article_id,type_id) values (0.5,'La Salamandre',1,2);

insert into ARTICLE (actif,description,numero,prix,fabricant_id,stock_id) values (1,'Biere: BFM, Saint-Bon Chien','aaaaaaaa-f0e8-4386-9628-zzzzzzzzzzzz',8.90,1,2);
insert into BIERE (contenance_l,nom,article_id,type_id) values (0.5,'Saint-Bon Chien',2,2);


