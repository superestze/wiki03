drop table if exists `test`;
create table `test`
(
    `id`       bigint not null comment 'id',
    `name`     varchar(50) comment '名称',
    `password` varchar(50) comment '密码',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='测试';

insert into `test` (id, name, password) values (1,'测试','password');

# 分类
drop table if exists `category`;
create table `category`(
    `id` bigint not null comment 'id',
    `parent` bigint not null default 0 comment 'parent id',
    `name` varchar(50) not null comment 'name',
    `sort` int comment 'sort',
    primary key (`id`)
);

insert into `category` (id, parent, name, sort) values (100, 000, '前端开发', 100);
insert into `category` (id, parent, name, sort) values (101, 100, 'vue', 101);
insert into `category` (id, parent, name, sort) values (102, 100, 'html', 102);
insert into `category` (id, parent, name, sort) values (200, 000, 'java', 200);
insert into `category` (id, parent, name, sort) values (201, 200, 'java基础应用', 201);
insert into `category` (id, parent, name, sort) values (202, 200, 'java框架应用', 202);


drop table if exists `demo`;
create table `demo`
(
    `id`       bigint not null comment 'id',
    `name`     varchar(50) comment '名称',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='demo';

insert into `demo` (id, name) values (1,'测试');

drop table if exists `ebook`;
create table ebook(
    `id` bigint not null comment 'id',
    `name` varchar(50) comment 'name',
    `category1_id` bigint comment 'category1_id',
    `category2_id` bigint comment 'category2_id',
    `description` varchar(200) comment 'description',
    `cover` varchar(200) comment 'cover',
    `doc_count` int comment 'doc_count',
    `view_count` int comment 'view_count',
    `vote_count` int comment 'vote_count',
    primary key (`id`)
);

insert into `ebook` (id, name, description) values (1, 'sprintboot', '零基础java入门');
insert into `ebook` (id, name, description) values (2, 'sprintboot', '零基础java入门');
insert into `ebook` (id, name, description) values (3, 'sprintboot', '零基础java入门');
insert into `ebook` (id, name, description) values (4, 'sprintboot', '零基础java入门');