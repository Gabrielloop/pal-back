
INSERT INTO `user` VALUES   (1,'admin','gabriel.henin@gmail.com','password_lol','ADMIN','2025-01-16 16:00:00','2025-01-16 16:00:00'),
                            (2,'Gabriel','gabriel.henin@gmail.com','password_lol','USER','2025-01-16 16:00:00','2025-01-16 16:00:00'),
                            (3,'Laura','gabriel.henin@gmail.com','password_lol','USER','2025-01-16 16:00:00','2025-01-16 16:00:00');

INSERT INTO book VALUES     ('2070408507','Le Petit Prince','Antoine de Saint-Exupéry','Gallimard',1947,'2025-01-16 16:00:00','2025-01-16 16:00:00');

INSERT INTO userlist VALUES     (1,1,'Liste','Liste par defaut','list','2025-01-16 16:00:00'),
                                (2,2,'Liste','Liste par defaut','list','2025-01-16 16:00:00'),
                                (3,3,'Liste','Liste par defaut','list','2025-01-16 16:00:00');

INSERT INTO `userlist_book` VALUES  (1,'2070408507','2025-01-16 16:00:00'),
                                    (2,'2070408507','2025-01-16 16:00:00'),
                                    (3,'2070408507','2025-01-16 16:00:00');