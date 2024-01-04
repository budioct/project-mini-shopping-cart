show databases;

create database pt_sofco_graha;

use pt_sofco_graha;

show tables;

create table customers
(
    id                 int(11) not null auto_increment,
    name               varchar(100),
    address            varchar(250),
    create_at          timestamp,
    update_modified_at timestamp,
    primary key (id)
) engine = InnoDB;

create table keranjangs
(
    id                 int(11) not null auto_increment,
    keterangan         varchar(250),
    jumlah_pemesanan   int(7),
    create_at          timestamp,
    update_modified_at timestamp,
    primary key (id)
) engine = InnoDB;

create table items
(
    id                 int(11) not null auto_increment,
    item_name          varchar(100),
    category           varchar(100),
    create_at          timestamp,
    update_modified_at timestamp,
    primary key (id)
) engine = InnoDB;

create table keranjangs_like_items
(
    id           int(11) not null auto_increment,
    keranjang_id int(11) not null,
    item_id      int(11) not null,
    foreign key (keranjang_id) references keranjangs (id) on delete cascade on update cascade,
    foreign key (item_id) references items (id) on delete no action on update no action,
    primary key (id, keranjang_id, item_id)
) engine = InnoDB;

create table pesanans
(
    id                 int(11) not null auto_increment,
    no_register        varchar(100),
    keranjang_id       int(7)  not null,
    customer_id        int(7)  not null,
    create_at          timestamp,
    update_modified_at timestamp,
    foreign key (keranjang_id) references keranjangs (id),
    foreign key (customer_id) references customers (id),
    primary key (id)
) engine = InnoDB;

describe customers;
describe items;
describe keranjangs;
describe keranjangs_like_items;

select * from customers;
select * from items;
select * from keranjangs;
select * from keranjangs_like_items;
select * from pesanans;
