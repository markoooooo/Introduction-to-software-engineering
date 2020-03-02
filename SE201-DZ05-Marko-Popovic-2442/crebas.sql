/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     8/11/2018 10:51:54 PM                        */
/*==============================================================*/


drop table if exists KORISNIK;

drop table if exists MOZE_NE_MORE_DA_KUPI;

drop table if exists PROIZVOD;

drop table if exists RACUN;

/*==============================================================*/
/* Table: KORISNIK                                              */
/*==============================================================*/
create table KORISNIK
(
   KORISNIK_ID          int not null,
   USERNAME             char(45),
   PASSWORD             int,
   REPASSWORD           int,
   primary key (KORISNIK_ID)
);

/*==============================================================*/
/* Table: MOZE_NE_MORE_DA_KUPI                                  */
/*==============================================================*/
create table MOZE_NE_MORE_DA_KUPI
(
   KORISNIK_ID          int not null,
   PROIZVOD_ID          int not null,
   primary key (KORISNIK_ID, PROIZVOD_ID)
);

/*==============================================================*/
/* Table: PROIZVOD                                              */
/*==============================================================*/
create table PROIZVOD
(
   PROIZVOD_ID          int not null,
   RACUN_ID             int not null,
   CODE                 int,
   NAME                 char(256),
   PRICE                float(8,0),
   TIP_PROIZVODA        char(256),
   primary key (PROIZVOD_ID)
);

/*==============================================================*/
/* Table: RACUN                                                 */
/*==============================================================*/
create table RACUN
(
   RACUN_ID             int not null,
   KORISNIK_ID          int not null,
   USERNAME             char(45),
   BILL                 float(8,0),
   primary key (RACUN_ID)
);

alter table MOZE_NE_MORE_DA_KUPI add constraint FK_MOZE_NE_MORE_DA_KUPI foreign key (KORISNIK_ID)
      references KORISNIK (KORISNIK_ID) on delete restrict on update restrict;

alter table MOZE_NE_MORE_DA_KUPI add constraint FK_MOZE_NE_MORE_DA_KUPI2 foreign key (PROIZVOD_ID)
      references PROIZVOD (PROIZVOD_ID) on delete restrict on update restrict;

alter table PROIZVOD add constraint FK_NALAZI_SE foreign key (RACUN_ID)
      references RACUN (RACUN_ID) on delete restrict on update restrict;

alter table RACUN add constraint FK_UZIMA foreign key (KORISNIK_ID)
      references KORISNIK (KORISNIK_ID) on delete restrict on update restrict;

