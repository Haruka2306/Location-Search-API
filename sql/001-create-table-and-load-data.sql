DROP TABLE IF EXISTS subscribers;

CREATE TABLE subscribers (
  id int unsigned AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  dateOfBirth VARCHAR(10) NOT NULL,
  address VARCHAR(50) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO subscribers (name, dateOfBirth, address) VALUES ("tanaka", "1992/01/01", "東京都世田谷区");
INSERT INTO subscribers (name, dateOfBirth, address) VALUES ("yamada", "1995/06/05", "千葉県千葉市");
INSERT INTO subscribers (name, dateOfBirth, address) VALUES ("taniguchi", "2000/10/10", "神奈川県横浜市");
INSERT INTO subscribers (name, dateOfBirth, address) VALUES ("suzuki", "2011/12/24", "埼玉県さいたま市");
INSERT INTO subscribers (name, dateOfBirth, address) VALUES ("yamamoto", "2014/12/31", "茨城県水戸市");
