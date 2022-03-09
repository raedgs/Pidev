<?php

namespace App\Controller;

use App\Entity\Commande;

use App\Form\CommandeType;
use App\Form\CommandeclientType;
use App\Repository\CommandeRepository;
use Knp\Component\Pager\PaginatorInterface;
use Endroid\QrCode\Builder\BuilderInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Endroid\QrCodeBundle\Response\QrCodeResponse;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Doctrine\ORM\Tools\Console\Command\MappingDescribeCommand;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Security\Http\Firewall\X509AuthenticationListener;
use Dompdf\Dompdf;
use Dompdf\Options;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;



class CommandeController extends AbstractController
{
    /**
     * @Route("/commande", name="commande")
     */
    public function index(BuilderInterface $customQrCodeBuilder): Response
    {
        return $this->render('commande/index.html.twig', [
            'controller_name' => 'CommandeController',
        ]);
    }
    /**
     * @Route("/back/show_com", name="showCom")
     */
    public function show()
    {
        $Commande = $this->getDoctrine()->getRepository(Commande::class)->findAll();
        return $this->render('commande/showCom.html.twig', array('Commande' => $Commande));
    }
    /**
     * @Route("/affichercommande", name="affichercommande")
     */
    public function affichercommande(Request $request, PaginatorInterface $paginator)
    {
        $Commande = $this->getDoctrine()->getRepository(Commande::class)->findAll();
        $Commande = $paginator->paginate(
            $Commande, //on passe les données
            $request->query->getInt('page', 1), //num de la page en cours, 1 par défaut
            4 //nbre d'articles par page
        );
        return $this->render('front/affichercommande.html.twig', array('Commande' => $Commande));
    }

    /**
     * @Route("/back/addcommande", name="addcommande")
     */
    public function addcommande(Request $request): Response
    {
        $Commande = new Commande();
        $form = $this->createForm(CommandeType::class, $Commande);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($Commande);
            $entityManager->flush();


            return $this->redirectToRoute('showCom');
        }

        return $this->render('commande/addcommande.html.twig',  [
            '$Commande' => $Commande,
            'formC' => $form->createView(),
        ]);
    }

    /**
     * @Route("/addcom", name="addcom")
     */
    public function addcom(Request $request): Response
    {
        $Commande = new Commande();
        $form = $this->createForm(CommandeclientType::class, $Commande);
        $form->handleRequest($request);
        $Commande->setEtat('encour');

        if ($form->isSubmitted() && $form->isValid()) {

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($Commande);
            $entityManager->flush();

            $this->addFlash('c_add', 'Commande Ajoutée !!');
            return $this->redirectToRoute('addliv');
        }

        return $this->render('front/ajoutercommande.html.twig',  [
            '$Commande' => $Commande,
            'formD' => $form->createView(),
        ]);
    }
    /**
     * @Route("/back/commande_back/delete/{id}", name="deleteCommande")
     */
    public function deletes($id)
    {


        $entityManager = $this->getDoctrine()->getManager();
        $delete = $entityManager->getRepository(Commande::class)->find($id);
        $entityManager->remove($delete);
        $entityManager->flush();
        $this->addFlash('c_del', 'Commande Supprimée !!');

        return $this->redirectToRoute('showCom');
    }

    /**
     * @Route("/commande/delete/{id}", name="deleteCom")
     */
    public function delete($id)
    {
        $entityManager = $this->getDoctrine()->getManager();
        $delete = $entityManager->getRepository(Commande::class)->find($id);
        $entityManager->remove($delete);
        $entityManager->flush();
        $this->addFlash('c_del', 'Commande Supprimée !!');


        return $this->redirectToRoute('affichercommande');
    }

    /**
     * @Route("/commande/delete/{id}", name="deleteCom")
     */
    public function getCommandeById($id)
    {
        $entityManager = $this->getDoctrine()->getManager();
        $delete = $entityManager->getRepository(Commande::class)->find($id);
        $entityManager->remove($delete);
        $entityManager->flush();
        $this->addFlash('c_del', 'Commande Supprimée !!');


        return $this->redirectToRoute('affichercommande');
    }

    /**
     * @Route("/commande/modify{id}", name="modifCom")
     */
    public function modifCom(Request $request, int $id): Response
    {
        $entityManager = $this->getDoctrine()->getManager();

        $Commande = $entityManager->getRepository(Commande::class)->find($id);
        $form = $this->createForm(CommandeType::class, $Commande);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();
            $this->addFlash('c_mod', 'Commande Modifiée !!');
            return $this->redirectToRoute('affichercommande');
        }

        return $this->render('front/updateCommande.html.twig', [
            'commande' => $Commande,
            'formA' => $form->createView(),
        ]);
    }

    /**
     * @Route("/commande/done/{id}", name="markDone")
     */
    public function setCommandeToTerminee(int $id)
    {
        $entityManager = $this->getDoctrine()->getManager();
        $Commande = $entityManager->getRepository(Commande::class)->find($id);
        $Commande->setEtat("terminer");
        $entityManager->flush();
        return new Response('<h1 style="color: #008000;">Commande a été terminée</h1>');
    }


    /**
     * @Route("/back/statpro", name="statpro")
     */
    public function stat(){

        $repository = $this->getDoctrine()->getRepository(Commande::class);
        $com = $repository->findAll();

        $pro = $this->getDoctrine()->getManager();

        $c1=1;
        $c2=2;


        foreach ($com as $com)
        {
            if (  $com->getEtat()=="En Cours")  :

                $c1+=1;
            elseif ($com->getEtat()=="terminer"):

                $c2+=1;

            endif;

        }
        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable(
            [['Etat', 'Nombre'],
                ['En Cours',  $c1],
                ['Terminer',  $c2]


            ]
        );
        $pieChart->getOptions()->setTitle('Top Catégories');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);

        return $this->render('commande/Statistique.html.twig', array('piechart' => $pieChart));
    }



    /**
     * @Route("/imprimer_com", name="imprimer_com")
     */
    public function imprimevol(CommandeRepository $commandeRepository): Response
    {
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        $dompdf = new Dompdf($pdfOptions);

        $com = $commandeRepository->findAll();

        $html = $this->renderView('commande/pdf1.html.twig', [
            'com' => $com,
        ]);

        $dompdf->loadHtml($html);

        $dompdf->setPaper('A4', 'portrait');

        $dompdf->render();

        $dompdf->stream("Liste  commande.pdf", [
            "Attachment" => true
        ]);

        return $this->redirectToRoute('imprimer_com');

    }









}
