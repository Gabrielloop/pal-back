CREATE TABLE IF NOT EXISTS `book`(
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `book_title` varchar(255) NOT NULL COMMENT 'Book Title',
  `book_author` varchar(255) NOT NULL COMMENT 'Book Author',
  `book_publisher` varchar(255) NOT NULL COMMENT 'Book Publisher',
  `book_year` int NOT NULL COMMENT 'Book Year',
  `book_updated_time` datetime NOT NULL COMMENT 'Book Updated Time',
  `book_create_time` datetime NOT NULL COMMENT 'Book Create Time',
  PRIMARY KEY (`isbn`)
);

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `user_name` varchar(255) NOT NULL COMMENT 'User Name',
  `user_mail` varchar(255) NOT NULL COMMENT 'User Mail',
  `user_password` varchar(255) NOT NULL COMMENT 'User Password',
  `user_last_login` datetime NOT NULL COMMENT 'Last Login Time',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`user_id`)
);

CREATE TABLE IF NOT EXISTS `comment` (
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `user_id` int NOT NULL COMMENT 'User ID',
  `comment_content` text NOT NULL COMMENT 'Comment Content',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`isbn`,`user_id`),
  KEY `user_id` (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
);

CREATE TABLE IF NOT EXISTS `favorite`(
  `user_id` int NOT NULL COMMENT 'User ID',
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`user_id`,`isbn`),
  KEY `isbn` (`isbn`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
);

CREATE TABLE IF NOT EXISTS `userlist`(
  `userlist_id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `user_id` int NOT NULL COMMENT 'User ID',
  `userlist_name` varchar(255) NOT NULL COMMENT 'List Name',
  `userlist_description` varchar(255) DEFAULT NULL COMMENT 'List Description',
  `userlist_type` varchar(255) NOT NULL COMMENT 'List Type',
  `userlist_create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`userlist_id`),
  KEY `user_id` (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);


CREATE TABLE IF NOT EXISTS `userlist_book`(
  `userlist_id` int NOT NULL COMMENT 'List ID',
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`userlist_id`,`isbn`),
  KEY `isbn` (`isbn`),
  FOREIGN KEY (`userlist_id`) REFERENCES `userlist` (`userlist_id`),
  FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
);


CREATE TABLE IF NOT EXISTS `note`(
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `user_id` int NOT NULL COMMENT 'User ID',
  `note_content` text NOT NULL COMMENT 'Note Content',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`isbn`,`user_id`),
  KEY `user_id` (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
);

CREATE TABLE IF NOT EXISTS `wishlist`(
  `user_id` int NOT NULL COMMENT 'User ID',
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`user_id`,`isbn`),
  KEY `isbn` (`isbn`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
);

