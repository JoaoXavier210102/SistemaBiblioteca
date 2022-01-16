-- Sript para criar a tabela book
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ISBN` bigint NOT NULL,
  `authors` varchar(40) NOT NULL,
  `edition` varchar(40) NOT NULL,
  `title` varchar(40) NOT NULL,
  `year` int NOT NULL,
  `publishingCompany` varchar(40) DEFAULT NULL,
  `borrowed` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
);

-- Script para criar a tabela user
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` int NOT NULL,
  `type` varchar(15) DEFAULT NULL,
  `borrowing` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);