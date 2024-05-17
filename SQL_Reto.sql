DROP DATABASE DISCOSOUND;

-- Crear la base de datos

	CREATE DATABASE DISCOSOUND;

	USE DISCOSOUND;

-- 	Crear tablas

	CREATE TABLE USUARIO (
	DNI VARCHAR (9) PRIMARY KEY,
	NOMBRE VARCHAR (20),
	APELLIDO VARCHAR (20),
	NOMUSU VARCHAR (20) not null,
	FECHANAC DATE,
	EMAIL VARCHAR(40),
	CONTRASEINA varchar(20) not null
	);

	CREATE TABLE ADMINISTRADOR (
	DNIADMIN VARCHAR(9) PRIMARY KEY,
	NOMDISCOTECA VARCHAR(20),
	FOREIGN KEY (DNIADMIN) REFERENCES usuario (dni) on delete cascade on update cascade
	);

	CREATE TABLE CLIENTE (
	DNICLIENTE VARCHAR(9) PRIMARY KEY,
	GENERO VARCHAR (10),
	FOREIGN KEY (DNICLIENTE) REFERENCES usuario (dni) on delete cascade on update cascade
	);

	CREATE TABLE DISCOTECA (
	CODIGODISC VARCHAR (20) PRIMARY KEY,
	NOMBRE VARCHAR (20),
	DIRECCION VARCHAR (50),
	AFORO INT);

	CREATE TABLE ENTRADA (
	CODENTRADA VARCHAR(20) PRIMARY KEY,
	NOMEVENTO VARCHAR(20),
	NOMDJ VARCHAR(20) default 'SORPRESA',
	PRECIO INT,
	CATEGORIA VARCHAR(20),
	CANTCONSUMI INT,
	FECHA DATE,
	DIFERENCIAPRECIOMUJER INT,
	CODIGODISC VARCHAR (20),
	FOREIGN KEY (CODIGODISC) REFERENCES DISCOTECA (CODIGODISC) on delete cascade on update cascade
	);

	CREATE TABLE COMPRA (
	DNICLIENTE VARCHAR(9),
	CODENTRADA VARCHAR(20),
	METODOPAGO VARCHAR(20),
	PRECIOTOTAL FLOAT,
	CANTIDADENTRADAS INT, 
	TELEFONO INT, 
	PRIMARY KEY (DNICLIENTE, CODENTRADA),
	FOREIGN KEY (DNICLIENTE) REFERENCES CLIENTE (DNICLIENTE),
	FOREIGN KEY (CODENTRADA) REFERENCES ENTRADA (CODENTRADA));

-- Insertar administradores y clientes 

	INSERT INTO USUARIO (DNI, NOMBRE, APELLIDO, NOMUSU, FECHANAC, EMAIL, CONTRASEINA)
	VALUES ('20978340V', 'Juan', 'Lopez', 'adminStage', '2005-12-06', 'adminStage@gmail.com', 'abcd*1234');
    
    INSERT INTO USUARIO (DNI, NOMBRE, APELLIDO, NOMUSU, FECHANAC, EMAIL, CONTRASEINA)
	VALUES ('79003943r', 'Maria', 'Alvez', 'adminMoma', '2004-11-09', 'adminMoma@gmail.com', 'abcd*1234');
    
    INSERT INTO USUARIO (DNI, NOMBRE, APELLIDO, NOMUSU, FECHANAC, EMAIL, CONTRASEINA)
	VALUES ('35883328Q', 'Andoni', 'Ordoñez', 'andoniDam', '2005-12-06', 'andoni@gmail.com', '1');
    
    INSERT INTO USUARIO (DNI, NOMBRE, APELLIDO, NOMUSU, FECHANAC, EMAIL, CONTRASEINA)
	VALUES ('80452460R', 'Markel', 'Arabio', 'markelDam', '2004-11-09', 'markel@gmail.com', '1');
    
    INSERT INTO USUARIO (DNI, NOMBRE, APELLIDO, NOMUSU, FECHANAC, EMAIL, CONTRASEINA)
	VALUES ('61872037J', 'Oscar', 'Gonzalez', 'oscarDam', '2005-12-06', 'oscar@gmail.com', '1');
    
    INSERT INTO USUARIO (DNI, NOMBRE, APELLIDO, NOMUSU, FECHANAC, EMAIL, CONTRASEINA)
	VALUES ('34488720J', 'Sachin', 'Buraj', 'sachinDam', '2004-11-09', 'sachin@gmail.com', '1');
    
    INSERT INTO CLIENTE (DNICLIENTE, GENERO)
	VALUES ('35883328Q', 'hombre');
    
	INSERT INTO CLIENTE (DNICLIENTE, GENERO)
	VALUES ('80452460R', 'hombre');
    
	INSERT INTO CLIENTE (DNICLIENTE, GENERO)
	VALUES ('61872037J', 'hombre');
    
	INSERT INTO CLIENTE (DNICLIENTE, GENERO)
	VALUES ('34488720J', 'hombre');
    
	INSERT INTO ADMINISTRADOR (DNIADMIN, NOMDISCOTECA)
	VALUES ('20978340V', 'Stage');
    
    INSERT INTO ADMINISTRADOR (DNIADMIN, NOMDISCOTECA)
	VALUES ('79003943r', 'Moma');

