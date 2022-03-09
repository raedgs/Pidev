<?php

namespace App\Controller;





use App\Form\LivraisonType;

use App\Entity\Livraison;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\Common\Collections\ArrayCollection;

class LivraisonController extends AbstractController
{
    /**
     * @Route("/livraison", name="livraison")
     */
    public function index(): Response
    {
        return $this->render('livraison/index.html.twig', [
            'controller_name' => 'LivraisonController',
        ]);
    }

    /**
     * @Route("/back/show_liv", name="showLiv")
     */
    public function show()
    {
        $Livraison = $this->getDoctrine()->getRepository(Livraison::class)->findAll();
        return $this->render('livraison/showLiv.html.twig', array('Livraison' => $Livraison));
    }
    /**
     * @Route("/addliv", name="addliv")
     */
    public function addliv(Request $request): Response
    {
        $Livraison = new Livraison();
        $form = $this->createForm(LivraisonType::class, $Livraison);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($Livraison);
            $entityManager->flush();

            return $this->redirect('addliv');
        }

        return $this->render('front/ajouterlivraison.html.twig',  [
            '$Livraison' => $Livraison,
            'formL' => $form->createView(),
        ]);
    }
    /**
     * @Route("/back/livraison/delete{id}", name="deleteLivraison")

     */
    public function delete($id)
    {


        $entityManager = $this->getDoctrine()->getManager();
        $delete = $entityManager->getRepository(Livraison::class)->find($id);
        $entityManager->remove($delete);
        $entityManager->flush();
        return $this->redirectToRoute('showLiv');
    }
    /**
     * @Route("/livraison/delete{id}", name="deleteLiv")

     */
    public function deletes($id)
    {


        $entityManager = $this->getDoctrine()->getManager();
        $delete = $entityManager->getRepository(Livraison::class)->find($id);
        $entityManager->remove($delete);
        $entityManager->flush();

        return $this->redirectToRoute('ajouterlivraison');
    }
    /**
     * @Route("/livraison/modify{id}", name="modifLiv")
     */
    public function modifLiv(Request $request, int $id): Response
    {
        $entityManager = $this->getDoctrine()->getManager();

        $Livraison = $entityManager->getRepository(Livraison::class)->find($id);
        $form = $this->createForm(LivraisonType::class, $Livraison);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();
            return $this->redirectToRoute('showLiv');
        }

        return $this->render('front/afficherlivraison.html.twig', [
            'livraison' => $Livraison,
            'formA' => $form->createView(),
        ]);
    }
}
