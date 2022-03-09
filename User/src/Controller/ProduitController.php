<?php

namespace App\Controller;
use App\Entity\Categorie;
use App\Repository\CategorieRepository;
use App\Entity\Produit;
use App\Entity\User;
use App\Form\CategorieType;
use App\Form\ProduitType;
use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Knp\Component\Pager\PaginatorInterface;
use App\Form\ProduitSearchType;
use App\Entity\ProduitSearch;
use Symfony\Component\Security\Core\User\UserInterface;


class ProduitController extends AbstractController
{


     /**
     * @Route("/back/addProduit", name="addProduit")
     */
    public function addProduit(Request $request): Response
    {
        /** @var UploadedFile $file */
        $produit = new Produit();
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);
        $userid=$this->getUser();
        $produit->setUser($userid);

        if ($form->isSubmitted() && $form->isValid()) {
            $file=$form->get('Image')->getData();
            $uploads_directory=$this->getParameter('uploads_directory_produit');
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
            'formProd' => $form->createView(),
        ]);
    }
    /**
     * @Route("/back/show_prod", name="showProd")
     */
    public function show(Request $request,PaginatorInterface $paginator)
    {
        $Produit = $this->getDoctrine()->getRepository(Produit::class)->findAll();
        $Produit=$paginator->paginate(
            $Produit, //on passe les données
            $request->query->getInt('page', 1), //num de la page en cours, 1 par défaut
             3);//nbre d'articles par page
        return $this->render('produit/showProd.html.twig',array('Produit' => $Produit));
    }
    /**
     * @Route("/produit", name="home")
     */
    public function afficher(Request $request,PaginatorInterface $paginator)
    {
        $Produit = $this->getDoctrine()->getRepository(Produit::class)->findAll();
        $Produit=$paginator->paginate(
            $Produit, //on passe les données
            $request->query->getInt('page', 1), //num de la page en cours, 1 par défaut
            3);//nbre d'articles par page
              return $this->render('/produit/shop-left-sidebar.html.twig',array('Produit' => $Produit));
    }

      /**
     * @Route("/show/{id}", name="produit_show", methods={"GET"}))
     */
    public function showP(Produit $produit): Response
    {
        return $this->render('produit/show.html.twig', [
            'produit' => $produit,
        ]);
    }

    /**
     * @Route("/back/produit/delete{id}", name="delete_prod")

     */
    public function delete($id){


        $entityManager = $this->getDoctrine()->getManager();
        $delete=$entityManager->getRepository(Produit::class)->find($id);
        $entityManager->remove($delete);
        $entityManager->flush();

        return $this->redirectToRoute('showProd');
    }
    /**
     * @Route("/back/produit/modify{id}", name="modify_prod")
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
            $uploads_directory=$this->getParameter('uploads_directory_produit');
            $filename = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($uploads_directory, $filename);
            $Produit->setImage($filename);

            $entityManager->flush();
            return $this->redirectToRoute('showProd');
        }

        return $this->render('produit/updateProduit.html.twig', [
            'Produit' => $Produit,
            'formProd' => $form->createView(),
        ]);
    }

    /**
     * @Route("/triproduit", name="triproduit")
     */
    public function triproduit(Request $request,PaginatorInterface $paginator)
    {
        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
            'SELECT a FROM App\Entity\Produit a 
            ORDER BY a.Libelle ASC'
        );
        $Produit=$query->getResult();
        $Produit=$paginator->paginate(
            $Produit, //on passe les données
            $request->query->getInt('page', 1), //num de la page en cours, 1 par défaut
            3);//nbre d'articles par page
        return $this->render('produit/shop-left-sidebar.html.twig', array("Produit" => $Produit));
    }
      /**
     * @Route("/produit/ajax_search", name="ajax_search" ,methods={"GET"})
     * @param Request $request
     * @param ProduitRepository $produitRepository
     * @return Response
     */
    public function searchAction(Request $request,ProduitRepository $produitRepository) : Response
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $produits =$produitRepository->SearchNom($requestString);
        if(!$produits) {
            $result['produits']['error'] = "produit non trouvée ";
        } else {
            $result['produits'] = $this->getRealEntities($produits);
        }
        return new Response(json_encode($result));
    }
    public function getRealEntities($produits){
        foreach ($produits as $produit){
            $realEntities[$produit->getIdproduit()] = [$produit->getImage(),$produit->getLibelle()];

        }
        return $realEntities;
    }

    /**
     * @route("/back/stat",name="sta")
     */
    public function statisti(ProduitRepository $repository,CategorieRepository $categorieRepository)
    {

        $opp=$repository->findAll();

        return $this->render("produit/statistique.html.twig",['Reg'=>$opp]);

    }

}
