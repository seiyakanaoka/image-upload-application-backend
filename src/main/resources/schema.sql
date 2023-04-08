create database if not exists image_upload_application;

use image_upload_application;

create table if not exists user (
  id bigint(20) unsigned not null auto_increment comment 'id',
  email varchar(255) not null comment 'E-mail',
  password_hash varchar(255) not null comment 'パスワードのハッシュ値',
  password_salt varchar(255) not null comment 'パスワードのソルト',
  primary key (`id`)
);

create table if not exists token (
  id bigint(20) unsigned not null auto_increment comment 'id',
  token varchar(255) comment 'トークン',
  user_id bigint(20) not null comment 'ユーザーID',
  primary key (`id`)
);