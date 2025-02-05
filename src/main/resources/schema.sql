CREATE TABLE IF NOT EXISTS `book`(
  `isbn` varchar(255) NOT NULL COMMENT 'isbn',
  `book_title` varchar(255) NOT NULL COMMENT 'title',
  `book_author` varchar(255) NOT NULL COMMENT 'author',
  `book_publisher` varchar(255) NOT NULL COMMENT 'publisher',
  `book_year` int NOT NULL COMMENT 'year',
  `book_updated_time` datetime NOT NULL COMMENT 'updatedTime',
  `create_time` datetime NOT NULL COMMENT 'createTime',
  PRIMARY KEY (`isbn`)
);

CREATE TABLE IF NOT EXISTS `comment` (
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `user_id` int NOT NULL COMMENT 'User ID',
  `comment_content` text NOT NULL COMMENT 'Comment Content',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`isbn`,`user_id`),
  KEY `user_id` (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
);

CREATE TABLE IF NOT EXISTS `favorite`(
  `user_id` int NOT NULL COMMENT 'User ID',
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`user_id`,`isbn`),
  KEY `isbn` (`isbn`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `userlist`(
  `userlist_id` int NOT NULL AUTO_INCREMENT COMMENT 'userlistId',
  `user_id` int NOT NULL COMMENT 'userId',
  `userlist_name` varchar(255) NOT NULL COMMENT 'userlistName',
  `userlist_description` varchar(255) DEFAULT NULL COMMENT 'userlistDescription',
  `userlist_type` varchar(255) NOT NULL COMMENT 'userlistType',
  `userlist_create_time` datetime NOT NULL COMMENT 'userlistCreateTime',
  PRIMARY KEY (`userlist_id`),
  KEY `user_id` (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS `userlist_book`(
  `userlist_id` int NOT NULL COMMENT 'userListId',
  `isbn` varchar(255) NOT NULL COMMENT 'isbn',
  `create_time` datetime NOT NULL COMMENT 'createTime',
  PRIMARY KEY (`userlist_id`,`isbn`),
  KEY `isbn` (`isbn`),
  FOREIGN KEY (`userlist_id`) REFERENCES `userlist` (`userlist_id`) ON DELETE CASCADE,
  FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
);


CREATE TABLE IF NOT EXISTS `note`(
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `user_id` int NOT NULL COMMENT 'User ID',
  `note_content` text NOT NULL COMMENT 'Note Content',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`isbn`,`user_id`),
  KEY `user_id` (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `wishlist`(
  `user_id` int NOT NULL COMMENT 'User ID',
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`user_id`,`isbn`),
  KEY `isbn` (`isbn`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`) ON DELETE CASCADE
);

