<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;


/**
 * @ORM\Entity(repositoryClass=UserRepository::class)
 * @ORM\Table(name="`user`")
 * @UniqueEntity(fields={"email"}, message="There is already an account with this email")
 */
class User implements UserInterface
{
    const ROLE_ADMIN = 'ROLE_ADMIN';
    const ROLE_VENDEUR = 'ROLE_VENDEUR';
    const ROLE_BLOCKED = 'ROLE_BLOCKED';


    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255, unique=true)
     * @Assert\Email(
     *     message="l adresse  n est pas valide")
     */
    private $email;

    /**
     * @ORM\Column(type="json")
     */
    private $roles = [];

    /**
     * @var string The hashed password
     * @ORM\Column(type="string")
     *  @Assert\Length(
     * min = 8,
     * minMessage = "Votre mot de passe doit comporter au minimum {{ limit }} caractères")
     */
    private $password;

    /**
     * @Assert\EqualTo(propertyPath = "password",
     * message="Vous n'avez pas saisi le même mot de passe !" )
     */
    private $confirm_password;

    /**
     * @ORM\Column(type="string", length=255, unique=true)
     * @Assert\Length(
     * min = 2,
     * max = 20,
     * minMessage = "Votre nom doit contenir au moins {{limit }} caracteres",
     * maxMessage = "Votre nom doit contenir au plus {{limit }} caracteres",
     * allowEmptyString = false
     * )
     * @Assert\Type(
     * type={"alpha", "digit"},
     * message="Votre nom {{ value }} doit contenir
    seulement des lettres alphabétiques"
     * )
     */
    private $username;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $image;

    /**
     * @ORM\Column(type="boolean")
     */
    private $isVerified = false;


    /**
     * @ORM\OneToMany(targetEntity=ServiceAV::class, mappedBy="user")
     */
    private $serviceAVs;

    /**
     * @ORM\OneToMany(targetEntity=Reclamation::class, mappedBy="user")
     */
    private $reclamations;

    /**
     * @ORM\OneToMany(targetEntity=Produit::class, mappedBy="user")
     */
    private $produits;

    public function __construct()
    {
        $this->produits = new ArrayCollection();
        $this->serviceAVs = new ArrayCollection();
        $this->reclamations = new ArrayCollection();
    }


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    /**
     * A visual identifier that represents this user.
     *
     * @see UserInterface
     */
    public function getUsername(): string
    {
        return (string) $this->username;
    }

    /**
     * @see UserInterface
     */
    public function getRoles(): array
    {
        $roles = $this->roles;
        // guarantee every user at least has ROLE_USER // na7it $roles[] = 'ROLE_USER';  5atr ya3mel duplication
        $roles[] = '';
        return array_unique($roles);
    }

    public function setRoles(array $roles): self
    {
        $this->roles = $roles;

        return $this;
    }

    /**
     * @see UserInterface
     */
    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    /**
     * Returning a salt is only needed, if you are not using a modern
     * hashing algorithm (e.g. bcrypt or sodium) in your security.yaml.
     *
     * @see UserInterface
     */
    public function getSalt(): ?string
    {
        return null;
    }

    /**
     * @see UserInterface
     */
    public function eraseCredentials()
    {
        // If you store any temporary, sensitive data on the user, clear it here
        // $this->plainPassword = null;
    }

    public function setUsername(string $username): self
    {
        $this->username = $username;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(?string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getConfirmPassword(): ?string
    {
        return $this->confirm_password;
    }

    public function setConfirmPassword(string $confirm_password): self
    {
        $this->confirm_password = $confirm_password;

        return $this;
    }
    public function isAdmin(): bool
    {
        return in_array(self::ROLE_ADMIN, $this->getRoles());
    }
    public function isVendeur(): bool
    {
        return in_array(self::ROLE_VENDEUR, $this->getRoles());
    }
    public function isBlocked(): bool
    {
        return in_array(self::ROLE_BLOCKED, $this->getRoles());
    }

    public function isVerified(): bool
    {
        return $this->isVerified;
    }

    public function setIsVerified(bool $isVerified): self
    {
        $this->isVerified = $isVerified;

        return $this;
    }



    /**
     * @return Collection|ServiceAV[]
     */
    public function getServiceAVs(): Collection
    {
        return $this->serviceAVs;
    }

    public function addServiceAV(ServiceAV $serviceAV): self
    {
        if (!$this->serviceAVs->contains($serviceAV)) {
            $this->serviceAVs[] = $serviceAV;
            $serviceAV->setUser($this);
        }

        return $this;
    }

    public function removeServiceAV(ServiceAV $serviceAV): self
    {
        if ($this->serviceAVs->removeElement($serviceAV)) {
            // set the owning side to null (unless already changed)
            if ($serviceAV->getUser() === $this) {
                $serviceAV->setUser(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Reclamation[]
     */
    public function getReclamations(): Collection
    {
        return $this->reclamations;
    }

    public function addReclamation(Reclamation $reclamation): self
    {
        if (!$this->reclamations->contains($reclamation)) {
            $this->reclamations[] = $reclamation;
            $reclamation->setUser($this);
        }

        return $this;
    }

    public function removeReclamation(Reclamation $reclamation): self
    {
        if ($this->reclamations->removeElement($reclamation)) {
            // set the owning side to null (unless already changed)
            if ($reclamation->getUser() === $this) {
                $reclamation->setUser(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Produit[]
     */
    public function getProduits(): Collection
    {
        return $this->produits;
    }

    public function addProduit(Produit $produit): self
    {
        if (!$this->produits->contains($produit)) {
            $this->produits[] = $produit;
            $produit->setUser($this);
        }

        return $this;
    }

    public function removeProduit(Produit $produit): self
    {
        if ($this->produits->removeElement($produit)) {
            // set the owning side to null (unless already changed)
            if ($produit->getUser() === $this) {
                $produit->setUser(null);
            }
        }

        return $this;
    }
}
