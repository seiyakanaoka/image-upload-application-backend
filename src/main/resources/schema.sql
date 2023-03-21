create database if not exists image_upload_application;

use image_upload_application;

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
  user_id bigint(20) not null comment 'ユーザーID',
  primary key (`id`)
);

create table if not exists aws_s3 (
  id bigint(20) unsigned not null auto_increment comment 'id',
  image_prefix varchar(255) comment '画像の接頭辞(画像取得時の判別に使用)',
  primary key (`id`)
);
