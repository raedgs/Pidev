<?php

namespace App\Controller;

use App\Entity\Categorie;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class CategorieJsonController extends AbstractController
{
    /**
     * @Route("/categorie/json", name="categorie_json")
     */
    public function index(): Response
    {
        return $this->render('categorie_json/index.html.twig', [
            'controller_name' => 'CategorieJsonController',
        ]);
    }
    /**
     * @Route("/AllCategories", name="AllCategories")
     */
    public function AllCategories ( NormalizerInterface $Normalizer) {
        $repository = $this->getDoctrine()->getRepository(Categorie::class);
        $Categorie = $repository->findAll();
        $jsonContent = $Normalizer->normalize($Categorie, 'json',['groups'=>'post:read']);
        return $this->render('categorie_json/allcategorieJSON.html.twig',[
            'data'=> $jsonContent,
        ]);
        return new Response(json_encode($jsonContent));
    }
    /**
     * @Route("/addCategorieJSON/new", name="addCategorieJSON")
     */

    public function addCategorieJSON(Request $request, NormalizerInterface $Normalizer)
    {
        $em = $this->getDoctrine()->getManager();
        $categorie = new Categorie();
        $categorie->setLibelleCategorie($request->get('LibelleCategorie'));
        $em->persist($categorie);
        $em->flush();
        $jsonContent = $Normalizer->normalize($categorie, 'json', ['groups' => 'post:read']);
        return new Response(json_encode($jsonContent));;
    }

    /**
     * @Route("/updateCategorieJSON/{id}", name="updateCategorieJSON")
     */

    public function updateCategorieJSON(Request $request, NormalizerInterface $Normalizer, $id)
    {
        $em = $this->getDoctrine()->getManager();
        $categorie = $em->getRepository(Categorie::class)->find($id);
        $categorie->setLibelleCategorie($request->get('LibelleCategorie'));
        $em->flush();
        $jsonContent = $Normalizer->normalize($categorie, 'json', ['groups' => 'post:read']);
        return new Response("Information updated succusfuly" . json_encode($jsonContent));;
    }

    /**
     * @Route("/deleteCategorieJSON/{id}", name="deleteCategorieJSON")
     */
    public function deleteCategorieJSON(Request $request, NormalizerInterface $Normalizer, $id)
    {
        $em = $this->getDoctrine()->getManager();
        $categorie = $em->getRepository(Categorie::class)->find($id);
        $em->remove($categorie);
        $em->flush();
        $jsonContent = $Normalizer->normalize($categorie, 'json', ['groups' => 'post:read']);
        return new Response("categorie delete succusfuly" . json_encode($jsonContent));;

    }
}
