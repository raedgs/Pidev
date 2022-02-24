<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use App\Repository\TypeAVRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TypeAVRepository::class)
 */
class TypeAV
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     *  @Assert\Length(
     *      min = 10,
     *      max = 100,
     *      minMessage = "Your description must be at least {{ limit }} characters long",
     *      maxMessage = "Your description cannot be longer than {{ limit }} characters"
     * )
     */
    private $Description;

    /**
     * @ORM\Column(type="string", length=255)
     *    @Assert\NotBlank
     */
    private $nature;

    /**
     * @ORM\OneToMany(targetEntity=ServiceAV::class, mappedBy="typeAV", orphanRemoval=true)
     */
    private $ServiceAV;

    public function __construct()
    {
        $this->ServiceAV = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDescription(): ?string
    {
        return $this->Description;
    }

    public function setDescription(string $Description): self
    {
        $this->Description = $Description;

        return $this;
    }

    public function getNature(): ?string
    {
        return $this->nature;
    }

    public function setNature(string $nature): self
    {
        $this->nature = $nature;

        return $this;
    }

    /**
     * @return Collection|ServiceAV[]
     */
    public function getServiceAV(): Collection
    {
        return $this->ServiceAV;
    }

    public function addServiceAV(ServiceAV $serviceAV): self
    {
        if (!$this->ServiceAV->contains($serviceAV)) {
            $this->ServiceAV[] = $serviceAV;
            $serviceAV->setTypeAV($this);
        }

        return $this;
    }

    public function removeServiceAV(ServiceAV $serviceAV): self
    {
        if ($this->ServiceAV->removeElement($serviceAV)) {
            // set the owning side to null (unless already changed)
            if ($serviceAV->getTypeAV() === $this) {
                $serviceAV->setTypeAV(null);
            }
        }

        return $this;

    }
    public function __toString() {
        return $this->nature;
    }
}
