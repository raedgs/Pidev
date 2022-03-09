<?php

namespace App\Entity;

use App\Repository\PromotionRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=PromotionRepository::class)
 */
class Promotion
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="date")
     */
    private $dated;

    /**
     * @ORM\Column(type="date")
     */
    private $datef;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $description;

    /**
     * @ORM\Column(type="integer")
     */
    private $pourcentage;



    /**
     * @ORM\ManyToOne(targetEntity=Codecoupone::class, inversedBy="promotion")
     */
    private $codecoupone;



    /**
     * @ORM\OneToMany(targetEntity=Produit::class, mappedBy="promotion")
     */
    private $produit;



    public function __construct()
    {
        $this->produit = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }
    public function getDated(): ?\DateTimeInterface
    {
        return $this->dated;
    }

    public function setDated(\DateTimeInterface $dated): self
    {
        $this->dated = $dated;

        return $this;
    }

    public function getDatef(): ?\DateTimeInterface
    {
        return $this->datef;
    }

    public function setDatef(\DateTimeInterface $datef): self
    {
        $this->datef = $datef;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }





    public function getPourcentage(): ?int
    {
        return $this->pourcentage;
    }

    public function setPourcentage(int $pourcentage): self
    {
        $this->pourcentage = $pourcentage;

        return $this;
    }



    public function getCodecoupone(): ?Codecoupone
    {
        return $this->codecoupone;
    }

    public function setCodecoupone(?Codecoupone $codecoupone): self
    {
        $this->codecoupone = $codecoupone;

        return $this;
    }

    public function __toString()
    {
        return $this->Codecoupone;

    }




    /**
     * @return Collection|Produit[]
     */
    public function getProduit(): Collection
    {
        return $this->produit;
    }

    public function addProduit(Produit $produit): self
    {
        if (!$this->produit->contains($produit)) {
            $this->produit[] = $produit;
            $produit->setPromotion($this);
        }

        return $this;
    }

    public function removeProduit(Produit $produit): self
    {
        if ($this->produit->removeElement($produit)) {
            // set the owning side to null (unless already changed)
            if ($produit->getPromotion() === $this) {
                $produit->setPromotion(null);
            }
        }

        return $this;
    }


}
