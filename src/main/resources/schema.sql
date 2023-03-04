create database if not exists project202302;

use project202302;

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
  id bigint(20) unsigned not null auto_increment comment 'id',
  email varchar(255) not null comment 'E-mail',
  password varchar(255) not null comment 'パスワード',
  primary key (`id`)
);

create table if not exists token (
  id bigint(20) unsigned not null auto_increment comment 'id',
  token varchar(255) comment 'トークン',
  primary key (`id`)
);
