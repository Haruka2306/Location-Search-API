DROP TABLE IF EXISTS location;

CREATE TABLE location (
  id int unsigned AUTO_INCREMENT,
  corner VARCHAR(20) NOT NULL,
  locationName VARCHAR(1) NOT NULL,
  place VARCHAR(20) NOT NULL,
  creator VARCHAR(20) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO location (corner, locationName, place, creator) VALUES ("food", "A", "left-back", "yamada");
INSERT INTO location (corner, locationName, place, creator) VALUES ("drink", "B", "center-back", "yamada");
INSERT INTO location (corner, locationName, place, creator) VALUES ("daily-necessities", "C", "right-back", "yamada");
INSERT INTO location (corner, locationName, place, creator) VALUES ("electric-appliances", "D", "left-front", "yamada");
INSERT INTO location (corner, locationName, place, creator) VALUES ("outdoor-product", "E", "center-front", "yamada");
INSERT INTO location (corner, locationName, place, creator) VALUES ("toy", "F", "right-front", "yamada");