-- Insertar Discoteca

	INSERT INTO DISCOTECA (CODIGODISC, NOMBRE, DIRECCION, AFORO)
	VALUES ('1111', 'Stage', 'DirecciónDiscoteca', 900);
    
    INSERT INTO DISCOTECA (CODIGODISC, NOMBRE, DIRECCION, AFORO)
	VALUES ('2222', 'Moma', 'DirecciónDiscotesssca', 600);
    
-- Insertar entrada

	INSERT INTO ENTRADA (CODENTRADA, NOMEVENTO, NOMDJ, PRECIO, CATEGORIA, CANTCONSUMI, FECHA, DIFERENCIAPRECIOMUJER, CODIGODISC)
	VALUES ('ST-0002', 'Fiesta de Verano', 'DJ Pepito', 20, 'General', 2, '2024-06-01', 0, '1111');
    
    INSERT INTO ENTRADA (CODENTRADA, NOMEVENTO, NOMDJ, PRECIO, CATEGORIA, CANTCONSUMI, FECHA, DIFERENCIAPRECIOMUJER, CODIGODISC)
	VALUES ('ST-0003', 'Reggeaton', 'DJ Mambo', 20, 'General', 2, '2024-06-03', 0, '1111');
    
    INSERT INTO ENTRADA (CODENTRADA, NOMEVENTO, NOMDJ, PRECIO, CATEGORIA, CANTCONSUMI, FECHA, DIFERENCIAPRECIOMUJER, CODIGODISC)
	VALUES ('ST-0004', 'Fiestoon', 'DJ Palos', 20, 'General', 2, '2024-06-05', 0, '1111');
    
    
    
    INSERT INTO ENTRADA (CODENTRADA, NOMEVENTO, NOMDJ, PRECIO, CATEGORIA, CANTCONSUMI, FECHA, DIFERENCIAPRECIOMUJER, CODIGODISC)
	VALUES ('MO-0005', 'RBF', 'DJ andoni', 15, 'General', 2, '2024-06-01', 0, '2222');
    
    INSERT INTO ENTRADA (CODENTRADA, NOMEVENTO, NOMDJ, PRECIO, CATEGORIA, CANTCONSUMI, FECHA, DIFERENCIAPRECIOMUJER, CODIGODISC)
	VALUES ('MO-0006', 'ANUEL', 'DJ Nino', 15, 'General', 2, '2024-06-03', 0, '2222');
    
    INSERT INTO ENTRADA (CODENTRADA, NOMEVENTO, NOMDJ, PRECIO, CATEGORIA, CANTCONSUMI, FECHA, DIFERENCIAPRECIOMUJER, CODIGODISC)
	VALUES ('MO-0007', 'Techno', 'DJ Palos', 15, 'General', 2, '2024-06-05', 0, '2222');
    
    
