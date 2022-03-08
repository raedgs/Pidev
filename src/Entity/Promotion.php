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
     * @ORM\Column(type="integer")
     */
    private $vote;


    /**
     * @ORM\ManyToOne(targetEntity=Codecoupone::class, inversedBy="promotion")
     */
    private $codecoupone;

    /**
     * @ORM\OneToMany(targetEntity=Postlike::class, mappedBy="post")
     */
    private $likes;

    public function __construct()
    {
        $this->likes = new ArrayCollection();
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
    public function getVote(): ?int
    {
        return $this->vote;
    }

    public function setVote(int $vote): self
    {
        $this->vote = $vote;

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
     * @return Collection<int, Postlike>
     */
    public function getLikes(): Collection
    {
        return $this->likes;
    }
    public function setLikes(?Likes $likes): self
    {
        $this->likes = $likes;

        return $this;
    }


    public function addLike(Postlike $like): self
    {
        if (!$this->likes->contains($like)) {
            $this->likes[] = $like;
            $like->setPost($this);
        }

        return $this;
    }

    public function removeLike(Postlike $like): self
    {
        if ($this->likes->removeElement($like)) {
            // set the owning side to null (unless already changed)
            if ($like->getPost() === $this) {
                $like->setPost(null);
            }
        }

        return $this;
    }

}
