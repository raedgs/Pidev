<?php

namespace App\Controller;

use App\Entity\ServiceAV;
use App\Form\ServiceAV1Type;
use App\Repository\ServiceAVRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/service/a/v")
 */
class ServiceAVController extends AbstractController
{
    /**
     * @Route("/", name="service_a_v_index", methods={"GET"})
     */
    public function index(ServiceAVRepository $serviceAVRepository): Response
    {
        return $this->render('/back/pages/forms/editors.html.twig', [
            'service_a_vs' => $serviceAVRepository->findAll(),
        ]);
    }


    /**
     * @Route("/new", name="service_a_v_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $serviceAV = new ServiceAV();
        $form = $this->createForm(ServiceAV1Type::class, $serviceAV);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($serviceAV);
            $entityManager->flush();

            return $this->redirectToRoute('service_a_v_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('service_av/creerS.html.twig', [
            'service_a_v' => $serviceAV,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="service_a_v_show", methods={"GET"})
     */
    public function show(ServiceAV $serviceAV): Response
    {
        return $this->render('service_av/listeS.html.twig', [
            'service_a_v' => $serviceAV,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="service_a_v_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, ServiceAV $serviceAV, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ServiceAV1Type::class, $serviceAV);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('service_a_v_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('service_av/modification.html.twig', [
            'service_a_v' => $serviceAV,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="service_a_v_delete", methods={"POST"})
     */
    public function delete(Request $request, ServiceAV $serviceAV, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$serviceAV->getId(), $request->request->get('_token'))) {
            $entityManager->remove($serviceAV);
            $entityManager->flush();
        }

        return $this->redirectToRoute('service_a_v_index', [], Response::HTTP_SEE_OTHER);
    }
}
