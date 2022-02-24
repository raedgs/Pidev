<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220222181906 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE service_av (id INT AUTO_INCREMENT NOT NULL, type_av_id INT NOT NULL, nomdeproduit VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, INDEX IDX_D63C52A6BB28CD75 (type_av_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE type_av (id INT AUTO_INCREMENT NOT NULL, description VARCHAR(255) NOT NULL, nature VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE service_av ADD CONSTRAINT FK_D63C52A6BB28CD75 FOREIGN KEY (type_av_id) REFERENCES type_av (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE service_av DROP FOREIGN KEY FK_D63C52A6BB28CD75');
        $this->addSql('DROP TABLE service_av');
        $this->addSql('DROP TABLE type_av');
    }
}
