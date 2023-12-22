create database canteen_database;

use canteen_database;

-- user
-- 1:student/teacher 2:canteen_admin 3:system_admin
create table user
(
    user_id     bigint unsigned auto_increment primary key comment '用户id',
    username    varchar(20) not null comment '用户名',
    nickname    varchar(20)          default null comment '昵称',
    password    varchar(32) not null comment '加密后的密码',
    email       varchar(50)          default null comment '邮箱',
    phone       varchar(11)          default null comment '手机号',
    avatar      varchar(255)         default null comment '头像',
    type        tinyint(1)  not null comment '用户类型 1:学生/教师 2:食堂管理员 3:系统管理员',
    level       tinyint(1)           default 0 comment '用户等级',
    exp         int                  default 0 comment '经验值',
    create_time timestamp   not null default current_timestamp comment '创建时间',
    update_time timestamp   not null default current_timestamp on update current_timestamp comment '更新时间'
);

-- canteen
create table canteen
(
    canteen_id  bigint unsigned auto_increment primary key comment '食堂id',
    user_id     bigint unsigned not null comment '食堂管理员id',
    name        varchar(20)     not null comment '食堂名称',
    address     varchar(255)             default null comment '食堂地址',
    phone       varchar(11)              default null comment '食堂电话',
    avatar      varchar(255)             default null comment '食堂头像',
    create_time timestamp       not null default current_timestamp comment '创建时间',
    update_time timestamp       not null default current_timestamp on update current_timestamp comment '更新时间',
    constraint fk_canteen_user foreign key (user_id) references user (user_id)
);

-- dish
create table dish
(
    dish_id       bigint unsigned auto_increment primary key comment '菜品id',
    canteen_id    bigint unsigned not null comment '所属食堂id',
    name          varchar(20)     not null comment '名称',
    style         varchar(20)     not null comment '菜系',
    price         decimal(8, 2)   not null comment '价格',
    special_price decimal(8, 2)   not null comment '特价',
    description   varchar(255)             default null comment '描述',
    image         varchar(255)             default null comment '图片',
    rate          double          not null comment '评分',
    rating_people int             not null comment '评分人数',
    create_time   timestamp       not null default current_timestamp comment '创建时间',
    update_time   timestamp       not null default current_timestamp on update current_timestamp comment '更新时间',
    constraint fk_dish_canteen foreign key (canteen_id) references canteen (canteen_id)
);

-- comment
create table comment
(
    comment_id  bigint unsigned auto_increment primary key comment '评论id',
    user_id     bigint unsigned not null comment '评论者id',
    dish_id     bigint unsigned not null comment '评论的菜品id',
    score       tinyint(1)      not null comment '评分 1-5',
    content     varchar(255)    not null comment '评论内容',
    state       tinyint(1)      not null comment '评论状态 0:删除 1:正常',
    create_time timestamp       not null default current_timestamp comment '创建时间',
    update_time timestamp       not null default current_timestamp on update current_timestamp comment '更新时间',
    constraint fk_comment_user foreign key (user_id) references user (user_id),
    constraint fk_comment_dish foreign key (dish_id) references dish (dish_id)
);

-- comment_img
create table comment_img
(
    comment_img_id bigint unsigned auto_increment primary key comment '评论图片id',
    comment_id     bigint unsigned not null comment '评论id',
    image          varchar(255)    not null comment '图片url地址',
    create_time    timestamp       not null default current_timestamp comment '创建时间',
    update_time    timestamp       not null default current_timestamp on update current_timestamp comment '更新时间',
    constraint fk_ci_comment foreign key (comment_id) references comment (comment_id)
);

-- canteen_admin
create table canteen_admin
(
    canteen_admin_id bigint unsigned auto_increment primary key comment '食堂管理员id',
    canteen_id       bigint unsigned not null comment '所属食堂id',
    user_id          bigint unsigned not null comment '食堂管理员id',
    create_time      timestamp       not null default current_timestamp comment '创建时间',
    update_time      timestamp       not null default current_timestamp on update current_timestamp comment '更新时间',
    constraint fk_ca_canteen foreign key (canteen_id) references canteen (canteen_id),
    constraint fk_ca_user foreign key (user_id) references user (user_id)
);