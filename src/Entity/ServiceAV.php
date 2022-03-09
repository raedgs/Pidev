<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use App\Repository\ServiceAVRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ServiceAVRepository::class)
 */
class ServiceAV
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     *  @Assert\NotBlank(message="obligatoire")
     */
    private $Nomdeproduit;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(
     *      min = 10,
     *      max = 100,
     *      minMessage = "Your Description must be at least {{ limit }} characters long",
     *      maxMessage = "Your Description cannot be longer than {{ limit }} characters"
     * )
     */
    private $Description;

    /**
     * @ORM\ManyToOne(targetEntity=TypeAV::class, inversedBy="ServiceAV")
     * @ORM\JoinColumn(nullable=false)
     */
    private $typeAV;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomdeproduit(): ?string
    {
        return $this->Nomdeproduit;
    }

    public function setNomdeproduit(string $Nomdeproduit): self
    {
        $this->Nomdeproduit = $Nomdeproduit;

        return $this;
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

    public function getTypeAV(): ?TypeAV
    {
        return $this->typeAV;
    }

    public function setTypeAV(?TypeAV $typeAV): self
    {
        $this->typeAV = $typeAV;

        return $this;
    }
}
