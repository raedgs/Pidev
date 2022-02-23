<?php

namespace App\Entity;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use App\Repository\ProduitRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * @ORM\Entity(repositoryClass=ProduitRepository::class)
 */
class Produit
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $idproduit;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     *@Assert\NotBlank(message="libelle obligatoire")
     */
    private $Libelle;
    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     *@Assert\NotBlank(message="description obligatoire")
     */
    private $Description;


    /**
     * @ORM\Column(type="float", nullable=true)
     * @Assert\NotBlank(message="prix obligatoire")
     */
    private $prix;

    /**
     * @ORM\Column(type="string", length=255, nullable=false)
     */
    private $image;

    /**
     * @ORM\ManyToOne(targetEntity=Categorie::class, inversedBy="Produit")
     * * @Assert\NotBlank(message="categorie du produit obligatoire")
     */
    private $categorie;


    public function getIdproduit(): ?int
    {
        return $this->idproduit;
    }

    public function getLibelle(): ?string
    {
        return $this->Libelle;
    }

    public function setLibelle(?string $Libelle): self
    {
        $this->Libelle = $Libelle;

        return $this;
    }
    public function getDescription(): ?string
    {
        return $this->Description;
    }

    public function setDescription(?string $Description): self
    {
        $this->Description= $Description;

        return $this;
    }
    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(?float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getCategorie(): ?Categorie
    {
        return $this->categorie;
    }

    public function setCategorie(?Categorie $categorie): self
    {
        $this->categorie = $categorie;

        return $this;
    }
}
