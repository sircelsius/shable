<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- TODO import taglib JSTL -->
<html class="no-js" lang="en">
  <head>
    <meta name="description" content="">
    <title>Shable | Bienvenue</title>
    <meta name="HandheldFriendly" content="True">
    <meta name="MobileOptimized" content="320">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="cleartype" content="on">

    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/touch/icon-main.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/touch/icon-main.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/touch/icon-main.png">
    <link rel="apple-touch-icon-precomposed" href="img/touch/icon-main.png">
    <link rel="shortcut icon" href="img/touch/icon-main.png">

        <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="img/touch/icon-main.png">
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
      <h1><a href="home">Shable</a></h1>
    </li>
  </ul>

 
  <section class="top-bar-section">
    <ul class="right">
      <li class="active"><a href="statut">Statut</a></li>
      <li class="active"><a href="#">Inscription</a></li>
      <li class="active"><a href="login">Connexion</a></li>
    </ul>
  </section>
</nav>


    <div class="main">
    <div class="row">
      <div class="large-12 columns">
        <h1>Bienvenue sur Shable</h1> 
        <h2 class="show-for-medium-up">Votre utilitaire de remplissage de salles</h2>
      </div>
    </div>
    
    <div class="row" id="lala">
      <div class="large-12 columns">
        <div class="panel">
          <h3>Sachez où placer chaque élève </h3>
          <p>Grâce à Shable, vous n'avez plus à vous soucier de l'emplacement de vos élèves lors d'examens: rentrez juste vos classes d'élèves et vos salles, Shable s'occupe de tout!</p>
          <div class="row">
            <div class="small-12 large-4 medium-4 columns">
          <h4>Ne rentrez vos élèves et vos salles qu'une fois</h4>
          <p>Shable sauvegarde vos données d'une session à l'autre, vous n'avez qu'à créer vos salles et vos listes d'élèves une fois! </p>
        </div>
            <div class="small-12 large-4 medium-4 columns">
              <h4>Choisissez vos groupes en fonction de vos options</h4>
              <p>Vous pouvez définir plusieurs groupes pour chaque élèves, ne vous inquiétez pas, vous n'aurez pas à les créer plusieurs fois!</p>
            </div>
            <div class="small-12 large-4 medium-4 columns">
              <h4>Trouvez le placement optimal</h4>
              <p>En cas d'examen, Shable vous fournit le placement optimal dans chaque salle. Quoiqu'il arrive, les élèves seront répartis au mieux!</p>
            </div>        
          </div>
        </div>
      </div>
    </div>

    <% Boolean down = false;
      try{
        down = (Boolean)request.getAttribute("down");

    }
    catch(NullPointerException e){
    %>
    <div class="row">
      <div class="small-12 large-12 columns">
        <h3>Oops <small>Erreur Réseau</small></h3>
        <p>Il semblerait que notre réseau fasse une dépression. Consultez le statut et si nous ne sommes pas encore au courant, n'hésitez pas à nous prévenir sur Facebook ou Twitter, ou même par mail!</p>
      </div>
    </div>
    <%
  }

  if(down==true){
    %>
    <section id="down">
      <div class="row">
        <div class="small-12 large-12 columns">
          <h1>Nous modifions actuellement Shable</h1>
          <p>Nous sommes actuellement en train d'apporter des modifications importantes à Shable, revenez plus tard, suivez notre travail sur Twitter ou Facebook, ou encore allez voir le gif du jour sur <a href="http://suckmongif.com">suckmongif.com</a>!</p>
        </div>
      </div>
      <div class="row">
        <div class="small-12 large-6 columns">
          <h3>Ce sur quoi nous travaillons</h3>
          <p>
            <% try{
              String cur_work = (String)request.getAttribute("cur_work");
              out.println(cur_work);
              }catch(NullPointerException e)
          { 
            out.println("Erreur Réseau");
          }
            %>
          </p>
        </div>
        <div class="small-12 large-6 columns">
          <h3>Quand nous pensons revenir</h3>
        </div>
      </div>
    </section>
    
    <% } %>
    </div>
    
    
    <script src="js/jquery.js"></script>
    <script src="js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
  </body>
</html>