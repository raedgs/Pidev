<?php

namespace App\Controller;

use App\Entity\Exercice;
use App\Form\ExerciceType;
use App\Repository\ExerciceRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;



class ClassroomController extends AbstractController
{
    /**
     * @Route("/", name="home")
     */
    public function base(): Response
    {
        return $this->render('/front/base.html.twig');
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
     * @Route("/login-register", name="login-register")
     */
    public function login_register(): Response
    {
        return $this->render('/front/login-register.html.twig');
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
        return $this->render('/back/pages/examples/login-v2.html.twig');
    }
    /**
     * @Route("/register_admin", name="register_admin")
     */
    public function register_admin(): Response
    {
        return $this->render('/back/pages/examples/register-v2.html.twig');
    }
    /**
     * @Route("/forgot_password_admin", name="forgot_password_admin")
     */
    public function forgot_password_admin(): Response
    {
        return $this->render('/back/pages/examples/forgot-password-v2.html.twig');
    }

    /**
     * @Route("/classroom", name="classroom")
     */
    public function index(): Response
    {
        return $this->render('classroom/index.html.twig', ['controller_name' => 'ClassroomController',]);
    }


    /**
     * @Route("/list", name="list")
     */
    public function listClassroom()
    {
        $classroom = $this->getDoctrine()->getRepository(Exercice:: class)->findAll();
        return $this->render('/classroom/list.html.twig', array("classroom" => $classroom));
    }

    /**
     * @Route("/listid/{id}", name="show")
     */
    public function showClassroom($id)
    {
        $classroom = $this->getDoctrine()->getRepository(Exercice:: class)->find($id);
        return $this->render('/classroom/showid.html.twig', array("classroom" => $classroom));
    }

    /**
     * @Route("/delete/{id}", name="delete")
     */
    public function deleteAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $delete=$em->getRepository(Exercice::class)->find($id);
        $em->remove($delete);
        $em->flush();
        return $this->redirectToRoute('list');
    }

    /**
     * @Route("/create", name="create")
     * @param Request $request
     * @return Response
     */
    public function create(Request $request)
    {
        $exercice=new Exercice();
        $form=$this->createForm(ExerciceType::class,$exercice);
        $form->add('ajouter',SubmitType::class);
        $form->handleRequest($request);

        if($form->isSubmitted() )
        {
            $em=$this->getDoctrine()->getManager();
            $em->persist($exercice);
            $em->flush();
            return $this->redirectToRoute('list');
        }
        return $this->render('/classroom/create.html.twig', ['form'=>$form->createView() ]);
    }
    /**
     * @Route("/update/{id}",name="update")
     * @param Request $request
     */
    public function update(ExerciceRepository $repository,$id,Request $request)
    {
        $exercice=$repository->find($id);
        $form=$this->createForm(ExerciceType::class,$exercice);
        $form->add('update',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() )
        {
            $em=$this->getDoctrine()->getManager();
            $em->persist($exercice);
            $em->flush();
            return $this->redirectToRoute('list');
        }
        return $this->render('/classroom/update.html.twig', ['form'=>$form->createView() ]);
    }
}


