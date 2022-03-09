<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\EnvoyerReclamationType;
use App\Form\ReclamationType;
use App\Repository\ReclamationRepository;
use Doctrine\ORM\EntityManagerInterface;
use phpDocumentor\Reflection\Type;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\User\UserInterface;
use App\Entity\User;
use Dompdf\Dompdf;
use Dompdf\Options;



class ReclamationController extends AbstractController
{
    /**
     * @Route("/back/reclamation", name="reclamation_index", methods={"GET"})
     */
    public function index(ReclamationRepository $reclamationRepository): Response
    {
        return $this->render('reclamation/afficheRecl.html.twig', [
            'reclamations' => $reclamationRepository->findAll(),

        ]);
    }

    /**
     * @Route("/back/reclamation_new", name="reclamation_new")
     */
    public function new(Request $request)
    {
        $reclamation = new Reclamation();
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);
        $userid=$this->getUser();
        $reclamation->setUser($userid);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();

            $entityManager->persist($reclamation);
            $entityManager->flush();

            return $this->redirectToRoute('reclamation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reclamation\cree.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/envoyer-reclamation", name="envoyer-reclamation")
     */
    public function envoyer(Request $request)
    {
        $reclamation = new Reclamation();
        $form = $this->createForm(EnvoyerReclamationType::class, $reclamation);
        $form->handleRequest($request);
        $userid=$this->getUser();
        $reclamation->setUser($userid);
    $data=$form->getData();

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();

            $entityManager->persist($reclamation);
            $entityManager->flush();
$this->addFlash('reclamation_added','Reclamation ajoutée avec succées' );
            return $this->redirectToRoute('home', [], Response::HTTP_SEE_OTHER);
        }
    return $this->render('reclamation\Recla.html.twig', [
            'reclamation' => $reclamation,
            'envoyerForm' => $form->createView(),
        ]);
    }

    /**
     * @Route("/back/reclamation/{id}", name="reclamation_show", methods={"GET"})
     */
    public function show(Reclamation $reclamation): Response
    {
        return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }

    /**
     * @Route("/back/reclamation/{id}/edit", name="reclamation_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request,ReclamationRepository $repository,Reclamation $reclamation, EntityManagerInterface $entityManager,$id)
    { $reclamation = $repository->find($id);
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->flush();

            return $this->redirectToRoute('reclamation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reclamation/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/back/reclamation/delete/{id}", name="reclamation_delete", methods={"POST"})
     */
    public function delete($id)
    {
        $reclamation=$this->getDoctrine()->getRepository(Reclamation::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($reclamation);
            $em->flush();


        return $this->redirectToRoute('reclamation_index', [], Response::HTTP_SEE_OTHER);
    }

    /**
     * @Route("/imprimer_recl", name="imprimer_recl")
     */
    public function imprimevol(ReclamationRepository $ReclamationRepository): Response
    {
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        $dompdf = new Dompdf($pdfOptions);

        $recl = $ReclamationRepository->findAll();

        $html = $this -> renderView('reclamation/pdfrecl.html.twig', [
            'recl' => $recl,
        ]);

        $dompdf->loadHtml($html);

        $dompdf->setPaper('A4', 'portrait');

        $dompdf->render();

        $dompdf->stream("Liste  reclamation.pdf", [
            "Attachment" => true
        ]);

        return $this->redirectToRoute('imprimer_recl');
    }

    /**
     * @Route("/back/search", name="serie-search")
     */
    public function searchSeries(ReclamationRepository $testrepository, Request $request)
    {
        $tests = $testrepository->findByNamePopular(
            $request->query->get('query')
        );

        $entityManager = $this->getDoctrine()->getManager();
        $categorieRepository=$entityManager->getRepository(Reclamation::class);


        return $this->render('reclamation/afficheRecl.html.twig', [
            'controller_name' => 'ReclamationController',
            'reclamations'=>$tests,

        ]);
    }
}