<?php

namespace App\Repository;

use App\Entity\Produit;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Produit|null find($id, $lockMode = null, $lockVersion = null)
 * @method Produit|null findOneBy(array $criteria, array $orderBy = null)
 * @method Produit[]    findAll()
 * @method Produit[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ProduitRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Produit::class);
    }
    
    // /**
    //  * @return Produit[] Returns an array of Produit objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

   
    function SearchByLibelle($nsc)
    {
        return $this->createQueryBuilder('o')
            ->where ('o.Categorie = :categorie')
            ->setParameter('categorie',$nsc)
            ->getQuery()->getResult();
    }
   public function SearchNom($nsc)
    {
        return $this->createQueryBuilder('o')
            ->where ('o.Libelle LIKE :nsc')->orWhere('o.prix LIKE :nsc')
            ->setParameter('nsc','%'.$nsc.'%')
            ->getQuery()
            ->getResult();
    }

    public function findEntitiesByString($str)
    {

        $entityManager = $this->getEntityManager();

        $query = $entityManager->createQuery(
            'SELECT p
            FROM App\Entity\Produit p
            WHERE p.Libelle LIKE :str'

        )->setParameter('str', $str);

        // returns an array of Product objects
        return $query->getResult();
    }
}
    

