<?php

namespace App\Repository;

use App\Entity\Codecoupone;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Codecoupone|null find($id, $lockMode = null, $lockVersion = null)
 * @method Codecoupone|null findOneBy(array $criteria, array $orderBy = null)
 * @method Codecoupone[]    findAll()
 * @method Codecoupone[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CodecouponeRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Codecoupone::class);
    }

    // /**
    //  * @return Codecoupone[] Returns an array of Codecoupone objects
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
    public function findOneBySomeField($value): ?Codecoupone
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
