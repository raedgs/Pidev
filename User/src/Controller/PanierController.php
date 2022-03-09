<?php

namespace App\Controller;

use App\Entity\Produit;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\SessionInterface;

class PanierController extends AbstractController
{
    /**
     * @Route("/panier", name="panier_index")
     */
    public function index(SessionInterface $session)
    {
        $panier = $session->get('panier', []);
        $panierWithData = PanierController::getPanierWithData($panier);
        $total = PanierController::getTotalFromPanier($panierWithData);
        //dd($panierWithData);
        return $this->render('panier/index.html.twig', [
            'items' => $panierWithData,
            'total' => $total,
        ]);
    }

    /**
     * @Route("/fetchCart", name="cartjson")
     */
    public function cartjson(SessionInterface $session)
    {
        $panier = $session->get('panier', []);
        $count = 0;
        foreach ($panier as $id => $qte)
            $count += $qte;
        $total = PanierController::getTotalFromPanier(PanierController::getPanierWithData($panier));
        return $this->json([
            'code' => 200,
            'message' => 'fetching cart successfully',
            'count' => $count,
            'total' => $total,
        ], 200);
    }


    /**
     * @Route("/panier/add/{id}", name="panier_add")
     */

    public function add($id, SessionInterface $session)
    {
        $panier = $session->get('panier', []);
        $panier[$id] = (empty($panier[$id]) ? 1 : $panier[$id] + 1);
        $session->set('panier', $panier);
        $count = 0;
        foreach ($panier as $id => $qte)
            $count += $qte;
        $total = PanierController::getTotalFromPanier(PanierController::getPanierWithData($panier));
        return $this->json([
            'code' => 200,
            'message' => 'produit ajoute avec succee au panier',
            'count' => $count,
            'total' => $total,
        ], 200);
    }

    /**
     * @Route("panier/remove/{id}", name="panier_remove")
     */
    public function remove($id, SessionInterface $session)
    {
        $panier = $session->get('panier', []);
        if (!empty($panier[$id])) {
            unset($panier[$id]);
        }
        $session->set('panier', $panier);
        $count = 0;
        foreach ($panier as $id => $qte)
            $count += $qte;
        $total = PanierController::getTotalFromPanier(PanierController::getPanierWithData($panier));

        return $this->json([
            'code' => 200,
            'message' => 'produit suprimee avec succee de panier',
            'count' => $count,
            'total' => $total,
            'id' => $id,
        ], 200);
    }

    /**
     * @Route("panier/update/{id}", name="cart_update")
     */
    /*  public function update($id, SessionInterface $session) {
        $panier = $session->get('panier', []);
        $panier[$id] = 1;
        $session->set('panier', $panier);
        return $this->redirectToRoute("cart_index");
    }*/

    private function getPanierWithData($panier)
    {
        $ProduitRepository = $this->getDoctrine()->getRepository(Produit::class);
        $panierWithData = [];
        foreach ($panier as $id => $qte) {
            $panierWithData[] = [
                'produit' => $ProduitRepository->find($id),
                'qte' => $qte
            ];
        }
        return $panierWithData;
    }

    private function getTotalFromPanier($panierWithData)
    {
        $total = 0;
        foreach ($panierWithData as $item) {
            $total += $item['produit']->getPrix() * $item['qte'];
        }
        return $total;
    }
}
