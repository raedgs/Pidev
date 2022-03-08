<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Entity\Promotion;
use App\Form\CategorieType;
use App\Form\PromotionType;
use phpDocumentor\Reflection\Types\True_;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\PromotionRepository;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Symfony\Component\Validator\Constraints\DateTime;

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
     * @Route("/erreur", name="erreur")
     */
    public function erreur (Request $request, AuthenticationUtils $authUtils)
    {
        $error = $authUtils->getLastAuthenticationError();

        $promotion = $authUtils->getCodecoupone();

        return $this->render('promotion/security.html.twig', [
            'promotion' => $promotion,
            'error' => $error,
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
     * @Route("promotion/affiche/{id}", name="u")
     */
    public function Edit(PromotionRepository $repository,$id, Request $request ):response
    {
        $Promotion=$repository->find($id);
        $form=$this->createForm(PromotionType::class, $Promotion);
        $form->add('update',SubmitType::class
            ,[
                'attr'=>['class'=>'btn btn-primary btn-sm']]
        );
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid() )
        {
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('affiche');
        }
        return $this->render('promotion/addPromotion.html.twig',[

            'formC' =>$form->createView()
        ]);
    }
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
     * @Route("/nbrj", name="nombre")
     */
public function sql ($id,PromotionRepository $repository):Response
{
    $promotion = $repository->find($id);
        $sql = "SELECT DATEDIFF( datef, dated );";
        $post = $promotion['db']->fetchAll($sql);
        print_r($post);
        return $promotion['twig']->render('promotion/affiche.html.twig',
        ['promotion'=>$promotion]
            , array("nombre" => $promotion));

    }

    /**
     * @Route("/view", name="view")
     */
    public function view(): Response
    {
        $repo=$this->getDoctrine()->getRepository(Promotion::class);
        $promotion=$repo->findAll();
        return $this->render('promotion/vieww.html.twig',
            ['promotion'=>$promotion]);
    }
    /**
 * @Route("/security", name="security")
 */
    public function security(): Response
    {
        $repo=$this->getDoctrine()->getRepository(Promotion::class);
        $promotion=$repo->findAll();
        return $this->render('promotion/security.html.twig',
            ['promotion'=>$promotion]);
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
     * @Route ("/calendary", name="calendary")
     */
    public function calendary(){
        $repo=$this->getDoctrine()->getRepository(Promotion::class);
        $promotion=$repo->findAll();
        return $this->render('promotion/call.html.twig',
            ['promotion'=>$promotion]);
    }



    /**
     * @Route ("/afficheE",name ="afficheE")
     */
    public function affichePromo(PromotionRepository $repository)
    {
        $nbrj = $repository->findAll();
        $promotion = [];
        $i = 0;
        foreach ($nbrj as $e) {
            $dated = $e->getDated()->format('d/m/Y');
            $datef = $e->getDatef()->format('d/m/Y');
            $variable1 = new DateTime($dated);
            $variable2 = new DateTime($datef);
         $difference=date_diff($e->getDated(),$e->getDatef())->format("%y Année");
         $difference1 = date_diff($e->getDated(), $e->getDatef())->format("%m Mois ");
            $difference2 =date_diff($e->getDated(), $e->getDatef())->format(" %d Jours Restants");
            //  %m months,and %h hours
            $promotion[$i]["id"] = $e->getId();
            $promotion[$i]["pourcentage"] = $e->getPourcentage();
            $promotion[$i]["dated"] = $e->getDated();
            $promotion[$i]["datef"] = $e->getDatef();
            $promotion[$i]["difference"] = $difference;
            $promotion[$i]["difference1"] = $difference1;
            $promotion[$i]["difference2"] = $difference2;
            $promotion[$i]["Description"] = $e->getDescription();
            $i++;
        }
        return $this->render('promotion/affiche2.html.twig',
            ['promotion'=>$nbrj]);

    }
    public function  nbrjr(){
        $entityManager=$this->getEntityManager();
        $query=$entityManager
            ->createQuery('SELECT DATEDIFF( dated, datef)');
        return $query->getSingleScalarResult();
    }
    /**
     * @param PromotionRepository $repository
     * @return Response
     * @Route ("/affiche2/nbrj",name="trie")
     */
    public function nbrj(PromotionRepository $repository){
        $promotion = $repository->nbrjr();
        return $this->render('promotion/affiche2.html.twig',
        ['promotion' => $promotion]);
    }
    /**
     * @param PromotionRepository $repository
     * @return Response
     * @Route ("/affiche2/nbrj",name="like")
     */
    public function nombre(PromotionRepository $repository){
        $promotion = $repository->like();
        return $this->render('promotion/affiche2.html.twig',
            ['promotion' => $promotion]);
    }
    /**
     * @Route ("affiche2/recherche",name="recherche")
     */
function Recherche (PromotionRepository $repository , request $request){
    $data=$request->get('search');
    $promotion=$repository->findBy(['pourcentage'=>$data]);
    return $this->render('promotion/affiche2.html.twig',
        ['promotion' => $promotion]);
}
    /**
     * @Route ("/first", name="first")
     */
    public function first(){
        $repo=$this->getDoctrine()->getRepository(Promotion::class);
        $promotion=$repo->findAll();
        return $this->render('promotion/first.html.twig',
            ['promotion'=>$promotion]);
    }


    /**
     * @Route ("/affichage", name="affichage")
     */
    public function affichage(){
        $repo=$this->getDoctrine()->getRepository(Promotion::class);
        $promotion=$repo->findAll();
        return $this->render('promotion/affiche.html.twig',
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
    /**
     * @Route ("/lawla", name="lawla")
     */
    public function lawla (){
        $repo=$this->getDoctrine()->getRepository(Promotion::class);
        $promotion=$repo->findAll();
        return $this->render('promotion/lawla.html.twig',
            ['promotion'=>$promotion]);
    }

}
