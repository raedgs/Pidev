<?php

namespace App\Entity;

use App\Repository\PostLRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=PostLRepository::class)
 */
class PostL
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @var \Produit
     *
     * @ORM\ManyToOne(targetEntity="Produit")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="post_id", referencedColumnName="idproduit")
     * })
     */
    private $post;


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getPost(): ?Produit
    {
        return $this->post;
    }

    public function setPost(?Produit $post): self
    {
        $this->post = $post;

        return $this;
    }

    public function getPostId(): ?int
    {
        return $this->postId;
    }

    public function setPostId(?int $postId): self
    {
        $this->postId = $postId;

        return $this;
    }






}
