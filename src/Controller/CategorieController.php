<?php

namespace App\Controller;
use App\Entity\Categorie;
use App\Form\CategorieType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;

class CategorieController extends AbstractController
{
    /**
     * @Route("/categorie", name="categorie")
     */
    public function index(): Response
    {
        return $this->render('categorie/index.html.twig', [
            'controller_name' => 'CategorieController',
        ]);
    }
  /**
     * @Route("/show_cat", name="showCat")
     */
    public function show()
    {
        $Categorie = $this->getDoctrine()->getRepository(Categorie::class)->findAll();
        return $this->render('/categorie/showCat.html.twig',array("Categorie" => $Categorie));
    }
 
    /**
     * @Route("/addCategorie", name="addCategorie")
     */
    public function addCategorie(Request $request): Response
    {
        $categorie = new Categorie();
        $form = $this->createForm(CategorieType::class, $categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
          
         $entityManager = $this->getDoctrine()->getManager();
           $entityManager->persist($categorie);
            $entityManager->flush();

            return $this->redirectToRoute('showCat');
        }

        return $this->render('/categorie/addCategorie.html.twig', [
            'categorie' => $categorie,
            'formC' => $form->createView(),
        ]);
    }
    /**
     * @Route("/categorie/delete{id}", name="delete_cat")

     */
    public function delete($id){


        $entityManager = $this->getDoctrine()->getManager();
        $delete=$entityManager->getRepository(Categorie::class)->find($id);
        $entityManager->remove($delete);
        $entityManager->flush();

        return $this->redirectToRoute('showCat');
    }

    /**
     * @Route("/categorie/modify{id}", name="modify_cat")
     */
    public function modify(Request $request, int $id): Response
    {
        $entityManager = $this->getDoctrine()->getManager();

        $categorie = $entityManager->getRepository(Categorie::class)->find($id);
        $form = $this->createForm(CategorieType::class, $categorie);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $entityManager->flush();
            return $this->redirectToRoute('showCat');
        }

        return $this->render('/categorie/addCategorie.html.twig', [
            'categorie' => $categorie,
            'formC' => $form->createView(),
        ]);
    }
}
