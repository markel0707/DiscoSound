use discosound;

CREATE USER administrador identified by 'abcd*1234';
GRANT ALL PRIVILEGES ON DiscoSound.* TO administrador;

CREATE ROLE usuario;
GRANT SELECT ON discosound.* TO usuario;





