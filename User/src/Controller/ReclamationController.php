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
use Dompdf\Dompdf;
use Dompdf\Options;



class ReclamationController extends AbstractController
{
    /**
     * @Route("/reclamation", name="reclamation_index", methods={"GET"})
     */
    public function index(ReclamationRepository $reclamationRepository): Response
    {
        return $this->render('reclamation/afficheRecl.html.twig', [
            'reclamations' => $reclamationRepository->findAll(),

        ]);
    }

    /**
     * @Route("/reclamation_new", name="reclamation_new")
     */
    public function new(Request $request)
    {
        $reclamation = new Reclamation();
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

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
        $form = $this->createForm(EnvoyerReclamationType::class, $reclamation);$form->handleRequest($request);
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
     * @Route("/reclamation/{id}", name="reclamation_show", methods={"GET"})
     */
    public function show(Reclamation $reclamation): Response
    {
        return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }

    /**
     * @Route("/reclamation/{id}/edit", name="reclamation_edit", methods={"GET", "POST"})
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
     * @Route("/reclamation/delete/{id}", name="reclamation_delete", methods={"POST"})
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
     * @Route("/listReclamation", name="reclamation_list", methods={"GET"})
     */
    public function listReclamation (ReclamationRepository $reclamationRepository) : Response
    {
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);


        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('reclamation/listReclamation.html.twig', [
            'reclamations' => $reclamationRepository->findAll(),
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A3', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);

    }
}