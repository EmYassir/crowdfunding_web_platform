CREATE USER 'eggsale'@'localhost' IDENTIFIED BY  'eggsale';

GRANT USAGE ON * . * TO  'eggsale'@'localhost' IDENTIFIED BY  'eggsale' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;

GRANT ALL PRIVILEGES ON  `eggsale\_%` . * TO  'eggsale'@'localhost';