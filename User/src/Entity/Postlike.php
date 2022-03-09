<?php

namespace App\Entity;

use App\Repository\PostlikeRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=PostlikeRepository::class)
 */
class Postlike
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity=promotion::class, inversedBy="likes")
     */
    private $post;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getPost(): ?promotion
    {
        return $this->post;
    }

    public function setPost(?promotion $post): self
    {
        $this->post = $post;

        return $this;
    }
}
