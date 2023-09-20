DROP TABLE IF EXISTS locations;

CREATE TABLE locations (
  id int unsigned AUTO_INCREMENT,
  corner VARCHAR(20) NOT NULL,
  locationName VARCHAR(1) NOT NULL,
  place VARCHAR(20) NOT NULL,
  creator VARCHAR(20) NOT NULL,
  dateCreated VARCHAR(10) NOT NULL,
  PRIMARY KEY(id),
  UNIQUE KEY(corner)
);

INSERT INTO locations (corner, locationName, place, creator, dateCreated) VALUES ("food", "A", "left-back", "yamada", "2023/08/01");
INSERT INTO locations (corner, locationName, place, creator, dateCreated) VALUES ("drink", "B", "center-back", "yamada", "2023/08/01");
INSERT INTO locations (corner, locationName, place, creator, dateCreated) VALUES ("daily-necessities", "C", "right-back", "yamada", "2023/08/01");
INSERT INTO locations (corner, locationName, place, creator, dateCreated) VALUES ("electric-appliances", "D", "left-front", "yamada", "2023/08/01");
INSERT INTO locations (corner, locationName, place, creator, dateCreated) VALUES ("outdoor-product", "E", "center-front", "yamada", "2023/08/01");
INSERT INTO locations (corner, locationName, place, creator, dateCreated) VALUES ("toy", "F", "right-front", "yamada", "2023/08/01");
