use bikepark;

drop table if exists taste_item_similarity;
drop table if exists taste_preferences;

create table taste_preferences (
                                 user_id      bigint not null,
                                 item_id      bigint not null,
                                 preference   integer not null,
                                 timestamp    timestamp not null default current_timestamp,

                                 foreign key (user_id) references biker (biker_id) on delete cascade,
                                 foreign key (item_id) references bikepark (bikepark_id) on delete cascade
);

create table taste_item_similarity (
                                     item_id_a    bigint not null,
                                     item_id_b    bigint not null,
                                     similarity   double not null,

                                     foreign key (item_id_a) references items (item_id) on delete cascade,
                                     foreign key (item_id_b) references items (item_id) on delete cascade
);
