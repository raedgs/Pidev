<?php

namespace App\Entity;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
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



    /**
     * @ORM\OneToMany(targetEntity=Commande::class, mappedBy="produit")
     */
    private $commandes;

    /**
     * @ORM\OneToMany(targetEntity=PostL::class, mappedBy="post",orphanRemoval=true)
     */
    private $likes;


    /**
     * @ORM\OneToMany(targetEntity=PostDislike::class, mappedBy="post")
     */
    private $dislikes;

    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="produits")
     */
    private $user;

    /**
     * @ORM\ManyToOne(targetEntity=Promotion::class, inversedBy="produit")
     */
    private $promotion;


    public function __construct()
    {
        $this->likes = new ArrayCollection();
        $this->dislikes = new ArrayCollection();
    }

    public function ___construct()
    {
        $this->commandes = new ArrayCollection();
    }





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



    /**
     * @return Collection|Commande[]
     */
    public function getCommandes(): Collection
    {
        return $this->commandes;
    }

    public function addCommande(Commande $commande): self
    {
        if (!$this->commandes->contains($commande)) {
            $this->commandes[] = $commande;
            $commande->setProduit($this);
        }

        return $this;
    }

    public function removeCommande(Commande $commande): self
    {
        if ($this->commandes->removeElement($commande)) {
            // set the owning side to null (unless already changed)
            if ($commande->getProduit() === $this) {
                $commande->setProduit(null);
            }
        }

        return $this;
    }
    /**
     * @return Collection|PostL[]
     */
    public function getLikes(): Collection
    {
        return $this->likes;
    }


    public function addLike(PostL $like): self
    {
        if (!$this->likes->contains($like)) {
            $this->likes[] = $like;
            $like->setPost($this);
        }

        return $this;
    }

    /**
     * @return Collection|PostDislike[]
     */
    public function getdislikes(): Collection
    {
        return $this->dislikes;
    }

    public function addPostDislike(PostDislike $postDislike): self
    {
        if (!$this->dislikes->contains($postDislike)) {
            $this->dislikes[] = $postDislike;
            $postDislike->setPost($this);
        }

        return $this;
    }

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;

        return $this;
    }

    public function getPromotion(): ?Promotion
    {
        return $this->promotion;
    }

    public function setPromotion(?Promotion $promotion): self
    {
        $this->promotion = $promotion;

        return $this;
    }



    
}
