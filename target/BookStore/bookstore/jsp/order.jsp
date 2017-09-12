<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Order"%>
<%@ page import="model.Orderitem"%>
<%@ page import="model.Buyer"%>
<%@ page import="model.Book"%>
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
	
	ArrayList<Book> bookList = new ArrayList<Book>();
	if (session.getAttribute("books") != null) {
		bookList = (ArrayList<Book>) session.getAttribute("books");
		session.setAttribute("books", null);
	}
	
	ArrayList<Buyer> buyerList = new ArrayList<Buyer>();
	if (session.getAttribute("buyers") != null) {
		buyerList = (ArrayList<Buyer>) session.getAttribute("buyers");
		session.setAttribute("buyers", null);
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
					<li><a href="allUsersPro"><i class="fa fa-user fa-fw"></i>
							Users</a></li>
					<li><a href="allBooksPro"><i class="fa fa-book fa-fw"></i>
							Books</a></li>
					<li><a class="active"><i
							class="fa fa-reorder fa-fw"></i> Orders</a></li>
					<li><a href="<%=path%>/bookstore/jsp/statics.jsp"><i class="fa fa-bar-chart"></i>
							Statics</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Orders</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							add order
							<button class="btn btn-default" type="button" id="add">
								<i class="fa fa-plus"></i>
							</button>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables">
									<thead>
										<tr>
											<th>OrderId</th>
											<th>Userid</th>
											<th>Username</th>
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
											<td><%=order.getBuyer().getId()%></td>
											<td><%=order.getBuyer().getUsername()%></td>
											<td><%=order.getDate()%></td>
											<td><%=order.getAddress()%></td>
											<td><%=order.getStatus()%></td>
											<td><%=order.getPrice() / 100%></td>
											<td>
												<button class="btn btn-default edit" type="button"
													data-id="<%=order.getOrderid()%>"
													data-status="<%=order.getStatus()%>"
													data-address="<%=order.getAddress()%>">
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
										<option value="payed">payed</option>
										<option value="canceled">canceled</option>	
									</select>
								</div>
								<div class="form-group">
									<label>Address</label> <input class="form-control" name="address">
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
	
	
	<div class="modal fade" id="addOrders" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="itemsTitle"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group">
									<label>BookId</label>
									<select class="form-control" id="bookid">
									<%for(int i = 0; i < bookList.size(); i++){
										Book book = bookList.get(i);%>
										<option value="<%=book.getId()%>"><%=book.getId()%></option><%}%>
									</select>
								</div>
								<div class="form-group">
									<label>BuyerID</label>
									<select class="form-control" id="bookid">
									<%for(int i = 0; i < buyerList.size(); i++){
										Buyer buyer = buyerList.get(i);%>
										<option value="<%=buyer.getId()%>"><%=buyer.getId()%></option><%}%>
									</select>
								</div>
							</form>
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

	<script src="<%=path%>/bookstore/js/order.js"></script>
	
	<script>
		$(document).ready(function() {
			$('#dataTables').DataTable({
				responsive : true
			});
		});
	</script>

</body>

</html>

