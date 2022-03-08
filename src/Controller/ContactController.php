<?php

namespace App\Controller;

use App\Form\ContactType;
use http\Env\Request;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ContactController extends AbstractController
{
    /**
     * @Route("/contact", name="contact")
     */
    public function index(\Symfony\Component\HttpFoundation\Request $request ,\Swift_Mailer $mailer)
    {
        $form = $this->createForm(ContactType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $contact = $form->getData();
            $message=(new \Swift_Message('Nouveau Contact'))
                ->setFrom($contact['email'])
                ->setTo('votre@adresse.fr')
                ->setBody(
                    $this->renderView(
                        'email/contact.html.twig',compact('contact')
                    ),
                    'text/html'
                );
            $mailer->send($message);
            $this->addFlash('message','le message a bien éte  envoye ');
            return $this->redirectToRoute('contact');
        }
        return $this->render('contact/index.html.twig', [
            'contactForm' =>$form->createView(),
        ]);
    }
}
