<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Cart"%>
<%@ page import="model.Book"%>
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
		ArrayList<Cart> cartList = new ArrayList<Cart>();
		if (session.getAttribute("carts") != null) {
			cartList = (ArrayList<Cart>) session.getAttribute("carts");
			session.setAttribute("carts", null);
		}
		int buyerId = cartList.size() > 0 ? cartList.get(0).getBuyerid() : -1;
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
					<li><a class="active"><i class="fa fa-shopping-cart fa-fw"></i> 
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
					<h1 class="page-header">Cart</h1>
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
											<th>Select</th>
										    <th>Book Name</th>
											<th>Price</th>
											<th>Num</th>
											<th>Total price</th>
											<th>Stock</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<%
											for (int i = 0; i < cartList.size(); i++) {
												Cart cart = cartList.get(i);
										%>
										<tr>
											<td><input name="select" type="checkbox" value="<%=cart.getId()%>"/></td>
										    <td><%=cart.getBook().getTitle()%></td>
											<td><%=cart.getBook().getPrice() / 100%></td>
											<td><%=cart.getNum()%></td>
											<td><%=cart.getBook().getPrice() / 100 * cart.getNum()%>
											<td><%=cart.getBook().getStock()%></td>
											<td>
												<button class="btn btn-default delete" type="button"
													data-id="<%=cart.getId()%>">
													<i class="fa fa-trash"></i>
												</button>
												<button class="btn btn-default edit" type="button"
													data-id="<%=cart.getId()%>"
													data-num="<%=cart.getNum()%>"
													data-stock="<%=cart.getBook().getStock()%>">
													<i class="fa fa-edit"></i>
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
					<div  style="text-align:right">
						<button type="button" class="btn btn-primary" id="makeOrders" data-id="<%=buyerId%>">Make Orders</a>
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
									<label>Num</label> <input id="input-num" class="form-control" type="number" max=""
										min="0" step="1" name="num" onkeyup="check()">
									<p id="warn" style="color:red;font-size:16px;"></p>
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
	
	<div class="modal fade" id="address" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="modalTitle">Choose one address</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group" id="addressList">
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" id="close">Close</button>
					<button type="button" class="btn btn-primary" id="generateOrder">MakeOrders</button>
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

	<script src="<%=path%>/bookstore/js/myCart.js"></script>

	<script>
		var check_val = [];
		$(document).ready(function() {
			$('#dataTables').DataTable({
				responsive : true
			});
		});
	</script>
	

</body>

</html>

