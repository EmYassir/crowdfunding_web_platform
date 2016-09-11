SET SQL_SAFE_UPDATES=0;
DELETE FROM `eggsale_dev`.`reward`;
DELETE FROM `eggsale_dev`.`comment_votedown`;
DELETE FROM `eggsale_dev`.`comment_voteup`;
DELETE FROM `eggsale_dev`.`comment`;
DELETE FROM `eggsale_dev`.`idea_votedown`;
DELETE FROM `eggsale_dev`.`idea_voteup`;
DELETE FROM `eggsale_dev`.`idea`;
DELETE FROM `eggsale_dev`.`attachment`;
DELETE FROM `eggsale_dev`.`cartproject`;
DELETE FROM `eggsale_dev`.`project`;
DELETE FROM `eggsale_dev`.`cart`;
DELETE FROM `eggsale_dev`.`user`;



INSERT INTO `eggsale_dev`.`user` (`DTYPE`, `id`, `email`, `password`, `username`, `description`) VALUES ('User', '1', 'user1@user.fr', '24c9e15e52afc47c225b757e7bee1f9d', 'user1', 'Utilisateur');
INSERT INTO `eggsale_dev`.`user` (`DTYPE`, `id`, `email`, `password`, `username`, `description`) VALUES ('User', '2', 'user2@user.fr', '7e58d63b60197ceb55a1c487989a3720', 'user2', 'Utilisateur');
INSERT INTO `eggsale_dev`.`user` (`DTYPE`, `id`, `email`, `password`, `username`, `description`) VALUES ('User', '3', 'user3@user.fr', '92877af70a45fd6a2ed7fe81e1236b78', 'user3', 'Utilisateur');
INSERT INTO `eggsale_dev`.`user` (`DTYPE`, `id`, `email`, `password`, `username`, `description`) VALUES ('User', '4', 'user4@user.fr', '3f02ebe3d7929b091e3d8ccfde2f3bc6', 'user4', 'Utilisateur');
INSERT INTO `eggsale_dev`.`user` (`DTYPE`, `id`, `email`, `password`, `username`, `description`) VALUES ('Administrator', '5', 'admin@admin.fr', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'Administrateur');



INSERT INTO `eggsale_dev`.`cart` (`id`, `isValidate`, `total`, `userId`) VALUES ('1', 0, '120', '1');
INSERT INTO `eggsale_dev`.`cart` (`id`, `isValidate`, `total`, `userId`) VALUES ('2', 1, '210', '1');
INSERT INTO `eggsale_dev`.`cart` (`id`, `isValidate`, `total`, `userId`) VALUES ('3', 0, '211', '2');
INSERT INTO `eggsale_dev`.`cart` (`id`, `isValidate`, `total`, `userId`) VALUES ('4', 1, '32', '2');
INSERT INTO `eggsale_dev`.`cart` (`id`, `isValidate`, `total`, `userId`) VALUES ('5', 0, '112', '3');
INSERT INTO `eggsale_dev`.`cart` (`id`, `isValidate`, `total`, `userId`) VALUES ('6', 1, '211', '3');
INSERT INTO `eggsale_dev`.`cart` (`id`, `isValidate`, `total`, `userId`) VALUES ('7', 0, '100', '4');
INSERT INTO `eggsale_dev`.`cart` (`id`, `isValidate`, `total`, `userId`) VALUES ('8', 1, '120', '4');
INSERT INTO `eggsale_dev`.`cart` (`id`, `isValidate`, `total`, `userId`) VALUES ('9', 0, '121', '5');
INSERT INTO `eggsale_dev`.`cart` (`id`, `isValidate`, `total`, `userId`) VALUES ('10', 1, '210', '5');




