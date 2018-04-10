/**
 * Created by Renato on 24/03/2018.
 */
function refreshTable(){
    var table = document.getElementById("table");
    cleanTable(table);

    xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET","logRegister.json",false);
    xmlhttp.send(null);
    var fileContent = xmlhttp.responseText;
    var fileArray = fileContent.split('\n');

    //alert(fileArray);
    var obj = JSON.parse(fileArray);

    //var obj = JSON.parse('{  "rows": { "methods": "GET",  "time": 30, "server": "localhost", "refer": "/localhost", "url": "www.test.com", "data": "mensaje=Hola"} }');

   var row = table.insertRow(table.rows.length);
   var cell = row.insertCell(0);
   cell.innerHTML = obj.rows.methods;
   /* var cell = row.insertCell(0);
    cell.innerHTML = obj.methods;
    cell = row.insertCell(1);
    cell.innerHTML = obj.time;
    cell = row.insertCell(2);
    cell.innerHTML = obj.server;
    cell = row.insertCell(3);
    cell.innerHTML = obj.refer;
    cell = row.insertCell(4);
    cell.innerHTML = obj.url;
    cell = row.insertCell(5);
    cell.innerHTML = obj.data;*/
}

function cleanTable(table) {
    for (var x=table.rows.length-1; x>0; x--) {
        table.deleteRow(x);
    }
}

$(document).ready(function() {

});