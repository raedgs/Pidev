<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Entity\Promotion;
use App\Form\CategorieType;
use App\Form\PromotionType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\PromotionRepository;

class PromotionController extends AbstractController
{
    /**
     * @Route("/promotion", name="promotion")
     */
    public function index(): Response
    {
        return $this->render('promotion/index.html.twig', [
            'controller_name' => 'PromotionController',
        ]);
    }
    /**
     * *@Route ("/addPromotion", name="addPromotion")
     */
    public function addPromotion(Request $request): Response
    {
        $promotion = new Promotion();
        $form = $this->createForm(PromotionType::class, $promotion);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($promotion);
            $entityManager->flush();

            return $this->redirectToRoute('affiche');
        }
        return $this->render('promotion/addPromotion.html.twig', [
            'promotion' => $promotion,
            'formC' => $form->createView(),
        ]);
    }
    /**
     * @Route("promotion/affiche/{id}", name="edit")
     */
    /**public function Edit(PromotionRepository $repository,$id, Request $request ):response
    {
        $Promotion=$repository->find($id);
        $form=$this->createForm(PromotionType::class, $Promotion);
        $form->add('update',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid() )
        {
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('affiche');
        }
        return $this->render('promotion/addPromotion.html.twig',[

            'form' =>$form->createView()
        ]);
    }**/
    /**
     * @Route("/affiche/{id}", name="dd")
     */
    public function delete($id)
    {
        $em = $this->getDoctrine()->getManager();
        $promotion = $this->getDoctrine()->getRepository(Promotion::class)->find($id);
        $em->remove($promotion);
        $em->flush();
        return $this->render('promotion/affiche.html.twig',
            ['promotion'=>$promotion]);

    }
    /**
     * @Route("/update/{id}", name="u")
     */
    /**function Update($id,PromotionRepository $repository,Request $request){
        $Promotion = $repository->find($id);
        $form=$this->createForm(PromotionType::class,$Promotion);
        $form->add('Update',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->flush();
        }
        return $this->render('promotion/addPromotion.html.twig',
            ['promotion'=>$Promotion]
            );
    }
    **/
    /**
     * @Route("front/testmaker/test/edittest/{id}", name="u")
     */
   /** public function edit(PromotionRepository $repository,$id, Request $request ):response
    {
        $promotion=$repository->find($id);

        $form=$this->createForm(PromotionType::class, $promotion);
        $form->add('update',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted()  )
        {
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('affiche');
        }
        return $this->render('promotion/edit.html.twig',[

            'form' =>$form->createView()
        ]);
    }
**/
    /**
     * @Route("/Supp/{id}", name="d")
     */

    function remove($id,PromotionRepository $repository)
    {
        $promotion = $repository->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($promotion);
        $em->flush();
        return $this->render('promotion/affiche.html.twig',
            ['promotion'=>$promotion]);
    }

/**
 * @Route("/", name="home")
 */
public function base(): Response
{
    return $this->render('/front/base.html.twig');
}
    /**
     * @Route("/fronti", name="fronti")
     */
    public function fronti(): Response
    {
        $repo=$this->getDoctrine()->getRepository(Promotion::class);
        $promotion=$repo->findAll();
        return $this->render('front.html.twig',
            ['promotion'=>$promotion]);
    }

    /**
     * @Route ("/affiche", name="affiche")
     */
    public function affiche(){
        $repo=$this->getDoctrine()->getRepository(Promotion::class);
        $promotion=$repo->findAll();
        return $this->render('promotion/affiche.html.twig',
        ['promotion'=>$promotion]);
    }
    /**
     * @Route ("/affichage", name="affichage")
     */
    public function affichage(){
        $repo=$this->getDoctrine()->getRepository(Promotion::class);
        $promotion=$repo->findAll();
        return $this->render('promotion/affichage.html.twig',
            ['promotion'=>$promotion]);
    }
    /**
     * @Route ("/affichage", name="edit")
     */
    public function editt(){
        $repo=$this->getDoctrine()->getRepository(Promotion::class);
        $promotion=$repo->findAll();
        return $this->render('promotion/suppression.html.twig',
            ['promotion'=>$promotion]);
    }
    /**
     * @Route("/addPromotion", name="arja")
     */
    public function arja(): Response
    {
        return $this->render('promotion/affiche.html.twig' );
    }
    /**
     * @Route("/affiche2", name="affiche2")
     */
    public function affichee(){
        $repo=$this->getDoctrine()->getRepository(Promotion::class);
        $promotion=$repo->findAll();
        return $this->render('promotion/affiche2.html.twig',
            ['promotion'=>$promotion]);
    }
/**
 * @Route("/index-2", name="index-2")
 */
public function index2(): Response
{
    return $this->render('/front/index-2.html.twig');
}
/**
 * @Route("/index-3", name="index-3")
 */
public function index3(): Response
{
    return $this->render('/front/index-3.html.twig');
}

/**
 * @Route("/back", name="back")
 */
public function back(): Response
{
    return $this->render('/back/index.html.twig');
}
/**
 * @Route("/login_admin", name="login_admin")
 */
public function login_admin(): Response
{
    return $this->render('/user/login-v2.html.twig');
}
/**
 * @Route("/register_admin", name="register_admin")
 */
public function register_admin(): Response
{
    return $this->render('/user/register-v2.html.twig');
}
/**
 * @Route("/forgot_password_admin", name="forgot_password_admin")
 */

public function forgot_password_admin(): Response
{
    return $this->render('/user/forgot-password-v2.html.twig');
}

}