INSERT INTO `eggsale_dev`.`project` (`id`, `description`, `isSalable`, `name`, `price`, `userId`) VALUES ('1', 'Développement applications JavaCard embarquées sur carte. Programmation Java, Base de cryptographie', 1, 'Projet ATAC', '120', '1');
INSERT INTO `eggsale_dev`.`project` (`id`, `description`, `isSalable`, `name`, `price`, `userId`) VALUES ('2', 'Mettre en pratique les notions vues en cours de sécurité et de réseaux dans le cadre dun projet de construction une architecture de calcul et de stockage distribuée. Il faudra spécifier une politique de sécurité ainsi que son implantation en sappuyant sur des systèmes existants dont le choix devra être justifié.', 1, 'Projet CIS', '360', '2');
INSERT INTO `eggsale_dev`.`project` (`id`, `description`, `isSalable`, `name`, `price`, `userId`) VALUES ('3', 'Expérimenter les technologies de construction d’applications réparties - réseaux, systèmes distribués, systèmes de gestion de bases de données - à travers la réalisation d’une application concrète les mettant en œuvre.', 1, 'Projet DAC', '500', '3');
INSERT INTO `eggsale_dev`.`project` (`id`, `description`, `isSalable`, `name`, `price`, `userId`) VALUES ('4', 'La simulation d’entreprise en 3ème année a pour objectif :de réaliser la synthèse des enseignements de gestion suivis au cours de la scolarité en école d’ingénieur.d’approfondir et de mettre en œuvre l’ensemble des concepts de gestion et de management.', 1, 'Projet Business Game', '240', '4');
INSERT INTO `eggsale_dev`.`project` (`id`, `description`, `isSalable`, `name`, `price`, `userId`) VALUES ('5', 'À partir des caractéristiques et des besoins des applications réparties, ce cours présente les intergiciels (middleware) permettant la construction et le déploiement dapplications réparties. Ces intergiciels ont pour caractéristique commune de permettre à des applications - programmées dans des langages différents et sexécutant sur des systèmes matériels et logiciels hétérogènes.', 1, 'Projet CAR', '230', '5');
INSERT INTO `eggsale_dev`.`project` (`id`, `description`, `isSalable`, `name`, `price`, `userId`) VALUES ('6', 'Ce Projet fournit une initiation au logiciel de modélisation 3D et danimation Maya. Dans une première partie, ce cours présente la mise en œuvre sous Maya des principales techniques de modélisation (polygones et NURBS) et danimation dun personnage 3D. Dans une seconde partie, lenvironnement de programmation de Maya.', 1, 'Projet Ingénierie danimation 3D', '500', '1');
INSERT INTO `eggsale_dev`.`project` (`id`, `description`, `isSalable`, `name`, `price`, `userId`) VALUES ('7', 'objectif pédagogique de ce projet est dillustrer certains aspects du génie logiciel à travers le développement dune application de taille conséquente. Les aspects visés sont principalement le respect dun cahier des charges, la conception logicielle, les techniques de validation et vérification et la mise en place dune démarche qualité. Le logiciel, développé en Ada.', 1, 'Projet Génie Logiciel', '1000', '2');


INSERT INTO `eggsale_dev`.`cartproject` (`Cart_id`, `projects_id`) VALUES ('1', '1');
INSERT INTO `eggsale_dev`.`cartproject` (`Cart_id`, `projects_id`) VALUES ('2', '2');
INSERT INTO `eggsale_dev`.`cartproject` (`Cart_id`, `projects_id`) VALUES ('3', '3');
INSERT INTO `eggsale_dev`.`cartproject` (`Cart_id`, `projects_id`) VALUES ('4', '4');
INSERT INTO `eggsale_dev`.`cartproject` (`Cart_id`, `projects_id`) VALUES ('5', '5');
INSERT INTO `eggsale_dev`.`cartproject` (`Cart_id`, `projects_id`) VALUES ('6', '6');
INSERT INTO `eggsale_dev`.`cartproject` (`Cart_id`, `projects_id`) VALUES ('7', '7');




INSERT INTO `eggsale_dev`.`idea` (`id`, `content`, `title`, `userId`, `projectId`) VALUES ('1', 'Il faut avoir des prérequis afin de pouvoir travailler ce projet', 'Prérequis projet ATAC', '1', '1');
INSERT INTO `eggsale_dev`.`idea` (`id`, `content`, `title`, `userId`, `projectId`) VALUES ('2', 'Il faut avoir des prérequis en Sécurité des systèmes dinformations pour travailler sur ce projet', 'Prérequis projet CIS', '2', '2');
INSERT INTO `eggsale_dev`.`idea` (`id`, `content`, `title`, `userId`, `projectId`) VALUES ('3', 'Il faut avoir des prérequis en Technologie Java J2EE pour pouvoir travailler sur ce projet', 'Prérequis projet DAC', '3', '3');
INSERT INTO `eggsale_dev`.`idea` (`id`, `content`, `title`, `userId`, `projectId`) VALUES ('4', 'Il faut avoir des prérequis en Finances et Management de projet pour pouvoir travailler sur ce projet', 'Prérequis projet Business Game', '4', '4');
INSERT INTO `eggsale_dev`.`idea` (`id`, `content`, `title`, `userId`, `projectId`) VALUES ('5', 'Il faut avoir des prérequis en Technologie Java J2EE et en architecture r&partie pour pouvoir travailler sur ce projet', 'Prérequis projet CAR', '5', '5');
INSERT INTO `eggsale_dev`.`idea` (`id`, `content`, `title`, `userId`, `projectId`) VALUES ('6', 'Il faut avoir des prérequis en Ingénierie 3D et en script Python', 'Prérequis Projet 3D', '1', '6');
INSERT INTO `eggsale_dev`.`idea` (`id`, `content`, `title`, `userId`, `projectId`) VALUES ('7', 'Il faut avoir des prérequis en Théorie de language, en Sript SHELL ', 'Prérequis Projet GL', '2', '7');




