<?php

namespace App\Entity;

use App\Repository\CommandeRepository;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\ORM\Mapping\JoinColumn;
use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=CommandeRepository::class)
 * @method getCommandemontant()
 */
class Commande
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    private $id;

    /**
     * @ORM\Column(type="decimal", precision=10, scale=0)
     *  @Groups("post:read")
     * @Assert\NotBlank(message="montant obligatoire")

     */
    private $montant;

    /**
     * @ORM\Column(type="date")
     *  @Groups("post:read")
     */
    private $date;

    /**
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     * @Assert\NotBlank(message="quantiter obligatoire")
     */
    private $qte;

    /**
     * @ORM\Column(type="string", length=20, options={"default" : "encour"})
     */
    private $etat;

    /**
     * @ORM\ManyToOne(targetEntity=Produit::class, inversedBy="commandes")
     * @JoinColumn(name="idproduit", referencedColumnName="idproduit")
     */
    private $produit;


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getMontant(): ?string
    {
        return $this->montant;
    }

    public function setMontant(string $montant): self
    {
        $this->montant = $montant;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }
    public function getQte(): ?int
    {
        return $this->qte;
    }
    public function setQte(int $qte): self
    {
        $this->qte = $qte;

        return $this;
    }

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): self
    {
        $this->etat = $etat;

        return $this;
    }
    public function __toString()
    {
        return $this->montant;
    }

    public function getProduit(): ?Produit
    {
        return $this->produit;
    }

    public function setProduit(?Produit $produit): self
    {
        $this->produit = $produit;

        return $this;
    }



}
