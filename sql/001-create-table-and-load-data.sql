DROP TABLE IF EXISTS locations;

CREATE TABLE locations (
  id int unsigned AUTO_INCREMENT,
  corner VARCHAR(20) NOT NULL,
  locationName VARCHAR(1) NOT NULL,
  place VARCHAR(20) NOT NULL,
  creator VARCHAR(20) NOT NULL,
  PRIMARY KEY(id),
  UNIQUE KEY(corner)
);

INSERT INTO locations (corner, locationName, place, creator) VALUES ("food", "A", "left-back", "yamada");
INSERT INTO locations (corner, locationName, place, creator) VALUES ("drink", "B", "center-back", "yamada");
INSERT INTO locations (corner, locationName, place, creator) VALUES ("daily-necessities", "C", "right-back", "yamada");
INSERT INTO locations (corner, locationName, place, creator) VALUES ("electric-appliances", "D", "left-front", "yamada");
INSERT INTO locations (corner, locationName, place, creator) VALUES ("outdoor-product", "E", "center-front", "yamada");
INSERT INTO locations (corner, locationName, place, creator) VALUES ("toy", "F", "right-front", "yamada");
