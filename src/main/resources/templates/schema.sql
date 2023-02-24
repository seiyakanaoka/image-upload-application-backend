create database if not exists project-2023-02;

use project-2023-02;

create table if not exists product (
  id bigint(20) unsigned not null auto_increment comment 'id',
  title varchar(30) not null comment 'タイトル',
  price bigint(20) not null comment '料金',
--  product_image mediumblob not null comment '商品画像',
  create_time datetime not null default current_timestamp comment '作成日時',
  update_time datetime not null default current_timestamp on update current_timestamp comment '更新日時',
  primary key (`id`),
  unique key title_unique (`title`)
) engine=innodb default charset=utf8mb4 comment='商品テーブル';

create table if not exists user (
  id int(11) not null auto_increment,
  email varchar(255) not null,
  password varchar(255) not null,
  role_type varchar(32) not null,
  primary key (`id`)
);
