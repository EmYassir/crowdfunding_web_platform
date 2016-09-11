CREATE TABLE IF NOT EXISTS `sentences` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sentence` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
INSERT INTO `eggsale_dev`.`sentences` (`id`, `sentence`) VALUES (NULL, 'My very first sentence!');
INSERT INTO `eggsale_dev`.`sentences` (`id`, `sentence`) VALUES (NULL, 'My second sentence!');