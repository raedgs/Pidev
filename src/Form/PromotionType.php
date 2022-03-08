<?php

namespace App\Form;

use App\Entity\Codecoupone;
use App\Entity\Promotion;
use Doctrine\DBAL\Types\DateTimeType;
use Doctrine\DBAL\Types\TextType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Date;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;

class PromotionType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            /*->add('dated', DateType::class, [
                'label' => 'Date du stage',
                'html5'  => true,
                'view_timezone' => 'Europe/Paris',
                'years' => [
                    (new \DateTime())->format('Y'),
                    (new \DateTime())->add(new \DateInterval('P1Y'))->format('Y'),
                    (new \DateTime())->add(new \DateInterval('P2Y'))->format('Y'),
                    (new \DateTime())->add(new \DateInterval('P3Y'))->format('Y'),
                    (new \DateTime())->add(new \DateInterval('P4Y'))->format('Y'),
                ],
                "minutes" => [
                    '00',
                    '15',
                    '30',
                    '45'
                ]
            ])*/

            ->add('dated', DateType::class, array(
                'label' =>'Date debut',
                'format' => 'yyyy-MM-dd',
            ))
            ->add('datef',DateType::class, array(
                'label' =>'Date fin',
        'format' => 'yyyy-MM-dd',
    ))
            ->add('description',TextareaType::class,[
        'attr' => ['class' => 'form-control'],
        'label' =>'Description'] )
            ->add('pourcentage')
            ->add('codecoupone')
            ->add('save', SubmitType::class,[
                'attr'=>['class'=>'btn btn-block btn-dark btn-sm'],
                'label' => 'valider'])

        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Promotion::class,
        ]);
    }
}
