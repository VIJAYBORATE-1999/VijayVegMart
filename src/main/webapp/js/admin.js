function showSection(sectionId) {
     document.querySelectorAll('.section').forEach(section => {
         section.classList.remove('active');
     });
     document.getElementById(sectionId).classList.add('active');
 }

 // Sample data
 const users = [
     {id: 1, name: 'John Doe', email: 'john@example.com', type: 'Customer'},
     {id: 2, name: 'Jane Smith', email: 'jane@example.com', type: 'Vendor'},
     {id: 3, name: 'Bob Johnson', email: 'bob@example.com', type: 'Customer'},
 ];

 const pendingUsers = [
     {id: 4, name: 'Alice Brown', email: 'alice@example.com', type: 'Vendor'},
     {id: 5, name: 'Charlie Green', email: 'charlie@example.com', type: 'Customer'},
 ];

 const inventory = [
     {vegetable: 'Tomatoes', stock: 100},
     {vegetable: 'Potatoes', stock: 150},
     {vegetable: 'Carrots', stock: 80},
     {vegetable: 'Broccoli', stock: 50},
 ];

 // Populate users table
 function populateUsersTable() {
     const usersTable = document.getElementById('usersTable');
     usersTable.innerHTML = '<tr><th>ID</th><th>Name</th><th>Email</th><th>Type</th><th>Action</th></tr>';
     users.forEach(user => {
         const row = usersTable.insertRow();
         row.insertCell(0).textContent = user.id;
         row.insertCell(1).textContent = user.name;
         row.insertCell(2).textContent = user.email;
         row.insertCell(3).textContent = user.type;
         const actionCell = row.insertCell(4);
         const editButton = document.createElement('button');
         editButton.textContent = 'Edit';
         editButton.className = 'button';
         editButton.onclick = () => editUser(user.id);
         const deleteButton = document.createElement('button');
         deleteButton.textContent = 'Delete';
         deleteButton.className = 'button';
         deleteButton.onclick = () => deleteUser(user.id);
         actionCell.appendChild(editButton);
         actionCell.appendChild(deleteButton);
     });
 }

 // Populate inventory table and chart
 function populateInventory() {
     const inventoryTable = document.getElementById('inventoryTable');
     inventoryTable.innerHTML = '<tr><th>Vegetable</th><th>Stock (kg)</th><th>Action</th></tr>';
     inventory.forEach(item => {
         const row = inventoryTable.insertRow();
         row.insertCell(0).textContent = item.vegetable;
         row.insertCell(1).textContent = item.stock;
         const actionCell = row.insertCell(2);
         const updateButton = document.createElement('button');
         updateButton.textContent = 'Update';
         updateButton.className = 'button';
         updateButton.onclick = () => updateStock(item.vegetable);
         actionCell.appendChild(updateButton);
     });

     const ctx = document.getElementById('inventoryChart').getContext('2d');
     new Chart(ctx, {
         type: 'bar',
         data: {
             labels: inventory.map(item => item.vegetable),
             datasets: [{
                 label: 'Stock (kg)',
                 data: inventory.map(item => item.stock),
                 backgroundColor: 'rgba(75, 192, 192, 0.2)',
                 borderColor: 'rgba(75, 192, 192, 1)',
                 borderWidth: 1
             }]
         },
         options: {
             scales: {
                 y: {
                     beginAtZero: true
                 }
             }
         }
     });
 }

 // Populate approvals table
 function populateApprovalsTable() {
     const approvalsTable = document.getElementById('approvalsTable');
     approvalsTable.innerHTML = '<tr><th>ID</th><th>Name</th><th>Email</th><th>Type</th><th>Action</th></tr>';
     pendingUsers.forEach(user => {
         const row = approvalsTable.insertRow();
         row.insertCell(0).textContent = user.id;
         row.insertCell(1).textContent = user.name;
         row.insertCell(2).textContent = user.email;
         row.insertCell(3).textContent = user.type;
         const actionCell = row.insertCell(4);
         const approveButton = document.createElement('button');
         approveButton.textContent = 'Approve';
         approveButton.className = 'button';
         approveButton.onclick = () => approveUser(user.id);
         const rejectButton = document.createElement('button');
         rejectButton.textContent = 'Reject';
         rejectButton.className = 'button';
         rejectButton.onclick = () => rejectUser(user.id);
         actionCell.appendChild(approveButton);
         actionCell.appendChild(rejectButton);
     });
 }

 function editUser(userId) {
     alert(`Edit user ${userId}`);
     // Implement edit functionality here
 }

 function deleteUser(userId) {
     if (confirm(`Are you sure you want to delete user ${userId}?`)) {
         // Implement delete functionality here
         alert(`User ${userId} deleted`);
         users.splice(users.findIndex(user => user.id === userId), 1);
         populateUsersTable();
     }
 }

 function updateStock(vegetable) {
     const newStock = prompt(`Enter new stock for ${vegetable}:`);
     if (newStock !== null) {
         // Implement update stock functionality here
         const index = inventory.findIndex(item => item.vegetable === vegetable);
         inventory[index].stock = parseInt(newStock);
         populateInventory();
     }
 }

 function approveUser(userId) {
     // Implement approve functionality here
     const userIndex = pendingUsers.findIndex(user => user.id === userId);
     const approvedUser = pendingUsers.splice(userIndex, 1)[0];
     users.push(approvedUser);
     populateUsersTable();
     populateApprovalsTable();
     alert(`User ${userId} approved`);
 }

 function rejectUser(userId) {
     // Implement reject functionality here
     const userIndex = pendingUsers.findIndex(user => user.id === userId);
     pendingUsers.splice(userIndex, 1);
     populateApprovalsTable();
     alert(`User ${userId} rejected`);
 }

 // Initialize the dashboard
 populateUsersTable();
 populateInventory();
 populateApprovalsTable();
 showSection('users');