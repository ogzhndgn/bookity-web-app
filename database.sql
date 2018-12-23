create table book
(
  id       int auto_increment
    primary key,
  title    varchar(512) null,
  author   varchar(512) null,
  category varchar(256) null,
  isbn     varchar(16)  null
);

INSERT INTO book (id, title, author, category, isbn) VALUES (1, 'Afrikalı Leo', 'Amin Maalouf', 'Roman', '1231231231');
INSERT INTO book (id, title, author, category, isbn) VALUES (2, 'Semerkand', 'Amin Maalouf', 'Roman', '1231231232');
INSERT INTO book (id, title, author, category, isbn) VALUES (3, 'Doğunun Limanları', 'Amin Maalouf', 'Roman', '1231231233');
INSERT INTO book (id, title, author, category, isbn) VALUES (4, 'Yüzüncü Ad', 'Amin Maalouf', 'Roman', '1231231234');
INSERT INTO book (id, title, author, category, isbn) VALUES (5, 'Marslı', 'Andy Weir', 'Bilim Kurgu', '1231231235');
INSERT INTO book (id, title, author, category, isbn) VALUES (6, 'Artemis', 'Andy Weir', 'Bilim Kurgu', '1231231236');
INSERT INTO book (id, title, author, category, isbn) VALUES (7, 'Hayvan Çiftliği', 'George Orwell', 'Roman', '1231231237');
INSERT INTO book (id, title, author, category, isbn) VALUES (8, 'Clean Code', 'Robert C. Martin', 'Teknik', '1231231238');
INSERT INTO book (id, title, author, category, isbn) VALUES (9, 'Buraları Rüzgar Buraları Yağmur', 'Selçuk Altun', 'Roman', '1231231239');
INSERT INTO book (id, title, author, category, isbn) VALUES (10, 'Sol Omzuna Güneşi Asmadan Gelme', 'Selçuk Altun', 'Roman', '1231231240');
INSERT INTO book (id, title, author, category, isbn) VALUES (11, 'Yalnızlık Gittiğin Yoldan Gelir', 'Selçuk Altun', 'Roman', '1231231241');
INSERT INTO book (id, title, author, category, isbn) VALUES (12, 'Bir Tek Sen Yakınsın Uzakta Kalınca', 'Selçuk Altun', 'Roman', '1231231242');
INSERT INTO book (id, title, author, category, isbn) VALUES (13, 'Ardıç Ağacının Altında', 'Selçuk Altun', 'Roman', '1231231243');
INSERT INTO book (id, title, author, category, isbn) VALUES (14, 'Annemin Öğretmediği Şarkılar', 'Selçuk Altun', 'Roman', '1231231244');
INSERT INTO book (id, title, author, category, isbn) VALUES (15, 'Amin Maalouf''ın Hayatı', 'Oguzhan Dogan', 'Roman', '1231231245');
INSERT INTO book (id, title, author, category, isbn) VALUES (16, 'Rimalar', 'Gustavo Adolfe Becquer', 'Şiir', '1231231246');
INSERT INTO book (id, title, author, category, isbn) VALUES (17, 'Selçuk Altun ile Şiir Üzerine', 'Demir Şenlik', 'Deneme', '1231231246');
INSERT INTO book (id, title, author, category, isbn) VALUES (18, 'Selçuk Altun ile Şiir Üzerine', 'Demir Şenlik', 'Deneme', '1231231246');

create table user
(
  id           int auto_increment
    primary key,
  mailaddress  varchar(256) null,
  passwordhash varchar(512) null,
  passwordsalt varchar(64)  null
);