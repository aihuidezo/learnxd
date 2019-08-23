create table t_comment
(
	id bigint not null  auto_increment,
	parent_id bigint not null comment '父类Id',
	type int not null comment '回复类型',
	commentator int not null comment '评论人Id',
	gmt_create bigint not null comment '创建时间',
	gmt_modified bigint not null comment '更新时间',
	like_count bigint default 0 null comment '点赞数',
	constraint t_comment_pk
		primary key (id)
);

