<?php

namespace App\Repository;

use App\Entity\ServiceAV;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method ServiceAV|null find($id, $lockMode = null, $lockVersion = null)
 * @method ServiceAV|null findOneBy(array $criteria, array $orderBy = null)
 * @method ServiceAV[]    findAll()
 * @method ServiceAV[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ServiceAVRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, ServiceAV::class);
    }

    // /**
    //  * @return ServiceAV[] Returns an array of ServiceAV objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('s.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?ServiceAV
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */

    function diag()
    {
        $entityManager=$this->getEntityManager();
        $query=$entityManager->createQuery('SELECT count(a) from App\Entity\ServiceAV a WHERE (a.typeAV = :type) ')->setParameter('type', '2');
        return $query->getSingleScalarResult();
    }
    function repa()
    {
        $entityManager=$this->getEntityManager();
        $query=$entityManager->createQuery('SELECT count(a) from App\Entity\ServiceAV a WHERE (a.typeAV = :type) ')->setParameter('type', '3');
        return $query->getSingleScalarResult();
    }
    function affi()
    {
        $entityManager=$this->getEntityManager();
        $query=$entityManager->createQuery('SELECT count(a) from App\Entity\ServiceAV a WHERE (a.typeAV = :type) ')->setParameter('type', '4');
        return $query->getSingleScalarResult();
    }
}
