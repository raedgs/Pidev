<?php

namespace App\Controller;

use App\Entity\Codecoupone;
use App\Entity\Promotion;
use App\Form\CodeType;
use App\Form\PromotionType;
use App\Repository\CodecouponeRepository;
use App\Repository\PromotionRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
class CodeCouponeController extends AbstractController
{
    /**
     * @Route("/code/coupone", name="code_coupone")
     */
    public function index(): Response
    {
        return $this->render('/index.html.twig', [
            'controller_name' => 'CodeCouponeController',
        ]);
    }
    /**
     * @Route ("/addCode", name="addCode")
     */
    public function addCode(Request $request): Response
    {
        $codecoupone = new Codecoupone();
        $form = $this->createForm(CodeType::class, $codecoupone);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($codecoupone);
            $entityManager->flush();
            return $this->redirectToRoute('addCode');
        }
        return $this->render('code_coupone/addCode.html.twig', [
            'codecoupone' => $codecoupone,
            'formC' => $form->createView(),
        ]);
    }
    /**
     * @Route ("/affichec", name="affichec")
     */
    public function affiche (){
        $repo=$this->getDoctrine()->getRepository(Codecoupone::class);
        $codecoupone=$repo->findAll();
        return $this->render('code_coupone/afficheC.html.twig',
            ['codecoupone'=>$codecoupone]);
    }
    /**
     * @Route ("/affichecc", name="affichecc")
     */
    public function affichec (){
        $repo=$this->getDoctrine()->getRepository(Codecoupone::class);
        $codecoupone=$repo->findAll();
        return $this->render('code_coupone/del.html.twig',
            ['codecoupone'=>$codecoupone]);
    }
    /**
     * @Route ("/vieww", name="vieww")
     */
    public function view (){
        $repo=$this->getDoctrine()->getRepository(Codecoupone::class);
        $codecoupone=$repo->findAll();
        return $this->render('code_coupone/view.html.twig',
            ['codecoupone'=>$codecoupone]);
    }
    /**
     * @Route("code_coupone/afficheC/{id}", name="ex")
     */
 public function Edittt(CodecouponeRepository $repository,$id, Request $request ):response
    {
        $codecoupone=$repository->find($id);
        $form=$this->createForm(CodeType::class, $codecoupone);
        $form->add('update',SubmitType::class
            ,[
                'attr'=>['class'=>'btn btn-primary btn-sm']]
        );
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('addCode');
        }
        return $this->render('code_coupone/addCode.html.twig',[

            'formC' =>$form->createView()
        ]);
    }

    /**
     * @Route("afficheC/{id}", name="cx")
     */
    function remove ($id,CodecouponeRepository $repository)
    {
        $codecoupone = $repository->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($codecoupone);
        $em->flush();
        return $this->render('code_coupone/afficheC.html.twig',
            ['codecoupone'=>$codecoupone]);
    }
    public function __toString()
    {
        return $this->codecoupone;
    }

}
