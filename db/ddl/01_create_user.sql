CREATE USER 'base_user'@'%' IDENTIFIED BY 'base_pass';
GRANT SELECT,INSERT,UPDATE,DELETE,EXECUTE,SHOW VIEW ON base.* TO 'base_user'@'%';
GRANT SELECT,SHOW VIEW ON base_ro.* TO 'base_user'@'%';