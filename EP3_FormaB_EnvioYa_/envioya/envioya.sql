CREATE TABLE envios (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    tipo VARCHAR2(20) NOT NULL,
    destinatario VARCHAR2(100) NOT NULL,
    tarifa_base NUMBER(10) NOT NULL,
    peso_kg NUMBER(6) NOT NULL,
    distancia_km NUMBER(6),
    es_fragil CHAR(1),
    dias_estimados NUMBER(3),
    impuesto_aduana NUMBER(10)
);

INSERT INTO envios (tipo, destinatario, tarifa_base, peso_kg, distancia_km, es_fragil, dias_estimados, impuesto_aduana)
VALUES ('NACIONAL', 'Sebastián Morales', 6500, 3, 420, 'S', NULL, NULL);

INSERT INTO envios (tipo, destinatario, tarifa_base, peso_kg, distancia_km, es_fragil, dias_estimados, impuesto_aduana)
VALUES ('INTERNACIONAL', 'Fernanda Castro', 28000, 5, NULL, NULL, 10, 14500);

INSERT INTO envios (tipo, destinatario, tarifa_base, peso_kg, distancia_km, es_fragil, dias_estimados, impuesto_aduana)
VALUES ('NACIONAL', 'Diego Paredes', 4900, 1, 120, 'N', NULL, NULL);

COMMIT;
