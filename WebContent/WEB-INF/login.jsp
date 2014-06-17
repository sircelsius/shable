<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<html class="no-js" lang="en">
  <head>
    <meta name="description" content="">
    <title>Shable | Login</title>
    <meta name="HandheldFriendly" content="True">
    <meta name="MobileOptimized" content="320">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="cleartype" content="on">

    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/touch/apple-touch-icon-144x144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/touch/apple-touch-icon-114x114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/touch/apple-touch-icon-72x72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="img/touch/apple-touch-icon-57x57-precomposed.png">
    <link rel="shortcut icon" href="img/touch/apple-touch-icon.png">

        <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="img/touch/apple-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#222222">


        <!-- For iOS web apps. Delete if not needed. https://github.com/h5bp/mobile-boilerplate/issues/94 -->
        <!--
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-title" content="">
        -->

        <!-- This script prevents links from opening in Mobile Safari. https://gist.github.com/1042026 -->
        <!--
        <script>(function(a,b,c){if(c in b&&b[c]){var d,e=a.location,f=/^(a|html)$/i;a.addEventListener("click",function(a){d=a.target;while(!f.test(d.nodeName))d=d.parentNode;"href"in d&&(d.href.indexOf("http")||~d.href.indexOf(e.host))&&(a.preventDefault(),e.href=d.href)},!1)}})(document,window.navigator,"standalone")</script>
        -->

    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/foundation.css" />
    <link rel="stylesheet" href="css/style.css">
    <script src="js/modernizr.js"></script>
  </head>
  <body>

    <nav class="top-bar top-bar-media">
  <ul class="title-area">
    <li class="name">
      <h1><a href="index.html">Shable: Login</a></h1>
    </li>
  </ul>
 
  <section class="top-bar-section">
    <ul class="right">
      <li class="active"><a href="statut">Statut</a></li>
      <li class="active"><a href="home">Accueil</a></li>
    </ul>
  </section>
</nav>
    
    <div class="row">
      <div class="large-12 columns">
        <h1>Connectez-vous à Shable</h1> 
        <h2 class="show-for-medium-up">Votre utilitaire de remplissage de salles</h2>
      </div>
    </div>

    
    <div class="row" id="connect">
      <div class="small-12 large-12 columns">
        <form method="post" action="j_security_check">
          <% Boolean success = true;
            try{
                success =(Boolean)request.getAttribute("success");
                if(success==false){
                %>
          <div class="small-12 large-12 columns error">
            <h3>Erreur</h3>
            <p>Login ou mot de passe incorrect, veuillez réessayer. Si le problème persiste, merci de contacter les administrateurs de Shable.</p>
          </div>
          <% }
              }
              catch(NullPointerException e){
           } 

           Boolean down = false;

           try{
           down = (Boolean)request.getAttribute("down");

           if(down==true){
           %>

           <div class="small-12 large-12 columns error">
            <h3>Erreur de Traitement</h3>
            <p>Soit la zombie invasion a commencée soit nous sommes en train de modifier Shable.
            <br>Dans le premier cas foncez lire le <a href="http://blogs.cdc.gov/publichealthmatters/2011/05/preparedness-101-zombie-apocalypse/">guide du survivant du CDC</a>, dans le second vous pouvez consulter le <a href="statut">statut</a> du site et nous contacter si nécessaire.</p>
          </div>

          <%
         }
         }catch(NullPointerException e){}%>
          
          <h2>Connexion</h2>

          <div class="small-12 medium-4 large-4 columns">
            <label for="j_username">Login</label>
            <input type="text" name="j_username" id="connect_login" value="Votre login">
          </div>
          
          <div class="small-12 large-4 medium-4 columns">
            <label for="j_password">Password</label>
            <input type="password" name="j_password" id="connect_password" value="Votre password">
          </div>

          <div class="small-12 large-4 medium-4 columns">
            <input type="submit" class="button inline" value="Connexion">
          </div>
          
        </form>
        </div>
      </div>

    
    
    <script src="../js/jquery.js"></script>
    <script src="../js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
  </body>
</html>