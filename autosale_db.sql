DROP TABLE IF EXISTS car;

CREATE TABLE car (
    id BIGINT NOT NULL,
    brand VARCHAR NOT NULL,
    model VARCHAR NOT NULL,
    year INTEGER NOT NULL,
    car_type VARCHAR NOT NULL,
    price DECIMAL(19,2) NOT NULL,
    trunk_capacity DOUBLE PRECISION,
    load_capacity DOUBLE PRECISION,
    seating_capacity DOUBLE PRECISION
);

INSERT INTO car VALUES (1,'Chevrolet', 'COLODADO', 2010, 'TRUCK',
                        25000.0,null,456.0, null);

-- INSERT INTO car VALUES (493.0,25000.0, null, null, 2010,
--                         1,'TRUCK','Chevrolet', 'Chevrolet');

