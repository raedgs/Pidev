<?php

namespace App\Entity;

use App\Repository\CodecouponeRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CodecouponeRepository::class)
 */
class Codecoupone
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     */
    private $id_c;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $code;
    /**
     * @ORM\Column(type="integer")
     */
    private $pourcentage_p;

    /**
     *
     * @ORM\OneToMany(targetEntity=Promotion::class, mappedBy="codecoupone")
     */
    private $promotion;

    public function __construct()
    {
        $this->promotion = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdC(): ?int
    {
        return $this->id_c;
    }

    public function setIdC(int $id_c): self
    {
        $this->id_c = $id_c;

        return $this;
    }

    public function getCode(): ?string
    {
        return $this->code;
    }

    public function setCode(string $code): self
    {
        $this->code = $code;

        return $this;
    }
    public function getPourcentageP(): ?int
    {
        return $this->pourcentage_p;
    }

    public function setPourcentageP(int $pourcentage_p): self
    {
        $this->pourcentage_p = $pourcentage_p;

        return $this;
    }


    /**
     * @return Collection<int, Promotion>
     */
    public function getPromotion(): Collection
    {
        return $this->promotion;
    }

    public function addPromotion(Promotion $promotion): self
    {
        if (!$this->promotion->contains($promotion)) {
            $this->promotion[] = $promotion;
            $promotion->setCodecoupone($this);
        }
        return $this;
    }
    public function removePromotion(Promotion $promotion): self
    {
        if ($this->promotion->removeElement($promotion)) {
            // set the owning side to null (unless already changed)
            if ($promotion->getCodecoupone() === $this) {
                $promotion->setCodecoupone(null);
            }
        }

        return $this;
    }
    public function __toString()
    {
        return $this->code;
    }
}
