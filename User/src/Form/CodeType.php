<?php

namespace App\Form;

use App\Entity\Codecoupone;
use App\Entity\Promotion;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class CodeType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('id_c',TextareaType::class,[
                'attr' => ['class' => 'form-control'],
                'label' =>'QR code'] )

            ->add('code',TextareaType::class,[
                'attr' => ['class' => 'form-control'],
                'label' =>'Code'] )
            ->add('pourcentage_p',TextareaType::class,[
                'attr' => ['class' => 'form-control'],
                'label' =>'getPourcentage'] )
            ->add('save', SubmitType::class,[
                'attr'=>['class'=>'btn btn-block btn-dark btn-sm'],
                'label' => 'valider'])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Codecoupone::class,
        ]);
    }
}
