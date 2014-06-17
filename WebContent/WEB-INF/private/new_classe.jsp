<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- TODO import taglib JSTL -->
<html class="no-js" lang="en">
  <head>
    <meta name="description" content="">
    <title>Shable | Nouvelle Classe</title>
    <meta name="HandheldFriendly" content="True">
    <meta name="MobileOptimized" content="320">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="cleartype" content="on">

    
    <link rel="apple-touch-icon-precomposed" href="../img/touch/icon-main.png">
    <link rel="shortcut icon" href="../img/touch/icon-main.png">

        <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="../img/touch/apple-touch-icon-144x144-precomposed.png">
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

    <link rel="stylesheet" href="../css/normalize.css">
    <link rel="stylesheet" href="../css/foundation.css" />
    <link rel="stylesheet" href="../css/style.css">
    <script src="../js/modernizr.js"></script>
  </head>
  <body>
    
    <nav class="top-bar top-bar-media">
  <ul class="title-area">
    <li class="name">
      <h1><a href="index.html">Shable</a></h1>
    </li>
  </ul>
 
  <section class="top-bar-section">
    <ul class="right">
      <li class="active"><a href="../statut">Statut</a></li>
      <li class="active"><a href="../home">Déconnexion</a></li>
    </ul>
  </section>
</nav>


    <div class="main">
    <div class="row">
      <div class="large-12 columns">
        <h1>Shable <small>Nouvelle classe</small></h1> 
        <h2 class="show-for-medium-up">Ajoutez une nouvelle classe à vos données</h2>
      </div>
    </div>
    <div class="row">
      <div class="large-12 small-12 small-12 columns">
        <div class="panel">
          <form action="new_classe" method="post">
            <div class="row">
              <div class="large-4 medium-12 small-12 columns">
                <label for="nom_classe">Nom</label>
                <input type="text" value="GSI">
              </div>
              <div class="large-4 medium-12 small-12 columns">
                <label for="promo">Promo</label>
                <select name="promo" id="promo">
                </select>
              </div>
              <div class="large-4 medium-12 small-12 columns">
                <label for="campus">Campus</label>
                <select name="campus" id="campus">
                  <option value="cergy">Cergy</option>
                  <option value="pau">Pau</option>
                  <option value="singapour">Singapour</option>
                </select>
              </div>
            </div>

          </form>
        </div>
      </div>
    </div>
    </div>
    
    
    
    
    <script src="../js/jquery.js"></script>
    <script src="../js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
  </body>
</html>