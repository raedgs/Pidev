<?php

namespace App\Controller;

use App\Entity\TypeAV;
use App\Form\TypeAVType;
use App\Repository\TypeAVRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use MercurySeries\FlashyBundle\FlashyNotifier;


/**
 * @Route("/back/type/a/v")
 */
class TypeAVController extends AbstractController
{
    /**
     * @Route("/", name="type_a_v_index", methods={"GET"})
     */
    public function index(TypeAVRepository $typeAVRepository): Response
    {
        return $this->render('type_av/affichetype.html.twig', [
            'type_a_vs' => $typeAVRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="type_a_v_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $typeAV = new TypeAV();
        $form = $this->createForm(TypeAVType::class, $typeAV);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($typeAV);
            $entityManager->flush();

            return $this->redirectToRoute('type_a_v_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('type_av/creer.html.twig', [
            'type_a_v' => $typeAV,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="type_a_v_show", methods={"GET"})
     */
    public function show(TypeAV $typeAV): Response
    {
        return $this->render('type_av/liste.html.twig', [
            'type_a_v' => $typeAV,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="type_a_v_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, TypeAV $typeAV, EntityManagerInterface $entityManager, FlashyNotifier $flashy): Response
    {
        $form = $this->createForm(TypeAVType::class, $typeAV);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();
            $flashy->warning('voulez vous modifiez ? ', 'http://your-awesome-link.com');
            return $this->redirectToRoute('type_a_v_index', [], Response::HTTP_SEE_OTHER);
        }
        $flashy->warning('voulez vous modifiez ? ', 'http://your-awesome-link.com');

        return $this->render('type_av/modifier.html.twig', [
            'type_a_v' => $typeAV,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="type_a_v_delete", methods={"POST"})
     */
    public function delete(Request $request, TypeAV $typeAV, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$typeAV->getId(), $request->request->get('_token'))) {
            $entityManager->remove($typeAV);
            $entityManager->flush();
        }
        return $this->redirectToRoute('type_a_v_index', [], Response::HTTP_SEE_OTHER);
    }

}
