<?php

namespace App\Repository;

use App\Entity\PostL;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method PostL|null find($id, $lockMode = null, $lockVersion = null)
 * @method PostL|null findOneBy(array $criteria, array $orderBy = null)
 * @method PostL[]    findAll()
 * @method PostL[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class PostLRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, PostL::class);
    }

    // /**
    //  * @return PostL[] Returns an array of PostL objects
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

    /*
    public function findOneBySomeField($value): ?PostL
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
