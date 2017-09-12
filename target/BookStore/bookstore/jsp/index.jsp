<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Book"%>

<% String path = request.getContextPath(); %>
<%
	String info = "Info";
	ArrayList<Book> bookList = new ArrayList<Book>();
	if (session.getAttribute("books") != null) {
		bookList = (ArrayList<Book>)session.getAttribute("books");
		session.setAttribute("books", null);
	}
	
	int status = 0;
	if (session.getAttribute("role") != null) {
		String role = (String)session.getAttribute("role");
		if (role.equals("buyer")) {
			status = 1;
			info = (String)session.getAttribute("name");
			session.setAttribute("name", null);
		}
		else if (role.equals("admin")) {
			status = 2;
			info = "Management";
		}
	}
	else status = 3;
%>
    

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bookstore</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="<%=path%>/bookstore/css/fuck.css" rel="stylesheet">

    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }

      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
    </style>
    <link href="<%=path%>/bookstore/css/fuck_you.css" rel="stylesheet">
    <link href="<%=path%>/bookstore/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
    

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#"><ul class="active">Bookstore-online</ul></a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right"> 	
            </p>
            <ul class="nav">
              <li><a href="#">Home</a></li>
              
              <li><a href="#contact">Contact</a></li>
              
            </ul>
            <ul class="nav pull-right">  
            	<%if (status == 1 || status == 2) {%>
            	<li><a href="logout">Logout<i class="fa fa-hand-o-up fa-fw"></i></a></li><%}
            	else{%><li><a href="login_buyer">Login as buyer<i class="fa fa-hand-o-up fa-fw"></i></a></li>
            			<li><a href="login_admin">Login as admin<i class="fa fa-hand-o-up fa-fw"></i></a></li><%}%>
          		<%if(status!=2){%><li><a href="javascript:void(0)" id="mycart">My cart<i class="fa fa-shopping-cart fa-fw"></i></a></li><%}%>
          		<li><a href="javascript:void(0)" id="info"><%=info%><i class="fa fa-user fa-fw"></i></a></li>
    		</ul>  
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
          <li class="nav-header"><h3 class="text-center">Classification</h3></li>
            <ul class="nav nav-list">
              <li class="nav-header"></li>
              <li class="active"><a href="#"><p class="text-center">Total</p></a></li>
              <li><a href="#"><p class="text-center">Fictions</p></a></li>
              <li><a href="#"><p class="text-center">Novels</p></a></li>
              <li><a href="#"><p class="text-center">Project books</p></a></li>
              
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <form class="navbar-form navbar-right">
            <div class="form-group">
              <input name="key" type="text" placeholder="bookname"> 
          		<button type="button" class="btn btn-primary search" id="search">Search</button>
            </div>
        <div class="span9">
          <div class="hero-unit">
            <h1 class="text-center">BookStore-online</h1>
            <br>
            <p class="text-center"><img src="img/profile.png"></img></p>
            <h3 class="text-center">This is a demo of online-bookstore</h3>
          </div>
          <%
          	int index = 1;
          	int count = 0;
          	for (int i = 0; i < bookList.size(); i++) {
          		count ++;
          		Book book = bookList.get(i);
          		if (index == 1) {
          			%><div class="row-fluid"><%
          		}
          		%>
          		<div class="span4">
          	 		<div class="row">
          	  		<div class="col-md-4">
          	  		<div class="thumbnail">
          	  		<img src="img" height="200" width="200">
          	  		<div class="caption">
          	  		<h3 class="text-center">No.<%=count%></h3>
          	  		<h4 class="text-center"><%=book.getTitle()%></h4>
          	  		<h5 class="text-center">Publish: <%=book.getPublisher()%></h5>
        	  		<h5 class="text-center">Price(CNY): <%=book.getPrice()/100 %></h5>
        	  		<h5 class="text-center">Stock: <%=book.getStock()%></h5>
        	  		<p></p>
        	  		<div style="text-align:center;">
        	  		<button data-id="<%=book.getId()%>" class="btn btn-primary edit" type="button">Add</button>
        	  		<button data-id="<%=book.getId()%>" class="btn btn-primary buy" type="button"
        	  		data-id="<%=book.getId()%>"
					data-stock="<%=book.getStock()%>">Buy Now!</button>
        	  		</div></div></div></div></div></div>
          	  	<%
          	  	if (index == 3){
          	  		%></div><%
          	  	}
          			
          	  	index++;
          	  	if (index == 4)
          		  	index = 1;
            	}
          	
            if (index != 1)
          	  out.println("</div>");
          %>
        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; BookStore-online 2017</p>
      </footer>
    </div><!--/.fluid-container-->
    
    
    <div class="modal fade" id="modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<button type="button" class="close" data-dismiss="modal">
			<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
		</button>
		<label> Num
			<input name="num" id="input-num"/> 	
		</label>
		<button type="button" class="btn btn-primary" id="buynow">Buy</button>
		<p id="warn"  style="color:red;font-size:16px;"></p>
	</div>

   	<script src="<%=path%>/bookstore/js/jquery.min.js"></script>
	<script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/jquery.dataTables.min.js"></script>
	<script src="<%=path%>/bookstore/js/dataTables.bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/bookstore.js"></script>
	<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>

	<script src="<%=path%>/bookstore/js/index.js"></script>
	<script>
		var status = <%=status%>
	</script>
  </body>
</html>
    