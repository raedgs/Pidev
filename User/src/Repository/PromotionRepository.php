<?php

namespace App\Repository;

use App\Entity\Promotion;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Promotion|null find($id, $lockMode = null, $lockVersion = null)
 * @method Promotion|null findOneBy(array $criteria, array $orderBy = null)
 * @method Promotion[]    findAll()
 * @method Promotion[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class PromotionRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Promotion::class);
    }
    public function  nbrjr(){
        $entityManager=$this->getEntityManager();
        $query=$entityManager->createQuery('SELECT s FROM App\Entity\promotion s order by s.pourcentage  DESC');
        return $query->getResult();
    }
    public function  OrderByPourcentage(){
        return $this->createQueryBuilder('s')
            ->orderBy('s.pourcentage', 'ASC');
    }
    public function  like(){
        $entityManager=$this->getEntityManager();
        $query=$entityManager->createQuery('UPDATE s FROM App\Entity\promotion s set s.vote=vote+1 ');
        return $query->getResult();
    }
    public function  Likes(){
        return $this->createQueryBuilder('s')
            ->set('s.vote','s.vote++');
    }



    // /**
    //  * @return Promotion[] Returns an array of Promotion objects
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
    public function findOneBySomeField($value): ?Promotion
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
