<?php

namespace App\Repository;

use App\Entity\Codecoupon;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Codecoupon|null find($id, $lockMode = null, $lockVersion = null)
 * @method Codecoupon|null findOneBy(array $criteria, array $orderBy = null)
 * @method Codecoupon[]    findAll()
 * @method Codecoupon[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CodecouponRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Codecoupon::class);
    }

    // /**
    //  * @return Codecoupon[] Returns an array of Codecoupon objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Codecoupon
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
