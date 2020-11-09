create table PURCHASES (
        id varchar(255) not null,
        PRODUCT_ID varchar(255) not null,
        STATUS varchar(255) not null,
        USER_ID int8 not null,
        primary key (id)
    );

    create table USERS (
        ID int8 not null,
        USER_NAME varchar(255) not null,
        primary key (ID)
    );

    alter table PURCHASES
        add constraint FK_PURCHASE_USER_ID
        foreign key (USER_ID)
        references USERS;