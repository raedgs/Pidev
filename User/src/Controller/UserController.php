<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\ProfileUpdateType;
use App\Form\SearchEmailType;
use App\Repository\UserRepository;
use App\Form\UserAddType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Knp\Component\Pager\PaginatorInterface;


class UserController extends AbstractController
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


    /**********************************BACKEND************************************************/

    /**
     * @Route("/back", name="back")
     */
    public function back(): Response
    {
        return $this->render('/back/index.html.twig');
    }



    /*                       CRUD                    */

    /**
     * @Route("/back/user_list", name="user_list")
     */
    public function user_list(Request $request, PaginatorInterface $paginator)
    {
        $user = $this->getDoctrine()->getRepository(User:: class)->findAll();
        $user=$paginator->paginate(
            $user, //on passe les données
            $request->query->getInt('page', 1), //num de la page en cours, 1 par défaut
            5 //nbre d'articles par page
        );
        return $this->render('/user/user_list.html.twig', array("user" => $user));
    }
    /**
     * @Route("/back/show_user/{id}", name="show_user_by_id")
     */
    public function show_user_by_id($id)
    {
        $user = $this->getDoctrine()->getRepository(User:: class)->find($id);
        return $this->render('/user/show_user_profile.html.twig', array("user" => $user));
    }
    /**
     * @Route("/back/delete_user/{id}", name="delete_user")
     */
    public function deleteAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $delete=$em->getRepository(User::class)->find($id);
        $em->remove($delete);
        $em->flush();
        $this->addFlash('user_deleted', 'User Deleted Successfully!!');
        return $this->redirectToRoute('user_list');
    }
    /**
     * @Route("/back/create_user", name="create_user")
     * @param Request $request
     * @return Response
     */
    public function create_user(Request $request, UserPasswordEncoderInterface $passwordEncoder)
    {
        /** @var UploadedFile $file */
        $user=new User();
        $form=$this->createForm(UserAddType::class,$user);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            $user->setPassword(
                $passwordEncoder->encodePassword(
                    $user, $form->get('password')->getData()
                )
            );
            //$file = $request->files->get('user')['image'];
            $file = $form->get('image')->getData();
            $uploads_directory = $this->getParameter('uploads_directory');
            $filename = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($uploads_directory,$filename);
            $user->setImage($filename);

            $em=$this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
            $this->addFlash('user_added', 'User Added Successfully!!');
            return $this->redirectToRoute('user_list');
        }
        return $this->render('/user/create.html.twig', ['CreateForm_User'=>$form->createView() ]);
    }

    /**
     * @Route("/back/update/{id}",name="update_user")
     * @param Request $request
     */
    public function update(UserRepository $repository,$id,Request $request, UserPasswordEncoderInterface $passwordEncoder)
    {
        /** @var UploadedFile $file */
        $user=$repository->find($id);
        $form=$this->createForm(UserAddType::class,$user);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid() )
        {
            $user->setPassword($passwordEncoder->encodePassword($user, $form->get('password')->getData()) );

            $file = $form->get('image')->getData();
            $uploads_directory = $this->getParameter('uploads_directory');
            $filename = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($uploads_directory,$filename);
            $user->setImage($filename);

            $em=$this->getDoctrine()->getManager();

            $em->flush();
            $this->addFlash('user_modified', 'User Modified Successfully!!');
            return $this->redirectToRoute('user_list');
        }
        return $this->render('/user/update_user.html.twig', ['UpdateForm_User'=>$form->createView() ]);
    }
    /**
     * @Route("/profile_front/{id}", name="profile_front")
     * @param Request $request
     */
    public function profile_front(UserRepository $repository,$id,Request $request, UserPasswordEncoderInterface $passwordEncoder)
    {
        /** @var UploadedFile $file */
        $user=$repository->find($id);
        $form=$this->createForm(ProfileUpdateType::class,$user);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            $user->setPassword($passwordEncoder->encodePassword($user, $form->get('password')->getData()) );

            $file = $form->get('image')->getData();
            $uploads_directory = $this->getParameter('uploads_directory');
            $filename = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($uploads_directory,$filename);
            $user->setImage($filename);

            $em=$this->getDoctrine()->getManager();

            $em->flush();
            $this->addFlash('Profile_Modified', 'Profile Modified Successfully!!');
            return $this->redirectToRoute('home');
        }
        return $this->render('/user/profile_front.html.twig', ['ProfileUpdate'=>$form->createView() ]);
    }

    /**
     * @Route("/back/user_list/tri_username", name="tri_username")
     */
    public function tri_username(Request $request, PaginatorInterface $paginator)
    {
        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
            'SELECT a FROM App\Entity\User a 
            ORDER BY a.username ASC'
        );
        $user = $query->getResult();
        $user=$paginator->paginate(
            $user, //on passe les données
            $request->query->getInt('page', 1), //num de la page en cours, 1 par défaut
            5 //nbre d'articles par page
        );
              return $this->render('/user/user_list.html.twig', array("user" => $user));
    }

    /**
     * @Route ("/recherche_email",name="recherche_email")
     */
    public function recherche_email(UserRepository $repository , Request $request, PaginatorInterface $paginator)
    {
        $data=$request->get('search');
        $user=$repository->SearchEmail($data);
        $user=$paginator->paginate(
            $user, //on passe les données
            $request->query->getInt('page', 1), //num de la page en cours, 1 par défaut
            5 //nbre d'articles par page
        );
        return $this->render('user/user_list.html.twig',array('user'=>$user));
    }
    /**
     * @Route ("/recherche_username",name="recherche_username")
     */
    public function recherche_username(UserRepository $repository , Request $request, PaginatorInterface $paginator)
    {
        $data=$request->get('search');
        $user=$repository->SearchUsername($data);
        $user=$paginator->paginate(
            $user, //on passe les données
            $request->query->getInt('page', 1), //num de la page en cours, 1 par défaut
            5 //nbre d'articles par page
        );
        return $this->render('user/user_list.html.twig',array('user'=>$user));
    }

    /**
     * @Route("/back/user_list/tri_role", name="tri_role")
     */
    public function tri_role(Request $request, PaginatorInterface $paginator)
    {
        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
            'SELECT a FROM App\Entity\User a 
            ORDER BY a.roles ASC'
        );
        $user = $query->getResult();
        $user=$paginator->paginate(
            $user, //on passe les données
            $request->query->getInt('page', 1), //num de la page en cours, 1 par défaut
            5 //nbre d'articles par page
        );
        return $this->render('/user/user_list.html.twig', array("user" => $user));
    }


}
