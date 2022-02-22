<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220220212004 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE codecoupone (id INT AUTO_INCREMENT NOT NULL, id_c INT NOT NULL, code VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE promotion ADD codecoupone_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE promotion ADD CONSTRAINT FK_C11D7DD1F9D9603A FOREIGN KEY (codecoupone_id) REFERENCES codecoupone (id)');
        $this->addSql('CREATE INDEX IDX_C11D7DD1F9D9603A ON promotion (codecoupone_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE promotion DROP FOREIGN KEY FK_C11D7DD1F9D9603A');
        $this->addSql('DROP TABLE codecoupone');
        $this->addSql('DROP INDEX IDX_C11D7DD1F9D9603A ON promotion');
        $this->addSql('ALTER TABLE promotion DROP codecoupone_id');
    }
}