INSERT INTO `eggsale_dev`.`idea_voteup` (`Idea_id`, `votedUp_id`) VALUES ('1', '1');
INSERT INTO `eggsale_dev`.`idea_voteup` (`Idea_id`, `votedUp_id`) VALUES ('2', '1');
INSERT INTO `eggsale_dev`.`idea_voteup` (`Idea_id`, `votedUp_id`) VALUES ('3', '1');
INSERT INTO `eggsale_dev`.`idea_voteup` (`Idea_id`, `votedUp_id`) VALUES ('1', '2');
INSERT INTO `eggsale_dev`.`idea_voteup` (`Idea_id`, `votedUp_id`) VALUES ('2', '2');
INSERT INTO `eggsale_dev`.`idea_voteup` (`Idea_id`, `votedUp_id`) VALUES ('3', '2');
INSERT INTO `eggsale_dev`.`idea_voteup` (`Idea_id`, `votedUp_id`) VALUES ('7', '2');
INSERT INTO `eggsale_dev`.`idea_voteup` (`Idea_id`, `votedUp_id`) VALUES ('2', '3');
INSERT INTO `eggsale_dev`.`idea_voteup` (`Idea_id`, `votedUp_id`) VALUES ('3', '3');
INSERT INTO `eggsale_dev`.`idea_voteup` (`Idea_id`, `votedUp_id`) VALUES ('4', '3');
INSERT INTO `eggsale_dev`.`idea_voteup` (`Idea_id`, `votedUp_id`) VALUES ('5', '3');
INSERT INTO `eggsale_dev`.`idea_voteup` (`Idea_id`, `votedUp_id`) VALUES ('6', '3');
INSERT INTO `eggsale_dev`.`idea_voteup` (`Idea_id`, `votedUp_id`) VALUES ('7', '3');


INSERT INTO `eggsale_dev`.`idea_votedown` (`Idea_id`, `votedDown_id`) VALUES ('4', '1');
INSERT INTO `eggsale_dev`.`idea_votedown` (`Idea_id`, `votedDown_id`) VALUES ('5', '1');
INSERT INTO `eggsale_dev`.`idea_votedown` (`Idea_id`, `votedDown_id`) VALUES ('6', '1');
INSERT INTO `eggsale_dev`.`idea_votedown` (`Idea_id`, `votedDown_id`) VALUES ('4', '2');
INSERT INTO `eggsale_dev`.`idea_votedown` (`Idea_id`, `votedDown_id`) VALUES ('5', '2');
INSERT INTO `eggsale_dev`.`idea_votedown` (`Idea_id`, `votedDown_id`) VALUES ('6', '2');
INSERT INTO `eggsale_dev`.`idea_votedown` (`Idea_id`, `votedDown_id`) VALUES ('4', '4');
INSERT INTO `eggsale_dev`.`idea_votedown` (`Idea_id`, `votedDown_id`) VALUES ('5', '4');
INSERT INTO `eggsale_dev`.`idea_votedown` (`Idea_id`, `votedDown_id`) VALUES ('6', '4');
INSERT INTO `eggsale_dev`.`idea_votedown` (`Idea_id`, `votedDown_id`) VALUES ('4', '5');
INSERT INTO `eggsale_dev`.`idea_votedown` (`Idea_id`, `votedDown_id`) VALUES ('5', '5');
INSERT INTO `eggsale_dev`.`idea_votedown` (`Idea_id`, `votedDown_id`) VALUES ('6', '5');


INSERT INTO `eggsale_dev`.`comment` (`id`, `content`, `userId`, `ideaId`) VALUES ('1', 'Oui effectivement, il surtout avoir une base en Sécurité informatique', '1', '1');
INSERT INTO `eggsale_dev`.`comment` (`id`, `content`, `userId`, `ideaId`) VALUES ('2', 'Oui effectivement, il surtout avoir une base en Sécurité informatique et en Scipt', '2', '2');
INSERT INTO `eggsale_dev`.`comment` (`id`, `content`, `userId`, `ideaId`) VALUES ('3', 'Oui effectivement, il surtout avoir une base en technos Java et en archi répartie', '3', '3');
INSERT INTO `eggsale_dev`.`comment` (`id`, `content`, `userId`, `ideaId`) VALUES ('4', 'Oui effectivement, il surtout avoir une base en Finance et en Management Projet', '4', '4');
INSERT INTO `eggsale_dev`.`comment` (`id`, `content`, `userId`, `ideaId`) VALUES ('5', 'Oui effectivement, il surtout avoir une base en technos Java et en archi répartie', '5', '5');
INSERT INTO `eggsale_dev`.`comment` (`id`, `content`, `userId`, `ideaId`) VALUES ('6', 'Oui effectivement, il surtout avoir une base en script Python et en 3D', '1', '6');
INSERT INTO `eggsale_dev`.`comment` (`id`, `content`, `userId`, `ideaId`) VALUES ('7', 'Oui effectivement, il surtout avoir une base en théorie de language et en script', '2', '7');

