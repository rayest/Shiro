-- ---------------------------------------------------------
-- ------------------------- t_role ------------------------
-- ---------------------------------------------------------
DROP TABLE IF EXISTS t_role;

CREATE TABLE t_role (
  id       INT(11) NOT NULL AUTO_INCREMENT,
  roleName VARCHAR(20)      DEFAULT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------
-- ---------------- t_permission --------------------------
-- 角色有什么权限 ------------------------------------------
-- --------------------------------------------------------
DROP TABLE IF EXISTS t_permission;

CREATE TABLE t_permission (
  id             INT(11) NOT NULL AUTO_INCREMENT,
  permissionName VARCHAR(50)      DEFAULT NULL,
  roleId         INT(11)          DEFAULT NULL,
  PRIMARY KEY (id),
  KEY roleId (roleId),
  CONSTRAINT t_permission_ibfk_1 FOREIGN KEY (roleId) REFERENCES t_role (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ---------------------------------------------------------
-- -------------------- t_user -----------------------------
-- 用户是什么角色 -------------------------------------------
-- ---------------------------------------------------------
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
  id       INT(11) NOT NULL AUTO_INCREMENT,
  userName VARCHAR(20)      DEFAULT NULL,
  password VARCHAR(20)      DEFAULT NULL,
  roleId   INT(11)          DEFAULT NULL,
  PRIMARY KEY (id),
  KEY roleId (roleId),
  CONSTRAINT t_user_ibfk_1 FOREIGN KEY (roleId) REFERENCES t_role (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;