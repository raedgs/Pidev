<?php

namespace App\Controller;
use App\Entity\Categorie;
use App\Entity\Produit;
use App\Form\CategorieType;
use App\Form\ProduitType;
use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use App\Controller\NormalizerInterface;
use Knp\Component\Pager\PaginatorInterface;


class ProduitController extends AbstractController
{

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
     * @Route("/addProduit", name="addProduit")
     */
    public function addProduit(Request $request): Response
    {
        /** @var UploadedFile $file */
        $produit = new Produit();
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file=$form->get('Image')->getData();
            $uploads_directory=$this->getParameter('uploads_directory');
            $filename = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($uploads_directory, $filename);
            $produit->setImage($filename);

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($produit);
            $entityManager->flush();

            return $this->redirectToRoute('showProd');
        }

        return $this->render('produit/addProduit.html.twig', [
            'produit' => $produit,
            'formA' => $form->createView(),
        ]);
    }
    /**
     * @Route("/show_prod", name="showProd")
     */
    public function show()
    {
        $Produit = $this->getDoctrine()->getRepository(Produit::class)->findAll();
        return $this->render('produit/showProd.html.twig',array('Produit' => $Produit));
    }
    /**
     * @Route("/produit", name="home")
     */
    public function afficher()
    {
        $Produit = $this->getDoctrine()->getRepository(Produit::class)->findAll();
        return $this->render('/produit/shop-left-sidebar.html.twig',array('Produit' => $Produit));
    }

    /**
     * @Route("/produit/delete{id}", name="delete_prod")

     */
    public function delete($id){


        $entityManager = $this->getDoctrine()->getManager();
        $delete=$entityManager->getRepository(Produit::class)->find($id);
        $entityManager->remove($delete);
        $entityManager->flush();

        return $this->redirectToRoute('showProd');
    }
    /**
     * @Route("/produit/modify{id}", name="modify_prod")
     */
    public function modify(Request $request, int $id): Response
    {        /** @var UploadedFile $file */

        $entityManager = $this->getDoctrine()->getManager();

        $Produit = $entityManager->getRepository(Produit::class)->find($id);
        $form = $this->createForm(ProduitType::class, $Produit);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $file=$form->get('Image')->getData();
            $uploads_directory=$this->getParameter('uploads_directory');
            $filename = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($uploads_directory, $filename);
            $Produit->setImage($filename);

            $entityManager->flush();
            return $this->redirectToRoute('showProd');
        }

        return $this->render('produit/updateProduit.html.twig', [
            'Produit' => $Produit,
            'formA' => $form->createView(),
        ]);
    }

    /**
     * @Route("/recherche", name="recherche")
     */
  public function  recherche(ProduitRepository  $repository){
    $Produit=$repository->findSearch();
      return $this->render('/produit/shop-left-sidebar.html.twig',array('Produit' => $Produit));
}




    /**
     * @Route("/", name="produits")
     */
    public function index(Request $request, PaginatorInterface $paginator) // Nous ajoutons les paramètres requis
    {
        // Méthode findBy qui permet de récupérer les données avec des critères de filtre et de tri
        $donnees = $this->getDoctrine()->getRepository(Produit::class)->findBy([], ['created_at' => 'desc']);

        $produit = $paginator->paginate(
            $donnees, // Requête contenant les données à paginer (ici nos articles)
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            3 // Nombre de résultats par page
        );

        return $this->render('produit/showProd.html.twig', [
            'produit' => $produit,
        ]);
    }


}
