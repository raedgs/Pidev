<?php

namespace App\Controller;

use App\Entity\ServiceAV;
use App\Form\ServiceAV1Type;
use App\Repository\ServiceAVRepository;
use App\Repository\TypeAVRepository;
use Symfony\Component\Security\Core\User\UserInterface;
use App\Entity\User;
use Doctrine\ORM\EntityManagerInterface;
use phpDocumentor\Reflection\Types\Integer;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Knp\Component\Pager\PaginatorInterface;



/**
 * @Route("/service/a/v")
 */
class ServiceAVController extends AbstractController
{
    /**
     * @Route("/back", name="service_a_v_index", methods={"GET"})
     */
    public function index(Request $request,ServiceAVRepository $serviceAVRepository ,PaginatorInterface $paginator): Response
    {
        $service_a_vs = $this->getDoctrine()->getRepository(ServiceAV:: class)->findAll();
        $service_a_vs=$paginator->paginate(
            $service_a_vs, //on passe les données
            $request->query->getInt('page', 1), //num de la page en cours, 1 par défaut
            4 //nbre d'articles par page
        );
        return $this->render('/service_av/editors.html.twig', array("service_a_vs" => $service_a_vs));

    }


    /**
     * @Route("/back/new", name="service_a_v_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $serviceAV = new ServiceAV();
        $form = $this->createForm(ServiceAV1Type::class, $serviceAV);
        $form->handleRequest($request);
        $userid=$this->getUser();
        $serviceAV->setUser($userid);

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
     * @Route("/back/{id}", name="service_a_v_show", methods={"GET"})
     */
    public function show(ServiceAV $serviceAV): Response
    {
        return $this->render('service_av/listeS.html.twig', [
            'service_a_v' => $serviceAV,
        ]);
    }
    /**
     * @Route("/back/afficheserv", name="affichserv", methods={"GET"})
     */
    public function showw(ServiceAV $serviceAV): Response
    {
        return $this->render('service_av/listeS.html.twig', [
            'service_a_v' => $serviceAV,
        ]);
    }

    /**
     * @Route("/back/{id}/edit", name="service_a_v_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, ServiceAV $serviceAV, EntityManagerInterface $entityManager, FlashyNotifier $flashy): Response
    {
        $form = $this->createForm(ServiceAV1Type::class, $serviceAV);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();
            $flashy->warning('voulez vous modifiez ? ', 'http://your-awesome-link.com');
            return $this->redirectToRoute('service_a_v_index', [], Response::HTTP_SEE_OTHER);
        }
        return $this->render('service_av/modification.html.twig', [
            'service_a_v' => $serviceAV,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/back/{id}", name="service_a_v_delete", methods={"POST"})
     */
    public function delete(Request $request, ServiceAV $serviceAV, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$serviceAV->getId(), $request->request->get('_token'))) {
            $entityManager->remove($serviceAV);
            $entityManager->flush();
        }

        return $this->redirectToRoute('service_a_v_index', [], Response::HTTP_SEE_OTHER);
    }
    /**
     * @Route("/test/test/ajoutS", name="service_a_v_ajoutS", methods={"GET", "POST"})
     */
    public function ajoutS(Request $request, EntityManagerInterface $entityManager,FlashyNotifier $flashy): Response
    {
        $serviceAV = new ServiceAV();
        $form = $this->createForm(ServiceAV1Type::class, $serviceAV);
        $form->handleRequest($request);
        $userid=$this->getUser();
        $serviceAV->setUser($userid);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($serviceAV);
            $entityManager->flush();
            $flashy->success('Service ajouté!', 'http://your-awesome-link.com');
            return $this->redirectToRoute('service_a_v_ajoutS', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('/service_av/client.html.twig', [
            'service_a_v' => $serviceAV,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/back/test/test/stats", name="stats", methods={"GET", "POST"})
     */
    public function indexAction()
    {
        $pieChart = new PieChart();
        $diag=$this->getDoctrine()->getRepository(ServiceAV::class)->diag();
        $rep=$this->getDoctrine()->getRepository(ServiceAV::class)->repa();
        $affi=$this->getDoctrine()->getRepository(ServiceAV::class)->affi();
        $pieChart->getData()->setArrayToDataTable(
            [['hh', 'Hours per Day'],
                ['Diagnostic',     (int)$diag],
                ['Reparation',      (int)$rep],
                ['Afficheur',  (int)$affi]
            ]
        );
        $pieChart->getOptions()->setTitle('Type');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);

        return $this->render('service_av/statss.html.twig', array('piechart' => $pieChart));
    }
}