// comment_voteup
INSERT INTO `eggsale_dev`.`comment_voteup` (`Comment_id`, `votedUp_id`) VALUES ('1', '1');
INSERT INTO `eggsale_dev`.`comment_voteup` (`Comment_id`, `votedUp_id`) VALUES ('2', '1');
INSERT INTO `eggsale_dev`.`comment_voteup` (`Comment_id`, `votedUp_id`) VALUES ('3', '1');
INSERT INTO `eggsale_dev`.`comment_voteup` (`Comment_id`, `votedUp_id`) VALUES ('1', '2');
INSERT INTO `eggsale_dev`.`comment_voteup` (`Comment_id`, `votedUp_id`) VALUES ('2', '2');
INSERT INTO `eggsale_dev`.`comment_voteup` (`Comment_id`, `votedUp_id`) VALUES ('3', '2');
INSERT INTO `eggsale_dev`.`comment_voteup` (`Comment_id`, `votedUp_id`) VALUES ('7', '2');
INSERT INTO `eggsale_dev`.`comment_voteup` (`Comment_id`, `votedUp_id`) VALUES ('2', '3');
INSERT INTO `eggsale_dev`.`comment_voteup` (`Comment_id`, `votedUp_id`) VALUES ('3', '3');
INSERT INTO `eggsale_dev`.`comment_voteup` (`Comment_id`, `votedUp_id`) VALUES ('4', '3');
INSERT INTO `eggsale_dev`.`comment_voteup` (`Comment_id`, `votedUp_id`) VALUES ('5', '3');
INSERT INTO `eggsale_dev`.`comment_voteup` (`Comment_id`, `votedUp_id`) VALUES ('6', '3');
INSERT INTO `eggsale_dev`.`comment_voteup` (`Comment_id`, `votedUp_id`) VALUES ('7', '3');



INSERT INTO `eggsale_dev`.`comment_votedown` (`Comment_id`, `votedDown_id`) VALUES ('4', '1');
INSERT INTO `eggsale_dev`.`comment_votedown` (`Comment_id`, `votedDown_id`) VALUES ('5', '1');
INSERT INTO `eggsale_dev`.`comment_votedown` (`Comment_id`, `votedDown_id`) VALUES ('6', '1');
INSERT INTO `eggsale_dev`.`comment_votedown` (`Comment_id`, `votedDown_id`) VALUES ('4', '2');
INSERT INTO `eggsale_dev`.`comment_votedown` (`Comment_id`, `votedDown_id`) VALUES ('5', '2');
INSERT INTO `eggsale_dev`.`comment_votedown` (`Comment_id`, `votedDown_id`) VALUES ('6', '2');
INSERT INTO `eggsale_dev`.`comment_votedown` (`Comment_id`, `votedDown_id`) VALUES ('4', '4');
INSERT INTO `eggsale_dev`.`comment_votedown` (`Comment_id`, `votedDown_id`) VALUES ('5', '4');
INSERT INTO `eggsale_dev`.`comment_votedown` (`Comment_id`, `votedDown_id`) VALUES ('6', '4');
INSERT INTO `eggsale_dev`.`comment_votedown` (`Comment_id`, `votedDown_id`) VALUES ('4', '5');
INSERT INTO `eggsale_dev`.`comment_votedown` (`Comment_id`, `votedDown_id`) VALUES ('5', '5');
INSERT INTO `eggsale_dev`.`comment_votedown` (`Comment_id`, `votedDown_id`) VALUES ('6', '5');


INSERT INTO `eggsale_dev`.`reward` (`id`, `amount`, `ideaId`) VALUES ('1', '200', '1');
INSERT INTO `eggsale_dev`.`reward` (`id`, `amount`, `ideaId`) VALUES ('2', '150', '2');
INSERT INTO `eggsale_dev`.`reward` (`id`, `amount`, `ideaId`) VALUES ('3', '230', '3');
INSERT INTO `eggsale_dev`.`reward` (`id`, `amount`, `ideaId`) VALUES ('4', '122', '4');
INSERT INTO `eggsale_dev`.`reward` (`id`, `amount`, `ideaId`) VALUES ('5', '100', '5');
INSERT INTO `eggsale_dev`.`reward` (`id`, `amount`, `ideaId`) VALUES ('6', '100', '6');
INSERT INTO `eggsale_dev`.`reward` (`id`, `amount`, `ideaId`) VALUES ('7', '90', '7');



