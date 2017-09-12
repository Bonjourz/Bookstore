<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Buyer"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>BookStore</title>

<%
	String path = request.getContextPath();
%>
<link href="<%=path%>/bookstore/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="<%=path%>/bookstore/css/dataTables.responsive.css"
	rel="stylesheet">
<link href="<%=path%>/bookstore/css/bookstore.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
</head>

<body>
	<%
		Buyer buyer = new Buyer();
		if (session.getAttribute("buyer") != null) {
			buyer = (Buyer) session.getAttribute("buyer");
			session.setAttribute("buyer", null);
		}
		int id = buyer.getId();
	%>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">

		<div class="navbar-header">
			<a class="navbar-brand" href="index">BookStore</a>
		</div>

		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li><a class="active"><i class="fa fa-user fa-fw"></i>
							Personal Info</a></li>
					<li><a href="myCart"><i class="fa fa-shopping-cart fa-fw"></i> 
							My Cart</a></li>
					<li><a href="myOrdersPro"><i class="fa fa-reorder fa-fw"></i>
							My Orders</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">My information</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="col-md-4">
				<img src="getBuyerImg" height="350" width="300"></img>
				<form method="post" action="uploadImg" enctype="multipart/form-data">
					<input type="file" name="file" id="file" />
					<br>
					<input type="submit" value="上传" />
				</form>
			</div>
			<div class="col-md-8">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- /.panel-heading -->
						<div class="panel-body">
							<form role="form">
							<div class="form-group">
									<label>id</label> <input class="form-control" disabled="true" value="<%=buyer.getId()%>">
								</div>
								<div class="form-group">
								<label>UserName</label> <input class="form-control" disabled="true" value="<%=buyer.getUsername()%>">
								</div>
								<div class="form-group">
									<label>Email</label> <input class="form-control" disabled="true" value="<%=buyer.getEmail()%>">
								</div>
								<div class="form-group">
									<label>Password</label> <input class="form-control" type="password" disabled="true" value="<%=buyer.getPassword()%>">
								</div>
								
								<div class="form-group">
									<label>Role</label> <input class="form-control" disabled="true" value="buyer">
								</div>
								</form>
						</div>
						<!-- /.panel-body -->
					</div>
					<div  style="text-align:right">
						<button class="btn btn-primary" id="pwd">Change Password</button>
						<button class="btn btn-primary" id="addr" data-id="<%=buyer.getId()%>">Address Mananger</button>
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->

	<div class="modal fade" id="ChangePassword" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modalTitle">ChangePassword</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group">
									<label>Old pwd</label><input id="old-pwd" name="old-pwd" class="form-control" type="password">
									<label>New pwd</label><input id="new-pwd" name="new-pwd" class="form-control" type="password">
									<label>Confirm</label><input id="new-pwd-confirm" name="new-pwd-confirm" class="form-control" type="password">
									<p id="warn" style="color:red;font-size:16px;"></p>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="change" data-id="<%=buyer.getId()%>">Change</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="address" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="modalTitle">Adress List</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<div class="table-responsive">
							<table class="table table-hover">
							<button class="btn btn-default edit"><i class="fa fa-plus"></i></button>
							<input name="add-address">
							    <thead>
							        <tr>
							       		<th>#</th>
							            <th>Address</th>
							            <th>Action</th>
							        </tr>
							    </thead>
							    <tbody id="tbody">
							    </tbody>
							</table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" id="close">Close</button>
					<button type="button" class="btn btn-primary" id="change" data-id="<%=buyer.getId()%>">Change</button>
				</div>
			</div>
		</div>
	</div>

	<script src="<%=path%>/bookstore/js/jquery.min.js"></script>
	<script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/jquery.dataTables.min.js"></script>
	<script src="<%=path%>/bookstore/js/dataTables.bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/bookstore.js"></script>
	<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>

	<script src="<%=path%>/bookstore/js/buyerInfo.js"></script>

	<script>
		$(document).ready(function() {
			$('#dataTables').DataTable({
				responsive : true
			});
		});
		var id = <%=buyer.getId()%>;
	</script>
	

</body>

</html>

