<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220309124113 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE codecoupone (id INT AUTO_INCREMENT NOT NULL, id_c INT NOT NULL, code VARCHAR(255) NOT NULL, pourcentage_p INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE commande (id INT AUTO_INCREMENT NOT NULL, montant NUMERIC(10, 0) NOT NULL, date DATE NOT NULL, qte INT NOT NULL, etat VARCHAR(20) DEFAULT \'encour\' NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE livraison (id INT AUTO_INCREMENT NOT NULL, date_livraison DATE NOT NULL, lieux VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE livraison_commande (livraison_id INT NOT NULL, commande_id INT NOT NULL, INDEX IDX_CD1650678E54FB25 (livraison_id), INDEX IDX_CD16506782EA2E54 (commande_id), PRIMARY KEY(livraison_id, commande_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE postlike (id INT AUTO_INCREMENT NOT NULL, post_id INT DEFAULT NULL, INDEX IDX_B84FD43A4B89032C (post_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE promotion (id INT AUTO_INCREMENT NOT NULL, codecoupone_id INT DEFAULT NULL, dated DATE NOT NULL, datef DATE NOT NULL, description VARCHAR(255) NOT NULL, pourcentage INT NOT NULL, vote INT NOT NULL, INDEX IDX_C11D7DD1F9D9603A (codecoupone_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reclamation (id INT AUTO_INCREMENT NOT NULL, type_id INT NOT NULL, description VARCHAR(255) NOT NULL, etat VARCHAR(255) NOT NULL, INDEX IDX_CE606404C54C8C93 (type_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE type_reclamation (id INT AUTO_INCREMENT NOT NULL, type VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE livraison_commande ADD CONSTRAINT FK_CD1650678E54FB25 FOREIGN KEY (livraison_id) REFERENCES livraison (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE livraison_commande ADD CONSTRAINT FK_CD16506782EA2E54 FOREIGN KEY (commande_id) REFERENCES commande (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE postlike ADD CONSTRAINT FK_B84FD43A4B89032C FOREIGN KEY (post_id) REFERENCES promotion (id)');
        $this->addSql('ALTER TABLE promotion ADD CONSTRAINT FK_C11D7DD1F9D9603A FOREIGN KEY (codecoupone_id) REFERENCES codecoupone (id)');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT FK_CE606404C54C8C93 FOREIGN KEY (type_id) REFERENCES type_reclamation (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE promotion DROP FOREIGN KEY FK_C11D7DD1F9D9603A');
        $this->addSql('ALTER TABLE livraison_commande DROP FOREIGN KEY FK_CD16506782EA2E54');
        $this->addSql('ALTER TABLE livraison_commande DROP FOREIGN KEY FK_CD1650678E54FB25');
        $this->addSql('ALTER TABLE postlike DROP FOREIGN KEY FK_B84FD43A4B89032C');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY FK_CE606404C54C8C93');
        $this->addSql('DROP TABLE codecoupone');
        $this->addSql('DROP TABLE commande');
        $this->addSql('DROP TABLE livraison');
        $this->addSql('DROP TABLE livraison_commande');
        $this->addSql('DROP TABLE postlike');
        $this->addSql('DROP TABLE promotion');
        $this->addSql('DROP TABLE reclamation');
        $this->addSql('DROP TABLE type_reclamation');
        $this->addSql('ALTER TABLE categorie CHANGE libelle_categorie libelle_categorie VARCHAR(255) DEFAULT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE produit CHANGE libelle libelle VARCHAR(255) DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description description VARCHAR(255) DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE image image VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE reset_password_request CHANGE selector selector VARCHAR(20) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE hashed_token hashed_token VARCHAR(100) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE service_av CHANGE nomdeproduit nomdeproduit VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description description VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE type_av CHANGE description description VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE nature nature VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE `user` CHANGE email email VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE roles roles LONGTEXT NOT NULL COLLATE `utf8mb4_unicode_ci` COMMENT \'(DC2Type:json)\', CHANGE password password VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE username username VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE image image VARCHAR(255) DEFAULT NULL COLLATE `utf8mb4_unicode_ci`');
    }
}
