<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ServiceController extends AbstractController
{
    /**
     * @Route("/service", name="service")
     */
    public function index(): Response
    {
        return $this->render('service/index.html.twig', [
            'controller_name' => 'ServiceController',
        ]);
    }
    /**
     * @Route("/", name="home")
     */
    public function base(): Response
    {
        return $this->render('/front/base.html.twig');
    }
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
     * @Route("/login_admin", name="login_admin")
     */
    public function login_admin(): Response
    {
        return $this->render('/user/login-v2.html.twig');
    }
    /**
     * @Route("/register_admin", name="register_admin")
     */
    public function register_admin(): Response
    {
        return $this->render('/user/register-v2.html.twig');
    }
    /**
     * @Route("/forgot_password_admin", name="forgot_password_admin")
     */

    public function forgot_password_admin(): Response
    {
        return $this->render('/user/forgot-password-v2.html.twig');
    }

}
