<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        
              
    </head>
    <body>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script> 
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      
      <div class="container">
        
        <div class="row">
            <form class="form-inline" id="formContact">
                <input class="form-control" type="hidden" name="id" id="id" value="0" />
                <input class="form-control" type="text" name="name" id="name" placeholder="Please, enter the name" />
                <input class="form-control" type="text" name="phone" id="phone" placeholder="Please, enter the phone number" />
                <input class="form-control" type="text" name="address" id="address" placeholder="Please, enter the address" size="50"/>
                <input class="form-control" id="btnAction" type="button" onclick="actionAdd()" value="Insert" />
            </form>
        </div>
        
        <div class="row">
        <table class="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Address</th>
                </tr>
            </thead>
            <tbody id="tbody">
                
            </tbody>
        </table>
        </div>
        
      </div>
        
        <script> 
            $(document).ready(function(){
                loadContacts();
            });
            
            function actionAdd() {
                var name = $('#name').val();
                var phone = $('#phone').val();
                var address = $('#address').val();
                addContact(name, phone, address);
            }
            
            function loadContacts() {
                $.ajax({
                    url: 'contacts/list',
                    method: 'GET',
                    success: function(result){
                        displayContact(result);
                        clearInput();
                    },
                    error: function(result) {
                        alert(result);
                    }
                });
            }
            
            function clearInput() {
                $('#name').val('');
                $('#phone').val('');
                $('#address').val('');
            }
            
            function displayContact(result) {
                var tr = "";
                for (var i=0; i<result.length; i++) {
                    tr += "<tr>" + 
                                    "<td>" + result[i].name + "</td>" +
                                    "<td>" + result[i].phone + "</td>" +
                                    "<td>" + result[i].address + "</td>" +
                                    "<td> <button onClick='deleteContact(" + result[i].id + ")'>remove</button>" +
                                    "<button onClick='editContact(" + result[i].id + ")'>edit</button> </td>" +
                            "</tr>";
                }
                $('#tbody').html(tr);                
            }
            
            function deleteContact(id) {
                $.ajax({
                    url: 'contacts/delete?id=' + id,
                    method: 'POST',
                    success: function(result) {
                        loadContacts();
                    },
                    error: function(result) {
                        alert(result);
                    }
                });
            }
            
            function addContact(name, phone, address) {
                $.ajax({
                    url: 'contacts/add',
                    method: 'POST',
                    data: {
                        "name": name,
                        "phone": phone,
                        "address": address
                    },
                    success: function(result) {
                        loadContacts();
                    },
                    error: function(result) {
                        alert(result);
                    }
                });
            }
            
            function editContact(id) {
                findContact(id);
            }
            
            function findContact(id) {
                $.ajax({
                    url: 'contacts/find?id=' + id,
                    method: 'GET',
                    success: function(result) {
                        showEditContact(result);
                    },
                    error: function(result) {
                        alert(result);
                    }
                });
            }
            
            function showEditContact(result) {
                $('#id').val(result.id);
                $('#name').val(result.name);
                $('#phone').val(result.phone);
                $('#address').val(result.address);
                
                $('#btnAction').attr('value', 'Edit');
                $('#btnAction').attr('onclick', 'updateContact()');
            }
            
            function updateContact() {
                var id = $('#id').val();
                var name = $('#name').val();
                var phone = $('#phone').val();
                var address = $('#address').val();
                
                $.ajax({
                    url: 'contacts/edit',
                    method: 'POST',
                    data: {
                        "id": id,
                        "name": name,
                        "phone": phone,
                        "address": address
                    },
                    success: function(result) {
                        loadContacts(result);
                    },
                    error: function(result) {
                        alert(result);
                    }
                }); 
            }
            
        </script>
    </body>
</html>
