DROP TABLE IF EXISTS locations;

CREATE TABLE locations (
  id int unsigned AUTO_INCREMENT,
  corner VARCHAR(20) NOT NULL,
  location_name VARCHAR(1) NOT NULL,
  place VARCHAR(20) NOT NULL,
  created_by VARCHAR(20) NOT NULL,
  created_date VARCHAR(10) NOT NULL,
  PRIMARY KEY(id),
  UNIQUE KEY(corner)
);

INSERT INTO locations (corner, location_name, place, created_by, created_date) VALUES ("food", "A", "left-back", "yamada", "2023/08/01");
INSERT INTO locations (corner, location_name, place, created_by, created_date) VALUES ("drink", "B", "center-back", "yamada", "2023/08/01");
INSERT INTO locations (corner, location_name, place, created_by, created_date) VALUES ("daily-necessities", "C", "right-back", "yamada", "2023/08/01");
INSERT INTO locations (corner, location_name, place, created_by, created_date) VALUES ("electric-appliances", "D", "left-front", "yamada", "2023/08/01");
INSERT INTO locations (corner, location_name, place, created_by, created_date) VALUES ("outdoor-product", "E", "center-front", "yamada", "2023/08/01");
INSERT INTO locations (corner, location_name, place, created_by, created_date) VALUES ("toy", "F", "right-front", "yamada", "2023/08/01");
