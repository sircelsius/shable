<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!-- TODO import taglib JSTL -->
<html class="no-js" lang="en">
  <head>
    <meta name="description" content="">
    <title>Shable | Dashboard</title>
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

    <link rel="stylesheet" href="../css/normalize.css">
    <link rel="stylesheet" href="../css/foundation.css" />
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/dashboard.css">
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
    </ul>
  </section>
</nav>


<section class="main">
  <div class="main-content">
    <div class="row">
      <div class="large-12 small-12 columns">
        <h2>Dashboard</h2>
        <p>Gérez les actions possibles avec votre Shable.</p>
      </div>
    </div>
    <div class="row">
      
      <div class="small-12 medium-6 large-6 columns">
        <div class="panel">
        <h3>Placez vos élèves pour un examen</h3>
        <p>Choisissez une salle et une liste d'élèves pour calculer le placement optimal grâce à notre alogrithme.</p>

        <form action="dashboard" method="post">
          <div class="row">
            <div class="large-6 small-12 columns">
              <label for="salle">Salle</label>
              <select name="salle" id="salle_select">
              <label for="log">Voulez-vous voir les logs ?</label>
              <input type="checkbox" name="log" />
                <% ArrayList<String> list_salle = new ArrayList<String>();
                String salle = new String();

                list_salle = (ArrayList<String>)request.getAttribute("list_salle");

                for(int i=0; i< list_salle.size(); i++){
                	salle = list_salle.get(i);
                	out.println("<option value='" + salle + "'>" + salle + "</option>");
                }

                 %>
              </select>
            </div>
          <div class="large-6 small-12 columns">
              <label for="classe">Classe</label>
              <select name="classe" id="classe_select">
                <% ArrayList<String> list_classe = new ArrayList<String>();
                String classe = new String();

                //list_classe = (ArrayList<String>)request.getAttribute("list_classe");

                for(int i=0; i< list_classe.size(); i++){
                  classe = list_classe.get(i);
                  out.println("<option value='" + classe + "'>" + classe + "</option>");
                }

                 %>
              </select>
            </div>
          </div>
          <input type="submit" class="button" value="Placer les élèves">
        </form>
        
        
        
      </div>
      </div>

          <% if(request.getAttribute("result_salle")!=null){
        String result_salle = (String)request.getAttribute("result_salle");
        String result_class = (String)request.getAttribute("result_class");
        ArrayList<String> result_tables = (ArrayList<String>)request.getAttribute("result_tables");
        ArrayList<String> result_students = (ArrayList<String>)request.getAttribute("result_students");
        %>
      <div class="small-12 large-6 columns">
        <div class="panel">
        <div id="table-containerlarge-12" class="columns">
          Vous avez sélectionné la salle <% out.println(result_salle); %> et la liste d'élèves <% out.println(result_class); %>.

          <table>
            <thead>
              <th>Elève</th>
              <th>Place</th>
            </thead>
            <tbody>
            <% 
            String student ="Marc";
            String table = "25";
             for(int i=0; i< result_tables.size(); i++){
                  student = result_students.get(i);
                  table = result_tables.get(i);
                  out.println("<tr><td>" + student + "</td><td>" + table + "</td></tr>");
                } %>
            </tbody>

          </table>
        </div>
        </div>
      </div>
        <% } %>
  </div>
</section>

    
    

    
    
    
    <script src="../js/jquery.js"></script>
    <script src="../js/foundation/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
  </body>
</html>