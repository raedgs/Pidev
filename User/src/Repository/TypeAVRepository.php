<?php

namespace App\Repository;

use App\Entity\TypeAV;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method TypeAV|null find($id, $lockMode = null, $lockVersion = null)
 * @method TypeAV|null findOneBy(array $criteria, array $orderBy = null)
 * @method TypeAV[]    findAll()
 * @method TypeAV[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class TypeAVRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, TypeAV::class);
    }

    // /**
    //  * @return TypeAV[] Returns an array of TypeAV objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('t')
            ->andWhere('t.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('t.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?TypeAV
    {
        return $this->createQueryBuilder('t')
            ->andWhere('t.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
