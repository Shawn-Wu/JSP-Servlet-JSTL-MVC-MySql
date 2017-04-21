-- ɾ�����ݿ�
DROP DATABASE IF EXISTS mshop ;
-- �������ݿ�
CREATE DATABASE mshop CHARACTER SET UTF8 ;
-- ʹ��mshop���ݿ�
USE mshop ;
DROP TABLE IF EXISTS shopcar ;
DROP TABLE IF EXISTS details ;
DROP TABLE IF EXISTS orders ;
DROP TABLE IF EXISTS member ;
DROP TABLE IF EXISTS goods ;
DROP TABLE IF EXISTS item ;
DROP TABLE IF EXISTS admin ;
-- 1��������Ʒ���ͱ�
CREATE TABLE item (
  iid       INT     AUTO_INCREMENT ,
  title     VARCHAR(200)  NOT NULL ,
  CONSTRAINT pk_iid PRIMARY KEY(iid)
) ENGINE=innodb ;

-- 2����������Ա��Ϣ��
CREATE TABLE admin(
  aid       VARCHAR(50) ,
  password  VARCHAR(32) NOT NULL ,
  lastdate  DATETIME ,
  CONSTRAINT pk_aid PRIMARY KEY(aid)
) ENGINE=innodb ;

-- 3�������û���Ϣ��
CREATE TABLE member(
  mid       VARCHAR(50) ,
  password  VARCHAR(32) NOT NULL ,
  name      VARCHAR(50) ,
  phone     VARCHAR(50) ,
  address   VARCHAR(100) ,
  status    INT ,
  code      VARCHAR(100) ,
  regdate   DATETIME    NOT NULL ,
  photo     VARCHAR(50)   DEFAULT 'nophoto.jpg' ,
  CONSTRAINT pk_mid PRIMARY KEY(mid)
) ENGINE=innodb ;

-- 4��������Ʒ��Ϣ��
CREATE TABLE goods (
  gid       INT       AUTO_INCREMENT ,
  iid       INT       ,
  aid       VARCHAR(50) ,
  title     VARCHAR(50) ,
  pubdate   DATETIME ,
  price     FLOAT ,
  amount    INT ,
  bow       INT ,
  note      TEXT ,
  photo     VARCHAR(100) ,
  status    INT ,
  CONSTRAINT pk_gid PRIMARY KEY(gid) ,
  CONSTRAINT fk_iid FOREIGN KEY(iid) REFERENCES item(iid) ON DELETE SET NULL ,
  CONSTRAINT fk_aid FOREIGN KEY(aid) REFERENCES admin(aid) ON DELETE SET NULL
) ENGINE=innodb ;

-- 5������������Ϣ��
CREATE TABLE orders(
  oid         INT       AUTO_INCREMENT ,
  mid         VARCHAR(50) ,
  name        VARCHAR(50) ,
  phone       VARCHAR(50) ,
  address     VARCHAR(100) ,
  credate     DATETIME ,
  pay         FLOAT ,
  CONSTRAINT pk_oid PRIMARY KEY(oid) ,
  CONSTRAINT fk_mid FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE CASCADE
) ENGINE=innodb ;

-- 6���������������
CREATE TABLE details(
  odid        INT       AUTO_INCREMENT ,
  oid         INT       NOT NULL ,
  gid         INT       ,
  title       VARCHAR(50) NOT NULL ,
  price       FLOAT     NOT NULL ,
  amount      INT       NOT NULL ,
  CONSTRAINT pk_odid PRIMARY KEY(odid) ,
  CONSTRAINT fk_oid2 FOREIGN KEY(oid) REFERENCES orders(oid) ON DELETE CASCADE ,
  CONSTRAINT fk_gid2 FOREIGN KEY(gid) REFERENCES goods(gid) ON DELETE SET NULL
) ENGINE=innodb ;
-- 7�����ﳵ��Ϣ��
CREATE TABLE shopcar(
  gid         INT      ,
  mid         VARCHAR(50) ,
  amount      INT ,
  CONSTRAINT fk_gid3 FOREIGN KEY(gid) REFERENCES goods(gid) ON DELETE CASCADE ,
  CONSTRAINT fk_mid3 FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE CASCADE
) ;

-- ������Ʒ������Ϣ
INSERT INTO item(title) VALUES ('������Ʒ') ;
INSERT INTO item(title) VALUES ('��ͯ���') ;
INSERT INTO item(title) VALUES ('ҽ����е') ;
INSERT INTO item(title) VALUES ('�˶�����') ;
INSERT INTO item(title) VALUES ('�칫��Ʒ') ;
-- ���ӹ���Ա��Ϣ��admin / hello
INSERT INTO admin(aid,password) VALUES ('admin','5D41402ABC4B2A76B9719D911017C592') ;
-- ������ͨ�û���Ϣ��mldn / java
INSERT INTO member(mid,password,regdate) VALUES ('mldn','93F725A07423FE1C889F448B33D21F46','1999-10-10') ;
COMMIT ;
