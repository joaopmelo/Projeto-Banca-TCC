<%-- 
    Document   : navbar
    Created on : 30/08/2018, 01:10:34
    Author     : jpedro
--%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  	<a class="navbar-brand mb-0 h1" href="#">Projeto Banca TCC</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
  	<div class="collapse navbar-collapse" id="navbarNavDropdown">
	    <ul class="navbar-nav">
		    

		    <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          Projeto
		        </a>
		        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
		          <a class="dropdown-item" href="/ProjetoBanca/IncluiProjeto.jsp">Cadastrar - PROJETO</a>
		          <a class="dropdown-item" href="/ProjetoBanca/Projeto/ListaProjeto/listarProjetosTodos.jsp">Listar Projetos - TODOS</a>
		          <a class="dropdown-item" href="/ProjetoBanca/Projeto/ListaProjeto/listProjetosTitulo.jsp">Buscar Projetos - TITULO</a>
		          <a class="dropdown-item" href="#">Buscar Projetos - ALUNO</a>
		          <a class="dropdown-item" href="#">Buscar Projetos - AREA DE PESQUISA</a>
		          <a class="dropdown-item" href="/ProjetoBanca/Projeto/ListaProjeto/listProjetosTipoProjeto.jsp?id_tipo=0">Buscar Projetos - TIPO DE PROJETO</a>
		          
		          
		        </div>
		    </li>
	      
	      
		    <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          Banca
		        </a>
		        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
		          <a class="dropdown-item" href="#">Cadastrar - BANCA</a>
		          <a class="dropdown-item" href="#">Buscar Bancas - TODAS</a>
		          <a class="dropdown-item" href="#">Buscar Bancas - FINALIZADAS</a>
		          <a class="dropdown-item" href="#">Buscar Bancas - NÃO REALIZADAS</a>
		          
		        </div>
		    </li>

	        <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          Avaliação
		        </a>
		        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
		          <a class="dropdown-item" href="#">Cadastrar - AVALIAÇÃO</a>
		          <a class="dropdown-item" href="#">Busca Avaliação - POR BANCA</a>
		          
		    	</div>
	      	</li>

	    </ul>
  	</div>
</nav>

<div class="container">
