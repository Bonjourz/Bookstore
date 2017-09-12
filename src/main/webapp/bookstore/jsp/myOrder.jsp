<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Order"%>
<%@ page import="model.Orderitem"%>
<%@ page import="model.Buyer"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Iterator"%>
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
		ArrayList<Order> orderList = new ArrayList<Order>();
	if (session.getAttribute("orders") != null) {
		orderList = (ArrayList<Order>) session.getAttribute("orders");
		session.setAttribute("orders", null);
	}
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
					<li><a href="buyerInfo"><i class="fa fa-user fa-fw"></i>
							Personal Info</a></li>
					<li><a href="myCart"><i class="fa fa-shopping-cart fa-fw"></i>
							My Cart</a></li>
					<li><a class="active"><i
							class="fa fa-reorder fa-fw"></i> My Orders</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">My Orders</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables">
									<thead>
										<tr>
											<th>OrderId</th>
											<th>Date</th>
											<th>Address</th>
											<th>Status</th>
											<th>Total Price</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<%
											for (int i = 0; i < orderList.size(); i++) {
												Order order = orderList.get(i);
										%>
										<tr>
											<td><%=order.getOrderid()%></td>
											<td><%=order.getDate()%></td>
											<td><%=order.getAddress()%></td>
											<td><%=order.getStatus()%></td>
											<td><%=order.getPrice() / 100%></td>
											<td>
												<button class="btn btn-default edit" type="button"
													data-id="<%=order.getOrderid()%>"
													data-status="<%=order.getStatus()%>">
													<i class="fa fa-edit"></i>
												</button>
												<button class="btn btn-default item" type="button"
													data-id="<%=order.getOrderid()%>">
													<i class="fa fa-table"></i>
												</button>
											</td>	
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
						<!-- /.panel-body -->
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

	<div class="modal fade" id="modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modalTitle"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group">
									<select class="form-control" id="status">
										<option value="canceling">canceling</option>
										<option value="Apply for return">Apply for return</option>	
									</select>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="save">Save</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="orderItems" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="itemsTitle"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<div class="table-responsive">
							<table class="table table-hover">
							    <thead>
							        <tr>
							       		<th>#</th>
							            <th>Book Name</th>
							            <th>Price</th>
							            <th>Num</th>
							            <th>Total Price</th>
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
					<button id="close-button" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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

	<script src="<%=path%>/bookstore/js/myOrder.js"></script>

	<script>
		var status = 5; 
	</script>

</body>

</html>

