<?php

namespace App\Form;

use App\Entity\Produit;
use App\Entity\Categorie;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Validator\Constraints\Image;


class ProduitType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('Libelle' , TextType::class, [
                'attr' => [
                    'class' => 'custom_class'
                ]
            ])
            ->add('Description' , TextareaType::class, [
                'attr' => [
                    'class' => 'custom_class'
                ]
            ])

            ->add('Prix')
            ->add('Image', FileType::class, [
                'label' => 'Please upload your image' ,
                'mapped' => false,
                'required' => true,
                'constraints' => [
                    new Image([
                        'maxSize' => "10M",
                        'mimeTypes' => [
                            "image/jpeg",
                            "image/jpg",
                            "image/png",
                            "image/gif",
                        ],
                        'mimeTypesMessage' => 'Type image incorrecte',
                    ]),
                ],
            ])
            ->add('categorie', EntityType::class,
            array('class' =>Categorie::class,'choice_label' =>'LibelleCategorie'))

            ->add('save', SubmitType::class,
            ['label' => 'valider'])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Produit::class,
            'methode'=> 'GET',
            'csrf_protection'=> false
        ]);
    }


    public function findByPrix()
    {
        return $this->createQueryBuilder('e')
            ->orderBy('e.Prix','ASC')
            ->getQuery()
            ->getResult()
            ;
    }
}
