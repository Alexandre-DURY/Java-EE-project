-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 02 mai 2021 à 20:10
-- Version du serveur :  10.4.18-MariaDB
-- Version de PHP : 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `tds`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(4) NOT NULL,
  `id_utilisateur` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`admin_id`, `id_utilisateur`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(100) NOT NULL,
  `Modele` varchar(100) NOT NULL,
  `Categorie` varchar(100) NOT NULL,
  `Description` varchar(5000) NOT NULL,
  `Etat` varchar(15) NOT NULL,
  `km` int(10) NOT NULL,
  `ct` tinyint(1) NOT NULL,
  `annee` int(4) NOT NULL,
  `prix_offre` int(11) NOT NULL,
  `prix_vente` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp(),
  `nom_vendeur` varchar(200) NOT NULL,
  `nom_utilisateur` varchar(200) NOT NULL,
  `visuel` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `transaction`
--

INSERT INTO `transaction` (`id`, `Modele`, `Categorie`, `Description`, `Etat`, `km`, `ct`, `annee`, `prix_offre`, `prix_vente`, `date`, `nom_vendeur`, `nom_utilisateur`, `visuel`) VALUES
(5, 'BMW M8', 'Sportive', 'Les BMW M8 atteignent le summum du plaisir de conduire et du luxe. Avec grande élégance, elles combinent leurs gènes M authentiques avec l’exclusivité la plus raffinée – pour communiquer au conducteur des sensations sportives sur route comme sur circuit. Elles se déclinent en deux versions aussi séduisantes l’une que l’autre : la BMW M850i xDrive Coupé séduit par la synthèse unique qu’elle établit entre confort, puissance et efficience et la BMW M8 Competition Coupé avec M xDrive représente le summum. Il s’agit en effet d\'une version encore plus puissante et extravagante de ce coupé élégant hautes performances.', 'Tres bon', 500, 1, 2019, 171000, 159000, '2021-05-01 14:31:19', 'BMW', 'administrateur', 'M8.jpg'),
(7, 'Mercedes GLC', 'SUV', 'Mercedes puissante', 'Mauvais', 1, 0, 2020, 6, 70000, '2021-05-01 15:13:47', 'administrateur', 'Audi', 'GLC.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `pseudo` varchar(200) NOT NULL,
  `mdp` varchar(200) NOT NULL,
  `id` int(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `date_creation` timestamp NOT NULL DEFAULT current_timestamp(),
  `photo` varchar(255) NOT NULL DEFAULT 'assets/photos/vendeurs/default.jpg',
  `nom` varchar(40) DEFAULT NULL,
  `prenom` varchar(40) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`pseudo`, `mdp`, `id`, `email`, `date_creation`, `photo`, `nom`, `prenom`, `description`) VALUES
('administrateur', 'root', 1, 'admin@root.fr', '2021-03-09 08:29:50', 'assets/photos/vendeurs/default.jpg', 'UZI', 'jack', 'Gérant du site depuis 2021.'),
('Audi', 'vendeur1', 4, 'vendeur1@gmail.com', '2021-03-22 09:27:54', 'assets/photos/vendeurs/logo4.jpg', 'PILOTE', 'Enna', 'Découvrez nos gammes de véhicules neufs et d\'occasion. Financement & réseau Audi. Nos gammes de véhicules. Offres de financement pour particuliers & professionels. Horaires adaptés. Visites sans contact. Contrôles sanitaires. Mois de loyers offerts.'),
('Mercedes', 'vendeur2', 5, 'vendeur2@gmail.com', '2021-03-22 09:27:54', 'assets/photos/vendeurs/logo1.jpg', 'ULTRA', 'Olivier', 'Le mariage du luxe, de la sportivité et de la performance. Berlines, breaks, coupés, cabriolets, roadsters, SUV et plus encore.'),
('BMW', 'vendeur3', 6, 'vendeur3@gmail.com', '2021-03-22 09:39:57', 'assets/photos/vendeurs/logo3.jpg', 'IPSEITE', 'Saibot', 'Découvrez tous les modèles BMW : Compacte, Citadine, Cabriolet, Berline, SUV, Coupé, Touring, Monospace. Des BMW disponibles en Essence, Diesel, ...'),
('ferrari', 'ferrari', 7, 'ferrari@gmail.com', '2021-05-02 17:53:35', 'assets/photos/vendeurs/default.jpg', 'Ferrari', 'Enzo', 'sacré voiture');

-- --------------------------------------------------------

--
-- Structure de la table `voiture`
--

CREATE TABLE `voiture` (
  `visuel` varchar(100) NOT NULL,
  `modele` varchar(100) NOT NULL,
  `categorie` varchar(100) NOT NULL,
  `etat` varchar(15) NOT NULL,
  `km` int(10) NOT NULL,
  `ct` tinyint(1) NOT NULL,
  `couleur` varchar(20) NOT NULL,
  `annee` int(4) NOT NULL,
  `description` varchar(5000) NOT NULL,
  `prix` int(20) NOT NULL,
  `id` int(255) NOT NULL,
  `id_utilisateur` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `voiture`
--

INSERT INTO `voiture` (`visuel`, `modele`, `categorie`, `etat`, `km`, `ct`, `couleur`, `annee`, `description`, `prix`, `id`, `id_utilisateur`) VALUES
('quattro.jpg', 'Audi A1 quattro', 'citadine', 'Neuf', 100, 1, 'blanche', 2017, 'L\'Audi A1 quattro arrivera en France en juillet. Elle sera vendue à 35 exemplaires, à 51 190 € l\'unité ! ... Mais l\'A1 quattro a le mérite d\'être la citadine la plus puissante du marché. Son quatre cylindres (2.0 TFSI) développe 256 ch quand celui d\'une MINI John Cooper Works en développe 45 de moins (211 ch).', 51190, 28, 4),
('classeE.jpg', 'Mercedes Classe E', 'Luxieuse', 'Neuf', 0, 1, 'noir', 2018, 'Le design vous séduira dès le premier regard. Le système d\'infodivertissement vous comprend comme un ami proche. Et le confort vous permet de profiter de chaque seconde de votre voyage. Parfait pour relever les défis du quotidien avec style, dynamisme et confort. Découvrez les points forts de la Nouvelle Classe E Coupé.', 64050, 29, 5),
('GLC.jpg', 'Mercedes GLC coupé', 'SUV', 'Bon', 50000, 1, 'noir', 2020, 'Plus sportif que jamais, le GLC Coupé attire à lui tous les regards. Grâce à ses jupes plus larges, à ses nouvelles sorties d\'échappement et aux projecteurs LED hautes performances montés pour la première fois de série.', 63100, 30, 5),
('bmw-x7.jpg', 'BMW X7 ', 'SUV', 'Moyen', 100000, 0, 'noir', 2018, 'La Nouvelle BMW X7 fusionne présence et personnalité de façon très élégante. Allure imposante, design épuré, silhouette athlétique. Dans le même temps, l habitacle spacieux offre une combinaison inédite de fonctionnalité et de liberté, doublée d’un niveau de confort incomparable grâce à sa troisième rangée de sièges.', 97000, 31, 6),
('quattro.jpg', 'Audi A1 quattro', 'citadine', 'Bon', 150000, 1, 'blanche', 2016, 'L\'audi A1 est incroyable. En effet, son 2.0L TFSI gagne 71 cv en optant pour la transmission intégrale, qui alourdi l\'auto.\r\nL\'intérieur se veut moins Clubsport que sur le concept et récupère ses 2 places arrière, mais il reçoit un traitement différent des versions normales.', 35000, 38, 4),
('classeE.jpg', 'Mercedes Classe E', 'Luxieuse', 'Mauvais', 200000, 0, 'noir', 2012, 'La Mercedes-Benz Classe E est une gamme d\'automobile routière du constructeur allemand Mercedes-Benz existant en berline, break, limousine, coupé et cabriolet. Cinq générations se succèdent, lancées en 1993 (Type 124) puis en 1995 (Type 210), en 2002 (Type 211), en 2009 (Type 212) et enfin en 2016 (Type 213). La première Classe E, datant de 1993 (Type 124 phase 3), remplace la Type 124 phase 2 des séries 200, 300, 400 et 500.', 50000, 39, 5),
('bmw-x7.jpg', 'BMW X7', 'SUV', 'Bon', 123336, 1, 'bordeaux', 2018, 'Le X7 est muni de la même planche de bord que le X5 avec une instrumentation numérique BMW Live Cockpit dotée d\'un écran tactile de 12,3 pouces de diagonale, que l\'on contrôle soit par gestes, par commande vocale, par la molette iDrive, ou par l\'écran tactile.', 89000, 40, 6),
('GLC.jpg', 'Mercedes GLC coupé', 'SUV', 'Moyen', 15631, 1, 'jaune', 2015, 'Les Mercedes-AMG GLC se distinguent par un gain visible et surtout tangible en matière d agilité et de dynamique transversale. Leur appartenance à la famille Mercedes-AMG est evidente tant sur le plan esthetique qu au niveau technique, impressionnant par leurs performances hors du commun.', 69000, 41, 4);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`),
  ADD KEY `id_utilisateur` (`id_utilisateur`);

--
-- Index pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `nom_utilisateur` (`nom_utilisateur`),
  ADD KEY `nom_vendeur` (`nom_vendeur`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `pseudo` (`pseudo`);

--
-- Index pour la table `voiture`
--
ALTER TABLE `voiture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `utilisateur_id` (`id_utilisateur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `voiture`
--
ALTER TABLE `voiture`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`nom_utilisateur`) REFERENCES `utilisateurs` (`pseudo`),
  ADD CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`nom_vendeur`) REFERENCES `utilisateurs` (`pseudo`);

--
-- Contraintes pour la table `voiture`
--
ALTER TABLE `voiture`
  ADD CONSTRAINT `voiture_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateurs` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
