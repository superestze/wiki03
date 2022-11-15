drop table if exists `test`;
create table `test`
(
    `id`       bigint not null comment 'id',
    `name`     varchar(50) comment '名称',
    `password` varchar(50) comment '密码',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='测试';

insert into `test` (id, name, password)
values (1, '测试', 'password');

# 分类
drop table if exists `category`;
create table `category`
(
    `id`     bigint      not null comment 'id',
    `parent` bigint      not null default 0 comment 'parent id',
    `name`   varchar(50) not null comment 'name',
    `sort`   int comment 'sort',
    primary key (`id`)
);

insert into `category` (id, parent, name, sort)
values (100, 000, '前端开发', 100);
insert into `category` (id, parent, name, sort)
values (101, 100, 'vue', 101);
insert into `category` (id, parent, name, sort)
values (102, 100, 'html', 102);
insert into `category` (id, parent, name, sort)
values (200, 000, 'java', 200);
insert into `category` (id, parent, name, sort)
values (201, 200, 'java基础应用', 201);
insert into `category` (id, parent, name, sort)
values (202, 200, 'java框架应用', 202);


# 文档表
drop table if exists `doc`;
create table `doc`
(
    `id`         bigint      not null comment 'id',
    `ebook_id`   bigint      not null default 0 comment 'ebook id',
    `parent`     bigint      not null default 0 comment '父id',
    `name`       varchar(50) not null comment '名称',
    `sort`       int comment '顺序',
    `view_count` int                  default 0 comment '阅读数',
    `vote_count` int                  default 0 comment '阅读数',
    primary key (`id`)
);

insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count)
values (1, 1, 0, '文档1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count)
values (2, 1, 1, '文档1.1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count)
values (3, 1, 0, '文档2', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count)
values (4, 1, 3, '文档2.1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count)
values (5, 1, 3, '文档2.2', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count)
values (6, 1, 5, '文档2.2.1', 1, 0, 0);

drop table if exists `content`;
create table `content`
(
    `id`      bigint     not null comment 'id',
    `content` mediumtext not null comment '内容',
    primary key (`id`)
);


drop table if exists `demo`;
create table `demo`
(
    `id`   bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='demo';

insert into `demo` (id, name)
values (1, '测试');

drop table if exists `ebook`;
create table ebook
(
    `id`           bigint not null comment 'id',
    `name`         varchar(50) comment 'name',
    `category1_id` bigint comment 'category1_id',
    `category2_id` bigint comment 'category2_id',
    `description`  varchar(200) comment 'description',
    `cover`        varchar(200) comment 'cover',
    `doc_count`    int comment 'doc_count',
    `view_count`   int comment 'view_count',
    `vote_count`   int comment 'vote_count',
    primary key (`id`)
);

insert into `ebook` (id, name, description)
values (1, 'sprintboot', '零基础java入门');
insert into `ebook` (id, name, description)
values (2, 'sprintboot', '零基础java入门');
insert into `ebook` (id, name, description)
values (3, 'sprintboot', '零基础java入门');
insert into `ebook` (id, name, description)
values (4, 'sprintboot', '零基础java入门');