-- Crear Funcion

		DELIMITER //

	CREATE FUNCTION AforoDisponibleEnFecha(nombreDiscoteca VARCHAR(20), fecha DATE)
	RETURNS INT
	DETERMINISTIC
	BEGIN
		DECLARE aforoDisco INT;
		DECLARE entradasVendidas INT;

		-- Variable para controlar si ocurrió algún error
		DECLARE errorFlag INT DEFAULT 0;

		-- Obtener el aforo de la discoteca
		SELECT AFORO INTO aforoDisco
		FROM DISCOTECA
		WHERE NOMBRE = nombreDiscoteca;

		-- Manejo de excepciones para asegurar que se encontró la discoteca
		IF aforoDisco IS NULL THEN
			SET errorFlag = 1;
		END IF;

		-- Si no hubo error al obtener el aforo, continuamos
		IF errorFlag = 0 THEN
			-- Obtener la cantidad de entradas vendidas en la fecha específica
			SELECT SUM(C.CANTIDADENTRADAS) INTO entradasVendidas
			FROM COMPRA C
			JOIN ENTRADA E ON C.CODENTRADA = E.CODENTRADA
			WHERE E.FECHA = fecha
			AND E.CODIGODISC = (SELECT CODIGODISC FROM DISCOTECA WHERE NOMBRE = nombreDiscoteca);

			-- Calcular el aforo disponible restando las entradas vendidas del aforo total
			RETURN aforoDisco - IFNULL(entradasVendidas, 0);
		ELSE
			-- Si ocurrió un error, devolvemos un valor indicativo, por ejemplo, -1
			RETURN -1;
		END IF;
	END //

	DELIMITER ;
    
    DELIMITER //
		
	CREATE FUNCTION ContarUsuarios()
	RETURNS INT
	DETERMINISTIC
	BEGIN
		DECLARE totalUsuarios INT;

		-- Declaramos un cursor para recorrer la tabla USUARIO
		DECLARE userCursor CURSOR FOR
			SELECT COUNT(*) FROM USUARIO;

		-- Abrimos el cursor
		OPEN userCursor;

		-- Recuperamos el número total de usuarios
		FETCH userCursor INTO totalUsuarios;

		-- Cerramos el cursor
		CLOSE userCursor;

		-- Devolvemos el número total de usuarios
		RETURN totalUsuarios;
	END //

	DELIMITER ;
    
-- Crear procedimiento

		DELIMITER //

	CREATE PROCEDURE ObtenerCantidadEntradasVendidas(IN codigoEntrada VARCHAR(20), OUT p_cantidadEntradasVendidas INT)
	BEGIN
		DECLARE cantidad INT DEFAULT 0;

		-- Manejo de excepciones
		DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		BEGIN
			-- Si ocurre una excepción, establecemos la cantidad de entradas vendidas en -1
			SET p_cantidadEntradasVendidas = -1;
		END;

		-- Contar la cantidad de entradas vendidas para el evento dado
		SELECT SUM(CantidadEntradas) INTO cantidad FROM COMPRA WHERE codEntrada = codigoEntrada;

		-- Devolver la cantidad de entradas vendidas
		SET p_cantidadEntradasVendidas = cantidad;
	END //

	DELIMITER ;
    
		DELIMITER //

	CREATE PROCEDURE MostrarUsuarios()
	BEGIN
		-- Seleccionar todos los usuarios de la tabla USUARIO
		SELECT * FROM USUARIO;
	END //

	DELIMITER ;
    
			DELIMITER //

	CREATE PROCEDURE CalcularEdadPromedioEntrada(
		IN codigoEntrada VARCHAR(20),
		OUT edadPromedio FLOAT
	)
	BEGIN
		-- Calcular la edad promedio de los compradores de la entrada especificada
		SELECT AVG(TIMESTAMPDIFF(YEAR, U.FECHANAC, CURDATE())) INTO edadPromedio
		FROM USUARIO U
		INNER JOIN COMPRA C ON U.DNI = C.DNICLIENTE
		WHERE C.CODENTRADA = codigoEntrada;
	END //

	DELIMITER ;
    
-- Crear usuario

	DROP USER 'markel'@'localhost';
    
	create user 'markel'@'localhost' identified by 'abcd*1234';
	grant all privileges on *.* to 'markel'@'localhost';
    
   DROP USER 'andoni'@'localhost';
    
    create user 'andoni'@'localhost' identified by 'abcd*1234';
	grant all privileges on *.* to 'andoni'@'localhost';